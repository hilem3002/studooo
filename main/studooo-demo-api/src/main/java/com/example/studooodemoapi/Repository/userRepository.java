package com.example.studooodemoapi.Repository;

import com.example.studooodemoapi.Model.regularUser;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;


public interface userRepository extends JpaRepository<regularUser, Integer> {
    List<regularUser> findByUsername(String username);

    List<regularUser> findByEposta(String username);
}
