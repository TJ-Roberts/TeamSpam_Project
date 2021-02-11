package com.spam.data;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spam.models.Event;

@Repository
public interface EventDao {

    /**
     * Gets a list of all the events
     * @return list of events
     */
    List<Event> getAllEvents();

    /**
     * Gets the specified event
     * @param id id of the event
     * @return the event object
     */
    Event getEventById(int id);

    /**
     * Deletes the event by the specified id
     * @param id id of the event to remove
     * @return true if the event was removed, false otherwise
     */
    boolean delById(int id);

    /**
     * Updates the specified event with the given information.
     * Replaces info stored in the db with that of what was passed in,
     * for the one that was passed in.
     * @param event the event info to store for the event with this id
     * @return true if the record was updated, false otherwise
     */
    boolean updateEvent(Event event);

    /**
     * Adds the specified event
     * and returns the event with the generated id
     * @param event the event to add
     * @return the added event
     */
    Event addNewEvent(Event event);
}