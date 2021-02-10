package com.spam.data;

import com.spam.models.Organizer;

import java.util.List;

public interface OrganizerDao {
    List<Organizer> getAllOrganizers();
    Organizer getOrganizerById(int id);
    Organizer addOrganizer(Organizer organizer);
    void updateOrganizer(Organizer organizer);
    void deleteOrganizerById(int id);
}
