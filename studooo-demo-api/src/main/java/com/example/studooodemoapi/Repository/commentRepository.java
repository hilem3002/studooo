package com.example.studooodemoapi.Repository;

import com.example.studooodemoapi.Model.Comment;
import com.example.studooodemoapi.Model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface commentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findCommentByPosts(Posts posts);
}
