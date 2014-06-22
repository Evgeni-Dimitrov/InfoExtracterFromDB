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

import edu.pragmatic.homework.jdbc.model.DepartmentAdd;

public class ShowEmployeesNamesandDepartmentOOP {
	public Collection<DepartmentAdd> getAllEmployeesBasicInformation()
			throws SQLException {
		loadDriver();
		try (Connection con = getConnection()) {
			Statement basicEmployeeInformation = con.createStatement();
			ResultSet employeesBasicInfo = basicEmployeeInformation
					.executeQuery(SQLUtils
							.showEmployeesNamesandDepartmentsQuery());
			return buildModel(employeesBasicInfo);
		}
	}

	private Collection<DepartmentAdd> buildModel(ResultSet rawInfo)
			throws SQLException {
		if (rawInfo == null)
			return null;

		Collection<DepartmentAdd> basicInfo = new ArrayList<>();
		while (rawInfo.next()) {
			DepartmentAdd info = buildEmployeeInfo(rawInfo);
			basicInfo.add(info);
		}

		return basicInfo;
	}

	private DepartmentAdd buildEmployeeInfo(ResultSet rawInfo)
			throws SQLException {
		String name = rawInfo.getString("first_name");
		String lastName = rawInfo.getString("last_name");
		String department = rawInfo.getString("dept_name");
		return new DepartmentAdd(name, lastName, department);
	}

	public static void main(String[] args) throws SQLException {
		ShowEmployeesNamesandDepartmentOOP command = new ShowEmployeesNamesandDepartmentOOP();
		Collection<DepartmentAdd> allEmployeesBasicInformation = command
				.getAllEmployeesBasicInformation();
		if (allEmployeesBasicInformation == null
				|| allEmployeesBasicInformation.isEmpty()) {
			System.out
					.println("Unfortunatelly employee information is unavailable");
			return;
		}
		for (DepartmentAdd employeeInfo : allEmployeesBasicInformation) {
			System.out.println(employeeInfo);
		}
	}
}