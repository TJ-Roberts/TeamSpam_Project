package com.spam.models;

import java.util.List;
import java.util.Objects;

public class Event {

    private int eventId;
    private User user;
    private List<User> attendees;

    private String location;
    private String eventTime;
    private String eventDate;
    private String eventTitle;

    // optional fields
    private String foodType;
    private String description;
    private String organization;

    public Event () {

    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<User> attendees) {
        this.attendees = attendees;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return eventId == event.eventId && Objects.equals(user, event.user) && Objects.equals(attendees, event.attendees) && Objects.equals(location, event.location) && Objects.equals(eventTime, event.eventTime) && Objects.equals(eventDate, event.eventDate) && Objects.equals(eventTitle, event.eventTitle) && Objects.equals(foodType, event.foodType) && Objects.equals(description, event.description) && Objects.equals(organization, event.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, user, attendees, location, eventTime, eventDate, eventTitle, foodType, description, organization);
    }
}