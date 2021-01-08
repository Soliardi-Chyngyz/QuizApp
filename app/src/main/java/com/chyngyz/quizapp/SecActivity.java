package com.chyngyz.quizapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class SecActivity extends AppCompatActivity {

    private TextView txt;
    private Button btn;
    private MainVModel mainVModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);

        mainVModel = new MainVModel();
        txt = findViewById(R.id.sec_text);
        btn = findViewById(R.id.sec_btn);

        mainVModel.mViewData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txt.setText(s);
            }
        });

        btn.setOnClickListener(v -> mainVModel.getName());
    }
}
