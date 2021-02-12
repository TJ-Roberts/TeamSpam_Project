package com.spam;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spam.data.EventDao;
import com.spam.data.UserDao;
import com.spam.models.Event;
import com.spam.models.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class DaoTest {

	@Autowired
	EventDao eventDao;
	
	@Autowired
	UserDao userDao;
	
	@Before
	public void setup() throws Exception {
		// TODO: clean up tables
		List<Event> events = eventDao.getAllEvents();
		for (Event event : events) {
			eventDao.delById(event.getEventId());
		}
		
		List<User> users = userDao.getAllUsers();
		for (User user : users) {
			userDao.delById(user.getUserId());
		}
	}
	
	private User makeUser() {
		User user = new User();
		user.setFirstName("Mike");
		user.setLastName("Zyskind");
		user.setOrganizer(false);
		user.setSummary("this is a test profile");
		
		return user;
	}
	
	private Event makeEvent() {
		Event event = new Event();
		event.setDescription("desc");
		event.setEventDate("2/11/2021");
		event.setEventTime("12:34:56 am");
		event.setEventTitle("title");
		event.setFoodType("Pizza");
		event.setLocation("Library");
		event.setOrganization("Org1");
		
		User user = userDao.addNewUser(makeUser());
		event.setUserId(user.getUserId());
		
		return event;
	}
	
	private Event makeEvent(int creatorId) {
		Event event = new Event();
		event.setDescription("desc");
		event.setEventDate("2/11/2021");
		event.setEventTime("12:34:56 am");
		event.setEventTitle("title");
		event.setFoodType("Pizza");
		event.setLocation("Library");
		event.setOrganization("Org1");
		event.setUserId(creatorId);
		
		return event;
	}
	
	// test userDao
	@Test
	public void testAddUser() {
		int amtUsers = userDao.getAllUsers().size();
		userDao.addNewUser(makeUser());
		
		assertEquals(amtUsers+1, userDao.getAllUsers().size());
	}
	
	@Test
	public void testUpdateUser() {
		User user1 = userDao.addNewUser(makeUser());
		User user2 = userDao.getUserById(user1.getUserId());
		assertEquals(user1, user2);
		
		user1.setFirstName("Test");
		userDao.updateUser(user1);
		assertNotEquals(userDao.getUserById(user1.getUserId()), user2);
	}
	
	@Test
	public void testDelUser() {
		User user = userDao.addNewUser(makeUser());
		assertNotNull(userDao.getUserById(user.getUserId()));
		
		userDao.delById(user.getUserId());
		assertNull(userDao.getUserById(user.getUserId()));
	}
	
	@Test
	public void testGetAllUsers() {
		userDao.addNewUser(makeUser());
		userDao.addNewUser(makeUser());
		assertEquals(userDao.getAllUsers().size(), 2);
	}
	
	// test eventDao
	@Test
	public void testAddEvent() {
		int amtEvents = eventDao.getAllEvents().size();
		eventDao.addNewEvent(makeEvent());
		
		assertEquals(amtEvents+1, eventDao.getAllEvents().size());
	}
	
	@Test
	public void testUpdateEvent() {
		Event ev1 = eventDao.addNewEvent(makeEvent());
		Event ev2 = eventDao.getEventById(ev1.getEventId());
		assertEquals(ev1, ev2);
		
		ev1.setFoodType("Donuts");
		eventDao.updateEvent(ev1);
		assertNotEquals(eventDao.getEventById(ev1.getEventId()), ev2);
	}
	
	@Test
	public void testDelEvent() {
		Event ev = eventDao.addNewEvent(makeEvent());
		assertNotNull(eventDao.getEventById(ev.getEventId()));
		
		eventDao.delById(ev.getEventId());
		assertNull(eventDao.getEventById(ev.getEventId()));
	}
	
	@Test
	public void testGetAllEvents() {
		eventDao.addNewEvent(makeEvent());
		eventDao.addNewEvent(makeEvent());
		assertEquals(eventDao.getAllEvents().size(), 2);
	}
	
	@Test
	public void testGetEventsByCreator() {
		Event ev = eventDao.addNewEvent(makeEvent());
		eventDao.addNewEvent(makeEvent());
		eventDao.addNewEvent(makeEvent(ev.getUserId()));
		
		assertEquals(eventDao.getEventsByCreator(ev.getUserId()).size(), 2);
	}
}
