package com.tcs.umsuser.persist.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.tcs.umsuser.persist.DBUtil;
import com.tcs.umsuser.persist.entities.ProvisionDetails;
import com.tcs.umsuser.persist.query.Queries;
import com.tcs.umsuser.request.asbo.ProvDetailsRequestASBO;
import com.tcs.umsuser.response.asbo.ProvisionDetailsResponseASBO;
import com.tcs.umsuser.util.UtilConstants;


public class ProvisionDetailsDaoImpl implements ProvisionDetailsDao {

    private static final Logger LOGGER = Logger
            .getLogger(ProvisionDetailsDaoImpl.class);

    @Override
    public ProvisionDetailsResponseASBO provisionDetailsUpdate(
            ProvDetailsRequestASBO provDetailsRequestASBO) throws SQLException{
    	EntityManager entityManager =null;
        ProvisionDetailsResponseASBO provisionDetailsResponseASBO = new ProvisionDetailsResponseASBO();
        Long provId = 0L;
        try {
            entityManager = DBUtil.getEntityManager();
            LOGGER.info("Prov Details to be added : "+ provDetailsRequestASBO.toString());
            Date date = new Date();
            entityManager.getTransaction().begin();
            LOGGER.info("transaction begin in ProvisionDetails");
            ProvisionDetails provisionDetails = new ProvisionDetails();
            
            provisionDetails.setProvUserId(provDetailsRequestASBO.getProvUserId());
            provisionDetails.setProvApplicationId(provDetailsRequestASBO.getProvApplicationId());
            provisionDetails.setProvRoleId(provDetailsRequestASBO.getProvRoleId());
            provisionDetails.setProvAction(provDetailsRequestASBO.getProvAction());
            provisionDetails.setProvBranchId(provDetailsRequestASBO.getProvBranchId());
            provisionDetails.setProvStatus("R");
            provisionDetails.setProvCreatedBy(provDetailsRequestASBO.getProvModifiedBy());
            provisionDetails.setProvCreatedDate(date);
            provisionDetails.setProvModifiedBy(provDetailsRequestASBO.getProvModifiedBy());
            provisionDetails.setProvModifiedDate(date);

            entityManager.persist(provisionDetails);
            LOGGER.info("value persisted in ProvisionDetails");
            entityManager.getTransaction().commit();
            provId = provisionDetails.getProvId();
            entityManager.clear();
            entityManager.close();
            provisionDetailsResponseASBO.setProvId(provId);
            provisionDetailsResponseASBO.setResultStatus(UtilConstants.Success_code);
            provisionDetailsResponseASBO.setStatusMessage(UtilConstants.Prov_Success_message);
        
        }finally{
			try{
				if(null!=entityManager){
				entityManager.close();
				}
			}
			catch(Exception e){
				provisionDetailsResponseASBO.setProvId(provId);
	            provisionDetailsResponseASBO.setResultStatus(UtilConstants.Fail_code);
	            provisionDetailsResponseASBO.setStatusMessage(UtilConstants.Prov_Fail_message);
	            LOGGER.error(e.getStackTrace(),e);
			}
		}
        LOGGER.info("Prov ID generated  : -------   " + provId );
        return provisionDetailsResponseASBO;
    }

	@Override
	public List<String> getUserAppId(String userId) throws SQLException{
		List<String> appIdList = new ArrayList<String>();
		EntityManager entityManager=null;
		try{
			entityManager = DBUtil.getEntityManager();
	        LOGGER.info("Inside get User App ID method -------------   "   +       userId);
	        
	        Query query = entityManager.createNativeQuery(Queries.getUserApplicationId);
	        query.setParameter("userId", userId);
	        @SuppressWarnings("unchecked")
			List<Object> resultList = query.getResultList();
	        
	        LOGGER.info("DB response list : "  + resultList.toString());
	        if(!resultList.isEmpty()){
	        	for(Object appId : resultList){
	        		appIdList.add(appId.toString());
	        	}
	        }
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
		LOGGER.info("User App list -------------- : "  + appIdList.toString());
		return appIdList;
	}
}
