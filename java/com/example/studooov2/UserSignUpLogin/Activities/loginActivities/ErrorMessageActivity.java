package com.example.studooov2.UserSignUpLogin.Activities.loginActivities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studooov2.R;
import com.google.android.material.button.MaterialButton;

public class ErrorMessageActivity extends AppCompatActivity {

    MaterialButton GoBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_message_design);

        GoBackButton = findViewById(R.id.GoBackButton);
    }
}