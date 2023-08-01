package com.example.studooodemoapi.Model;

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
    private byte[] post_photo;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private regularUser user;

    public Posts(){

    }

    // posts with pic
    public Posts(String post_text, byte[] post_photo, regularUser user) {
        this.post_text = post_text;
        this.post_photo = post_photo;
        this.user = user;
    }

    // posts without pics
    public Posts(String post_text, regularUser user) {
        this.post_text = post_text;
        this.user = user;
    }

    public int getPostId() {
        return postId;
    }

    public String getPost_text() {
        return post_text;
    }

    public byte[] getPost_photo() {
        return post_photo;
    }

    public regularUser getUser() {
        return user;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setPost_text(String post_text) {
        this.post_text = post_text;
    }

    public void setPost_photo(byte[] post_photo) {
        this.post_photo = post_photo;
    }

    public void setUser(regularUser user) {
        this.user = user;
    }
}
