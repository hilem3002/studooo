package com.example.studooov2.UserSignUpLogin.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Posts implements Parcelable {

    private int postId;
    private String post_text;
    private String post_photo;
    private regularUser user;
    private int like;

    public Posts(int postId, String post_text, String post_photo, regularUser user, int like) {
        this.postId = postId;
        this.post_text = post_text;
        this.post_photo = post_photo;
        this.user = user;
        this.like = like;
    }

    // posts with pic
    public Posts(String post_text, String post_photo, regularUser user, int like) {
        this.post_text = post_text;
        this.post_photo = post_photo;
        this.user = user;
        this.like = like;
    }

    // posts without pics
    public Posts(String post_text, regularUser user, int like) {
        this.post_text = post_text;
        this.user = user;
        this.like = like;
    }

    protected Posts(Parcel in) {
        postId = in.readInt();
        post_text = in.readString();
        post_photo = in.readString();
        user = in.readParcelable(regularUser.class.getClassLoader());
        like = in.readInt();
    }

    public static final Creator<Posts> CREATOR = new Creator<Posts>() {
        @Override
        public Posts createFromParcel(Parcel in) {
            return new Posts(in);
        }

        @Override
        public Posts[] newArray(int size) {
            return new Posts[size];
        }
    };

    public int getPostId() {
        return postId;
    }

    public String getPost_text() {
        return post_text;
    }

    public String getPost_photo() {
        return post_photo;
    }

    public regularUser getUser() {
        return user;
    }

    public int getLike() {
        return like;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setPost_text(String post_text) {
        this.post_text = post_text;
    }

    public void setPost_photo(String post_photo) {
        this.post_photo = post_photo;
    }

    public void setUser(regularUser user) {
        this.user = user;
    }

    public void setLike(int like) {
        this.like = like;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(postId);
        parcel.writeString(post_text);
        parcel.writeString(post_photo);
        parcel.writeParcelable(user, i);
        parcel.writeInt(like);
    }
}
