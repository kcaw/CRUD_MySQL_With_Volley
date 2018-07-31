package com.example.user_pc.tbcrudsql.Matakuliah;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.user_pc.tbcrudsql.R;
import com.example.user_pc.tbcrudsql.Util.AppController;
import com.example.user_pc.tbcrudsql.Util.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InsertMatkul extends AppCompatActivity {

    EditText id_matkul,matkul,prodi_matkul,dosen_matkul;
    Button btnbatalMatkul,btnsimpanMatkul;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_matkul);

        /*get data from intent*/

        Intent data = getIntent();
        final int update = data.getIntExtra("update",0);
        String intent_id_matkul     = data.getStringExtra("id_matkul");
        String intent_matkul        = data.getStringExtra("matkul");
        String intent_prodi_matkul  = data.getStringExtra("prodi_matkul");
        String intent_dosen_matkul  = data.getStringExtra("dosen_matkul");
        /*end get data from intent*/

        id_matkul         = (EditText) findViewById(R.id.inp_id_matkul);
        matkul            = (EditText) findViewById(R.id.inp_matkul);
        prodi_matkul      = (EditText) findViewById(R.id.inp_prodi_matkul);
        dosen_matkul      = (EditText) findViewById(R.id.inp_dosen_matkul);
        btnbatalMatkul    = (Button) findViewById(R.id.btn_cancelMatkul);
        btnsimpanMatkul   = (Button) findViewById(R.id.btn_simpanMatkul);
        pd                = new ProgressDialog(InsertMatkul.this);
        /*kondisi update / insert*/
        if(update == 1)
        {
            btnsimpanMatkul.setText("update Data");
            id_matkul.setText(intent_id_matkul);
            id_matkul.setVisibility(View.GONE);
            matkul.setText(intent_matkul);
            prodi_matkul.setText(intent_prodi_matkul);
            dosen_matkul.setText(intent_dosen_matkul);

        }

        btnsimpanMatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(update == 1)
                {
                    Update_data();
                }else {
                    simpanData();
                }
            }
        });

        btnbatalMatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(InsertMatkul.this,HalamanMatakuliah.class);
                startActivity(main);
            }
        });
    }


    private void Update_data()
    {
        pd.setMessage("Update Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.URL_UPDATE_MATKUL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertMatkul.this, "pesan : "+  res.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertMatkul.this,HalamanMatakuliah.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertMatkul.this, "Pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("id_matkul",id_matkul.getText().toString());
                map.put("matkul",matkul.getText().toString());
                map.put("prodi_matkul",prodi_matkul.getText().toString());
                map.put("dosen_matkul",dosen_matkul.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(updateReq);
    }

    private void simpanData()
    {
        pd.setMessage("Menyimpan Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_INSERT_MATKUL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertMatkul.this, "pesan : "+  res.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertMatkul.this,HalamanMatakuliah.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertMatkul.this, "Pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("id_matkul",id_matkul.getText().toString());
                map.put("matkul",matkul.getText().toString());
                map.put("prodi_matkul",prodi_matkul.getText().toString());
                map.put("dosen_matkul",dosen_matkul.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }
}