package com.example.user_pc.tbcrudsql.Matakuliah;

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
import com.example.user_pc.tbcrudsql.Adapter.AdapterMatkul;
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

public class HalamanMatakuliah extends AppCompatActivity {

    RecyclerView mRecyclerview;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    List<ModelData> mItems;
    Button btnInsertMatkul, btnDeleteMatkul, btnHomeMatkul;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_matakuliah);

        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerviewTemp);
        btnInsertMatkul = (Button) findViewById(R.id.btn_insertMatkul);
        btnDeleteMatkul = (Button) findViewById(R.id.btn_deleteMatkul);
        btnHomeMatkul   = (Button) findViewById(R.id.btn_homeMatkul);
        pd = new ProgressDialog(HalamanMatakuliah.this);
        mItems = new ArrayList<>();

        loadJson();

        mManager = new LinearLayoutManager(HalamanMatakuliah.this,LinearLayoutManager.VERTICAL,false);
        mRecyclerview.setLayoutManager(mManager);
        mAdapter = new AdapterMatkul(HalamanMatakuliah.this,mItems);
        mRecyclerview.setAdapter(mAdapter);

        btnInsertMatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanMatakuliah.this, InsertMatkul.class);
                startActivity(intent);
            }
        });

        btnDeleteMatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hapus = new Intent(HalamanMatakuliah.this, DeleteMatkul.class);
                startActivity(hapus);
            }
        });

        btnHomeMatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hapus = new Intent(HalamanMatakuliah.this, Utama.class);
                startActivity(hapus);
            }
        });

    }


    private void loadJson()
    {
        pd.setMessage("Mengambil Data");
        pd.setCancelable(false);
        pd.show();

        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_VIEW_MATKUL,null,
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
                                md.setId_matkul(data.getString("id_matkul"));
                                md.setMatkul(data.getString("matkul"));
                                md.setProdi_matkul(data.getString("prodi_matkul"));
                                md.setDosen_matkul(data.getString("dosen_matkul"));
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
