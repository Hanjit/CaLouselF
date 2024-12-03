package model;

import connection.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    private String userId;
    private String username;
    private String password;
    private String phoneNumber;
    private String address;
    private String role;

    public User(String userId, String username, String password, String phoneNumber, String address, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    public void Register(User user) {
        String userId = user.getUserId();
        String username = user.getUsername();
        String password = user.getPassword();
        String phoneNumber = user.getPhoneNumber();
        String address = user.getAddress();
        String role = user.getRole();

        //  SQL Query and register user to db
        Database db = Database.getInstance();
        Connection connection = db.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO MsUser VALUES('%s', '%s', '%s', '%s', '%s', '%s')", userId, username, password, phoneNumber, address, role);
            statement.executeUpdate(query);
            System.out.println("Inserted Successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
