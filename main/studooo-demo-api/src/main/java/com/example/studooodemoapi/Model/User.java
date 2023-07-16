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

    public User() {
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

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
