package com.example.studooov2.UserSignUpLogin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.Activities.PostActivities.PostSeeActivity;
import com.example.studooov2.UserSignUpLogin.Activities.profileActivities.otherUserProfile;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class userSearchAdapter extends RecyclerView.Adapter<userSearchAdapter.ViewHolder> {

    List<regularUser> regularUsers = new ArrayList<>();
    LayoutInflater inflater;
    Context context;
    regularUser user;


    public userSearchAdapter(List<regularUser> regularUsers, Context context, regularUser user) {
        this.regularUsers = regularUsers;
        this.context = context;
        this.user = user;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_search_row_design, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.userSearchUsername.setText(regularUsers.get(position).getUsername());
        holder.userSearchUni.setText(regularUsers.get(position).getUni());
        downloadPhoto(regularUsers.get(position).getProfile_photo(), holder.userSearchProfilePhoto);
        holder.userSearchRowLinearLayout.setTag(holder);

        holder.userSearchRowLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewHolder holder1 = (ViewHolder)view.getTag();
                int position = holder1.getAdapterPosition();

                Intent intent = new Intent(context, otherUserProfile.class);
                intent.putExtra("user", user);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return regularUsers.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addListToRegularUsers(List<regularUser> newList) {
        regularUsers.addAll(newList);
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView userSearchUsername;
        TextView userSearchUni;
        ImageView userSearchProfilePhoto;
        ImageView userSearchUniLogo;
        LinearLayout userSearchRowLinearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userSearchUsername = itemView.findViewById(R.id.userSearchUsername);
            userSearchUni = itemView.findViewById(R.id.userSearchUni);
            userSearchProfilePhoto = itemView.findViewById(R.id.userSearchProfilePhoto);
            userSearchUniLogo = itemView.findViewById(R.id.userSearchUniLogo);
            userSearchRowLinearLayout = itemView.findViewById(R.id.userSearchRowLinearLayout);
        }
    }

    public void downloadPhoto(String fileName, ImageView imageView) {
        FirebaseStorage firebaseStorage;
        StorageReference storageReference;
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        StorageReference reference = storageReference.child("profile_photos/"+fileName);
        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
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
