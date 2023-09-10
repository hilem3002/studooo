package com.example.studooov2.UserSignUpLogin.Activities.profileActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.Activities.PostActivities.PostSeeActivity;
import com.example.studooov2.UserSignUpLogin.Activities.loginActivities.LoginActivity;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnSuccessListener;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.PipedReader;
import java.io.Serializable;

public class editProfileActivity extends AppCompatActivity
{
    private ImageButton imagePickerButton;
    private TextView menuText;
    private ImageButton backToProfileButton;
    private Button saveAllButton,popUpMenuUniversityButton;
    private regularUser user;
    private Boolean allowChangeUsername,allowChangeMail;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private EditText nameText,usernameText,passwordText,mailText,bioText;
    int PICK_IMAGE_REQUEST = 1;
    Uri uri;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        allowChangeUsername=true;
        allowChangeMail=true;
        backToProfileButton = findViewById(R.id.backToProfileButton);
        nameText=findViewById(R.id.nameInputText);
        usernameText= findViewById(R.id.usernameInputText);
        passwordText = findViewById(R.id.passwordText);
        saveAllButton=findViewById(R.id.buttonSave);
        bioText = findViewById(R.id.bioText);
        imagePickerButton = findViewById(R.id.imageButton);
        preferences = getSharedPreferences("AppPreferences",MODE_PRIVATE);
        editor = preferences.edit();
        popUpMenuUniversityButton = findViewById(R.id.popUpMenuButtonUniversity);

        user = (regularUser) getIntent().getParcelableExtra("user");
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        downloadPhoto(user.getProfile_photo());


        if (user!=null) {
            nameText.setText(user.getName());
            usernameText.setText(user.getUsername());
            passwordText.setText(user.getPass());
            bioText.setText(user.getBio());
            popUpMenuUniversityButton.setText(user.getUni());
        }


        usernameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String newUsername = usernameText.getText().toString();
                regularUser userx = new regularUser();
                userx.isUsernameValid(newUsername, new OnSuccessListener() {
                    @Override
                    public void onSuccessLoading(Boolean loadingSuccess) {

                    }

                    @Override
                    public void onSuccessLoaded(Boolean success) {
                        if (!success) {
                            usernameText.setError("Bu kullanıcı adı kullanılmaktadır.");
                        }
                        else {
                            allowChangeUsername=true;
                        }
                    }

                    @Override
                    public void onSuccessFailed(Boolean errorSuccess) {
                        Intent intent = new Intent(getApplicationContext(),Error.class);
                    }
                });
            }
        });


        backToProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),profileActivity.class);
                intent.putExtra("user",(Parcelable) user);
                startActivity(intent);
                finish();
            }
        });

        saveAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                StorageReference reference = storageReference.child("profile_photos");
                reference.child(uri.toString().replaceAll("/","")).putFile(uri).addOnSuccessListener(new com.google.android.gms.tasks.OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String oldUsername = user.getUsername();
                        user.setUsername(usernameText.getText().toString());
                        user.setPass(passwordText.getText().toString());
                        user.setProfile_photo(uri.toString().replaceAll("/",""));
                        user.setName(nameText.getText().toString());
                        user.setBio(bioText.getText().toString());
                        editor.putString("password",user.getPass());
                        user.setUni(preferences.getString("university","Ege Üniversitesi"));
                        user.changeProfileInfo(oldUsername, user, new OnSuccessListener() {
                            @Override
                            public void onSuccessLoading(Boolean loadingSuccess) {

                            }

                            @Override
                            public void onSuccessLoaded(Boolean success) {
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onSuccessFailed(Boolean errorSuccess) {
                                Log.d("hata", "aaaa");
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("hata", e.getMessage());
                    }
                });


            }
        });

        popUpMenuUniversityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(getApplicationContext(),view);
                menu.getMenuInflater().inflate(R.menu.university_menu,menu.getMenu());
                menu.setForceShowIcon(true);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String unitextinput = menuItem.getTitle().toString();
                        editor.putString("university",unitextinput);
                        editor.commit();
                        popUpMenuUniversityButton.setText(unitextinput);
                        user.setUni(unitextinput);
                        return true;
                    }
                });
                menu.show();
            }

        });

        imagePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImageFromGallery();
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
            imagePickerButton.setImageURI(uri);
        }
    }

    public void downloadPhoto(String fileName) {
        StorageReference reference = storageReference.child("profile_photos/"+fileName);
        reference.getDownloadUrl().addOnSuccessListener(new com.google.android.gms.tasks.OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(editProfileActivity.this)
                        .load(uri)
                        .into(imagePickerButton);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}