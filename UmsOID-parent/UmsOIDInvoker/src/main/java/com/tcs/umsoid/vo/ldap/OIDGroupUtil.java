package com.tcs.umsoid.vo.ldap;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;

import org.apache.log4j.Logger;

import com.tcs.umsoid.vo.bean.OIDUserDetails;
import com.tcs.umsoid.vo.bean.ProvisionDetails;
import com.tcs.umsoid.vo.ldap.GetLdapSessionUtill;

public class OIDGroupUtil {

	private static final Logger LOGGER = Logger.getLogger("umsOIDLogger");

	private static final String ALPHA_CAPS 	= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String ALPHA 	= "abcdefghijklmnopqrstuvwxyz";
	private static final String NUM 	= "0123456789";
	private static final String SPL_CHARS	= "!#&";


	public OIDGroupUtil() {
	}

	public OIDUserDetails addRole(ProvisionDetails provDetails){
		OIDUserDetails user = searchUser(provDetails.getUserID());

		LOGGER.info("User Status : " + user.getStatus());
		if(user.getStatus().equals("New")||user.getStatus().equals("Error")){
			LOGGER.info("--- User does not exist in OID. Creating... ---");
			user = createUser(provDetails);
		}else{
			LOGGER.info("User exists. Proceeding to add role.");
		}

		user = addRoleOID(user,provDetails.getRoleCode());

		return user;
	}

	public OIDUserDetails createUser(ProvisionDetails provDetails){

		OIDUserDetails newUser = new OIDUserDetails();

		String userDN = "cn="+provDetails.getUserID()+",cn=users,dc=newindia,dc=co,dc=in";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String[] dates = sdf.format(new Date()).split("_");
		String oidDate = dates[0]+"T"+dates[1]+"Z";

		Attribute oc = new BasicAttribute("objectclass");
		oc.add("top");
		oc.add("person");
		oc.add("organizationalPerson");
		oc.add("orcluserv2");
		oc.add("inetOrgPerson");
		oc.add("OblixOrgPerson");
		oc.add("OblixPersonPwdPolicy");

		Attributes entry = new BasicAttributes();

		entry.put(new BasicAttribute("uid", provDetails.getUserID()));
		entry.put(new BasicAttribute("cn", provDetails.getUserID()));
		entry.put(new BasicAttribute("givenname", provDetails.getFirstName()));
		if(provDetails.getMiddleName()!=null){
			entry.put(new BasicAttribute("middlename", provDetails.getMiddleName()));
		}
		entry.put(new BasicAttribute("sn", provDetails.getLastName()));
		entry.put(new BasicAttribute("ou", provDetails.getBranchID()));
		entry.put(new BasicAttribute("telephonenumber", provDetails.getPhoneNo()));
		entry.put(new BasicAttribute("orclGender", provDetails.getGender()));
		entry.put(new BasicAttribute("title", provDetails.getDesignation()));
		entry.put(new BasicAttribute("obpasswordchangeflag", "true"));
		entry.put(new BasicAttribute("oblastsuccessfullogin", oidDate));
		entry.put(new BasicAttribute("previousLoginTime", oidDate));
		entry.put(new BasicAttribute("orcldefaultprofilegroup", "cn=Employee,cn=groups,dc=newindia,dc=co,dc=in"));
		entry.put(new BasicAttribute("userpassword", getRandomPassword()));
		if(provDetails.getEmail()!=null){
			entry.put(new BasicAttribute("mail", provDetails.getEmail()));
		}
		entry.put(oc);

		LOGGER.info("Distinguished name :" + userDN);

		try{
			LOGGER.info("Getting LDAP session.");
			DirContext dirContext = new GetLdapSessionUtill().getldapSession(); 

			DirContext subDir = dirContext.createSubcontext(userDN, entry);
			LOGGER.info("Created User : " + subDir);

			newUser.setUserID(entry.get("uid"));
			newUser.setDistinguishedName(userDN);
			newUser.setStatus("Fresh");
			addRoleOID(newUser , "Employee");
		}catch(Exception e){
			newUser.setStatus("Error");
			LOGGER.error("error ",e);
		}
		return newUser;


	}	

	@SuppressWarnings("rawtypes")
	public OIDUserDetails addRoleOID(OIDUserDetails user, String provDetails) {

		LOGGER.info("---Adding user to target group---"+provDetails);

		String groupDN = "cn="+provDetails+",cn=groups,dc=newindia,dc=co,dc=in";
		String userDN = user.getDistinguishedName();
		userDN = userDN.replaceAll("\\s+","");

		Attributes userEntry = new BasicAttributes();

		userEntry.put(new BasicAttribute("uniquemember", userDN));


		try{
			LOGGER.info("Getting LDAP session.");
			DirContext dirContext = new GetLdapSessionUtill().getldapSession(); 

			Attributes groupAttrs =dirContext.getAttributes(groupDN);
			NamingEnumeration members = groupAttrs.get("uniquemember").getAll();
			boolean userExists = false;
			while(members != null && members.hasMore()){
				if(userDN.equals(members.next().toString())){
					userExists = true;
				}
			}

			if(!userExists){
				dirContext.modifyAttributes(groupDN, DirContext.ADD_ATTRIBUTE, userEntry);
				user.setStatus("S");
				LOGGER.info("User " + user.getDistinguishedName() + "added to group " + provDetails);
			}else{
				user.setStatus("S");
				LOGGER.info("User already exists.");				
			}
		}catch(Exception e){
			LOGGER.error(e);
			user.setStatus("F");
		}

		return user;
	}


	public OIDUserDetails searchUser(String userID){
		LOGGER.info("---Executing Search User---");
		OIDUserDetails response = new OIDUserDetails();
		String userDN = "cn="+userID+",cn=users,dc=newindia,dc=co,dc=in";
		userDN = userDN.replaceAll("\\s+","");

		try {

			DirContext dirContext = new GetLdapSessionUtill().getldapSession(); 
			Attributes respAttrs =dirContext.getAttributes(userDN);
			response.setUserID(respAttrs.get("uid"));
			response.setGivenName(respAttrs.get("givenname"));
			response.setDistinguishedName(userDN);
			LOGGER.debug("Distinguished Name : " + response.getDistinguishedName());
			response.setStatus("Found");

		} catch (NamingException e) {
			LOGGER.error("User not found : "+e);
			response.setStatus("New");
		}
		return response;
	}

	@SuppressWarnings("rawtypes")
	public OIDUserDetails deleteRole(ProvisionDetails provDetails) {
		OIDUserDetails user = new OIDUserDetails();

		LOGGER.info("---Deleting user from target group---");

		String userDN = "cn="+provDetails.getUserID()+",cn=users,dc=newindia,dc=co,dc=in";
		String groupDN = "cn="+provDetails.getRoleCode()+",cn=groups,dc=newindia,dc=co,dc=in";

		userDN = userDN.replaceAll("\\s+","");

		Attributes userEntry = new BasicAttributes();

		userEntry.put(new BasicAttribute("uniquemember", userDN));

		LOGGER.info("Distinguished name of group :" + groupDN);
		LOGGER.info("Distinguished name of user :" + userDN);

		try{
			LOGGER.info("Getting LDAP session.");
			DirContext dirContext = new GetLdapSessionUtill().getldapSession(); 

			Attributes groupAttrs =dirContext.getAttributes(groupDN);
			NamingEnumeration members = groupAttrs.get("uniquemember").getAll();
			boolean userExists = false;
			while(members != null && members.hasMore()){
				if(userDN.equals(members.next().toString())){
					userExists = true;
				}
			}

			if(userExists){
				dirContext.modifyAttributes(groupDN, DirContext.REMOVE_ATTRIBUTE, userEntry);
				LOGGER.info("User " + userDN + "removed from group " + provDetails.getRoleCode());
			}else{
				LOGGER.error("User is not present in group.");				
			}
			user.setUserID(provDetails.getUserID());
			user.setStatus("S");

			dirContext.close();

		}catch(Exception e){
			LOGGER.error(e);
			user.setStatus("F");
		}
		return user;
	}


	public OIDUserDetails updateUser(ProvisionDetails provDetails) {

		LOGGER.info("---Updating user details---");

		OIDUserDetails user = searchUser(provDetails.getUserID());
		String userDN = user.getDistinguishedName();

		if(!user.getStatus().equals("Found")){
			LOGGER.error("User does not exist");
			user.setStatus("F");
			return user;
		}

		LOGGER.info("--- User found in OID. Updating... ---");

		LOGGER.info("Distinguished name of user :" + userDN);

		Attributes userEntry = new BasicAttributes();

		userEntry.put(new BasicAttribute("givenname", provDetails.getFirstName()));
		userEntry.put(new BasicAttribute("middlename", provDetails.getMiddleName()));
		userEntry.put(new BasicAttribute("sn", provDetails.getLastName()));
		userEntry.put(new BasicAttribute("ou", provDetails.getBranchID()));
		userEntry.put(new BasicAttribute("telephonenumber", provDetails.getPhoneNo()));
		userEntry.put(new BasicAttribute("title", provDetails.getDesignation()));
		userEntry.put(new BasicAttribute("mail", provDetails.getEmail()));

		try{
			LOGGER.info("Getting LDAP session.");
			DirContext dirContext = new GetLdapSessionUtill().getldapSession(); 

			dirContext.modifyAttributes(userDN, DirContext.REPLACE_ATTRIBUTE, userEntry);

			LOGGER.info("User " + userDN + "updated.");
			user.setStatus("S");
			dirContext.close();

		}catch(Exception e){
			LOGGER.error(e);
			user.setStatus("F");
		}
		return user;
	}

	public OIDUserDetails disableUser(ProvisionDetails provDetails) {
		LOGGER.info("---Disabling user---");


		OIDUserDetails user = searchUser(provDetails.getUserID());
		String userDN = user.getDistinguishedName();

		if(!user.getStatus().equals("Found")){
			LOGGER.error("User does not exist");
			user.setStatus("F");
			return user;
		}

		LOGGER.info("--- User found in OID. Updating... ---");

		LOGGER.info("Distinguished name of user :" + userDN);

		Attributes userEntry = new BasicAttributes();

		userEntry.put(new BasicAttribute("orclisenabled", "DISABLED"));

		try{
			LOGGER.info("Getting LDAP session.");
			DirContext dirContext = new GetLdapSessionUtill().getldapSession(); 

			dirContext.modifyAttributes(userDN, DirContext.REPLACE_ATTRIBUTE, userEntry);

			LOGGER.info("User " + userDN + "updated.");
			user.setStatus("S");
			dirContext.close();

		}catch(Exception e){
			LOGGER.error(e);
			user.setStatus("F");
		}
		return user;
	}

	private static String getRandomPassword() {
		int noOfCAPSAlpha = 1;
		int noOfDigits = 1;
		int noOfSplChars = 1;
		int minLen = 8;
		int maxLen = 8;
		char[] pswd=null;
	
			pswd = generatePassword(minLen, maxLen,
					noOfCAPSAlpha, noOfDigits, noOfSplChars);
		return new String(pswd);	
	}
	
	private static char[] generatePassword(int minLen, int maxLen, int noOfCAPSAlpha, 
			int noOfDigits, int noOfSplChars) {
		Random rnd = new Random();
		int len = rnd.nextInt(maxLen - minLen + 1) + minLen;
		char[] pswd = new char[len];
		int index = 0;
		for (int i = 0; i < noOfCAPSAlpha; i++) {
			index = getNextIndex(rnd, len, pswd);
			pswd[index] = ALPHA_CAPS.charAt(rnd.nextInt(ALPHA_CAPS.length()));
		}
		for (int i = 0; i < noOfDigits; i++) {
			index = getNextIndex(rnd, len, pswd);
			pswd[index] = NUM.charAt(rnd.nextInt(NUM.length()));
		}
		for (int i = 0; i < noOfSplChars; i++) {
			index = getNextIndex(rnd, len, pswd);
			pswd[index] = SPL_CHARS.charAt(rnd.nextInt(SPL_CHARS.length()));
		}
		for(int i = 0; i < len; i++) {
			if(pswd[i] == 0) {
				pswd[i] = ALPHA.charAt(rnd.nextInt(ALPHA.length()));
			}
		}
		return pswd;
	}
	
	private static int getNextIndex(Random rnd, int len, char[] pswd) {
		int index = rnd.nextInt(len);
		while(pswd[index = rnd.nextInt(len)] != 0);
		return index;
	}

	
}
