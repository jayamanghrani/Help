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

import com.tcs.umsapp.search.controller.RequestTrackController;
import com.tcs.umsapp.search.exception.cmo.ErrorVO;
import com.tcs.umsapp.search.so.request.RequestTrackAppRoleRequestSO;
import com.tcs.umsapp.search.so.request.RequestTrackDetailRequestSO;
import com.tcs.umsapp.search.so.request.RequestTrackXLSRequestSO;
import com.tcs.umsapp.search.util.ExceptionUtil;
import com.tcs.umsapp.search.util.UtilConstants;
import com.tcs.umsapp.search.vo.cmo.UmsappResponseObject;
import com.tcs.umsapp.web.util.HttpHeaderUtil;

@Component
@RestController
@RequestMapping("/requestTracker")
public class RequestTrackerController {

    private static final Logger LOGGER = Logger
            .getLogger(RequestTrackerController.class);

    /** 
     * This web service used for getting detail of Request track
     * @param requestTrackDetailRequestSO
     * @param request
     * @return
     */
    @RequestMapping(value = "/searchAll", method = RequestMethod.POST)
    public ResponseEntity<UmsappResponseObject> searchAll(
            @RequestBody RequestTrackDetailRequestSO requestTrackDetailRequestSO,
            HttpServletRequest request) {

        LOGGER.info(" *** getRequestTrackerDetailPost Service Start *** ");
        HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();

        String loginUserId = null;

        loginUserId = request.getHeader("loginID");

        try {
            requestTrackDetailRequestSO
                    .setHeader(httpHeaderUtil
                            .generateSpringHeader(
                            		loginUserId,
                                    com.tcs.umsapp.search.util.UtilConstants.GET_REQUEST_TRACK_DETAIL));

        } catch (Exception ex) {
            ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO();
            return new ResponseEntity<>(errorVO,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        UmsappResponseObject responseObject = new RequestTrackController()
                .getRequestTrackDetail(requestTrackDetailRequestSO , loginUserId);

        LOGGER.info("UmsappResponseObject value: " + responseObject.toString());
        LOGGER.info(" *** getRequestTrackerDetailPost Service Ends *** ");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    /** 
     * This web service user for getting Application Role or particular
     * @param requestTrackAppRoleRequestSO
     * @param request
     * @return
     */
    @RequestMapping(value = "/showDetails", method = RequestMethod.POST)
    public ResponseEntity<UmsappResponseObject> showDetails(
            @RequestBody RequestTrackAppRoleRequestSO requestTrackAppRoleRequestSO,
            HttpServletRequest request) {

        LOGGER.info(" *** getRequestTrackerAppRoleDetailPost Service Start *** ");
        HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
        String userId = null;
        userId = request.getHeader("loginID");
        try {
            requestTrackAppRoleRequestSO.setHeader(httpHeaderUtil
                    .generateSpringHeader(userId,
                            UtilConstants.GET_REQUEST_TRACK_APP_ROLE_DETAIL));

        } catch (Exception ex) {
            ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO();
            return new ResponseEntity<>(errorVO,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        UmsappResponseObject responseObject = new RequestTrackController()
                .getRequestTrackAppRoleDetail(requestTrackAppRoleRequestSO);

        LOGGER.info("UmsappResponseObject value: " + responseObject.toString());
        LOGGER.info(" *** getRequestTrackerAppRoleDetailPost Service Ends *** ");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }
    /** 
     * 
     * @param requestTrackXLSRequestSO
     * @param request
     * @return
     */
    @RequestMapping(value = "/getRequestTrackerXLS", method = RequestMethod.POST)
    public ResponseEntity<UmsappResponseObject> getRequestTrackerXLS(
            @RequestBody RequestTrackXLSRequestSO requestTrackXLSRequestSO,
            HttpServletRequest request) {

        LOGGER.info(" *** getRequestTrackerXLS Service Start *** ");

        LOGGER.info(" *** getRequestTrackerXLS Service Start *** "
                + requestTrackXLSRequestSO.toString());
        HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
        String userId = null;
        userId = request.getHeader("loginID");
        try {
            requestTrackXLSRequestSO.setHeader(httpHeaderUtil
                    .generateSpringHeader(userId,
                            UtilConstants.GET_REQUEST_TRACKER_XLS));
        } catch (Exception ex) {
            ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO();
            return new ResponseEntity<>(errorVO,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info(" *** getRequestTrackerXLS Service Ends *** ");
        return new ResponseEntity<>(
                new RequestTrackController()
                        .getRequestTrackerXLS(requestTrackXLSRequestSO),
                HttpStatus.OK);
    }
}