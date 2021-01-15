package com.chyngyz.quizapp.data.underCategory;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.chyngyz.quizapp.ui.models.UnderCategory;

import java.util.List;

public class UnderCatAdaperView extends AdapterView implements IUnderCatApiClient.UnderCatCallBack {

    public UnderCatAdaperView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public Adapter getAdapter() {
        return null;
    }

    @Override
    public void setAdapter(Adapter adapter) {

    }

    @Override
    public View getSelectedView() {
        return null;
    }

    @Override
    public void setSelection(int position) {

    }

    @Override
    public void onItemSelected(List<UnderCategory> result) {

    }

    @Override
    public void onNothingSelected(Exception e) {

    }
}
