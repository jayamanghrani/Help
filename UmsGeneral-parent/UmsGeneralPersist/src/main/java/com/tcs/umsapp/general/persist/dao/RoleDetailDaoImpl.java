package com.tcs.umsapp.general.persist.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import com.tcs.umsapp.general.persist.DBUtil;
import com.tcs.umsapp.general.persist.entities.AppRole;
import com.tcs.umsapp.general.persist.entities.Role;

public class RoleDetailDaoImpl implements RoleDetailDao {

    private static final Logger LOGGER = Logger
            .getLogger(RoleDetailDaoImpl.class);

    private ConcurrentMap<String, String> applicationNameList;

    EntityManager entityManager = DBUtil.getEntityManager();

    @Override
    public ConcurrentMap<String, ConcurrentMap<String, Long>> getRoleDetailList() {

        LOGGER.debug("getRoleDetailList called");
        ConcurrentMap<String, ConcurrentMap<String, Long>> roleList = new ConcurrentHashMap<String, ConcurrentMap<String, Long>>();
        try {

            ConcurrentMap<String, String> appNameList = getAppNameList(entityManager);

            ConcurrentMap<String, Long> appRoleList = new ConcurrentHashMap<>();

            for (Map.Entry<String, String> entry : appNameList.entrySet()) {
                appRoleList = getRoleNameList(entityManager, entry.getValue());
                roleList.put(entry.getKey(), appRoleList);

            }

            entityManager.clear();
            entityManager.close();

        } catch (Exception ex) {
            LOGGER.error("Exception inside DB call " + ex.getMessage());

        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.clear();
                entityManager.close();
            }
        }

        return roleList;

    }

    public ConcurrentMap<String, String> getAppNameList(
            EntityManager entityManager) {
        CriteriaBuilder criteriaBuilderObj = entityManager.getCriteriaBuilder();
        CriteriaQuery<AppRole> queryObj = criteriaBuilderObj
                .createQuery(AppRole.class);

        Root<AppRole> root = queryObj.from(AppRole.class);

        queryObj.where(criteriaBuilderObj.equal(root.get("internalRole"), "N"));

        CriteriaQuery<AppRole> selectQuery = queryObj.select(root);

        TypedQuery<AppRole> typedQuery = entityManager.createQuery(selectQuery);

        List<AppRole> dataList = typedQuery.getResultList();
        ConcurrentMap<String, String> appNameList = new ConcurrentHashMap<String, String>();

        for (AppRole list : dataList) {
            appNameList.put(list.getAoolicationName(), list.getApplicationId());
        }
        this.applicationNameList = appNameList;
        return appNameList;

    }

    public ConcurrentMap<String, Long> getRoleNameList(
            EntityManager entityManager, String applicationId) {
        CriteriaBuilder criteriaBuilderObj = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> queryObj = criteriaBuilderObj
                .createQuery(Role.class);

        Root<Role> root = queryObj.from(Role.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        Predicate predicateAppId = criteriaBuilderObj.equal(
                root.get("applicationId"), applicationId);
        predicates.add(predicateAppId);

        Predicate predicateInternalRole = criteriaBuilderObj.equal(
                root.get("internalRole"), "N");
        predicates.add(predicateInternalRole);

        Predicate predicateStatus = criteriaBuilderObj.equal(
                root.get("status"), "E");
        predicates.add(predicateStatus);

        queryObj.where(criteriaBuilderObj.and(predicates
                .toArray(new Predicate[] {})));

        CriteriaQuery<Role> selectQuery = queryObj.select(root);

        TypedQuery<Role> typedQuery = entityManager.createQuery(selectQuery);
        List<Role> dataList = typedQuery.getResultList();

        ConcurrentMap<String, Long> roleNameList = new ConcurrentHashMap<String, Long>();
        for (Role list : dataList) {
            roleNameList.put(list.getRoleName(), list.getRoleId());
        }
        return roleNameList;
    }

    // Application Name Id map
    public ConcurrentMap<String, String> getApplicationNameList() {
        return applicationNameList;
    }

	@Override
	public ConcurrentMap<Long , String> getNonPrivilageRoleList() {
		EntityManager entityManagerPrivilege = DBUtil.getEntityManager();
		LOGGER.debug("getPrivilageRoleList called -----------  ");
        ConcurrentMap<Long , String> nonPrivilageRoleList = new ConcurrentHashMap<Long , String>();
        try {

            CriteriaBuilder criteriaBuilderObj = entityManagerPrivilege.getCriteriaBuilder();
            CriteriaQuery<Role> queryObj = criteriaBuilderObj
                    .createQuery(Role.class);

            Root<Role> root = queryObj.from(Role.class);

            List<Predicate> predicates = new ArrayList<Predicate>();

            Predicate predicateInternalRole = criteriaBuilderObj.equal(
                    root.get("internalRole"), "N");
            predicates.add(predicateInternalRole);
            
            Predicate predicatePrivilageRole = criteriaBuilderObj.equal(
                    root.get("rolePrivilage"), "N");
            predicates.add(predicatePrivilageRole);

            Predicate predicateStatus = criteriaBuilderObj.equal(
                    root.get("status"), "E");
            predicates.add(predicateStatus);

            queryObj.where(criteriaBuilderObj.and(predicates
                    .toArray(new Predicate[] {})));

            CriteriaQuery<Role> selectQuery = queryObj.select(root);

            TypedQuery<Role> typedQuery = entityManagerPrivilege.createQuery(selectQuery);
            List<Role> dataList = typedQuery.getResultList();

            for (Role list : dataList) {
            	nonPrivilageRoleList.put(list.getRoleId(), list.getRoleName());
            }
            entityManagerPrivilege.clear();
            entityManagerPrivilege.close();

        } catch (Exception ex) {
            LOGGER.error("Exception inside DB call " + ex.getMessage());

        } finally {
            if (entityManagerPrivilege != null && entityManagerPrivilege.isOpen()) {
            	entityManagerPrivilege.clear();
            	entityManagerPrivilege.close();
            }
        }

        return nonPrivilageRoleList;
	}
	
	@Override
	public String getBranchId(String userId) {
		String LoginId=null;
		EntityManager entityManager = null;
        try {
            entityManager = DBUtil.getEntityManager();
            Query query = entityManager.createNativeQuery("select distinct UUM_BRANCH_ID from UMS_USR_MST where uum_usr_id=:userId");
           
            query.setParameter("userId", userId);
            List resultList = query.getResultList();
            
            if(!resultList.isEmpty()){
            	LoginId = (String) resultList.get(0);
            }

            entityManager.close();
        } catch (Exception ex) {
            LOGGER.info("Exception in getLoginUserBranch: "
                    + ex.getStackTrace());
            if (entityManager != null) {
                entityManager.clear();
                entityManager.close();
            }
        }finally {
            try {
                if (null != entityManager) {
                    entityManager.close();
                }
            } catch (Exception e) {
                LOGGER.error("error occurred "+e.getStackTrace());
            }
        }
		
		return LoginId;
	}

    @Override
    public ConcurrentMap<String , String> getRoleForJira(String branchId) {
        String LoginId=null;
        ConcurrentMap<String , String> roleList = new ConcurrentHashMap<String , String>();
        EntityManager entityManager = null;
        try {
            entityManager = DBUtil.getEntityManager();
            Query query = entityManager.createNativeQuery("select * from UMS_JIRA_RO_MAPPING where UJRM_RO_CODE=:branchId");
           
            query.setParameter("branchId", branchId);
            List resultList = query.getResultList();
            Iterator iter=resultList.iterator();
            while(iter.hasNext()){
                Object[] rolesData= (Object[]) iter.next();
                roleList.put(String.valueOf(rolesData[0]), rolesData[1].toString());
            }

            entityManager.close();
        } catch (Exception ex) {
            LOGGER.info("Exception in getLoginUserBranch: "
                    + ex.getStackTrace());
            if (entityManager != null) {
                entityManager.clear();
                entityManager.close();
            }
        }finally {
            try {
                if (null != entityManager) {
                    entityManager.close();
                }
            } catch (Exception e) {
                LOGGER.error("error occurred "+e.getStackTrace());
            }
        }
        
        return roleList;
    }

}
