package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Connect to database
public class Database {

    private String host = "localhost";
    private String port = "3306";
    private String dbName = "CaLouselF";
    private String username = "root";
    private String password = "";
    private static Database instance = null;
    private Connection connection;
    public Statement stmt;
    public ResultSet rs;
    public PreparedStatement ps;

    private Database() {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;

        try {
            this.connection = DriverManager.getConnection(url, username, password);
            stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {return this.connection;}

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    
    public ResultSet execQuery(String query) {
    	try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return rs;
    }

    public PreparedStatement prepareStatement(String query) {
    	try {
			ps = connection.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return ps;
    }   
}
