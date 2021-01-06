package com.chyngyz.quizapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class MineViewPager extends ViewPager {

    private boolean isPagingEnabled = true;

    public MineViewPager(@NonNull Context context) {
        super(context);
    }

    public MineViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isPagingEnabled)
            return super.onTouchEvent(ev);
        else
            return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isPagingEnabled)
            return super.onInterceptTouchEvent(ev);
        else
            return false;
    }

    public void setPagingScrollEnabled(boolean a) {
        this.isPagingEnabled = a;
    }
}
