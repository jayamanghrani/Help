package com.tcs.umsapp.search.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.tcs.umsapp.search.exception.cmo.ErrorVO;
import com.tcs.umsapp.search.exception.cmo.ValidationException;
import com.tcs.umsapp.search.persist.dao.RequestTrackDao;
import com.tcs.umsapp.search.persist.dao.RequestTrackDaoImpl;
import com.tcs.umsapp.search.request.RequestTrackAppRoleDBRequestASBO;
import com.tcs.umsapp.search.request.RequestTrackDBReqeustASBO;
import com.tcs.umsapp.search.request.RequestTrackXLSRequestASBO;
import com.tcs.umsapp.search.request.UserRequestIdASBO;
import com.tcs.umsapp.search.so.request.RequestTrackAppRoleRequestSO;
import com.tcs.umsapp.search.so.request.RequestTrackDetailRequestSO;
import com.tcs.umsapp.search.so.request.RequestTrackXLSRequestSO;
import com.tcs.umsapp.search.so.request.UserRequestId;
import com.tcs.umsapp.search.so.response.RequestTrackAppRoleResponseSO;
import com.tcs.umsapp.search.so.response.RequestTrackDetailResponseSO;
import com.tcs.umsapp.search.util.UtilConstants;
import com.tcs.umsapp.search.util.ValidationUtil;
import com.tcs.umsapp.search.vo.cmo.Header;
import com.tcs.umsapp.search.vo.cmo.UmsappResponseObject;
import com.tcs.umsapp.serach.response.RequestTrackAppRoleDBResponseASBO;
import com.tcs.umsapp.serach.response.RequestTrackDBResponseASBO;
import com.tcs.umsapp.serach.response.RequestTrackXLSResponseASBO;

public class RequestTrackController {

    private static final Logger LOGGER = Logger
            .getLogger(RequestTrackController.class);

    private RequestTrackDao requestTrackDao;

    public RequestTrackController() {

        requestTrackDao = new RequestTrackDaoImpl();
    }

    /**
     * This Method is used for service getRequestTrackerDetailPost
     * 
     * @param requestTrackDetailRequestSO
     * @param loginUserId 
     * @return
     */
    public UmsappResponseObject getRequestTrackDetail(
            RequestTrackDetailRequestSO requestTrackDetailRequestSO, String loginUserId) {

        RequestTrackDBResponseASBO requestTrackDBResponseASBO;
        RequestTrackDetailResponseSO requestTrackDetailResponseSO;
        boolean dataFlag = false;
        try {

            if (requestTrackDetailRequestSO.getRequestId() != null
                    && !requestTrackDetailRequestSO.getRequestId().isEmpty()) {
                dataFlag = true;
                if (!ValidationUtil
                        .validateRequestID(requestTrackDetailRequestSO
                                .getRequestId())) {
                    LOGGER.error("Invalid Request ID : "
                            + requestTrackDetailRequestSO.getRequestId());
                    throw new ValidationException(
                            UtilConstants.ERROR_CODE_TransformationException,
                            "Request ID");
                } else {
                    LOGGER.debug("Valid Request ID : "
                            + requestTrackDetailRequestSO.getRequestId());
                }
            }
            if (requestTrackDetailRequestSO.getUserId() != null
                    && !requestTrackDetailRequestSO.getUserId().isEmpty()) {
                dataFlag = true;
                if (!ValidationUtil.validateUserID(requestTrackDetailRequestSO
                        .getUserId())) {
                    LOGGER.error("Invalid User ID : "
                            + requestTrackDetailRequestSO.getUserId());
                    throw new ValidationException(
                            UtilConstants.ERROR_CODE_TransformationException,
                            "User ID");
                } else {
                    LOGGER.debug("Valid User ID : "
                            + requestTrackDetailRequestSO.getUserId());
                }
            }
            if (requestTrackDetailRequestSO.getRequestBy() != null
                    && !requestTrackDetailRequestSO.getRequestBy().isEmpty()) {
                dataFlag = true;
                if (!ValidationUtil.validateUserID(requestTrackDetailRequestSO
                        .getRequestBy())) {
                    LOGGER.error("Invalid ID for Requested By : "
                            + requestTrackDetailRequestSO.getRequestBy());
                    throw new ValidationException(
                            UtilConstants.ERROR_CODE_TransformationException,
                            "Requested By");
                } else {
                    LOGGER.debug("Valid ID for Requested By : "
                            + requestTrackDetailRequestSO.getRequestBy());
                }
            }
            if (requestTrackDetailRequestSO.getRequestStatus() != null
                    && !requestTrackDetailRequestSO.getRequestStatus()
                            .isEmpty()) {
                dataFlag = true;
                if (!ValidationUtil
                        .validatRequestStatus(requestTrackDetailRequestSO
                                .getRequestStatus())) {
                    LOGGER.error("Invalid Request Status : "
                            + requestTrackDetailRequestSO.getRequestStatus());
                    throw new ValidationException(
                            UtilConstants.ERROR_CODE_TransformationException,
                            "Request Status");
                } else {
                    LOGGER.debug("Valid Request Status : "
                            + requestTrackDetailRequestSO.getRequestStatus());
                }
            }
            if (requestTrackDetailRequestSO.getApplication() != null
                    && !requestTrackDetailRequestSO.getApplication().isEmpty()) {
                dataFlag = true;
                if (!ValidationUtil
                        .validateApplication(requestTrackDetailRequestSO
                                .getApplication())) {
                    LOGGER.error("Invalid Application : "
                            + requestTrackDetailRequestSO.getApplication());
                    throw new ValidationException(
                            UtilConstants.ERROR_CODE_TransformationException,
                            "Application");
                } else {
                    LOGGER.debug("Valid Application : "
                            + requestTrackDetailRequestSO.getApplication());
                }
            }

            int requestDateFlag = 0;
            if (requestTrackDetailRequestSO.getRequestDateFrom() != null
                    && !requestTrackDetailRequestSO.getRequestDateFrom()
                            .isEmpty()) {
                dataFlag = true;
                if (!ValidationUtil.validateDate(requestTrackDetailRequestSO
                        .getRequestDateFrom())) {
                    LOGGER.error("Invalid Request From Date : "
                            + requestTrackDetailRequestSO.getRequestDateFrom());
                    throw new ValidationException(
                            UtilConstants.ERROR_CODE_TransformationException,
                            "Request From Date");
                } else {
                    LOGGER.debug("Valid Request From Date : "
                            + requestTrackDetailRequestSO.getRequestDateFrom());
                    requestDateFlag++;
                }
            }
            if (requestTrackDetailRequestSO.getRequestDateTo() != null
                    && !requestTrackDetailRequestSO.getRequestDateTo()
                            .isEmpty()) {
                dataFlag = true;
                if (!ValidationUtil.validateDate(requestTrackDetailRequestSO
                        .getRequestDateTo())) {
                    LOGGER.error("Invalid Request To Date : "
                            + requestTrackDetailRequestSO.getRequestDateTo());
                    throw new ValidationException(
                            UtilConstants.ERROR_CODE_TransformationException,
                            "Request To Date");
                } else {
                    LOGGER.debug("Valid Request To Date : "
                            + requestTrackDetailRequestSO.getRequestDateTo());
                    requestDateFlag++;
                }
            }

            if (requestDateFlag == 2) {
                SimpleDateFormat sdf = new SimpleDateFormat(
                        UtilConstants.datePattern);
                try {
                    Date reqDateFrom = sdf.parse(requestTrackDetailRequestSO
                            .getRequestDateFrom());
                    Date reqDateTo = sdf.parse(requestTrackDetailRequestSO
                            .getRequestDateTo());
                    if (reqDateFrom.compareTo(reqDateTo) > 0) {
                        LOGGER.error("Date from which data is requested exceeds date to which data is requested");
                        ErrorVO errorVO = new ErrorVO();
                        errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
                        errorVO.setErrorMessage("Date from which data is requested exceeds date to which data is requested");
                        return errorVO;
                    }
                } catch (ParseException e) {
                    LOGGER.error("Error while parsing dates for Request Track");
                    ErrorVO errorVO = new ErrorVO();
                    errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
                    errorVO.setErrorMessage("Error while parsing dates for Request Track");
                    return errorVO;
                }
            } else if (requestDateFlag == 1
                    && (requestTrackDetailRequestSO.getRequestDateFrom() == null || requestTrackDetailRequestSO
                            .getRequestDateFrom().isEmpty())) {
                LOGGER.error("Missing Request Date From");
                ErrorVO errorVO = new ErrorVO();
                errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
                errorVO.setErrorMessage("Missing Request Date From");
                return errorVO;
            }

            if (!dataFlag) {
                LOGGER.error("No criteria specified for search request");
                ErrorVO errorVO = new ErrorVO();
                errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
                errorVO.setErrorMessage("No criteria specified for search");
                return errorVO;
            }
        } catch (ValidationException ve) {
            ErrorVO errorVO = new ErrorVO();
            errorVO.setErrorCode(ve.getErrorCode());
            errorVO.setErrorMessage("Invalid value for the field : "
                    + ve.getField());
            return errorVO;
        }

        LOGGER.info(" *** Indside getRequestTrackDetail *** ");

        /* Transformation from SO to ASBO */
        Header header = requestTrackDetailRequestSO.getHeader();
        RequestTrackDBReqeustASBO requestTrackDBReqeustASBO = new RequestTrackDBReqeustASBO();

        requestTrackDBReqeustASBO.setRequestBy(requestTrackDetailRequestSO
                .getRequestBy());
        requestTrackDBReqeustASBO
                .setRequestDateFrom(requestTrackDetailRequestSO
                        .getRequestDateFrom());
        
        if(null!=requestTrackDetailRequestSO.getRequestDateTo() && !requestTrackDetailRequestSO.getRequestDateTo().isEmpty()){
	        SimpleDateFormat sdf = new SimpleDateFormat(
	                UtilConstants.datePattern);
	        try {
				Date reqDateTo = sdf.parse(requestTrackDetailRequestSO
				        .getRequestDateTo());
				
				Calendar calendar=Calendar.getInstance();
				calendar.setTime(reqDateTo);
				calendar.add(Calendar.DATE, 1);
				
				requestTrackDetailRequestSO.setRequestDateTo(sdf.format(calendar.getTime()));
				LOGGER.info("-------------- To Date ---------- " + requestTrackDetailRequestSO.getRequestDateTo() );
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
        }
        
        requestTrackDBReqeustASBO.setRequestDateTo(requestTrackDetailRequestSO
                .getRequestDateTo());
        requestTrackDBReqeustASBO.setRequestId(requestTrackDetailRequestSO
                .getRequestId());
        requestTrackDBReqeustASBO.setRequestStatus(requestTrackDetailRequestSO
                .getRequestStatus());
        requestTrackDBReqeustASBO.setUserId(requestTrackDetailRequestSO
                .getUserId());
        requestTrackDBReqeustASBO.setApplication(requestTrackDetailRequestSO
                .getApplication());
        LOGGER.info("RequestTrackDBReqeustASBO value: "
                + requestTrackDBReqeustASBO.toString());

        String loginUserBranchId=requestTrackDao.getLoginUserBranch(loginUserId);
        /* DataBase Call through dao class */
        
        requestTrackDBResponseASBO = requestTrackDao
                .getRequestTrackDetails(requestTrackDBReqeustASBO , loginUserBranchId);

        LOGGER.info("RequestTrackDBResponseASBO value: "
                + requestTrackDBResponseASBO.toString());
        /* Transformation from ASBO to SO */
        requestTrackDetailResponseSO = new RequestTrackDetailResponseSO();
        requestTrackDetailResponseSO.setHeader(header);
        requestTrackDetailResponseSO.setList(requestTrackDBResponseASBO
                .getList());

        return requestTrackDetailResponseSO;

    }

    /**
     * This Method is used for service getRequestTrackerAppRoleDetailPost
     * 
     * @param requestTrackAppRoleRequestSO
     * @return
     */
    public UmsappResponseObject getRequestTrackAppRoleDetail(
            RequestTrackAppRoleRequestSO requestTrackAppRoleRequestSO) {
        RequestTrackAppRoleDBResponseASBO requestTrackAppRoleDBResponseASBO;
        RequestTrackAppRoleResponseSO requestTrackAppRoleResponseSO;
        LOGGER.info(" *** Indside getRequestTrackAppRoleDetail *** " + requestTrackAppRoleRequestSO.toString());
        Header header = requestTrackAppRoleRequestSO.getHeader();

        try {
            if (!ValidationUtil.validateUserID(requestTrackAppRoleRequestSO
                    .getUserId())) {
                LOGGER.error("Invalid UserID : "
                        + requestTrackAppRoleRequestSO.getUserId());
                throw new ValidationException(
                        UtilConstants.ERROR_CODE_TransformationException,
                        "Invalid UserID received");
            } else if (!ValidationUtil
                    .validateRequestID(requestTrackAppRoleRequestSO
                            .getRequestId())) {
                LOGGER.error("Invalid Request ID : "
                        + requestTrackAppRoleRequestSO.getRequestId());
                throw new ValidationException(
                        UtilConstants.ERROR_CODE_TransformationException,
                        "Invalid RequestID received");
            }

            else {
                LOGGER.info("Validated user and request ID");
            }
        } catch (ValidationException ve) {
            ErrorVO errorVO = new ErrorVO();
            errorVO.setErrorCode(ve.getErrorCode());
            errorVO.setErrorMessage(ve.getField());
            return errorVO;
        }

        /* Transformation from SO to ASBO */
        RequestTrackAppRoleDBRequestASBO requestTrackAppRoleDBRequestASBO = new RequestTrackAppRoleDBRequestASBO();
        requestTrackAppRoleDBRequestASBO.setUserId(requestTrackAppRoleRequestSO
                .getUserId());
        requestTrackAppRoleDBRequestASBO
                .setRequestId(requestTrackAppRoleRequestSO.getRequestId());
        requestTrackAppRoleDBRequestASBO.setStatus(requestTrackAppRoleRequestSO.getStatus());
        LOGGER.info("requestTrackAppRoleDBRequestASBO value: "
                + requestTrackAppRoleDBRequestASBO.toString());

        /* DataBase Call through dao class */
        requestTrackAppRoleDBResponseASBO = requestTrackDao
                .getRequestTrackAppRoleDetails(requestTrackAppRoleDBRequestASBO);

        LOGGER.info("RequestTrackAppRoleDBResponseASBO value: "
                + requestTrackAppRoleDBResponseASBO.toString());

        /* Transformation from ASBO to SO */
        requestTrackAppRoleResponseSO = new RequestTrackAppRoleResponseSO();
        requestTrackAppRoleResponseSO.setHeader(header);
        requestTrackAppRoleResponseSO.setList(requestTrackAppRoleDBResponseASBO
                .getList());

        return requestTrackAppRoleResponseSO;
    }

    /**
     * 
     * @param requestTrackXLSRequestSO
     * @return
     */
    public UmsappResponseObject getRequestTrackerXLS(
            RequestTrackXLSRequestSO requestTrackXLSRequestSO) {
        RequestTrackXLSResponseASBO requestTrackXLSResponseASBO = new RequestTrackXLSResponseASBO();
        LOGGER.info(" *** Indside getRequestTrackXLSDetail *** ");

        try {
            if (!ValidationUtil.validateList(requestTrackXLSRequestSO
                    .getUserRequestId())) {
                LOGGER.error("Invalid Request ID List : "
                        + requestTrackXLSRequestSO.getUserRequestId());
                throw new ValidationException(
                        UtilConstants.ERROR_CODE_TransformationException,
                        "Empty/Null list received.");
            }
            for (UserRequestId userReq : requestTrackXLSRequestSO
                    .getUserRequestId()) {
                if (!ValidationUtil.validateUserID(userReq.getUserId())) {
                    LOGGER.error("Invalid UserID : " + userReq.getUserId());
                    throw new ValidationException(
                            UtilConstants.ERROR_CODE_TransformationException,
                            "Invalid UserID received : " + userReq.getUserId());
                } else if (!ValidationUtil.validateRequestID(userReq
                        .getRequestId())) {
                    LOGGER.error("Invalid Request ID : "
                            + userReq.getRequestId());
                    throw new ValidationException(
                            UtilConstants.ERROR_CODE_TransformationException,
                            "Invalid RequestID received : "
                                    + userReq.getRequestId());
                }
            }
        } catch (ValidationException ve) {
            ErrorVO errorVO = new ErrorVO();
            errorVO.setErrorCode(ve.getErrorCode());
            errorVO.setErrorMessage(ve.getField());
            return errorVO;
        }

        Header header = requestTrackXLSRequestSO.getHeader();

        RequestTrackXLSRequestASBO requestTrackXLSRequestASBO = new RequestTrackXLSRequestASBO();

        ArrayList<UserRequestIdASBO> rlist = new ArrayList<>();

        for (UserRequestId UserRequestId : requestTrackXLSRequestSO
                .getUserRequestId()) {
            LOGGER.info("inside for loop");
            UserRequestIdASBO userRequestIdASBO = new UserRequestIdASBO();

            userRequestIdASBO.setUserId(UserRequestId.getUserId());
            userRequestIdASBO.setRequestId(UserRequestId.getRequestId());

            rlist.add(userRequestIdASBO);
        }

        requestTrackXLSRequestASBO.setHeader(header);
        requestTrackXLSRequestASBO.setUserRequestId(rlist);

        LOGGER.info("userRequestIdASBO" + requestTrackXLSRequestASBO.toString());

        try {
            requestTrackXLSResponseASBO = requestTrackDao
                    .getRequestTrackXLS(requestTrackXLSRequestASBO);
        } catch (Exception e) {
            LOGGER.info("Error occurred" + e.getStackTrace());
        }
        requestTrackXLSResponseASBO.setHeader(header);

        LOGGER.info("before return statement of requestTrackXLSRequestASBO---"
                + requestTrackXLSResponseASBO);
        return requestTrackXLSResponseASBO;

    }

}