package com.example.studooov2.UserSignUpLogin.Activities.PostActivities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.Activities.notiticationActivity.BolumSecmeActivity;
import com.example.studooov2.UserSignUpLogin.Activities.profileActivities.profileActivity;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.ApiRequest;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnPostsLoadedListener;
import com.example.studooov2.UserSignUpLogin.Models.Posts;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;
import com.example.studooov2.UserSignUpLogin.rvadapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class PostSeeActivity extends AppCompatActivity {

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    rvadapter adapter;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_design);
        FirebaseApp.initializeApp(this);

        rv = findViewById(R.id.rv);
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        regularUser user = getIntent().getParcelableExtra("user");

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        BottomNavigationView bottomMenu = findViewById(R.id.bottommenu);
        bottomMenu.setSelectedItemId(R.id.action_home);
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
                else if (item.getItemId()==R.id.action_notification) {
                    Intent intent = new Intent(getApplicationContext(), BolumSecmeActivity.class);
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



        try {

            ApiRequest request = new ApiRequest();

            request.getRequest(0,"a",  new OnPostsLoadedListener() {
                @Override
                public void OnPostsLoading(Boolean loadingSuccess) {
                    Toast.makeText(PostSeeActivity.this, "loading...", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void OnPostsLoaded(List<Posts> posts) {
                    if (!posts.isEmpty()) {
                        /*seeText.setText(posts.get(0).getPost_text());
                        downloadPhoto(posts.get(0).getPost_photo());*/

                        adapter = new rvadapter(PostSeeActivity.this, posts, user);
                        rv.setAdapter(adapter);
                    }
                }

                @Override
                public void OnPostsLoadFailed(Boolean errorSuccess) {
                    Toast.makeText(PostSeeActivity.this, "hata", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e) {
            Log.d("t", e.getMessage());
        }
    }

}