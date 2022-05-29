package com.tcs.employeeportal.web;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tcs.employeeportal.asbo.infopolicy.request.GetInfoPolicyDetailsRequestASBO;
import com.tcs.employeeportal.asbo.infopolicy.request.InfoPolicyAcceptRequestASBO;
import com.tcs.employeeportal.controller.InfoPolicyIntegrationController;
import com.tcs.employeeportal.exception.cmo.ErrorVO;
import com.tcs.employeeportal.exception.cmo.IntegrationTechnicalException;
import com.tcs.employeeportal.util.ExceptionUtil;
import com.tcs.employeeportal.util.UtilConstants;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;
import com.tcs.employeeportal.web.util.HttpHeaderUtil;

	/**
	 * @author 738796
	 *
	 */

	@Component
	@RestController
	@RequestMapping("/infoPolicy")

	public class InfoPolicyController {

		/** The Constant LOGGER. */
		private static final Logger LOGGER = Logger
				.getLogger(InfoPolicyController.class);


		
		@RequestMapping(value = "/getInfoPolicyDetails", method = RequestMethod.POST)
		public ResponseEntity<EmployeePortalResponseObject> getInfoPolicyDetails(HttpServletRequest httpServletRequest) {
			
			LOGGER.info("Inside getInfoPolicyDetails");
			HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
			String memberOf=null;
			String userid=null;
			String firstname=null;
		
			GetInfoPolicyDetailsRequestASBO getInfoPolicyDetailsRequestASBO=new GetInfoPolicyDetailsRequestASBO() ;
			try{
				memberOf = httpServletRequest.getHeader("memberOf");
				userid = httpServletRequest.getHeader("userid");
				firstname = httpServletRequest.getHeader("firstname");
				
				
				LOGGER.info("getInfoPolicyDetails member of is--> "+memberOf);
				LOGGER.info("getInfoPolicyDetails userid of is--> "+userid);
				LOGGER.info("getInfoPolicyDetails firstname of is--> "+firstname);

				}
			catch(Exception e){
				ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO(e);
				return new ResponseEntity<EmployeePortalResponseObject>(errorVO,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			try {
				getInfoPolicyDetailsRequestASBO.setHeader(httpHeaderUtil.generateSpringHeaderLogin(userid,firstname,memberOf,UtilConstants.GET_INFO_POLICY_DETAILS));
				LOGGER.debug("userid============================="+userid);
				getInfoPolicyDetailsRequestASBO.setUserId(userid);
				
				
				
				} catch (IntegrationTechnicalException integrationTechnicalException) 
			{
				ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
				return new ResponseEntity<EmployeePortalResponseObject>(errorVO,
						HttpStatus.INTERNAL_SERVER_ERROR);

			}
			LOGGER.info("getInfoPolicyDetails ends");
			return new ResponseEntity<EmployeePortalResponseObject>(
					new InfoPolicyIntegrationController().processGetInfoPolicyDetails(getInfoPolicyDetailsRequestASBO),
					HttpStatus.OK);
			
		}
		
		@RequestMapping(value="/getInfoAcceptPolicy", method=RequestMethod.POST)
		public ResponseEntity<EmployeePortalResponseObject> getInfoAcceptPolicy(HttpServletRequest httpServletRequest)
				{
			
			LOGGER.info("Inside getInfoAcceptPolicy");
			HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
					
			
			String memberOf=null;
			String userid=null;
			String firstname=null;
		
	
			InfoPolicyAcceptRequestASBO infoPolicyAcceptRequestASBO = new InfoPolicyAcceptRequestASBO();
			try{
				
				memberOf = httpServletRequest.getHeader("memberOf");
				userid = httpServletRequest.getHeader("userid");
				firstname = httpServletRequest.getHeader("firstname");
				}
			catch(Exception e){
				ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO(e);
				return new ResponseEntity<EmployeePortalResponseObject>(errorVO,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			try
			{
			
				infoPolicyAcceptRequestASBO.setHeader(httpHeaderUtil.generateSpringHeaderLogin(userid,firstname,memberOf, UtilConstants.GET_INFO_ACCEPT_POLICY));
				LOGGER.debug("userid============================="+userid);
				infoPolicyAcceptRequestASBO.setUserId(userid);
			}
			catch(IntegrationTechnicalException integrationTechnicalException)
			{
				ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
				return new ResponseEntity<EmployeePortalResponseObject>(errorVO,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
	
			LOGGER.info("getInfoAcceptPolicy ends");
			return new ResponseEntity<EmployeePortalResponseObject>(
					new InfoPolicyIntegrationController()
					.processGetInfoAcceptPolicy(infoPolicyAcceptRequestASBO),
					HttpStatus.OK);
			}	
		}
	











































