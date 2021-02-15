package com.spam.data;

import org.springframework.stereotype.Repository;

import com.spam.models.User;

import java.util.List;

@Repository
public interface UserDao {

    /**
     * Adds the specified organizer to the db
     * and returns the organizer with the genereated id
     * @param user the organizer to add
     * @return the added organizer
     */
    User addNewUser(User user);

    boolean updateUser(User user);

    boolean delById(int id);

    List<User> getAllUsers();

    /**
     * Gets the specified event
     * @param id id of the event
     * @return the event object
     */
    User getUserById(int id);
}