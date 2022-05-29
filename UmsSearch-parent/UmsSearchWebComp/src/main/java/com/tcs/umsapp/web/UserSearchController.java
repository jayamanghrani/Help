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
import com.tcs.umsapp.search.exception.cmo.ErrorVO;
import com.tcs.umsapp.search.so.request.GetContentRequestSO;
import com.tcs.umsapp.search.so.request.UserRoleSearchRequestSO;
import com.tcs.umsapp.search.so.request.UserRoleSearchRequestXLSSO;
import com.tcs.umsapp.search.util.ExceptionUtil;
import com.tcs.umsapp.search.util.UtilConstants;
import com.tcs.umsapp.search.vo.cmo.UmsappResponseObject;
import com.tcs.umsapp.web.util.HttpHeaderUtil;

@Component
@RestController
@RequestMapping("/search")
public class UserSearchController {

	private static final Logger logger = Logger
			.getLogger(UserSearchController.class);

	@RequestMapping(value = "/userSearch", method = RequestMethod.POST)
	public ResponseEntity<UmsappResponseObject> getUserDetail(
			@RequestBody GetContentRequestSO getContentRequestSO,
			HttpServletRequest request) {
		logger.info("User Search Service " + getContentRequestSO.toString());
		HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
		String userId = null;
		userId = request.getHeader("loginID");
		try {
			getContentRequestSO.setHeader(httpHeaderUtil.generateSpringHeader(
					userId, UtilConstants.GET_SEARCH_DETAIL));
		} catch (Exception ex) {
			ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO();
			return new ResponseEntity<>(errorVO,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("getContentRequestSO :" + getContentRequestSO);
		return new ResponseEntity<>(
				new UserSearchIntegrationController()
				.getUserDetails(getContentRequestSO),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/userRoleSearch", method = RequestMethod.POST)
	public ResponseEntity<UmsappResponseObject> getUserRoleDetail(
			@RequestBody UserRoleSearchRequestSO userRoleSearchRequestSO,
			HttpServletRequest request) {
		logger.info("User Role Search Service Started");
		HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
		String userId = null;
		userId = request.getHeader("loginID");
		try {
			userRoleSearchRequestSO.setHeader(httpHeaderUtil
					.generateSpringHeader(userId,
							UtilConstants.GET_USER_ROLE_SEARCH_DETAIL));
		} catch (Exception ex) {
			ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO();
			return new ResponseEntity<>(errorVO,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(
				new UserSearchIntegrationController()
				.getUserRoleDetails(userRoleSearchRequestSO),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/XLSexport", method = RequestMethod.POST)
	public ResponseEntity<UmsappResponseObject> getXLS(
			@RequestBody UserRoleSearchRequestXLSSO userRoleSearchRequestSO,
			HttpServletRequest request) {
		logger.info("User Role Search Service Started"
				+ userRoleSearchRequestSO.toString());
		HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
		String userId = null;
		userId = request.getHeader("loginID");
		try {
			userRoleSearchRequestSO.setHeader(httpHeaderUtil
					.generateSpringHeader(userId,
							UtilConstants.GET_xls_ROLE_SEARCH_DETAIL));
		} catch (Exception ex) {
			ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO();
			return new ResponseEntity<>(errorVO,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(
				new UserSearchIntegrationController()
				.getUserRoleDetailXLS(userRoleSearchRequestSO),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/getLoginDetails", method = RequestMethod.POST)
	public ResponseEntity<UmsappResponseObject> getUserLoginDetails(
			HttpServletRequest request) {
		String userId = null;
		userId = request.getHeader("loginID");
		return new ResponseEntity<>(
				new UserSearchIntegrationController().getLoginDetails(userId),
				HttpStatus.OK);
	}


	@RequestMapping(value = "/getOfficeCodes", method = RequestMethod.POST)
	public ResponseEntity<UmsappResponseObject> getOfficeCodes(HttpServletRequest request){
		String userId = null;
		userId = request.getHeader("loginID");
		logger.info("Inside get office codes method ------   " + userId);

		return new ResponseEntity<>(
				new UserSearchIntegrationController()
				.getOfficeCodeDetails(userId),
				HttpStatus.OK);
	}
}