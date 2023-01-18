package me.stitchie.legacypit.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

	private static final String host = "193.135.10.237";
	private static final String port = "3306";
	private static final String database = "db663f2287";
	private static final String username = "db663f2287";
	private static final String password = "svv60nhiyv4uv3ubuzollr6mc6kp17g7";
	private static Connection connection;

	private MySQL() {

	}

	public static void connect() {
		if (!isConnected()) {
			try {
				loadDriver();
				connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?characterEncoding=latin1", username, password);
				System.out.println("[MySQL] Connected to Database");
			} catch (final SQLException throwables) {
				throwables.printStackTrace();
			}
		}
	}

	private static void loadDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void disconnect() {
		if (isConnected()) {
			try {
				connection.close();
				System.out.println("[MySQL] Closed Database Connection");
			} catch (final SQLException throwables) {
				throwables.printStackTrace();
			}
		}
	}

	public static Connection getConnection() {
		return connection;
	}

	public static String getDatabaseName() {
		return database;
	}

	private static boolean isConnected() {
		return connection != null;
	}
}
