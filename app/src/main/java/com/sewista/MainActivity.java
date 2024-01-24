package com.sewista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar(findViewById(R.id.toolbar));
        setListOfPatterns(findViewById(R.id.all_patterns));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, "You clicked settings", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.action_app_info) {
            Toast.makeText(this, "You clicked app info", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void setListOfPatterns(RecyclerView recyclerView) {
        List<Pattern> patterns = new ArrayList<>();
        MyAdapter myAdapter = new MyAdapter(this, patterns);

        patterns.add(new Pattern("Classic A-Line Skirt", "Timeless and versatile, this pattern guides you in creating a flattering A-line skirt that suits any occasion.", R.drawable.image1));
        patterns.add(new Pattern("Boho-Chic Maxi Dress", "Embrace bohemian style with this flowy maxi dress pattern. Perfect for summer days or special events.", R.drawable.image2));
        patterns.add(new Pattern("Quilted Pillow", " Dive into quilting with this delightful patchwork pillow pattern. Explore color combinations, practice.", R.drawable.image3));
        patterns.add(new Pattern("Modern Bag", "Elevate your accessory game with a modern crossbody bag. This pattern teaches you the fundamentals of bag construction.", R.drawable.image4));
        patterns.add(new Pattern("Cozy Flannel Pajamas", "Snuggle up in a handmade flannel pajama set! This pattern covers everything from selecting cozy fabrics .", R.drawable.image5));
        patterns.add(new Pattern("Embroidered Towels", "Add a personal touch to your kitchen with embroidered tea towels.", R.drawable.image6));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);
    }

    private void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
    }
}