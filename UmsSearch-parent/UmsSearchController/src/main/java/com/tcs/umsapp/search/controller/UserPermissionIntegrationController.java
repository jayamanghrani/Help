package com.tcs.umsapp.search.controller;

import org.apache.log4j.Logger;

import com.tcs.umsapp.search.exception.cmo.ErrorVO;
import com.tcs.umsapp.search.exception.cmo.ValidationException;
import com.tcs.umsapp.search.persist.dao.UserDetailDao;
import com.tcs.umsapp.search.persist.dao.UserDetailDaoImpl;
import com.tcs.umsapp.search.request.GetUserPermissionDetailsRequestASBO;
import com.tcs.umsapp.search.request.UserPermissionSearchRequestASBO;
import com.tcs.umsapp.search.so.request.GetContentPermissionRequestSO;
import com.tcs.umsapp.search.so.request.UserPermissionSearchRequestSO;
import com.tcs.umsapp.search.util.UtilConstants;
import com.tcs.umsapp.search.util.ValidationUtil;
import com.tcs.umsapp.search.vo.cmo.Header;
import com.tcs.umsapp.search.vo.cmo.UmsappResponseObject;
import com.tcs.umsapp.serach.response.UserPermissionResponseASBO;
import com.tcs.umsapp.serach.response.UserPermissionSearchResponseASBO;

public class UserPermissionIntegrationController {

    private static final Logger LOGGER = Logger
            .getLogger(UserPermissionIntegrationController.class);

    private UserDetailDao userDetailDao;
    /** 
	 * 
	 */
    public UserPermissionIntegrationController() {
        userDetailDao = new UserDetailDaoImpl();
    }

    /**
     * 
     * @param getContentPermissionRequestSO
     * @return
     */
    public UmsappResponseObject getUserPermissionDetails(
            GetContentPermissionRequestSO getContentPermissionRequestSO) {
        UserPermissionResponseASBO getuserserPermissionResponseASBO;
        LOGGER.info("****entering getUserPermissionDetails***");
        Header header = getContentPermissionRequestSO.getHeader();
        GetUserPermissionDetailsRequestASBO getUserPermissionDetailsRequestASBO = new GetUserPermissionDetailsRequestASBO();

        getUserPermissionDetailsRequestASBO
                .setUserID(getContentPermissionRequestSO.getUserID());
        getUserPermissionDetailsRequestASBO
                .setPlID(getContentPermissionRequestSO.getPlID());
        getUserPermissionDetailsRequestASBO
                .setPlName(getContentPermissionRequestSO.getPlName());
        getUserPermissionDetailsRequestASBO
                .setBranchID(getContentPermissionRequestSO.getBranchId());
        getUserPermissionDetailsRequestASBO
                .setHeader(getContentPermissionRequestSO.getHeader());

        LOGGER.info(" getUserPermissionDetailsRequestASBO :---- "
                + getUserPermissionDetailsRequestASBO.toString());
       try{
        getuserserPermissionResponseASBO = userDetailDao
                .getUserPermissionDetailsDB(getUserPermissionDetailsRequestASBO);
       }catch(Exception e)
       {
           LOGGER.error("Exception Occurred--"+e.getStackTrace());
           ErrorVO errorVO = new ErrorVO();
           errorVO.setErrorCode(UtilConstants.ERROR_CODE_RUNTIME_TECHNICHALEXCEPTION);
           errorVO.setErrorMessage(e.getLocalizedMessage());
           return errorVO;
       }
        getuserserPermissionResponseASBO.setHeader(header);

        return getuserserPermissionResponseASBO;
    }

    /**
     * 
     * @param getuserPermissionSearchRequestSO
     * @return
     */
    public UmsappResponseObject getUserSearchPermissionDetails(
            UserPermissionSearchRequestSO getuserPermissionSearchRequestSO) {
        LOGGER.info("****Reached in getUserSearchPermissionDetails in UserPermissionIntegrationController***");

        try {
            if (!ValidationUtil.validateUserID(getuserPermissionSearchRequestSO
                    .getUserId())) {
                LOGGER.error("Invalid User ID  : "
                        + getuserPermissionSearchRequestSO.getUserId());
                throw new ValidationException(
                        UtilConstants.ERROR_CODE_TransformationException,
                        "Invalid User ID received : "
                                + getuserPermissionSearchRequestSO.getUserId());
            }
        } catch (ValidationException ve) {
            ErrorVO errorVO = new ErrorVO();
            errorVO.setErrorCode(ve.getErrorCode());
            errorVO.setErrorMessage(ve.getField());
            return errorVO;
        }

        UserPermissionSearchRequestASBO getUserPermissionSearchRequestASBO = new UserPermissionSearchRequestASBO();
        UserPermissionSearchResponseASBO getUserPermissionSearchResponseASBO = new UserPermissionSearchResponseASBO();
        getUserPermissionSearchRequestASBO
                .setHeader(getuserPermissionSearchRequestSO.getHeader());
        getUserPermissionSearchRequestASBO
                .setUserId(getuserPermissionSearchRequestSO.getUserId());
        try {
            getUserPermissionSearchResponseASBO = userDetailDao
                    .getUserSearchPermissionDetailsDB(getUserPermissionSearchRequestASBO);
        } catch (Exception e) {
            LOGGER.error("Exception Occurred--" + e.getStackTrace());
            ErrorVO errorVO = new ErrorVO();
            errorVO.setErrorCode(UtilConstants.ERROR_CODE_RUNTIME_TECHNICHALEXCEPTION);
            errorVO.setErrorMessage(e.getLocalizedMessage());
            return errorVO;
        }

        getUserPermissionSearchResponseASBO
                .setHeader(getuserPermissionSearchRequestSO.getHeader());
        LOGGER.info("****Reached in getUserPermissionSearchResponseASBO in UserPermissionIntegrationController***");
        return getUserPermissionSearchResponseASBO;
    }

}