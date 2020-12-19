package com.gianmarco.merletti.progetto_ISPW.logic.controller;

import com.gianmarco.merletti.progetto_ISPW.logic.bean.UserBean;
import com.gianmarco.merletti.progetto_ISPW.logic.dao.UserDAO;
import com.gianmarco.merletti.progetto_ISPW.logic.exception.UserNotFoundException;
import com.gianmarco.merletti.progetto_ISPW.logic.model.User;
import com.gianmarco.merletti.progetto_ISPW.logic.util.CityEnum;
import com.gianmarco.merletti.progetto_ISPW.logic.util.LevelEnum;
import com.gianmarco.merletti.progetto_ISPW.logic.view.SessionView;

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
			System.out.println("USER REGISTRED!");
		} else {
			System.out.println("USER NOT REGISTRED!");
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
		System.out.println("NEW USER INSERTED! (" + userBean.getUsername() +")");

		return true;

	}

	public void logout() {
		SessionView.setUsername(null);
		SessionView.setCityEnum(null);
		SessionView.setLevelEnum(null);
	}
}
