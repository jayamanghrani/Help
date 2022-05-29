package com.tcs.umsoid.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.umsoid.exception.cmo.ErrorVO;
import com.tcs.umsoid.ldap.UserOidMapIntegrationController;
import com.tcs.umsoid.so.request.OIDRoleUpdateRequestSO;
import com.tcs.umsoid.so.request.UserLdapDetailsRequestSO;
import com.tcs.umsoid.util.ExceptionUtil;
import com.tcs.umsoid.util.UtilConstants;
import com.tcs.umsoid.vo.cmo.UmsappResponseObject;
import com.tcs.umsoid.web.util.HttpHeaderUtil;

@Component
@RestController
@RequestMapping("/ldap")
public class UserOidController {

    private static final Logger LOGGER = Logger.getLogger("umsOIDLogger");

    /**
     * --Service to revoke all the roles from the Portal/OID
     * @param getuserLdapDetailsRequestSO
     * @param request
     * @return
     */
    @RequestMapping(value = "/revokeRolesRequest", method = RequestMethod.POST)
    public ResponseEntity<UmsappResponseObject> ldifRevokeRolesMapManagement(
            @RequestBody UserLdapDetailsRequestSO getuserLdapDetailsRequestSO,
            HttpServletRequest request) {

        LOGGER.info("variable service ldifappRoleMapManagement set");

        HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
        String userId = null;
        userId = request.getHeader("loginID");
        try {
            getuserLdapDetailsRequestSO.setHeader(httpHeaderUtil
                    .generateSpringHeader(userId,UtilConstants.GET_USER_LDAP_DETAILS));
        } catch (Exception Ex) {
            ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO(Ex);
            return new ResponseEntity<>(errorVO,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        UmsappResponseObject responseObject = new UserOidMapIntegrationController()
                .ldifRevokeRolesMapManagement(getuserLdapDetailsRequestSO);

        LOGGER.info(" *** UmsappResponseObject Service Ends *** "
                + responseObject.toString());

        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }

    /**
     * 
     * REST Service URI for OID Role updates Expects requestID, provisionID as
     * parameters Input type : JSON
     * 
     * @param oidRoleUpdateRequestSO
     *            - Service object, maps JSON input to bean for further
     *            processing
     * @param request
     *            - HttpServletRequest Object
     * @return Response containing status code and message for request execution
     */
    @RequestMapping(value = "/oidRoleUpdate", method = RequestMethod.POST)
    public ResponseEntity<UmsappResponseObject> oidRoleUpdate(
            @RequestBody OIDRoleUpdateRequestSO oidRoleUpdateRequestSO,
            HttpServletRequest request) {
        LOGGER.info("Request for OID Role update received");
        LOGGER.info("Request : " + oidRoleUpdateRequestSO.toString());
        return new ResponseEntity<>(
                new UserOidMapIntegrationController()
                        .mapOIDRequestAction(oidRoleUpdateRequestSO),
                HttpStatus.OK);

    }

}
