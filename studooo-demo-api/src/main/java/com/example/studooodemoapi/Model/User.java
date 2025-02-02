package com.example.studooodemoapi.Model;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int user_id;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;
    @Column(name = "eposta", length = 50, nullable = false, unique = true)
    private String eposta;
    @Column(name = "pass", length = 50, nullable = false)
    private String pass;
    @Column(name = "profile_photo", length = 2083)
    private String profile_photo;
    @Column(name = "bio", length = 100)
    private String bio;
    @Column(name = "uni", length = 50)
    private String uni;


    public User() {
    }

    public User(int user_id, String name, String username, String eposta, String pass, String profile_photo, String bio, String uni) {
        this.user_id = user_id;
        this.name = name;
        this.username = username;
        this.eposta = eposta;
        this.pass = pass;
        this.profile_photo = profile_photo;
        this.bio = bio;
        this.uni = uni;
    }

    // for sign up
    public User(String name, String username, String eposta, String pass) {
        this.name = name;
        this.username = username;
        this.eposta = eposta;
        this.pass = pass;
    }

    // for log in
    public User(String epostaUsername, String pass) {
        this.username = epostaUsername;
        this.pass = pass;
    }

    public User(String username) {
        this.username = username;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEposta() {
        return eposta;
    }

    public String getPass() {
        return pass;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public String getBio() {
        return bio;
    }

    public String getUni() {
        return uni;
    }

    public void setName(String name) {
        if (!name.equals(getName())) {
            this.name = name;
        }
    }

    public void setUsername(String username) {
        if (!username.equals(getUsername())) {
            this.username = username;
        }
    }

    public void setEposta(String eposta) {
        if (!eposta.equals(getEposta())) {
            this.eposta = eposta;
        }
    }

    public void setPass(String pass) {
        if (!pass.equals(getPass())) {
            this.pass = pass;
        }
    }

    public void setProfile_photo(String profile_photo) {
        if (!profile_photo.equals(getProfile_photo())) {
            this.profile_photo = profile_photo;
        }
    }

    public void setBio(String bio) {
        if (!bio.equals(getBio())) {
            this.bio = bio;
        }
    }

    public void setUni(String uni) {
        if (!uni.equals(getUni())) {
            this.uni = uni;
        }
    }
}
