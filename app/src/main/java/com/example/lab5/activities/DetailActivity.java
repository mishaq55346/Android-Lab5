package com.example.lab5.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5.R;

import java.util.ArrayList;
import java.util.List;


public class DetailActivity extends AppCompatActivity {
    private LikedAdapter adapter;
    private RecyclerView recyclerView;
    private List<LikedItem> likedList=new ArrayList<LikedItem>();
    public Adapter.ExampleViewHolder data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view_like);
        LinearLayoutManager linearLayout=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        likedList = Singleton.getInstance().getItems();
        adapter=new LikedAdapter(DetailActivity.this, likedList);
        recyclerView.setAdapter(adapter);
    }
}
