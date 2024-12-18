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
import model.User;
import view.admin.AdminHome;
import view.buyer.HomePage;
import view.seller.SellerHome;

public class LoginPage {
	
	Scene sc;
	BorderPane bp;
	GridPane gpForm;
	HBox hbButtons;
	
	Label titleLabel, usernameLabel, passwordLabel;
	TextField usernameField, passwordField;
	
	Button loginButton, registerButton;
	
	// Initializes UI components and sets up their basic properties
	private void intialize() {
		bp = new BorderPane();
		sc = new Scene(bp, 600, 400);
		
		gpForm = new GridPane();
		gpForm.setHgap(10);
	    gpForm.setVgap(10);
	    gpForm.setPadding(new Insets(20));
	    gpForm.setAlignment(Pos.CENTER);
		
		hbButtons = new HBox(10);
		hbButtons.setAlignment(Pos.CENTER);
		
		titleLabel = new Label("Welcome to CaLouselF!");
		titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); 
		titleLabel.setTextAlignment(TextAlignment.CENTER);
	
		usernameLabel = new Label("Username: ");
		passwordLabel = new Label("Password: ");
		
		usernameField = new TextField();
		passwordField = new TextField();
		usernameField.setPrefWidth(200);
		passwordField.setPrefWidth(200);
		
		loginButton = new Button("Login");
		registerButton = new Button("Register");
		loginButton.setPrefWidth(100);
		registerButton.setPrefWidth(100);
	}
	
	// Configures the layout and positions the UI components in the scene
	private void layouting() {
		gpForm.add(usernameLabel, 0, 0);
		gpForm.add(passwordLabel, 0, 1);
		
		gpForm.add(usernameField, 1, 0);
		gpForm.add(passwordField, 1, 1);
		
		hbButtons.getChildren().add(loginButton);
		hbButtons.getChildren().add(registerButton);
		gpForm.add(hbButtons, 1, 2);
		
		bp.setTop(titleLabel);
		bp.setCenter(gpForm);
		BorderPane.setAlignment(titleLabel, Pos.CENTER); 
		BorderPane.setMargin(gpForm, new Insets(0, 0, 110, 0));
		BorderPane.setMargin(titleLabel, new Insets(110, 0, 0, 0));
	}
	
	// Handles the login process by validating the input and checking the credentials
	private User login() {
		String username = usernameField.getText().toString();
		String password = passwordField.getText().toString();
		
		return UserController.getInstance().login(username, password);
	}
	
//	Adds event listeners to the UI components to handle user interactions
	private void setAction() {
		// Event for clicking the "Login" button
		loginButton.setOnMouseClicked(e -> {
			Main.setUser(login());
			if (Main.getUser() == null) {
				return;
			}
			
			// Role Validation
			if (Main.getUser().getRole().equals("Buyer")) {
				HomePage homePage = new HomePage();
				Scene homeScene = homePage.getScene();
				Main.switchScene(homeScene);				
			} else if (Main.getUser().getRole().equals("Seller")) {
				SellerHome sellerPage = new SellerHome();
				Scene sellerScene = sellerPage.getScene();
				Main.switchScene(sellerScene);
			} else if (Main.getUser().getRole().equals("Admin")) {
				AdminHome adminPage = new AdminHome();
				Scene adminScene = adminPage.getScene();
				Main.switchScene(adminScene);
			}
		});
		
		// Event for clicking the "Register" button
		registerButton.setOnMouseClicked(e -> {
			RegisterPage registerPage = new RegisterPage();
			Scene regisScene = registerPage.getScene();
			Main.switchScene(regisScene);
		});
	}
	
	// Constructor for Login Page (call all the functions)
	public LoginPage() {
		intialize();
		layouting();
		setAction();
	}
	
	//	Returns the scene for this LoginPage
	public Scene getScene() {
		return sc;
	}
}
