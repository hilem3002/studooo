package com.example.studooov2.UserSignUpLogin.Models;

public class Comment {

    int comment_id;
    String comment_text;
    Posts posts;
    regularUser user;

    public Comment() {

    }

    public Comment(int comment_id, String comment_text, Posts posts, regularUser user) {
        this.comment_id = comment_id;
        this.comment_text = comment_text;
        this.posts = posts;
        this.user = user;
    }

    public Comment(String comment_text, Posts posts, regularUser user) {
        this.comment_text = comment_text;
        this.posts = posts;
        this.user = user;
    }

    public int getComment_id() {
        return comment_id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public Posts getPosts() {
        return posts;
    }

    public regularUser getUser() {
        return user;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    public void setUser(regularUser user) {
        this.user = user;
    }
}
