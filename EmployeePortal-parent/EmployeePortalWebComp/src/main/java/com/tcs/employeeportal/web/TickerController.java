/**
 * 
 */
package com.tcs.employeeportal.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.employeeportal.asbo.emp.request.TickerRequestASBO;
import com.tcs.employeeportal.controller.TickerIntegrationController;
import com.tcs.employeeportal.exception.cmo.ErrorVO;
import com.tcs.employeeportal.exception.cmo.IntegrationTechnicalException;
import com.tcs.employeeportal.util.ExceptionUtil;
import com.tcs.employeeportal.util.UtilConstants;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;
import com.tcs.employeeportal.web.util.HttpHeaderUtil;

/**
 * @author 585226
 *
 */

@Component
@RestController
@RequestMapping("/TickerValue")
public class TickerController {
	
		/** The Constant LOGGER. */
		private static final Logger LOGGER = Logger
				.getLogger(TickerController.class);
		
		@RequestMapping(value = "/{player}", method = RequestMethod.GET)
		public String getMsg(@PathVariable String player) {
			
			return "" + player;
		}
		
		@RequestMapping(value = "/getTickerValues", method = RequestMethod.POST	)
		public ResponseEntity<EmployeePortalResponseObject> getIntermediariesValues(@RequestBody TickerRequestASBO requestASBO, HttpServletRequest request){
			long a =System.currentTimeMillis();
			LOGGER.info("The webservice getTickerValues starting time "+a);
			String memberOf=null;
			String firstname=null;
			String userid=null;
			
			try{
				memberOf = request.getHeader("memberOf");
				firstname = request.getHeader("firstname");
				userid = request.getHeader("userid");
				
				LOGGER.debug("TICKER member of is--> "+memberOf);
				LOGGER.debug("TICKER userid of is--> "+userid);
				LOGGER.debug("TICKER firstname of is--> "+firstname);
				
			}
			catch(Exception e){
				ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO(e);
				LOGGER.error("ERROR in executing the TICKER "+e.getStackTrace());
				return new ResponseEntity<EmployeePortalResponseObject>(errorVO,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
			try {
				requestASBO.setHeader(httpHeaderUtil.generateSpringHeaderLogin(userid,firstname,memberOf,UtilConstants.GET_TICKER_VALUES));
			} catch (IntegrationTechnicalException integrationTechnicalException) {
				ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
				return new ResponseEntity<EmployeePortalResponseObject>(errorVO,
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			LOGGER.debug("TICKER requestASBO.getHeader().getGroups() of is--> "+requestASBO.getHeader().getGroups());
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("getIntermediariesValues service completed");
			}



			return new ResponseEntity<EmployeePortalResponseObject>(
					new TickerIntegrationController().getTickerValues(requestASBO),HttpStatus.OK);

		}

		
}
