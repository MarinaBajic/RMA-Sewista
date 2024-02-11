package com.sewista.fragments;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
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
            handler.post(() -> {
                Toast.makeText(requireContext(), "Added to Database", Toast.LENGTH_SHORT).show();
                makeNotification();
            });
        });
    }

    private void makeNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(requireActivity(),
                        new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }

        String channelId = "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext().getApplicationContext(), channelId);
        builder.setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("New pattern!")
                .setContentText("You have successfully added a new pattern to the database")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) requireContext().getSystemService(Context.NOTIFICATION_SERVICE);
        
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channelId);
            if (notificationChannel == null) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel notificationChannelNew = new NotificationChannel(channelId, "Description", importance);
                notificationChannelNew.setLightColor(Color.DKGRAY);
                notificationChannelNew.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannelNew);
            }
        }
        notificationManager.notify(0, builder.build());
    }
}