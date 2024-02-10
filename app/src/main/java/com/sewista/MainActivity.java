package com.sewista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar(findViewById(R.id.toolbar));
        setNavbar(findViewById(R.id.navbar), findViewById(R.id.floating_action_button));
        replaceFragment(new HomeFragment());
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
            replaceFragment(new SettingsFragment());
        }
        if (id == R.id.action_app_info) {
            replaceFragment(new AppInfoFragment());
        }
        return true;
    }

    private void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
    }

    private void setNavbar(BottomNavigationView navbar, FloatingActionButton floatingActionButton) {
        navbar.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.action_home) {
               replaceFragment(new HomeFragment());
            }
            if (id == R.id.action_gallery) {
                replaceFragment(new PatternsFragment());
            }
            if (id == R.id.action_location) {
                replaceFragment(new MapsFragment());
            }
            if (id == R.id.action_admin) {
                replaceFragment(new AdminFragment());
            }
            return true;
        });

        floatingActionButton.setOnClickListener(view -> replaceFragment(new AddPatternFragment()));
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}