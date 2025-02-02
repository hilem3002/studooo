package com.example.studooodemoapi.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_info")
public class regularUser extends User {
    public regularUser() {
    }

    public regularUser(int user_id, String name, String username, String eposta, String pass, String profile_photo, String bio, String uni) {
        super(user_id, name, username, eposta, pass, profile_photo, bio, uni);
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
