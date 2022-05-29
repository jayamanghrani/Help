/**
 * 
 */
package com.tcs.employeeportal.persist;

import java.sql.Connection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

/**
 * @author 738566
 *
 */
public class DBUtil {

	
	private static final Logger LOGGER = Logger.getLogger("empPortalLogger");
	/** The entity manager factory. */
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY;
	
	private static Connection connection;
	private static final String CONNECTION_TYPE = "JNDI"; //FOR DEPLOYING ON SERVER ONLY
	
	// comment while deploying on local sysytem
	static {
		ENTITY_MANAGER_FACTORY = Persistence
				.createEntityManagerFactory("EmployeePortalPersist");
	}

	/**
	 * Creates the connection.
	 * 
	 * @return the entity manager
	 */
	// public static EntityManager createConnection() {
	// System.err.println("Creating jpa conection");
	// ENTITY_MANAGER_FACTORY =
	// Persistence.createEntityManagerFactory("BaNCSIntegrationPersist");
	// EntityManager entityManager =
	// ENTITY_MANAGER_FACTORY.createEntityManager();
	// return entityManager;
	// }

	/**
	 * Gets the entity manager.
	 * @param applicaitonInvokingName 
	 * 
	 * @return the entity manager
	 */
	/* public static EntityManager getEntityManager() {
	 if (entityManager == null) {
	 entityManager = createConnection();
	 }
	 return entityManager;
	 }
	*/
/*	public static Connection getConnection(String applicaitonInvokingName) { //Connection getConnection(String applicaitonInvokingName)
		if (connection == null) {
			if (CONNECTION_TYPE.equals("JDBC")) {
				if(applicaitonInvokingName.equalsIgnoreCase("HRMS")){
				System.out.println("the jdbc connection");
				connection = createJdbcConnection();
				}
				if(applicaitonInvokingName.equalsIgnoreCase("REPORTS")){
					System.out.println("the jdbc connection");
					connection = createReportsJdbcConnection();
					
				}
			}

			if (CONNECTION_TYPE.equals("JNDI")) {
				connection = createJndiConnection();
				System.out.println("no JNDI connection");
			}
		}

		return connection;
	}*/

	
	public static EntityManager getEntityManager() {
		return ENTITY_MANAGER_FACTORY.createEntityManager();
	}

	/**
	 * Close entity manager.
	 */
	public static void closeEntityManager(EntityManager entityManager) {
		entityManager.close();
	}

	/*private static EntityManager createEntityManagerFactory() {

		try {
			ENTITY_MANAGER_FACTORY = Persistence
					.createEntityManagerFactory("BaNCSIntegrationPersist");
			EntityManager entityManager = ENTITY_MANAGER_FACTORY
					.createEntityManager();
			return entityManager;
		} catch (Exception ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}*/

	/**
	 * Creates the jndi connection.
	 * 
	 * @return the connection
	 */
/*	private static Connection createJndiConnection() {
		Context context = null;
	//	LOGGER.info("Creating JNDI connection");
		Map<String, String> map = new Hashtable<String, String>();
		map.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");
		map.put(Context.PROVIDER_URL,
				PropertiesUtil.getConfigProperty("jndiProviderURL"));

		try {
			context = new InitialContext((Hashtable<String, String>) map);
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup(PropertiesUtil.getConfigProperty("dataSourceName"));
			return dataSource.getConnection();
		} catch (NamingException e) {
			LOGGER.error("Naming exception in JNDI connection : ",e);
		} catch (SQLException e) {
			LOGGER.error("Exception during JNDI connection.",e);
		}

		return null;
	}
	
	private static Connection createJdbcConnection() {
		System.err.println("Creating jdbc connection");
		System.out.println("PropertiesUtil.getConfigData(jdbcHRMSUrl)"+PropertiesUtil.getConfigProperty("jdbcHRMSUrl"));
		System.out.println("PropertiesUtil.getConfigData(jdbcHRMSUserName)"+PropertiesUtil.getConfigProperty("jdbcHRMSUserName"));
		try {
			Class.forName(UtilConstants.DRIVER);
			System.out.println("the driver is"+Class.forName(UtilConstants.DRIVER));
			return DriverManager.getConnection(
					PropertiesUtil.getConfigProperty("jdbcHRMSUrl"),
					PropertiesUtil.getConfigProperty("jdbcHRMSUserName"),
					PropertiesUtil.getConfigProperty("jdbcHRMSPassword"));
		//	return DriverManager.getConnection("jdbc:oracle:thin:@10.90.34.206:1522:NIAHRMS","hrmsuat","tausmrh");
			
		} catch (ClassNotFoundException e) {
			System.out.println("error here 1");
			LOGGER.info(e.getMessage());
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error here 2");
			LOGGER.info(e.getMessage());
			return null;
		}
		
	}
	
	private static Connection createReportsJdbcConnection() {
		// TODO Auto-generated method stub
		System.err.println("Creating jdbc connection");
		System.out.println("PropertiesUtil.getConfigData(jdbcHRMSUrl)"+PropertiesUtil.getConfigProperty("jdbcREPORTSUrl"));
		System.out.println("PropertiesUtil.getConfigData(jdbcHRMSUserName)"+PropertiesUtil.getConfigProperty("jdbcREPORTSUserName"));
		try {
			Class.forName(UtilConstants.DRIVER);
			System.out.println("the driver is"+Class.forName(UtilConstants.DRIVER));
			return DriverManager.getConnection(
					PropertiesUtil.getConfigProperty("jdbcREPORTSUrl"),
					PropertiesUtil.getConfigProperty("jdbcREPORTSUserName"),
					PropertiesUtil.getConfigProperty("jdbcREPORTSPassword"));
			
		} catch (ClassNotFoundException e) {
			System.out.println("error here 1");
			LOGGER.info(e.getMessage());
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error here 2");
			LOGGER.info(e.getMessage());
			return null;
		}
	}*/


} 