package com.tcs.umsrole.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.umsapp.role.controller.RoleIntegrationController;
import com.tcs.umsrole.exception.cmo.ErrorVO;
import com.tcs.umsrole.so.request.AcknowledgeRequestSo;
import com.tcs.umsrole.so.request.GetRoleRequestSO;
import com.tcs.umsrole.util.ExceptionUtil;
import com.tcs.umsrole.vo.cmo.UmsRoleResponseObject;
import com.tcs.umsrole.vo.util.UtilConstants;
import com.tcs.umsrole.web.util.HttpHeaderUtil;

@Component
@RestController
@RequestMapping("/roleManagement")
public class RoleController {
    private static final Logger LOGGER = Logger
            .getLogger(RoleController.class);

    /**
     * 
     *  Request mapping for roleupdate */

    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    public ResponseEntity<UmsRoleResponseObject> updateRole(
            @RequestBody GetRoleRequestSO getRoleRequestSO,
            HttpServletRequest request) {
        LOGGER.info(" variable service roleupdate set ");

        HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();

        String userId = null;

        userId = request.getHeader("loginID");

        try {
            getRoleRequestSO.setHeader(httpHeaderUtil.generateSpringHeader(
                    userId,UtilConstants.GET_SEARCH_DETAIL));

        } catch (Exception Ex) {
            ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO();
            return new ResponseEntity<>(errorVO,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.info(" *** Service Ends *** " + getRoleRequestSO.toString());
        return new ResponseEntity<>(
                new RoleIntegrationController()
                        .processUpdateRole(getRoleRequestSO),
                HttpStatus.OK);

    }
    
    /**
     * 
     * @param acknowledgeRequestSo
     * @return
     */
    @RequestMapping(value = "/acknowledgeRequest", method = RequestMethod.POST)
    public ResponseEntity<UmsRoleResponseObject> acknowledgeRequest(
            @RequestBody AcknowledgeRequestSo acknowledgeRequestSo) {
        LOGGER.info(" variable service roleupdate set ");

             LOGGER.info(" *** Service Ends *** " + acknowledgeRequestSo.toString());
        return new ResponseEntity<>(
                new RoleIntegrationController()
                        .acknowledgeRequest(acknowledgeRequestSo),
                HttpStatus.OK);

    }
 
    /**
     * 
     * @param getRoleRequestSO
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateXLRole", method = RequestMethod.POST)
    public ResponseEntity<UmsRoleResponseObject> updateXLRole(
            @RequestBody GetRoleRequestSO getRoleRequestSO,
            HttpServletRequest request) {
        LOGGER.info(" variable service roleupdate set ");

        HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();

        String userId = null;

        userId = request.getHeader("loginID");

        try {
            getRoleRequestSO.setHeader(httpHeaderUtil.generateSpringHeader(
                    userId, UtilConstants.GET_SEARCH_DETAIL));

        } catch (Exception Ex) {
            ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO();
            return new ResponseEntity<>(errorVO,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.info(" *** Service Ends *** " + getRoleRequestSO.toString());
        return new ResponseEntity<>(
                new RoleIntegrationController()
                        .updateXLRole(getRoleRequestSO),
                HttpStatus.OK);

    }
    
    
    
}
