package com.spam.eventFoods.data;

import org.springframework.stereotype.Repository;

import com.spam.eventFoods.models.Organizer;

@Repository
public interface OrganizerDaoIntf {

	/**
	 * Adds the specified organizer to the db
	 * and returns the organizer with the genereated id
	 * @param organizer the organizer to add
	 * @return the added organizer
	 */
	Organizer addNewOrganizer(Organizer organizer);
}
