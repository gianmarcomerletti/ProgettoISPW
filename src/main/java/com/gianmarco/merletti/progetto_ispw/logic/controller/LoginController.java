package com.gianmarco.merletti.progetto_ispw.logic.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.gianmarco.merletti.progetto_ispw.logic.bean.UserBean;
import com.gianmarco.merletti.progetto_ispw.logic.dao.ReviewDAO;
import com.gianmarco.merletti.progetto_ispw.logic.dao.UserDAO;
import com.gianmarco.merletti.progetto_ispw.logic.exception.UserNotFoundException;
import com.gianmarco.merletti.progetto_ispw.logic.model.User;
import com.gianmarco.merletti.progetto_ispw.logic.util.CityEnum;
import com.gianmarco.merletti.progetto_ispw.logic.util.LevelEnum;
import com.gianmarco.merletti.progetto_ispw.logic.view.SessionView;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class LoginController {

	private Logger log;

	public LoginController() {
		log = Logger.getLogger("together_run");
	}

	public UserBean checkUser(UserBean user) throws UserNotFoundException {
		UserDAO dao = new UserDAO();
		User checkUser = dao.findUser(user);
		if (checkUser != null) {
			user.setLevel(LevelEnum.valueOf(checkUser.getLevel().toUpperCase()));
			user.setCity(CityEnum.valueOf(checkUser.getCity().toUpperCase()));
			log.log(Level.FINE, "Utente registrato");
		} else {
			log.log(Level.WARNING, "Utente non registrato");
			throw new UserNotFoundException();
		}
		return user;
	}

	public boolean signupUser(UserBean userBean) {

		UserDAO dao = new UserDAO();

		if (dao.userExists(userBean.getUsername())) {
			return false;
		}


		dao.insertUser(userBean);
		log.log(Level.FINE, "Utente inserito");

		return true;

	}

	public void logout() {
		SessionView.setUsername(null);
		SessionView.setCityEnum(null);
		SessionView.setLevelEnum(null);
	}

	public Integer getUserRating(String username) {
		ReviewDAO dao = new ReviewDAO();
		return dao.getUserRating(username);
	}
}
