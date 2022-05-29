package com.tcs.umsapp.unlock.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {

    /** The entity manager factory. */
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY;

    // comment while deploying on local sysytem
    static {
        ENTITY_MANAGER_FACTORY = Persistence
                .createEntityManagerFactory("UmsUnlockUserPersist");
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
