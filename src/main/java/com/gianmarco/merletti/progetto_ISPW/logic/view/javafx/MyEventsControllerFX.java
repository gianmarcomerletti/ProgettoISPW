package com.gianmarco.merletti.progetto_ISPW.logic.view.javafx;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import com.gianmarco.merletti.progetto_ISPW.logic.app.App;
import com.gianmarco.merletti.progetto_ISPW.logic.bean.EventBeanView;
import com.gianmarco.merletti.progetto_ISPW.logic.controller.SystemFacade;
import com.gianmarco.merletti.progetto_ISPW.logic.util.LevelEnum;
import com.gianmarco.merletti.progetto_ISPW.logic.view.SessionView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MyEventsControllerFX implements Initializable {

	@FXML
	private Text usernameText;

	@FXML
	private Label levelLabel;

	@FXML
	private VBox eventsContainer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		NavbarManager.setNavbar(usernameText, levelLabel);

		List<EventBeanView> events = new ArrayList<>();
		events = new SystemFacade().getMyEvents();

		URL url = App.class.getResource("single_event.fxml");
		fillEvents(events, url);
	}

	private void fillEvents(List<EventBeanView> events, URL urlSingleEventFXML) {
		if (events.isEmpty())
			return;

		for (EventBeanView event : events) {
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
						type.setText(event.getEventType());
						break;
					case "levelLabel":
						Label level = (Label) nodeEvent;
						level.setText(event.getEventLevel());
						switch (LevelEnum.valueOf(event.getEventLevel())) {
						case BEGINNER:
							level.setStyle("-fx-background-color: GREEN; -fx-background-radius: 3;");
							break;
						case INTERMEDIATE:
							level.setStyle("-fx-background-color: BLUE; -fx-background-radius: 3;");
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
						break;
					case "usersText":
						Text users = (Text) nodeEvent;
						users.setText("10");
						break;
					case "organizerText":
						Text organizer = (Text) nodeEvent;
						organizer.setText("created by " + event.getEventOrganizer());
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
	private void logout() {
		new SystemFacade().logout();
		App.setRoot("login");
	}

}