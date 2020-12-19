package com.gianmarco.merletti.progetto_ispw.logic.view.javafx;

import java.net.URL;
import java.util.EventListener;
import java.util.Optional;
import java.util.ResourceBundle;

import com.gianmarco.merletti.progetto_ispw.logic.app.App;
import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBean;
import com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade;
import com.gianmarco.merletti.progetto_ispw.logic.view.SessionView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;

public class MapBoundary implements Initializable {
	@FXML
	private WebView mapWebView;

	@FXML
	private Text usernameTextHome;

	@FXML
	private Label levelLabelHome;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		NavbarManager.setNavbar(usernameTextHome, levelLabelHome);

		// TODO aggiungere lista degli eventi

		MapController mapController = new MapController(mapWebView);
		mapController.loadMap();
	}

	@FXML
	private void createEvent() {
		if (SessionView.getAddressSetOnMap() != null)
			App.setRoot("create_event");
		else {
			new Alert(AlertType.INFORMATION, "First pick a position on the map!", ButtonType.OK).showAndWait();
		}
	}

	@FXML
	private void createRequest() {
		if (SessionView.getEventSetOnMap() != null)
			App.setRoot("sendRequestForm");
		else {
			new Alert(AlertType.INFORMATION, "First select an event on the map!", ButtonType.OK).showAndWait();
		}
	}

	@FXML
	private void toMyEvents() {
		App.setRoot("my_events");
	}

	@FXML
	private void logout() {
		new SystemFacade().logout();
		App.setRoot("login");
	}
}
