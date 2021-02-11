package com.spam.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spam.data.EventDaoIntf;
import com.spam.data.UserDaoIntf;
import com.spam.models.Event;

@RestController
@RequestMapping("/api")
public class AppController {

	private final UserDaoIntf organizerDao;
    private final EventDaoIntf eventDao;

    public AppController(UserDaoIntf organizerDao, EventDaoIntf eventDao) {
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
