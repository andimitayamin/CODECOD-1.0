package com.codecod.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {
	private static MySQLConnection INSTANCE;
	private String host;
	private String port;
	private String schema;
	private String username;
	private String password;
	private Connection connection;
	
	private MySQLConnection() {
		super();
		host = "localhost";
		port = "3306";
		schema = "codecod";
		username = "root";
		password = "";
	}

    private void callDriver() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        String driver ="com.mysql.jdbc.Driver";
        Class.forName(driver).newInstance();
    }
	
	private void createConnection() throws SQLException {
        try {
			callDriver();
	        String url = String.format("jdbc:mysql://%s:%s/%s", host, port, schema);
			connection = DriverManager.getConnection(url, username, password);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new SQLException(e);
		}
	}

    public ResultSet executeTake(String query) throws SQLException {
		if (connection == null)
			createConnection();
		Statement statement = connection.createStatement();
		return statement.executeQuery(query);
    }

    public void executeStore(String query) throws SQLException {
		if (connection == null)
			createConnection();
		PreparedStatement statement = connection.prepareStatement(query);
		statement.clearParameters();
		statement.executeUpdate();
		statement.close();
    }

	public static MySQLConnection getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MySQLConnection();
		}
		return INSTANCE;
	}
}
