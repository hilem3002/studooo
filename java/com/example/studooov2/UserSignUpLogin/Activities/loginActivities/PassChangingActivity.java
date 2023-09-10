package com.example.studooov2.UserSignUpLogin.Activities.loginActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnSuccessListener;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class PassChangingActivity extends AppCompatActivity {

    TextInputEditText NewPasswordEdittext;
    TextInputEditText NewPasswordAgainEdittext;
    MaterialButton ChangePassButton;
    regularUser user;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass_changing_design);

        NewPasswordEdittext = findViewById(R.id.NewPasswordEdittext);
        NewPasswordAgainEdittext = findViewById(R.id.NewPasswordAgainEdittext);
        ChangePassButton = findViewById(R.id.ChangePassButton);

        ChangePassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // edittext to string
                String newPass = NewPasswordEdittext.getText().toString();
                String newPassAgain = NewPasswordAgainEdittext.getText().toString();
                user = getIntent().getParcelableExtra("user");
                user.setPass(newPass);

                if (newPass.equals(newPassAgain)) {

                    try {

                        user.changePass(user, new OnSuccessListener() {
                            @Override
                            public void onSuccessLoading(Boolean loadingSuccess) {

                            }

                            @Override
                            public void onSuccessLoaded(Boolean success) {
                                if (success) {
                                    // pass changing success page executed
                                    intent = new Intent(PassChangingActivity.this, PassChangingSucceedActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(PassChangingActivity.this, "Şifreniz değiştirilemedi", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onSuccessFailed(Boolean errorSuccess) {
                                // error page executed
                                intent = new Intent(PassChangingActivity.this, ErrorMessageActivity.class);
                                startActivity(intent);
                            }
                        });

                    }
                    catch (Exception e) {

                        // error page executed
                        intent = new Intent(PassChangingActivity.this, ErrorMessageActivity.class);
                        startActivity(intent);

                    }
                }
                else {
                    Toast.makeText(PassChangingActivity.this, "yeni şifreniz ile yeni şifre tekrar aynı olmalı", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}