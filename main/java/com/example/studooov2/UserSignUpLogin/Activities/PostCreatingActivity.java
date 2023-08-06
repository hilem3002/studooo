package com.example.studooov2.UserSignUpLogin.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.Models.Posts;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PostCreatingActivity extends AppCompatActivity {

    MaterialButton PostCreatingButton;
    MaterialButton PostCleanButton;
    MaterialButton PostDeleteButton;
    ImageView profilePhoto;
    TextView usernameTextInPostCreating;
    ImageButton addPhotoButton;
    TextInputEditText postTextEdittext;
    View postCreatView1;
    View postCreatView2;
    ImageView postPhoto;
    Uri uri;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    Intent intent;
    private static final int PICK_IMAGE_REQUEST = 1;
    regularUser user;
    Posts posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_creating_design);

        PostCreatingButton = findViewById(R.id.PostCreatingButton);
        PostCleanButton = findViewById(R.id.PostCleanButton);
        PostDeleteButton = findViewById(R.id.PostDeleteButton);
        profilePhoto = findViewById(R.id.profilePhoto);
        usernameTextInPostCreating = findViewById(R.id.usernameTextInPostCreating);
        addPhotoButton = findViewById(R.id.addPhotoButton);
        postTextEdittext = findViewById(R.id.postTextEdittext);
        postCreatView1 = findViewById(R.id.postCreatView1);
        postCreatView2 = findViewById(R.id.postCreatView2);
        postPhoto = findViewById(R.id.postPhoto);

        // user selecting an image from photos
        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImageFromGallery();
            }
        });

        // cleaning the post
        PostCleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postTextEdittext.setText(null);
                postPhoto.setImageDrawable(null);
                postCreatView1.setVisibility(View.INVISIBLE);
                postCreatView2.setVisibility(View.INVISIBLE);
            }
        });

        PostCreatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    firebaseStorage = FirebaseStorage.getInstance();
                    storageReference = firebaseStorage.getReference();

                    String postText = postTextEdittext.getText().toString();
                    StorageReference reference = storageReference.child("post_photos");

                    // saving the photo in storage
                    reference.child(uri.toString().replaceAll("/", "")).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            user = new regularUser(1, "melih", "hilem3002", "hilem3002@gmail.com", "mlh123");
                            posts = new Posts(postText, uri.toString().replaceAll("/", ""), user);

                            // if save is success infos saving in database
                            user.sendPost(posts, new com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnSuccessListener() {
                                @Override
                                public void onSuccessLoading(Boolean loadingSuccess) {

                                }

                                @Override
                                public void onSuccessLoaded(Boolean success) {
                                    if (success) {
                                        intent = new Intent(PostCreatingActivity.this, PostSeeActivity.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        // error page executed
                                        intent = new Intent(PostCreatingActivity.this, ErrorMessageActivity.class);
                                        startActivity(intent);
                                    }
                                }

                                @Override
                                public void onSuccessFailed(Boolean errorSuccess) {
                                    intent = new Intent(PostCreatingActivity.this, ErrorMessageActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            intent = new Intent(PostCreatingActivity.this, ErrorMessageActivity.class);
                            startActivity(intent);
                        }
                    });

                }
                catch (Exception e) {

                    // error page executed
                    intent = new Intent(PostCreatingActivity.this, ErrorMessageActivity.class);
                    startActivity(intent);

                }
            }
        });

    }

    // image selection from photo
    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            // get photo uri
            uri = data.getData();

            // set photo to imageview
            postPhoto.setImageURI(uri);
            postCreatView1.setVisibility(View.VISIBLE);
            postCreatView2.setVisibility(View.VISIBLE);
        }
    }

}
