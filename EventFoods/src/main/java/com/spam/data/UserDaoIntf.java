package com.spam.data;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spam.models.User;

@Repository
public interface UserDaoIntf {

	/**
	 * Adds the specified user to the db
	 * and returns the user with the genereated id
	 * @param user the user to add
	 * @return the added user
	 */
	User addNewUser(User user);
	
	/**
	 * Updates the specified user with the provided information
	 * @param user the user to update along with the info to use
	 * @return true if the update was successful, false otherwise
	 */
	boolean updateUser(User user);
	
	/**
	 * Deletes the specified user
	 * @param id the id of the user to delete
	 * @return true if the delete was successful, false otherwise
	 */
	boolean delById(int id);
	
	/**
	 * Gets a list of all the users
	 * @return List of all users
	 */
	List<User> getAllUsers();
	
	/**
	 * Gets the specified user
	 * @param id id of the user to get
	 * @return the specified user
	 */
	User getUserById(int id);
	
	/**
	 * Gets a list of all users that will attend the specified event
	 * @param eventId the specified event
	 * @return list of users going to the event
	 */
	List<User> getAttendingUsers(int eventId);
}
