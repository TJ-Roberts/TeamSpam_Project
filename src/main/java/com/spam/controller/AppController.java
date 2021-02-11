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

    @CrossOrigin
    @PostMapping("/create/event")
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@RequestBody Event e) {
        return eventDao.addNewEvent(e);
    }

    @CrossOrigin
    @PutMapping("/edit/event/{eventId}")
    public String editEvent(@RequestBody Event e) {
        if(eventDao.updateEvent(e)) {
            return "Changes made";
        } else {
            return "No changes made";
        }
    }

    @CrossOrigin
    @DeleteMapping("/delete/event/{eventId}")
    public String deleteEvent(@PathVariable int eventId) {
        if(eventDao.delById(eventId)) {
            return "Event deleted";
        } else {
            return "Event not deleted";
        }
    }
}
