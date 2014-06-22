package edu.pragmatic.homework.jdbc.model;

public enum EmployeeGender implements Gender {
	MALE() {
		@Override
		public String getGender() {
			return "man";
		}
	},
	FEMALE() {
		@Override
		public String getGender() {
			return "woman";
		}
	};

	public static Gender with(String genderAbriviation) {
		if (genderAbriviation == null || genderAbriviation.isEmpty())
			return null;

		if ("M".equalsIgnoreCase(genderAbriviation))
			return MALE;
		if ("W".equalsIgnoreCase(genderAbriviation))
			return FEMALE;

		return null;
	}

}