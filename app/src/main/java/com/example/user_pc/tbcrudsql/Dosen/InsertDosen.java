package com.example.user_pc.tbcrudsql.Dosen;

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

public class InsertDosen extends AppCompatActivity {

    EditText nip,nama_dosen,prodi_dosen,matkul_dosen;
    Button btnbatalDosen,btnsimpanDosen;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_dosen);

        /*get data from intent*/

        Intent data = getIntent();
        final int update = data.getIntExtra("update",0);
        String intent_nip = data.getStringExtra("nip");
        String intent_nama_dosen = data.getStringExtra("nama_dosen");
        String intent_prodi_dosen = data.getStringExtra("prodi_dosen");
        String intent_matkul_dosen = data.getStringExtra("matkul_dosen");
        /*end get data from intent*/

        nip             = (EditText) findViewById(R.id.inp_nip);
        nama_dosen      = (EditText) findViewById(R.id.inp_nama_dosen);
        prodi_dosen     = (EditText) findViewById(R.id.inp_prodi_dosen);
        matkul_dosen    = (EditText) findViewById(R.id.inp_matkul_dosen);
        btnbatalDosen   = (Button) findViewById(R.id.btn_cancelDosen);
        btnsimpanDosen  = (Button) findViewById(R.id.btn_simpanDosen);
        pd              = new ProgressDialog(InsertDosen.this);

        /*kondisi update / insert*/
        if(update == 1)
        {
            btnsimpanDosen.setText("update Data");
            nip.setText(intent_nip);
            nip.setVisibility(View.GONE);
            nama_dosen.setText(intent_nama_dosen);
            prodi_dosen.setText(intent_prodi_dosen);
            matkul_dosen.setText(intent_matkul_dosen);

        }

        btnsimpanDosen.setOnClickListener(new View.OnClickListener() {
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

        btnbatalDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(InsertDosen.this,HalamanDosen.class);
                startActivity(main);
            }
        });
    }


    private void Update_data()
    {
        pd.setMessage("Update Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.URL_UPDATE_DOSEN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertDosen.this, "pesan : "+  res.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertDosen.this,HalamanDosen.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertDosen.this, "Pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("nip",nip.getText().toString());
                map.put("nama_dosen",nama_dosen.getText().toString());
                map.put("prodi_dosen",prodi_dosen.getText().toString());
                map.put("matkul_dosen",matkul_dosen.getText().toString());

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

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_INSERT_DOSEN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertDosen.this, "pesan : "+  res.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertDosen.this,HalamanDosen.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertDosen.this, "Pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("nip",nip.getText().toString());
                map.put("nama_dosen",nama_dosen.getText().toString());
                map.put("prodi_dosen",prodi_dosen.getText().toString());
                map.put("matkul_dosen",matkul_dosen.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }
}