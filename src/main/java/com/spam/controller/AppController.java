package com.spam.controller;

import com.spam.data.EventDao;
import com.spam.data.OrganizerDao;
import com.spam.models.Event;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventDao.getAllEvents();
    }
}
