package com.tcs.jira.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {

    private static EntityManagerFactory entityManagerFactory;

    private DBUtil(){
    	
    }
    static {
    	DBUtil.entityManagerFactory = Persistence
                .createEntityManagerFactory("UmsJiraPersist");
    }

    public static EntityManager getEntityManager() {
        return DBUtil.entityManagerFactory.createEntityManager();
    }

    public static void closeEntityManager(EntityManager entityManager) {
        entityManager.close();
    }
}
