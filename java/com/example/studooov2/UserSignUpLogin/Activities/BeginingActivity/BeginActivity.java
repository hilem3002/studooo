package com.example.studooov2.UserSignUpLogin.Activities.BeginingActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.Activities.PostActivities.PostCreatingActivity;
import com.example.studooov2.UserSignUpLogin.Activities.PostActivities.PostSeeActivity;
import com.example.studooov2.UserSignUpLogin.Activities.loginActivities.SignUpActivity;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnSuccessListener;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnUsersLoadedListener;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;

import java.util.List;
import java.util.Locale;

public class BeginActivity extends AppCompatActivity {
    private regularUser user;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);

        preferences = getSharedPreferences("AppPreferences",MODE_PRIVATE);
        editor = preferences.edit();
        changeLang();
        nextStep();


    }
    public void changeLang() {
        if (preferences.getBoolean("turkce",true)) {
            changeLanguage("tr");
        }
        else if (preferences.getBoolean("ingilizce",true)) {
            changeLanguage("en");
        }
    }

    public void nextStep() {
        String username = preferences.getString("username",null);
        String password = preferences.getString("password",null);
        String mail = preferences.getString("email",null);
        user = new regularUser(username,password);
        user.login(new OnSuccessListener() {
            @Override
            public void onSuccessLoading(Boolean loadingSuccess) {

            }

            @Override
            public void onSuccessLoaded(Boolean success)
            {
                if (success) {
                    Intent intent = new Intent(BeginActivity.this, PostSeeActivity.class);
                    user.getRequest().getRequest(username, new OnUsersLoadedListener() {
                        @Override
                        public void OnUsersLoading(Boolean loadingSuccess) {

                        }

                        @Override
                        public void OnUsersLoaded(List<regularUser> users) {
                            user=users.get(0);
                            intent.putExtra("user", (Parcelable) user);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void OnUsersLoadFailed(Boolean errorSuccess)
                        {

                        }
                    });
                }
                else if (!success) {
                    Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onSuccessFailed(Boolean errorSuccess) {

            }
        });
    }
    public void changeLanguage(String languageCode)
    {
        Resources resources = this.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());
    }


}