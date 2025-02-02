package com.example.studooodemoapi.Repository;

import com.example.studooodemoapi.Model.Posts;
import com.example.studooodemoapi.Model.regularUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface postRepository extends JpaRepository<Posts, Integer> {
    List<Posts> findPostByPostId(int postId);

    Posts deleteById(int postId);

    List<Posts> findPostByUser(regularUser user);
}

