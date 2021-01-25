package com.chyngyz.quizapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.chyngyz.quizapp.R;
import com.chyngyz.quizapp.databinding.HistoryItemBinding;
import com.chyngyz.quizapp.ui.models.QuizResult;
import java.text.DateFormat;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<QuizResult> list;
    private final OnMenuBarClick listener;

    public HistoryAdapter(List<QuizResult> list, OnMenuBarClick listener) {
        this.list = list;
        this.listener = listener;
    }

    public void deleteItem(int position){
        list.remove(position);
        notifyDataSetChanged();
    }

    public void clearAll(){
        list.clear();
        notifyDataSetChanged();
    }

    public void addItem(List<QuizResult> quizResults) {
        this.list = quizResults;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HistoryItemBinding binding = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false));
        assert binding != null;
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final HistoryItemBinding binding;

        public ViewHolder(@NonNull HistoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(QuizResult quizResult){
            binding.setModel(quizResult);
            String date = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(quizResult.getCreatedAt());
            binding.hisTime.setText(date);
            binding.setListener(listener);
            binding.popUpMenu.setOnClickListener(v -> listener.menuClicked(v, getAdapterPosition()));
        }
    }

    public interface OnMenuBarClick{
        void menuClicked(View view, int position);
    }
}
