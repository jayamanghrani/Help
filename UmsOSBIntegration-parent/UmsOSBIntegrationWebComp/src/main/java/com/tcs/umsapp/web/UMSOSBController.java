package com.tcs.umsapp.web;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tcs.umsapp.osb.controller.UMSOSBIntegrationController;
import com.tcs.umsapp.osb.request.OSBRequestSO;
import com.tcs.umsapp.osb.response.OSBResponseSO;

@Component
@RestController
@RequestMapping("/umstoosb")
public class UMSOSBController {

    private static final Logger LOGGER = Logger
            .getLogger(UMSOSBController.class);
    /**
     * 
     * @return
     */
    @RequestMapping(value = "/updateUserRole", method = RequestMethod.POST)
    public ResponseEntity<OSBResponseSO> sendRequestToOSB() {
        OSBResponseSO asbo = new OSBResponseSO();
        LOGGER.info("Request is called for requestToOSB");

        UMSOSBIntegrationController umsosbIntegrationController = new UMSOSBIntegrationController();
        umsosbIntegrationController.generateUpdateRequest();
        asbo.setStatus("S");

        return new ResponseEntity<>(asbo, HttpStatus.OK);
    }
    
    /**
     * 
     * @param osbRequestSO
     * @return
     */
    @RequestMapping(value = "/userRoleResponse", method = RequestMethod.POST)
    public ResponseEntity<OSBResponseSO> receiveResponseFromOSB(
            @RequestBody OSBRequestSO osbRequestSO) {
        OSBResponseSO asbo = new OSBResponseSO();

        LOGGER.info("Response is called for responseFromOSB"
                + osbRequestSO.toString());
        UMSOSBIntegrationController umsosbIntegrationController = new UMSOSBIntegrationController();
        umsosbIntegrationController.sendAcknowledgeRequest(osbRequestSO);
        asbo.setStatus("Request received");
        return new ResponseEntity<>(asbo, HttpStatus.OK);

    }
}