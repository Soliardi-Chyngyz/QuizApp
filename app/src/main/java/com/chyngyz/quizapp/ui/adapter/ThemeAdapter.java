package com.chyngyz.quizapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.chyngyz.quizapp.R;
import com.chyngyz.quizapp.databinding.ItemThemeBinding;
import com.chyngyz.quizapp.interfaces.OnItemThemeClickListener;
import com.chyngyz.quizapp.ui.models.ThemeItem;

import java.util.ArrayList;
import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {

    private List<ThemeItem> list = new ArrayList<>();
    private OnItemThemeClickListener listener;

    public void onClick(OnItemThemeClickListener listener){
        this.listener = listener;
    }

    public void setList(List<ThemeItem> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemThemeBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemThemeBinding binding;

        public ViewHolder(@NonNull ItemThemeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v -> {
                binding.themeRadioBtn.setChecked(!binding.themeRadioBtn.isChecked());
                if(binding.themeRadioBtn.isChecked())
                    listener.onThemeClicked(getAdapterPosition());
            });
            binding.themeRadioBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) listener.onThemeClicked(getAdapterPosition());
                }
            });
        }

        public void onBind(ThemeItem item){
            binding.themeImg.setImageDrawable(ContextCompat.getDrawable(binding.getRoot().getContext(), item.getIconDrawableId()));
            binding.themeRadioBtn.setChecked(item.isChange());
        }
    }
}
