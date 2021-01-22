package com.chyngyz.quizapp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.chyngyz.quizapp.R;
import com.chyngyz.quizapp.databinding.QuizItemBinding;
import com.chyngyz.quizapp.ui.models.Question;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizVH> {

    private ArrayList<Question> list;
    private MainListener listener;

    public QuizAdapter(ArrayList<Question> list, MainListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuizVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuizItemBinding binding = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_item, parent, false));
        return new QuizVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizVH holder, int position) {
        //TODO:
        holder.binding.setQuestion(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class QuizVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        public static final int CORRECT_ANSWER = 1;
        public static final int CORRECT_AND_FINAL_ANSWER = 11;
        public static final int WRONG_ANSWER = 2;
        public static final int WRONG_AND_FINAL_ANSWER = 22;
        private QuizItemBinding binding;

        @SuppressLint("ClickableViewAccessibility")
        public QuizVH(@NonNull QuizItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.qBtn1.setOnClickListener(this);
            binding.qBtn2.setOnClickListener(this);
            binding.qBtn3.setOnClickListener(this);
            binding.qBtn4.setOnClickListener(this);
            binding.questionBtnYes.setOnClickListener(this);
            binding.questionBtnNo.setOnClickListener(this);
        }

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.q_btn1:
                    checkAnswer(binding.qBtn1.getText().toString(), binding.qBtn1, 0);
                    break;
                case R.id.q_btn2:
                    checkAnswer(binding.qBtn2.getText().toString(), binding.qBtn2, 1);
                    break;
                case R.id.q_btn3:
                    checkAnswer(binding.qBtn3.getText().toString(), binding.qBtn3, 2);
                    break;
                case R.id.q_btn4:
                    checkAnswer(binding.qBtn4.getText().toString(), binding.qBtn4, 3);
                    break;
                case R.id.question_btn_yes:
                    checkAnswer(binding.questionBtnYes.getText().toString(), binding.questionBtnYes, 0);
                    break;
                case R.id.question_btn_no:
                    checkAnswer(binding.questionBtnNo.getText().toString(), binding.questionBtnNo, 1);
                    break;
            }
        }

        private void checkAnswer(String userChoose, Button button, int answerPosition) {
            int result;
            if (userChoose.equals(list.get(getAdapterPosition()).getCorrect_answer())) {
                if (getAdapterPosition() >= list.size() - 1) {
                    button.setBackgroundTintList(ColorStateList.valueOf(R.drawable.back_true_back));
                    button.setTextColor(Color.WHITE);
                    result = CORRECT_AND_FINAL_ANSWER;
                } else {
                    button.setBackgroundTintList(ColorStateList.valueOf(R.drawable.back_true_back));
                    button.setTextColor(Color.WHITE);
                    result = CORRECT_ANSWER;
                }
                YoYo.with(Techniques.ZoomIn)
                        .duration(500)
                        .repeat(1)
                        .playOn(button);
            } else {
                if (getAdapterPosition() >= list.size() - 1) {
                    button.setBackground(ContextCompat.getDrawable(button.getContext(), R.drawable.back_wrong));
                    button.setTextColor(Color.WHITE);
                    result = WRONG_AND_FINAL_ANSWER;
                } else {
                    button.setBackground(ContextCompat.getDrawable(button.getContext(), R.drawable.back_wrong));
                    button.setTextColor(Color.WHITE);
                    result = WRONG_ANSWER;
                }
                YoYo.with(Techniques.Shake)
                        .duration(500)
                        .repeat(1)
                        .playOn(button);
                showCorrectAnswer(button, binding.getQuestion());
            }
            listener.onAnswerClick(getAdapterPosition(), answerPosition, result);
            btnEnableDisable(false);
        }

        private void showCorrectAnswer(Button button, Question question) {
            String correctAnswer = question.getCorrect_answer();
            int correctPosition = 0;
            for (int i = 0; i < question.getIncorrect_answers().size(); i++) {
                if (correctAnswer.equals(question.getIncorrect_answers().get(i)))
                    correctPosition = i;
            }
            switch (correctPosition) {
                case 0:
                    binding.qBtn1.setBackground(ContextCompat.getDrawable(button.getContext(), R.drawable.back_true_back));
                    binding.qBtn1.setTextColor(Color.WHITE);
                    binding.questionBtnYes.setBackground(ContextCompat.getDrawable(button.getContext(), R.drawable.back_true_back));
                    binding.questionBtnYes.setTextColor(Color.WHITE);
                    break;
                case 1:
                    binding.qBtn2.setBackground(ContextCompat.getDrawable(button.getContext(), R.drawable.back_true_back));
                    binding.qBtn2.setTextColor(Color.WHITE);
                    binding.questionBtnNo.setBackground(ContextCompat.getDrawable(button.getContext(), R.drawable.back_true_back));
                    binding.questionBtnNo.setTextColor(Color.WHITE);
                    break;
                case 2:
                    binding.qBtn3.setBackground(ContextCompat.getDrawable(button.getContext(), R.drawable.back_true_back));
                    binding.qBtn3.setTextColor(Color.WHITE);
                    break;
                case 3:
                    binding.qBtn4.setBackground(ContextCompat.getDrawable(button.getContext(), R.drawable.back_true_back));
                    binding.qBtn4.setTextColor(Color.WHITE);
                    break;
            }
        }

        private void btnEnableDisable(boolean clickable) {
            binding.qBtn1.setClickable(clickable);
            binding.qBtn2.setClickable(clickable);
            binding.qBtn3.setClickable(clickable);
            binding.qBtn4.setClickable(clickable);
            binding.questionBtnNo.setClickable(clickable);
            binding.questionBtnYes.setClickable(clickable);
        }
    }

    public interface MainListener {
        void onAnswerClick(int questionPosition, int answerPosition, int result);
    }

    public interface viewHolderListener {
        void viewHolderClickOnAnswer(View view, int positionAnswer);
    }
}

