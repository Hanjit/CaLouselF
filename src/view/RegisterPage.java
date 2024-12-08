package view;

import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class RegisterPage {
	
	Scene sc;
	BorderPane bp;
	GridPane gpForm;
	HBox hbButtons;
	
	Label titleLabel, usernameLabel, passwordLabel, phoneNumberLabel, addressLabel, roleLabel;
	TextField usernameField, passwordField, phoneNumberField, addressField, roleField;
	
	Button registerButton, backButton;
	
	private void initialize() {
		bp = new BorderPane();
		sc = new Scene(bp, 600, 400);
		
		gpForm = new GridPane();
		gpForm.setHgap(10);
	    gpForm.setVgap(10);
	    gpForm.setPadding(new Insets(20));
	    gpForm.setAlignment(Pos.CENTER);
		
	    hbButtons = new HBox(10);
		hbButtons.setAlignment(Pos.CENTER);
		
		titleLabel = new Label("Create a New Account");
		titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); 
		titleLabel.setTextAlignment(TextAlignment.CENTER); 
		
		usernameLabel = new Label("Username: ");
		passwordLabel = new Label("Password: ");
		phoneNumberLabel = new Label("Phone Number: ");
		addressLabel = new Label("Address: ");
		roleLabel = new  Label("Role: ");
		
		usernameField = new TextField(); 
		passwordField = new TextField(); 
		phoneNumberField = new TextField(); 
		addressField = new TextField(); 
		roleField = new TextField(); 
		usernameField.setPrefWidth(200);
		passwordField.setPrefWidth(200);
		phoneNumberField.setPrefWidth(200);
		addressField.setPrefWidth(200);
		roleField.setPrefWidth(200);
		
		registerButton = new Button("Register");
		backButton = new Button("Back");
		registerButton.setPrefWidth(100);
		backButton.setPrefWidth(100);
	}
	
	private void layouting() {
//		gpForm.add(titleLabel, 0, 0);
		gpForm.add(usernameLabel, 0, 0);
		gpForm.add(passwordLabel, 0, 1);
		gpForm.add(phoneNumberLabel, 0, 2);
		gpForm.add(addressLabel, 0, 3);
		gpForm.add(roleLabel, 0, 4);
		
		gpForm.add(usernameField, 1, 0);
		gpForm.add(passwordField, 1, 1);
		gpForm.add(phoneNumberField, 1, 2);
		gpForm.add(addressField, 1, 3);
		gpForm.add(roleField, 1, 4);
		
		hbButtons.getChildren().add(registerButton);
		hbButtons.getChildren().add(backButton);
		gpForm.add(hbButtons, 1, 5);
		
		bp.setTop(titleLabel);
		bp.setCenter(gpForm);
//		bp.setBottom(hbButtons);
		BorderPane.setAlignment(titleLabel, Pos.CENTER);
		BorderPane.setMargin(gpForm, new Insets(0, 0, 80, 0));
		BorderPane.setMargin(titleLabel, new Insets(80, 0, 0, 0));
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
		registerButton.setOnMouseClicked(e -> {
			register();
		});
		
		backButton.setOnMouseClicked(e -> {
			LoginPage loginPage = new LoginPage();
			Scene loginScene = loginPage.getScene();
			Main.switchScene(loginScene);
		});
	}
	
	public RegisterPage() {
		initialize();
		layouting();
		setAction();
	}
	
	public Scene getScene() {
		return sc;
	}
	
}
