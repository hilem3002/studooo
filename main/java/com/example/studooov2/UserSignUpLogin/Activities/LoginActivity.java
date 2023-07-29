package com.example.studooov2.UserSignUpLogin.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnSuccessListener;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextView forgotPasswordText;
    MaterialButton SignUpConnectButton;
    TextInputEditText usernameEpostaEdittext;
    TextInputEditText passwordLoginEdittext;
    MaterialButton LoginButton;
    String usernameEposta;
    String pass;
    regularUser user;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_design);

        forgotPasswordText = findViewById(R.id.forgotPasswordText);
        SignUpConnectButton = findViewById(R.id.SignUpConnectButton);
        usernameEpostaEdittext = findViewById(R.id.usernameEpostaEdittext);
        passwordLoginEdittext = findViewById(R.id.passwordLoginEdittex);
        LoginButton = findViewById(R.id.LoginButton);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // edittext to string
                usernameEposta = usernameEpostaEdittext.getText().toString();
                pass = passwordLoginEdittext.getText().toString();

                try {

                    user = new regularUser(usernameEposta, pass);
                    user.login(new OnSuccessListener() {
                        @Override
                        public void onSuccessLoading(Boolean loadingSuccess) {

                        }

                        @Override
                        public void onSuccessLoaded(Boolean success) {
                            if (success) {
                                Toast.makeText(LoginActivity.this, "başarılı bir şekilde giriş yaptın", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "kullanıcı adı veya şifre yanlış", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onSuccessFailed(Boolean errorSuccess) {
                            // error page executed
                            intent = new Intent(LoginActivity.this, ErrorMessageActivity.class);
                            startActivity(intent);
                        }
                    });

                }
                catch (Exception e) {

                    // error page executed
                    intent = new Intent(LoginActivity.this, ErrorMessageActivity.class);
                    startActivity(intent);

                }

            }
        });


        forgotPasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotThePassActivity.class);
                startActivity(intent);
            }
        });



        SignUpConnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}