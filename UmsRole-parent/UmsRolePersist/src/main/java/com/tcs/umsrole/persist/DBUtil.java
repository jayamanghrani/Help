package com.tcs.umsrole.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {

    private static EntityManagerFactory entityManagerFactory;

    private DBUtil(){
    	
    }
    static {
    	DBUtil.entityManagerFactory = Persistence
                .createEntityManagerFactory("UmsRolePersist");
    }

    public static EntityManager getEntityManager() {
        return DBUtil.entityManagerFactory.createEntityManager();
    }

    public static void closeEntityManager(EntityManager entityManager) {
        entityManager.close();
    }
}
