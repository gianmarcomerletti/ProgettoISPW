package com.gianmarco.merletti.progetto_ispw;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import org.junit.Test;

import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBean;
import com.gianmarco.merletti.progetto_ispw.logic.bean.RequestBean;
import com.gianmarco.merletti.progetto_ispw.logic.bean.UserBean;
import com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade;
import com.gianmarco.merletti.progetto_ispw.logic.dao.EventDAO;
import com.gianmarco.merletti.progetto_ispw.logic.dao.UserDAO;
import com.gianmarco.merletti.progetto_ispw.logic.exception.InvalidFieldException;
import com.gianmarco.merletti.progetto_ispw.logic.exception.RequestException;
import com.gianmarco.merletti.progetto_ispw.logic.exception.UserNotFoundException;
import com.gianmarco.merletti.progetto_ispw.logic.model.Event;
import com.gianmarco.merletti.progetto_ispw.logic.model.User;
import com.gianmarco.merletti.progetto_ispw.logic.util.CityEnum;
import com.gianmarco.merletti.progetto_ispw.logic.util.LevelEnum;
import com.gianmarco.merletti.progetto_ispw.logic.util.TypeEnum;
import com.gianmarco.merletti.progetto_ispw.logic.view.SessionView;

public class JUnitTest {

	@Test
	public void testCreateEvent() throws InvalidFieldException, ParseException, UserNotFoundException {

		UserBean userBean = new UserBean();
		userBean.setUsername("user");
		userBean.setPassword("pwd");
		userBean.setFirstName("User");
		userBean.setLastName("Test");
		userBean.setLevel(LevelEnum.PRO);
		userBean.setCity(CityEnum.ROMA);

		SystemFacade facade = new SystemFacade();
		facade.signupUser(userBean);

		UserBean user = new UserBean();
		user.setUsername("user");
		user.setPassword("pwd");
		user = new SystemFacade().isSignedUp(user);
		SessionView.setUsername(user.getUsername());

		double f = 41.5;
		EventBean event = new EventBean();
		User organizer = new User();
		organizer.setFromBean(user);
		event.setEventCity("ROMA");
		event.setEventOrganizer(organizer);
		event.setEventAddress("via //");
		event.setEventCreationDate(new java.sql.Date(new java.util.Date().getTime()));
		event.setEventDate(new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse("22/02/2022").getTime()));
		event.setEventDescription("...");
		event.setEventDistance(99);
		event.setEventLatitude(Double.valueOf(f));
		event.setEventLongitude(Double.valueOf(f));
		event.setEventTime("22:22");
		event.setEventTitle("title");
		event.setEventType(TypeEnum.FARTLEK);
		int id = new SystemFacade().createEvent(event);

		int check = new EventDAO().findById(id).getId();
		assertEquals(id, check);
	}

	@Test
	public void testSendRequest() throws InvalidFieldException, ParseException, UserNotFoundException, RequestException {
		SystemFacade facade = new SystemFacade();
		double d = 41.4;
		EventBean event = new EventBean();
		User organizer = new UserDAO().findUserFromUsername("test");
		event.setEventCity("ROMA");
		event.setEventOrganizer(organizer);
		event.setEventAddress("via");
		event.setEventCreationDate(new java.sql.Date(new java.util.Date().getTime()));
		event.setEventDate(new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse("31/01/2031").getTime()));
		event.setEventDescription("...");
		event.setEventDistance(22);
		event.setEventLatitude(Double.valueOf(d));
		event.setEventLongitude(Double.valueOf(d));
		event.setEventTime("20:02");
		event.setEventTitle("title2");
		event.setEventType(TypeEnum.LENTO);
		int eventId = facade.createEvent(event);

		Event temp = new EventDAO().findById(eventId);
		new SystemFacade().setEventForRequest(temp.getLongitude(), temp.getLatitude());

		RequestBean requestBean = new RequestBean();
		requestBean.setRequestCreationDate(new java.sql.Date(new java.util.Date().getTime()));
		requestBean.setRequestMessage("message");
		requestBean.setRequestUser("test2");
		requestBean.setRequestEvent(SessionView.getEventSetOnMap());


		boolean res = new SystemFacade().sendRequest(requestBean);
		assertEquals(res, true);
	}

	@Test
	public void testSignupNewUser() throws UserNotFoundException {

		int leftLimit = 97;		// lettera 'a'
		int rightLimit = 122;	// lettera 'z'
		int lengthLimit = 10;

		String randomUsername = new Random().ints(leftLimit, rightLimit + 1)
				.limit(lengthLimit)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
		String randomPassword = new Random().ints(leftLimit, rightLimit + 1)
				.limit(lengthLimit)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();

		UserBean userBean = new UserBean();
		userBean.setUsername(randomUsername);
		userBean.setPassword(randomPassword);
		userBean.setFirstName("Test");
		userBean.setLastName("JUnit");
		userBean.setCity(CityEnum.ROMA);
		userBean.setLevel(LevelEnum.INTERMEDIATE);
		new SystemFacade().signupUser(userBean);


		UserBean createdUser = new UserBean();
		createdUser.setUsername(randomUsername);
		createdUser.setPassword(randomPassword);
		createdUser = new SystemFacade().isSignedUp(createdUser);
		SessionView.setUsername(randomUsername);
		assertEquals(randomUsername, SessionView.getUsername());
	}

}
