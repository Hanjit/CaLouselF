package model;

import connection.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    private int userId;
    private String username;
    private String password;
    private String phoneNumber;
    private String address;
    private String role;
    
    public User() {}

    public User(int userId, String username, String password, String phoneNumber, String address, String role) {
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
				int userId = rs.getInt("User_id");
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
    
    public boolean register(String username, String password, String phoneNumber, String address, String role) {
    	String query = "INSERT INTO `msuser` VALUES(?, ?, ?, ?, ?)";
    	PreparedStatement ps = Database.getInstance().prepareStatement(query);
    	
    	try {
    		ps.setString(1, username);
    		ps.setString(2, password);
    		ps.setString(3, phoneNumber);
    		ps.setString(4, address);
    		ps.setString(5, role);
    		return ps.executeUpdate() == 1;
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return false;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId2) {
        this.userId = userId2;
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
