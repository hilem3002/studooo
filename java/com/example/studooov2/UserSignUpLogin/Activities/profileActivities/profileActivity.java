package com.example.studooov2.UserSignUpLogin.Activities.profileActivities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.Activities.PostActivities.PostCreatingActivity;
import com.example.studooov2.UserSignUpLogin.Activities.PostActivities.PostSeeActivity;
import com.example.studooov2.UserSignUpLogin.Activities.PostActivities.SearchUsersActivity;
import com.example.studooov2.UserSignUpLogin.Activities.notiticationActivity.BolumSecmeActivity;
import com.example.studooov2.UserSignUpLogin.Activities.settingActivities.SettingsActivity;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.ApiRequest;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnPostsLoadedListener;
import com.example.studooov2.UserSignUpLogin.Models.Posts;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;
import com.example.studooov2.UserSignUpLogin.rvadapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class profileActivity extends AppCompatActivity {
    private Button editProfileButton, button;

    private RecyclerView rv;
    private rvadapter adapter;
    private ImageButton settingsButton;
    private regularUser user;
    private TextView realNameText;
    private TextView usernameText;
    private TextView universityText;
    private TextView bioText;
    private ImageView pp;
    private BottomNavigationView bottomMenu;

    ApiRequest request;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        user = (regularUser) getIntent().getParcelableExtra("user");
        bottomMenu = findViewById(R.id.bottommenu);
        bottomMenu.setSelectedItemId(R.id.action_profile);
        bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.action_addpost) {
                    Intent intent1 = new Intent(getApplicationContext(), PostCreatingActivity.class);
                    intent1.putExtra("user",(Parcelable) user);
                    startActivity(intent1);
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                }
                else if (item.getItemId()==R.id.action_home) {
                    Intent intent1 = new Intent(getApplicationContext(), PostSeeActivity.class);
                    intent1.putExtra("user",(Parcelable) user);
                    startActivity(intent1);
                    overridePendingTransition(0, 0);
                    finish();
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

        editProfileButton = findViewById(R.id.buttonEditProfile);
        settingsButton = findViewById(R.id.imageButton);
        //realNameText = findViewById(R.id.realNameText);
        usernameText =findViewById(R.id.textView2);
        universityText=findViewById(R.id.textView3);
        bioText= findViewById(R.id.textView4);
        pp = findViewById(R.id.pp);
        rv = findViewById(R.id.rv);


        //realNameText.setText(user.getName());
        usernameText.setText(user.getUsername());
        bioText.setText(user.getBio());
        universityText.setText(user.getUni());

        downloadPhoto(user.getProfile_photo());

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));

        request = new ApiRequest();

        request.getRequest(0, user.getUsername(), new OnPostsLoadedListener() {
            @Override
            public void OnPostsLoading(Boolean loadingSuccess) {

            }

            @Override
            public void OnPostsLoaded(List<Posts> posts) {
                adapter = new rvadapter(profileActivity.this, posts, user, rv);
                rv.setAdapter(adapter);
            }

            @Override
            public void OnPostsLoadFailed(Boolean errorSuccess) {

            }
        });


        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),editProfileActivity.class);
                intent.putExtra("user", (Parcelable) user);
                startActivity(intent);

            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                intent.putExtra("user", (Parcelable) user);
                startActivity(intent);

            }
        });
    }

    public void downloadPhoto(String fileName) {
        StorageReference reference = storageReference.child("profile_photos/"+fileName);
        reference.getDownloadUrl().addOnSuccessListener(new com.google.android.gms.tasks.OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(profileActivity.this)
                        .load(uri)
                        .into(pp);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}