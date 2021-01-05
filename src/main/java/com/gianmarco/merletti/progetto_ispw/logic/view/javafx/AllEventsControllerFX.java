package com.gianmarco.merletti.progetto_ispw.logic.view.javafx;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

import com.gianmarco.merletti.progetto_ispw.logic.app.App;
import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBean;
import com.gianmarco.merletti.progetto_ispw.logic.bean.EventListElementBean;
import com.gianmarco.merletti.progetto_ispw.logic.bean.RequestBean;
import com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade;
import com.gianmarco.merletti.progetto_ispw.logic.exception.RequestException;
import com.gianmarco.merletti.progetto_ispw.logic.model.Event;
import com.gianmarco.merletti.progetto_ispw.logic.view.SessionView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition.Key;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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

	private EventBean selectedEvent;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		NavbarManager.setNavbar(usernameText, levelLabel);

		ObservableList<EventListElementBean> events = FXCollections.observableArrayList(
				new SystemFacade().getAllEvents());
		FilteredList<EventListElementBean> flEvent = new FilteredList<>(events, p -> true);

		titleCol.setCellValueFactory(new PropertyValueFactory<EventListElementBean, String>("ElemEventTitle"));
		distanceCol.setCellValueFactory(new PropertyValueFactory<EventListElementBean, Integer>("ElemEventDistance"));
		typeCol.setCellValueFactory(new PropertyValueFactory<EventListElementBean, String>("ElemEventType"));
		cityCol.setCellValueFactory(new PropertyValueFactory<EventListElementBean, String>("ElemEventCity"));
		dateCol.setCellValueFactory(new PropertyValueFactory<EventListElementBean, String>("ElemEventDate"));
		ratingCol.setCellValueFactory(new PropertyValueFactory<EventListElementBean, String>("ElemEventRating"));


		eventsContainer.setItems(flEvent);

		searchChoiceBox.getItems().addAll(
				"Title", "City", "Organizer");
		searchChoiceBox.setValue("Title");

		searchTextField.setOnKeyReleased(keyEvent -> {
			switch (searchChoiceBox.getValue()) {
			case "Title":
				flEvent.setPredicate(p -> p.getElemEventTitle().toLowerCase().contains
						(searchTextField.getText().toLowerCase().trim()));
				break;
			case "City":
				flEvent.setPredicate(p -> p.getElemEventCity().toLowerCase().contains
						(searchTextField.getText().toLowerCase().trim()));
				break;
			case "Organizer":
				flEvent.setPredicate(p -> p.getElemEventRating().toLowerCase().contains
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

		eventsContainer.setOnMousePressed(new EventHandler<MouseEvent>() {

			@SuppressWarnings("unchecked")
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					Node node = ((Node) event.getTarget()).getParent();
					TableRow<EventListElementBean> row;
					if (node instanceof TableRow<?>)
						row = (TableRow<EventListElementBean>) node;
					else
						row = (TableRow<EventListElementBean>) node.getParent();

					selectedEvent = new EventBean();
					selectedEvent.setEventId(row.getItem().getElemEventId());
					RequestBean requestBean = new RequestBean();
					requestBean.setRequestEvent(selectedEvent);
					requestBean.setRequestCreationDate(new java.sql.Date(new java.util.Date().getTime()));
					requestBean.setRequestUser(SessionView.getUsername());

					try {
						new SystemFacade().sendRequest(requestBean);
						new Alert(AlertType.CONFIRMATION, "Requested to join send!", ButtonType.OK).showAndWait();
					} catch (RequestException e) {
						new Alert(AlertType.ERROR, "You are already a participant of the event or your request is in pending status!", ButtonType.OK).showAndWait();
					}
				}

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
