package edu.pragmatic.homework.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.lang.*;

public class ShowEmployeesNamesandManagers {

	public static void main(String[] args) throws SQLException {
		try (Connection con = SQLUtils.getConnection()) {
			Statement showEmployeesNamesAndManagers = con.createStatement();
			ResultSet rawInfo = showEmployeesNamesAndManagers
					.executeQuery(SQLUtils.showEmployeesNamesandManagersQuery());
			if (rawInfo != null)
				while (rawInfo.next()) {
					String name = rawInfo.getString("first_name");
					String lastName = rawInfo.getString("last_name");
					String manager = rawInfo.getString("emp_no");
					System.out.printf("%s %s %s  \n", name, lastName, manager);
				}
		}
	}

}