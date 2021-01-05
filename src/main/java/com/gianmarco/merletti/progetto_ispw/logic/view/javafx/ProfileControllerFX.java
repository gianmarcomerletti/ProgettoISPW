package com.gianmarco.merletti.progetto_ispw.logic.view.javafx;

import java.net.URL;
import java.util.ResourceBundle;

import com.gianmarco.merletti.progetto_ispw.logic.app.App;
import com.gianmarco.merletti.progetto_ispw.logic.bean.UserBean;
import com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade;
import com.gianmarco.merletti.progetto_ispw.logic.dao.UserDAO;
import com.gianmarco.merletti.progetto_ispw.logic.model.User;
import com.gianmarco.merletti.progetto_ispw.logic.view.SessionView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ProfileControllerFX implements Initializable {

	@FXML
	private Text usernameText;

	@FXML
	private Label levelLabel;

	@FXML
	private TextField firstNameTextField;

	@FXML
	private TextField lastNameTextField;

	@FXML
	private TextField usernameTextField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private ChoiceBox<String> cityField;

	@FXML
	private ChoiceBox<String> levelField;

	@FXML
	private VBox reviewsContainer;

	ObservableList<String> cityList = FXCollections.observableArrayList("ROMA", "MILANO", "TORINO");
	ObservableList<String> levelList = FXCollections.observableArrayList("BEGINNER", "INTERMEDIATE", "PRO");

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		NavbarManager.setNavbar(usernameText, levelLabel);
		levelField.setItems(levelList);
		cityField.setItems(cityList);

		UserBean currentUser = new SystemFacade().getUserData(SessionView.getUsername());
		firstNameTextField.setText(currentUser.getFirstName());
		lastNameTextField.setText(currentUser.getLastName());
		usernameTextField.setText(currentUser.getUsername());
		passwordField.setText(currentUser.getPassword());
		cityField.setValue(currentUser.getCity().toString());
		levelField.setValue(currentUser.getLevel().toString());

	}

	@FXML
	private void toMap() {
		App.setRoot("home_user");
	}

	@FXML
	private void toAllEvents() {
		App.setRoot("all_events");
	}

	@FXML
	private void toMyRequests() {
		App.setRoot("my_requests");
	}

	@FXML
	private void logout() {
		new SystemFacade().logout();
		App.setRoot("login");
	}



}
