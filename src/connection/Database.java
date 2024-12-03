package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private String host = "localhost";
    private String port = "3306";
    private String dbName = "CaLouselF";
    private String username = "root";
    private String password = "";
    private static Database instance = null;
    private Connection connection;

    private Database() {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;

        try {
            this.connection = DriverManager.getConnection(url, username, password);
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

}
