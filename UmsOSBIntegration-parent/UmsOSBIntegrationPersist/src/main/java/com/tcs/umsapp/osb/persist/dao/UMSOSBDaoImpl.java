package com.tcs.umsapp.osb.persist.dao;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.tcs.umsapp.osb.common.ProvisionDetail;
import com.tcs.umsapp.osb.persist.DBUtil;
import com.tcs.umsapp.osb.persist.entities.ProvDetailEntity;
import com.tcs.umsapp.osb.persist.queries.Queries;
import com.tcs.umsapp.osb.persist.services.UMSOSBDao;

public class UMSOSBDaoImpl implements UMSOSBDao {

    private static final Logger LOGGER = Logger.getLogger(UMSOSBDaoImpl.class);

    @Override
    public ConcurrentHashMap<Number, ProvisionDetail> getProvisionDetails() {
        ConcurrentHashMap<Number, ProvisionDetail> provisionMapList = new ConcurrentHashMap<Number, ProvisionDetail>();

        EntityManager entityManager = null;
        try {
            entityManager = DBUtil.getEntityManager();
            String pendingRoleQuery = Queries.ProvisionList;

            LOGGER.info(" Generated Query " + pendingRoleQuery);

            Query query = entityManager.createNativeQuery(pendingRoleQuery);

            @SuppressWarnings("unchecked")
            List<Object[]> obList = query.getResultList();

            if (!obList.isEmpty()) {
            	provisionMapList.putAll(updateValuesInMap(obList));
                LOGGER.info("Data Length for Add and Update: " + provisionMapList.size());
            }
            LOGGER.info("Now checking for DU list : ------------ ");
            
            query = entityManager.createNativeQuery(Queries.ProvisionListForDeleteUser);
            @SuppressWarnings("unchecked")
            List<Object[]> deleteUserList = query.getResultList();
            if (!deleteUserList.isEmpty()) {
            	provisionMapList.putAll(updateValuesInMap(deleteUserList));
                
            }

            LOGGER.info("Data Length for All changes: " + provisionMapList.size());
            
        } catch (Exception Ex) {
            LOGGER.info("Exception in getProvisionDetails" + Ex.getMessage());
            Ex.printStackTrace();
        }

        return provisionMapList;

    }

    private ConcurrentHashMap<Number, ProvisionDetail> updateValuesInMap(List<Object[]> obList) {
    	ConcurrentHashMap<Number, ProvisionDetail> tempList = new ConcurrentHashMap<Number, ProvisionDetail>();
    	
    	for (Object[] ob : obList) {
    		ProvisionDetail provisionDetail = new ProvisionDetail();

            provisionDetail.setProvisionId(((ob[0] == null) ? null : (ob[0].toString().equalsIgnoreCase(" ")) ? " " : ob[0].toString()));
			
            provisionDetail.setApplicationId(((ob[1] == null) ? null : (ob[1].toString().equalsIgnoreCase(" ")) ? " " : ob[1].toString()));
            provisionDetail.setRoleCode(((ob[2] == null) ? null : (ob[2].toString().equalsIgnoreCase(" ")) ? " " : ob[2].toString()));
            provisionDetail
                    .setProvisionAction(((ob[3] == null) ? null : (ob[3].toString().equalsIgnoreCase(" ")) ? " " : ob[3].toString()));
            provisionDetail.setModifiedBy(((ob[4] == null) ? null : (ob[4].toString().equalsIgnoreCase(" ")) ? " " : ob[4].toString()));
            provisionDetail.setModifiedDate(((ob[5] == null) ? null : (ob[5].toString().equalsIgnoreCase(" ")) ? " " : ob[5].toString()));
            provisionDetail.setUserId(((ob[6] == null) ? null : (ob[6].toString().equalsIgnoreCase(" ")) ? " " : ob[6].toString()));
            provisionDetail.setFirstName(((ob[7] == null) ? null : (ob[7].toString().equalsIgnoreCase(" ")) ? " " : ob[7].toString()));
            provisionDetail.setMiddleName(((ob[8] == null) ? null : (ob[8].toString().equalsIgnoreCase(" ")) ? " " : ob[8].toString()));
            provisionDetail.setLastName(((ob[9] == null) ? null : (ob[9].toString().equalsIgnoreCase(" ")) ? " " : ob[9].toString()));
            provisionDetail.setBranchId(((ob[10] == null) ? null : (ob[10].toString().equalsIgnoreCase(" ")) ? "" : ob[10].toString()));
            provisionDetail.setGender(((ob[11] == null) ? null : (ob[11].toString().equalsIgnoreCase(" ")) ? " " : ob[11].toString()));
            provisionDetail.setDob(((ob[12] == null) ? null : (ob[12].toString().equalsIgnoreCase(" ")) ? " " : ob[12].toString()));
            provisionDetail.setAddr1(((ob[13] == null) ? null : (ob[13].toString().equalsIgnoreCase(" ")) ? " " : ob[13].toString()));
            provisionDetail.setAddr2(((ob[14] == null) ? null : (ob[14].toString().equalsIgnoreCase(" ")) ? " " : ob[14].toString()));
            provisionDetail.setAddr3(((ob[15] == null) ? null : (ob[15].toString().equalsIgnoreCase(" ")) ? " " : ob[15].toString()));
            provisionDetail.setCity(((ob[16] == null) ? null : (ob[16].toString().equalsIgnoreCase(" ")) ? " " : ob[16].toString()));
            provisionDetail.setState(((ob[17] == null) ? null : (ob[17].toString().equalsIgnoreCase(" ")) ? " " : ob[17].toString()));
            provisionDetail.setCounty(((ob[18] == null) ? null : (ob[18].toString().equalsIgnoreCase(" ")) ? " " : ob[18].toString()));
            provisionDetail.setPin(((ob[19] == null) ? null : (ob[19].toString().equalsIgnoreCase(" ")) ? " " : ob[19].toString()));
            provisionDetail.setEmail(((ob[20] == null) ? null : (ob[20].toString().equalsIgnoreCase(" ")) ? " " : ob[20].toString()));
            provisionDetail.setPhoneNo(((ob[21] == null) ? null : (ob[21].toString().equalsIgnoreCase(" ")) ? " " : ob[21].toString()));
            provisionDetail.setMobile(((ob[22] == null) ? null : (ob[22].toString().equalsIgnoreCase(" ")) ? " " : ob[22].toString()));
            provisionDetail.setExtension(((ob[23] == null) ? null : (ob[23].toString().equalsIgnoreCase(" ")) ? " " : ob[23].toString()));
            provisionDetail.setIpPhone(((ob[24] == null) ? null : (ob[24].toString().equalsIgnoreCase(" ")) ? " " : ob[24].toString()));
            provisionDetail.setStartDate(((ob[25] == null) ? null : (ob[25].toString().equalsIgnoreCase(" ")) ? " " : ob[25].toString()));
            provisionDetail.setTitle(((ob[26] == null) ? null : (ob[26].toString().equalsIgnoreCase(" ")) ? " " : ob[26].toString()));
            provisionDetail.setSupervisorId(((ob[27] == null) ? "" : (ob[27].toString().equalsIgnoreCase(" ")) ? "" : ob[27].toString()));
            provisionDetail.setDesignation(((ob[28] == null) ? null : (ob[28].toString().equalsIgnoreCase(" ")) ? " " : ob[28].toString()));
            provisionDetail
                    .setPermissionList(((ob[29] == null) ? null : (ob[29].toString().equalsIgnoreCase(" ")) ? " " : ob[29].toString()));
            provisionDetail.setStatus(((ob[30] == null) ? null : (ob[30].toString().equalsIgnoreCase(" ")) ? " " : ob[30].toString()));
            
            tempList.put((Number) ob[0], provisionDetail);
        }
		return tempList;
	}

	@Override
    public void updateIntermediateStatus(ProvDetailEntity provisionDetail) {
        EntityManager entityManager = null;
        try {
            entityManager = DBUtil.getEntityManager();
            entityManager.getTransaction().begin();
            ProvDetailEntity provDetailEntity = entityManager.find(
                    ProvDetailEntity.class, provisionDetail.getProvisionId());

            if (provDetailEntity.getProvisionStatus().equalsIgnoreCase("R")) {
                provDetailEntity.setProvisionStatus("I");
                ProvDetailEntity merge = entityManager.merge(provDetailEntity);
                LOGGER.info("Provision ID: " + merge);
            }else{
            	LOGGER.info(provisionDetail.getProvisionId() + "            Status already updated with -------------   " + provDetailEntity.getProvisionStatus());	
            }
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            LOGGER.info("Error occured during intermediate status"
                    + e.getMessage());
            entityManager.getTransaction().rollback();
        } finally {
            if (entityManager != null) {
                entityManager.close();
                LOGGER.debug("Entity Manager is closed");
            }
        }
    }
    
    @Override
    public void revertIntermediateStatus(Long provId) {

        EntityManager entityManager = null;
        try {
            entityManager = DBUtil.getEntityManager();
            entityManager.getTransaction().begin();
            ProvDetailEntity provDetailEntity = entityManager.find(
                    ProvDetailEntity.class, provId);

            if (provDetailEntity.getProvisionStatus().equalsIgnoreCase("I")) {
                provDetailEntity.setProvisionStatus("R");
                ProvDetailEntity merge = entityManager.merge(provDetailEntity);
                LOGGER.info("Reverted Provision ID Status: " + merge);
            }else{
            	LOGGER.info(provId + "              Status already updated with -------------   " + provDetailEntity.getProvisionStatus());	
            }
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            LOGGER.info("Error occured during intermediate status"
                    + e.getMessage());
            entityManager.getTransaction().rollback();
        } finally {
            if (entityManager != null) {
                entityManager.close();
                LOGGER.debug("Entity Manager is closed");
            }
        }
    }
    
    
    
}