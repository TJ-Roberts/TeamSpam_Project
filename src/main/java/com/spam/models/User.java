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

    public boolean isOrganizer() {
        return isOrganizer;
    }

    public void setOrganizer(boolean organizer) {
        isOrganizer = organizer;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && isOrganizer == user.isOrganizer && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(role, user.role) && Objects.equals(summary, user.summary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, isOrganizer, role, summary);
    }
}