package com.example.lab5.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.lab5.EndlessRecyclerViewScrollListener;
import com.example.lab5.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final String JSON_URL = "https://api.thecatapi.com/v1/images/search?limit=13";
    private RecyclerView mRecyclerView;
    private Adapter mExampleAdapter;
    private ArrayList<Item> mExampleList;
    private RequestQueue mRequestQueue;
    private EndlessRecyclerViewScrollListener scrollListener;
    private Button nextActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayout=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayout);
        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
        scrollListener =  new EndlessRecyclerViewScrollListener(linearLayout) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                parseJSON();
            }
        };
        mRecyclerView.addOnScrollListener(scrollListener);
        nextActivity=(Button)findViewById(R.id.next_btn);
        nextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
    }
    private void parseJSON() {
        JsonArrayRequest request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        String imageUrl = jsonObject.getString("url");
                        mExampleList.add(new Item(imageUrl));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mExampleAdapter = new Adapter(MainActivity.this, mExampleList);
                    mRecyclerView.setAdapter(mExampleAdapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        mRequestQueue.add(request);
    }
}