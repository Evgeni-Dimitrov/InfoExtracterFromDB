package edu.pragmatic.homework.jdbc.model;

import java.util.Date;

public class ShowAllEmployeesBasicInfo {

	private String name;
	private String lastName;
	private Date hiredOn;
	private Gender gender;
	private String department;

	public ShowAllEmployeesBasicInfo(String name, String lastName,
			Date hiredOn, String gender) {
		this.name = name;
		this.lastName = lastName;
		this.hiredOn = hiredOn;
		this.gender = EmployeeGender.with(gender);
	}

	public ShowAllEmployeesBasicInfo() {
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

	public Date getHiredOn() {
		return hiredOn;
	}

	public void setHiredOn(Date hiredOn) {
		this.hiredOn = hiredOn;
	}

	public String getGender() {
		if (gender == null)
			return "Gender N/A";
		return gender.getGender();
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return String.format("%s %s(%s) hiredOn : %s", getName(),
				getLastName(), getGender(), getHiredOn().toString());
	}
}
