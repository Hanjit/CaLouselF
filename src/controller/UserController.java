package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.User;

public class UserController {

	private static UserController uc = null;
	private User userModel;
	private Alert alert = new Alert(AlertType.NONE);
	
	public UserController() {
		userModel = new User();
	}
	
	// Retrieves the singleton instance of UserController
	public static UserController getInstance() {
		if (uc == null) {
			uc = new UserController();
		}
		
		return uc;
	}
	
	// Displays an error alert with the provided message
	private void errorAlert(String message) {
		alert.setAlertType(AlertType.ERROR);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	// Handles the login functionality by validating the user credentials
	public User login(String username, String password) {
		User user = userModel.login(username, password);
		
		// Validation
		if (username.isEmpty()) {
			errorAlert("User cannot be empty!");
		} else if (password.isEmpty()) {
			errorAlert("Password cannot be empty!");
		} else if (user == null) {
			errorAlert("Invalid credentials!");
		}
		
		return user;
	}
	
	// Retrieves the role of the user for further validation or authorization
	public String validate(User user) {
		return user.getRole();
	}
	
	// Handles the user registration process by validating all input fields
	public boolean register(String username, String password, String phoneNumber, String address, String role) {
		// validate username
		if (username.isEmpty()) {
			errorAlert("Username cannot be empty!");
			return false;
		} else if (username.length() < 3) {
			errorAlert("Username must be at least 3 characters long!");
			return false;
		} else if (!checkUsername(username)) {
			errorAlert("Username must be unique!");
			return false;
		}
		
		// validate password
		if (password.isEmpty()) {
			errorAlert("Password cannot be empty!");
			return false;
		} else if (password.length() < 8) {
			errorAlert("Password must at least be 8 characters long!");
			return false;
		} else if (!password.matches(".*[!@#$%^&*].*")) {
			errorAlert("Password must include special character!");
			return false;
		}
		
		// validate phone number
		if (!phoneNumber.startsWith("+62")) {
			errorAlert("Phone number must start with +62!");
			return false;
		} else if (phoneNumber.length() < 12) {
			errorAlert("Phone number must at least be 10 numbers long!");
			return false;
		} else if (!phoneNumber.substring(3).matches("\\d+")) {
			errorAlert("Phone number must be a number!");
			return false;
		}
		
		// validate address
		if (address.isEmpty()) {
			errorAlert("Address cannot be empty!");
			return false;
		}
		
		// validate role
		if (role == null) {
			errorAlert("Role must be selected!");
			return false;
		}
		
		return userModel.register(username, password, phoneNumber, address, role);
	}
	
	// Checks if the username already exists
	private boolean checkUsername(String username) {
		return userModel.getUserByUsername(username);
	}
}
