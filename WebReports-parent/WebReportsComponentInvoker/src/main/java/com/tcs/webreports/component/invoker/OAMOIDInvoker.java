/**
 * 
 */
package com.tcs.webreports.component.invoker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchResult;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import oracle.security.am.asdk.AccessClient;
import oracle.security.am.asdk.AccessException;
import oracle.security.am.asdk.AuthenticationScheme;
import oracle.security.am.asdk.ResourceRequest;
import oracle.security.am.asdk.UserSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.tcs.webreports.exception.cmo.IntegrationTechnicalException;
import com.tcs.webreports.oamoid.asbo.request.ForgotPasswordOAMOIDRequestASBO;
import com.tcs.webreports.oamoid.asbo.request.GetChannelOAMOIDRequestASBO;
import com.tcs.webreports.oamoid.asbo.request.IsLoggedInOAMOIDRequestASBO;
import com.tcs.webreports.oamoid.asbo.request.LoginOAMOIDRequestASBO;
import com.tcs.webreports.oamoid.asbo.request.LogoutOAMOIDRequestASBO;
import com.tcs.webreports.oamoid.asbo.response.ForgotPasswordOAMOIDResponseASBO;
import com.tcs.webreports.oamoid.asbo.response.GetChannelOAMOIDResponseASBO;
import com.tcs.webreports.oamoid.asbo.response.IsLoggedInOAMOIDResponseASBO;
import com.tcs.webreports.oamoid.asbo.response.LoginOAMOIDResponseASBO;
import com.tcs.webreports.oamoid.asbo.response.LogoutOAMOIDResponseASBO;
import com.tcs.webreports.util.OAMOIDUtil;
import com.tcs.webreports.util.UtilConstants;
import com.tcs.webreports.vo.cmo.WebReportsRequestObject;
import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

/**
 * @author 738566
 *
 */
public class OAMOIDInvoker {

	  private static final Logger LOGGER = Logger.getLogger("webReportsLogger");
	  private AccessClient accessClient;
	  private String m_configLocation;
	  private String orcldefaultprofilegroup = null;
	  private String groupName = null;

	  public OAMOIDInvoker()
	  {
	    this.m_configLocation = OAMOIDUtil.JPS_LOCATION;
	//	  this.m_configLocation = "D:\\Kalpana\\NewPortalDocuments\\WebReports\\oam_conf\\";
	  }

	  private DirContext getInitialContext(String hostname, int port, String username, String password)
	    throws NamingException
	  {
	    String providerURL = new StringBuilder().append("ldap://").append(hostname).append(":").append(port).toString();

	    Properties props = new Properties();
	    props.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");

	    props.put("java.naming.provider.url", providerURL);
	    if ((username != null) && (!username.equals(""))) {
	      props.put("java.naming.security.authentication", "simple");
	      props.put("java.naming.security.principal", new StringBuilder().append("cn=").append(username).toString());

	      props.put("java.naming.security.credentials", password == null ? "" : password);
	    }

	    return new InitialDirContext(props);
	  }

	  private DirContext getValidInitialContext(String hostname, int port, String username, String password)
	    throws NamingException
	  {
	    String providerURL = new StringBuilder().append("ldap://").append(hostname).append(":").append(port).toString();

	    Properties props = new Properties();
	    props.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");

	    props.put("java.naming.provider.url", providerURL);
	    if ((username != null) && (!username.equals(""))) {
	      props.put("java.naming.security.authentication", "simple");
	      props.put("java.naming.security.principal", username);
	      props.put("java.naming.security.credentials", password == null ? "" : password);
	    }

	    return new InitialDirContext(props);
	  }

	  private void setupAccessClient()
	    throws AccessException
	  {
	    this.accessClient = AccessClient.createDefaultInstance(this.m_configLocation, AccessClient.CompatibilityMode.OAM_10G);
	  }

	  private WebReportsResponseObject authenticate(String resource, WebReportsRequestObject requestVO)
	    throws IntegrationTechnicalException
	  {
		  LoginOAMOIDRequestASBO loginOAMOIDRequestASBO;
	    if ((requestVO instanceof LoginOAMOIDRequestASBO))
	    	loginOAMOIDRequestASBO = (LoginOAMOIDRequestASBO)requestVO;
	    else
	      throw new IntegrationTechnicalException();
	 //   LoginOAMOIDRequestASBO loginOAMOIDRequestASBO;
	    String userId = loginOAMOIDRequestASBO.getUserId();
	    String password = loginOAMOIDRequestASBO.getPassword();
	   /* long pwdExpiryValue = Long.parseLong(loginOAMOIDRequestASBO.getPwdExpiryValue());

	    long pwdExpiryNotify = Long.parseLong(loginOAMOIDRequestASBO.getPwdExpiryNotification());*/

	    StringBuilder lastLoginDate = new StringBuilder();

	    LoginOAMOIDResponseASBO loginOAMOIDResponseASBO = new LoginOAMOIDResponseASBO();
	    try {
	      if ((this.accessClient == null) || (!this.accessClient.isInitialized())) {
	        setupAccessClient();
	      }
	      ResourceRequest rrq = new ResourceRequest("http", resource, "GET");
	      
	      if (rrq.isProtected())
	      {
	    	AuthenticationScheme authnScheme = new AuthenticationScheme(rrq);	//Code for authorization
	    	if (authnScheme.isForm()) {	//Code for authorization
	        Hashtable creds = new Hashtable();

	        creds.put("userid", userId);
	        creds.put("password", password);

	        UserSession session = new UserSession(rrq, creds);
	        String[] actionTypes = session.getActionTypes();

	        if (session.getStatus() == 1)
	        {
	        	/**Code for authorization starts**/
	        
	        	 if (session.isAuthorized(rrq)) {
                     System.out.println("User is logged in and authorized for the"
                           +"request at level " + session.getLevel());
                 
	        	/**Code for authorization ends**/
	          String userIdentity = session.getUserIdentity();
	          String[] userIdentityParts = userIdentity.split(",");
	          String[] cnParts = userIdentityParts[0].split("=");
	          String userId1 = cnParts[1].trim();
	          LOGGER.info(new StringBuilder().append("user Id from session : ").append(userId1).toString());

	          loginOAMOIDResponseASBO.setLoggedIn("true");
	          loginOAMOIDResponseASBO.setToken(session.getSessionToken());

	          int i = 0; for (int iMax = actionTypes.length; i < iMax; i++)
	          {
	            Hashtable actions = session.getActions(actionTypes[i]);
	            Enumeration e = actions.keys();
	            int item = 0;
	            LOGGER.info(new StringBuilder().append("Printing Actions for type ").append(actionTypes[i]).toString());

	            while (e.hasMoreElements()) {
	              String name = (String)e.nextElement();
	              System.out.println(new StringBuilder().append("Actions[").append(item).append("]: Name ").append(name).append("value ").append(actions.get(name)).toString());

	              item++;
	            }
	            String memberOf = (String)actions.get("memberOf");
	            String firstName = (String)actions.get("firstname");
	            String lastName = (String)actions.get("lastname");


	            if (!"null".equalsIgnoreCase((String)actions.get("oblastsuccessfullogin")))
	            {
	              lastLoginDate.append(actions.get("oblastsuccessfullogin"));

	              lastLoginDate.replace(10, 11, " ").replace(13, 14, "").replace(16, 17, "").replace(19, 20, "");

	              loginOAMOIDResponseASBO.setLastLoginDate(lastLoginDate.toString());
	            }
	            
	            loginOAMOIDResponseASBO.setMemberOf(memberOf);
	            loginOAMOIDResponseASBO.setFirstName(firstName);
	            loginOAMOIDResponseASBO.setLastName(lastName);
	            loginOAMOIDResponseASBO.setUserId(userId);
	            addLastLogin(userId);

	            LOGGER.info("firstname : " +firstName);
	            LOGGER.info("Login successful : " +memberOf);

	          }
	          /**Code for authorization starts**/
	        	 } else {
	        		 loginOAMOIDResponseASBO.setLoggedIn("false");
                     System.out.println("User is logged in but NOT authorized"+session.getError());
                     loginOAMOIDResponseASBO.setStatusMessage("Sorry, you are not authorized to view the page");
                     loginOAMOIDResponseASBO.setStatusCode(session.getError());
                     
                  }	
	        	 }
	        /**Code for authorization ends**/
	        else
	        {
	          loginOAMOIDResponseASBO.setLoggedIn("false");
	          if (session.getError() == 103) {
	            loginOAMOIDResponseASBO.setStatusMessage("Incorrect Username/Password, please retry.");
	          }
	          else if (session.getError() == 113) {
	            loginOAMOIDResponseASBO.setStatusMessage("Account Locked");
	          }

	          loginOAMOIDResponseASBO.setStatusCode(session.getError());
	        }
	      }
	    	/**Code for authorization starts**/
	      } else {
              System.out.println("non-Form Authentication Scheme.");
           }
	      /**Code for authorization ends**/
	    }
	    catch (AccessException ae) {
	      LOGGER.error(ae.getMessage(), ae);
	    }
	    return loginOAMOIDResponseASBO;
	  }

	  private HashMap checkStatus(HashMap userDetails)
	  {
	    if ((userDetails.get("uid") != null) && (!userDetails.get("uid").equals("NULL")))
	    {
	      if ((userDetails.get("obpasswordchangeflag").equals("true")) && (!userDetails.get("obpasswordchangeflag").equals("NULL")))
	      {
	        userDetails.put("error", Integer.valueOf(112));
	      } else if ((userDetails.get("obpasswordcreationdate") != null) && (!userDetails.get("obpasswordcreationdate").equals("NULL")))
	      {
	        StringBuilder passowrdCreationDate = new StringBuilder();
//	        System.out.println(passowrdCreationDate);
	        passowrdCreationDate.append(userDetails.get("obpasswordcreationdate"));

	        passowrdCreationDate.replace(10, 11, " ").replace(13, 14, "").replace(16, 17, "");

	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

	        Date pwdDate = null;
	        Date sysDate = null;
	        try {
	          pwdDate = dateFormat.parse(passowrdCreationDate.toString());
//	          System.out.println(new StringBuilder().append("-----------").append(pwdDate).toString());

	          sysDate = new Date();
//	          System.out.println(sysDate);
//	          System.out.println(dateFormat.format(sysDate));
	          sysDate = dateFormat.parse(dateFormat.format(sysDate));
	          long diff = sysDate.getTime() - pwdDate.getTime();
	          long diffSeconds = diff / 1000L % 60L;
	          long diffMinutes = diff / 60000L % 60L;
	          long diffHours = diff / 3600000L % 24L;
	          long diffDays = diff / 86400000L;
	          long remainingDays = ((Long)userDetails.get("pwdExpiryValue")).longValue() - diffDays;

	          StringBuilder expireTime = new StringBuilder();
	          expireTime.append(diffDays).append(" days, ").append(diffHours).append(" hours, ").append(diffMinutes).append(" minutes, ").append(diffSeconds).append(" seconds.");

	          if (remainingDays < ((Long)userDetails.get("pwdExpiryNotify")).longValue())
	          {
	            if (userDetails.get("error") == null)
	            {
	              userDetails.put("error", Integer.valueOf(112));
	              userDetails.put("remainingDays", String.valueOf(remainingDays));
	            }

	          }

	          if (diffDays > ((Long)userDetails.get("pwdExpiryValue")).longValue())
	          {
	            userDetails.put("error", Integer.valueOf(111));
	            userDetails.put("expireTime", expireTime);
	          }

	        }
	        catch (ParseException e)
	        {
	          LOGGER.error(new StringBuilder().append("Date Conversion exception : ").append(e.getMessage()).toString());
	        }
	      }

	    }

	    return userDetails;
	  }

	  private WebReportsResponseObject isLoggedIn(WebReportsRequestObject requestVO)
	    throws IntegrationTechnicalException
	  {
	    LOGGER.info(new StringBuilder().append(requestVO.getHeader().getCoRelationId()).append(" Entering isLoggedIn()").toString());

	    long start = System.currentTimeMillis();

	    IsLoggedInOAMOIDResponseASBO responseASBO = new IsLoggedInOAMOIDResponseASBO();
	    IsLoggedInOAMOIDRequestASBO requestASBO;
	    if ((requestVO instanceof IsLoggedInOAMOIDRequestASBO)) {
	      requestASBO = (IsLoggedInOAMOIDRequestASBO)requestVO;
	    } else {
	      LOGGER.info(new StringBuilder().append(requestVO.getHeader().getCoRelationId()).append(" requestVo not an instance of IsLoggedInOAMOIDRequestASBO").toString());

	      throw new IntegrationTechnicalException();
	    }
	 //   IsLoggedInOAMOIDRequestASBO requestASBO;
	    LOGGER.info(new StringBuilder().append(requestASBO.getHeader().getCoRelationId()).append(" IsLoggedInOAMOIDRequestASBO : ").append(new Gson().toJson(requestASBO)).toString());
	    try
	    {
	      if ((this.accessClient == null) || (!this.accessClient.isInitialized())) {
	        setupAccessClient();
	      }
	 
	      UserSession session = new UserSession(this.accessClient, requestASBO.getSessionToken());

	      String anonResource = OAMOIDUtil.OAMOID_URL;
	      
	    //  String anonResource ="//10.10.3.250:7013/BaNCSIntegrationWebComp/rest/policies/authenticateMe";

	      ResourceRequest rrq = new ResourceRequest("http", anonResource, "GET");

	      LOGGER.info(new StringBuilder().append(requestASBO.getHeader().getCoRelationId()).append(" is authorised : ").append(session.isAuthorized(rrq)).toString());

	      LOGGER.info(new StringBuilder().append("Session status ").append(session.getStatus()).toString());
	      if (session.getStatus() == 1) {
	        String userIdentity = session.getUserIdentity();
	        String[] userIdentityParts = userIdentity.split(",");
	        String[] cnParts = userIdentityParts[0].split("=");
	        String userId = cnParts[1].trim();
	        LOGGER.info(new StringBuilder().append(requestASBO.getHeader().getCoRelationId()).append(" user Id from session IsLoggedin: ").append(userId).toString());

	        if (userId.equalsIgnoreCase(requestASBO.getUserId())) {
	          responseASBO.setLoggedIn(true);
	        } else {
	          LOGGER.info(new StringBuilder().append(requestASBO.getHeader().getCoRelationId()).append(" Valid token but wrong user id.").toString());

	          session.logoff();
	          responseASBO.setLoggedIn(false);
	        }
	      } else {
	        LOGGER.info(new StringBuilder().append(requestASBO.getHeader().getCoRelationId()).append(" Token expired.").toString());

	        LOGGER.info(new StringBuilder().append(requestASBO.getHeader().getCoRelationId()).append(" Session Object : ").append(session.toString()).toString());

	        session.logoff();
	        responseASBO.setLoggedIn(false);
	      }
	    } catch (Exception ae) {
	      LOGGER.error(new StringBuilder().append(requestASBO.getHeader().getCoRelationId()).append(" ").append(ae.getMessage()).toString(), ae);

	      responseASBO.setLoggedIn(false);
	    }
	    LOGGER.info(new StringBuilder().append(requestASBO.getHeader().getCoRelationId()).append(" IsLoggedInOAMOIDResponseASBO : ").append(new Gson().toJson(responseASBO)).toString());

	    long end = System.currentTimeMillis();
	    LOGGER.info(new StringBuilder().append(requestASBO.getHeader().getCoRelationId()).append(" Exting isLoggedIn()").toString());

	    LOGGER.info(new StringBuilder().append(requestASBO.getHeader().getCoRelationId()).append(" Time taken to isLoggedIn()").append(end - start).append("ms").toString());

	    return responseASBO;
	  }

	  private WebReportsResponseObject logout(WebReportsRequestObject requestVO)
	    throws IntegrationTechnicalException
	  {
	    LOGGER.info("Entering logout()");
	    long start = System.currentTimeMillis();
	    LogoutOAMOIDResponseASBO responseASBO = new LogoutOAMOIDResponseASBO();
	    LogoutOAMOIDRequestASBO logoutOAMOIDRequestASBO = null;
	    if ((requestVO instanceof LogoutOAMOIDRequestASBO))
	      logoutOAMOIDRequestASBO = (LogoutOAMOIDRequestASBO)requestVO;
	    else {
	      throw new IntegrationTechnicalException();
	    }
	    String sessionToken = logoutOAMOIDRequestASBO.getSessionToken();
	    try {
	      if ((this.accessClient == null) || (!this.accessClient.isInitialized())) {
	        setupAccessClient();
	      }
	      UserSession session = new UserSession(this.accessClient, sessionToken);

	      if (session.getStatus() == 1) {
	        session.logoff();
	        responseASBO.setMessage("Logged out successfully");
	      }
	    } catch (Exception ae) {
	      responseASBO.setMessage(new StringBuilder().append("Logging out failed due to ").append(ae.getMessage()).toString());
	    }

	    long end = System.currentTimeMillis();
	    LOGGER.info("Exiting logout()");
	    LOGGER.info(new StringBuilder().append("Time taken to logout() :").append(end - start).append("ms").toString());
	    return responseASBO;
	  }

	  private WebReportsResponseObject getChannel(WebReportsRequestObject requestVO)
			    throws IntegrationTechnicalException
			  {
			    LOGGER.info(new StringBuilder().append(requestVO.getHeader().getCoRelationId()).append(" Entering GetChannel()").toString());

			    long start = System.currentTimeMillis();

			    GetChannelOAMOIDResponseASBO responseASBO = new GetChannelOAMOIDResponseASBO();
			    GetChannelOAMOIDRequestASBO requestASBO;
			    if ((requestVO instanceof GetChannelOAMOIDRequestASBO)) {
			      requestASBO = (GetChannelOAMOIDRequestASBO)requestVO;
			    } else {
			      LOGGER.info(new StringBuilder().append(requestVO.getHeader().getCoRelationId()).append(" requestVo not an instance of GetChannelOAMOIDRequestASBO").toString());

			      throw new IntegrationTechnicalException();
			    }
			 //   IsLoggedInOAMOIDRequestASBO requestASBO;
			    LOGGER.info(new StringBuilder().append(requestASBO.getHeader().getCoRelationId()).append(" GetChannelInOAMOIDRequestASBO : ").append(new Gson().toJson(requestASBO)).toString());
			    try
			    {
			      if ((this.accessClient == null) || (!this.accessClient.isInitialized())) {
			        setupAccessClient();
			      }
			   /*   String anonResource = OAMOIDUtil.OAMOID_URL;

			      ResourceRequest rrq = new ResourceRequest("http", anonResource, "GET");
			      Hashtable creds = new Hashtable();

			        creds.put("userid", "AG_UAT10");
			        creds.put("password", "Nia@12345");
*/
			    //    UserSession session = new UserSession(rrq, creds);
			 
			      UserSession session = new UserSession(this.accessClient, requestASBO.getSessionToken());
			     
			            
			      String[] actionTypes = session.getActionTypes();
			      String userIdentity = session.getUserIdentity();
			        String[] userIdentityParts = userIdentity.split(",");
			        String[] cnParts = userIdentityParts[0].split("=");
			        String userId = cnParts[1].trim();
			        String group=createContext(userId);
			      //  String firstName="";
			        
			        
			        /////////////////////
			        
				      /*Set set=session.getSessionIds(userId);

				      Iterator it = set.iterator();

				      String sessionID = "";

				      while (it.hasNext()){

				      sessionID = (String) it.next();

				      }
				      
				      Hashtable sessionAttrs = UserSession.getSessionAttributes(sessionID);

			            System.out.println("memberOf from session Attributes ===>"+sessionAttrs.get("memberOf"));*/
			            
			            /////////////////////////////////
			        
			    /*    System.out.println("session==="+session.getStatus());
			        System.out.println("actionTypes==="+session.getActionTypes().length);
			        System.out.println(" session.getActionsmemberOf----"+ session.getActions("memberOf"));
			        int i = 0; 
			        for (int iMax = actionTypes.length; i < iMax; i++)
			          {
			        	System.out.println("inside for **");
			            Hashtable actions = session.getActions(actionTypes[i]);
			            Enumeration e = actions.keys();
			            int item = 0;
			            LOGGER.info(new StringBuilder().append("Printing Actions for type ").append(actionTypes[i]).toString());

			            while (e.hasMoreElements()) {
			              String name = (String)e.nextElement();
			              System.out.println(new StringBuilder().append("Actions[").append(item).append("]: Name ").append(name).append("value ").append(actions.get(name)).toString());

			              item++;
			            }
			            LOGGER.info(new StringBuilder().append("Printing Actions for type ").append(actionTypes[i]).toString());

			            memberOf = (String)actions.get("memberOf");
			            firstName = (String)actions.get("firstname");
			          }
			        System.out.println("memberOf---"+memberOf);
			        System.out.println("firstName---"+firstName);*/
			        
			 
			        
			        LOGGER.info("userId---"+userId);
			        responseASBO.setGroup(group);
			   /*   String anonResource = OAMOIDUtil.OAMOID_URL;

			      ResourceRequest rrq = new ResourceRequest("http", anonResource, "GET");

			      LOGGER.info(new StringBuilder().append(requestASBO.getHeader().getCoRelationId()).append(" is authorised : ").append(session.isAuthorized(rrq)).toString());

			      LOGGER.info(new StringBuilder().append("Session status ").append(session.getStatus()).toString());
			      if (session.getStatus() == 1) {
			        String userIdentity = session.getUserIdentity();
			        String[] userIdentityParts = userIdentity.split(",");
			        String[] cnParts = userIdentityParts[0].split("=");
			        String userId = cnParts[1].trim();
			        LOGGER.info(new StringBuilder().append(requestASBO.getHeader().getCoRelationId()).append(" user Id from session IsLoggedin: ").append(userId).toString());

			      } else {
			        LOGGER.info(new StringBuilder().append(requestASBO.getHeader().getCoRelationId()).append(" Token expired.").toString());

			        LOGGER.info(new StringBuilder().append(requestASBO.getHeader().getCoRelationId()).append(" Session Object : ").append(session.toString()).toString());

			        session.logoff();
			        responseASBO.setLoggedIn(false);
			      }*/
			    } catch (Exception ae) {
			      LOGGER.error(new StringBuilder().append(requestASBO.getHeader().getCoRelationId()).append(" ").append(ae.getMessage()).toString(), ae);

			    }
			    LOGGER.info(new StringBuilder().append(requestASBO.getHeader().getCoRelationId()).append(" GetChannelOAMOIDResponseASBO : ").append(new Gson().toJson(responseASBO)).toString());

			    long end = System.currentTimeMillis();
			    LOGGER.info(new StringBuilder().append(requestASBO.getHeader().getCoRelationId()).append(" Exting getChannel()").toString());

			    LOGGER.info(new StringBuilder().append(requestASBO.getHeader().getCoRelationId()).append(" Time taken to getChannel()").append(end - start).append("ms").toString());

			    return responseASBO;
			  }
	  private void getGroupDetails(String stakeCode)
	  {
	    if ("CUSTOMER".equalsIgnoreCase(stakeCode)) {
	      this.orcldefaultprofilegroup = "customers";
	      this.groupName = "customers";
	    } else if ("CORPORATE CUSTOMER-TCS".equalsIgnoreCase(stakeCode)) {
	      this.orcldefaultprofilegroup = "CorporateCustomers";
	      this.groupName = "TCS";
	    } else if ("CORPORATE CUSTOMER-CTS".equalsIgnoreCase(stakeCode)) {
	      this.orcldefaultprofilegroup = "CorporateCustomers";
	      this.groupName = "CTS";
	    } else if ("CORPORATE CUSTOMER-AON".equalsIgnoreCase(stakeCode)) {
	      this.orcldefaultprofilegroup = "AON";
	      this.groupName = "AON";
	    } else if ("BMB CUSTOMER".equalsIgnoreCase(stakeCode)) {
	      this.orcldefaultprofilegroup = "BMBCustomers";
	      this.groupName = "BMBCustomers";
	    } else if ("CORPORATE CUSTOMER-TPL".equalsIgnoreCase(stakeCode)) {
	      this.orcldefaultprofilegroup = "USERS";
	      this.groupName = "TPL";
	    } else {
	      this.orcldefaultprofilegroup = stakeCode;
	      this.groupName = stakeCode;
	    }
	  }

	  private void assignUserRole(DirContext context, String userId, String groupName)
	    throws NamingException
	  {
	    ModificationItem[] mods = new ModificationItem[1];

	    Attribute mod = new BasicAttribute("uniqueMember", new StringBuilder().append("cn=").append(userId).append(",cn=users,dc=newindia,dc=co,dc=in").toString());

	    mods[0] = new ModificationItem(1, mod);
	    context.modifyAttributes(new StringBuilder().append("cn=").append(groupName).append(",cn=Groups,dc=newindia,dc=co,dc=in").toString(), mods);
	  }

	/*  private WebReportsResponseObject changePassword(WebReportsRequestObject requestVO)
	    throws IntegrationTechnicalException
	  {
	    LOGGER.info("Entering changePassword()");
	    long start = System.currentTimeMillis();
	    ChangePasswordOAMOIDResponseASBO responseASBO = new ChangePasswordOAMOIDResponseASBO();
	    String errorMessage = "failure";
	    String errorCode = "1";
	    String error = "";
	    ChangePasswordOAMOIDRequestASBO requestASBO = null;
	    if ((requestVO instanceof ChangePasswordOAMOIDRequestASBO))
	      requestASBO = (ChangePasswordOAMOIDRequestASBO)requestVO;
	    else {
	      throw new IntegrationTechnicalException();
	    }

	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

	    Date sysDate = new Date();
	    String currentDate = dateFormat.format(sysDate).replace(' ', 'T').concat("Z");

	    ModificationItem[] mods = new ModificationItem[3];
	    mods[0] = new ModificationItem(2, new BasicAttribute("userpassword", requestASBO.getNewPassword()));

	    mods[1] = new ModificationItem(2, new BasicAttribute("obpasswordcreationdate", currentDate));

	    mods[2] = new ModificationItem(2, new BasicAttribute("obpasswordchangeflag", "false"));

	    DirContext context = null;
	    DirContext validContext = null;
	    try {
	      if ((this.accessClient == null) || (!this.accessClient.isInitialized())) {
	        setupAccessClient();
	      }
	      UserSession session = new UserSession(this.accessClient, requestASBO.getSessionToken());

	      context = getInitialContext(OAMOIDUtil.LDAP_HOSTNAME, Integer.parseInt(OAMOIDUtil.LDAP_PORT), OAMOIDUtil.LDAP_USERNAME, OAMOIDUtil.LDAP_PASSWORD);

	      String usrName = new StringBuilder().append("cn=").append(requestASBO.getUserId()).append(",cn=Users,dc=newindia,dc=co,dc=in").toString();

	      String userIdentity = session.getUserIdentity();
	      String[] userIdentityParts = userIdentity.split(",");
	      String[] cnParts = userIdentityParts[0].split("=");
	      String userId = cnParts[1].trim();
	      LOGGER.info(new StringBuilder().append("user id inside change pwd : ").append(userId).toString());
	      try {
	        validContext = getValidInitialContext(OAMOIDUtil.LDAP_HOSTNAME, Integer.parseInt(OAMOIDUtil.LDAP_PORT), usrName, requestASBO.getOldPassword());

	        LOGGER.debug(new StringBuilder().append("validContext: ").append(validContext).toString());
	      } catch (Exception e) {
	        error = e.getMessage();
	        LOGGER.error(error);
	        try {
	          context.close();
	        } catch (NamingException e1) {
	          LOGGER.error(e1.getMessage(), e1);
	        }

	        responseASBO.setStatusMessage(e.getMessage());
	        responseASBO.setStatusCode("1");
	        return responseASBO;
	      }

	      context.modifyAttributes(session.getUserIdentity(), mods);

	      errorMessage = "Password Changed Successfully";
	      errorCode = "0";
	    } catch (NamingException e) {
	      error = e.getMessage();
	      LOGGER.error(error);

	      errorMessage = error;
	      errorCode = "1";
	      try
	      {
	        context.close();
	      } catch (NamingException e1) {
	        LOGGER.error(e1.getMessage(), e1);
	      }
	    }
	    catch (AccessException e) {
	      errorMessage = e.getMessage();
	      errorCode = "1";
	      LOGGER.error(e.getMessage(), e);
	    }
	    try {
	      context.close();
	    } catch (NamingException e) {
	      LOGGER.error(e.getMessage(), e);
	    }
	    responseASBO.setStatusMessage(errorMessage);
	    responseASBO.setStatusCode(errorCode);
	    long end = System.currentTimeMillis();
	    LOGGER.info("Exiting changePassword()");
	    LOGGER.info(new StringBuilder().append("Time taken to changePassword() : ").append(end - start).append("ms").toString());
	    return responseASBO;
	  }
*/
	  private WebReportsResponseObject forgotPassword(WebReportsRequestObject requestVO)
	    throws IntegrationTechnicalException
	  {
	    ForgotPasswordOAMOIDResponseASBO responseASBO = new ForgotPasswordOAMOIDResponseASBO();
	    String errorMessage = "failure";
	    String error = "";
	    String errorCode = "1";
	    ForgotPasswordOAMOIDRequestASBO requestASBO = null;
	    if ((requestVO instanceof ForgotPasswordOAMOIDRequestASBO))
	      requestASBO = (ForgotPasswordOAMOIDRequestASBO)requestVO;
	    else {
	      throw new IntegrationTechnicalException();
	    }

	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

	    Date sysDate = new Date();
	    String currentDate = dateFormat.format(sysDate).replace(' ', 'T').concat("Z");

	    ModificationItem[] mods = new ModificationItem[3];
	    mods[0] = new ModificationItem(2, new BasicAttribute("userpassword", requestASBO.getPassword()));

	    mods[1] = new ModificationItem(2, new BasicAttribute("obpasswordcreationdate", currentDate));

	    mods[2] = new ModificationItem(2, new BasicAttribute("obpasswordchangeflag", "true"));

	    LOGGER.info(mods);
	    DirContext context = null;
	    try {
	      if ((this.accessClient == null) || (!this.accessClient.isInitialized())) {
	        setupAccessClient();
	      }

	      String userIndentification = requestASBO.getUserId();

	      context = getInitialContext(OAMOIDUtil.LDAP_HOSTNAME, Integer.parseInt(OAMOIDUtil.LDAP_PORT), OAMOIDUtil.LDAP_USERNAME, OAMOIDUtil.LDAP_PASSWORD);

	      context.modifyAttributes(new StringBuilder().append("cn=").append(userIndentification).append(",cn=users,dc=newindia,dc=co,dc=in").toString(), mods);

	      errorMessage = "Success";
	      errorCode = "0";
	      responseASBO.setUserId(userIndentification);
	      responseASBO.setPassword(requestASBO.getPassword());
	    }
	    catch (NamingException e) {
	      error = e.getMessage();
	      LOGGER.error(e.getMessage(), e);
	      errorMessage = error;
	      errorCode = "1";

	      LOGGER.info(errorMessage);
	      try
	      {
	        if (null != context)
	          context.close();
	      }
	      catch (NamingException e1) {
	        LOGGER.error(e1.getMessage(), e1);
	      }
	    } catch (AccessException e) {
	      errorMessage = e.getMessage();
	      errorCode = "1";

	      LOGGER.error(e.getMessage(), e);
	    }
	    try
	    {
	      if (null != context)
	        context.close();
	    }
	    catch (NamingException e)
	    {
	      LOGGER.error(e.getMessage(), e);
	    }
	    responseASBO.setStatusMessage(errorMessage);
	    responseASBO.setStatusCode(errorCode);
	    return responseASBO;
	  }

	  public String addLastLogin(String userid)
	  {
	    int no = 0;
	    String errorMessage = "failure";
	    String error = "";
	    String errorCode = "1";
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

	    Date sysDate = new Date();
	    String currentDate = dateFormat.format(sysDate).replace(' ', 'T').concat("Z");

	    System.out.println(new StringBuilder().append("lastLoginDate").append(dateFormat.format(sysDate)).toString());

	    System.out.println(currentDate);
	    ModificationItem[] mods = new ModificationItem[1];
	    mods[0] = new ModificationItem(2, new BasicAttribute("oblastsuccessfullogin", currentDate));

	    DirContext context = null;
	    LOGGER.info(mods);
	    try {
	      context = getInitialContext(OAMOIDUtil.LDAP_HOSTNAME, Integer.parseInt(OAMOIDUtil.LDAP_PORT), OAMOIDUtil.LDAP_USERNAME, OAMOIDUtil.LDAP_PASSWORD);

	      context.modifyAttributes(new StringBuilder().append("cn=").append(userid).append(",cn=users,dc=newindia,dc=co,dc=in").toString(), mods);

	      errorMessage = "success";
	      errorCode = "0";
	    } catch (NamingException e) {
	      error = e.getMessage();

	      errorMessage = error;
	      errorCode = "1";
	      LOGGER.error(errorMessage);
	      try
	      {
	        if (null != context)
	          context.close();
	      }
	      catch (NamingException e1)
	      {
	        LOGGER.error(e1.getMessage(), e1);
	      }

	      return errorMessage;
	    }

	    try
	    {
	      if (null != context)
	        context.close();
	    }
	    catch (NamingException e) {
	      LOGGER.error(e.getMessage(), e);
	    }
	    return errorMessage;
	  }

	  public WebReportsResponseObject invokeOIDOAM(WebReportsRequestObject requestVO)
	    throws IntegrationTechnicalException
	  {
	    String eventId = requestVO.getHeader().getEventID();
	    if (UtilConstants.LOGIN.equalsIgnoreCase(eventId)) {
	    	
	      String anonResource = OAMOIDUtil.OAMOID_URL;
	      return authenticate(anonResource, requestVO);
	    }
	    if (UtilConstants.LOGOUT.equalsIgnoreCase(eventId))
	      return logout(requestVO);
	    if (UtilConstants.ISLOGGEDIN.equalsIgnoreCase(eventId))
	      return isLoggedIn(requestVO);
	    if (UtilConstants.GET_CHANNEL_REPORTS.equalsIgnoreCase(eventId))
		      return getChannel(requestVO);
	   /* if ("createUser".equalsIgnoreCase(eventId))
	      return createUser(requestVO);
	    if ("changePassword".equalsIgnoreCase(eventId))
	      return changePassword(requestVO);*/
	    /*if ("forgotPassword".equalsIgnoreCase(eventId)) {
	      return forgotPassword(requestVO);
	    }*/
	    return null;
	  }
	  
/*	  
		public static void main(String argv[]) {
		    String anonResource = "//10.10.3.250:7013/BaNCSIntegrationWebComp/rest/policies/authenticateMe";
//		    String login = "DL_GAN54";
//		    String password = "iasdb123";
		      String login = "DL_TEST1";
		      String password = "nia12345";
		 
//	            HashMap objMap=new HashMap();
//	            objMap.put("obpasswordchangeflag", "false");
//	            objMap.put("obpasswordcreationdate", "2015-04-17T16\\:41\\:05Z");
//	            objMap.put("uid", "test02");
//	            objMap.put("memberOf", null);
//	            
//		    System.out.println(OAMAcccesClient.checkStatus(objMap));
	         
	         
	         
	         
	         
		    try {
		    	LoginOAMOIDResponseASBO sId = (LoginOAMOIDResponseASBO) authenticate(anonResource, login, password);
			      System.out.println("-------------------"+sId);               
	               
	            String Token=sId.getToken();
	       //     UserSession session=(UserSession)sId.get("Session");
	                
	                
	          System.out.println("TOKEN-----"+Token);
	          IsLoggedInOAMOIDRequestASBO req=new IsLoggedInOAMOIDRequestASBO();
	          req.setSessionToken(Token);
		        isLoggedIn(req);
	   // System.out.println(OAMAcccesClient.isLoggedIn("EusgSmrVPO5RWAlQIo6EGlsgt617Otv/NfL1yKb3PszAj5eZFXeifYrEfDOon3mFcVjTCgVgespWJ4gLOn3jNIWFdGqzPA1Z9x35tfuMMl4tYrY8KyqTdNd2Sa5+YzYKmUATAUoUa0tLD1vT0I/bOIOwV2AjUcF3x4W0Qm+qsXLAar7FPkoeBwXSHgg7WFS4djUbxyxJy0r6yT0RkJc7iNYz3vhVljXDw6xIexXBcaFcefGUqnMs7G7SnoFw8N6uJBpTNl7C62JPUaLKYBFaICH37nW5scV4RAK1WMw4QFR5deeMt545lJG7oBwNfEhYQ2UOMVjWphU1bqJr0wCR9LVvRED0WcMWvEehhWz+FT+iunlV+lyMCJUz8TXYwRdow5AFDpgk2Lqt7wASgd0lrkPqeT4Cxgeclo2lUxOo38Ux4aL9iB/yxejQIaX9lCtSnbyug4PDanI4hYfzaaMwlw+fwXdbr0UTuRfMN/5N8+k="));
	               //System.out.println(OAMAcccesClient.checkStatus(sId));
		//      shutdown();
		       
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		  }
	*/
	  

	  /********LDAP code
	 * @throws NamingException 
	 * @throws NumberFormatException ************/
	  
      public String createContext(String userId) throws NumberFormatException, NamingException
      {    	      
    	  DirContext context = null;
  	 
  	      context = getInitialContext(OAMOIDUtil.LDAP_HOSTNAME, Integer.parseInt(OAMOIDUtil.LDAP_PORT), OAMOIDUtil.LDAP_USERNAME, OAMOIDUtil.LDAP_PASSWORD);
    	//  context = getInitialContext("10.91.35.237", 3060, "orcladmin", "iasdb123");
  	      
  	      String memberOf="";
  	    String group="";
    	try {  
    	  SearchControls constraints = new SearchControls();
          constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
          String[] attrIDs = {"sn",
                  "givenname",
                  "memberOf"};
          constraints.setReturningAttributes(attrIDs);
       
          NamingEnumeration answer = context.search("cn=users,dc=newindia,dc=co,dc=in", "uid="
                  + userId, constraints);
          if (answer.hasMore()) {
              Attributes attrs = ((SearchResult) answer.next()).getAttributes();
              LOGGER.info("memberOf "+ attrs.get("memberOf"));
              LOGGER.info("givenname "+ attrs.get("givenname"));
              LOGGER.info("sn "+ attrs.get("sn"));
              memberOf=attrs.get("memberOf").toString();
          }else{
              throw new Exception("Invalid User");
          }
          LOGGER.info("memberOf--"+memberOf);
          String[] memberOfParts = memberOf.split(",");
	        String[] cnParts = memberOfParts[0].split("=");
	        group = cnParts[1].trim();
          

      } catch (Exception ex) {
    	  LOGGER.error("error " +ex);
    	  ex.printStackTrace();
      }
        
      return group;
      }
      

      
      /********LDAP code
     * @throws NamingException 
     * @throws NumberFormatException ************/
      
     /* public static void main(String args[]) throws NumberFormatException, NamingException{
    	  
    	  OAMOIDInvoker in=new OAMOIDInvoker();
    	 String memberOf= in.createContext("AG_UAT10");
    	 System.out.println(memberOf);
    	  
      }
*/
      

         public static final String ms_resource = "//Example.com:80/secrets/index.html";         
         public static final String ms_protocol = "http";
         public static final String ms_method = "GET";
         public static final String ms_login = "jsmith";
         public static final String ms_passwd = "j5m1th";
         public void test() {
                AccessClient ac = null; 
            try {
                ac = AccessClient.createDefaultInstance(m_configLocation,
       AccessClient.CompatibilityMode.OAM_10G);
                
               ResourceRequest rrq = new ResourceRequest(ms_protocol, ms_resource,
                     ms_method);
               if (rrq.isProtected()) {
                  System.out.println("Resource is protected.");
                  AuthenticationScheme authnScheme = new AuthenticationScheme(rrq);
                  if (authnScheme.isForm()) {
                     System.out.println("Form Authentication Scheme.");
                     Hashtable creds = new Hashtable();
                     creds.put("userid", ms_login);
                     creds.put("password", ms_passwd);
                     UserSession session = new UserSession(rrq, creds);
                     if (session.getStatus() == UserSession.LOGGEDIN) {
                        if (session.isAuthorized(rrq)) {
                           System.out.println("User is logged in and authorized for the"
                                 +"request at level " + session.getLevel());
                        } else {
                           System.out.println("User is logged in but NOT authorized");
                        }
                              //user can be loggedout by calling logoff method on the session object
                     } else {
                        System.out.println("User is NOT logged in");
                     }
                  } else {
                     System.out.println("non-Form Authentication Scheme.");
                  }
               } else {
                  System.out.println("Resource is NOT protected.");
               }
            }
            catch (AccessException ae) {
               System.out.println("Access Exception: " + ae.getMessage());
            }
            ac.shutdown();
         }
      
}
