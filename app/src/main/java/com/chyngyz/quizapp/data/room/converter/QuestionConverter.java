package com.chyngyz.quizapp.data.room.converter;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;
import com.chyngyz.quizapp.ui.models.Question;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class QuestionConverter {
    @TypeConverter
    public static String toRaw (@Nullable List<Question> questions){
        if(questions == null)
            return null;
        Gson gson = new Gson();
        Type type = new TypeToken<List<Question>>() {}.getType(); // указываем какой тиип данных конвертируем
        return gson.toJson(questions, type);
    }

    @TypeConverter
    public static List<Question> fromRaw(@Nullable String raw){
        if(raw == null)
            return null;
        Gson gson = new Gson();
        Type type = new TypeToken<List<Question>>() {}.getType();
        return gson.fromJson(raw, type);
    }
}
