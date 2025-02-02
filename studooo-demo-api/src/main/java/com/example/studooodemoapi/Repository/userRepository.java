package com.example.studooodemoapi.Repository;

import com.example.studooodemoapi.Model.regularUser;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;


public interface userRepository extends JpaRepository<regularUser, Integer> {
    List<regularUser> findByUsername(String username);

    List<regularUser> findByEposta(String username);

    @Query("SELECT r FROM regularUser r WHERE r.username LIKE CONCAT('%',:letter, '%') OR r.name LIKE CONCAT('%',:letter, '%')")
    Page<regularUser> findUserByFilter(PageRequest of, String letter);


}
