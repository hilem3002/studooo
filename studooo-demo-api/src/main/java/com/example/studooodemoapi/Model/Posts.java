package com.example.studooodemoapi.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

import java.sql.Blob;

@Entity
@Table(name = "user_posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private int postId;
    @Column(name = "post_text", length = 50, nullable = false)
    private String post_text;
    @Column(name = "post_photo")
    private String post_photo;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private regularUser user;
    @Column(name = "post_like")
    private int like;

    public Posts(){

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
}


