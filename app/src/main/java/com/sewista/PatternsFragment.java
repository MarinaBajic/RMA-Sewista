package com.sewista;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PatternsFragment extends Fragment implements RecyclerViewInterface {

    private List<Pattern> patternList;

    private PatternDatabase patternDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_patterns, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
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

        setListOfPatterns(view.findViewById(R.id.all_patterns));
    }

    private void setListOfPatterns(RecyclerView recyclerView) {
        getPatternListInBackground(recyclerView);
    }

    public void getPatternListInBackground(RecyclerView recyclerView) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(() -> {
            patternList = patternDatabase.getPatternDAO().getAllPatterns();

            handler.post(() -> {
                MyAdapter myAdapter = new MyAdapter(patternList, this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(myAdapter);
            });
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), PatternDetailsActivity.class);

        intent.putExtra("PatternId", patternList.get(position).getId());
        intent.putExtra("PatternDetailsTitle", patternList.get(position).getTitle());
        intent.putExtra("PatternDetailsDesc", patternList.get(position).getDesc());
        intent.putExtra("PatternDetailsMaterials", patternList.get(position).getMaterials());
        intent.putExtra("PatternDetailsInstructions", patternList.get(position).getInstructions());

        startActivity(intent);
    }
}