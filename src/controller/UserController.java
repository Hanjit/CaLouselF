package controller;

import model.User;

public class UserController {

	private static UserController uc = null;
	private User userModel;
	
	public UserController() {
		userModel = new User();
	}
	
	public static UserController getInstance() {
		if (uc == null) {
			uc = new UserController();
		}
		
		return uc;
	}
	
	public User login(String username, String password) {
		User user = userModel.login(username, password);
		
		if (user == null) {
			// Alert failed Credentials
		} else {
			return user;
		}
		
		return user;
	}
	
	public boolean register(String username, String password, String phoneNumber, String address, String role) {
		return userModel.register(username, password, phoneNumber, address, role);
	}
}
