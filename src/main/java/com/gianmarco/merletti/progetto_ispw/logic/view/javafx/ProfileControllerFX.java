package com.gianmarco.merletti.progetto_ispw.logic.view.javafx;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.gianmarco.merletti.progetto_ispw.logic.app.App;
import com.gianmarco.merletti.progetto_ispw.logic.bean.ReviewBean;
import com.gianmarco.merletti.progetto_ispw.logic.bean.UserBean;
import com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade;
import com.gianmarco.merletti.progetto_ispw.logic.view.SessionView;
import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ProfileControllerFX implements Initializable {

	@FXML
	private Text usernameText;

	@FXML
	private Label levelLabel;

	@FXML
	private TextField firstNameTextField;

	@FXML
	private TextField lastNameTextField;

	@FXML
	private TextField usernameTextField;

	@FXML
	private TextField cityField;

	@FXML
	private TextField levelField;

	@FXML
	private VBox reviewsContainer;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		NavbarManager.setNavbar(usernameText, levelLabel);

		UserBean currentUser = new SystemFacade().getUserData(SessionView.getUsername());
		firstNameTextField.setText(currentUser.getFirstName());
		lastNameTextField.setText(currentUser.getLastName());
		usernameTextField.setText(currentUser.getUsername());
		cityField.setText(currentUser.getCity().toString());
		levelField.setText(currentUser.getLevel().toString());

		URL urlSingleReviewFXML = App.class.getResource("single_profile_review.fxml");
		List<ReviewBean> reviews = new SystemFacade().getMyReviews();
		fillReviews(reviews, urlSingleReviewFXML);

	}

	private void fillReviews(List<ReviewBean> reviews, URL urlSingleReviewFXML) {

		if (reviews.isEmpty())
			return;

		for (ReviewBean review : reviews) {
			AnchorPane singleEvent;
			try {
				HBox parent;
				parent = FXMLLoader.load(urlSingleReviewFXML);
				singleEvent = (AnchorPane) parent.getChildren().get(0);
				for (Node nodeEvent : singleEvent.getChildren()) {
					switch (nodeEvent.getId()) {
					case "imageReview":
						ImageView image = (ImageView) nodeEvent;
						image.setImage(new Image(new ByteArrayInputStream(review.getImageBean())));
						break;
					case "textReviewEvent":
						Text textReview = (Text) nodeEvent;
						textReview.setText(review.getEventBean().getEventTitle());
						break;
					case "messageText":
						JFXTextArea message = (JFXTextArea) nodeEvent;
						message.setText(review.getTextBean());
						break;
					case "userText":
						Text user = (Text) nodeEvent;
						user.setText(review.getUserBean().getUsername());
						break;
					case "ratingValue":
						org.controlsfx.control.Rating rating = (org.controlsfx.control.Rating) nodeEvent;
						rating.setRating(review.getValueBean());
						rating.setMouseTransparent(true);
						break;
					default:
						continue;
					}
				}
				reviewsContainer.getChildren().add(singleEvent);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	private void toAllEvents() {
		App.setRoot("all_events");
	}

	@FXML
	private void toMyRequests() {
		App.setRoot("my_requests");
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
