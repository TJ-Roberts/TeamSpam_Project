package com.spam.data;

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
public class OrganizerDaoDB implements OrganizerDao {

    private final JdbcTemplate jdbc;

    @Autowired
    public OrganizerDaoDB(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public static final class OrganizerMapper implements RowMapper<Organizer> {

        @Override
        public Organizer mapRow(ResultSet  rs, int index) throws SQLException {
            Organizer organizer = new Organizer();
            organizer.setOrganizerId(rs.getInt("organizerId"));
            organizer.setFirstName(rs.getString("firstName"));
            organizer.setLastName(rs.getString("lastName"));
            organizer.setMembership(rs.getString("membership"));
            organizer.setRole(rs.getString("role"));
            organizer.setSummary(rs.getString("summary"));
            return organizer;
        }
    }

    @Override
    public List<Organizer> getAllOrganizers() {
        final String SELECT_ALL_ORGANIZERS = "SELECT * FROM organizers";
        return jdbc.query(SELECT_ALL_ORGANIZERS, new OrganizerMapper());
    }

    @Override
    public Organizer getOrganizerById(int id) {
        try {
            final String SELECT_ORGANIZER_BY_ID = "SELECT * FROM organizers WHERE organizerId = ?";
            return jdbc.queryForObject(SELECT_ORGANIZER_BY_ID, new OrganizerMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Organizer addOrganizer(Organizer organizer) {
        final String INSERT_ORGANIZER = "INSERT INTO organizers(firstName, lastName, membership, role, summary) VALUES (?, ?, ?, ?)";
        jdbc.update(INSERT_ORGANIZER, organizer.getFirstName(), organizer.getLastName(), organizer.getMembership(), organizer.getRole(), organizer.getSummary());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID", Integer.class);
        organizer.setOrganizerId(newId);
        return organizer;
    }

    @Override
    public void updateOrganizer(Organizer organizer) {
        final String UPDATE_ORGANIZER = "UPDATE organizers SET firstName = ?, lastName = ?, membership = ?, role = ?, summary = ? WHERE organizerId = ?";
        jdbc.update(UPDATE_ORGANIZER, organizer.getFirstName(), organizer.getLastName(), organizer.getMembership(), organizer.getRole(), organizer.getSummary(), organizer.getOrganizerId());
    }

    @Override
    @Transactional
    public void deleteOrganizerById(int id) {
        final String DELETE_EVENT_BY_ORGANIZER = "DELETE FROM events WHERE organizerId = ?";
        jdbc.update(DELETE_EVENT_BY_ORGANIZER, id);

        final String DELETE_ORGANIZER = "DELETE FROM organizers WHERE organizerId = ?";
        jdbc.update(DELETE_ORGANIZER, id);
    }
}
