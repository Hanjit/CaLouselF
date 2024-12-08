package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Item;
import model.User;

public class UserController {

	private static UserController uc = null;
	private User userModel;
	private Alert alert = new Alert(AlertType.NONE);
	
	public UserController() {
		userModel = new User();
	}
	
	public static UserController getInstance() {
		if (uc == null) {
			uc = new UserController();
		}
		
		return uc;
	}
	
	private void errorAlert(String message) {
		alert.setAlertType(AlertType.ERROR);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	public User login(String username, String password) {
		User user = userModel.login(username, password);
		
		if (user == null) {
			errorAlert("User Not Found!");
		} else {
			return user;
		}
		
		return user;
	}
	
	public String validate(User user) {
		return user.getRole();
	}
	
	public boolean register(String username, String password, String phoneNumber, String address, String role) {
		return userModel.register(username, password, phoneNumber, address, role);
	}
}
