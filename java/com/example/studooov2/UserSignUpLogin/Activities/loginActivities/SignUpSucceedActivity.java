package com.example.studooov2.UserSignUpLogin.Activities.loginActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studooov2.R;
import com.google.android.material.button.MaterialButton;

public class SignUpSucceedActivity extends AppCompatActivity {

    MaterialButton LoginConnectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_succeed_design);

        LoginConnectButton = findViewById(R.id.LoginConnectButton);

        LoginConnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // login page executed
                Intent intent = new Intent(SignUpSucceedActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }
}