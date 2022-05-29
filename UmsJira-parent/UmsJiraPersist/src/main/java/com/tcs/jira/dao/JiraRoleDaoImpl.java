package com.tcs.jira.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tcs.jira.asbo.request.GetJiraUserRequestDetails;
import com.tcs.jira.asbo.response.JiraGetUserIssueCreateResponseBean;
import com.tcs.jira.persist.DBUtil;
import com.tcs.jira.persist.entity.JiraIssueMaster;
import com.tcs.jira.vo.util.UtilProperties;

public class JiraRoleDaoImpl implements JiraRoleDao {

	private static final Logger LOGGER = Logger.getLogger(JiraRoleDaoImpl.class);
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	Date currentDate= new Date();
	
	public GetJiraUserRequestDetails getUserDetails(String userId) {
	    GetJiraUserRequestDetails userRequestDetails=new GetJiraUserRequestDetails();
	    EntityManager entityManager = DBUtil.getEntityManager();
       try{
            LOGGER.info("User Id  came for Jira Application-----"+userId);
            LOGGER.info("User "+ userId +"doesnot exist in JIRA,Create User in JIRA");
                    //Call UMS DataBase to get Information of User 
                    Query userJiraQuery= entityManager.createNativeQuery("select * from ums_usr_mst where uum_usr_id=:userId  and uum_status='E' and uum_end_date is null");
                    userJiraQuery.setParameter("userId", userId);
                    List userList=userJiraQuery.getResultList();
                    LOGGER.info("User master List get from UMS DB for create User in Jira---------"+userList);
                   if(!userList.isEmpty()){
                    Iterator iter=userList.iterator();
                    while (iter.hasNext()) 
                    {
                        Object[] masterData= (Object[]) iter.next();
                        userRequestDetails.setUsername(userId);
                        userRequestDetails.setUserFullName(masterData[3].toString() +" "+masterData[5].toString());
                        userRequestDetails.setEmailAddress(masterData[16].toString());
                        userRequestDetails.setPhone(masterData[18].toString());
                        userRequestDetails.setBranch(masterData[6].toString());
                        userRequestDetails.setCity(masterData[12].toString());
                    }
                   }
                   LOGGER.info("Value of getCreateJiraUserRequestDetailsobject --"+userRequestDetails);
       }catch (Exception ex) {
           LOGGER.info("Error" + ex);
           LOGGER.error(ex.getStackTrace(),ex);
       }
       finally{
           try{
               if(null!=entityManager){
                   entityManager.close();
               }
           }
           catch(Exception e){
               LOGGER.error(e.getStackTrace(),e);
           }
       }
       return userRequestDetails;
    }
	
   
    @Override
    public String getRoleName(String roleId) {
        EntityManager entityManager = DBUtil.getEntityManager();
        String roleName="";
        try{
             LOGGER.info("User Id  came for Jira Application-----"+roleId);
             LOGGER.info("User "+ roleId +" Role ID");
                     //Call UMS DataBase to get Information of User 
                     Query userJiraQuery= entityManager.createNativeQuery("select URM_ROLE_NAME from UMS_ROLE_MST where URM_ROLE_ID=:roleId");
                     userJiraQuery.setParameter("roleId", roleId);
                     List userList=userJiraQuery.getResultList();
                     LOGGER.info("User master List get from UMS DB for create User in Jira---------"+userList);
                    if(!userList.isEmpty()){
                        roleName=userList.get(0).toString();
                    }
                    LOGGER.info("Value of getCreateJiraUserRequestDetailsobject --"+roleName);
        }catch (Exception ex) {
            LOGGER.info("Error" + ex);
            LOGGER.error(ex.getStackTrace(),ex);
        }
        finally{
            try{
                if(null!=entityManager){
                    entityManager.close();
                }
            }
            catch(Exception e){
                LOGGER.error(e.getStackTrace(),e);
            }
        }
        return roleName;
    }

    @Override
    public String getIssueName(String userId) {
        EntityManager entityManager = DBUtil.getEntityManager();
        String issueId="";
        try{
             LOGGER.info("User Id  came for Jira Application-----"+userId);
             LOGGER.info("User "+ userId +" Role ID");
                     //Call UMS DataBase to get Information of User 
                     Query userJiraQuery= entityManager.createNativeQuery("select URJM_ISSUE_KEY_NO from UMS_JIRA_ISSUE_MST where URJM_USERID=:userId");
                     userJiraQuery.setParameter("userId", userId);
                     List userList=userJiraQuery.getResultList();
                     LOGGER.info("User master List get from UMS DB for create User in Jira---------"+userList);
                    if(!userList.isEmpty()){
                        issueId=userList.get(0).toString();
                    }
                    LOGGER.info("Value of getCreateJiraUserRequestDetailsobject --"+issueId);
        }catch (Exception ex) {
            LOGGER.info("Error" + ex);
            LOGGER.error(ex.getStackTrace(),ex);
        }
        finally{
            try{
                if(null!=entityManager){
                    entityManager.close();
                }
            }
            catch(Exception e){
                LOGGER.error(e.getStackTrace(),e);
            }
        }
        return issueId;
        
    }
}