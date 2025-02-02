package com.example.studooodemoapi.Controller;

import com.example.studooodemoapi.Model.Event;
import com.example.studooodemoapi.Service.regularUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class eventController {

    private final regularUserService regularUserService;

    public eventController(com.example.studooodemoapi.Service.regularUserService regularUserService) {
        this.regularUserService = regularUserService;
    }

    @GetMapping
    public ResponseEntity<List<Event>> getEvents() {
        return new ResponseEntity<>(regularUserService.getEvents(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Event> postEvent(@RequestBody Event event) {
        return new ResponseEntity<>(regularUserService.postEvent(event), HttpStatus.CREATED);
    }

    @DeleteMapping("/{event_id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable int event_id) {
        return new ResponseEntity<>(regularUserService.deleteEvent(event_id), HttpStatus.OK);
    }

}
