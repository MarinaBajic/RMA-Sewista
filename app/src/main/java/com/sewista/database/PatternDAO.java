package com.sewista.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sewista.model.Pattern;

import java.util.List;

@Dao
public interface PatternDAO {

    @Insert
    void addPattern(Pattern pattern);

    @Update
    void updatePattern(Pattern pattern);

    @Delete
    void deletePattern(Pattern pattern);

    @Query("select * from pattern")
    List<Pattern> getAllPatterns();

    @Query("select * from pattern where pattern_id==:pattern_id")
    Pattern getPattern(int pattern_id);
}
