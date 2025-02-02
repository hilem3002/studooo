package com.example.studooodemoapi.Repository;

import com.example.studooodemoapi.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface eventRepository extends JpaRepository<Event, Integer> {
}
