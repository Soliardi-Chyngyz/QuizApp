package com.chyngyz.quizapp.ui.mainFragment;

import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.viewpager.widget.PagerAdapter;

import com.chyngyz.quizapp.MainActivity;
import com.chyngyz.quizapp.QuizApp;
import com.chyngyz.quizapp.R;
import com.chyngyz.quizapp.interfaces.IQuizApiCallBack;
import com.chyngyz.quizapp.ui.adapter.spinner.CustomAdapter;
import com.chyngyz.quizapp.ui.models.Category;
import com.chyngyz.quizapp.ui.models.UnderCategory;

import java.util.ArrayList;

public class MainViewModel extends ViewModel implements IQuizApiCallBack.CategoriesCallBack {
    private MutableLiveData<UnderCategory> underCategoryLiceData = new MutableLiveData<>();

    public MutableLiveData<UnderCategory> getUnderCategoryLiceData() {
        return underCategoryLiceData;
    }

    public void getUnderCategoryBack() {
        QuizApp.getInstance().getQuizRepository().getCategories(this);
    }

    @Override
    public void onSuccess(Category result) {
        if (result.getTrivia_categories() != null) {
            ArrayList<UnderCategory> categories = result.getTrivia_categories();
            for (int i = 0; i < categories.size(); i++) {
                int id = categories.get(i).getId();
                String name = categories.get(i).getName();
                UnderCategory underCategory = new UnderCategory(id, name);
                underCategoryLiceData.setValue(underCategory);
            }
        }
    }

    @Override
    public void onFailure(Exception e) {
    }
}