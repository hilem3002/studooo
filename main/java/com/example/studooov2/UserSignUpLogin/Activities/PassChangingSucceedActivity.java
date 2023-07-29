package com.example.studooov2.UserSignUpLogin.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studooov2.R;
import com.google.android.material.button.MaterialButton;

public class PassChangingSucceedActivity extends AppCompatActivity {

    MaterialButton PassChangeSucceedConnectLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass_changing_succeed_design);

        PassChangeSucceedConnectLoginButton = findViewById(R.id.PassChangeSucceedConnectLoginButton);

        PassChangeSucceedConnectLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // login page executed
                Intent intent = new Intent(PassChangingSucceedActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}