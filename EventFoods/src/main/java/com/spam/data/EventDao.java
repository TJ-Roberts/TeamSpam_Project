package com.spam.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.spam.models.Event;

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
	
	private static final class EventMapper implements RowMapper<Event> {

		@Override
		public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
			Event newEvent = new Event();
			newEvent.setEventId(rs.getInt("eventId"));
			newEvent.setOrganizerId(rs.getInt("organizerId"));
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
	
	@Override
	public List<Event> getAllEvents() {
		return jdbc_template.query("SELECT * FROM events", new EventMapper());
	}

	@Override
	public Event getEventById(int id) {
		return jdbc_template.queryForObject("SELECT * FROM events WHERE eventId = ?", new EventMapper(), id);
	}

	@Override
	public boolean delById(int id) {
		int rows = jdbc_template.update("DELETE FROM events WHERE eventId = ?", id);
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
		
		return rows != 0 ? true : false;
	}

	@Override
	public Event addNewEvent(Event event) {
		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("location", event.getLocation())
				.addValue("eventDate", event.getEventDate())
		        .addValue("eventTime", event.getEventTime())
		        .addValue("eventTitle", event.getEventTitle())
		        .addValue("organizerId", event.getOrganizerId())
		        .addValue("description", event.getDescription())
		        .addValue("foodType", event.getFoodType())
		        .addValue("organization", event.getOrganization());
		
		// adds the event and returns the value of the auto_increment column
		Number id = simpleJdbcInsert.executeAndReturnKey(params);
		event.setEventId(id.intValue());
		return event; // event object is returned with the generated id
	}

	@Override
	public List<Event> getEventsByOrganizer(int organizerId) {
		return jdbc_template.query("SELECT * FROM events WHERE organizerId = ?", new EventMapper(), organizerId);
	}

}
