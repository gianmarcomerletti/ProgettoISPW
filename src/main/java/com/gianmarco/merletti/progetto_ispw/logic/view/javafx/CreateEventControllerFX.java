package com.gianmarco.merletti.progetto_ispw.logic.view.javafx;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import com.gianmarco.merletti.progetto_ispw.logic.app.App;
import com.gianmarco.merletti.progetto_ispw.logic.bean.AddressBean;
import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBean;
import com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade;
import com.gianmarco.merletti.progetto_ispw.logic.dao.UserDAO;
import com.gianmarco.merletti.progetto_ispw.logic.exception.InvalidFieldException;
import com.gianmarco.merletti.progetto_ispw.logic.util.ConverterUtil;
import com.gianmarco.merletti.progetto_ispw.logic.util.TypeEnum;
import com.gianmarco.merletti.progetto_ispw.logic.view.SessionView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

public class CreateEventControllerFX implements Initializable {

	@FXML
	private TextField eventTitleTextField;

	@FXML
	private TextArea eventDescriptionTextArea;

	@FXML
	private Button submitButton;

	@FXML
	private Button backButton;

	@FXML
	private DatePicker eventDatePicker;

	@FXML
	private TextField eventTimeTextField;

	@FXML
	private ChoiceBox<String> typeChoiceBox;

	@FXML
	private TextField eventDistanceTextField;

	@FXML
	private Text errorText;

	ObservableList<String> typeList = FXCollections.observableArrayList("LENTO", "MEDIO", "VELOCE", "FARTLEK", "RIPETUTE");


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		typeChoiceBox.setItems(typeList);
		typeChoiceBox.setValue(typeList.get(0));
	}

	@FXML
	public void handleSubmitButton() {
		errorText.setVisible(false);

		if (eventTitleTextField.getText() == null || eventDescriptionTextArea.getText() == null
				|| eventDatePicker.getValue() == null || eventTimeTextField.getText() == null
				|| eventDistanceTextField.getText() == null) {
			new Alert(AlertType.INFORMATION, "Invalid field!", ButtonType.OK).showAndWait();
			return;
		}

		EventBean eventBean = new EventBean();
		Date dateTime = new java.sql.Date(new java.util.Date().getTime());
		Double latitude = SessionView.getLatitudeSetOnMap();
		Double longitude = SessionView.getLongitudeSetOnMap();
		AddressBean address = SessionView.getAddressSetOnMap();

		eventBean.setEventTitle(eventTitleTextField.getText());
		eventBean.setEventDescription(eventDescriptionTextArea.getText());
		eventBean.setEventCreationDate(dateTime);
		try {
			eventBean.setEventDate(ConverterUtil.dateFromDatePicker(eventDatePicker));
		} catch (InvalidFieldException e) {
			new Alert(AlertType.WARNING, "Invalid date!", ButtonType.OK).showAndWait();
			return;
		}
		try {
			eventBean.setEventTime(eventTimeTextField.getText());
		} catch (InvalidFieldException e) {
			new Alert(AlertType.WARNING, "Invalid time!", ButtonType.OK).showAndWait();
			return;
		}
		eventBean.setEventLatitude(latitude);
		eventBean.setEventLongitude(longitude);
		eventBean.setEventAddress(address.getRoad());
		eventBean.setEventType(TypeEnum.valueOf(typeChoiceBox.getValue()));
		try {
			eventBean.setEventDistance(Integer.parseInt(eventDistanceTextField.getText()));
		} catch (NumberFormatException e) {
			new Alert(AlertType.WARNING, "Invalid distance field!", ButtonType.OK).showAndWait();
			return;
		}
		eventBean.setEventCity(address.getCity().toUpperCase());
		eventBean.setEventOrganizer(
				new UserDAO().findUserFromUsername(SessionView.getUsername()));


		new SystemFacade().createEvent(eventBean);
		App.setRoot("home_user");
	}

	@FXML
	public void handleBackButton() {
		App.setRoot("home_user");
	}


}
