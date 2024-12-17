package model;

import connection.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
    	String query = String.format("SELECT * "
    			+ "FROM msuser "
    			+ "WHERE BINARY Username LIKE '%s' "
    			+ "AND Password LIKE '%s'", username, password);
    	ResultSet rs = Database.getInstance().execQuery(query);
 
    	
    	try {
			if (rs.next()) {
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public boolean register(String username, String password, String phoneNumber, String address, String role) {
    	String query = "INSERT INTO `msuser` (`Username`, `Password`, `Phone_number`, `Address`, `Role`) VALUES(?, ?, ?, ?, ?)";
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
    
    public boolean getUserByUsername(String username) {
    	String query = "SELECT COUNT(*) FROM msuser WHERE Username = ?";
    	PreparedStatement ps = Database.getInstance().prepareStatement(query);
    	
    	try {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) == 0;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return false;
    }
    
    // Get declined offers for alert
    public ArrayList<Offer> getDeclinedOffers(String userId) {
    	ArrayList<Offer> offers = new ArrayList<>();
    	String query = String.format("SELECT `Offer_id`, `Offer_status`, `Offer_reason` "
    			+ "FROM `MsOffer` "
    			+ "WHERE `User_id` = %d AND "
    			+ "`Offer_status` LIKE '%s'", userId, "Declined");
    	ResultSet rs = Database.getInstance().execQuery(query);
    	
    	try {
			while (rs.next()) {
				int id = rs.getInt("Offer_id");
				String reason = rs.getString("Offer_reason");
				offers.add(new Offer(id, reason));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return offers;
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
