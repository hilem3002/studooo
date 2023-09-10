package com.example.studooov2.UserSignUpLogin;

import android.os.CountDownTimer;
import android.widget.TextView;

public class countDownTimer {

    private CountDownTimer countDownTimer;
    private long timeLeft = 120000;
    private boolean isTimerStopped = false;

    public void startTimer(TextView timer) {
        countDownTimer = new android.os.CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateTimer(timer);
            }

            @Override
            public void onFinish() {
                isTimerStopped = true;
            }
        }.start();
    }

    private void updateTimer(TextView timer) {
        int minutes = (int) timeLeft / 60000;
        int seconds = (int) timeLeft % 60000 / 1000;

        String timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds<10) {
            timeLeftText += "0";
        }
        timeLeftText += seconds;
        timer.setText(timeLeftText);
    }

    public boolean isTimerStopped() {
        return isTimerStopped;
    }

}
