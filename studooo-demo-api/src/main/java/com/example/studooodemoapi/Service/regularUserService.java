package com.example.studooodemoapi.Service;

import com.example.studooodemoapi.Model.regularUser;
import com.example.studooodemoapi.Repository.commentRepository;
import com.example.studooodemoapi.Repository.eventRepository;
import com.example.studooodemoapi.Repository.postRepository;
import com.example.studooodemoapi.Repository.userRepository;
import org.springframework.stereotype.Service;

@Service
public class regularUserService extends userService{

    public regularUserService(userRepository repository, postRepository repositoryPost, commentRepository commentRepository, eventRepository  eventRepository) {
        super(repository, repositoryPost, commentRepository, eventRepository);
    }

    @Override
    public regularUser signUp(regularUser user) {
        return getRepository().save(user);
    }
}
