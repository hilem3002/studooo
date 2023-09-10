package com.example.studooov2.UserSignUpLogin.Activities.loginActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.Activities.PostActivities.PostCreatingActivity;
import com.example.studooov2.UserSignUpLogin.Activities.PostActivities.PostSeeActivity;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnSuccessListener;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnUsersLoadedListener;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

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
        SharedPreferences preferences = getSharedPreferences("AppPreferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

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
                                intent = new Intent(LoginActivity.this, PostSeeActivity.class);
                                editor.putString("username",user.getUsername());
                                editor.putString("email",user.getEposta());
                                editor.putString("password",user.getPass());
                                editor.commit();
                                user.getRequest().getRequest(user.getUsername(), new OnUsersLoadedListener() {
                                    @Override
                                    public void OnUsersLoading(Boolean loadingSuccess) {

                                    }

                                    @Override
                                    public void OnUsersLoaded(List<regularUser> users) {
                                        user=users.get(0);
                                        intent.putExtra("user", (Parcelable) user);
                                        Toast.makeText(LoginActivity.this, "başarılı bir şekilde giriş yaptın", Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                        finish();
                                    }

                                    @Override
                                    public void OnUsersLoadFailed(Boolean errorSuccess) {

                                    }
                                });
                                /*Toast.makeText(LoginActivity.this, "başarılı bir şekilde giriş yaptın", Toast.LENGTH_SHORT).show();
                                intent = new Intent(LoginActivity.this, PostCreatingActivity.class);
                                startActivity(intent);*/
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