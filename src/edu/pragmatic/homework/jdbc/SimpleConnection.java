package edu.pragmatic.homework.jdbc;

import static edu.pragmatic.homework.jdbc.SQLUtils.getDBURL;
import static edu.pragmatic.homework.jdbc.SQLUtils.getDBUser;
import static edu.pragmatic.homework.jdbc.SQLUtils.getPassword;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleConnection {

	private static final String sql = "select distinct title as awesomeName from employees.titles";

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException, FileNotFoundException, IOException {
		Class.forName("com.mysql.jdbc.Driver");
		try (Connection connection = DriverManager.getConnection(getDBURL(),
				getDBUser(), getPassword())) {
			Statement statement = connection.createStatement();
			ResultSet statementResult = statement.executeQuery(sql);
			while (statementResult.next()) {
				String title = statementResult.getString("awesomeName");
				System.out.println(title);
			}
		}
	}

}
