package com.example.studooodemoapi.Service;

import com.example.studooodemoapi.Model.regularUser;
import com.example.studooodemoapi.Repository.userRepository;
import org.springframework.stereotype.Service;

@Service
public class regularUserService extends userService{

    public regularUserService(userRepository repository) {
        super(repository);
    }

    @Override
    public regularUser signUp(regularUser user) {
        return getRepository().save(user);
    }
}
