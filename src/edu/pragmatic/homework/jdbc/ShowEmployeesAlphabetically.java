package edu.pragmatic.homework.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class ShowEmployeesAlphabetically {

	public static void main(String[] args) throws SQLException {
		try (Connection con = SQLUtils.getConnection()) {
			Statement ShowEmployeesAlphabetically = con.createStatement();
			ResultSet rawInfo = ShowEmployeesAlphabetically
					.executeQuery(SQLUtils.showEmployeesAlphabeticallyQuery());
			if (rawInfo != null)
				while (rawInfo.next()) {
					String name = rawInfo.getString("first_name");
					String lastName = rawInfo.getString("last_name");
					Date hiredOn = rawInfo.getDate("hire_date");
					String gender = rawInfo.getString("gender");
					System.out.printf("%s %s (%s) hired on : %s \n", name,
							lastName, gender, hiredOn.toString());
				}

		}
	}

}