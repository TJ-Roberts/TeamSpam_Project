package com.spam.controller;

import com.spam.data.EventDao;
import com.spam.data.OrganizerDao;
import com.spam.models.Event;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppController {
    private final OrganizerDao organizerDao;
    private final EventDao eventDao;

    public AppController(OrganizerDao organizerDao, EventDao eventDao) {
        this.organizerDao = organizerDao;
        this.eventDao = eventDao;
    }

    @CrossOrigin
    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventDao.getAllEvents();
    }

    @CrossOrigin
    @GetMapping("/events/{eventId}")
    public ResponseEntity<Event> getEventById(@PathVariable int eventId) {
        Event event = eventDao.getEventById(eventId);
        if(event == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(event);
    }
}
