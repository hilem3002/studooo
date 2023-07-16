package com.example.studooodemoapi.Service;

import com.example.studooodemoapi.Model.regularUser;
import com.example.studooodemoapi.Repository.userRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class userService {

    private final userRepository repository;

    public userService(userRepository repository) {
        this.repository = repository;
    }

    // getting the users with these parameters
    public List<regularUser> getRegularUsers(String username, String eposta) {
        if (username == null && eposta == null) {
            return getRepository().findAll();
        }
        else {
            if (getRepository().findByUsername(username).isEmpty()) {
                return getRepository().findByEposta(eposta);
            }
            else if (getRepository().findByEposta(eposta).isEmpty()) {
                return getRepository().findByUsername(username);
            }
            else {
                return getRepository().findByEposta(eposta);
            }
        }
    }

    public abstract regularUser signUp(regularUser user);

    // changing the user's password
    public regularUser changePass(regularUser user) {
        regularUser oldUser = getRepository().findByUsername(user.getUsername()).get(0);
        oldUser.setPass(user.getPass());
        return getRepository().save(oldUser);
    }



    public userRepository getRepository() {
        return repository;
    }


}
