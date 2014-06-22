package edu.pragmatic.homework.jdbc.model;

import java.util.Date;

public class ManagerAdd {

	private String name;
	private String lastName;
	private String manager;

	public ManagerAdd(String name, String lastName, String manager) {
		this.name = name;
		this.lastName = lastName;
		this.manager = manager;
	}

	public ManagerAdd() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getManager() {
		return manager;
	}

	public void setDepartment(String department) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s ", getName(), getLastName(),
				getManager());
	}
}
