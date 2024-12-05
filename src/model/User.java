package model;

import connection.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    private String userId;
    private String username;
    private String password;
    private String phoneNumber;
    private String address;
    private String role;
    
    public User() {}

    public User(String userId, String username, String password, String phoneNumber, String address, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }
    
    public User login(String username, String password) {
    	User user = new User();
    	String query = String.format("SELECT *"
    			+ "FROM msuser"
    			+ "WHERE Username LIKE '%s'"
    			+ "AND Password LIKE '%s'", username, password);
    	ResultSet rs = Database.getInstance().execQuery(query);
    	
    	if (rs == null) {
    		// Alert login failed
    		return null;
    	} else {
    		try {
				String userId = rs.getString("User_id");
				String phoneNumber = rs.getString("Phone_number");
				String address = rs.getString("Address");
				String role = rs.getString("Role");
				user.setUserId(userId);
				user.setUsername(username);
				user.setPassword(password);
				user.setPhoneNumber(phoneNumber);
				user.setAddress(address);
				user.setRole(role);
				return user;
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	return null;
    }
    
    public boolean register(String userId, String username, String password, String phoneNumber, String address, String role) {
    	String query = "INSERT INTO `msuser` VALUES(?, ?, ?, ?, ?, ?)";
    	PreparedStatement ps = Database.getInstance().prepareStatement(query);
    	
    	try {
    		ps.setString(1, userId);
    		ps.setString(2, username);
    		ps.setString(3, password);
    		ps.setString(4, phoneNumber);
    		ps.setString(5, address);
    		ps.setString(6, role);
    		return ps.executeUpdate() == 1;
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return false;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
