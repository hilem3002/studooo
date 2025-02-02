package com.example.studooodemoapi.Service;

import com.example.studooodemoapi.Model.Comment;
import com.example.studooodemoapi.Model.Event;
import com.example.studooodemoapi.Model.Posts;
import com.example.studooodemoapi.Model.regularUser;
import com.example.studooodemoapi.Repository.commentRepository;
import com.example.studooodemoapi.Repository.eventRepository;
import com.example.studooodemoapi.Repository.postRepository;
import com.example.studooodemoapi.Repository.userRepository;
import org.apache.coyote.Request;
import org.apache.juli.logging.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public abstract class userService {

    private final userRepository repository;
    private final postRepository repositoryPost;
    private final commentRepository commentRepository;
    private final eventRepository eventRepository;
    public Page<regularUser> getRegularUsersByFilter(int offset, int pageSize, String letter) {
        return getRepository().findUserByFilter(PageRequest.of(offset, pageSize), letter);
    }
    regularUser user;

    public userService(userRepository repository, postRepository repositoryPost, commentRepository commentRepository, eventRepository eventRepository) {
        this.repository = repository;
        this.repositoryPost = repositoryPost;
        this.commentRepository = commentRepository;
        this.eventRepository = eventRepository;
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
    public regularUser changeInfo(String username, regularUser user) {
        regularUser oldUser = getRepository().findByUsername(username).get(0);
        oldUser.setName(user.getName());
        oldUser.setUsername(user.getUsername());
        oldUser.setPass(user.getPass());
        oldUser.setProfile_photo(user.getProfile_photo());
        oldUser.setBio(user.getBio());
        oldUser.setUni(user.getUni());
        return getRepository().save(oldUser);
    }

    // getting posts from database with their ids
    public List<Posts> getPosts(int postId, String username) {

        if (!getRepository().findByUsername(username).isEmpty()) {
            user = getRepository().findByUsername(username).get(0);
            return getRepositoryPost().findPostByUser(user);
        }
        else if (postId != 0) {
            return getRepositoryPost().findPostByPostId(postId);
        }
        else {
            return getRepositoryPost().findAll();
        }
    }

    // adding a post to database
    public Posts sendPost(Posts posts) {
        return getRepositoryPost().save(posts);
    }

    // deleting the post from database
    public Posts deletePost(int postId) {
        getRepositoryPost().deleteById(postId);
        return new Posts();
    }

    public List<Comment> getComments(int postId) {

        Posts posts = getRepositoryPost().findPostByPostId(postId).get(0);
        return getCommentRepository().findCommentByPosts(posts);

    }

    public Comment postComment(Comment comment) {

        return getCommentRepository().save(comment);

    }

    public Comment deleteComment(int commentId) {

        getCommentRepository().deleteById(commentId);
        return new Comment();

    }

    public Posts putPost(int postId, Posts post){
        Posts newPost = getRepositoryPost().findPostByPostId(postId).get(0);
        newPost.setLike(post.getLike());
        return  getRepositoryPost().save(newPost);
    }

    public List<Event> getEvents() {
        return getEventRepository().findAll();
    }

    public Event postEvent(Event event) {
        return getEventRepository().save(event);
    }

    public Event deleteEvent(int eventId) {

        getEventRepository().deleteById(eventId);
        return new Event();

    }


    public userRepository getRepository() {
        return repository;
    }

    public postRepository getRepositoryPost() {
        return repositoryPost;
    }

    public com.example.studooodemoapi.Repository.commentRepository getCommentRepository() {
        return commentRepository;
    }

    public com.example.studooodemoapi.Repository.eventRepository getEventRepository() {
        return eventRepository;
    }



}
