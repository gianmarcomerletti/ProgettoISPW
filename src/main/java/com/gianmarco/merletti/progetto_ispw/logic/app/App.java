package com.gianmarco.merletti.progetto_ispw.logic.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	private static Stage stage;

	@Override
	public void start(Stage primaryStage) {
		App.setStage(primaryStage);
		stage.setScene(new Scene(loadFXML("login"), 800, 600));
		stage.show();

	}

	private static void setStage(Stage stage) {
		App.stage = stage;
	}

	public static void setRoot(String fxml) {
		Parent newRoot = loadFXML(fxml);
		stage.getScene().setRoot(newRoot);
	}

	private static Parent loadFXML(String fxml) {
		FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		try {
			return loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
