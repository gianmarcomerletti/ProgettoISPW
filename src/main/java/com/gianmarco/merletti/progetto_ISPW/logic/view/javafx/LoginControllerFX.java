package com.gianmarco.merletti.progetto_ISPW.logic.view.javafx;

import com.gianmarco.merletti.progetto_ISPW.logic.app.App;
import com.gianmarco.merletti.progetto_ISPW.logic.bean.UserBean;
import com.gianmarco.merletti.progetto_ISPW.logic.controller.SystemFacade;
import com.gianmarco.merletti.progetto_ISPW.logic.exception.UserNotFoundException;
import com.gianmarco.merletti.progetto_ISPW.logic.view.SessionView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginControllerFX {
	@FXML
	private TextField usernameField;

	@FXML
	private TextField passwordField;

	@FXML
	private Button loginButton;

	@FXML
	private void submitLogin() {
		loginUser(usernameField.getText(), passwordField.getText());
	}

	public void loginUser(String username, String password) {
		UserBean userBean = new UserBean();
		userBean.setUsername(username);
		userBean.setPassword(password);
		UserBean response = new UserBean();

		try {
			response = new SystemFacade().isSignedUp(userBean);

			SessionView.setUsername(response.getUsername());
			SessionView.setLevelEnum(response.getLevel());
			SessionView.setCityEnum(response.getCity());
			App.setRoot("home_user");

		} catch (UserNotFoundException e) {
		}
	}

	@FXML
	private void showSignupForm() {
		App.setRoot("signup");
	}
}
