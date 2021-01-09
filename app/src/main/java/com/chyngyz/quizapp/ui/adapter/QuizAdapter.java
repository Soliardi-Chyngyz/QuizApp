package com.chyngyz.quizapp.ui.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.chyngyz.quizapp.R;
import com.chyngyz.quizapp.databinding.QuizItemBinding;
import com.chyngyz.quizapp.ui.models.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizVH> {
    private static int TYPE_MAIN_ANSWERS = 1;
    private static int TYPE_YES_OR_NO = 2;

    private ArrayList<Quiz> list;

    public QuizAdapter(ArrayList<Quiz> list) {
        this.list = list;
    }

//    @Override
//    public int getItemViewType(int position) {
//        if (TextUtils.isEmpty(list.get(position).getV1())) {
//            return TYPE_YES_OR_NO;
//        } else {
//            return TYPE_MAIN_ANSWERS;
//        }
//    }

    @NonNull
    @Override
    public QuizVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuizItemBinding binding = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_item, parent, false));
        return new QuizVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizVH holder, int position) {
        holder.binding.setQuiz(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class QuizVH extends RecyclerView.ViewHolder {

        QuizItemBinding binding;

        public QuizVH(@NonNull QuizItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
