package com.tcs.umsoid.vo.ldap;

import java.util.Properties;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.apache.log4j.Logger;

import com.tcs.umsoid.vo.util.LdifUtil;


public class GetLdapSessionUtill {
	private static final Logger LOGGER = Logger.getLogger("umsOIDLogger");
	
	public  DirContext dirContext = null;
	String errorMessage = "Failed hai ";
	String errorCode = "1";

	


	public DirContext getldapSession() {
		
		LOGGER.info("Establishing LDAP Session");
		try {
			String hostname=LdifUtil.LDAP_HOSTNAME;
			LOGGER.debug("LDAP Hosname "+hostname);
			
			String userName=LdifUtil.LDAP_USERNAME;	
			LOGGER.debug("LDAP UserName "+userName);
			
			String password=LdifUtil.LDAP_PASSWORD;
			LOGGER.debug("LDAP password "+password);
			
			int port = 0;
			try{
			 port = Integer.parseInt(LdifUtil.LDAP_PORT);
			}catch(Exception e){
				e.printStackTrace();
			}
			LOGGER.debug("Value of Port "+port);
			dirContext =getInitialContext(hostname,port,userName,password);
	
			LOGGER.info("Connection made");
		} catch (NumberFormatException | NamingException e) {
			LOGGER.error("Not able to established Connection "+e);
			e.printStackTrace();
		}

		
		return dirContext;
	}





	private DirContext getInitialContext(String ldapHostname, int port,
			String ldapUsername, String ldapPassword) throws NamingException {
		String providerURL = "ldap://" +ldapHostname+ ":" + port;
		LOGGER.info("Provider URL: " + providerURL);
		
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



	
	

