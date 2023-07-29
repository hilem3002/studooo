package com.example.studooov2.UserSignUpLogin.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.ApiRequest;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnUsersLoadedListener;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ForgotThePassActivity extends AppCompatActivity {

    TextInputEditText UsernameEpostaForgotThePassEdiitext;
    MaterialButton ForgotThePassButton;
    String usernameEposta;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_the_pass_design);

        UsernameEpostaForgotThePassEdiitext = findViewById(R.id.UsernameEpostaForgotThePassEdiitext);
        ForgotThePassButton = findViewById(R.id.ForgotThePassButton);

        ForgotThePassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // edittext to string
                usernameEposta = UsernameEpostaForgotThePassEdiitext.getText().toString();

                try {

                    ApiRequest request = new ApiRequest();
                    request.getRequest(usernameEposta, new OnUsersLoadedListener() {
                        @Override
                        public void OnUsersLoading(Boolean loadingSuccess) {

                        }

                        @Override
                        public void OnUsersLoaded(List<regularUser> users) {
                            if (users.isEmpty()) {
                                Toast.makeText(ForgotThePassActivity.this, "Bu bilgilere sahip bir kullanıcı bulunamadı", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                intent = new Intent(ForgotThePassActivity.this, VerifyCodeToChangePassActivity.class);

                                //sending the receiver eposta to other page
                                intent.putExtra("receiverEposta", users.get(0).getEposta());
                                intent.putExtra("receiverUsername", users.get(0).getUsername());
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void OnUsersLoadFailed(Boolean errorSuccess) {
                            // error page executed
                            intent = new Intent(ForgotThePassActivity.this, ErrorMessageActivity.class);
                            startActivity(intent);
                        }
                    });

                }
                catch (Exception e) {

                    // error page executed
                    intent = new Intent(ForgotThePassActivity.this, ErrorMessageActivity.class);
                    startActivity(intent);

                }

            }
        });

    }
}