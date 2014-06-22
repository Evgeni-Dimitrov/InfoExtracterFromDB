package edu.pragmatic.homework.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class ShowEmployeesNamesandDepartment {

	public static void main(String[] args) throws SQLException {
		try (Connection con = SQLUtils.getConnection()) {
			Statement showEmployeesNamesandDepartments = con.createStatement();
			ResultSet rawInfo = showEmployeesNamesandDepartments
					.executeQuery(SQLUtils
							.showEmployeesNamesandDepartmentsQuery());
			if (rawInfo != null)
				while (rawInfo.next()) {
					String name = rawInfo.getString("first_name");
					String lastName = rawInfo.getString("last_name");
					String department = rawInfo.getString("dept_name");
					System.out
							.printf("%s %s %s \n", name, lastName, department);
				}

		}
	}

}