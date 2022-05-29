package com.tcs.smsmail.web;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tcs.umsapp.smsmail.controller.SmsMailIntegrationController;
import com.tcs.umsapp.smsmail.so.request.MailContentRequestSO;
import com.tcs.umsapp.smsmail.so.request.SmsContentRequestSO;
import com.tcs.umsapp.smsmail.so.response.MailDeliveryResponseSO;
import com.tcs.umsapp.smsmail.so.response.SmsDeliveryResponseSO;

@Component
@RestController
@RequestMapping("/communicate")
public class SmsMailController {

    private static final Logger LOGGER = Logger
            .getLogger(SmsMailController.class);
    /**
     * 
     * @param mailContentRequestSO
     * @param request
     * @return
     */
    @RequestMapping(value = "/sendMail", method = RequestMethod.POST)
    public ResponseEntity<MailDeliveryResponseSO> sendMail(
            @RequestBody MailContentRequestSO mailContentRequestSO,
            HttpServletRequest request) {
        LOGGER.debug("Inside sendMail Web controller : " + mailContentRequestSO);
        return new ResponseEntity<>(
                new SmsMailIntegrationController()
                .sendMailController(mailContentRequestSO), HttpStatus.OK);
    }
    /**
     * 
     * @param smsContentRequestSO
     * @param request
     * @return
     */
    @RequestMapping(value = "/sendSms", method = RequestMethod.POST)
    public ResponseEntity<SmsDeliveryResponseSO> sendSms(
            @RequestBody SmsContentRequestSO smsContentRequestSO,
            HttpServletRequest request) {
        LOGGER.debug("Inside sendMail Web controller : " + smsContentRequestSO);
        return new ResponseEntity<>(new SmsMailIntegrationController().sendSmsController(smsContentRequestSO),
                HttpStatus.OK);
    }
}