package com.spam.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.spam.models.User;

@Repository
public class UserDao implements UserDaoIntf {

	@Autowired
	private final JdbcTemplate jdbc_template;
	private SimpleJdbcInsert simpleJdbcInsert;

	public UserDao(JdbcTemplate jdbcTemplate) {
		jdbc_template = jdbcTemplate;
		simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("users")
				.usingGeneratedKeyColumns("userId");
	}


	private static final class UserMapper implements RowMapper<User> {

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


	@Override
	public User addNewUser(User user) {
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("firstName", user.getFirstName())
				.addValue("lastName", user.getLastName()).addValue("isOrganizer", user.isOrganizer())
				.addValue("role", user.getRole()).addValue("summary", user.getSummary());

		// adds the user and returns the value of the auto_increment column
		Number id = simpleJdbcInsert.executeAndReturnKey(params);
		user.setUserId(id.intValue());
		return user; // user object is returned with the generated id
	}


	@Override
	public boolean updateUser(User user) {
		int rows = jdbc_template.update("UPDATE users "
				+ "SET firstName = ?, lastName = ?, isOrganizer = ?, "
				+ "role = ?, summary = ? "
				+ "WHERE userId = ?", 
				user.getFirstName(), user.getLastName(),
				user.isOrganizer(), user.getRole(),
				user.getSummary(), user.getUserId());
	
	return rows != 0 ? true : false;
	}


	@Override
	public boolean delById(int id) {
		int rows = jdbc_template.update("DELETE FROM users WHERE userId = ?", id);
		return rows != 0 ? true : false;
	}


	@Override
	public List<User> getAllUsers() {
		return jdbc_template.query("SELECT * FROM users", new UserMapper());
	}


	@Override
	public User getUserById(int id) {
		try {
			return jdbc_template.queryForObject("SELECT * FROM users WHERE userId = ?", 
					new UserMapper(), id);
			
		} catch (DataAccessException err) {
			return null;
			
		}
		
//		return jdbc_template.queryForObject("SELECT * FROM users WHERE userId = ?", 
//				new UserMapper(), id);
	}

	@Override
	public List<User> getAttendingUsers(int eventId) {
		return jdbc_template.query("SELECT * FROM attending JOIN users ON attending.userId = users.userId WHERE eventId = ?", new UserMapper(), eventId);
	}
}
