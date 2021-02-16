package com.spam.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.spam.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.spam.models.Event;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EventDaoDB implements EventDao {

    @Autowired
    private final JdbcTemplate jdbc_template;
    private SimpleJdbcInsert simpleJdbcInsert;

    public EventDaoDB(JdbcTemplate jdbcTemplate) {
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
        final String SELECT_ALL_EVENTS = "SELECT * FROM events";
        List<Event> events = jdbc_template.query(SELECT_ALL_EVENTS, new EventMapper());

        addCreatorAndUsersToEvents(events);

        return events;
    }

    @Override
    public Event getEventById(int id) {
        try {
            final String SELECT_EVENT_BY_ID = "SELECT * FROM events WHERE eventID = ?";
            Event event = jdbc_template.queryForObject(SELECT_EVENT_BY_ID, new EventMapper(), id);
            event.setUser(getCreatorForEvent(event));
            event.setAttendees(getUsersForEvent(event));
            return event;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public boolean delById(int id) {
        final String DELETE_ATTENDEES = "DELETE FROM attending WHERE eventId = ?";
        jdbc_template.update(DELETE_ATTENDEES, id);

        final String DELETE_EVENT = "DELETE FROM events WHERE eventId = ?";
        int rows = jdbc_template.update(DELETE_EVENT, id);

        return rows != 0 ? true : false;
    }

    @Override
    @Transactional
    public boolean updateEvent(Event event) {
        final String UPDATE_EVENT = "UPDATE events "
                + "SET userId = ?, location = ?, eventTime = ?, eventDate = ?, "
                + "eventTitle = ?, foodType = ?, description = ?, organization = ? "
                + "WHERE eventId = ?";
        int rows = jdbc_template.update(UPDATE_EVENT,
                event.getUser().getUserId(), event.getLocation(),
                event.getEventTime(), event.getEventDate(),
                event.getEventTitle(), event.getFoodType(),
                event.getDescription(), event.getOrganization(),
                event.getEventId());

        final String DELETE_ATTENDEES = "DELETE FROM attending WHERE eventId = ?";
        jdbc_template.update(DELETE_ATTENDEES, event.getEventId());
        insertAttendees(event);

        return rows != 0 ? true : false;
    }

    @Override
    @Transactional
    public Event addNewEvent(Event event) {
        final String INSERT_EVENT = "INSERT INTO events"
                + "(userId, location, eventTime, eventDate, eventTitle, foodType, description, organization) "
                + "VALUES(?,?,?,?,?,?,?,?)";
        jdbc_template.update(INSERT_EVENT,
                event.getUser().getUserId(), event.getLocation(),
                event.getEventTime(), event.getEventDate(),
                event.getEventTitle(), event.getFoodType(),
                event.getDescription(), event.getOrganization());

        int newId = jdbc_template.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        event.setEventId(newId);

        insertAttendees(event);

        return event;
    }

    @Override
    public List<Event> getEventsForCreator(User user) {
        try {
            final String SELECT_EVENTS_FOR_CREATOR = "SELECT * FROM events WHERE userId = ?";
            List<Event> events = jdbc_template.query(SELECT_EVENTS_FOR_CREATOR,
                    new EventMapper(), user.getUserId());

            addCreatorAndUsersToEvents(events);

            return events;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Event> getEventsForAttendee(User user) {
        try {
            final String SELECT_EVENTS_FOR_USER = "SELECT * FROM events e "
                    + "INNER JOIN attending a ON e.eventId = a.eventId WHERE a.userId = ?";
            List<Event> events = jdbc_template.query(SELECT_EVENTS_FOR_USER,
                    new EventMapper(), user.getUserId());

            addCreatorAndUsersToEvents(events);

            return events;
        } catch(DataAccessException ex) {
            return null;
        }
    }



    /* * * HELPER FUNCTIONS * * */

    private User getCreatorForEvent(Event event) {
        final String SELECT_CREATOR_FOR_MEETING = "SELECT u.* FROM users u "
                + "INNER JOIN events e ON u.userId = e.userId WHERE e.eventId = ?";
        return jdbc_template.queryForObject(SELECT_CREATOR_FOR_MEETING, new UserDaoDB.UserMapper(), event.getEventId());
    }

    private List<User> getUsersForEvent(Event event) {
        final String SELECT_USERS_FOR_EVENT = "SELECT u.* FROM users u "
                + "INNER JOIN attending a ON u.userId = a.userId WHERE a.eventId = ?";
        return jdbc_template.query(SELECT_USERS_FOR_EVENT, new UserDaoDB.UserMapper(), event.getEventId());
    }

    private void addCreatorAndUsersToEvents(List<Event> events) {
        for(Event e : events) {
            e.setUser(getCreatorForEvent(e));
            e.setAttendees(getUsersForEvent(e));
        }
    }

    private void insertAttendees(Event event) {
        final String INSERT_ATTENDEES = "INSERT INTO attending"
                + "(eventId, userId) VALUES (?,?)";
        for(User u : event.getAttendees()) {
            jdbc_template.update(INSERT_ATTENDEES, event.getEventId(), u.getUserId());
        }
    }
}