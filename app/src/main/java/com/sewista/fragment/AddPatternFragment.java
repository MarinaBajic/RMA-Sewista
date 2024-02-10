package com.sewista.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sewista.model.Pattern;
import com.sewista.database.PatternDAO;
import com.sewista.database.PatternDatabase;
import com.sewista.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddPatternFragment extends Fragment {

    private PatternDAO patternDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_pattern, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PatternDatabase patternDatabase = PatternDatabase.getInstance(requireContext());
        patternDAO = patternDatabase.getPatternDAO();

        savePattern(view, view.findViewById(R.id.add_pattern_btn));
    }

    private void savePattern(View view, Button saveBtn) {
        saveBtn.setOnClickListener(v -> {
            EditText titleView = view.findViewById(R.id.add_pattern_title);
            String title = titleView.getText().toString();

            EditText descView = view.findViewById(R.id.add_pattern_desc);
            String desc = descView.getText().toString();

            EditText materialsView = view.findViewById(R.id.add_pattern_materials);
            String materials = materialsView.getText().toString();

            EditText instructionsView = view.findViewById(R.id.add_pattern_instructions);
            String instructions = instructionsView.getText().toString();

            if (title.isEmpty() || desc.isEmpty() || materials.isEmpty() || instructions.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }
            else {
                Pattern pattern = new Pattern(title, desc, materials, instructions);
                savePatternInBackground(pattern);
            }
        });
    }

    public void savePatternInBackground(Pattern pattern) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(() -> {
            patternDAO.addPattern(pattern);
            handler.post(() -> Toast.makeText(requireContext(), "Added to Database", Toast.LENGTH_SHORT).show());
        });
    }
}