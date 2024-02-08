package com.sewista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    private List<Pattern> patterns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar(findViewById(R.id.toolbar));
        setListOfPatterns(findViewById(R.id.all_patterns));
        setNavbar(findViewById(R.id.navbar));



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
            // TODO
            Toast.makeText(this, "You clicked settings", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.action_app_info) {
            // TODO
            Toast.makeText(this, "You clicked app info", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void setListOfPatterns(RecyclerView recyclerView) {
        patterns = new ArrayList<>();
        MyAdapter myAdapter = new MyAdapter(this, patterns, this);

        patterns.add(new Pattern(
                "Floral Sundress",
                "A flowy sundress featuring a floral print, perfect for summer outings or beach days.",
                "Cotton fabric, thread, zipper, interfacing.",
                "1. Cut fabric pieces according to the provided pattern template.\n" +
                            "2. Sew bodice pieces together and attach the zipper.\n" +
                            "3. Gather and attach the skirt to the bodice.\n" +
                            "4. Hem the bottom edge of the dress.\n" +
                            "5. Add straps and finish edges.\n" +
                            "6. Press seams and finish with top stitching.",
                R.drawable.image1
        ));
        patterns.add(new Pattern(
                "Quilted Tote Bag",
                "A spacious tote bag with quilted panels, ideal for carrying groceries or everyday essentials.",
                "Quilting cotton, batting, lining fabric, thread, magnetic snap.",
                "1. Cut fabric and batting pieces for the bag exterior and lining.\n" +
                            "2. Quilt the exterior panels as desired.\n" +
                            "3. Assemble the bag exterior and lining, leaving an opening for turning.\n" +
                            "4. Attach the magnetic snap closure.\n" +
                            "5. Box the corners and tops titch the bag.\n" +
                            "6. Turn the bag right side out and press seams.",
                R.drawable.image2));
        patterns.add(new Pattern(
                "Ruffled Pillow Covers",
                "Decorative pillow covers with ruffled edges, perfect for adding a touch of charm to any room.",
                "Home decor fabric, thread, zipper, ruffling foot (optional).",
                "1. Cut fabric pieces for the front and back of the pillow covers.\n" +
                            "2. Create ruffles and attach them to the front panel.\n" +
                            "3. Insert the zipper along one edge of the back panel.\n" +
                            "4. Sew the front and back panels together, right sides facing.\n" +
                            "5. Clip corners and turn the covers right side out.\n" +
                            "6. Press seams and insert pillow forms.",
                R.drawable.image3));
        patterns.add(new Pattern(
                "Patchwork Coasters",
                "Set of four patchwork coasters, perfect for protecting surfaces from hot or cold drinks.",
                "Cotton fabric scraps, batting, thread.",
                "1. Cut fabric and batting into squares for the coaster tops and backs.\n" +
                            "2. Arrange fabric squares into desired patchwork design.\n" +
                            "3. Sew fabric squares together to create coaster tops.\n" +
                            "4. Layer coaster tops with batting and backing fabric.\n" +
                            "5. Quilt as desired and trim excess fabric.\n" +
                            "6. Bind coaster edges using double-fold binding.",
                R.drawable.image4));
        patterns.add(new Pattern(
                "Bow Tie Hairband",
                "Stylish hairband featuring a bow tie detail, perfect for adding flair to any hairstyle.",
                "Cotton fabric, elastic, thread.",
                "1. Cut fabric pieces for the hairband and bow tie.\n" +
                            "2. Sew hairband pieces together, leaving an opening for turning.\n" +
                            "3. Turn hairband right side out and press seams.\n" +
                            "4. Sew bow tie pieces together, leaving an opening for turning.\n" +
                            "5. Turn bow tie right side out and press seams.\n" +
                            "6. Attach bow tie to the hairband and sew elastic ends together.",
                R.drawable.image5));
        patterns.add(new Pattern(
                "Piped Throw Pillow",
                "Decorative throw pillow with piped edges, perfect for adding a pop of color to any sofa or bed.",
                "Home decor fabric, piping cord, zipper, thread.",
                "1. Cut fabric pieces for the front and back of the pillow cover.\n" +
                            "2. Sew piping to the edges of the front panel.\n" +
                            "3. Insert the zipper along one edge of the back panel.\n" +
                            "4. Sew front and back panels together, right sides facing.\n" +
                            "5. Clip corners and turn the cover right side out.\n" +
                            "6. Press seams and insert pillow form.",
                R.drawable.image6));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);
    }

    private void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
    }

    private void setNavbar(BottomNavigationView navbar) {
        navbar.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.action_home) {
               // TODO
            }
            if (id == R.id.action_gallery) {
                // TODO
            }
            if (id == R.id.action_location) {
                // TODO
            }
            return true;
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, PatternDetailsActivity.class);

        intent.putExtra("PatternDetailsTitle", patterns.get(position).getTitle());
        intent.putExtra("PatternDetailsDesc", patterns.get(position).getDesc());
        intent.putExtra("PatternDetailsMaterials", patterns.get(position).getMaterials());
        intent.putExtra("PatternDetailsInstructions", patterns.get(position).getInstructions());
        intent.putExtra("PatternDetailsImage", patterns.get(position).getImage());

        startActivity(intent);
    }
}