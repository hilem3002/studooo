package com.example.studooodemoapi.Service;

import com.example.studooodemoapi.Model.regularUser;
import com.example.studooodemoapi.Repository.userRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class userService {

    private final userRepository repository;
    private final postRepository repositoryPost;

    public userService(userRepository repository, postRepository repositoryPost) {
        this.repository = repository;
        this.repositoryPost = repositoryPost;
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

    // getting posts from database with their ids
    public List<Posts> getPosts(int postId, regularUser user) {
        if (postId == 0 && user == null) {
            return getRepositoryPost().findAll();
        }
        else {
            if (user != null) {
                return getRepositoryPost().findPostByUser(user);
            }
            else {
                return getRepositoryPost().findPostByPostId(postId);
            }
        }
    }

    // adding a post to database
    public Posts sendPost(Posts posts) {
        return getRepositoryPost().save(posts);
    }

    // deleting the post from database
    public Posts deletePost(int postId) {
        return getRepositoryPost().deleteById(postId);
    }



    public userRepository getRepository() {
        return repository;
    }

    public postRepository getRepositoryPost() {
        return repositoryPost;
    }


}
