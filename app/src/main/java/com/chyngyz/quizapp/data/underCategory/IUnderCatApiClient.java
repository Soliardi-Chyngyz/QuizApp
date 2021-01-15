package com.chyngyz.quizapp.data.underCategory;

import com.chyngyz.quizapp.ui.models.UnderCategory;

import java.util.List;

public interface IUnderCatApiClient {
    void getUnderCatData(UnderCatCallBack underCatCallBack);

    interface UnderCatCallBack extends IUnderCatBaseCallBack<List<UnderCategory>>{
        @Override
        void onItemSelected(List<UnderCategory> result);

        @Override
        void onNothingSelected(Exception e);
    }
}
