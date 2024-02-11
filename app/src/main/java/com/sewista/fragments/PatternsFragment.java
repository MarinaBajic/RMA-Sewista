package com.sewista.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sewista.database.PatternDAO;
import com.sewista.recycler.MyAdapter;
import com.sewista.model.Pattern;
import com.sewista.database.PatternDatabase;
import com.sewista.activity.PatternDetailsActivity;
import com.sewista.R;
import com.sewista.recycler.RecyclerViewInterface;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PatternsFragment extends Fragment implements RecyclerViewInterface {

    private List<Pattern> patternList;
    private int[] images;
    private PatternDAO patternDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_patterns, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PatternDatabase patternDatabase = PatternDatabase.getInstance(requireContext());
        patternDAO = patternDatabase.getPatternDAO();

        images = new int[] {
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4,
                R.drawable.image5,
                R.drawable.image6
        };

        setListOfPatterns(view.findViewById(R.id.all_patterns));
    }

    private void setListOfPatterns(RecyclerView recyclerView) {
        getPatternListInBackground(recyclerView);
    }

    public void getPatternListInBackground(RecyclerView recyclerView) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(() -> {
            patternList = patternDAO.getAllPatterns();

            handler.post(() -> {
                MyAdapter myAdapter = new MyAdapter(images, patternList, this);
                int spanCount = getString(R.string.screen_type).equals("phone") ? 1 : 3;
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), spanCount);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(myAdapter);
            });
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), PatternDetailsActivity.class);

        intent.putExtra("PatternPosition", position);
        intent.putExtra("PatternImages", images);
        intent.putExtra("PatternDetailsTitle", patternList.get(position).getTitle());
        intent.putExtra("PatternDetailsDesc", patternList.get(position).getDesc());
        intent.putExtra("PatternDetailsMaterials", patternList.get(position).getMaterials());
        intent.putExtra("PatternDetailsInstructions", patternList.get(position).getInstructions());

        startActivity(intent);
    }
}