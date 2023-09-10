package com.example.studooov2.UserSignUpLogin.Activities.settingActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.Activities.loginActivities.LoginActivity;
import com.example.studooov2.UserSignUpLogin.Activities.profileActivities.profileActivity;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;

import java.io.Serializable;
import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {
    private ImageButton backToProfileButton;
    private Button changeLanguageButton;
    private Button aboutUsButton;
    private Button buttonLogOut;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        changeLanguageButton = findViewById(R.id.popupLanguageButton);
        backToProfileButton = findViewById(R.id.backToProfileFromSettingsButton);
        preferences = getSharedPreferences("AppPreferences",MODE_PRIVATE);
        editor = preferences.edit();
        if (preferences.getBoolean("turkce",true)) {
            changeLanguageButton.setText("TÜRKÇE");
        }
        else if (preferences.getBoolean("ingilizce",true)) {
            changeLanguageButton.setText("ENGLISH");
        }
        editor.putBoolean("day",true);
        editor.putBoolean("night",false);


        regularUser user = (regularUser) getIntent().getParcelableExtra("user");


        backToProfileButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), profileActivity.class);
                intent.putExtra("user", (Parcelable) user);
                startActivity(intent);
                finish();
            }
        });
        changeLanguageButton=findViewById(R.id.popupLanguageButton);
        changeLanguageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(getApplicationContext(),view);
                menu.getMenuInflater().inflate(R.menu.languages_menu,menu.getMenu());
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getTitle().equals("TÜRKÇE")) {
                            editor.putBoolean("turkce",true);
                            editor.putBoolean("ingilizce",false);
                            change("tr");
                            changeLanguageButton.setText("TÜRKÇE");
                            editor.commit();
                            Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
                            intent.putExtra("user",(Parcelable) user);
                            startActivity(intent);
                            finish();

                        }
                        else if (menuItem.getTitle().equals("İNGİLİZCE")){
                            editor.putBoolean("ingilizce",true);
                            editor.putBoolean("turkce",false);
                            change("en");
                            changeLanguageButton.setText("İNGİLİZCE");
                            editor.commit();
                            Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
                            intent.putExtra("user",(Parcelable) user);
                            startActivity(intent);
                            finish();
                        }
                        return true;
                    }
                });
                menu.show();
            }
        });


        aboutUsButton = findViewById(R.id.buttonAboutUs);
        aboutUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AboutUsActivity.class);
                startActivity(intent);
            }
        });

        
        buttonLogOut = findViewById(R.id.buttonLogOut);
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                editor.remove("username");
                editor.remove("password");
                editor.remove("email");
                editor.commit();
            }
        });


    }
    public void change(String languageCode)
    {
        Resources resources = this.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());
    }

}