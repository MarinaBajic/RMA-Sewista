package com.sewista;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddPatternFragment extends Fragment {

    private PatternDatabase patternDatabase;
    List<Pattern> patternList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_pattern, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };

        patternDatabase = Room.databaseBuilder(getContext().getApplicationContext(), PatternDatabase.class, "Pattern Database").addCallback(roomCallback).build();

        savePattern(view.findViewById(R.id.add_pattern_title),
                    view.findViewById(R.id.add_pattern_desc),
                    view.findViewById(R.id.add_pattern_materials),
                    view.findViewById(R.id.add_pattern_instructions),
                    view.findViewById(R.id.add_pattern_btn));

        getPatterns(view.findViewById(R.id.get_patterns_btn));
    }

    private void getPatterns(Button getBtn) {
        getBtn.setOnClickListener(view -> getPatternListInBackground());
    }

    private void savePattern(EditText titleEdit, EditText descEdit, EditText materialsEdit, EditText instructionsEdit, Button saveBtn) {
        saveBtn.setOnClickListener(view -> {
            String title = titleEdit.getText().toString();
            String desc = descEdit.getText().toString();
            String materials = materialsEdit.getText().toString();
            String instructions = instructionsEdit.getText().toString();

            Pattern pattern = new Pattern(title, desc, materials, instructions, 1);
            savePatternInBackground(pattern);
        });
    }

    public void savePatternInBackground(Pattern pattern) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(() -> {
            patternDatabase.getPatternDAO().addPattern(pattern);

            handler.post(() -> Toast.makeText(requireContext(), "Added to Database", Toast.LENGTH_SHORT).show());
        });
    }

    public void getPatternListInBackground() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(() -> {
            patternList = patternDatabase.getPatternDAO().getAllPatterns();

            handler.post(() -> {
                StringBuilder sb = new StringBuilder();
                for (Pattern pattern: patternList) {
                    sb.append(pattern.getTitle());
                    sb.append("\n");
                }
                String finalData = sb.toString();
                Toast.makeText(requireContext(), finalData, Toast.LENGTH_SHORT).show();
            });
        });
    }
}