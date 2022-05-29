package com.tcs.umsapp.unlock.invoker;

import java.util.Properties;

import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;

import org.apache.log4j.Logger;

import com.tcs.umsapp.unlock.invoker.util.OAMOIDUtil;
import com.tcs.umsapp.unlock.request.UnlockAccountRequestASBO;
import com.tcs.umsapp.unlock.response.UnlockAccountResponseASBO;
import com.tcs.umsapp.unlock.util.UtilProperties;

public class OAMOIDInvoker {
	
	private static final Logger LOGGER = Logger
			.getLogger(OAMOIDInvoker.class);
	/**
	 * 
	 * @param unlockAccountRequestASBO
	 * @return
	 */
	public UnlockAccountResponseASBO unlockUserAccount(
			UnlockAccountRequestASBO unlockAccountRequestASBO) {
	
		LOGGER.info("Indisde OAMOIDInvoker.unlockUserAccount");
		long start = System.currentTimeMillis();
		UnlockAccountResponseASBO unlockAccountResponseASBO = new UnlockAccountResponseASBO();
		unlockAccountResponseASBO.setHeader(unlockAccountRequestASBO.getHeader());
		String errorMessage = OAMOIDUtil.FAILED;
		String errorCode = OAMOIDUtil.ERROR;
		
		ModificationItem[] mods = new ModificationItem[4];

		mods[0] = new ModificationItem(2, new BasicAttribute("userpassword",
				unlockAccountRequestASBO.getNewPassword()));
		mods[1] = new ModificationItem(2, new BasicAttribute("oblogintrycount",
				"0"));
		mods[2] = new ModificationItem(2, new BasicAttribute("oblockouttime",
				"0"));
		mods[3] = new ModificationItem(2, new BasicAttribute(
                "obpasswordchangeflag", "true"));
		
		DirContext context = null;
		
		try {
			context = getInitialContext(UtilProperties.getLdapHostName(),
					Integer.parseInt(UtilProperties.getLdapPort()),
					UtilProperties.getUserName(), UtilProperties.getPassword());

			LOGGER.info("InitialContex successfully");
			
			String usrName = "cn=" + unlockAccountRequestASBO.getUserId()
					+ ",cn=Users,dc=newindia,dc=co,dc=in";

			context.modifyAttributes(usrName, mods);

			
			LOGGER.info("Context is updated successfully");
			
			errorMessage = OAMOIDUtil.SUCCESS;
			errorCode = OAMOIDUtil.NO_ERROR;
			

		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage(), e);
			errorMessage = OAMOIDUtil.FAILED;
			errorCode = OAMOIDUtil.ERROR;

		} catch(NameNotFoundException nex){
			errorMessage = OAMOIDUtil.FAILED;
			errorCode = OAMOIDUtil.ERROR;
			LOGGER.error(nex.getMessage(), nex);
		} 
		
		catch (NamingException e) {
			errorMessage = OAMOIDUtil.FAILED;
			errorCode = OAMOIDUtil.ERROR;
			LOGGER.error(e.getMessage(), e);
		} finally {
			if (null != context) {
				try {
					context.close();
					LOGGER.error("context is closed");
				} catch (NamingException e1) {
					LOGGER.error("Exception in context closing");
				}
			}
		}
		
		unlockAccountResponseASBO.setStatusCode(errorCode);
		unlockAccountResponseASBO.setStatusMsg(errorMessage);
		
		long end = System.currentTimeMillis();
		
		LOGGER.info("Time taken to UnlockUser : " + (end - start) + "ms");
		return unlockAccountResponseASBO;
	}

	/**
	 *  Used for creating DirContext of ldap server
	 * @param ldapHostname
	 * @param parseInt
	 * @param ldapUsername
	 * @param ldapPassword
	 * @return
	 * @throws NamingException
	 */
	private DirContext getInitialContext(String ldapHostname, int parseInt,
			String ldapUsername, String ldapPassword) throws NamingException {
		
		
			String providerURL = "ldap://" + ldapHostname + ":" + parseInt;
			LOGGER.info("provide URL: " + providerURL);

			Properties props = new Properties();
			props.put("java.naming.factory.initial",
					"com.sun.jndi.ldap.LdapCtxFactory");

			props.put("java.naming.provider.url", providerURL);
			if ((ldapUsername != null) && (!ldapPassword.equals(""))) {
				props.put("java.naming.security.authentication", "simple");
				props.put("java.naming.security.principal", "cn=" + ldapUsername);

				props.put("java.naming.security.credentials", ldapPassword == null ? ""
						: (Object) ldapPassword);
			}

			return new InitialDirContext(props);
		
	}

}
