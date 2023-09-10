package com.example.studooov2.UserSignUpLogin.Activities.PostActivities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.Activities.loginActivities.ErrorMessageActivity;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.ApiRequest;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnCommentLoadedListener;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnCommentsLoadedListener;
import com.example.studooov2.UserSignUpLogin.Models.Comment;
import com.example.studooov2.UserSignUpLogin.Models.Posts;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;
import com.example.studooov2.UserSignUpLogin.commentAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class commentActivity extends AppCompatActivity {

    TextView name, username, uniName, date, postText;
    CircleImageView pp;
    ImageView postPhoto;
    Posts post;
    ImageButton commentButton;
    EditText commentEdittext;
    MaterialButton sendComButton;
    RecyclerView rv;
    ApiRequest request;
    commentAdapter adapter;
    regularUser user;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_page_design);

        post = getIntent().getParcelableExtra("post");
        user = getIntent().getParcelableExtra("user");

        name = findViewById(R.id.textViewName);
        username = findViewById(R.id.textViewUsername);
        uniName = findViewById(R.id.textViewUniversityName);
        date = findViewById(R.id.textViewPostDate);
        postText = findViewById(R.id.textViewPostText);
        pp = findViewById(R.id.imageViewProfilePhoto);
        postPhoto = findViewById(R.id.imageViewPostImage);
        commentButton = findViewById(R.id.commentButton);
        commentEdittext = findViewById(R.id.commentEdittext);
        sendComButton = findViewById(R.id.sendComButton);
        rv = findViewById(R.id.rv);

        commentButton.setVisibility(View.GONE);
        name.setText(post.getUser().getName());
        username.setText(post.getUser().getUsername());
        uniName.setText(post.getUser().getUni());
        date.setText("05.06.2003");
        postText.setText(post.getPost_text());
        downloadPhoto(post.getUser().getProfile_photo(), pp);
        if (post.getPost_photo() != null) {
            downloadPhoto1(post.getPost_photo(), postPhoto);
        }
        else {
            postPhoto.setVisibility(View.GONE);
        }

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));



        sendComButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commentText = commentEdittext.getText().toString();
                commentEdittext.setText(null);
                request = new ApiRequest();
                Comment comment = new Comment(commentText, post, user);

                request.postRequest(comment, new OnCommentLoadedListener() {
                    @Override
                    public void OnCommentLoading(Boolean loadingSuccess) {
                        Toast.makeText(commentActivity.this, "loading", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void OnCommentLoaded(Comment comment) {
                        if (comment.getComment_id() != 0) {
                            Toast.makeText(commentActivity.this, "gönderildi", Toast.LENGTH_SHORT).show();
                            adapter.addNewComment(comment);
                            rv.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void OnCommentLoadFailed(Boolean errorSuccess) {
                        Intent intent = new Intent(commentActivity.this, ErrorMessageActivity.class);
                    }
                });
            }
        });

        request = new ApiRequest();

        request.getRequest(post.getPostId(), new OnCommentsLoadedListener() {
            @Override
            public void OnCommentsLoading(Boolean loadingSuccess) {
                Toast.makeText(commentActivity.this, "loading", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnCommentsLoaded(List<Comment> comments) {
                if (!comments.isEmpty()) {
                    adapter = new commentAdapter(commentActivity.this, comments, user, rv);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void OnCommentsLoadFailed(Boolean errorSuccess) {

            }
        });


    }

    public void downloadPhoto(String fileName, ImageView imageView) {
        FirebaseStorage firebaseStorage;
        StorageReference storageReference;
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        StorageReference reference = storageReference.child("profile_photos/"+fileName);
        reference.getDownloadUrl().addOnSuccessListener(new com.google.android.gms.tasks.OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(commentActivity.this)
                        .load(uri)
                        .into(imageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void downloadPhoto1(String fileName, ImageView imageView) {
        FirebaseStorage firebaseStorage;
        StorageReference storageReference;
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        StorageReference reference = storageReference.child("post_photos/"+fileName);
        reference.getDownloadUrl().addOnSuccessListener(new com.google.android.gms.tasks.OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(commentActivity.this)
                        .load(uri)
                        .into(imageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}
