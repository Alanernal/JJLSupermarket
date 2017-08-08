package net.alily.db;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

/**
 * SqlHelper sql底层操作类
 */
public class SqlHelper {
	private static Logger logger = Logger.getLogger(SqlHelper.class);
	
	// State database content
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private Connection connection = null;

	//State database connection content
	private String DRIVER;
	private String URL;
	private String USERNAME;
	private String PASSWORD;

	private static ResourceBundle bundle;

	public SqlHelper() {
		try {
			bundle = ResourceBundle.getBundle("jdbc");
			DRIVER = bundle.getString("DRIVER");
			URL = bundle.getString("URL");
			USERNAME = bundle.getString("USERNAME");
			PASSWORD = bundle.getString("PASSWORD");
			// Loading driver
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Query operate
	 * @param sql sql语句
	 * @param parameter 参数列表
	 * @return
	 */
	public ResultSet query(String sql, String[] parameter) throws Exception{
		preparedStatement = connection.prepareStatement(sql);
		// Parameter assignment
		for (int i = 0; i < parameter.length; i++) {
			preparedStatement.setString(i + 1, parameter[i]);
		}
		resultSet = preparedStatement.executeQuery();

		return resultSet;
	}


	/**
	 * Query operate
	 * @param sql sql语句
	 * @return ResultSet
	 */
	public ResultSet query(String sql) throws Exception{

		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();

		// Return result set
		return resultSet;
	}


	/**
	 * Increase Delete Revise
	 * @param sql sql语句
	 * @param parameter 参数列表
	 * @return boolean
	 */
	public boolean alteration(String sql, String[] parameter) throws Exception{

		preparedStatement = connection.prepareStatement(sql);
		// Parameter assignment
		for (int i = 0; i < parameter.length; i++) {
			preparedStatement.setString(i + 1, parameter[i]);
		}

		// Judge whether the operation is successful
		if (preparedStatement.executeUpdate() != 1) {
			return false;
		}

		// Return operation successful
		return true;
	}


	/**
	 * Close resource function
	 */
	public void close() {
		try {
			// Judge whether the resource is null
			if (resultSet != null)
				resultSet.close();
			if (preparedStatement != null)
				preparedStatement.close();
			if (connection != null)
				connection.close();

		} catch (Exception e) {
			logger.error("Close be defeated. Exception: " + e);
		}
	}


}
