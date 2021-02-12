package com.spam.controller;

import com.spam.data.EventDao;
import com.spam.data.UserDao;
import com.spam.models.Event;
import com.spam.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppController {
    private final UserDao userDao;
    private final EventDao eventDao;

    public AppController(UserDao userDao, EventDao eventDao) {
        this.userDao = userDao;
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
    @PutMapping("/edit/event")
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
            return "Deletion made";
        } else {
            return "No deletion made";
        }
    }

    @CrossOrigin
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @CrossOrigin
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) {
        User user = userDao.getUserById(userId);
        if(user == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(user);
    }

    @CrossOrigin
    @PostMapping("/create/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userDao.addNewUser(user);
    }

    @CrossOrigin
    @PutMapping("/edit/user")
    public String editUser(@RequestBody User user) {
        if(userDao.updateUser(user)) {
            return "Changes made";
        } else {
            return "No changes made";
        }
    }

    @CrossOrigin
    @DeleteMapping("/delete/user/{userId}")
    public String deleteUser(@PathVariable int userId) {
        if(userDao.delById(userId)) {
            return "Deletion made";
        } else {
            return "No deletion made";
        }
    }
}
