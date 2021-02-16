package com.spam.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spam.models.Event;
import com.spam.models.User;

/**
 * Custom row mappers for db queries
 */
public class Mapper {

	public static final class EventMapper implements RowMapper<Event> {

		@Override
		public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
			Event newEvent = new Event();
			newEvent.setEventId(rs.getInt("eventId"));
			newEvent.setUserId(rs.getInt("userId"));
			newEvent.setEventDate(rs.getString("eventDate"));
			newEvent.setEventTime(rs.getString("eventTime"));
			newEvent.setEventTitle(rs.getString("eventTitle"));
			newEvent.setLocation(rs.getString("location"));
			newEvent.setDescription(rs.getString("description"));
			newEvent.setFoodType(rs.getString("foodType"));
			newEvent.setOrganization(rs.getString("organization"));
			
			return newEvent;
		}
	}
	
	public static final class UserMapper implements RowMapper<User> {

		@Override 
		public User mapRow(ResultSet rs, int rowNum) throws SQLException { 
			User newUser = new User();
			newUser.setUserId(rs.getInt("userId"));
			newUser.setFirstName(rs.getString("firstName"));
			newUser.setLastName(rs.getString("lastName"));
			newUser.setOrganizer(rs.getBoolean("isOrganizer"));
			newUser.setRole(rs.getString("role"));
			newUser.setSummary(rs.getString("summary"));

			return newUser; 
		} 
	}
}