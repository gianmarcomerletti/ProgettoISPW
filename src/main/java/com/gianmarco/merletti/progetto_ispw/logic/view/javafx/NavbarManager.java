package com.gianmarco.merletti.progetto_ispw.logic.view.javafx;

import com.gianmarco.merletti.progetto_ispw.logic.view.SessionView;

import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class NavbarManager {

	private NavbarManager() {
	}

	public static void setNavbar(Text usernameText, Label levelLabel) {
		usernameText.setText(SessionView.getUsername());
		levelLabel.setText(SessionView.getLevelEnum().toString());
		switch (SessionView.getLevelEnum()) {
		case BEGINNER:
			levelLabel.setStyle("-fx-background-color: GREEN; -fx-background-radius: 3;");
			break;
		case INTERMEDIATE:
			levelLabel.setStyle("-fx-background-color: #0080ff; -fx-background-radius: 3;");
			break;
		case PRO:
			levelLabel.setStyle("-fx-background-color: RED; -fx-background-radius: 3;");
			break;
		default:
			break;
		}

	}
}
