package com.tcs.umsrole.vo.ldif;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;
import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.apache.log4j.Logger;

import com.tcs.umsrole.vo.bean.LdapFileBean;



public class SelectLdapEntry {

    private static final Logger LOGGER = Logger
            .getLogger(SelectLdapEntry.class);

    private DirContext dirContext = null;

    public LdapFileBean getData(String userID) {
        LdapFileBean ldapbeanobj = new LdapFileBean();
        LdapFileBean ldapbeanemptyobj = new LdapFileBean();
        boolean searchValueExist=false;
        ArrayList<String> stlist = new ArrayList<>();
        ArrayList<String> atlist = new ArrayList<>();
        ArrayList<String> otlist = new ArrayList<>();
        String attobjectclassSting;
        String attorclcommonpwdSting;
        String attOidSting;
        int TokenCount = 0;
        int TokenCountnumber = 0;
        int TokenCountval = 0;
        LOGGER.info("inside SelectLdapEntry --- ");
        LOGGER.info("constructer called");
        try {
    

            LdapContext ldapContext=  getLdapContext(LdifUtil.LDAP_HOSTNAME,
                    Integer.parseInt(LdifUtil.LDAP_PORT),
                    LdifUtil.LDAP_USERNAME, LdifUtil.LDAP_PASSWORD);

            LOGGER.info("InitialContex successfully");


            searchValueExist=SelectLdapEntry.UserExist(ldapContext,userID);



            if(searchValueExist) { 
                LOGGER.info("User  exist, searchValue--"+searchValueExist );
                //Niraj 
                String userDN = "cn=" + userID + ",cn=users,dc=newindia,dc=co,dc=in";
                userDN = userDN.replaceAll("\\s+", "");

                NamingEnumeration<? extends Attribute> results2 = null;

                Attributes ldapAttrs = ldapContext.getAttributes(userDN, new String[] {"*","+"});
                //  NamingEnumeration results2 = ldapAttrs.getAll();
                results2 = ldapAttrs.getAll();


                LOGGER.info("Attributes retrieved **********: " + ldapAttrs.toString()); 

                LOGGER.info("In String--dn-- : " + userDN);
                ldapbeanobj.setDc(userID);



                //001
                if((((String) ldapAttrs.get("givenname").get()) != null) && (!((String) ldapAttrs.get("givenname").get()).isEmpty())){
                    LOGGER.info("Value of givenname  is not empty or null in if conditions---------");
                    ldapbeanobj.setGivenname((String) ldapAttrs.get("givenname").get());

                }   
                else{
                    LOGGER.info("Value of givenname  is empty or null in else conditions ---------");
                    ldapbeanobj.setGivenname("null");
                }

                // 5
                LOGGER.info("----Logger start for objectclass value  ---");
                @SuppressWarnings("unchecked")
                Object attrobjectclass = ldapAttrs.get("objectclass");
                LOGGER.info("In String --attrobjectclass1-- : "
                        + attrobjectclass.toString() +"attrobjectclass.getClass()" +"\n"
                        + attrobjectclass.getClass());

                LOGGER.info("-----------Splitter function for objectclass--------------------------------");
                String attrobjectclassval = attrobjectclass.toString();
                LOGGER.info("----attrobjectclassval  --- " +attrobjectclassval);
                StringTokenizer stobjectclass = new StringTokenizer(
                        attrobjectclassval, ",");
                TokenCount = stobjectclass.countTokens();
                LOGGER.info("Value of TokenCount ----" + TokenCount);
                int i = 0;
                while (stobjectclass.hasMoreTokens()) {
                    i++;
                    LOGGER.info("inside While loop --" + i);
                    attobjectclassSting = (stobjectclass.nextToken());
                    LOGGER.info("" + stobjectclass.countTokens());
                    LOGGER.info("In String --objectclass---: "
                            + attobjectclassSting);
                    stlist.add(attobjectclassSting);
                }
                LOGGER.info("Value of stlist list---------" + stlist);

                ldapbeanobj.setObjectClass(stlist);

                // 6
                @SuppressWarnings("unchecked")
                Object attroid = ldapAttrs.get("authpassword;oid");
                if(((attroid) != null)){
                    LOGGER.info("Value of attroid object  is not empty or null in if conditions---------");

                    LOGGER.info("In String --attroid-- : " + attroid.toString());
                    LOGGER.info("-----------Splitter function for authpassword;oid--------------------------------");
                    String attoidval = attroid.toString();
                    StringTokenizer stoid = new StringTokenizer(attoidval, ",");
                    TokenCountval = stoid.countTokens();
                    LOGGER.info("Value of TokenCountval ----" + TokenCountval);
                    int K = 0;
                    while (stoid.hasMoreTokens()) {
                        K++;
                        LOGGER.info("inside While loop --" + K);
                        attOidSting = (stoid.nextToken());
                        LOGGER.info("" + stoid.countTokens());
                        LOGGER.info("In String --authpassword;oid---------"
                                + attOidSting);
                        otlist.add(attOidSting);
                    }
                    LOGGER.info("Value of otlist list---------" + otlist);
                    ldapbeanobj.setAuthpassword_oid(otlist);
                }   
                else{
                    LOGGER.info("Value of attroid object(otlist)  is empty or null in else conditions---------");
                    otlist.add("null");
                    ldapbeanobj.setAuthpassword_oid(otlist);
                }
                // 7
                @SuppressWarnings("unchecked")
                Object attrorclcommonpwd = ldapAttrs
                .get("authpassword;orclcommonpwd");
                if(((attrorclcommonpwd) != null)){
                    LOGGER.info("Value of attrorclcommonpwd object  is not empty or null in if conditions---------");

                    LOGGER.info("In String --attrorclcommonpwd-- : "
                            + attrorclcommonpwd.toString());
                    LOGGER.info("-----------Splitter function for authpassword;orclcommonpwd--------------------------------");
                    String attrorclcommonpwdval = attrorclcommonpwd.toString();
                    StringTokenizer storclcommonpwd = new StringTokenizer(
                            attrorclcommonpwdval, ",");
                    TokenCountnumber = storclcommonpwd.countTokens();
                    LOGGER.info("Value of TokenCount ----" + TokenCountnumber);
                    int j = 0;
                    while (storclcommonpwd.hasMoreTokens()) {
                        j++;
                        LOGGER.info("inside While loop --" + j);
                        attorclcommonpwdSting = (storclcommonpwd.nextToken());
                        LOGGER.info("" + storclcommonpwd.countTokens());
                        LOGGER.info("In String --authpassword;orclcommonpwd-------"
                                + attorclcommonpwdSting);
                        atlist.add(attorclcommonpwdSting);
                    }
                    LOGGER.info("Value of atlist list---------" + atlist);
                    ldapbeanobj.setAuthpassword_orclcommonpwd(atlist);
                }
                else{
                    LOGGER.info("Value of attrorclcommonpwd  object(atlist)  is empty or null in else conditions---------");
                    atlist.add("null");
                    ldapbeanobj.setAuthpassword_orclcommonpwd(atlist);
                }

                Object attruserpassword = ldapAttrs.get("userpassword").get();
                if(((attrorclcommonpwd) != null)){
                    LOGGER.info("Value of attrorclcommonpwd  is not empty or null in if conditions---------");
                    ldapbeanobj.setUserpassword(attruserpassword.toString());
                }   
                else{
                    LOGGER.info("Value of attrorclcommonpwd  is empty or null in else conditions---------");
                    ldapbeanobj.setUserpassword("null");
                }                        

                if(((ldapAttrs.get("createtimestamp")) != null)){
                    LOGGER.info("Value of createtimestamp  is not empty or null in if conditions---------");
                    ldapbeanobj.setCreatetimestamp((String) ldapAttrs.get("createtimestamp").get());
                }   
                else{
                    LOGGER.info("Value of createtimestamp  is empty or null in else conditions---------");
                    ldapbeanobj.setCreatetimestamp("null");
                } 


                if((((String) ldapAttrs.get("ou").get()) != null) && (!((String) ldapAttrs.get("ou").get()).isEmpty())){
                    LOGGER.info("Value of ou  is not empty or null in if conditions---------");
                    ldapbeanobj.setOu((String) ldapAttrs.get("ou").get());
                }   
                else{
                    LOGGER.info("Value of ou  is empty or null in else conditions---------");
                    ldapbeanobj.setOu("null");
                } 


                if((((String) ldapAttrs.get("uid").get()) != null) && (!((String) ldapAttrs.get("uid").get()).isEmpty())){
                    LOGGER.info("Value of uid  is not empty or null in if conditions---------");
                    ldapbeanobj.setUid((String) ldapAttrs.get("uid").get());
                }   
                else{
                    LOGGER.info("Value of uid  is empty or null in else conditions---------");
                    ldapbeanobj.setUid("null");
                } 


                if((((String) ldapAttrs.get("cn").get()) != null) && (!((String) ldapAttrs.get("cn").get()).isEmpty())){
                    LOGGER.info("Value of cn  is not empty or null in if conditions---------");
                    ldapbeanobj.setCn((String) ldapAttrs.get("cn").get());
                }   
                else{
                    LOGGER.info("Value of uid  is empty or null in else conditions---------");
                    ldapbeanobj.setCn("null");
                } 


                if((null!= ldapAttrs.get("pwdchangedtime"))){
                    LOGGER.info("Value of attrpwdChangedTime  is not empty or null in if conditions---------");
                    ldapbeanobj.setPwdchangedtime((ldapAttrs.get("pwdchangedtime").get()).toString());
                }   
                else{
                    LOGGER.info("Value of attrpwdChangedTime  is empty or null in else conditions---------");
                    ldapbeanobj.setPwdchangedtime("null");
                } 



                if((null!=ldapAttrs.get("orclguid"))) {
                    LOGGER.info("Value of attrorclGuid  is not empty or null in if conditions---------");
                    ldapbeanobj.setOrclguid(ldapAttrs.get("orclguid").get().toString());
                }   
                else{
                    LOGGER.info("Value of attrorclGuid  is empty or null in else conditions---------");
                    ldapbeanobj.setOrclguid("null");
                } 


                if((  null!=ldapAttrs.get("modifiersname"))) {
                    LOGGER.info("Value of attrmodifiersName  is not empty or null in if conditions---------");
                    ldapbeanobj.setModifiersname(ldapAttrs.get("modifiersname").get().toString());
                }   
                else{
                    LOGGER.info("Value of attrmodifiersName  is empty or null in else conditions---------");
                    ldapbeanobj.setModifiersname("null");
                } 


                if( null!= ldapAttrs.get("creatorsname")){
                    LOGGER.info("Value of attrcreatorsName  is not empty or null in if conditions---------");
                    ldapbeanobj.setCreatorsname( ldapAttrs.get("creatorsname").get().toString());
                }   
                else{
                    LOGGER.info("Value of attrcreatorsName  is empty or null in else conditions---------");
                    ldapbeanobj.setCreatorsname("null");
                } 

                if(null!=ldapAttrs.get("orclnormdn")){
                    LOGGER.info("Value of attrorclNormDN  is not empty or null in if conditions---------");
                    ldapbeanobj.setOrclnormdn(ldapAttrs.get("orclnormdn").get().toString());
                }   
                else{
                    LOGGER.info("Value of attrorclNormDN  is empty or null in else conditions---------");
                    ldapbeanobj.setOrclnormdn("null");
                } 

                if(null!= ldapAttrs.get("obpasswordchangeflag")){
                    LOGGER.info("Value of attrobpasswordchangeflag  is not empty or null in if conditions---------");
                    ldapbeanobj.setObpasswordchangeflag( ldapAttrs.get("obpasswordchangeflag").get().toString());
                }   
                else{
                    LOGGER.info("Value of attrobpasswordchangeflag  is empty or null in else conditions---------");
                    ldapbeanobj.setObpasswordchangeflag("null");
                } 


                if(null!=ldapAttrs.get("sn")){
                    LOGGER.info("Value of attrsn  is not empty or null in if conditions---------");
                    ldapbeanobj.setSn(ldapAttrs.get("sn").get().toString());
                }   
                else{
                    LOGGER.info("Value of attrsn  is empty or null in else conditions---------");
                    ldapbeanobj.setSn("null");
                }    

                if( null!=ldapAttrs.get("oblogintrycount")){
                    LOGGER.info("Value of attroblogintrycount  is not empty or null in if conditions---------");
                    ldapbeanobj.setOblogintrycount(ldapAttrs.get("oblogintrycount").get().toString());
                }   
                else{
                    LOGGER.info("Value of attroblogintrycount  is empty or null in else conditions---------");
                    ldapbeanobj.setOblogintrycount("null");
                }    

                if( null!=ldapAttrs.get("orcldefaultprofilegroup")){
                    LOGGER.info("Value of attrorcldefaultprofilegroup  is not empty or null in if conditions---------");
                    ldapbeanobj.setOrcldefaultprofilegroup(ldapAttrs.get("orcldefaultprofilegroup").get().toString());
                }   
                else{
                    LOGGER.info("Value of attrorcldefaultprofilegroup  is empty or null in else conditions---------");
                    ldapbeanobj.setOrcldefaultprofilegroup("null");
                } 


                if(null !=ldapAttrs.get("orclactivestartdate")){
                    LOGGER.info("Value of attrorclActiveStartdate  is empty or null in if conditions---------");

                    ldapbeanobj.setOrclactivestartdate(ldapAttrs.get("orclactivestartdate").get().toString());
                }   
                else{
                    LOGGER.info("Value of attrorclActiveStartdate  is not empty or null in else conditions---------");
                    ldapbeanobj.setOrclactivestartdate("null");
                }

                if( null!=ldapAttrs.get("modifytimestamp")){
                    LOGGER.info("Value of attrmodifyTimestamp  is empty or null in if conditions---------");
                    ldapbeanobj.setModifytimestamp(ldapAttrs.get("modifytimestamp").get().toString());
                }   
                else{
                    LOGGER.info("Value of attrmodifyTimestamp  is not empty or null in else conditions---------");
                    ldapbeanobj.setModifytimestamp("null");
                }


                if(null!=ldapAttrs.get("orclpassword")){
                    LOGGER.info("Value of attrorclPassword  is not empty or null in if conditions---------");
                    ldapbeanobj.setOrclpassword(ldapAttrs.get("orclpassword").get().toString());
                }   
                else{
                    LOGGER.info("Value of attrorclPassword  is empty or null in else conditions---------");
                    ldapbeanobj.setOrclpassword("null");
                } 



                LOGGER.info(LdifUtil.SUCCESS +LdifUtil.NO_ERROR );

                //     dirContext.close();
            }
            else{
                LOGGER.info("User not exist, searchValueExist" +searchValueExist);
                LOGGER.info(LdifUtil.SUCCESS +LdifUtil.NO_ERROR ); 
                LOGGER.info("User not exist" );
                ldapbeanobj=null;
            }
        } catch (NumberFormatException e) {
            LOGGER.info("Error found, Set ldapbeanobj null");
            LOGGER.error(e.getMessage(), e);
            LOGGER.info(LdifUtil.FAILED +LdifUtil.ERROR );

        } catch (NameNotFoundException nex) {
            LOGGER.info("Error found, Set ldapbeanobj null");
            LOGGER.info(LdifUtil.FAILED +LdifUtil.ERROR );
            LOGGER.error(nex.getMessage(), nex);     

        } catch (NamingException e) {
            LOGGER.info("Error found, Set ldapbeanobj null");
            LOGGER.info(LdifUtil.FAILED +LdifUtil.ERROR );
            LOGGER.error(e.getMessage(), e);

        } catch (Exception e) {

            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != dirContext) {
                try {
                    dirContext.close();
                    LOGGER.error("dirContext is closed");
                } catch (NamingException e1) {
                    LOGGER.error("Exception in dirContext closing");
                }
            }
        }
    

        return ldapbeanobj;
    }

    private static boolean UserExist(LdapContext ldapContext, String userID) {
        boolean UserExistStatus=false;
        String usrName = "cn=" + userID
                + ",cn=Users,dc=newindia,dc=co,dc=in";
        String searchFilter = "(cn=" + userID + ")";
        SearchControls constraints = new SearchControls();
        constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration results2;
        try {
            results2 = ldapContext.search(usrName,
                    searchFilter, constraints);

            while (results2.hasMore()) {
                LOGGER.info("inside while loop");
                LOGGER.info(" Check User Exist ");
                SearchResult sr2 = (SearchResult) results2.next();
                Attributes attrs = sr2.getAttributes();
                String Givenname=(String) attrs.get("givenname").get();
                UserExistStatus =true;
            }
        } catch (NamingException e) {
            LOGGER.info(" Check User Exist  not");
            UserExistStatus=false;
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return UserExistStatus;
    }



    private static LdapContext getLdapContext(String ldapHostname, int parseInt,
            String ldapUsername, String ldapPassword) throws Exception {
        Hashtable<String, String> ldapProps = new Hashtable<String, String>();
        ldapProps.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        ldapProps.put(Context.PROVIDER_URL,"ldap://"+ldapHostname+":"+parseInt);
        ldapProps.put(Context.SECURITY_AUTHENTICATION,"simple");
        ldapProps.put(Context.SECURITY_PRINCIPAL,"cn=" + ldapUsername);
        ldapProps.put(Context.SECURITY_CREDENTIALS,ldapPassword == null ? "" : ldapPassword);
        return new InitialLdapContext(ldapProps, null);
    }

}
