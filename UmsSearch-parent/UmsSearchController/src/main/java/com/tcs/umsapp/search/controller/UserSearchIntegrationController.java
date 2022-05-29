package com.tcs.umsapp.search.controller;

import org.apache.log4j.Logger;

import com.tcs.umsapp.search.controller.util.UserAppAndRoleFormatter;
import com.tcs.umsapp.search.exception.cmo.ErrorVO;
import com.tcs.umsapp.search.exception.cmo.ValidationException;
import com.tcs.umsapp.search.persist.dao.UserDetailDao;
import com.tcs.umsapp.search.persist.dao.UserDetailDaoImpl;
import com.tcs.umsapp.search.request.GetUserSearchDetailsRequestASBO;
import com.tcs.umsapp.search.request.UserRoleSearchRequestASBO;
import com.tcs.umsapp.search.request.UserRoleSearchRequestXLSASBO;
import com.tcs.umsapp.search.so.request.GetContentRequestSO;
import com.tcs.umsapp.search.so.request.UserRoleSearchRequestSO;
import com.tcs.umsapp.search.so.request.UserRoleSearchRequestXLSSO;
import com.tcs.umsapp.search.so.response.UserAppAndRoleAccessDetails;
import com.tcs.umsapp.search.so.response.UserSearchDetailsSO;
import com.tcs.umsapp.search.util.UtilConstants;
import com.tcs.umsapp.search.util.ValidationUtil;
import com.tcs.umsapp.search.vo.cmo.Header;
import com.tcs.umsapp.search.vo.cmo.UmsappResponseObject;
import com.tcs.umsapp.serach.response.UserRoleExcelResponseASBO;
import com.tcs.umsapp.serach.response.UserRoleSearchResponseASBO;
import com.tcs.umsapp.serach.response.UserSearchDetailsResponseASBO;

public class UserSearchIntegrationController {

	private static final Logger LOGGER = Logger
			.getLogger(UserSearchIntegrationController.class);
	private UserDetailDao userDetailDao;

	
	public UserSearchIntegrationController() {
		userDetailDao = new UserDetailDaoImpl();
		LOGGER.info("Reached inside integration controller");
	}

	public UmsappResponseObject getUserDetails(
			GetContentRequestSO getContentRequestSO) {
		LOGGER.info("Reached in getUserDetails in UserSearchIntegrationController");
		UserSearchDetailsResponseASBO getUserSearchDetailsResponseASBO;
		
		boolean dataFlag = false;
		try {

			if(getContentRequestSO.getUserId()!=null&&!getContentRequestSO.getUserId().isEmpty()){
				dataFlag = true;
				if(!ValidationUtil.validateUserID(getContentRequestSO.getUserId())){
					LOGGER.error("Invalid UserID : "+getContentRequestSO.getUserId());
					throw new ValidationException(UtilConstants.ERROR_CODE_TransformationException,"User ID");
				}
			}
			if(getContentRequestSO.getBranchId()!=null&&!getContentRequestSO.getBranchId().isEmpty()){
				dataFlag = true;
				if(!ValidationUtil.validateBranchID(getContentRequestSO.getBranchId())){
					LOGGER.error("Invalid Branch ID : "+getContentRequestSO.getBranchId());
					throw new ValidationException(UtilConstants.ERROR_CODE_TransformationException,"Branch ID");
				}
			}
			if(getContentRequestSO.getFirstName()!=null&&!getContentRequestSO.getFirstName().isEmpty()){
				dataFlag = true;
				if(!ValidationUtil.validateName(getContentRequestSO.getFirstName())){
					LOGGER.error("Invalid First Name : "+getContentRequestSO.getFirstName());
					throw new ValidationException(UtilConstants.ERROR_CODE_TransformationException,"First Name");
				}
			}
			if(getContentRequestSO.getLastName()!=null&&!getContentRequestSO.getLastName().isEmpty()){
				dataFlag = true;
				if(!ValidationUtil.validateName(getContentRequestSO.getLastName())){
					LOGGER.error("Invalid Last Name : "+getContentRequestSO.getLastName());
					throw new ValidationException(UtilConstants.ERROR_CODE_TransformationException,"Last Name");
				}
			}
			if(getContentRequestSO.getApplicationId()!=null&&!getContentRequestSO.getApplicationId().isEmpty()){
				dataFlag = true;
				if(!ValidationUtil.validateApplication(getContentRequestSO.getApplicationId())){
					LOGGER.error("Invalid Application : "+getContentRequestSO.getApplicationId());
					throw new ValidationException(UtilConstants.ERROR_CODE_TransformationException,"Application");
				}
			}
			if(getContentRequestSO.getRoleId()!=null&&!getContentRequestSO.getRoleId().isEmpty()){
				dataFlag = true;
				if(!ValidationUtil.validateRoleID(getContentRequestSO.getRoleId())){
					LOGGER.error("Invalid Role ID : "+getContentRequestSO.getRoleId());
					throw new ValidationException(UtilConstants.ERROR_CODE_TransformationException,"Role ID ");
				}
			}
			if(getContentRequestSO.getSupervisorId()!=null&&!getContentRequestSO.getSupervisorId().isEmpty()){
				dataFlag = true;
				if(!ValidationUtil.validateUserID(getContentRequestSO.getSupervisorId())){
					LOGGER.error("Invalid Supervisor ID : "+getContentRequestSO.getSupervisorId());
					throw new ValidationException(UtilConstants.ERROR_CODE_TransformationException,"Supervisor ID");
				}
			}

			if(!dataFlag){
				LOGGER.error("No criteria specified for search request");
				ErrorVO errorVO = new ErrorVO();
				errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
				errorVO.setErrorMessage("No criteria specified for search");
				return errorVO;
			}
		}catch(ValidationException ve){
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrorCode(ve.getErrorCode());
			errorVO.setErrorMessage("Invalid value for the field : " + ve.getField());
			return errorVO;
		}
		/* Transform SO to ASBO */
		Header header = getContentRequestSO.getHeader();
		GetUserSearchDetailsRequestASBO getUserSearchDetailsRequestASBO = new GetUserSearchDetailsRequestASBO();

		getUserSearchDetailsRequestASBO.setUserId(getContentRequestSO
				.getUserId());
		getUserSearchDetailsRequestASBO.setBranchId(getContentRequestSO
				.getBranchId());
		getUserSearchDetailsRequestASBO.setDesignation(getContentRequestSO
				.getDesignation());
		getUserSearchDetailsRequestASBO.setFirstName(getContentRequestSO
				.getFirstName());
		getUserSearchDetailsRequestASBO.setLastName(getContentRequestSO
				.getLastName());
		getUserSearchDetailsRequestASBO.setSupervisorID(getContentRequestSO
				.getSupervisorId());
		getUserSearchDetailsRequestASBO.setApplicationId(getContentRequestSO.getApplicationId());
		getUserSearchDetailsRequestASBO.setRoleId(getContentRequestSO.getRoleId());
		getUserSearchDetailsRequestASBO.setHeader(getContentRequestSO
				.getHeader());

		LOGGER.info(" getUserSearchDetailsResponseASBO value: "
				+ getUserSearchDetailsRequestASBO.toString());
        try{
		/* DataBase Call through dao class */
		getUserSearchDetailsResponseASBO = userDetailDao
				.getUserDetailsDB(getUserSearchDetailsRequestASBO);
		getUserSearchDetailsResponseASBO.setHeader(header);
        }catch(Exception e)
        {
            LOGGER.error("Exception Occurred--"+e.getStackTrace());
            ErrorVO errorVO = new ErrorVO();
            errorVO.setErrorCode(UtilConstants.ERROR_CODE_RUNTIME_TECHNICHALEXCEPTION);
            errorVO.setErrorMessage(e.getLocalizedMessage());
            return errorVO;
            
        }
		LOGGER.info("getUserSearchDetailsResponseASBO value: "
				+ getUserSearchDetailsResponseASBO.toString());

		return getUserSearchDetailsResponseASBO;
	}


	public UmsappResponseObject getUserRoleDetails(
			UserRoleSearchRequestSO userRoleSearchRequestSO) {
	    UserRoleSearchResponseASBO userRoleSearchResponseASBO;
		LOGGER.info("getUserRoleDetails ==================>" +userRoleSearchRequestSO.toString());

		try{
			if(!ValidationUtil.validateUserID(userRoleSearchRequestSO.getUserId())){
				LOGGER.error("Invalid UserID : "+userRoleSearchRequestSO.getUserId());
				throw new ValidationException(UtilConstants.ERROR_CODE_TransformationException,"Invalid UserID received");
			}
		}catch(ValidationException ve){
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrorCode(ve.getErrorCode());
			errorVO.setErrorMessage(ve.getField());
			return errorVO;
		}

		/* Transform SO to ASBO */
		UserRoleSearchRequestASBO userRoleSearchRequestASBO = new UserRoleSearchRequestASBO();

		userRoleSearchRequestASBO
		.setHeader(userRoleSearchRequestSO.getHeader());
		userRoleSearchRequestASBO
		.setUserId(userRoleSearchRequestSO.getUserId());
		userRoleSearchRequestASBO.setBranchId(userRoleSearchRequestSO.getBranchId());
        
		/* DataBase Call through dao class */
		try{
		userRoleSearchResponseASBO = userDetailDao
				.getUserRoleDetailsDB(userRoleSearchRequestASBO);
		userRoleSearchResponseASBO.setHeader(userRoleSearchRequestASBO
				.getHeader());
		}catch(Exception e)
		{
		    LOGGER.error("Exception Occurred--"+e.getStackTrace());
            ErrorVO errorVO = new ErrorVO();
            errorVO.setErrorCode(UtilConstants.ERROR_CODE_RUNTIME_TECHNICHALEXCEPTION);
            errorVO.setErrorMessage(e.getLocalizedMessage());
            return errorVO;
		}
		UserAppAndRoleFormatter userAppAndRoleFormatter = new UserAppAndRoleFormatter();
		UserAppAndRoleAccessDetails userAppAndRoleAccessDetails = userAppAndRoleFormatter
				.getFormattedList(userRoleSearchResponseASBO);

		LOGGER.info("userRoleSearchResponseASBO value: "
				+ userRoleSearchResponseASBO.toString());

		return userAppAndRoleAccessDetails;

	}

	public UmsappResponseObject getUserRoleDetailXLS(
			UserRoleSearchRequestXLSSO userRoleSearchRequestSO) {
	    UserRoleExcelResponseASBO userRoleExcelResponseASBO;
	    LOGGER.info("getUserRoleDetailXLS ===== >"+userRoleSearchRequestSO.toString());
		try {
			if(!ValidationUtil.validateList(userRoleSearchRequestSO.getUserId())){
				LOGGER.error("Invalid User ID List : "+userRoleSearchRequestSO.getUserId());
				throw new ValidationException(UtilConstants.ERROR_CODE_TransformationException,"Empty/Null User ID list received.");				
			}
			for(String user : userRoleSearchRequestSO.getUserId()){
				if(!ValidationUtil.validateUserID(user)){
					LOGGER.error("Invalid UserID : "+user);
					throw new ValidationException(UtilConstants.ERROR_CODE_TransformationException,"Invalid user id found : " + user);
				}
			}
		}catch(ValidationException ve){
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrorCode(ve.getErrorCode());
			errorVO.setErrorMessage(ve.getField());
			return errorVO;
		}
		UserRoleSearchRequestXLSASBO userRoleSearchRequestASBO = new UserRoleSearchRequestXLSASBO();
		userRoleSearchRequestASBO.setHeader(userRoleSearchRequestSO.getHeader());
		userRoleSearchRequestASBO.setUserId(userRoleSearchRequestSO.getUserId());
		try{
		userRoleExcelResponseASBO = userDetailDao.getUserRoleDetailXLSDB(userRoleSearchRequestASBO);
		}catch(Exception e)
		{
		    LOGGER.error("Exception Occurred--"+e.getStackTrace());
            ErrorVO errorVO = new ErrorVO();
            errorVO.setErrorCode(UtilConstants.ERROR_CODE_RUNTIME_TECHNICHALEXCEPTION);
            errorVO.setErrorMessage(e.getLocalizedMessage());
            return errorVO;
		}
		return userRoleExcelResponseASBO;
	}
	
	public UmsappResponseObject getLoginDetails(String userId) {
		LOGGER.info("getLoginDetails ------->"+userId);
		/* Transform SO to ASBO */
		try{
			if(!ValidationUtil.validateUserID(userId)){
				LOGGER.error("Invalid UserID : "+userId);
				throw new ValidationException(UtilConstants.ERROR_CODE_TransformationException,"Invalid UserID");
			}
		}catch(ValidationException ve){
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrorCode(ve.getErrorCode());
			errorVO.setErrorMessage(ve.getField());
			return errorVO;			
		}
		LOGGER.info(" getUserSearchDetailsResponseASBO value: " + userId);  
		return  userDetailDao.getLoginUserDetails(userId);
	}
	
	public UmsappResponseObject getOfficeCodeDetails(String userid) {
	    UserSearchDetailsSO details=new UserSearchDetailsSO();
	      details=userDetailDao.getLoginUserDetails(userid);
	      String branchId =details.getBranchId();
	      
		LOGGER.info(" User Logged In branch Id : "+ branchId);
		return  userDetailDao.getOfficeCodeDetails(branchId);
	}

}
