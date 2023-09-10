package com.example.studooov2.UserSignUpLogin.Activities.profileActivities;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.ApiRequest;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnPostsLoadedListener;
import com.example.studooov2.UserSignUpLogin.Models.Posts;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;
import com.example.studooov2.UserSignUpLogin.rvadapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class otherUserProfile extends AppCompatActivity {

    TextView name, username, uni, bio;
    ImageView pp;
    regularUser user;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    rvadapter adapter;
    RecyclerView rv;
    ApiRequest request;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_user_profile_design);

        user = getIntent().getParcelableExtra("user");

        name = findViewById(R.id.textView);
        username = findViewById(R.id.textView2);
        uni = findViewById(R.id.textView3);
        bio = findViewById(R.id.textView4);
        pp = findViewById(R.id.pp);

        name.setText(user.getName());
        username.setText(user.getUsername());
        uni.setText(user.getUni());
        bio.setText(user.getBio());
        downloadPhoto(user.getProfile_photo());

        rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));

        request = new ApiRequest();

        request.getRequest(0, user.getUsername(), new OnPostsLoadedListener() {
            @Override
            public void OnPostsLoading(Boolean loadingSuccess) {

            }

            @Override
            public void OnPostsLoaded(List<Posts> posts) {
                adapter = new rvadapter(otherUserProfile.this, posts, user);
                rv.setAdapter(adapter);
            }

            @Override
            public void OnPostsLoadFailed(Boolean errorSuccess) {

            }
        });
    }



    public void downloadPhoto(String fileName) {
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        StorageReference reference = storageReference.child("profile_photos/"+fileName);
        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(otherUserProfile.this)
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
