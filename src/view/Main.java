package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

public class Main extends Application {
	// Static fields to hold the current user and primary stage
	private static User user;
	private static Stage primaryStage;
	
	// Setter-Getter for the currently logged-in user
    public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		Main.user = user;
	}
	
	// Switches the current scene displayed on the primary stage
	public static void switchScene(Scene scene) {
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// Main method to launch the JavaFX application
	public static void main(String[] args) {
        launch(args);
    }
	
	// Starts the JavaFX application and initializes the primary stage
    @Override
    public void start(Stage stage) throws Exception {
    	Main.primaryStage = stage;
    	LoginPage loginPage = new LoginPage();
    	Scene loginScene = loginPage.getScene();
    	switchScene(loginScene);
    }
}