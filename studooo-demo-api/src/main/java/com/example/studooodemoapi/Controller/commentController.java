package com.example.studooodemoapi.Controller;

import com.example.studooodemoapi.Model.Comment;
import com.example.studooodemoapi.Service.regularUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class commentController {

    private final regularUserService regularUserService;

    public commentController(com.example.studooodemoapi.Service.regularUserService regularUserService) {
        this.regularUserService = regularUserService;
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getComments(@RequestParam int postId) {
        return new ResponseEntity<>(regularUserService.getComments(postId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Comment> postComment(@RequestBody Comment comment) {
        return new ResponseEntity<>(regularUserService.postComment(comment), HttpStatus.CREATED);
    }

    @DeleteMapping("/{comment_id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable int comment_id) {
        return new ResponseEntity<>(regularUserService.deleteComment(comment_id), HttpStatus.OK);
    }
}
