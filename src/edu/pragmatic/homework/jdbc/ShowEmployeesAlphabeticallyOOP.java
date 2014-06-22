package edu.pragmatic.homework.jdbc;

import static edu.pragmatic.homework.jdbc.SQLUtils.getConnection;
import static edu.pragmatic.homework.jdbc.SQLUtils.loadDriver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import edu.pragmatic.homework.jdbc.model.ShowAllEmployeesBasicInfo;

;

public class ShowEmployeesAlphabeticallyOOP {
	public Collection<ShowAllEmployeesBasicInfo> getAllEmployeesBasicInformation()
			throws SQLException {
		loadDriver();
		try (Connection con = getConnection()) {
			Statement basicEmployeeInformation = con.createStatement();
			ResultSet employeesBasicInfo = basicEmployeeInformation
					.executeQuery(SQLUtils.showEmployeesAlphabeticallyQuery());
			return buildModel(employeesBasicInfo);
		}
	}

	private Collection<ShowAllEmployeesBasicInfo> buildModel(ResultSet rawInfo)
			throws SQLException {
		if (rawInfo == null)
			return null;

		Collection<ShowAllEmployeesBasicInfo> basicInfo = new ArrayList<>();
		while (rawInfo.next()) {
			ShowAllEmployeesBasicInfo info = buildEmployeeInfo(rawInfo);
			basicInfo.add(info);
		}

		return basicInfo;
	}

	private ShowAllEmployeesBasicInfo buildEmployeeInfo(ResultSet rawInfo)
			throws SQLException {
		String name = rawInfo.getString("first_name");
		String lastName = rawInfo.getString("last_name");
		Date hiredOn = rawInfo.getDate("hire_date");
		String gender = rawInfo.getString("gender");
		return new ShowAllEmployeesBasicInfo(name, lastName, hiredOn, gender);
	}

	public static void main(String[] args) throws SQLException {
		ShowEmployeesAlphabeticallyOOP command = new ShowEmployeesAlphabeticallyOOP();
		Collection<ShowAllEmployeesBasicInfo> allEmployeesBasicInformation = command
				.getAllEmployeesBasicInformation();
		if (allEmployeesBasicInformation == null
				|| allEmployeesBasicInformation.isEmpty()) {
			System.out
					.println("Unfortunatelly employee information is unavailable");
			return;
		}
		for (ShowAllEmployeesBasicInfo employeeInfo : allEmployeesBasicInformation) {
			System.out.println(employeeInfo);
		}
	}

}