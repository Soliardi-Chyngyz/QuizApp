package com.chyngyz.quizapp.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.chyngyz.quizapp.ui.models.QuizResult;

@Database(entities = {QuizResult.class}, version = 1)
public abstract class QuizDataBase extends RoomDatabase {
    public abstract QuizDao quizDao();
}
