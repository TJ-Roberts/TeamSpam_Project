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
    @PostMapping("/create/event/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@RequestBody Event event, @PathVariable int userId) {
        event.setUser(userDao.getUserById(userId));
        return eventDao.addNewEvent(event);
    }

    @CrossOrigin
    @PutMapping("/edit/event")
    public String editEvent(@RequestBody Event event) {
        return eventDao.updateEvent(event) ? "Changes made" : "No changes made";
    }

    @CrossOrigin
    @DeleteMapping("/delete/event/{eventId}")
    public String deleteEvent(@PathVariable int eventId) {
        return eventDao.delById(eventId) ? "Deletion made" : "No deletion made";
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
        return userDao.updateUser(user) ? "Changes made" : "No changes made";
    }

    @CrossOrigin
    @DeleteMapping("/delete/user/{userId}")
    public String deleteUser(@PathVariable int userId) {
        return userDao.delById(userId) ? "Deletion made" : "No deletion made";
    }

    @CrossOrigin
    @PutMapping("/add/attendee/{eventId}/{userId}")
    public String addAttendee(@PathVariable int eventId, @PathVariable int userId) {
        Event event = eventDao.getEventById(eventId);

        List<User> attendees = event.getAttendees();
        attendees.add(userDao.getUserById(userId));
        event.setAttendees(attendees);

        return eventDao.updateEvent(event) ? "Changes made" : "No changes made";
    }

    @CrossOrigin
    @PutMapping("/remove/attendee/{eventId}/{userId}")
    public String removeAttendee(@PathVariable int eventId, @PathVariable int userId) {
        Event event = eventDao.getEventById(eventId);

        List<User> attendees = event.getAttendees();
        attendees.remove(userDao.getUserById(userId));
        event.setAttendees(attendees);

        return eventDao.updateEvent(event) ? "Changes made" : "No changes made";
    }

    @CrossOrigin
    @PutMapping("/edit/event/details")
    public String editEventDetails(@RequestBody Event event) {
        Event e = eventDao.getEventById(event.getEventId());

        event.setUser(e.getUser());
        event.setAttendees(e.getAttendees());

        return eventDao.updateEvent(event) ? "Changes made" : "No changes made";
    }

    @CrossOrigin
    @GetMapping("/events/creator/{userId}")
    public List<Event> getCreatedEvents(@PathVariable int userId) {
        return eventDao.getEventsForCreator(userDao.getUserById(userId));
    }

    @CrossOrigin
    @GetMapping("/events/attendee/{userId}")
    public List<Event> getAttendingEvents(@PathVariable int userId) {
        return eventDao.getEventsForAttendee(userDao.getUserById(userId));
    }

    @CrossOrigin
    @GetMapping("/attendees/{eventId}")
    public List<User> getAttendees(@PathVariable int eventId) {
        return eventDao.getEventById(eventId).getAttendees();
    }
}
