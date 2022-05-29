package com.tcs.jira.controller;

import java.util.Iterator;

import org.apache.log4j.Logger;

import com.tcs.jira.asbo.request.AddRoleRequestSO;
import com.tcs.jira.asbo.request.GetCreateJiraUserIssueRequestDetails;
import com.tcs.jira.asbo.request.GetJiraUserRequestDetails;
import com.tcs.jira.asbo.request.UpdateUserRequestSO;
import com.tcs.jira.asbo.response.AddRoleResponseSO;
import com.tcs.jira.asbo.response.GetUserResponseBean;
import com.tcs.jira.asbo.response.GetUsersRolesResponse;
import com.tcs.jira.asbo.response.JiraGetUserIssueCreateResponseBean;
import com.tcs.jira.dao.JiraRoleDao;
import com.tcs.jira.dao.JiraRoleDaoImpl;
import com.tcs.jira.vo.cmo.UmsJiraResponseObject;

public class JiraIntegrationController {

    private static final Logger LOGGER = Logger
            .getLogger(JiraIntegrationController.class);
   
    private JiraRoleDao jiraRoleDao;
  
    public JiraIntegrationController() {

        jiraRoleDao = new JiraRoleDaoImpl();

        LOGGER.info("Reached inside role integration controller");
    }

    public UmsJiraResponseObject processAddRole(AddRoleRequestSO addRoleRequestSO) {
        GetCreateJiraUserIssueRequestDetails jiraIssueRequest =new GetCreateJiraUserIssueRequestDetails();
        JiraGetUserIssueCreateResponseBean jiraCreateIssueObj =new JiraGetUserIssueCreateResponseBean();
        AddRoleResponseSO resp=new AddRoleResponseSO();
        JiraServiceController ctrl=new JiraServiceController();
        LOGGER.info("Reached in processGetRole integration controller");
        GetUserResponseBean jiraBean=new GetUserResponseBean();
        LOGGER.info("-----appRoleMapRequestASBO.getActionDo() == 'A'for Jira Application -----------------"); 
        //Call JIRA Get User details service to check Exit or not
        
        String roleName=jiraRoleDao.getRoleName(addRoleRequestSO.getRoleName());
        
        if(addRoleRequestSO.getAction().equalsIgnoreCase("A"))
        {
        jiraBean =ctrl.CheckUserExitanceInJira(addRoleRequestSO.getUserid());
           //User doesnot exist in JIRA DB
        GetJiraUserRequestDetails userDetails=jiraRoleDao.getUserDetails(addRoleRequestSO.getUserid());
        if((jiraBean.getUserActiveStatus().equalsIgnoreCase("false"))&&(null!=(jiraBean.getErrorMessage()))){
            LOGGER.info("User "+ addRoleRequestSO.getUserid() +"doesnot exist in JIRA,Create User in JIRA");

            //Call UMS DataBase to get Information of User 
            
            //Call JIRA to create new user in Jira
            String status=ctrl.createUserInJIRA(userDetails);
            

            if(status.equalsIgnoreCase("true"))
            {  
                jiraIssueRequest.setJiraUserid(addRoleRequestSO.getUserid());  
                jiraIssueRequest.setJiraUserName(userDetails.getUserFullName());
                jiraIssueRequest.setJiraUserLocation(userDetails.getCity());
                jiraIssueRequest.setJiraUserPhonenumber(userDetails.getPhone());
                jiraIssueRequest.setJiraUserCompany("NIA");
                jiraIssueRequest.setJiraUserOfficeCode(userDetails.getBranch());

                // Call CreateIssueInJira Service to create in JIRA
                jiraCreateIssueObj=ctrl.CreateIssueInJira(jiraIssueRequest); 
                LOGGER.info("Value of jiraGetUserIssueResponseBeanObje after Issue Create in Jira--"+jiraCreateIssueObj);

              //  String issueId=jiraRoleDao.createUmsIssue(userDetails,jiraCreateIssueObj);
                
                LOGGER.info(" If CreateStatus is true i.e createissue and issue details insert into UMS DB ");
                //Call OSB for Remove group id 
                String roleStatus=ctrl.removeUserFromGroup("jira-servicedesk-users",addRoleRequestSO.getUserid());
                LOGGER.info("------Removed Role----------"+roleStatus);
                if(!roleName.equalsIgnoreCase("Utkarsh End User")){
                roleStatus=ctrl.addUserToGroup(roleName,addRoleRequestSO.getUserid());
                if(roleStatus.equals("200"))
                {
                    resp.setResultStatus("S");
                    resp.setStatusMessage("Role Assigned successfully in JIRA");
                }else
                {
                    resp.setResultStatus("F");
                    resp.setStatusMessage("Error while adding role in JIRA :" +roleStatus);
                }
                }else{
                    
                        resp.setResultStatus("S");
                        resp.setStatusMessage("Role User created successfully in JIRA");
                }
            }else
            {
                resp.setResultStatus("F");
                resp.setStatusMessage("Error while creating user in JIRA");
            }
        }
           // User exist in Jira Application, not required to create in Jira
        else{
            
            LOGGER.info("User "+ addRoleRequestSO.getUserid() +" exist in JIRA, Not required to Create User in JIRA"); 
            
              if(jiraBean.getUserActiveStatus().equalsIgnoreCase("true"))
            {
                if(!roleName.equalsIgnoreCase("Utkarsh End User")){
                String roleStatus=ctrl.addUserToGroup(roleName,addRoleRequestSO.getUserid());
                if(roleStatus.equals("200"))
                {
                    resp.setResultStatus("S");
                    resp.setStatusMessage("Role Assigned successfully in JIRA");
                }else
                {
                    resp.setResultStatus("F");
                    resp.setStatusMessage("Error while adding role in JIRA :" +roleStatus);
                }
                }else{
                    resp.setResultStatus("S");
                    resp.setStatusMessage("User is Added to group");
                }
            }

            else{
                LOGGER.info("User is disable ,in transfer mode");
                resp.setResultStatus("F");
                resp.setStatusMessage("User is disable ,in transfer mode");
            }
        }
        }
        else if(addRoleRequestSO.getAction().equalsIgnoreCase("D"))
        {
            String roleStatus=ctrl.removeUserFromGroup(roleName,addRoleRequestSO.getUserid());
            if(roleStatus.equals("200"))
            {
                resp.setResultStatus("S");
                resp.setStatusMessage("Role Removed successfully from JIRA");
            }else
            {
                resp.setResultStatus("F");
                resp.setStatusMessage("Error while adding role in JIRA :" +roleStatus);
            }
        }
        return resp;
    }
    
    
    public UmsJiraResponseObject processUpdateUser(UpdateUserRequestSO updateUserRequestSO) {
        AddRoleResponseSO resp=new AddRoleResponseSO();
        LOGGER.info("Update user reqest" +updateUserRequestSO.toString());
        JiraServiceController ctrl=new JiraServiceController();
        JiraGetUserIssueCreateResponseBean jiraCreateIssueObj =new JiraGetUserIssueCreateResponseBean();
        if(updateUserRequestSO.getAction().equalsIgnoreCase("UU"))
        {
            LOGGER.info("User action is update");
            //Get Issue ID for Update.
            jiraCreateIssueObj=ctrl.getIssueDetailsInJIRA(updateUserRequestSO.getUserId());
           // String issueId=jiraRoleDao.getIssueName(updateUserRequestSO.getUserId());
            updateUserRequestSO.setIssueId(jiraCreateIssueObj.getIssueID());
            
            String status=ctrl.updateIssueInJira(updateUserRequestSO);
            LOGGER.info("update issue resp "+status);
            if (status.equalsIgnoreCase("200"))
            {
               // jiraRoleDao.updateIssueMaster(updateUserRequestSO.getUserId());
                resp.setResultStatus("S");
                resp.setStatusMessage("User Updated successfully in JIRA");
            }
            LOGGER.info("User action update resp "+resp.toString());
        }else
            if(updateUserRequestSO.getAction().equalsIgnoreCase("BU"))
            {
                LOGGER.info("User action is Branch Transfer");
                String statusmsg="";
                //get roles from JIRA
                GetUsersRolesResponse roles=ctrl.getUserRolesFromJira(updateUserRequestSO.getUserId());
                boolean flag=false;
                for (Iterator iterator = roles.getRoles().iterator(); iterator
                        .hasNext();) {
                    String groupName = (String) iterator.next();
                    LOGGER.info("removing Group------"+groupName);
                    //revoke users from all group
                    String status=ctrl.removeUserFromGroup(groupName, updateUserRequestSO.getUserId());
                    if(status.equalsIgnoreCase("200"))
                    {
                        flag=true;
                    }else
                    {
                        flag=false;
                        statusmsg=status;
                        break;
                    }
                   LOGGER.info("Status------------"+status);
                
                }
                if(flag)
                {  
                resp.setResultStatus("S");
                resp.setStatusMessage("User Branch updated successfully in JIRA");
                }else
                {
                    resp.setResultStatus("F");
                    resp.setStatusMessage(statusmsg);
                }
            }else
                if(updateUserRequestSO.getAction().equalsIgnoreCase("DU"))
                {
                    LOGGER.info("User action is Disable");
                    //disable user
                    String status=ctrl.disableUserInJira(updateUserRequestSO.getUserId(), true);
                    if(status.equalsIgnoreCase("200"))
                    {
                        resp.setResultStatus("S");
                        resp.setStatusMessage("User Disabled successfully in JIRA");
                    }else
                    {
                        resp.setResultStatus("F");
                        resp.setStatusMessage(status);
                    }
                   
                }
        return resp;
    }  
    
   
}