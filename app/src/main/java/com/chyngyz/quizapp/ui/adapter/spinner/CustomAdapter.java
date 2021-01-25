package com.chyngyz.quizapp.ui.adapter.spinner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chyngyz.quizapp.R;
import com.chyngyz.quizapp.ui.models.UnderCategory;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<UnderCategory> {

    private final List<UnderCategory> list;

    public CustomAdapter(@NonNull Context context, int my_own_spinner_item,  @NonNull List<UnderCategory> objects) {
        super(context, my_own_spinner_item, objects);
        this.list = objects;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v;
        v = LayoutInflater.from(getContext()).inflate(R.layout.my_own_spinner_item, parent, false);
        TextView name = v.findViewById(R.id.adapter_spin_txt);
        name.setText(list.get(position).getName());
        return v;
    }
}
