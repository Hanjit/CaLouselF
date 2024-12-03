package view;

import model.User;
import connection.Database;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    BorderPane bp;
    HBox hb;
    Button button;

    public void initComponents() {
        bp = new BorderPane();
        hb = new HBox();
        button = new Button();
    }

    public void layouting() {
        hb.getChildren().add(button);
        bp.setCenter(hb);
    }

    public void addEvent() {
        button.setOnMouseClicked(e -> {
            User user = new User("1", "Hanjit", "Gatau", "123456789", "Rumahnya hans", "Seller");
            user.Register(user);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        initComponents();
        layouting();
        addEvent();
        Scene scene = new Scene(bp, (double)600.0f, (double)600.0f);
        stage.setScene(scene);
        stage.show();
//        stage.setTitle("Hello, JavaFX!");
//        stage.show();
    }
}