package com.example.user_pc.tbcrudsql.Dosen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.user_pc.tbcrudsql.Adapter.AdapterDosen;
import com.example.user_pc.tbcrudsql.Model.ModelData;
import com.example.user_pc.tbcrudsql.R;
import com.example.user_pc.tbcrudsql.Utama;
import com.example.user_pc.tbcrudsql.Util.AppController;
import com.example.user_pc.tbcrudsql.Util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HalamanDosen extends AppCompatActivity {

    RecyclerView mRecyclerview;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    List<ModelData> mItems;
    Button btnInsertDosen, btnDeleteDosen, btnHomeDosen;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_dosen);

        mRecyclerview  = (RecyclerView) findViewById(R.id.recyclerviewTemp);
        btnInsertDosen = (Button) findViewById(R.id.btn_insert_dosen);
        btnDeleteDosen = (Button) findViewById(R.id.btn_delete_dosen);
        btnHomeDosen   = (Button) findViewById(R.id.btn_home_dosen);
        pd = new ProgressDialog(HalamanDosen.this);
        mItems = new ArrayList<>();

        loadJson();

        mManager = new LinearLayoutManager(HalamanDosen.this,LinearLayoutManager.VERTICAL,false);
        mRecyclerview.setLayoutManager(mManager);
        mAdapter = new AdapterDosen(HalamanDosen.this,mItems);
        mRecyclerview.setAdapter(mAdapter);

        btnInsertDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanDosen.this, InsertDosen.class);
                startActivity(intent);
            }
        });

        btnDeleteDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hapus = new Intent(HalamanDosen.this, DeleteDosen.class);
                startActivity(hapus);
            }
        });

        btnHomeDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hapus = new Intent(HalamanDosen.this, Utama.class);
                startActivity(hapus);
            }
        });

    }


    private void loadJson()
    {
        pd.setMessage("Mengambil Data");
        pd.setCancelable(false);
        pd.show();

        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_VIEW_DOSEN,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        Log.d("volley","response : " + response.toString());
                        for(int i = 0 ; i < response.length(); i++)
                        {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                ModelData md = new ModelData();
                                md.setNip(data.getString("nip"));
                                md.setNama_dosen(data.getString("nama_dosen"));
                                md.setProdi_dosen(data.getString("prodi_dosen"));
                                md.setMatkul_dosen(data.getString("matkul_dosen"));
                                mItems.add(md);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "error : " + error.getMessage());
                    }
                });

        AppController.getInstance().addToRequestQueue(reqData);
    }

}
