package com.gianmarco.merletti.progetto_ispw.logic.view.javafx;

import com.gianmarco.merletti.progetto_ispw.logic.app.App;
import com.gianmarco.merletti.progetto_ispw.logic.bean.UserBean;
import com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade;
import com.gianmarco.merletti.progetto_ispw.logic.exception.UserNotFoundException;
import com.gianmarco.merletti.progetto_ispw.logic.view.SessionView;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
			new Alert(AlertType.INFORMATION, "First pick a position on the map!", ButtonType.OK).showAndWait();
		}
	}

	@FXML
	private void showSignupForm() {
		App.setRoot("signup");
	}
}
