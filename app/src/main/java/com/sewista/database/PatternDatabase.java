package com.sewista.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.sewista.model.Pattern;

@Database(
        entities = {Pattern.class},
        version = 1)
public abstract class PatternDatabase extends RoomDatabase {

    public abstract PatternDAO getPatternDAO();

    public static PatternDatabase INSTANCE;

    public static PatternDatabase getInstance(Context context) {

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

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, PatternDatabase.class, "Pattern Database")
                    .addCallback(roomCallback)
                    .build();
        }

        return INSTANCE;
    }
}
