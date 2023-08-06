package com.example.studooov2.UserSignUpLogin.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.ApiRequest;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnPostsLoadedListener;
import com.example.studooov2.UserSignUpLogin.Models.Posts;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostSeeActivity extends AppCompatActivity {

    ImageView seePic;
    TextView seeText;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_see_design);
        FirebaseApp.initializeApp(this);

        seePic = findViewById(R.id.seePic);
        seeText = findViewById(R.id.seeText);
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();


        try {
            //regularUser user = new regularUser(1, "melih", "hilem3002", "hilem3002@gmail.com", "mlh123");

            ApiRequest request = new ApiRequest();

            request.getRequest(2,"hilem",  new OnPostsLoadedListener() {
                @Override
                public void OnPostsLoading(Boolean loadingSuccess) {
                    Toast.makeText(PostSeeActivity.this, "loading...", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void OnPostsLoaded(List<Posts> posts) {
                    if (!posts.isEmpty()) {
                        seeText.setText(posts.get(0).getPost_text());
                        downloadPhoto(posts.get(0).getPost_photo());
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

    public void downloadPhoto(String fileName) {
        StorageReference reference = storageReference.child("post_photos/"+fileName);
        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(PostSeeActivity.this)
                        .load(uri)
                        .into(seePic);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}