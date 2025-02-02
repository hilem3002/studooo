package com.example.studooodemoapi.Controller;

import com.example.studooodemoapi.Model.Posts;
import com.example.studooodemoapi.Model.regularUser;
import com.example.studooodemoapi.Service.regularUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class postController {

    private final regularUserService regularUserService;


    public postController(regularUserService regularUserService) {
        this.regularUserService = regularUserService;
    }

    // getting the posts from database with their ids
    @GetMapping
    public ResponseEntity<List<Posts>> getPosts(@RequestParam(required = false) int postId, @RequestParam(required = false) String username) {
        return new ResponseEntity<>(regularUserService.getPosts(postId, username), HttpStatus.OK);
    }

    // adding a post to database
    @PostMapping
    public ResponseEntity<Posts> sendPost(@RequestBody Posts posts) {
        return new ResponseEntity<>(regularUserService.sendPost(posts), HttpStatus.CREATED);
    }

    // deleting the post with its postId
    @DeleteMapping("/{postId}")
    public ResponseEntity<Posts> deletePost(@PathVariable int postId) {
        return new ResponseEntity<>(regularUserService.deletePost(postId), HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Posts> putPost(@PathVariable int postId, @RequestBody Posts post) {
        return new ResponseEntity<>(regularUserService.putPost(postId,post),HttpStatus.OK);
    }
}
