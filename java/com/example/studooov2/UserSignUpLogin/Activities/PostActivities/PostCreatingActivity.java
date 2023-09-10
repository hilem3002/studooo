package com.example.studooov2.UserSignUpLogin.Activities.PostActivities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.Activities.loginActivities.ErrorMessageActivity;
import com.example.studooov2.UserSignUpLogin.Activities.notiticationActivity.BolumSecmeActivity;
import com.example.studooov2.UserSignUpLogin.Activities.profileActivities.profileActivity;
import com.example.studooov2.UserSignUpLogin.Models.Posts;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PostCreatingActivity extends AppCompatActivity {

    MaterialButton PostCreatingButton;
    MaterialButton PostCleanButton;
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
        profilePhoto = findViewById(R.id.profilePhoto);
        usernameTextInPostCreating = findViewById(R.id.usernameTextInPostCreating);
        addPhotoButton = findViewById(R.id.addPhotoButton);
        postTextEdittext = findViewById(R.id.postTextEdittext);
        postCreatView1 = findViewById(R.id.postCreatView1);
        postCreatView2 = findViewById(R.id.postCreatView2);
        postPhoto = findViewById(R.id.postPhoto);
        user = getIntent().getParcelableExtra("user");

        usernameTextInPostCreating.setText(user.getUsername());

        BottomNavigationView bottomMenu = findViewById(R.id.bottommenu);
        bottomMenu.setSelectedItemId(R.id.action_addpost);
        bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.action_profile) {
                    Intent intent1 = new Intent(getApplicationContext(), profileActivity.class);
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

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        downloadPhoto(user.getProfile_photo());


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

                    String postText = postTextEdittext.getText().toString();
                    StorageReference reference = storageReference.child("post_photos");

                    if (uri != null) {
                        // saving the photo in storage
                        reference.child(uri.toString().replaceAll("/", "")).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                posts = new Posts(postText, uri.toString().replaceAll("/", ""), user, 0);

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
                                            Log.d("t","burda2");
                                        }
                                    }

                                    @Override
                                    public void onSuccessFailed(Boolean errorSuccess) {
                                        Log.d("t","burda1");
                                        intent = new Intent(PostCreatingActivity.this, ErrorMessageActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("t", "burda");
                                intent = new Intent(PostCreatingActivity.this, ErrorMessageActivity.class);
                                startActivity(intent);
                            }
                        });
                    }

                    else {
                        posts = new Posts(postText, user, 0);

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
                                    Log.d("t","burda2");
                                }
                            }

                            @Override
                            public void onSuccessFailed(Boolean errorSuccess) {
                                // error page executed
                                intent = new Intent(PostCreatingActivity.this, ErrorMessageActivity.class);
                                startActivity(intent);
                                Log.d("t","burda2");
                            }
                        });
                    }



                }
                catch (Exception e) {

                    Log.d("t", "burda3");
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

    public void downloadPhoto(String fileName) {
        StorageReference reference = storageReference.child("profile_photos/"+fileName);
        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(PostCreatingActivity.this)
                        .load(uri)
                        .into(profilePhoto);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

}
