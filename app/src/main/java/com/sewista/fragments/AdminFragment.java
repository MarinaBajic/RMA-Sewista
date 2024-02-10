package com.sewista.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sewista.recycler.AdminAdapterListener;
import com.sewista.recycler.MyAdminAdapter;
import com.sewista.model.Pattern;
import com.sewista.database.PatternDAO;
import com.sewista.database.PatternDatabase;
import com.sewista.R;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AdminFragment extends Fragment implements AdminAdapterListener {

    private List<Pattern> patternList;
    private MyAdminAdapter myAdminAdapter;
    private PatternDatabase patternDatabase;
    private PatternDAO patternDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        patternDatabase = PatternDatabase.getInstance(requireContext());
        patternDAO = patternDatabase.getPatternDAO();

        setListOfPatterns(view);
    }

    private void setListOfPatterns(View view) {
        getPatternListInBackground(view.findViewById(R.id.all_patterns_admin));
    }

    public void getPatternListInBackground(RecyclerView recyclerView) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(() -> {
            patternList = patternDatabase.getPatternDAO().getAllPatterns();

            handler.post(() -> {
                myAdminAdapter = new MyAdminAdapter(patternList, this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(myAdminAdapter);
            });
        });
    }

    @Override
    public void onUpdate(Pattern pattern, int position) {
        // TODO
    }

    @Override
    public void onDelete(Pattern pattern, int position) {
        deletePatternInBackground(pattern);
        myAdminAdapter.removePattern(position);
    }

    private void deletePatternInBackground(Pattern pattern) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(() -> {
            patternDAO.deletePattern(pattern);
            handler.post(() -> Toast.makeText(requireContext(), "Deleted pattern", Toast.LENGTH_SHORT).show());
        });
    }
}