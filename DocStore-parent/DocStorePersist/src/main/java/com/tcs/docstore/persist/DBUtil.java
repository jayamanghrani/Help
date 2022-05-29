/**
 * 
 */
package com.tcs.docstore.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author 738566
 *
 */
public class DBUtil {

	/** The entity manager factory. */
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY;
	static {
		ENTITY_MANAGER_FACTORY = Persistence
				.createEntityManagerFactory("DocStorePersist");
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
	 * 
	 * @return the entity manager
	 */
	// public static EntityManager getEntityManager() {
	// if (entityManager == null) {
	// entityManager = createConnection();
	// }
	// return entityManager;
	// }

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

}
