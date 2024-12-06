package view;

import controller.UserController;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RegisterPage extends Application {
	
	Scene sc;
	BorderPane bp;
	GridPane gpForm;
	HBox hbButtons;
	
	Label userIdLabel, usernameLabel, passwordLabel, phoneNumberLabel, addressLabel, roleLabel;
	TextField userIdField, usernameField, passwordField, phoneNumberField, addressField, roleField;
	
	Button registerButton, backButton;
	
	private void initialize() {
		bp = new BorderPane();
		sc = new Scene(bp);
		
		gpForm = new GridPane();
		hbButtons = new HBox();
		
		userIdLabel = new Label("ID");
		usernameLabel = new Label("Username");
		passwordLabel = new Label("Password");
		phoneNumberLabel = new Label("Phone Number");
		addressLabel = new Label("Address");
		roleLabel = new  Label("Role");
		
		userIdField = new TextField();
		usernameField = new TextField(); 
		passwordField = new TextField(); 
		phoneNumberField = new TextField(); 
		addressField = new TextField(); 
		roleField = new TextField(); 
		
		registerButton = new Button("Register");
		backButton = new Button("Back");
	}
	
	private void layouting() {
//		gpForm.add(userIdLabel, 0, 0);
		gpForm.add(usernameLabel, 0, 1);
		gpForm.add(passwordLabel, 0, 2);
		gpForm.add(phoneNumberLabel, 0, 3);
		gpForm.add(addressLabel, 0, 4);
		gpForm.add(roleLabel, 0, 5);
		
//		gpForm.add(userIdField, 1, 0);
		gpForm.add(usernameField, 1, 1);
		gpForm.add(passwordField, 1, 2);
		gpForm.add(phoneNumberField, 1, 3);
		gpForm.add(addressField, 1, 4);
		gpForm.add(roleField, 1, 5);
		
		hbButtons.getChildren().add(registerButton);
		hbButtons.getChildren().add(backButton);
		
		bp.setTop(gpForm);
		bp.setBottom(hbButtons);
	}
	
	private void register() {
//		String id = userIdField.getText().toString();
		String username = usernameField.getText().toString();
		String password = passwordField.getText().toString();
		String phoneNumber = phoneNumberField.getText().toString();
		String address = addressField.getText().toString();
		String role = roleField.getText().toString();
		
		boolean result = UserController.getInstance().register(username, password, phoneNumber, address, role);
		if (result) {
			System.out.println("Register Successfull");
		} 
		else {
			System.out.println("Register Failed");
		}
	}
	
	private void setAction() {
		registerButton.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				register();
			}
		});
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		initialize();
		layouting();
		setAction();
		
		primaryStage.setScene(sc);
		primaryStage.show();
	}
	
	
}
