package com.chyngyz.quizapp.data.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.chyngyz.quizapp.ui.models.QuizResult;

import java.util.List;

@Dao
public interface QuizDao {
    @Insert
    void insert(QuizResult quizResult);

    @Query("SELECT * FROM qresult WHERE id=:id")
    QuizResult getById(int id);

    @Delete
    void delete(QuizResult quizResult);

    @Query("DELETE FROM qresult WHERE id = :userId")
    void deleteById(long userId);

    @Query("SELECT * FROM QResult")
    LiveData<List<QuizResult>> getAll();

    @Query("DELETE FROM qresult")
    void deleteAll();
}
