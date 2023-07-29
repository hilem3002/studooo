package com.example.studooov2.UserSignUpLogin.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnSuccessListener;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    TextView loginConnectText;
    TextInputEditText nameSurnameEdittext;
    TextInputEditText usernameEdittext;
    TextInputEditText epostaEdittext;
    TextInputEditText passwordSignUpEdittext;
    TextInputEditText passwordAgainEdittext;
    MaterialButton signUpButton;
    Boolean isUsernameValidSuccess;
    Boolean isEpostaValidSuccess;
    private Intent intent;
    private regularUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_design);

        loginConnectText = findViewById(R.id.loginConnectText);
        nameSurnameEdittext = findViewById(R.id.nameSurnameEdittext);
        usernameEdittext = findViewById(R.id.usernameEdittext);
        epostaEdittext = findViewById(R.id.epostaEdittext);
        passwordSignUpEdittext = findViewById(R.id.passwordSignUpEdittext);
        passwordAgainEdittext = findViewById(R.id.passwordAgainEdittext);
        signUpButton = findViewById(R.id.signUpButton);

        // looking for is username valid while typing
        usernameEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String username = usernameEdittext.getText().toString();

                try {

                    user = new regularUser();
                    user.isUsernameValid(username, new OnSuccessListener() {
                        @Override
                        public void onSuccessLoading(Boolean loadingSuccess) {

                        }

                        @Override
                        public void onSuccessLoaded(Boolean success) {
                            if (!success) {
                                isUsernameValidSuccess = false;
                                usernameEdittext.setError("Bu kullanıcı adı kullanılamaz");
                            }
                            else {
                                isUsernameValidSuccess = true;
                            }
                        }

                        @Override
                        public void onSuccessFailed(Boolean errorSuccess) {
                            // error page executed
                            intent = new Intent(SignUpActivity.this, ErrorMessageActivity.class);
                            startActivity(intent);
                        }
                    });

                }
                catch (Exception e) {
                    // error page executed
                    intent = new Intent(SignUpActivity.this, ErrorMessageActivity.class);
                    startActivity(intent);
                }
            }
        });

        // looking for is eposta valid while typing
        epostaEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String eposta = epostaEdittext.getText().toString();

                try {

                    user = new regularUser();
                    user.isEpostaValid(eposta, new OnSuccessListener() {
                        @Override
                        public void onSuccessLoading(Boolean loadingSuccess) {

                        }

                        @Override
                        public void onSuccessLoaded(Boolean success) {
                            if (!success) {
                                isEpostaValidSuccess = false;
                                epostaEdittext.setError("Bu e-posta adresi kullanılamaz");
                            }
                            else {
                                isEpostaValidSuccess = true;
                            }
                        }

                        @Override
                        public void onSuccessFailed(Boolean errorSuccess) {
                            // error page executed
                            intent = new Intent(SignUpActivity.this, ErrorMessageActivity.class);
                            startActivity(intent);
                        }
                    });

                }
                catch (Exception e) {
                    // error page executed
                    intent = new Intent(SignUpActivity.this, ErrorMessageActivity.class);
                    startActivity(intent);
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // edittext to string value
                String name = nameSurnameEdittext.getText().toString();
                String username = usernameEdittext.getText().toString();
                String eposta = epostaEdittext.getText().toString();
                String pass = passwordSignUpEdittext.getText().toString();
                String passAgain = passwordAgainEdittext.getText().toString();

                // looking for is there any empty edittext
                if (name.isEmpty() || username.isEmpty() || eposta.isEmpty() || pass.isEmpty() || passAgain.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "boş bırakılan alanlar mevcut", Toast.LENGTH_SHORT).show();
                }
                else {
                    // looking for pass and passAgain value are the same
                    if (pass.equals(passAgain)) {
                        try {

                            user = new regularUser(name, username, eposta, pass);

                            // looking for e-posta and username are valid
                            if (isUsernameValidSuccess && isEpostaValidSuccess) {

                                // verification page executed
                                intent = new Intent(SignUpActivity.this, VeriftyCodeToSignUpActivity.class);

                                // sending the user object to verification acitivty
                                intent.putExtra("user", user);
                                startActivity(intent);

                            }
                            else {
                                Toast.makeText(SignUpActivity.this, "bu kullanıcı adı ya da e-posta adresi kullanılamaz", Toast.LENGTH_SHORT).show();
                            }

                        }
                        catch (Exception e) {
                            // error page executed
                            intent = new Intent(SignUpActivity.this, ErrorMessageActivity.class);
                            startActivity(intent);
                        }
                    }
                    else {
                        Toast.makeText(SignUpActivity.this, "şifre ve şifre tekrar alanları aynı değil", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        loginConnectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}