/**
 * 
 */
package com.tcs.docstore.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import com.tcs.docstore.config.utils.UtilProperties;
import com.tcs.docstore.util.UtilConstants;

/**
 * @author 738566
 *
 */
public class DBUtil1 {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(DBUtil.class);

	/** The connection object. */
	private Connection connection;

	/**
	 * Instantiates a new DB util.
	 */
	public DBUtil1() {

	}

	/**
	 * Creates the jndi connection.
	 * 
	 * @return the connection
	 */
	private Connection createJndiConnection() {
		Context context = null;
		//LOGGER.info("Creating JNDI connection");
		/*Map<String, String> map = new Hashtable<String, String>();
		map.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");
		map.put(Context.PROVIDER_URL,
				PropertiesUtil.getConfigProperty("jndiProviderURL"));*/

		try {
			//context = new InitialContext((Hashtable<String, String>) map);
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup(UtilProperties.getDataSourceName());
			return dataSource.getConnection();
		} catch (NamingException e) {
			LOGGER.error("Naming excewption : ",e);
		} catch (SQLException e) {
			LOGGER.error("Exception during JNDI connection.",e);
		}

		return null;
	}

	/**
	 * Creates the jdbc connection.
	 * 
	 * @return the connection
	 */
	private Connection createJdbcConnection() {
	//	LOGGER.info("Creating jdbc connection");
		try {
			Class.forName(UtilConstants.DRIVER);

			return DriverManager.getConnection(
					UtilProperties.getJdbcUrl(),
					UtilProperties.getJdbcUserName(),
					UtilProperties.getJdbcPassword());
		} catch (ClassNotFoundException e) {
			LOGGER.error("Class not found exception", e);
		} catch (SQLException e) {
			LOGGER.error("Exception during JDBC connection.", e);
		}
		return null;
	}

	/**
	 * Gets the connection..
	 * 
	 * @return the connection
	 */
	public Connection getConnection() {
		
			String connectionType = UtilProperties.getConnectionType();
			if ("JDBC".equalsIgnoreCase(connectionType)) {
				return createJdbcConnection();
			}

			if ("JNDI".equalsIgnoreCase(connectionType)) {
				return createJndiConnection();
			}
		

		return connection;
	}

	/**
	 * Close connection.
	 */
	public static void closeConnection(Connection connection) {
		
		if(null != connection){
		try {
			connection.close();
		} catch (SQLException e) {
			LOGGER.error("Exception during closing a connection.", e);
		}
		}
	}

	/**
	 * Close prepared statement.
	 * 
	 * @param preparedStatement
	 *            the prepared statement
	 */
	public static void closePreparedStatement(
			PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				LOGGER.error("Exception during closing prepared statement.", e);
			}
		}
	}

	/**
	 * Close statement.
	 * 
	 * @param statement
	 *            the statement
	 */
	public static void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.error("Exception during closing statement.", e);
			}
		}
	}

	/**
	 * Close result set.
	 * 
	 * @param resultSet
	 *            the result set
	 */
	public static void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LOGGER.error("Exception during closing result set.", e);
			}
		}
	}

}
