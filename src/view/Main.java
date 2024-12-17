package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

public class Main extends Application {
	private static User user;
	private static Stage primaryStage;

    public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		Main.user = user;
	}
	
	public static void switchScene(Scene scene) {
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
    	Main.primaryStage = stage;
    	LoginPage loginPage = new LoginPage();
    	Scene loginScene = loginPage.getScene();
    	switchScene(loginScene);
    }
}