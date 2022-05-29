package com.tcs.umsapp.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.umsapp.search.controller.UserPermissionIntegrationController;
import com.tcs.umsapp.search.exception.cmo.ErrorVO;
import com.tcs.umsapp.search.so.request.GetContentPermissionRequestSO;
import com.tcs.umsapp.search.so.request.UserPermissionSearchRequestSO;
import com.tcs.umsapp.search.vo.cmo.UmsappResponseObject;
import com.tcs.umsapp.search.util.ExceptionUtil;
import com.tcs.umsapp.search.util.UtilConstants;
import com.tcs.umsapp.web.util.HttpHeaderUtil;

@Component
@RestController
@RequestMapping("/permission")
public class UserPermissionController {

    private static final Logger LOGGER = Logger
            .getLogger(UserPermissionController.class);
    /** 
     * 
     * @param getuserPermissionSearchRequestSO
     * @param request
     * @return
     */
    @RequestMapping(value = "/getUserPermission", method = RequestMethod.POST)
    public ResponseEntity<UmsappResponseObject> getUserPermissionSearchDetail(
            @RequestBody UserPermissionSearchRequestSO getuserPermissionSearchRequestSO,
            HttpServletRequest request) {

        LOGGER.info("getUserPermission--"
                + getuserPermissionSearchRequestSO.toString());
        HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
        String userId = null;
        userId = request.getHeader("loginID");
        try {
            getuserPermissionSearchRequestSO.setHeader(httpHeaderUtil
                    .generateSpringHeader(userId, 
                            UtilConstants.GET_USER_Permission_SEARCH_DETAIL));

        } catch (Exception ex) {
            ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO();
            return new ResponseEntity<>(errorVO,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        UmsappResponseObject responseObject = new UserPermissionIntegrationController()
                .getUserSearchPermissionDetails(getuserPermissionSearchRequestSO);
        LOGGER.info(" *** userPermissionSearch Service Ends *** "
                + responseObject.toString());
        return new ResponseEntity<>(responseObject,
                HttpStatus.OK);
    }
    
    @RequestMapping(value = "/getAllList", method = RequestMethod.POST)
    public ResponseEntity<UmsappResponseObject> getUserPermissionDetail(
            @RequestBody GetContentPermissionRequestSO getContentPermissionRequestSO,
            HttpServletRequest request) {
        LOGGER.info("User Permission Service Started with data "
                + getContentPermissionRequestSO.toString());
        HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
        String userId = null;
        userId = request.getHeader("loginID");
        try {
            getContentPermissionRequestSO.setHeader(httpHeaderUtil
                    .generateSpringHeader(userId, 
                            UtilConstants.GET_Permission_DETAIL));

        } catch (Exception ex) {
            ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO();
            return new ResponseEntity<>(errorVO,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        UmsappResponseObject responseObject = new UserPermissionIntegrationController()
                .getUserPermissionDetails(getContentPermissionRequestSO);
        LOGGER.info(" *** userSearch Service Ends *** "
                + responseObject.toString());
        return new ResponseEntity<>(responseObject,
                HttpStatus.OK);
    }
}