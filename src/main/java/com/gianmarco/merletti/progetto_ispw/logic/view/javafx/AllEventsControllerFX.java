package com.gianmarco.merletti.progetto_ispw.logic.view.javafx;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

import com.gianmarco.merletti.progetto_ispw.logic.app.App;
import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBean;
import com.gianmarco.merletti.progetto_ispw.logic.bean.EventListElementBean;
import com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition.Key;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private ChoiceBox<String> searchChoiceBox;

	@FXML
	private TextField searchTextField;

	@FXML
	private TableView<EventListElementBean> eventsContainer;
	@FXML
	private TableColumn<EventListElementBean, String> titleCol;
	@FXML
	private TableColumn<EventListElementBean, Integer> distanceCol;
	@FXML
	private TableColumn<EventListElementBean, String> typeCol;
	@FXML
	private TableColumn<EventListElementBean, String> cityCol;
	@FXML
	private TableColumn<EventListElementBean, String> dateCol;
	@FXML
	private TableColumn<EventListElementBean, String> ratingCol;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		NavbarManager.setNavbar(usernameText, levelLabel);

		ObservableList<EventListElementBean> events = FXCollections.observableArrayList(
				new SystemFacade().getAllEvents());
		FilteredList<EventListElementBean> flEvent = new FilteredList<>(events, p -> true);
		eventsContainer.setItems(flEvent);

		titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
		distanceCol.setCellValueFactory(new PropertyValueFactory<>("KM"));
		typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
		cityCol.setCellValueFactory(new PropertyValueFactory<>("City"));
		dateCol.setCellValueFactory(new PropertyValueFactory<>("Date"));
		ratingCol.setCellValueFactory(new PropertyValueFactory<>("Rating"));

		searchChoiceBox.getItems().addAll(
				"Title", "City", "Organizer");

		searchTextField.setOnKeyReleased(keyEvent -> {
			switch (searchChoiceBox.getValue()) {
			case "Title":
				flEvent.setPredicate(p -> p.getEventTitle().toLowerCase().contains
						(searchTextField.getText().toLowerCase().trim()));
				break;
			case "City":
				flEvent.setPredicate(p -> p.getEventCity().toLowerCase().contains
						(searchTextField.getText().toLowerCase().trim()));
				break;
			case "Organizer":
				flEvent.setPredicate(p -> p.getEventRating().toLowerCase().contains
						(searchTextField.getText().toLowerCase().trim()));
				break;
			}
		});

		searchChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal != null) {
				searchTextField.setText("");
				flEvent.setPredicate(null);
			}
		});

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
