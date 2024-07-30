package com.example.lab5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LAB7MainActivity extends AppCompatActivity {
    EditText txt1,txt2,txt3;
    Button btnSelect , btn2;
    TextView tvKQ;
    Context context = this;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab7_main);
        txt1=findViewById(R.id.lab7Txt1);
        txt2=findViewById(R.id.lab7Txt2);
        txt3=findViewById(R.id.lab7Txt3);
        btnSelect=findViewById(R.id.lab71BtnSelect);
        btn2=findViewById(R.id.lab7Btn2);
        tvKQ=findViewById(R.id.lab7TvKQ);
        btnSelect.setOnClickListener(v->{
            selectVolley();
        });
        btn2.setOnClickListener(v->{

        });
    }
    String strKQ="";
    private void selectVolley(){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url="https://hungnq28.000webhostapp.com/su2024/select.php";

        JsonObjectRequest request1 = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("products");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject p = jsonArray.getJSONObject(i);//lay ve doi tuong i
                        String MaSP = p.getString("MaSP");
                        String TenSP = p.getString("TenSP");
                        String MoTa = p.getString("MoTa");
                        strKQ += "MaSP: " + MaSP + "; TenSP:" + TenSP + "; MoTa: " + MoTa + "\n";
                    }
                    tvKQ.setText(strKQ);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvKQ.setText(error.getMessage());
            }
    });
        queue.add(request1);
    }
}