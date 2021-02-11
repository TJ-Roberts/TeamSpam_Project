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

import com.spam.models.Organizer;

@Repository
public class OrganizerDaoDB implements OrganizerDao {

    @Autowired
    private final JdbcTemplate jdbc_template;
    private SimpleJdbcInsert simpleJdbcInsert;

    public OrganizerDaoDB(JdbcTemplate jdbcTemplate) {
        jdbc_template = jdbcTemplate;
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("organizers")
                .usingGeneratedKeyColumns("organizerId");
    }


    private static final class OrganizerMapper implements RowMapper<Organizer> {

        @Override
        public Organizer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Organizer newOrganizer = new Organizer();
            newOrganizer.setOrganizerId(rs.getInt("organizerId"));
            newOrganizer.setFirstName(rs.getString("firstName"));
            newOrganizer.setLastName(rs.getString("lastName"));
            newOrganizer.setMembership(rs.getString("membership"));
            newOrganizer.setRole(rs.getString("role"));
            newOrganizer.setSummary(rs.getString("summary"));

            return newOrganizer;
        }
    }


    @Override
    public Organizer addNewOrganizer(Organizer organizer) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("firstName", organizer.getFirstName())
                .addValue("lastName", organizer.getLastName()).addValue("membership", organizer.getMembership())
                .addValue("role", organizer.getRole()).addValue("summary", organizer.getSummary());

        // adds the organizer and returns the value of the auto_increment column
        Number id = simpleJdbcInsert.executeAndReturnKey(params);
        organizer.setOrganizerId(id.intValue());
        return organizer; // organizer object is returned with the generated id
    }


    @Override
    public boolean updateOrganizer(Organizer organizer) {
        int rows = jdbc_template.update("UPDATE organizers "
                        + "SET firstName = ?, lastName = ?, membership = ?, "
                        + "role = ?, summary = ? "
                        + "WHERE organizerId = ?",
                organizer.getFirstName(), organizer.getLastName(),
                organizer.getMembership(), organizer.getRole(),
                organizer.getSummary(), organizer.getOrganizerId());

        return rows != 0 ? true : false;
    }


    @Override
    public boolean delById(int id) {
        int rows = jdbc_template.update("DELETE FROM organizers WHERE organizerId = ?", id);
        return rows != 0 ? true : false;
    }


    @Override
    public List<Organizer> getAllOrganizers() {
        return jdbc_template.query("SELECT * FROM organizers", new OrganizerMapper());
    }
}