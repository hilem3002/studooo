package com.example.studooo_v1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

public class signupActivity extends AppCompatActivity {

    // creating the object with ids in layout
    EditText nameSurname;
    EditText username;
    EditText eposta;
    EditText password;
    Button signupButton;
    TextView logInConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_design);

        // assigned the values of objects
        this.nameSurname = findViewById(R.id.nameSurname);
        this.username = findViewById(R.id.username);
        this.eposta = findViewById(R.id.eposta);
        this.password = findViewById(R.id.password);
        this.signupButton = findViewById(R.id.sigupButton);
        this.logInConnect = findViewById(R.id.logInConnect);


        username.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                // looking for if this usernama valid or not
                String uname = username.getText().toString();
                try {
                    regularUser user = new regularUser();
                    if (!user.isUsernameValid(uname)) {
                        Toast.makeText(signupActivity.this, "bu kullanıcı adı zaten kullanılıyor", Toast.LENGTH_SHORT).show();
                        user.getOperator().disconnect();
                    }
                }
                catch (Exception e) {
                    Toast.makeText(signupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    // error message page executed
                    Intent intent = new Intent(signupActivity.this, errormassageLoginActivity.class);
                    startActivity(intent);
                }
            }
        });



        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // edittext to string value
                String name = nameSurname.getText().toString();
                String uname = username.getText().toString();
                String e_posta = eposta.getText().toString();
                String pass = password.getText().toString();


                try {

                    regularUser user = new regularUser(name, uname, e_posta, pass);

                    // looking for username is valid or not
                    if (user.isUsernameValid(user.getUsername())) {

                        // verification page executed
                        Intent intent = new Intent(signupActivity.this, verificationActivity.class);

                        //sending user object to another activity class
                        intent.putExtra("user", user);
                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(signupActivity.this, "bu kullanıcı adı kullanılamaz", Toast.LENGTH_SHORT).show();
                    }

                }
                catch (Exception e) {

                    Toast.makeText(signupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    // error message page executed
                    Intent intent = new Intent(signupActivity.this, errormassageLoginActivity.class);
                    startActivity(intent);

                }

            }
        });


    }
    }
}
