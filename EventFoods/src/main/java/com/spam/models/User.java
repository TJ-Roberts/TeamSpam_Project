package com.spam.models;

import java.util.Objects;

public class User {

	private int userId;
	
	private String firstName;
	private String lastName;
	
	private boolean isOrganizer;
	
	// optional fields
	private String role;
	private String summary;
	
	public User() {
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public boolean isOrganizer() {
		return isOrganizer;
	}

	public void setOrganizer(boolean isOrganizer) {
		this.isOrganizer = isOrganizer;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		
		if (!(obj instanceof User))
			return false;
		
		User tmp = (User)obj;
		return tmp.getUserId() == userId &&
				tmp.getFirstName().equals(firstName) &&
				tmp.getLastName().equals(lastName) &&
				tmp.isOrganizer == isOrganizer &&
				(tmp.getRole() == null || tmp.getRole().equals(role)) &&
				(tmp.getSummary() == null || tmp.getSummary().equals(summary));
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, firstName, lastName, isOrganizer, role, summary);
	}
}
