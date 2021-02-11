package com.chyngyz.quizapp.cloud_masseging;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.chyngyz.quizapp.R;

public class PushNotifActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notif);

        init();
    }

    private void init() {
        TextView title = findViewById(R.id.push_title);
        title.setText(getIntent().getStringExtra("push_title"));

        TextView text = findViewById(R.id.push_text);
        text.setText(getIntent().getStringExtra("push_text"));

        ImageView img = findViewById(R.id.push_img);
        Uri uri = Uri.parse(getIntent().getExtras().getString("push_img"));
        img.setImageURI(uri);
    }
}