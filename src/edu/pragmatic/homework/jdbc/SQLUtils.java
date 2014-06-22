package edu.pragmatic.homework.jdbc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public final class SQLUtils {

	private static final String CONFIG_FILE = "resources/config.properties";
	private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	private static final String FILE_PATH_ROOT = "resources/sql";
	public static final String SHOW_ALL_EMPLOYEES = FILE_PATH_ROOT
			+ "/ShowAllEmployees.sql";
	public static final String SHOW_EMPLOYEES_ALPHABETICALLY = FILE_PATH_ROOT
			+ "/ShowEmployeesAlphabetically.sql";
	public static final String SHOW_EMPLOYEES_NAMESand_DEPARTMENTS = FILE_PATH_ROOT
			+ "/ShowEmployeesNamesandDepartments.sql";
	public static final String SHOW_EMPLOYEES_NAMESand_MANAGERS = FILE_PATH_ROOT
			+ "/ShowEmployeesNamesandManagers.sql";
	public static final String SHOW_EMPLOYEES_NAMESand_MANAGERSOOP = FILE_PATH_ROOT
			+ "/ShowEmployeesNamesandManagersOOP.sql";
	private static boolean showLog = false;

	public static void showLog() {
		showLog = true;
	}

	public static void hideLog() {
		showLog = false;
	}

	private SQLUtils() {
	}

	private static Properties config = new Properties();

	static {
		try {
			config.load(new FileReader(new File(CONFIG_FILE)));
		} catch (IOException e) {
			throw new ConfigurationException("Cannot load config file [ "
					+ CONFIG_FILE + "]", e);
		}
	}

	public static String getQuerySQL(String fileName) {
		try (Scanner sqlFile = new Scanner(new File(fileName))) {
			StringBuilder sql = new StringBuilder();
			while (sqlFile.hasNext())
				sql.append(sqlFile.nextLine());
			String actualSQL = sql.toString();
			log(actualSQL);
			return actualSQL;
		} catch (FileNotFoundException e) {
			throw new ConfigurationException("Cannot find sql file @["
					+ fileName + "]", e);
		}
	}

	private static void log(String querySql) {
		if (showLog)
			System.out.printf("Executing %s @ %s \n", querySql, getDBURL());
	}

	public static String showAllEmployeesQuery() {
		return getQuerySQL(SHOW_ALL_EMPLOYEES);
	}

	public static String showEmployeesAlphabeticallyQuery() {
		return getQuerySQL(SHOW_EMPLOYEES_ALPHABETICALLY);
	}

	public static String showEmployeesNamesandDepartmentsQuery() {
		return getQuerySQL(SHOW_EMPLOYEES_NAMESand_DEPARTMENTS);
	}

	public static String showEmployeesNamesandManagersQuery() {
		return getQuerySQL(SHOW_EMPLOYEES_NAMESand_MANAGERS);
	}

	public static String showEmployeesNamesandManagersOOPQuery() {
		return getQuerySQL(SHOW_EMPLOYEES_NAMESand_MANAGERSOOP);
	}

	public static String getDBUser() {
		return config.getProperty("user");
	}

	public static String getPassword() {
		return config.getProperty("password");
	}

	public static String getDBURL() {
		return config.getProperty("url");
	}

	public static void loadDriver() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			throw new ConfigurationException("Cannot load driver["
					+ DRIVER_CLASS + "]", e);
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(getDBURL(), getDBUser(),
					getPassword());
		} catch (SQLException e) {
			throw new ConfigurationException(
					"Cannot establish a connection to [ " + getDBURL() + "]", e);
		}
	}

	public static class ConfigurationException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public ConfigurationException(String errMsg, Throwable cause) {
			super(errMsg, cause);
		}
	}
}
