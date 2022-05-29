package com.tcs.umsrole.persist.dao;

import java.util.Date;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.tcs.umsrole.persist.DBUtil;
import com.tcs.umsrole.persist.entity.ProvisionDetails;
import com.tcs.umsrole.request.ProvDetailsRequestASBO;
import com.tcs.umsrole.response.ProvisionDetailsResponseASBO;

public class ProvisionDetailsDaoImpl implements ProvisionDetailsDao {

    private static final Logger LOGGER = Logger
            .getLogger(ProvisionDetailsDaoImpl.class);

    @Override
    public ProvisionDetailsResponseASBO provisionDetailsUpdate(
            ProvDetailsRequestASBO provDetailsRequestASBO) {
    	EntityManager entityManager = DBUtil.getEntityManager();
        ProvisionDetailsResponseASBO asbo = new ProvisionDetailsResponseASBO();
        Long provId = 0L;
        try {
            
            LOGGER.info("EntityManager created in ProvisionDetails");
            Date date = new Date();
            entityManager.getTransaction().begin();
            LOGGER.info("transaction begin in ProvisionDetails");
            ProvisionDetails provisionDetails = new ProvisionDetails();
            provisionDetails.setProvAction(provDetailsRequestASBO
                    .getProvAction());
            provisionDetails.setProvApplicationId(provDetailsRequestASBO
                    .getProvApplicationId());
            provisionDetails.setProvBranchId(provDetailsRequestASBO
                    .getProvBranchId());
            provisionDetails.setProvCreatedBy(provDetailsRequestASBO
                    .getProvCreatedBy());
            provisionDetails.setProvCreatedDate(date);
            provisionDetails.setProvDate(date);
            provisionDetails.setProvModifiedBy(provDetailsRequestASBO
                    .getProvModifiedBy());
            provisionDetails.setProvModifiedDate(provDetailsRequestASBO.getProvModifiedDate());
            provisionDetails.setProvRequestId(provDetailsRequestASBO
                    .getProvRequestId().toString());
            provisionDetails.setProvRoleId(provDetailsRequestASBO
                    .getProvRoleId());
            provisionDetails.setProvStatus(provDetailsRequestASBO
                    .getProvStatus());
            provisionDetails.setProvUserId(provDetailsRequestASBO
                    .getProvUserId());
            entityManager.persist(provisionDetails);
            LOGGER.info("value persist in ProvisionDetails");
            entityManager.getTransaction().commit();
            LOGGER.info("transaction commit in ProvisionDetails");
            provId = provisionDetails.getProvId();
            entityManager.clear();
            entityManager.close();
            asbo.setProvId(provId);
            asbo.setResultStatus("S");
            asbo.setStatusMessage("Provesion Details updated success fully");
        } catch (Exception ex) {
            LOGGER.info("Error" + ex);
            asbo.setResultStatus("F");
            asbo.setStatusMessage("Provesion Details Failed");
            LOGGER.error(ex.getStackTrace(),ex);
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
        
        return asbo;
    }
}
