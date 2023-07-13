package com.example.studooo_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class verifyCodeToChangePass extends AppCompatActivity {

    EditText verifyCodePass;
    Button sendcodeButtonPass;
    TextView sendCodePass;
    private String sendedCode = null;
    private String receivedCode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verification_pass_change_design);

        this.verifyCodePass = findViewById(R.id.verifyCodePass);
        this.sendcodeButtonPass = findViewById(R.id.sendcodeButtonPass);
        this.sendCodePass = findViewById(R.id.sendCodePass);

        // getting the string value which is send
        String receiverEposta = getIntent().getStringExtra("receiverEposta");
        String receiverUsername = getIntent().getStringExtra("receiverUsername");
        mailSender sender = new mailSender();



        try {

            // sended a code for verification and getting it
            sendedCode = sender.sendmailCode(receiverEposta);

        }
        catch (Exception e) {

            // error page executed
            Intent intent = new Intent(verifyCodeToChangePass.this, errormassageLoginActivity.class);
            startActivity(intent);

        }


        sendcodeButtonPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                receivedCode = verifyCodePass.getText().toString();

                try {

                    regularUser user = new regularUser();

                    if (user.verification(sendedCode, receivedCode)) {
                        Intent intent = new Intent(verifyCodeToChangePass.this, passchangeActivity.class);
                        intent.putExtra("receiverUsername", receiverUsername);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(verifyCodeToChangePass.this, "yanlış giriş yaptınız lütfen tekrar deneyiniz", Toast.LENGTH_SHORT).show();
                    }

                }
                catch (Exception e) {

                    // error page executed
                    Intent intent = new Intent(verifyCodeToChangePass.this, errormassageLoginActivity.class);
                    startActivity(intent);

                }
            }
        });


        sendCodePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    // sended a code for verification and getting it again
                    sendedCode = sender.sendmailCode(receiverEposta);

                }
                catch (Exception e) {

                    // error page executed
                    Intent intent = new Intent(verifyCodeToChangePass.this, errormassageLoginActivity.class);
                    startActivity(intent);

                }
            }
        });

    }
}