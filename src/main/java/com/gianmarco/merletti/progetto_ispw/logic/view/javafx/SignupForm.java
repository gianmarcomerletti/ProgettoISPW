package com.gianmarco.merletti.progetto_ispw.logic.view.javafx;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import com.gianmarco.merletti.progetto_ispw.logic.app.App;
import com.gianmarco.merletti.progetto_ispw.logic.bean.UserBean;
import com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade;
import com.gianmarco.merletti.progetto_ispw.logic.util.CityEnum;
import com.gianmarco.merletti.progetto_ispw.logic.util.LevelEnum;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignupForm implements Initializable{
	@FXML
	private Button signupButton;

	@FXML
	private TextField firstNameField;

	@FXML
	private TextField lastNameField;

	@FXML
	private TextField userNameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private ChoiceBox<String> cityChoiceBox;

	@FXML
	private ChoiceBox<String> levelChoiceBox;

	ObservableList<String> cityList = FXCollections.observableArrayList("Roma", "Milano", "Torino");
	ObservableList<String> levelList = FXCollections.observableArrayList("Beginner (up to 06:00)", "Intermediate (06:00/04:30)", "PRO (below 04:30)");


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		levelChoiceBox.setItems(levelList);
		levelChoiceBox.setValue(levelList.get(0));
		cityChoiceBox.setItems(cityList);
		cityChoiceBox.setValue(cityList.get(0));
	}

	@FXML
	private void signupUser() {
		UserBean newUser = new UserBean();
		newUser.setUsername(userNameField.getText());
		newUser.setPassword(passwordField.getText());
		newUser.setFirstName(firstNameField.getText());
		newUser.setLastName(lastNameField.getText());
		newUser.setCity(CityEnum.valueOf(cityChoiceBox.getValue().toUpperCase()));
		newUser.setLevel(LevelEnum.valueOf(levelChoiceBox.getValue().split(" ")[0].toUpperCase()));
		if (new SystemFacade().signupUser(newUser)) {
			new LoginControllerFX().loginUser(newUser.getUsername(), newUser.getPassword());
		}
	}

	@FXML
	private void clearValues() {
		firstNameField.clear();
		lastNameField.clear();
		userNameField.clear();
		passwordField.clear();
	}

	@FXML
	public void backToLogin() {
		App.setRoot("login");
	}

}
