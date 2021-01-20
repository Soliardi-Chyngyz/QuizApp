package com.chyngyz.quizapp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.chyngyz.quizapp.R;
import com.chyngyz.quizapp.databinding.QuizItemBinding;
import com.chyngyz.quizapp.interfaces.AnimationShort;
import com.chyngyz.quizapp.interfaces.OnButtonAnswerClick;
import com.chyngyz.quizapp.ui.models.Question;
import com.chyngyz.quizapp.ui.question.QuestionActivity;

import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizVH> {

    private ArrayList<Question> list;

    public QuizAdapter(ArrayList<Question> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public QuizVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuizItemBinding binding = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_item, parent, false));
        return new QuizVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizVH holder, int position) {
        holder.binding.setQuestion(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class QuizVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        private int currentPosition = getAdapterPosition() + 1;
        private QuizItemBinding binding;

        @SuppressLint("ClickableViewAccessibility")
        public QuizVH(@NonNull QuizItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.qBtn1.setOnClickListener(this);
            binding.qBtn2.setOnClickListener(this);
            binding.qBtn3.setOnClickListener(this);
            binding.qBtn4.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.q_btn1:
                    checkAnswer(binding.qBtn1.getText().toString(), binding.qBtn1);
                    updateData();
                    break;
                case R.id.q_btn2:
                    checkAnswer(binding.qBtn2.getText().toString(), binding.qBtn2);
                    updateData();
                    break;
                case R.id.q_btn3:
                    checkAnswer(binding.qBtn3.getText().toString(), binding.qBtn3);
                    updateData();
                    break;
                case R.id.q_btn4:
                    checkAnswer(binding.qBtn4.getText().toString(), binding.qBtn4);
                    updateData();
                    break;
            }
        }


        private void updateData() {

        }

        private void checkAnswer(String userChoose, Button button) {
            if (userChoose.equals(list.get(getAdapterPosition()).getCorrect_answer())) {
                fadeView(button);
                btnDisEnabled();
            } else {
                btnDisEnabled();
                shakeAnim(button);
            }
        }

        private void shakeAnim(Button button) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(350);
            alphaAnimation.setRepeatCount(1);
            alphaAnimation.setRepeatMode(Animation.REVERSE);
            button.setAnimation(alphaAnimation);
            alphaAnimation.setAnimationListener(new AnimationShort() {
                @Override
                public void onAnimationStart(Animation animation) {
                    button.setBackgroundResource(R.drawable.back_color);
                    if (getAdapterPosition() == list.size() - 1) {
                        btnDisEnabled();
                        saveResults();
                    } else {
                        goNext();
                    }
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    button.setBackgroundResource(R.drawable.back_color);
                }
            });
        }

        private void fadeView(Button button) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(350);
            alphaAnimation.setRepeatCount(1);
            alphaAnimation.setRepeatMode(Animation.REVERSE);
            button.setAnimation(alphaAnimation);
            alphaAnimation.setAnimationListener(new AnimationShort() {
                @Override
                public void onAnimationStart(Animation animation) {
                    button.setBackgroundResource(R.drawable.back_true_back);
                    if (getAdapterPosition() == list.size() - 1) {
                        btnDisEnabled();
                        saveResults();
                    } else {
                        goNext();
                    }
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    button.setBackgroundResource(R.drawable.back_true_back);
                }

            });
        }

        private void goNext() {
            if (currentPosition != list.size() - 1) {
                QuestionActivity activity = new QuestionActivity();
                activity.nextPageOfAdapter();
                binding.qBtn1.setClickable(false);
                binding.qBtn2.setEnabled(true);
                binding.qBtn3.setEnabled(true);
                binding.qBtn4.setEnabled(true);
            }
        }

        private void saveResults() {

        }

        private void btnDisEnabled() {
            binding.qBtn1.setEnabled(false);
            binding.qBtn2.setEnabled(false);
            binding.qBtn3.setEnabled(false);
            binding.qBtn4.setEnabled(false);
        }
    }
}

