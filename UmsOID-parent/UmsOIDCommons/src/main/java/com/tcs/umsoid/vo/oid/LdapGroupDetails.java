package com.tcs.umsoid.vo.oid;


import javax.naming.NamingException;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import org.apache.log4j.Logger;

import com.tcs.umsoid.vo.ldap.GetLdapSessionUtill;
import com.tcs.umsoid.vo.util.LdifUtil;


public class LdapGroupDetails {

	private static final Logger LOGGER = Logger.getLogger("umsOIDLogger");

	public void getSpecificRoleFromOid(String RoleCode,
			String userId) {
		LOGGER.info("Reached inside getSpecificRoleFromOid method  in LdapGroupDetails ");
		LdapGroupDetails ldapGroupDetails=new LdapGroupDetails();
		GetLdapSessionUtill getldapSessionUtil=new GetLdapSessionUtill();
		DirContext dirContext =getldapSessionUtil.getldapSession(); 
		ldapGroupDetails.getSpecficRole(userId,RoleCode,dirContext);

	}

	private void getSpecficRole(String userId,
			String userRolecode, DirContext dirContext) {
		LOGGER.info("Inside getSpecficRole method,for UserRoleCode --"+userRolecode+"oF User--"+userId);
		String  ldap_base = "cn=users,dc=newindia,dc=co,dc=in";
		String user_dn = ("cn="+userId+","+ldap_base);
		String UNIQUE_MEMBER ="uniquemember";
		
		ModificationItem[] mods = new ModificationItem[1];	
		mods[0] = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, new BasicAttribute(UNIQUE_MEMBER));
		 try {
			dirContext.modifyAttributes(user_dn,mods);
			LOGGER.info("Inside trty Block---");
			String errorMessage = LdifUtil.SUCCESS;
			LOGGER.info("value of errorMessage---"+errorMessage);
			String errorCode = LdifUtil.NO_ERROR;
			LOGGER.info("value of errorCode---"+errorCode);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}

}
