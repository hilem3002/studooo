package com.example.studooo_v1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class errormassageLoginActivity extends AppCompatActivity {

    EditText usernameEposta;
    EditText password;
    Button loginButton;
    TextView signupConnect;
    TextView newpasswordConnect;
    private boolean logInSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_message_login_design);

        // object assignment
        this.usernameEposta = findViewById(R.id.usernameEposta);
        this.password = findViewById(R.id.password);
        this.loginButton = findViewById(R.id.loginButton);
        this.signupConnect = findViewById(R.id.signupConnect);
        this.newpasswordConnect = findViewById(R.id.newpasswordConnect);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // editText to string
                String username_eposta = usernameEposta.getText().toString();
                String pass = password.getText().toString();

                try {

                    regularUser user = new regularUser(username_eposta, pass);
                    logInSuccess = user.log_in();

                }
                catch (Exception e) {

                    // error page executed
                    Intent intent = new Intent(loginActivity.this, errormassageLoginActivity.class);
                    startActivity(intent);

                }

                // looking for log in success is true or not
                if (logInSuccess) {
                    Toast.makeText(loginActivity.this, "başarılı bir şekilde giriş yaptınız", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(loginActivity.this, "kullanıcı adı veya şifre yanlış", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // sign up page executed
        signupConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(loginActivity.this, signupActivity.class);
                startActivity(intent);
            }
        });


        // verify eposta page executed
        newpasswordConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(loginActivity.this, verifyepostaActivity.class);
                startActivity(intent);
            }
        });


    }
    }
}
