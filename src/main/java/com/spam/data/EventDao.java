package com.spam.data;

import com.spam.models.Event;
import com.spam.models.Organizer;

import java.util.List;

public interface EventDao {
    List<Event> getAllEvents();
    Event getEventById(int id);
    Event addEvent(Event event);
    void updateEvent(Event event);
    void deleteEventById(int id);
    List<Event> getEventsForOrganizer(Organizer organizer);
}
