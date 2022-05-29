package com.tcs.employeeportal.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.employeeportal.access.invoker.OAMOIDInvoker;
import com.tcs.employeeportal.asbo.login.request.ChangePasswordRequestASBO;
import com.tcs.employeeportal.asbo.login.request.LoginRequestASBO;
import com.tcs.employeeportal.asbo.login.response.LoginDetailsResponseASBO;
import com.tcs.employeeportal.controller.LoginIntegrationController;
import com.tcs.employeeportal.exception.cmo.ErrorVO;
import com.tcs.employeeportal.exception.cmo.IntegrationTechnicalException;
import com.tcs.employeeportal.util.ExceptionUtil;
import com.tcs.employeeportal.util.UtilConstants;
import com.tcs.employeeportal.util.ValidationUtil;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;
import com.tcs.employeeportal.web.util.HttpHeaderUtil;

/**
 * @author 738566
 *
 */

@Component
@RestController
@RequestMapping("/login")

public class LoginController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(LoginController.class);


	/**
	 * @param requestASBO
	 * @param request
	 * @return the cwiss links to be shown to the user
	 */
	@RequestMapping(value = "/getCwissLinks", method = RequestMethod.POST)
	public ResponseEntity<EmployeePortalResponseObject> getCwissLinks(@RequestBody LoginRequestASBO requestASBO, HttpServletRequest request) {
		LOGGER.info("Inside getCwissLinks");
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("getCwissLinks service invoked");
		}

		String memberOf=null;
		String firstname=null;
		String userid=null;
		
		try{

			memberOf = request.getHeader("memberOf");
			firstname = request.getHeader("firstname");
			userid = request.getHeader("userid");
		}
		catch(Exception e){
			ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO(e);
			return new ResponseEntity<EmployeePortalResponseObject>(errorVO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
			LOGGER.info("memberOf------"+memberOf);
		try {
			requestASBO.setHeader(httpHeaderUtil.generateSpringHeaderLogin(userid,firstname,memberOf,UtilConstants.LOGIN));
		} catch (IntegrationTechnicalException integrationTechnicalException) {
			ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
			return new ResponseEntity<EmployeePortalResponseObject>(errorVO,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("getCwissLinks service completed");
		}

		return new ResponseEntity<EmployeePortalResponseObject>(
				new LoginIntegrationController().processCwissLinks(requestASBO),HttpStatus.OK);

	}

	/**
	 * @param requestASBO
	 * @param request
	 * @return the last login date and pwd expiry date of the user
	 */
	@RequestMapping(value = "/getPwdDetails", method = RequestMethod.POST)
    public ResponseEntity<EmployeePortalResponseObject> getPwdDetails(HttpServletRequest request) {
        LOGGER.info("Inside getPwdDetails");
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("getPwdDetails service invoked");
        }
        StringBuilder dateNew = new StringBuilder();
        String memberOf=null;
        String firstname=null;
        String userid=null;
        String lastLoginDt=null;
        String pwdChangeDt=null;
        String prevLoginDt=null;
        try{
            memberOf = request.getHeader("memberOf");
            firstname = request.getHeader("firstname");
            userid = request.getHeader("userid");
            lastLoginDt = request.getHeader("lastLoginDate");
            pwdChangeDt= request.getHeader("Pwdchngdate");
            prevLoginDt=request.getHeader("prevLoginDate");
        }
        catch(Exception e){
            ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO(e);
            return new ResponseEntity<EmployeePortalResponseObject>(errorVO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();

        lastLoginDt=prevLoginDt;
        LOGGER.info("pwdchngdate------"+pwdChangeDt);
        LOGGER.info("lastLoginDt------"+lastLoginDt);

        LoginDetailsResponseASBO loginDetailsResponseASBO=new LoginDetailsResponseASBO();
        try {
            loginDetailsResponseASBO.setHeader(httpHeaderUtil.generateSpringHeaderLogin(userid,firstname,memberOf ,UtilConstants.PWD_DETAILS));
        } catch (IntegrationTechnicalException integrationTechnicalException) {
            ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
            return new ResponseEntity<EmployeePortalResponseObject>(errorVO,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String newLastLoginDt =null;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date pwdChangeDtNew= new Date();
        Date lastLoginDt2= new Date();

        String newpwdChangeDt =  pwdChangeDt.substring(0, pwdChangeDt.length()-1);
         if(!(null==lastLoginDt ||lastLoginDt.equalsIgnoreCase("NOT_FOUND"))){
        String logindate123 =  lastLoginDt.substring(0, lastLoginDt.indexOf('T'));
        
        String logindate456 =  lastLoginDt.substring(lastLoginDt.indexOf('T')+1, lastLoginDt.length()-1);
        dateNew.append(logindate123);
        dateNew.append(" ");
        dateNew.append(logindate456);
         }
        newLastLoginDt  = dateNew.toString();
        DateFormat df1New = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat dfNew = new SimpleDateFormat("yyyyMMddHHmmss");
        
        LOGGER.info("the newLastLoginDt ----->"+newLastLoginDt);

        try {
            
            pwdChangeDtNew = dfNew.parse(newpwdChangeDt);
            lastLoginDt2 = df1New.parse(newLastLoginDt);
        
        LOGGER.info("lastLoginDt2------"+lastLoginDt2);
        LOGGER.info("pwdChangeDtNew------"+pwdChangeDtNew);
        Calendar cal = Calendar.getInstance();
        cal.setTime(pwdChangeDtNew);
        cal.add(Calendar.DATE, 30); // add 30 days

        Date pwdExpiryDt = cal.getTime();
        String pwdExpiryDtNew=df.format(pwdExpiryDt);

        String lastLoginDtNew=df1.format(lastLoginDt2);
        LOGGER.info("lastLoginDtNew------"+lastLoginDtNew);
        
        loginDetailsResponseASBO.setLastLoginDt(lastLoginDtNew);
        loginDetailsResponseASBO.setPwdExpiryDt(pwdExpiryDtNew);
        LOGGER.info("pwdExpiryDtNew------"+pwdExpiryDtNew);
        } catch (ParseException e) {
            LOGGER.error(e.getStackTrace());

            e.printStackTrace();

        }
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("getPwdDetails service completed");
        }
        
        return new ResponseEntity<EmployeePortalResponseObject>(loginDetailsResponseASBO,HttpStatus.OK);

    }

	/**
	 * @param requestASBO
	 * @param request
	 * update the last login date of the user
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> logout(HttpServletRequest request) {
		LOGGER.info("Inside logout");
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("logout service invoked");
		}
		OAMOIDInvoker oamoidInvoker= new OAMOIDInvoker();

		String memberOf=null;
		String firstname=null;
		String userid=null;
		String lastLoginDt=null;
		String prevLoginDt=null;
		try{
			memberOf = request.getHeader("memberOf");
			firstname = request.getHeader("firstname");
			userid = request.getHeader("userid");
			lastLoginDt = request.getHeader("lastLoginDate");
			prevLoginDt=request.getHeader("prevLoginDate");
		}
		catch(Exception e){
			ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO(e);
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		String tempLoginDate=lastLoginDt;
				
		try {
			oamoidInvoker.lastLoginTime(userid,tempLoginDate);
		} catch (IntegrationTechnicalException e) {
			LOGGER.error(e);
			
		}
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("logout service completed");
		}
		
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);

	}

	
	/**
	 * Change password.
	 *
	 * @param changePasswordRequestASBO the change password request asbo
	 * @param httpServletRequest the http servlet request
	 * @return the response entity
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ResponseEntity<EmployeePortalResponseObject> changePassword(
			@RequestBody ChangePasswordRequestASBO changePasswordRequestASBO,
			HttpServletRequest request) {
		
		String memberOf=null;
		String firstname=null;
		String userid=null;
		
		try{
			memberOf = request.getHeader("memberOf");
			firstname = request.getHeader("firstname");
			userid = request.getHeader("userid");
			
			ErrorVO errorVO=ValidationUtil.validateUserHeader(userid, firstname, memberOf);
			if(errorVO.getErrorCode()!=0)
			{
				return new ResponseEntity<EmployeePortalResponseObject>(errorVO,
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		catch(Exception e){
			ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO(e);
			return new ResponseEntity<EmployeePortalResponseObject>(errorVO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
		try {
			changePasswordRequestASBO.setHeader(httpHeaderUtil.generateSpringHeaderLogin(userid,firstname,memberOf ,UtilConstants.CHANGE_PASSWORD));
		} catch (IntegrationTechnicalException integrationTechnicalException) {
			ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
			return new ResponseEntity<EmployeePortalResponseObject>(errorVO,
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<EmployeePortalResponseObject>(
				new LoginIntegrationController()
						.processChangePassword(changePasswordRequestASBO),
				HttpStatus.OK);
	}
}