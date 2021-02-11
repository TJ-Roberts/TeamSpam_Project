package com.spam.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("organizers")
                .usingGeneratedKeyColumns("organizerId");
    }

    /*
     * private static final class OrganizerMapper implements RowMapper<Organizer> {
     *
     * @Override public Organizer mapRow(ResultSet rs, int rowNum) throws
     * SQLException { Organizer newOrganizer = new Organizer();
     * newOrganizer.setOrganizerId(rs.getInt("organizerId"));
     * newOrganizer.setFirstName(rs.getString("firstName"));
     * newOrganizer.setLastName(rs.getString("lastName"));
     * newOrganizer.setMembership(rs.getString("membership"));
     *
     * return newOrganizer; } }
     */

    @Override
    public Organizer addNewOrganizer(Organizer organizer) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("firstName", organizer.getFirstName())
                .addValue("lastName", organizer.getLastName())
                .addValue("membership", organizer.getMembership());

        // adds the organizer and returns the value of the auto_increment column
        Number id = simpleJdbcInsert.executeAndReturnKey(params);
        organizer.setOrganizerId(id.intValue());
        return organizer; // organizer object is returned with the generated id
    }
}