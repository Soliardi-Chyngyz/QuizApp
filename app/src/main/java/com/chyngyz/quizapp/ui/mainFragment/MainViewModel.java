package com.chyngyz.quizapp.ui.mainFragment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chyngyz.quizapp.QuizApp;
import com.chyngyz.quizapp.interfaces.IQuizApiCallBack;
import com.chyngyz.quizapp.ui.models.Category;
import com.chyngyz.quizapp.ui.models.UnderCategory;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel implements IQuizApiCallBack.CategoriesCallBack {
    private MutableLiveData<List<UnderCategory>> underCategoryLiceData = new MutableLiveData<>();

    public MutableLiveData<List<UnderCategory>> getUnderCategoryLiceData() {
        return underCategoryLiceData;
    }

    public void getUnderCategoryBack() {
        QuizApp.getInstance().getQuizRepository().getCategories(this);
    }

    @Override
    public void onSuccess(Category result) {
        List<UnderCategory> list = new ArrayList<>();
        if (result.getTrivia_categories() != null) {
            ArrayList<UnderCategory> categories = result.getTrivia_categories();
            for (int i = 0; i < categories.size(); i++) {
                int id = categories.get(i).getId();
                String name = categories.get(i).getName();
                UnderCategory underCategory = new UnderCategory(id, name);
                list.add(underCategory);

            }
            UnderCategory anyCat = new UnderCategory(8, "Any-type");
            list.add(0, anyCat);
            underCategoryLiceData.setValue(list);
        }

    }

    @Override
    public void onFailure(Exception e) {
    }
}