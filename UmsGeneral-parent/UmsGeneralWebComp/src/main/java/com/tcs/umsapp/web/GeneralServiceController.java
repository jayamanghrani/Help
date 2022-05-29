package com.tcs.umsapp.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.umsapp.general.controller.RoleDetailController;
import com.tcs.umsapp.general.vo.cmo.Header;
import com.tcs.umsapp.general.vo.cmo.UmsappResponseObject;
import com.tcs.umsapp.web.util.HttpHeaderUtil;

@Component
@RestController
@RequestMapping("/cache")
public class GeneralServiceController {
    public static final String GET_ROLE_DETAIL = "getRoleDetail";
    private static final Logger LOGGER = LoggerFactory
            .getLogger(GeneralServiceController.class);
    /**
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/getAppRoles", method = RequestMethod.POST)
    public ResponseEntity<UmsappResponseObject> getRoleDetail(
            HttpServletRequest request) {

        LOGGER.debug(" *** getRoleDetailPost Service Start *** ");

        HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
        String userId = null;

        userId = request.getHeader("loginID");

        Header header = httpHeaderUtil.generateSpringHeader(userId,GET_ROLE_DETAIL);

        return new ResponseEntity<>(
                new RoleDetailController().getRoleList(header , userId), HttpStatus.OK);

    }
}
