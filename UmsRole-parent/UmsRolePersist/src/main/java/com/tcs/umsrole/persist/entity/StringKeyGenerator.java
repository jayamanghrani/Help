package com.tcs.umsrole.persist.entity;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.tcs.umsrole.persist.DBUtil;

public class StringKeyGenerator implements IdentifierGenerator {

    private static final Logger LOGGER = Logger
            .getLogger(StringKeyGenerator.class);

    @Override
    public Serializable generate(SharedSessionContractImplementor arg0,
            Object arg1) throws HibernateException {
        String currId = null;
        EntityManager entityManager = null;
        try {
            entityManager = DBUtil.getEntityManager();
            String q = "SELECT SEQ_UMS_PROV_LOG_ID.nextval AS upl_log_id FROM dual";

            Query query = entityManager.createNativeQuery(q);
            Object singleResult = query.getSingleResult();
            currId = singleResult.toString();
            
        } catch (Exception e) {
        	LOGGER.error(e.getStackTrace(),e);
        }finally{
        	try{
				if(null!=entityManager){
					entityManager.close();
				}
			}
			catch(Exception e){
				LOGGER.error(e.getStackTrace(),e);
			}
        }
        LOGGER.info("Primark key is :" + currId);
        return currId;
    }

}
