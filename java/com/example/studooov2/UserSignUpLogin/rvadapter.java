package com.example.studooov2.UserSignUpLogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.Activities.PostActivities.commentActivity;
import com.example.studooov2.UserSignUpLogin.Activities.profileActivities.profileActivity;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.ApiRequest;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnPostLoadedListener;
import com.example.studooov2.UserSignUpLogin.Models.Posts;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class rvadapter extends RecyclerView.Adapter<rvadapter.CardViewHolder>
{
    private Context context;
    private List<Posts> postList;
    private regularUser user;
    RecyclerView rv;
    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;

    public rvadapter (Context context,List<Posts> list, regularUser user, RecyclerView rv)
    {
        this.context=context;
        this.postList=list;
        this.user = user;
        this.rv = rv;
        preferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public rvadapter (Context context,List<Posts> list, regularUser user)
    {
        this.context=context;
        this.postList=list;
        this.user = user;
        preferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public TextView realname;
        public TextView posttext;
        public TextView postdate;
        public ImageView postPhoto;
        public ImageView userPhoto;
        public TextView uniname;
        public CardView view;
        public ImageButton commentButton, deletePostButton, likeButton;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.cv);
            username = itemView.findViewById(R.id.textViewUsername);
            realname = itemView.findViewById(R.id.textViewName);
            posttext = itemView.findViewById(R.id.textViewPostText);
            postdate = itemView.findViewById(R.id.textViewPostDate);
            uniname = itemView.findViewById(R.id.textViewUniversityName);
            postPhoto = itemView.findViewById(R.id.imageViewPostImage);
            userPhoto = itemView.findViewById(R.id.imageViewProfilePhoto);
            commentButton = itemView.findViewById(R.id.commentButton);
            deletePostButton = itemView.findViewById(R.id.deletePostButton);
            likeButton = itemView.findViewById(R.id.likeBtn);
        }
    }
    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new CardViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Posts post = postList.get(position);

        boolean isLikedBefore = preferences.getBoolean(String.valueOf(post.getPostId()),false);
        if (isLikedBefore) {
            holder.likeButton.setImageDrawable(context.getResources().getDrawable(R.drawable.baseline_favorite_24));
        }

        holder.posttext.setText(post.getPost_text());
        holder.postdate.setText("12,3,2023");
        holder.realname.setText(post.getUser().getName());
        holder.username.setText(post.getUser().getUsername());
        holder.uniname.setText(post.getUser().getUni());
        //holder.postPhoto.setImageIcon(Icon.createWithContentUri(post.getPost_photo()));
        //holder.userPhoto.setImageIcon(Icon.createWithContentUri(user.getProfile_photo()));
        downloadPhoto(post.getUser().getProfile_photo(), holder.userPhoto);
        if (post.getPost_photo() != null) {
            downloadPhoto1(post.getPost_photo(), holder.postPhoto);
        }
        else {
            holder.postPhoto.setVisibility(View.GONE);
        }

        holder.commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, commentActivity.class);
                intent.putExtra("post", post);
                intent.putExtra("user", user);
                context.startActivity(intent);
            }
        });

        if (context instanceof profileActivity) {
            holder.deletePostButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ApiRequest request = new ApiRequest();
                    request.deleteRequest(post.getPostId(), new OnPostLoadedListener() {
                        @Override
                        public void OnPostLoading(Boolean loadingSuccess) {
                            Toast.makeText(context, "siliniyor", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void OnPostLoaded(Posts posts) {
                            postList.remove(post);
                            rvadapter adapter = new rvadapter(context, postList, user);
                            rv.setAdapter(adapter);
                        }

                        @Override
                        public void OnPostLoadFailed(Boolean errorSuccess) {

                        }
                    });
                }
            });
        }
        else {
            holder.deletePostButton.setVisibility(View.GONE);
        }

        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isLikedBefore = preferences.getBoolean(String.valueOf(post.getPostId()),false);
                if (!isLikedBefore)
                {
                    post.setLike(post.getLike()+1);
                    ApiRequest request = new ApiRequest();
                    request.putRequest(post, new OnPostLoadedListener() {
                        @Override
                        public void OnPostLoading(Boolean loadingSuccess) {

                        }

                        @Override
                        public void OnPostLoaded(Posts posts) {
                            holder.likeButton.setImageDrawable(context.getResources().getDrawable(R.drawable.baseline_favorite_24));
                            editor.putBoolean(String.valueOf(post.getPostId()),true);
                            editor.commit();
                        }

                        @Override
                        public void OnPostLoadFailed(Boolean errorSuccess) {

                        }
                    });
                }
                else if (isLikedBefore) {
                    post.setLike(post.getLike()-1);
                    ApiRequest request = new ApiRequest();
                    request.putRequest(post, new OnPostLoadedListener() {
                        @Override
                        public void OnPostLoading(Boolean loadingSuccess) {

                        }

                        @Override
                        public void OnPostLoaded(Posts posts) {
                            holder.likeButton.setImageDrawable(context.getResources().getDrawable(R.drawable.baseline_favorite_border_24));
                            editor.remove(String.valueOf(post.getPostId()));
                            editor.commit();
                        }

                        @Override
                        public void OnPostLoadFailed(Boolean errorSuccess) {

                        }
                    });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
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
                Glide.with(context)
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
                Glide.with(context)
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
