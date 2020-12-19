package com.gianmarco.merletti.progetto_ispw.logic.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.gianmarco.merletti.progetto_ispw.logic.bean.UserBean;
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

	public UserBean checkUser(UserBean user) throws UserNotFoundException {
		UserDAO dao = new UserDAO();
		User checkUser = dao.findUser(user);
		if (checkUser != null) {
			user.setLevel(LevelEnum.valueOf(checkUser.getLevel().toUpperCase()));
			user.setCity(CityEnum.valueOf(checkUser.getCity().toUpperCase()));
			Logger.getLogger("together_run").log(Level.FINE, "Utente registrato");
		} else {
			Logger.getLogger("together_run").log(Level.WARNING, "Utente non registrato");
			throw new UserNotFoundException();
		}
		return user;
	}

	public boolean signupUser(UserBean userBean) {

		UserDAO dao = new UserDAO();

		if (dao.userExists(userBean.getUsername())) {
			new Alert(AlertType.ERROR, "Utente già presente nel database!", ButtonType.OK).showAndWait();
			return false;
		}


		dao.insertUser(userBean);
		Logger.getLogger("together_run").log(Level.FINE, "Utente inserito");

		return true;

	}

	public void logout() {
		SessionView.setUsername(null);
		SessionView.setCityEnum(null);
		SessionView.setLevelEnum(null);
	}
}
