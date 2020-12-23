package com.gianmarco.merletti.progetto_ispw.logic.view.javafx;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import com.gianmarco.merletti.progetto_ispw.logic.app.App;
import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBean;
import com.gianmarco.merletti.progetto_ispw.logic.bean.RequestBean;
import com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MyRequestsControllerFX implements Initializable {

	@FXML
	private Text usernameText;

	@FXML
	private Label levelLabel;

	@FXML
	private VBox requestDetailsBox;

	@FXML
	private TreeTableView<RequestBean> requestTable;
	@FXML
	private TreeTableColumn<RequestBean, String> eventColumn;
	@FXML
	private TreeTableColumn<RequestBean, String> userColumn;
	@FXML
	private TreeTableColumn<RequestBean, String> statusColumn;

	@FXML
	private Text dateText;
	@FXML
	private Text addressText;
	@FXML
	private TextArea messageTextArea;

	private RequestBean requestSelected;
	private EventBean requestEvent;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		NavbarManager.setNavbar(usernameText, levelLabel);
		Logger logger = Logger.getLogger("togetherrun");

//		List<RequestBean> requests = new SystemFacade().getMyRequests();

		final TreeItem<RequestBean> root = new TreeItem<>();
		root.setValue(new RequestBean());
		requestTable.setRoot(root);
		requestTable.setShowRoot(false);
		root.setExpanded(true);
/*		requests.stream().forEach(request -> root.getChildren().add(new TreeItem<>(request)));

		requestTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null) {
				requestSelected = newValue.getValue();
				requestEvent = requestSelected.getRequestEvent();
				addressText.setText(requestEvent.getEventAddress() + ", " +
					requestEvent.getEventCity());
				dateText.setText(new SimpleDateFormat("dd/MM/yyyy").format(requestEvent.getEventDate()) + " " +
					requestEvent.getEventTime().toLocalTime().toString());
				messageTextArea.setText(requestSelected.getRequestMessage());
			}
		});
*/
		eventColumn.setCellValueFactory(
				(TreeTableColumn.CellDataFeatures<RequestBean, String> param) -> new SimpleStringProperty(
						param.getValue().getValue().getRequestEvent().getEventTitle()));

		userColumn.setCellValueFactory(
				(TreeTableColumn.CellDataFeatures<RequestBean, String> param) -> new SimpleStringProperty(
						param.getValue().getValue().getRequestUser()));

		statusColumn.setCellValueFactory(
				(TreeTableColumn.CellDataFeatures<RequestBean, String> param) -> new SimpleStringProperty(
						param.getValue().getValue().getRequestStatus()));
	}

	@FXML
	public void acceptRequest() {

	}

	@FXML
	public void rejectRequest() {

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
	private void logout() {
		new SystemFacade().logout();
		App.setRoot("login");
	}

}
