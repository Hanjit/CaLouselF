package view;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        new HomePage().start(stage);
    	new RegisterPage().start(stage);
    	
//        stage.setTitle("Hello, JavaFX!");
//        stage.show();
    }
}