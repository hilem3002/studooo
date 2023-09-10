package com.example.studooov2.UserSignUpLogin.Activities.settingActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.studooov2.R;

public class AboutUsActivity extends AppCompatActivity {
    private ImageButton backToSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        backToSettings = findViewById(R.id.backToSettingsFromAboutUsButton);
        backToSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}