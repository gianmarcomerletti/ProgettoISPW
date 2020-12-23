package com.gianmarco.merletti.progetto_ispw.logic.view.javafx;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import com.gianmarco.merletti.progetto_ispw.logic.app.App;
import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBean;
import com.gianmarco.merletti.progetto_ispw.logic.bean.RequestBean;
import com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade;
import com.gianmarco.merletti.progetto_ispw.logic.view.SessionView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class SendRequestControllerFX implements Initializable {

	@FXML
	private Text titleText;

	@FXML
	private Label levelLabel;

	@FXML
	private TextArea descriptionTextArea;

	@FXML
	private Text addressText;

	@FXML
	private Text dateText;

	@FXML
	private Text timeText;

	@FXML
	private Text distanceText;

	@FXML
	private Text typeText;

	@FXML
	private Text organizerText;

	@FXML
	private Button contactButton;

	private EventBean selectedEvent;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selectedEvent = SessionView.getEventSetOnMap();
		titleText.setText(selectedEvent.getEventTitle());
		levelLabel.setText(selectedEvent.getEventLevel().toString());
		descriptionTextArea.setText(selectedEvent.getEventDescription());
		addressText.setText(selectedEvent.getEventAddress());
		dateText.setText(selectedEvent.getEventDate().toLocalDate().toString());
		timeText.setText(selectedEvent.getEventTime().toLocalTime().toString());
		distanceText.setText(selectedEvent.getEventDistance().toString() + " KM");
		typeText.setText(selectedEvent.getEventType().toString());
		organizerText.setText(selectedEvent.getEventOrganizer().getUsername());
	}

	@FXML
	public void handleCancelButton() {
		App.setRoot("home_user");
	}

	@FXML
	public void handleSendButton() {
		RequestBean requestBean = new RequestBean();
		requestBean.setRequestEvent(selectedEvent);
		requestBean.setRequestCreationDate(new java.sql.Date(new java.util.Date().getTime()));
		requestBean.setRequestUser(SessionView.getUsername());

		new SystemFacade().sendRequest(requestBean);
		App.setRoot("home_user");
	}

}
