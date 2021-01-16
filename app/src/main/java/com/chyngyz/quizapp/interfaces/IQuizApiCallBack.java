package com.chyngyz.quizapp.interfaces;

import com.chyngyz.quizapp.core.IBaseCallBack;
import com.chyngyz.quizapp.ui.models.Category;
import com.chyngyz.quizapp.ui.models.Question;
import com.chyngyz.quizapp.ui.models.UnderCategory;

import java.util.ArrayList;
import java.util.List;

public interface IQuizApiCallBack {

    interface  QuestionsCallBask extends IBaseCallBack<ArrayList<Question>> {
        @Override
        void onSuccess(ArrayList<Question> result);

        @Override
        void onFailure(Exception e);
    }

    interface CategoriesCallBack extends IBaseCallBack<Category>{

        @Override
        void onSuccess(Category result);

        @Override
        void onFailure(Exception e);
    }
}
