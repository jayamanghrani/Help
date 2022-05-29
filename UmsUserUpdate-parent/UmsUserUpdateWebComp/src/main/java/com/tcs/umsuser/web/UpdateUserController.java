package com.tcs.umsuser.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.umsuser.controller.UpdateUserIntegrationController;
import com.tcs.umsuser.request.UpdateUserRequestSO;
import com.tcs.umsuser.util.UtilConstants;
import com.tcs.umsuser.vo.cmo.UmsResponseObject;
import com.tcs.umsuser.web.util.HttpHeaderUtil;

@Component
@RestController
@RequestMapping("/public/user")
public class UpdateUserController {
    
        /** The Constant LOGGER. */
        private static final Logger LOGGER = Logger.getLogger(UpdateUserController.class);
        
        @RequestMapping(value = "/update", method = RequestMethod.POST)
        public ResponseEntity<UmsResponseObject> updateUser(@RequestBody UpdateUserRequestSO requestSO,HttpServletRequest httpServletRequest) {
            LOGGER.info("Inside updateUser method ---- "+ requestSO.toString());
            HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
            try {
            	requestSO.setHeader(httpHeaderUtil
                        .generateSpringHeader( UtilConstants.UPDATE_USR_TEMP));
            } catch (Exception ex) {
                LOGGER.error("Exception inside updateUser",ex);
            }
            return new ResponseEntity<>(
                    new UpdateUserIntegrationController().updateUser(requestSO),
                    HttpStatus.OK); 
        }
        
}
