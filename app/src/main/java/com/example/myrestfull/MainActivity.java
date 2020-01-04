package com.example.myrestfull;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_request=findViewById(R.id.button);
        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());

                Toast.makeText(MainActivity.this, "Sent", Toast.LENGTH_SHORT).show();
                String url="https://api.github.com/users/hadley/orgs";
                StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("vw", "response: ");
                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject job= jsonArray.getJSONObject(i);
                                String jsonValue=  job.getString("login");
                                Log.d("vw", "onResponse: "+jsonValue);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.d("vw", "onErrorResponse: "+error.getMessage());
                        Toast.makeText(MainActivity.this, "Failed "+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                mRequestQueue.start();
                mRequestQueue.add(stringRequest);
                Log.d("vw", "onClick: ");

            }
        });


    }
}
