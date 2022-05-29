/**
 * 
 */
package com.tcs.umsuser.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author 738566
 *
 */
public class DBUtil {	
	
	private DBUtil() {
	}
	/** The entity manager factory. */
	private static EntityManagerFactory entitymanagerfactory;
	static {
		entitymanagerfactory = Persistence
				.createEntityManagerFactory("UmsUserUpdatePersist");
	}
	public static EntityManager getEntityManager() {
		return entitymanagerfactory.createEntityManager();
	}
	/**
	 * Close entity manager.
	 */
	public static void closeEntityManager(EntityManager entityManager) {
		entityManager.close();
	}

} 