package com.spam.data;

import com.spam.models.Event;

import com.spam.models.Organizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EventDaoDB implements EventDao {

    private final JdbcTemplate jdbc;

    @Autowired
    public EventDaoDB(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public static final class EventMapper implements RowMapper<Event> {

        @Override
        public Event mapRow(ResultSet rs, int index) throws SQLException {
            Event event = new Event();
            event.setEventId(rs.getInt("eventId"));
            event.setLocation(rs.getString("location"));
            event.setEventTime(rs.getString("eventTime"));
            event.setEventDate(rs.getString("eventDate"));
            event.setEventTitle(rs.getString("eventTitle"));
            event.setDescription(rs.getString("description"));
            event.setFoodType(rs.getString("foodType"));
            event.setOrganization(rs.getString("organization"));
            return event;
        }
    }

    @Override
    public List<Event> getAllEvents() {
        final String SELECT_ALL_EVENTS = "SELECT * FROM events";
        List<Event> events = jdbc.query(SELECT_ALL_EVENTS, new EventMapper());

        addOrganizerToEvents(events);

        return events;
    }

    @Override
    public Event getEventById(int id) {
        try {
            final String SELECT_EVENT_BY_ID = "SELECT * FROM events WHERE eventId = ?";
            Event event = jdbc.queryForObject(SELECT_EVENT_BY_ID, new EventMapper(), id);

            event.setOrganizerId(getOrganizerForEvent(event).getOrganizerId());

            return event;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Event addEvent(Event event) {
        final String INSERT_EVENT = "INSERT INTO events(organizerId, location, eventTime, eventDate, eventTitle, description, foodType, organization) VALUES (?,?,?,?,?,?,?,?)";
        jdbc.update(INSERT_EVENT,
                event.getOrganizerId(),
                event.getLocation(),
                event.getEventTime(),
                event.getEventDate(),
                event.getEventTitle(),
                event.getDescription(),
                event.getFoodType(),
                event.getOrganization());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        event.setEventId(newId);
        return event;
    }

    @Override
    public void updateEvent(Event event) {
        final String UPDATE_EVENT = "UPDATE events SET organizerId = ?, location = ?, eventTime = ?, eventDate = ?, eventTitle = ?, description = ?, foodType = ?, organization = ?";
        jdbc.update(UPDATE_EVENT,
                event.getOrganizerId(),
                event.getLocation(),
                event.getEventTime(),
                event.getEventDate(),
                event.getEventTitle(),
                event.getDescription(),
                event.getFoodType(),
                event.getOrganization());
    }

    @Override
    public void deleteEventById(int id) {
        final String DELETE_EVENT = "DELETE FROM events WHERE eventId = ?";
        jdbc.update(DELETE_EVENT, id);
    }

    @Override
    public List<Event> getEventsForOrganizer(Organizer organizer) {
        final String SELECT_EVENTS_FOR_ORGANIZER = "SELECT * FROM events WHERE organizerId = ?";
        List<Event> events = jdbc.query(SELECT_EVENTS_FOR_ORGANIZER, new EventMapper(), organizer.getOrganizerId());

        addOrganizerToEvents(events);

        return events;
    }



    /* * * HELPER FUNCTIONS * * */

    private void addOrganizerToEvents(List<Event> events) {
        for(Event e : events) {
            e.setOrganizerId(getOrganizerForEvent(e).getOrganizerId());
        }
    }

    private Organizer getOrganizerForEvent(Event event) {
        final String SELECT_ORGANIZER_FOR_EVENT = "SELECT o.* FROM organizers o "
                + "INNER JOIN events e ON o.organizerId = e.organizerId WHERE e.eventId = ?";
        return jdbc.queryForObject(SELECT_ORGANIZER_FOR_EVENT, new OrganizerDaoDB.OrganizerMapper(), event.getEventId());
    }
}
