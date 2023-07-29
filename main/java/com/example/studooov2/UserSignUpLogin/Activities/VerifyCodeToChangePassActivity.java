package com.example.studooov2.UserSignUpLogin.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.InternetOperations.VerificationCodeSender;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;
import com.example.studooov2.UserSignUpLogin.countDownTimer;
import com.google.android.material.button.MaterialButton;

public class VerifyCodeToChangePassActivity extends AppCompatActivity {

    PinView VerifyCodeToChangePassPinEdittext;
    MaterialButton VerifyCodeToChangePassButton;
    MaterialButton SendTheCodeAgainToChangePassButton;
    TextView timerChangePass;
    String receiverEposta;
    String receiverUsername;
    VerificationCodeSender sender;
    String sendedCode;
    String receivedCode;
    Intent intent;
    regularUser user;
    //countDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_code_to_change_pass_design);

        VerifyCodeToChangePassPinEdittext = findViewById(R.id.VerifyCodeToChangePassPinEdittext);
        VerifyCodeToChangePassButton = findViewById(R.id.VerifyCodeToChangePassButton);
        SendTheCodeAgainToChangePassButton = findViewById(R.id.SendTheCodeAgainToChangePassButton);
        timerChangePass = findViewById(R.id.timerChangePass);

        receiverEposta = getIntent().getStringExtra("receiverEposta");
        receiverUsername = getIntent().getStringExtra("receiverUsername");
        sender = new VerificationCodeSender();

        try {

            // verification code sended
            sendedCode = sender.sendmailCode(receiverEposta);
            //timer.startTimer(timerChangePass);

        }
        catch (Exception e) {

            // error page excuted
            intent = new Intent(VerifyCodeToChangePassActivity.this, ErrorMessageActivity.class);
            startActivity(intent);

        }

        VerifyCodeToChangePassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*if (timer.isTimerStopped()) {
                    sendedCode = "notimeleft";
                }*/

                receivedCode = VerifyCodeToChangePassPinEdittext.getText().toString();

                try {

                    user = new regularUser();

                    if (user.verification(sendedCode, receivedCode)) {
                        intent = new Intent(VerifyCodeToChangePassActivity.this, PassChangingActivity.class);
                        intent.putExtra("receiverUsername", receiverUsername);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(VerifyCodeToChangePassActivity.this, "yanlış bir giriş yaptınız lütfen tekrar deneyin", Toast.LENGTH_SHORT).show();
                    }

                }
                catch (Exception e) {

                    // error page excuted
                    intent = new Intent(VerifyCodeToChangePassActivity.this, ErrorMessageActivity.class);
                    startActivity(intent);

                }

            }
        });

        SendTheCodeAgainToChangePassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    // verification code sended
                    sendedCode = sender.sendmailCode(receiverEposta);
                    //timer.startTimer(timerChangePass);

                }
                catch (Exception e) {

                    // error page excuted
                    intent = new Intent(VerifyCodeToChangePassActivity.this, ErrorMessageActivity.class);
                    startActivity(intent);

                }
            }
        });

    }
}