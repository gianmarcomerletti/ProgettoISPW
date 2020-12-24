package com.gianmarco.merletti.progetto_ispw.logic.view.javafx;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.gianmarco.merletti.progetto_ispw.logic.app.App;
import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBean;
import com.gianmarco.merletti.progetto_ispw.logic.bean.RequestBean;
import com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade;
import com.gianmarco.merletti.progetto_ispw.logic.view.SessionView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SendRequestControllerFX implements Initializable {

	@FXML
	private GridPane gridInfo;

	@FXML
	private GridPane gridMessage;

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

	@FXML
	private TextArea messageTextArea;

	@FXML
	private Button addButton;

	private EventBean selectedEvent;
	private RequestBean requestBean;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		requestBean = new RequestBean();
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

		gridInfo.setVisible(true);
		gridMessage.setVisible(false);

	}

	@FXML
	public void handleCancelButton() {
		App.setRoot("home_user");
	}

	@FXML
	public void handleSendButton() {
		requestBean.setRequestEvent(selectedEvent);
		requestBean.setRequestCreationDate(new java.sql.Date(new java.util.Date().getTime()));
		requestBean.setRequestUser(SessionView.getUsername());

		new SystemFacade().sendRequest(requestBean);
		App.setRoot("home_user");
	}

	@FXML
	public void handleAddMessageButton() {
		gridMessage.setVisible(true);
		gridInfo.setVisible(false);

		if (requestBean.getRequestMessage() != null)
			messageTextArea.setText(requestBean.getRequestMessage());
	}

	@FXML
	public void handleAddButton() {
		requestBean.setRequestMessage(messageTextArea.getText());
		gridMessage.setVisible(false);
		gridInfo.setVisible(true);
		contactButton.setText("Edit message...");
	}

}
