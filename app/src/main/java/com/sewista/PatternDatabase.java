package com.sewista;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Pattern.class}, version = 1)
public abstract class PatternDatabase extends RoomDatabase {

    public abstract PatternDAO getPatternDAO();
}
