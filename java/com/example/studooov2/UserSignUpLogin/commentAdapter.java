package com.example.studooov2.UserSignUpLogin;

import android.content.Context;
import android.content.Intent;
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
import com.example.studooov2.UserSignUpLogin.Activities.loginActivities.ErrorMessageActivity;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.ApiRequest;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnCommentLoadedListener;
import com.example.studooov2.UserSignUpLogin.Models.Comment;
import com.example.studooov2.UserSignUpLogin.Models.Posts;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class commentAdapter extends RecyclerView.Adapter<commentAdapter.CardViewHolder> {

    private Context context;
    private List<Comment> commentList;
    private regularUser user;
    private RecyclerView rv;
    public commentAdapter(Context context, List<Comment> commentList, regularUser user, RecyclerView rv) {
        this.context = context;
        this.commentList = commentList;
        this.user = user;
        this.rv = rv;
    }

    public void addNewComment(Comment comment) {
        commentList.add(comment);
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        CardView commentCard;
        TextView commentText;
        TextView commentUsername;
        CircleImageView pp;
        ImageButton commentDeleteButton;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            commentCard = itemView.findViewById(R.id.commentCard);
            commentText = itemView.findViewById(R.id.commentText);
            commentUsername = itemView.findViewById(R.id.username);
            pp = itemView.findViewById(R.id.pp);
            commentDeleteButton = itemView.findViewById(R.id.deleteCommentButton);
        }
    }
    @NonNull
    @Override
    public commentAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_card_design, parent, false);
       return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull commentAdapter.CardViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.commentText.setText(comment.getComment_text());
        holder.commentUsername.setText(comment.getUser().getUsername());
        downloadPhoto(comment.getUser().getProfile_photo(), holder.pp);

        if (comment.getUser().getUser_id() == user.getUser_id()) {
            holder.commentDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ApiRequest request = new ApiRequest();
                    request.deleteRequest(comment.getComment_id(), new OnCommentLoadedListener() {
                        @Override
                        public void OnCommentLoading(Boolean loadingSuccess) {
                            Toast.makeText(context, "siliniyor", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void OnCommentLoaded(Comment comment1) {
                            if (comment1.getComment_id() == 0) {
                                commentList.remove(comment);
                                commentAdapter adapter = new commentAdapter(context, commentList, user, rv);
                                rv.setAdapter(adapter);
                            }
                        }

                        @Override
                        public void OnCommentLoadFailed(Boolean errorSuccess) {
                            Intent intent = new Intent(context, ErrorMessageActivity.class);
                            context.startActivity(intent);
                        }
                    });
                }
            });
        }
        else {
            holder.commentDeleteButton.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return commentList.size();
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
}
