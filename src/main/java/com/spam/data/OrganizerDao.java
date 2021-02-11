package com.spam.data;

import org.springframework.stereotype.Repository;

import com.spam.models.Organizer;

@Repository
public interface OrganizerDao {

    /**
     * Adds the specified organizer to the db
     * and returns the organizer with the genereated id
     * @param organizer the organizer to add
     * @return the added organizer
     */
    Organizer addNewOrganizer(Organizer organizer);
}