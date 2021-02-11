package com.spam.data;

import org.springframework.stereotype.Repository;

import com.spam.models.Organizer;

import java.util.List;

@Repository
public interface OrganizerDao {

    /**
     * Adds the specified organizer to the db
     * and returns the organizer with the genereated id
     * @param organizer the organizer to add
     * @return the added organizer
     */
    Organizer addNewOrganizer(Organizer organizer);

    boolean updateOrganizer(Organizer organizer);

    boolean delById(int id);

    List<Organizer> getAllOrganizers();

    /**
     * Gets the specified event
     * @param id id of the event
     * @return the event object
     */
    Organizer getOrganizerById(int id);
}