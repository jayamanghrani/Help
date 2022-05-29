package com.tcs.umsapp.unlock.persist.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import com.tcs.umsapp.unlock.persist.DBUtil;
import com.tcs.umsapp.unlock.persist.entities.UserInfo;
import com.tcs.umsapp.unlock.request.UserDetailDBRequestASBO;
import com.tcs.umsapp.unlock.response.UserDetailDBResponseASBO;

public class UserInfoDaoImpl implements UserInfoDao {

	private static final Logger LOGGER = Logger
			.getLogger(UserInfoDaoImpl.class);
	/**
	 * 
	 */
	@Override
	public UserDetailDBResponseASBO getUserInfo(UserDetailDBRequestASBO userDetailDBRequestASBO) {
		
		LOGGER.info("Inside UserInfoDaoImpl.getUserInfo");
		
		EntityManager entityManager = null;
		UserDetailDBResponseASBO userDetailDBResponseASBO = new UserDetailDBResponseASBO();
		userDetailDBResponseASBO.setHeader(userDetailDBRequestASBO.getHeader());
		try{
			
			entityManager = DBUtil.getEntityManager();
			
			CriteriaBuilder criteriaBuilderObj = entityManager
					.getCriteriaBuilder();
			CriteriaQuery<UserInfo> queryObj = criteriaBuilderObj.createQuery(UserInfo.class);
			
			Root<UserInfo> root = queryObj.from(UserInfo.class);
			
			queryObj.where(criteriaBuilderObj.equal(root.get("userId"),userDetailDBRequestASBO.getUserId()));
			
			CriteriaQuery<UserInfo> selectQuery = queryObj.select(root);

			TypedQuery<UserInfo> typedQuery = entityManager
					.createQuery(selectQuery);

			List<UserInfo> dataList = typedQuery.getResultList();
			
			if(!dataList.isEmpty()){
				UserInfo userInfo = dataList.get(0);
				
				userDetailDBResponseASBO.setUserId(userInfo.getUserId());
				userDetailDBResponseASBO.setEmail(userInfo.getEmail());
				userDetailDBResponseASBO.setMobile(userInfo.getMobile());
				userDetailDBResponseASBO.setFirstName(userInfo.getFirstName());
				userDetailDBResponseASBO.setLastName(userInfo.getLastName());
				userDetailDBResponseASBO.setGender(userInfo.getGender());
				LOGGER.info("userInfo: " + userInfo.toString());
			}
		}catch(Exception ex){
			LOGGER.error("Exception in getUserInfo: " + ex.getLocalizedMessage());
			if(entityManager!=null && entityManager.isOpen()){
				entityManager.clear();
				entityManager.close();
				LOGGER.info("EntityManager is closed");
			}
		} 
		
		return userDetailDBResponseASBO;
	}

}
