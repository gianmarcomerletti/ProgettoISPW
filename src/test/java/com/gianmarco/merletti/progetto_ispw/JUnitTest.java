package com.gianmarco.merletti.progetto_ispw;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import com.gianmarco.merletti.progetto_ispw.logic.bean.UserBean;
import com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade;
import com.gianmarco.merletti.progetto_ispw.logic.exception.UserNotFoundException;
import com.gianmarco.merletti.progetto_ispw.logic.util.CityEnum;
import com.gianmarco.merletti.progetto_ispw.logic.util.LevelEnum;
import com.gianmarco.merletti.progetto_ispw.logic.view.SessionView;

public class JUnitTest {

	int leftLimit = 97;		// lettera 'a'
	int rightLimit = 122;	// lettera 'z'
	int lengthLimit = 10;

	@Test
	public void testSignuoNewUser() throws UserNotFoundException {

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
