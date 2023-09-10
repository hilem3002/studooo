package com.example.studooov2.UserSignUpLogin.Activities.notiticationActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;

import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.Activities.PostActivities.PostCreatingActivity;
import com.example.studooov2.UserSignUpLogin.Activities.PostActivities.PostSeeActivity;
import com.example.studooov2.UserSignUpLogin.Activities.PostActivities.SearchUsersActivity;
import com.example.studooov2.UserSignUpLogin.Activities.profileActivities.profileActivity;
import com.example.studooov2.UserSignUpLogin.BolumSecmeAdapter;
import com.example.studooov2.UserSignUpLogin.BolumSecmeRecyclerviewInterface;
import com.example.studooov2.UserSignUpLogin.Models.Bolum;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class BolumSecmeActivity extends AppCompatActivity implements BolumSecmeRecyclerviewInterface {

    private RecyclerView bolumSecmeRV;

    private SearchView searchViewBolumSecme;

    private ArrayList<Bolum> bolums;

    private BolumSecmeAdapter adapter;

    private ArrayList<Bolum> filteredList;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bolum_secme);

        regularUser user = getIntent().getParcelableExtra("user");

        BottomNavigationView bottomMenu = findViewById(R.id.bottommenu);
        bottomMenu.setSelectedItemId(R.id.action_notification);
        bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.action_addpost) {
                    Intent intent1 = new Intent(getApplicationContext(), PostCreatingActivity.class);
                    intent1.putExtra("user",(Parcelable) user);
                    startActivity(intent1);
                    overridePendingTransition(0, 0);
                    return true;
                }
                else if (item.getItemId()==R.id.action_profile) {
                    Intent intent1 = new Intent(getApplicationContext(), profileActivity.class);
                    intent1.putExtra("user",(Parcelable) user);
                    startActivity(intent1);
                    overridePendingTransition(0, 0);
                    return true;
                }
                else if (item.getItemId()==R.id.action_search) {
                    Intent intent = new Intent(getApplicationContext(), SearchUsersActivity.class);
                    intent.putExtra("user",(Parcelable) user);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    return true;
                }
                else if (item.getItemId()==R.id.action_home) {
                    Intent intent = new Intent(getApplicationContext(), PostSeeActivity.class);
                    intent.putExtra("user",(Parcelable) user);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    return true;
                }
                else {
                    return true;
                }
            }
        });


        bolumSecmeRV = findViewById(R.id.bolumSecmeRV);
        searchViewBolumSecme = findViewById(R.id.searchViewBolumSecme);
        searchViewBolumSecme.clearFocus();
        searchViewBolumSecme.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });

        bolums = new ArrayList<>();

        bolums.add(new Bolum("Bilgisayar Mühendisliği", "https://bilmuh.ege.edu.tr/"));
        bolums.add(new Bolum("Biyomühendislik", "https://biyomuhendislik.ege.edu.tr/"));
        // bolums.add(new Bolum("Deri Mühendisliği", "https://deri.ege.edu.tr/"));
        bolums.add(new Bolum("Elektrik-Elektronik Mühendisliği", "https://electronics.ege.edu.tr/"));
        // bolums.add(new Bolum("Gıda Mühendisliği", "https://food.ege.edu.tr/"));
        bolums.add(new Bolum("İnşaat Mühendisliği", "https://insaat.ege.edu.tr/"));
        bolums.add(new Bolum("Kimya Mühendisliği", "https://chemeng.ege.edu.tr/"));
        bolums.add(new Bolum("Makine Mühendisliği", "https://me.ege.edu.tr/"));
        bolums.add(new Bolum("Tekstil Mühendisliği", "https://textile.ege.edu.tr/"));
        bolums.add(new Bolum("Tıp Fakültesi", "https://med.ege.edu.tr/"));
        bolums.add(new Bolum("Diş Fakültesi", "https://dent.ege.edu.tr/"));
        bolums.add(new Bolum("Hemşirelik Fakültesi", "https://hemsirelik.ege.edu.tr/"));
        bolums.add(new Bolum("Biyoloji","https://biyoloji.ege.edu.tr/"));
        bolums.add(new Bolum("Fizik", "https://fizik.ege.edu.tr/"));
        bolums.add(new Bolum("Matematik", "https://matematik.ege.edu.tr/"));
        bolums.add(new Bolum("Alman Dil ve Edebiyatı", "https://germanistikizmir.ege.edu.tr/"));
        bolums.add(new Bolum("Almanca Mütercim ve Tercümanlık", "https://translation.ege.edu.tr/"));
        bolums.add(new Bolum("Astronomi ve Uzay Bilimleri", "https://astronomi.ege.edu.tr/"));
        bolums.add(new Bolum("Coğrafya", "https://cografya.ege.edu.tr/"));
        bolums.add(new Bolum("İktisat", "https://iibf.ege.edu.tr/"));
        bolums.add(new Bolum("Psikoloji", "https://psikoloji.ege.edu.tr/"));


        bolumSecmeRV.setHasFixedSize(true);
        bolumSecmeRV.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BolumSecmeAdapter(this, bolums, this );

        bolumSecmeRV.setAdapter(adapter);

    }

    private void filterList(String newText) {
        filteredList = new ArrayList<>();
        for(Bolum bolum : bolums){
            if(bolum.getBolumName().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(bolum);
            }
        }

        adapter.setFilteredList(filteredList);
    }

    @Override
    public void onItemClick(int position) {
        Intent goToNotificactionRecyclerviewActivity = new Intent(BolumSecmeActivity.this, NotificationRecyclerviewActivity.class);
        if(filteredList != null) {
            goToNotificactionRecyclerviewActivity.putExtra("BOLUMNAME", filteredList.get(position).getBolumName());
            goToNotificactionRecyclerviewActivity.putExtra("BOLUMWEBLİNK", filteredList.get(position).getBolumWebLink());
        } else{
            goToNotificactionRecyclerviewActivity.putExtra("BOLUMNAME", bolums.get(position).getBolumName());
            goToNotificactionRecyclerviewActivity.putExtra("BOLUMWEBLİNK", bolums.get(position).getBolumWebLink());
        }
        startActivity(goToNotificactionRecyclerviewActivity);
    }
}