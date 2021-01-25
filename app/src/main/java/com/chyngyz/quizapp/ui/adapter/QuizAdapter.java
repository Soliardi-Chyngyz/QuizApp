package com.chyngyz.quizapp.ui.adapter;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.chyngyz.quizapp.R;
import com.chyngyz.quizapp.databinding.QuizItemBinding;
import com.chyngyz.quizapp.ui.models.Question;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import java.util.ArrayList;

import static com.chyngyz.quizapp.core.StaticMethods.CORRECT_AND_FINAL_ANSWER;
import static com.chyngyz.quizapp.core.StaticMethods.CORRECT_ANSWER;
import static com.chyngyz.quizapp.core.StaticMethods.WRONG_ANSWER;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizVH> {

    private final ArrayList<Question> list;
    private final OnResultAnswerClickListener answerClick;

    public QuizAdapter(ArrayList<Question> list, OnResultAnswerClickListener answerClick) {
        this.list = list;
        this.answerClick = answerClick;
    }

    @NonNull
    @Override
    public QuizVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuizItemBinding binding = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_item, parent, false));
        assert binding != null;
        return new QuizVH(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull QuizVH holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class QuizVH extends RecyclerView.ViewHolder implements com.chyngyz.quizapp.interfaces.OnButtonAnswerClick, View.OnTouchListener {

        private final QuizItemBinding binding;

        @SuppressLint("ClickableViewAccessibility")
        public QuizVH(@NonNull QuizItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.setHandlers(this);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        public void onBind(Question question) {
            binding.qBtn1.setBackgroundResource(R.drawable.btn_trassparent_style);
            binding.qBtn2.setBackgroundResource(R.drawable.btn_trassparent_style);
            binding.qBtn3.setBackgroundResource(R.drawable.btn_trassparent_style);
            binding.qBtn4.setBackgroundResource(R.drawable.btn_trassparent_style);
            binding.questionBtnYes.setBackgroundResource(R.drawable.btn_trassparent_style);
            binding.questionBtnNo.setBackgroundResource(R.drawable.btn_trassparent_style); // дэфолтим (обнуляем для каждого цикла)

            question.getIsSkipClicked().observeForever(aBoolean -> { // клик на скип
                if (aBoolean) {
                    buttonClickable(false);
                    showCorrectButton(question);
                    onTouch();
                }
            });

            if (question.isClick()) {
                switch (question.getUserChoice()) {
                    case 0:
                        binding.qBtn1.setBackgroundResource(R.drawable.back_wrong);
                        break;
                    case 1:
                        binding.qBtn2.setBackgroundResource(R.drawable.back_wrong);
                        break;
                    case 2:
                        binding.qBtn3.setBackgroundResource(R.drawable.back_wrong);
                        break;
                    case 3:
                        binding.qBtn4.setBackgroundResource(R.drawable.back_wrong);
                        break;
                } // на случай если ответит правильно, красный фон заменится правильным
                onTouch();
                showCorrectButton(question);
                buttonClickable(false);
            } else {
                onTouch();
                buttonClickable(true);
            }
            binding.setQuestion(question);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        private void showCorrectButton(Question question) {
            String correctAnc = question.getCorrect_answer();
            int positionCorrectAnc = 0;
            for (int i = 0; i < question.getIncorrect_answers().size(); i++) {
                if (correctAnc.equals(question.getIncorrect_answers().get(i))) // находим нужный ответ
                    positionCorrectAnc = i;
            }
            switch (positionCorrectAnc) {
                case 0:
                    binding.qBtn1.setBackgroundResource(R.drawable.back_true_back);
                    binding.questionBtnYes.setBackgroundResource(R.drawable.back_true_back);
                    YoYo(binding.qBtn1);
                    YoYo(binding.questionBtnYes);
                    break;
                case 1:
                    binding.qBtn2.setBackgroundResource(R.drawable.back_true_back);
                    binding.questionBtnNo.setBackgroundResource(R.drawable.back_true_back);
                    YoYo(binding.qBtn2);
                    YoYo(binding.questionBtnNo);
                    break;
                case 2:
                    binding.qBtn3.setBackgroundResource(R.drawable.back_true_back);
                    YoYo(binding.qBtn3);
                    break;
                case 3:
                    binding.qBtn4.setBackgroundResource(R.drawable.back_true_back);
                    YoYo(binding.qBtn4);
                    break;
            } // сетим цвета бэграунда

        }

        private void YoYo(View view) {
            YoYo.with(Techniques.ZoomIn)
                    .duration(500)
                    .repeat(1)
                    .playOn(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View view, int positionAnswer) {
            binding.getQuestion().setClick(true);
            binding.getQuestion().setUserChoice(positionAnswer);
            Button button = (Button) view;
            int result;
            Question questionModel = list.get(getAdapterPosition());
            String userAnswer = questionModel.getIncorrect_answers().get(positionAnswer);
            if (userAnswer.equals(questionModel.getCorrect_answer())) {
                if (getAdapterPosition() >= list.size() - 1) {
                    button.setBackgroundResource(R.drawable.back_true_back);
                    result = CORRECT_AND_FINAL_ANSWER;
                } else {
                    button.setBackgroundResource(R.drawable.back_true_back);
                    result = CORRECT_ANSWER;
                }
                YoYo(view);
            } else {
                if (getAdapterPosition() >= list.size() - 1) {
                    button.setBackgroundResource(R.drawable.back_wrong);
                    result = CORRECT_AND_FINAL_ANSWER;

                } else {
                    button.setBackgroundResource(R.drawable.back_wrong);
                    result = WRONG_ANSWER;
                }
                YoYo.with(Techniques.Tada)
                        .duration(500)
                        .repeat(1)
                        .playOn(view);
            }
            buttonClickable(false);
            showCorrectButton(binding.getQuestion());

            answerClick.onClick(result, binding.getQuestion().getIncorrect_answers().get(positionAnswer));
        }

        private void buttonClickable(boolean clickable) {
            binding.qBtn1.setClickable(clickable);
            binding.qBtn2.setClickable(clickable);
            binding.qBtn3.setClickable(clickable);
            binding.qBtn4.setClickable(clickable);
            binding.questionBtnYes.setClickable(clickable);
            binding.questionBtnNo.setClickable(clickable);
        }

        @SuppressLint("ClickableViewAccessibility")
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (binding.getQuestion().getIsSkipClicked().getValue() != null)
                if (binding.getQuestion().getIsSkipClicked().getValue()) return false;
            if (binding.getQuestion().isClick()) return false;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.setBackgroundResource(R.drawable.back_true_back);
                    return false; // if you want to handle the touch event
                case MotionEvent.ACTION_UP:
                    v.setBackgroundResource(R.drawable.btn_trassparent_style);
                    return false; // if you want to handle the touch event
            }
            return false;
        }

        @SuppressLint("ClickableViewAccessibility")
        private void onTouch() {
            binding.qBtn1.setOnTouchListener(this);
            binding.qBtn2.setOnTouchListener(this);
            binding.qBtn3.setOnTouchListener(this);
            binding.qBtn4.setOnTouchListener(this);
            binding.questionBtnYes.setOnTouchListener(this);
            binding.questionBtnNo.setOnTouchListener(this);
        } // инитим
    }

    public interface OnResultAnswerClickListener {
        void onClick(int result, String answer);
    }
}

