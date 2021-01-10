package com.gianmarco.merletti.progetto_ispw.logic.view.javafx;

import java.io.File;

import org.controlsfx.control.Rating;

import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBean;
import com.gianmarco.merletti.progetto_ispw.logic.bean.ReviewBean;
import com.gianmarco.merletti.progetto_ispw.logic.util.ConverterUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

public class SendReviewControllerFX {

	private MyEventsControllerFX controller;
	private EventBean currEvent;

	@FXML
	private GridPane reviewGridPane;

	@FXML
	private Label titleEventLabel;

	@FXML
	private TextArea textField;

	@FXML
	private Rating rating;

	@FXML
	private ImageView imgView;

	private byte[] selectedImage;

	public void setControllerAndEvent(MyEventsControllerFX controller, EventBean eventBean) {
		this.controller = controller;
		this.currEvent = eventBean;
		this.titleEventLabel.setText(currEvent.getEventTitle());
	}

	@FXML
	private void cancel() {
		reviewGridPane.getScene().getWindow().hide();
	}

	@FXML
	private void send() {
		if (textField.getText().isEmpty()) {
			new Alert(AlertType.INFORMATION, "Missing text review!", ButtonType.OK).showAndWait();
			return;
		}
		ReviewBean reviewBean = new ReviewBean();
		reviewBean.setTextBean(textField.getText());
		reviewBean.setImageBean(selectedImage);
		reviewBean.setEventBean(currEvent);
		reviewBean.setValueBean(rating.ratingProperty().intValue());

		controller.sendReview(reviewBean);
		reviewGridPane.getScene().getWindow().hide();
	}

	@FXML
	private void handlePictureUpload() {
		Window window = reviewGridPane.getScene().getWindow();
		FileChooser imageFileChooser = new FileChooser();
		imageFileChooser.getExtensionFilters()
				.add((new ExtensionFilter("JPG files (*.jpg, *.jpeg)", "*.JPG", "*.jpg", "*.JPEG", "*.jpeg")));
		File imageFile = imageFileChooser.showOpenDialog(window);

		if (imageFile != null) {
			Image selImage = new Image(imageFile.toURI().toString());

			imgView.setImage(selImage);
			selectedImage = ConverterUtil.byteArrayFromImage(imageFile);

		}

	}
}
