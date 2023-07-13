package com.example.studooo_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class passchangesuccessActivity extends AppCompatActivity {

    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passchangesuccess_design);

        this.loginButton = findViewById(R.id.loginButton);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // pass change succeeded and go to login page
                Intent intent = new Intent(passchangesuccessActivity.this, loginActivity.class);
                startActivity(intent);

            }
        });
    }
    }
}
