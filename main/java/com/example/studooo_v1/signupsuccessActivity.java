package com.example.studooo_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class signupsuccessActivity extends AppCompatActivity {

    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_success_design);

        this.loginButton = findViewById(R.id.loginButton);


        // log in page executed
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(signupsuccessActivity.this, loginActivity.class);
                startActivity(intent);

            }
        });
    }
}
