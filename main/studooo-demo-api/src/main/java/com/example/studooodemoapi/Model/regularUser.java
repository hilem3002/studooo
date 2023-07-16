package com.example.studooodemoapi.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_info")
public class regularUser extends User {
    public regularUser() {
    }

    public regularUser(String name, String username, String eposta, String pass) {
        super(name, username, eposta, pass);
    }

    public regularUser(String epostaUsername, String pass) {
        super(epostaUsername, pass);
    }

    public regularUser(String username) {
        super(username);
    }

}
