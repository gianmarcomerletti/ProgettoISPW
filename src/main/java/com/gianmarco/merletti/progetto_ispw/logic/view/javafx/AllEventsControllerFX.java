package com.gianmarco.merletti.progetto_ispw.logic.view.javafx;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

import com.gianmarco.merletti.progetto_ispw.logic.app.App;
import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBean;
import com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AllEventsControllerFX implements Initializable {

	@FXML
	private Text usernameText;

	@FXML
	private Label levelLabel;

	@FXML
	private VBox eventsContainer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		NavbarManager.setNavbar(usernameText, levelLabel);


		List<EventBean> events = new SystemFacade().getAllEvents();
		fillEvents(events);

	}

	private void fillEvents(List<EventBean> events) {

		URL urlSingleEventFXML = App.class.getResource("single_event.fxml");

		if (events.isEmpty())
			return;

		for (EventBean event : events) {
			AnchorPane singleEvent;
			try {
				HBox parent;
				parent = FXMLLoader.load(urlSingleEventFXML);
				singleEvent = (AnchorPane) parent.getChildren().get(0);
				for (Node nodeEvent : singleEvent.getChildren()) {
					switch (nodeEvent.getId()) {
					case "distanceText":
						Text distance = (Text) nodeEvent;
						distance.setText(event.getEventDistance().toString() + " KM");
						break;
					case "typeText":
						Text type = (Text) nodeEvent;
						type.setText(event.getEventType().toString());
						break;
					case "levelLabel":
						Label level = (Label) nodeEvent;
						level.setText(event.getEventLevel().toString());
						switch (event.getEventLevel()) {
						case BEGINNER:
							level.setStyle("-fx-background-color: GREEN; -fx-background-radius: 3;");
							break;
						case INTERMEDIATE:
							level.setStyle("-fx-background-color: #0080ff; -fx-background-radius: 3;");
							break;
						case PRO:
							level.setStyle("-fx-background-color: RED; -fx-background-radius: 3;");
							break;
						default:
							break;
						}
						break;
					case "titleText":
						Text title = (Text) nodeEvent;
						title.setText(event.getEventTitle());
						break;
					case "descriptionTextArea":
						JFXTextArea description = (JFXTextArea) nodeEvent;
						description.setText(event.getEventDescription());
						break;
					case "addressText":
						Text address = (Text) nodeEvent;
						address.setText(event.getEventAddress() + ", " + event.getEventCity());
						break;
					case "dateText":
						Text date = (Text) nodeEvent;
						String dateFormatted = new SimpleDateFormat("dd/MM/yyyy").format(event.getEventDate());
						String timeFormatted = new SimpleDateFormat("HH:mm").format(event.getEventTime());
						date.setText(dateFormatted + " - " + timeFormatted);
						break;
					case "cancelButton":
						JFXButton cancelBtn = (JFXButton) nodeEvent;
						cancelBtn.setOnAction(value -> App.setRoot("home_user"));
						break;
					case "rateButton":
						JFXButton rateBtn = (JFXButton) nodeEvent;
						rateBtn.setOnAction(value -> App.setRoot("home_user"));
						break;
					case "usersText":
						Text users = (Text) nodeEvent;
						users.setText(new SystemFacade().getEventParticipants(event).toString());
						break;
					case "organizerText":
						Text organizer = (Text) nodeEvent;
						organizer.setText("created by " + event.getEventOrganizer().getUsername());
						break;

					default:
						continue;
					}
				}
				eventsContainer.getChildren().add(singleEvent);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	@FXML
	private void toMap() {
		App.setRoot("home_user");
	}

	@FXML
	private void toMyEvents() {
		App.setRoot("my_events");
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
