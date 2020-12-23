package com.gianmarco.merletti.progetto_ispw.logic.view.javafx;

import java.net.URL;
import java.util.ResourceBundle;

import com.gianmarco.merletti.progetto_ispw.logic.app.App;
import com.gianmarco.merletti.progetto_ispw.logic.bean.RequestBean;
import com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

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
