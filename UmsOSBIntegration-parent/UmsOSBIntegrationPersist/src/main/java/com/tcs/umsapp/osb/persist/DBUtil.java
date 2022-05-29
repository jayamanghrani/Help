package com.tcs.umsapp.osb.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
	/** The entity manager factory. */
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY;
		static {
		ENTITY_MANAGER_FACTORY = Persistence
				.createEntityManagerFactory("UmsOSBIntegrationPersist");
	}

	public static EntityManager getEntityManager() {
		return ENTITY_MANAGER_FACTORY.createEntityManager();
	}

	/**
	 * Close entity manager.
	 */
	public static void closeEntityManager(EntityManager entityManager) {
		entityManager.close();
	}
}
