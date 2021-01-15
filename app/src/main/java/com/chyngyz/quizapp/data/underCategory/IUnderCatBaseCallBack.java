package com.chyngyz.quizapp.data.underCategory;

public interface IUnderCatBaseCallBack<T> {
    void onItemSelected (T result);

    void onNothingSelected(Exception e);
}
