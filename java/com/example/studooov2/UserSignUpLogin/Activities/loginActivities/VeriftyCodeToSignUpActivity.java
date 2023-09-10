package com.example.studooov2.UserSignUpLogin.Activities.loginActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnSuccessListener;
import com.example.studooov2.UserSignUpLogin.InternetOperations.VerificationCodeSender;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;
import com.example.studooov2.UserSignUpLogin.countDownTimer;
import com.google.android.material.button.MaterialButton;

public class VeriftyCodeToSignUpActivity extends AppCompatActivity {

    PinView VerifyCodeToSignUpPinEdittext;
    MaterialButton VerifyCodeToSignUpButton;
    MaterialButton SendTheCodeAgainToSignUpButton;
    VerificationCodeSender sender;
    TextView timerSignUp;
    regularUser user;
    Intent intent;
    String receivedCode;
    String sendedCode;
    countDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_code_to_sign_up_design);

        VerifyCodeToSignUpPinEdittext = findViewById(R.id.VerifyCodeToSignUpPinEdittext);
        VerifyCodeToSignUpButton = findViewById(R.id.VerifyCodeToSignUpButton);
        SendTheCodeAgainToSignUpButton = findViewById(R.id.SendTheCodeAgainToSignUpButton);
        timerSignUp = findViewById(R.id.timerSignUp);

        user = getIntent().getParcelableExtra("user");

        try {

            // verification code sended
            sender = new VerificationCodeSender();
            sendedCode = sender.sendmailCode(user.getEposta());
            timer = new countDownTimer();
            timer.startTimer(timerSignUp);


        }
        catch (Exception e) {

            // error page executed
            intent = new Intent(VeriftyCodeToSignUpActivity.this, ErrorMessageActivity.class);
            startActivity(intent);

        }

        VerifyCodeToSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (timer.isTimerStopped()) {
                    sendedCode = "notimeleft";
                }

                receivedCode = VerifyCodeToSignUpPinEdittext.getText().toString();

                if (user.verification(sendedCode, receivedCode)) {

                    try {

                        user.signUp(user, new OnSuccessListener() {
                            @Override
                            public void onSuccessLoading(Boolean loadingSuccess) {

                            }

                            @Override
                            public void onSuccessLoaded(Boolean success) {
                                if (success) {
                                    // sign up page executed
                                    intent = new Intent(VeriftyCodeToSignUpActivity.this, SignUpSucceedActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    // error page executed
                                    intent = new Intent(VeriftyCodeToSignUpActivity.this, ErrorMessageActivity.class);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onSuccessFailed(Boolean errorSuccess) {
                                // error page executed
                                intent = new Intent(VeriftyCodeToSignUpActivity.this, ErrorMessageActivity.class);
                                startActivity(intent);
                            }
                        });

                    }
                    catch (Exception e) {

                        // error page executed
                        intent = new Intent(VeriftyCodeToSignUpActivity.this, ErrorMessageActivity.class);
                        startActivity(intent);

                    }

                }
                else {
                    Toast.makeText(VeriftyCodeToSignUpActivity.this, "Yanlış bir giriş yaptınız ya da zaman aşımına uğradınız lütfen tekrar deneyin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        SendTheCodeAgainToSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    // code sended again
                    sendedCode = sender.sendmailCode(user.getEposta());
                    timer.startTimer(timerSignUp);

                }
                catch (Exception e) {

                    // error page executed
                    intent = new Intent(VeriftyCodeToSignUpActivity.this, ErrorMessageActivity.class);
                    startActivity(intent);

                }
            }
        });

    }
}