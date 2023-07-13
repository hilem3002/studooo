package com.example.studooo_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class passchangeActivity extends AppCompatActivity {

    EditText newPassword;
    EditText newpasswordAgain;
    Button changepassButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passchange_design);

        this.newPassword = findViewById(R.id.newPassword);
        this.newpasswordAgain = findViewById(R.id.newpasswordAgain);
        this.changepassButton = findViewById(R.id.changepassButton);


        changepassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newPass = newPassword.getText().toString();
                String newPassAgain = newpasswordAgain.getText().toString();
                String receiverUsername = getIntent().getStringExtra("receiverUsername");

                if (newPass.equals(newPassAgain)) {

                    try {

                        regularUser user = new regularUser();
                        user.changePass(receiverUsername, newPass, newPassAgain);

                    }
                    catch (Exception e) {

                        // error page executed
                        Intent intent = new Intent(passchangeActivity.this, errormassageLoginActivity.class);
                        startActivity(intent);
                    }
                    // password change success page executed
                    Intent intent = new Intent(passchangeActivity.this, passchangesuccessActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(passchangeActivity.this, "girilen iki şifre birbiri ile aynı olmak zorunda", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    }
}
