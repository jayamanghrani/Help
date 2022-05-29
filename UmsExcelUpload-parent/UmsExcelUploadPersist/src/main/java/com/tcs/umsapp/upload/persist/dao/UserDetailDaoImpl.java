package com.tcs.umsapp.upload.persist.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tcs.umsapp.upload.persist.DBUtil;
import com.tcs.umsapp.upload.persist.entities.Application;
import com.tcs.umsapp.upload.persist.entities.Role;
import com.tcs.umsapp.upload.persist.query.Queries;

public class UserDetailDaoImpl implements UserDetailDao {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserDetailDaoImpl.class);

	public String getLoginUserBranch(String user) {

		LOGGER.info(" *** Inside method getLoginUserBranch *** ");
		EntityManager entityManager = null;

		try {
			entityManager = DBUtil.getEntityManager();

			String q = Queries.UserBranch;

			LOGGER.info(" Generated Query " + q);

			Query query = entityManager.createNativeQuery(q);

			LOGGER.info("User: " + user);

			query.setParameter("userId", user);

			LOGGER.info("DB Query object" + query);

			@SuppressWarnings("unchecked")
			List<Object> obList = query.getResultList();
			String branch = null;
			LOGGER.info("Query Result : " + obList.size());
			if (obList.size() == 1) {
				branch = obList.get(0).toString();
				LOGGER.info("User branch: " + obList.get(0));
			}
			if (entityManager != null) {
				entityManager.clear();
				entityManager.close();
			}
			return branch;
		} catch (Exception ex) {
			LOGGER.info("Exception in getLoginUserBranch ");
			ex.printStackTrace();
			if (entityManager != null) {
				entityManager.clear();
				entityManager.close();
			}
		}
		return null;
	}

	// Get All Application Name List
	public List<String> getAppNameList() {

		EntityManager entityManager = null;
		List<String> appNameList = new ArrayList<String>();
		try {
			entityManager = DBUtil.getEntityManager();

			CriteriaBuilder criteriaBuilderObj = entityManager
					.getCriteriaBuilder();
			CriteriaQuery<Application> queryObj = criteriaBuilderObj
					.createQuery(Application.class);

			Root<Application> root = queryObj.from(Application.class);

			queryObj.where(criteriaBuilderObj.equal(root.get("internalRole"),
					"N"));

			CriteriaQuery<Application> selectQuery = queryObj.select(root);

			TypedQuery<Application> typedQuery = entityManager
					.createQuery(selectQuery);

			List<Application> dataList = typedQuery.getResultList();

			for (Application list : dataList) {
				appNameList.add(list.getApplicationName());
				// System.out.println("Role: " + list.getRoleName());
			}
		} catch (Exception ex) {
			LOGGER.info("Exception in getAppNameList ");
			ex.printStackTrace();
			if (entityManager != null) {
				entityManager.clear();
				entityManager.close();
			}
		}
		return appNameList;

	}

	// Get All Role Name List
	public Map<String, String> getRoleNameList() {

		EntityManager entityManager = null;
		Map<String, String> roleNameListMap = new HashMap<String, String>();
		try {

			entityManager = DBUtil.getEntityManager();
			CriteriaBuilder criteriaBuilderObj = entityManager
					.getCriteriaBuilder();
			CriteriaQuery<Role> queryObj = criteriaBuilderObj
					.createQuery(Role.class);

			Root<Role> root = queryObj.from(Role.class);

			List<Predicate> predicates = new ArrayList<Predicate>();

			Predicate predicateStatus = criteriaBuilderObj.equal(
					root.get("status"), "E");
			predicates.add(predicateStatus);

			queryObj.where(predicates.toArray(new Predicate[] {}));

			CriteriaQuery<Role> selectQuery = queryObj.select(root);

			TypedQuery<Role> typedQuery = entityManager
					.createQuery(selectQuery);

			List<Role> dataList = typedQuery.getResultList();

			for (Role list : dataList) {

				roleNameListMap.put(list.getRoleName(), list.getInternalRole());
				// System.out.println("Role: " + list.getRoleName());
			}
		} catch (Exception ex) {
			LOGGER.info("Exception in getAppNameList ");
			ex.printStackTrace();
			if (entityManager != null) {
				entityManager.clear();
				entityManager.close();
			}
		}
		return roleNameListMap;
	}

	
	// Find Application Access given to login User
	public List<String> getAppAccessList(String user){
		EntityManager entityManager = null;
		List<String> userAppList = new ArrayList<String>();
		
		try{
			
			entityManager = DBUtil.getEntityManager();

			String q = Queries.UserApplication;

			LOGGER.info(" Generated Query " + q);

			Query query = entityManager.createNativeQuery(q);

			LOGGER.info("User: " + user);

			query.setParameter("userId", user);

			LOGGER.info("DB Query object" + query);

			@SuppressWarnings("unchecked")
			List<Object> obList = query.getResultList();
			
			LOGGER.info("Query Result : " + obList.size());
			if (obList.size() != 0) {
				
				for(Object appName : obList){
					userAppList.add(appName.toString());
				
				}
				
				LOGGER.info("User Applcation: " + userAppList.toString());
			}
			if (entityManager != null) {
				entityManager.clear();
				entityManager.close();
			}
			
			
			
		}catch (Exception ex) {
			LOGGER.info("Exception in getAppNameList ");
			ex.printStackTrace();
			if (entityManager != null) {
				entityManager.clear();
				entityManager.close();
			}
		}
		return userAppList;
	}

	@Override
	public List<String> getAppId(String application) {
		EntityManager entityManager = null;
		List<String> userAppId = new ArrayList<String>();
		
		try{
			
			entityManager = DBUtil.getEntityManager();

			String q = Queries.UserApplicationId;

			LOGGER.info(" Generated Query " + q);

			Query query = entityManager.createNativeQuery(q);

			LOGGER.info("appName " + application);

			query.setParameter("appName", application);

			LOGGER.info("DB Query object" + query);

			@SuppressWarnings("unchecked")
			List<Object> obList = query.getResultList();
			
			LOGGER.info("Query Result : " + obList.size());
			if (obList.size() != 0) {
				
				for(Object appName : obList){
					userAppId.add(appName.toString());
				
				}
				
				LOGGER.info("User Applcation: " + userAppId);
			}
			if (entityManager != null) {
				entityManager.clear();
				entityManager.close();
			}
			
			
			
		}catch (Exception ex) {
			LOGGER.info("Exception in getAppNameList ");
			ex.printStackTrace();
			if (entityManager != null) {
				entityManager.clear();
				entityManager.close();
			}
		}
		return userAppId;
	}

	@Override
	public List<String> getRoleId(String AppId1, String role) {
		// TODO Auto-generated method stub
		EntityManager entityManager = null;
		List<String> userRoleId = new ArrayList<String>();
		
		try{
			
			entityManager = DBUtil.getEntityManager();

			String q = Queries.RoleId;

			LOGGER.info(" Generated Query " + q);

			Query query = entityManager.createNativeQuery(q);

			LOGGER.info("Role " + role);
			
			LOGGER.info("AppId " + AppId1);
			
			
			
			query.setParameter("Role", role);
			query.setParameter("AppId", AppId1);

			LOGGER.info("DB Query object" + query);

			@SuppressWarnings("unchecked")
			List<Object> obList = query.getResultList();
			
			LOGGER.info("Query Result : " + obList.size());
			if (obList.size() != 0) {
				
				for(Object appName : obList){
					userRoleId.add(appName.toString());
				
				}
				
				LOGGER.info("User Applcation: " + userRoleId.toString());
			}
			if (entityManager != null) {
				entityManager.clear();
				entityManager.close();
			}
			
			
			
		}catch (Exception ex) {
			LOGGER.info("Exception in getAppNameList ");
			ex.printStackTrace();
			if (entityManager != null) {
				entityManager.clear();
				entityManager.close();
			}
		}
		return userRoleId;
}
}