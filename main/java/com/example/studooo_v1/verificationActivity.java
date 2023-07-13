package com.example.studooo_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class verificationActivity extends AppCompatActivity {

    EditText verifyCode;
    Button sendcodeButton;
    TextView sendCode;
    private String sendedCode = null;
    private String receivedCode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verification_design);

        // assigned the values of objects
        this.verifyCode = findViewById(R.id.verifyCode);
        this.sendcodeButton = findViewById(R.id.sendcodeButton);
        this.sendCode = findViewById(R.id.sendCode);

        // getting the sended object
        regularUser user = getIntent().getParcelableExtra("user");
        mailSender sender = new mailSender();



        try {

            // sended a code for verification and getting it
            sendedCode = sender.sendmailCode(user.getEposta());

        }
        catch (Exception e) {

            // error page executed
            Intent intent = new Intent(verificationActivity.this, errormassageLoginActivity.class);
            startActivity(intent);

        }



        // user clicked to button to verify
        sendcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // editText to string
                receivedCode = verifyCode.getText().toString();

                // sign up after the verification
                if (user.verification(sendedCode, receivedCode)) {

                    try {

                        // sign up
                        user.sign_up();

                        // sign up success page executed
                        Intent intent = new Intent(verificationActivity.this, signupsuccessActivity.class);
                        startActivity(intent);

                    }
                    catch (Exception e) {

                        // error page executed
                        Intent intent = new Intent(verificationActivity.this, errormassageLoginActivity.class);
                        startActivity(intent);

                    }
                }
                else {
                    Toast.makeText(verificationActivity.this, "Yanlış bir giriş yaptınız lütfen tekrar deneyiniz", Toast.LENGTH_SHORT).show();
                }
            }
        });



        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    // sended a code for verification and getting it again
                    sendedCode = sender.sendmailCode(user.getEposta());

                }
                catch (Exception e) {

                    // error page executed
                    Intent intent = new Intent(verificationActivity.this, errormassageLoginActivity.class);
                    startActivity(intent);

                }
            }
        });
    }
}
