package edu.pragmatic.homework.jdbc;

import static edu.pragmatic.homework.jdbc.SQLUtils.getConnection;
import static edu.pragmatic.homework.jdbc.SQLUtils.loadDriver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import edu.pragmatic.homework.jdbc.model.ManagerAdd;

public class ShowEmployeesNamesandManagersOOP {
	public Collection<ManagerAdd> getAllEmployeesBasicInformation()
			throws SQLException {
		loadDriver();
		try (Connection con = getConnection()) {
			Statement basicEmployeeInformation = con.createStatement();
			ResultSet employeesBasicInfo = basicEmployeeInformation
					.executeQuery(SQLUtils
							.showEmployeesNamesandManagersOOPQuery());
			return buildModel(employeesBasicInfo);
		}
	}

	private Collection<ManagerAdd> buildModel(ResultSet rawInfo)
			throws SQLException {
		if (rawInfo == null)
			return null;

		Collection<ManagerAdd> basicInfo = new ArrayList<>();
		while (rawInfo.next()) {
			ManagerAdd info = buildEmployeeInfo(rawInfo);
			basicInfo.add(info);
		}

		return basicInfo;
	}

	private ManagerAdd buildEmployeeInfo(ResultSet rawInfo) throws SQLException {
		String name = rawInfo.getString("first_name");
		String lastName = rawInfo.getString("last_name");
		String manager = rawInfo.getString("emp_no");
		return new ManagerAdd(name, lastName, manager);
	}

	public static void main(String[] args) throws SQLException {
		ShowEmployeesNamesandManagersOOP command = new ShowEmployeesNamesandManagersOOP();
		Collection<ManagerAdd> allEmployeesBasicInformation = command
				.getAllEmployeesBasicInformation();
		if (allEmployeesBasicInformation == null
				|| allEmployeesBasicInformation.isEmpty()) {
			System.out
					.println("Unfortunatelly employee information is unavailable");
			return;
		}
		for (ManagerAdd employeeInfo : allEmployeesBasicInformation) {
			System.out.println(employeeInfo);
		}
	}
}