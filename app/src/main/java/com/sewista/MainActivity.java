package com.sewista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Pattern> patterns;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        patterns = new ArrayList<>();
        myAdapter = new MyAdapter(this, patterns);

        patterns.add(new Pattern("Title 1", "Desc 1", R.drawable.image1));
        patterns.add(new Pattern("Title 2", "Desc 2", R.drawable.image2));
        patterns.add(new Pattern("Title 3", "Desc 3", R.drawable.image3));
        patterns.add(new Pattern("Title 4", "Desc 4", R.drawable.image4));
        patterns.add(new Pattern("Title 5", "Desc 5", R.drawable.image5));
        patterns.add(new Pattern("Title 6", "Desc 6", R.drawable.image6));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);
    }
}