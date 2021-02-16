package com.spam.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.mysql.cj.xdevapi.JsonArray;
import com.spam.models.Event;
import com.spam.models.User;

@Repository
public class EventDao implements EventDaoIntf {

	@Autowired
	private final JdbcTemplate jdbc_template;
	private SimpleJdbcInsert simpleJdbcInsert;
	
	public EventDao(JdbcTemplate jdbcTemplate) {
		jdbc_template = jdbcTemplate;
		simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
								.withTableName("events")
								.usingGeneratedKeyColumns("eventId");
	}
	
	@Override
	public List<Event> getAllEvents() {
		List<Event> events = new ArrayList<Event>();
		events = jdbc_template.query("SELECT * FROM events", new Mapper.EventMapper());
		for (Event event : events) {
			event.setAttending(getAttendingUsers(event.getEventId()));
		}
		
		return events;
	}

	@Override
	public Event getEventById(int id) {
		try {
			Event event = jdbc_template.queryForObject("SELECT * FROM events WHERE eventId = ?", new Mapper.EventMapper(), id);
			event.setAttending(getAttendingUsers(id));
			return event;
			
		} catch (DataAccessException err) {
			return null;
			
		}
	}

	@Override
	public boolean delById(int id) {
		int rows = jdbc_template.update("DELETE FROM attending WHERE eventId = ?", id);
		rows += jdbc_template.update("DELETE FROM events WHERE eventId = ?", id);
		return rows != 0 ? true : false;
	}

	@Override
	public boolean updateEvent(Event event) {
		int rows = jdbc_template.update("UPDATE events "
					+ "SET location = ?, eventTime = ?, eventDate = ?, eventTitle = ?, "
					+ "description = ?, foodType = ?, organization = ? "
					+ "WHERE eventId = ?", 
					event.getLocation(), event.getEventTime(), 
					event.getEventDate(), event.getEventTitle(), 
					event.getDescription(), event.getFoodType(),
					event.getOrganization(), event.getEventId());
		
		updateAttending(event);
		return rows != 0 ? true : false;
	}

	@Override
	public Event addNewEvent(Event event) {
		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("location", event.getLocation())
				.addValue("eventDate", event.getEventDate())
		        .addValue("eventTime", event.getEventTime())
		        .addValue("eventTitle", event.getEventTitle())
		        .addValue("userId", event.getUserId())
		        .addValue("description", event.getDescription())
		        .addValue("foodType", event.getFoodType())
		        .addValue("organization", event.getOrganization());
		
		updateAttending(event);
		// adds the event and returns the value of the auto_increment column
		Number id = simpleJdbcInsert.executeAndReturnKey(params);
		event.setEventId(id.intValue());
		return event; // event object is returned with the generated id
	}

	@Override
	public List<Event> getEventsByCreator(int userId) {
		return jdbc_template.query("SELECT * FROM events WHERE userId = ?", new Mapper.EventMapper(), userId);
	}

	@Override
	public List<Event> getEventsForAttendee(int userId) {
		return jdbc_template.query("SELECT * FROM attending JOIN events ON attending.eventId = events.eventId WHERE attending.userId = ?", new Mapper.EventMapper(), userId);
	}

	@Override
	public List<User> getAttendingUsers(int eventId) {
		return jdbc_template.query("SELECT * FROM attending JOIN users ON attending.userId = users.userId WHERE eventId = ?", new Mapper.UserMapper(), eventId);
	}
	
	@Override
	public boolean addAttending(int userId, int eventId) {
		int rows = jdbc_template.update("INSERT INTO attending(eventId, userId) VALUES (?, ?)", eventId, userId);
		return rows != 0 ? true : false;
	}

	@Override
	public boolean remAttending(int userId, int eventId) {
		int rows = jdbc_template.update("DELETE FROM attending WHERE userId = ? AND eventId = ?", userId, eventId);
		return rows != 0 ? true : false;
	}
	
	// helper function to set attending
	private void updateAttending(Event event) {
		jdbc_template.update("DELETE FROM attending WHERE eventId = ?", event.getEventId());
		for (User user : event.getAttending()) {
			jdbc_template.update("INSERT INTO attending(eventId, userId) VALUES (?, ?)", event.getEventId(), user.getUserId());
		}
	}
}
