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
import com.tcs.umsapp.search.controller.UserSearchIntegrationController;
import com.tcs.umsapp.search.so.request.UserRoleSearchRequestSO;
import com.tcs.umsapp.search.vo.cmo.UmsappResponseObject;

@Component
@RestController
@RequestMapping("/public")
public class PublicController {

    private static final Logger logger = Logger
            .getLogger(PublicController.class);
    /** 
     * 
     * @param userRoleSearchRequestSO
     * @param request
     * @return
     */
    @RequestMapping(value = "/userRoleSearch", method = RequestMethod.POST)
    public ResponseEntity<UmsappResponseObject> getUserRoleDetail(
            @RequestBody UserRoleSearchRequestSO userRoleSearchRequestSO,
            HttpServletRequest request) {

        logger.info("User Role Search Service Started");

        return new ResponseEntity<>(
                new UserSearchIntegrationController()
                        .getUserRoleDetails(userRoleSearchRequestSO),
                HttpStatus.OK);

    }
}
