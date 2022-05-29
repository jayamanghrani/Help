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

import com.tcs.umsapp.general.vo.cmo.UmsappResponseObject;
import com.tcs.umsapp.unlock.controller.UnlockUserAccountController;
import com.tcs.umsapp.unlock.so.request.UnlockAccountRequestSO;
import com.tcs.umsapp.util.UtilConstants;
import com.tcs.umsapp.web.util.HttpHeaderUtil;

@Component
@RestController
@RequestMapping("/unlock")
public class UnlockAccountController {

    private static final Logger LOGGER = Logger
            .getLogger(UnlockAccountController.class);
    /**
     * 
     * @param unlockAccountRequestSO
     * @param request
     * @return
     */
    @RequestMapping(value = "/unlockAccount", method = RequestMethod.POST)
    public ResponseEntity<UmsappResponseObject> unlockAccount(
            @RequestBody UnlockAccountRequestSO unlockAccountRequestSO,
            HttpServletRequest request) {
        String userid = null;
        userid = request.getHeader("loginID");

        HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
        try {
            unlockAccountRequestSO.setHeader(httpHeaderUtil
                    .generateSpringHeader(userid,UtilConstants.UNLOCK_ACCOUNT));
        } catch (Exception ex) {
            LOGGER.error("Exception inside UnlockAccountController.unlockAccount");
        }
        return new ResponseEntity<>(
                new UnlockUserAccountController()
                        .unlockAccount(unlockAccountRequestSO),
                HttpStatus.OK);

    }

}
