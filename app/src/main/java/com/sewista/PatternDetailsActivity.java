package com.sewista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PatternDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_details);

        setUpDetails();
    }

    private void setUpDetails() {
        String patternDetailsTitle = getIntent().getStringExtra("PatternDetailsTitle");
        String patternDetailsDesc = getIntent().getStringExtra("PatternDetailsDesc");
        String patternDetailsMaterials = getIntent().getStringExtra("PatternDetailsMaterials");
        String patternDetailsInstructions = getIntent().getStringExtra("PatternDetailsInstructions");
        int patternDetailsImage = getIntent().getIntExtra("PatternDetailsImage", 1);

        TextView patternDetailsTitleView = findViewById(R.id.pattern_details_title);
        TextView patternDetailsDescView = findViewById(R.id.pattern_details_desc);
        TextView patternDetailsMaterialsView = findViewById(R.id.pattern_details_materials);
        TextView patternDetailsInstructionsView = findViewById(R.id.pattern_details_instructions);
        ImageView patternDetailsImageView = findViewById(R.id.pattern_details_img);

        patternDetailsTitleView.setText(patternDetailsTitle);
        patternDetailsDescView.setText(patternDetailsDesc);
        patternDetailsMaterialsView.setText(patternDetailsMaterials);
        patternDetailsInstructionsView.setText(patternDetailsInstructions);
        patternDetailsImageView.setImageResource(patternDetailsImage);
    }
}