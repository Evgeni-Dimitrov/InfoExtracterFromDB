package edu.pragmatic.homework.jdbc.model;

import java.util.Date;

public class DepartmentAdd {

	private String name;
	private String lastName;
	private String department;

	public DepartmentAdd(String name, String lastName, String department) {
		this.name = name;
		this.lastName = lastName;
		this.department = department;
	}

	public DepartmentAdd() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s ", getName(), getDepartment(),
				getLastName());
	}
}
