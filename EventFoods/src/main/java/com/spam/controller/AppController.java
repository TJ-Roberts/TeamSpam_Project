package com.spam.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spam.data.EventDaoIntf;
import com.spam.data.OrganizerDaoIntf;
import com.spam.models.Event;

@RestController
@RequestMapping("/api")
public class AppController {

	private final OrganizerDaoIntf organizerDao;
    private final EventDaoIntf eventDao;

    public AppController(OrganizerDaoIntf organizerDao, EventDaoIntf eventDao) {
        this.organizerDao = organizerDao;
        this.eventDao = eventDao;
    }

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventDao.getAllEvents();
    }
}
