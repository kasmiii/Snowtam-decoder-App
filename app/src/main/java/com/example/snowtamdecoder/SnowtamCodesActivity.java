package com.example.snowtamdecoder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SnowtamCodesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snowtam_codes);

        GetDataSnotam serviceStream = RetrofitClientSnotam.getRetrofitInstance().create(GetDataSnotam.class);

        System.out.println("begin sending request to server...");
        Call<List<RetroSnowtam>> call = serviceStream.getStreams("ENGM");

        call.enqueue(new Callback<List<RetroSnowtam>>() {
                         @Override
                         public void onResponse(Call<List<RetroSnowtam>> call, Response<List<RetroSnowtam>> response) {
                             System.out.println("response is ::"+response.body().get(2).getAll());
                         }
                         @Override
                         public void onFailure(Call<List<RetroSnowtam>> call, Throwable t) {
                             System.out.println("error during requesting server...");
                         }
                     }
        );
    }

    private void loadDataList(List<RetroSnowtam> usersList) {

        //Get a reference to the RecyclerView//
        /*myRecyclerView = this.findViewById(R.id.myRecyclerView);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(TestActivity.this, RecyclerView.VERTICAL,false));
        myAdapter = new UserAdapter(TestActivity.this,usersList);
        myRecyclerView.setAdapter(myAdapter);*/
    }
}