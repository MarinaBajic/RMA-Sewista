package com.sewista.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sewista.R;

public class PatternDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_details);

        setUpDetails();
    }

    private void setUpDetails() {
        int patternPosition = getIntent().getIntExtra("PatternPosition", 0);
        int[] images = getIntent().getIntArrayExtra("PatternImages");
        String patternDetailsTitle = getIntent().getStringExtra("PatternDetailsTitle");
        String patternDetailsDesc = getIntent().getStringExtra("PatternDetailsDesc");
        String patternDetailsMaterials = getIntent().getStringExtra("PatternDetailsMaterials");
        String patternDetailsInstructions = getIntent().getStringExtra("PatternDetailsInstructions");

        TextView patternDetailsTitleView = findViewById(R.id.pattern_details_title);
        TextView patternDetailsDescView = findViewById(R.id.pattern_details_desc);
        TextView patternDetailsMaterialsView = findViewById(R.id.pattern_details_materials);
        TextView patternDetailsInstructionsView = findViewById(R.id.pattern_details_instructions);
        ImageView patternDetailsImageView = findViewById(R.id.pattern_details_img);

        patternDetailsTitleView.setText(patternDetailsTitle);
        patternDetailsDescView.setText(patternDetailsDesc);
        patternDetailsMaterialsView.setText(patternDetailsMaterials);
        patternDetailsInstructionsView.setText(patternDetailsInstructions);

        if (images != null && patternPosition < images.length)
            patternDetailsImageView.setImageResource(images[patternPosition]);
        else
            patternDetailsImageView.setImageResource(R.drawable.image1);
    }
}