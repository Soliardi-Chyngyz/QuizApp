package com.chyngyz.quizapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.chyngyz.quizapp.R;
import com.chyngyz.quizapp.databinding.QuizItemBinding;
import com.chyngyz.quizapp.ui.models.Question;

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

    public class QuizVH extends RecyclerView.ViewHolder {

        QuizItemBinding binding;

        public QuizVH(@NonNull QuizItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
