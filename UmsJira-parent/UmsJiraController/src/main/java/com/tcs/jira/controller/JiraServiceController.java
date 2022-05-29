/**
 * 
 */
package com.tcs.jira.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tcs.jira.asbo.request.GetCreateJiraUserIssueRequestDetails;
import com.tcs.jira.asbo.request.GetJiraUserRequestDetails;
import com.tcs.jira.asbo.request.UpdateUserRequestSO;
import com.tcs.jira.asbo.response.GetUserResponseBean;
import com.tcs.jira.asbo.response.GetUsersRolesResponse;
import com.tcs.jira.asbo.response.JiraGetUserIssueCreateResponseBean;
import com.tcs.jira.vo.util.UtilProperties;
/**
 * @author 926814
 *
 */
public class JiraServiceController {

    private static final Logger LOGGER = Logger
            .getLogger(JiraServiceController.class);
    private final String usernameColonPassword= UtilProperties.getJiraUserId()+":"+UtilProperties.getJiraPassword();
    private final String basicAuthPayload = "Basic " + Base64.getEncoder().encodeToString(usernameColonPassword.getBytes());

    public String createUserInJIRA(GetJiraUserRequestDetails userRequestDetails) {
        LOGGER.info("--- createUserInJIRA RestApi Method invoked-----");
        String CreateStatus = "";
        String Output=null;
        String errorStatus=" ";
        URL url = null;
        HttpURLConnection connection = null;

        try {
            url=new URL(UtilProperties.getJiraUserCreationURL());   
            StringBuilder response = new StringBuilder();
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST"); 
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization",basicAuthPayload);
            connection.setReadTimeout(15 * 1000);
            connection.setUseCaches(true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());

            outputStreamWriter.write("{\r\n\t\"name\": \""+userRequestDetails.getUsername()+"\",\r\n    \"emailAddress\": \""+userRequestDetails.getEmailAddress()+"\",\r\n\t\"displayName\": \""+userRequestDetails.getUserFullName()+"\"\r\n }");

            outputStreamWriter.flush();
            outputStreamWriter.close();
            LOGGER.info("--Response from JIRA CreateUSer  service------------"+connection.getResponseCode());           
            if (connection.getResponseCode() == 201) {
                BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));               
                while ((Output=br.readLine()) != null) {
                    response.append(Output);
                }   
                JsonElement jelement = new JsonParser().parse(response.toString());
                JsonObject jobject = jelement.getAsJsonObject();
                CreateStatus =jobject.get("active").getAsString();
                LOGGER.info("Value of CreateStatus  " +CreateStatus);
                br.close();
            }
            else{
                 BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    while ((Output = in.readLine()) != null) {
                        response.append(Output);
                    }
                    JsonElement jelement = new JsonParser().parse(response.toString());
                    JsonObject jobject = jelement.getAsJsonObject();
                    errorStatus=(jobject.getAsJsonObject("errors").toString());
                    LOGGER.info("Value of CreateStatus  " +errorStatus);
                    CreateStatus="false";
                    in.close();           
                  }
        

        } catch (MalformedURLException e) {
            LOGGER.error(e.getStackTrace(),e);
        } catch (JsonGenerationException e) {
            LOGGER.error(e.getStackTrace(),e);
        } catch (JsonMappingException e) {
            LOGGER.error(e.getStackTrace(),e);
        } catch (IOException e) {
            LOGGER.error(e.getStackTrace(),e);
        }
        return CreateStatus;
    }

    public GetUserResponseBean CheckUserExitanceInJira(String userid){
        LOGGER.info("---getUserFromJIRA RestApi Method -- invoked for CheckUserExitanceInJira method ----");
        URL url = null;
        String errorStatus;
        GetUserResponseBean getuserResponse =new GetUserResponseBean();
        String urlfull=UtilProperties.getJiraGetUserURL()+"username="+userid;
        LOGGER.info("value of  urlfull for getJIRA_getUserURL --"+urlfull);

        HttpURLConnection connection = null;
        try {
            url=new URL(urlfull);           
            StringBuilder response = new StringBuilder();
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");        
            connection.setRequestProperty("Authorization",basicAuthPayload);
            connection.setReadTimeout(15 * 1000);
            connection.setUseCaches(true);
            LOGGER.info("--Response from JIRA GetUser service------------"+connection.getResponseCode());
            if (connection.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));               
                String Success_output="";
                while ((Success_output=br.readLine()) != null) {
                    response.append(Success_output);
                }   
                JsonElement jelement = new JsonParser().parse(response.toString());
                JsonObject jobject = jelement.getAsJsonObject();
                getuserResponse.setUserId(jobject.get("key").getAsString());
                getuserResponse.setUserEmailAddress(jobject.get("emailAddress").getAsString());
                getuserResponse.setUserFullName(jobject.get("displayName").getAsString());
                getuserResponse.setUserActiveStatus(jobject.get("active").getAsString());
                getuserResponse.setErrorMessage(" ");
                LOGGER.info("--Response from JIRA GetUser service------------"+response.toString());
            }
            
            else{
                BufferedReader in = new BufferedReader( new InputStreamReader(connection.getErrorStream()));
                String Error_output;
                StringBuffer response2 = new StringBuffer();
                while ((Error_output = in.readLine()) != null) {
                 response2.append(Error_output);
                }
                JsonElement jelement = new JsonParser().parse(response2.toString());
                LOGGER.info("-ServiceResponse for JIRA GetUser  service --- -" +jelement); 
                JsonObject jobject = jelement.getAsJsonObject();
                LOGGER.info("Error Message for for JIRA GetUser  service"+jobject.getAsJsonArray("errorMessages"));
                errorStatus=(jobject.getAsJsonArray("errorMessages").toString());
                LOGGER.info("errorStatus ----"+errorStatus);
                getuserResponse.setUserId(userid);
                getuserResponse.setUserEmailAddress(" ");
                getuserResponse.setUserFullName(" ");
                getuserResponse.setUserActiveStatus("false");
                getuserResponse.setErrorMessage(errorStatus);
                in.close();
                }   
            LOGGER.info("getuserResponse for JIRA GetUser  service ----"+getuserResponse);
        }
        catch (Exception e) {
            LOGGER.error(e.getStackTrace(),e);
        }
        return getuserResponse;

    }

    public String disableUserInJira(String userId,boolean flagStatus) {
           String userdisable_Status="";
           LOGGER.info("----disableUserInJira RestApi Method-----");
    
            URL url = null;
            HttpURLConnection connection = null;
            try {
                url=new URL(UtilProperties.getJiraUserDisableURL()); 
                
                StringBuilder response = new StringBuilder();

                connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestMethod("PUT");   
                connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestProperty("Authorization",basicAuthPayload);
                connection.setReadTimeout(15 * 1000);
                connection.setUseCaches(true); 
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
                
                if(flagStatus){
                    outputStreamWriter.write("{\r\n\"name\":\""+userId+"\",\r\n\"active\":false\r\n\r\n}");
                }
                else{
                    outputStreamWriter.write("{\r\n\"name\":\""+userId+"\",\r\n\"active\":true\r\n\r\n}");
                }
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                
                    LOGGER.info("--Response from JIRA GetUser  service------------"+connection.getResponseCode());
                if (connection.getResponseCode() == 200) {                  
                    BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));               
                    String s=null;
                    while ((s=br.readLine()) != null) {
                        response.append(s);
                    }   
                    LOGGER.info("-ServiceResponse -"+response);
                    
                    JsonElement jelement = new JsonParser().parse(response.toString());
                    String message = jelement.getAsJsonObject().get("massage").toString();
                     LOGGER.info("message"+message);
                     userdisable_Status="200";
                }
                else{
                    String errorStatus;
                     BufferedReader in = new BufferedReader(
                                new InputStreamReader(connection.getErrorStream()));
                        String output2;
                        StringBuffer response2 = new StringBuffer();
                       
                        while ((output2 = in.readLine()) != null) {
                         response2.append(output2);
                        }
                        JsonElement jelement = new JsonParser().parse(response.toString());
                         errorStatus = jelement.getAsJsonObject().get("massage").toString();
                          LOGGER.info("errorStatus"+errorStatus);
                        in.close();
                        userdisable_Status=errorStatus;
                }
            }
            catch (Exception e) {
                LOGGER.error(e.getStackTrace(),e);

            }   
            return userdisable_Status;
    }

    public JiraGetUserIssueCreateResponseBean CreateIssueInJira(
            GetCreateJiraUserIssueRequestDetails getCreateJiraUserIssueRequestDetailsObj) {
        LOGGER.info("---CreateIssueInJira RestApi Method -- invoked ----");
        JiraGetUserIssueCreateResponseBean jiraCreateIssueResponseBeanObj =new JiraGetUserIssueCreateResponseBean(); 
        
        URL url = null;
        HttpURLConnection connection = null;
        
        String UserName=UtilProperties.getUserNameCustomAttr();
        String Userid=UtilProperties.getUserIdCustomAttr();
        String UserPhonenumber=UtilProperties.getPhoneNoCustomAttr();
        String UserCompany=UtilProperties.getCompanyCustomAttr();
        String UserOfficeCode=UtilProperties.getOfficeCodeCustomAttr();
        
        String Project_ID=UtilProperties.getCreateIssueProjectId();
        String IssueType_id=UtilProperties.getCreateIssueTypeId();
        try {   
            url=new URL(UtilProperties.getJiraCreateIssueURL());           
            StringBuilder response = new StringBuilder();
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST"); 
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization",basicAuthPayload);
            connection.setReadTimeout(15 * 1000);
            connection.setUseCaches(true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());          
            outputStreamWriter.write("{  \"fields\": {\"project\": { \"id\": \""+Project_ID+"\" }, \"summary\": \""+getCreateJiraUserIssueRequestDetailsObj.getJiraUserid()+"\",\"issuetype\": { \"id\": \""+IssueType_id+"\" },\""+UserName+"\":\""+getCreateJiraUserIssueRequestDetailsObj.getJiraUserName()+"\",        \""+Userid+"\":\""+getCreateJiraUserIssueRequestDetailsObj.getJiraUserid()+"\",        \""+UserPhonenumber+"\":\""+getCreateJiraUserIssueRequestDetailsObj.getJiraUserPhonenumber()+"\",\""+UserCompany+"\":{\"value\": \""+getCreateJiraUserIssueRequestDetailsObj.getJiraUserCompany()+"\" },\""+UserOfficeCode+"\":{ \"value\": \""+getCreateJiraUserIssueRequestDetailsObj.getJiraUserOfficeCode()+"\" }}}");
            outputStreamWriter.flush();
            outputStreamWriter.close();
            LOGGER.info("--Response from JIRA CreateIssue  service------------"+connection.getResponseCode());
            if (connection.getResponseCode() == 201) {
                BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));               
                String s=null;
                while ((s=br.readLine()) != null) {
                    response.append(s);
                }   
                LOGGER.info("-ServiceResponse for JIRA CreateIssue  service -"+response);
                        JsonElement jelement = new JsonParser().parse(response.toString());
                        JsonObject jobject = jelement.getAsJsonObject();

                        jiraCreateIssueResponseBeanObj.setIssueKeyNumber(jobject.get("key").getAsString());
                        jiraCreateIssueResponseBeanObj.setIssueID(jobject.get("id").getAsString());


                        LOGGER.info("-jiraCreateIssueResponseBeanObj -"+jiraCreateIssueResponseBeanObj);
            }
            else{
                String errorStatus="";      
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(connection.getErrorStream()));
                    String output2;
                    StringBuffer response2 = new StringBuffer();
                    while ((output2 = in.readLine()) != null) {
                     response2.append(output2);
                    }
                    JsonElement jelement = new JsonParser().parse(response2.toString());
                    LOGGER.info("-ServiceResponse ---jelement-- -" +jelement); 
                    JsonObject jobject = jelement.getAsJsonObject();        
                    errorStatus=(jobject.getAsJsonObject("errors").toString());
                    LOGGER.info("errorStatus ----"+errorStatus);
                    in.close();
                    
              }

        } catch (MalformedURLException e) {
            LOGGER.error(e.getStackTrace(),e);
        } catch (ProtocolException e) {
            LOGGER.error(e.getStackTrace(),e);
        } catch (IOException e) {
            LOGGER.error(e.getStackTrace(),e);
        }

        return jiraCreateIssueResponseBeanObj;

    }
    
    public String removeUserFromGroup(String groupName,String userName) {
        LOGGER.info("--- createUserInJIRA RestApi Method invoked-----");
        String createStatus = "";
        URL url = null;
        HttpURLConnection connection = null;

        try {
            StringBuilder newUrl=new StringBuilder();
            newUrl.append(UtilProperties.getJiraUserRemoveFromGroupURL()).
            append("groupname=").append(URLEncoder.encode(groupName.replaceAll(" +", " "),"UTF-8")).append("&username=").append(userName);
            LOGGER.info("--------------"+newUrl);
            url = new URL(newUrl.toString());
            connection= (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization",basicAuthPayload);
           LOGGER.info("--Response from oid service------------"+connection.getResponseCode());
            if (connection.getResponseCode() == 200) {
                    createStatus="200";
                   LOGGER.info("-ServiceResponse -"+connection.getResponseCode());
               }else
               {
                   String Output=null;
                   StringBuilder response = new StringBuilder();
                   BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                   while ((Output = in.readLine()) != null) {
                       response.append(Output);
                   }
                   JsonElement jelement = new JsonParser().parse(response.toString());
                   LOGGER.info("-ServiceResponse ---jelement-- -" +jelement); 
                   JsonObject jobject = jelement.getAsJsonObject();    
                   createStatus=jobject.get("errorMessages").getAsString();
                   in.close();  
               }
               } catch (MalformedURLException e) {
                   LOGGER.error(e.getStackTrace(),e);
        } catch (JsonGenerationException e) {
            LOGGER.error(e.getStackTrace(),e);
        } catch (JsonMappingException e) {
            LOGGER.error(e.getStackTrace(),e);
        } catch (IOException e) {
            LOGGER.error(e.getStackTrace(),e);
        }
        return createStatus;
    }
    
    
    public String addUserToGroup(String groupName,String userName) {
        LOGGER.info("--- createUserInJIRA RestApi Method invoked-----");
        String CreateStatus = "";
        String Output=null;
        String errorStatus=" ";
        URL url = null;
        HttpURLConnection connection = null;

        try {
           
            StringBuilder newUrl=new StringBuilder();
            newUrl.append(UtilProperties.getJiraUserAssignedToGroupURL()).
            append("groupname=").append(URLEncoder.encode(groupName.replaceAll(" +", " "),"UTF-8"));
            LOGGER.info("--------------"+newUrl);
            url = new URL(newUrl.toString());
            StringBuilder response = new StringBuilder();
           
                connection= (HttpURLConnection) url.openConnection();
           
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization",basicAuthPayload);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            outputStreamWriter.write("{\"name\":\""+userName+"\"}");
            outputStreamWriter.flush();
            outputStreamWriter.close();
           LOGGER.info("--Response from oid service------------"+connection.getResponseCode());
            if (connection.getResponseCode() == 201) {
                   BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));               
                   String s=null;
                   while ((s=br.readLine()) != null) {
                       response.append(s);
                   }   
                   LOGGER.info("-ServiceResponse -"+response);
                   CreateStatus="200";
                   LOGGER.info("Value of CreateStatus  " +response.toString());
               }
            else{
                 BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    while ((Output = in.readLine()) != null) {
                        response.append(Output);
                    }
                    JsonElement jelement = new JsonParser().parse(response.toString());
                    LOGGER.info("-ServiceResponse ---jelement-- -" +jelement); 
                    JsonObject jobject = jelement.getAsJsonObject();    
                    CreateStatus=jobject.get("errorMessages").getAsString();
                    in.close();           
                  }
        

        } catch (MalformedURLException e) {
            LOGGER.error(e.getStackTrace(),e);
        } catch (JsonGenerationException e) {
            LOGGER.error(e.getStackTrace(),e);
        } catch (JsonMappingException e) {
            LOGGER.error(e.getStackTrace(),e);
        } catch (IOException e) {
            LOGGER.error(e.getStackTrace(),e);
        }
        return CreateStatus;
    }
    
    public String updateIssueInJira(UpdateUserRequestSO updateUserRequestSO) {
        LOGGER.info("---CreateIssueInJira RestApi Method -- invoked ----");
        URL url = null;
        HttpURLConnection connection = null;
        String status="";
        String UserName=UtilProperties.getUserNameCustomAttr();
        String UserPhonenumber=UtilProperties.getPhoneNoCustomAttr();
        String UserOfficeCode=UtilProperties.getOfficeCodeCustomAttr();
        try {   
            StringBuilder urlNew=new StringBuilder();
            urlNew.append(UtilProperties.getJiraUpdateIssueURL()).append("/").append(updateUserRequestSO.getIssueId());
            url=new URL(urlNew.toString());       
            LOGGER.info(urlNew.toString());
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("PUT"); 
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization",basicAuthPayload);
            connection.setReadTimeout(15 * 1000);
            connection.setUseCaches(true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());          
            outputStreamWriter.write("{\"update\":{\""+UserName+"\":[{\"set\":\""+updateUserRequestSO.getName()+"\"}],\""+UserPhonenumber+"\":[{\"set\":\""+updateUserRequestSO.getMobileNo()+"\"}],\""+UserOfficeCode+"\":[{\"set\": {\"value\":\""+updateUserRequestSO.getBranchId()+"\"}}]}}");
            outputStreamWriter.flush();
            outputStreamWriter.close();
            LOGGER.info("{\"update\":{\""+UserName+"\":[{\"set\":\""+updateUserRequestSO.getName()+"\"}],\""+UserPhonenumber+"\":[{\"set\":\""+updateUserRequestSO.getMobileNo()+"\"}],\""+UserOfficeCode+"\":[{\"set\": {\"value\":\""+updateUserRequestSO.getBranchId()+"\"}}]}}");
            LOGGER.info("--Response from JIRA CreateIssue  service------------"+connection.getResponseCode());
            if (connection.getResponseCode() == 204) {
                status="200";
                LOGGER.info("-connection.getResponseCode()-----"+connection.getResponseCode());
            }
            else{
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(connection.getErrorStream()));
                    String output2;
                    StringBuffer response2 = new StringBuffer();
                    while ((output2 = in.readLine()) != null) {
                     response2.append(output2);
                    }
                    JsonElement jelement = new JsonParser().parse(response2.toString());
                    LOGGER.info("-ServiceResponse ---jelement-- -" +jelement); 
                    JsonObject jobject = jelement.getAsJsonObject();        
                    status=(jobject.getAsJsonObject("errors").toString());
                    
                    LOGGER.info("errorStatus ----"+status);
                    in.close();
                    
              }

        } catch (MalformedURLException e) {
            LOGGER.error(e.getStackTrace(),e);
        } catch (ProtocolException e) {
            LOGGER.error(e.getStackTrace(),e);
        } catch (IOException e) {
            LOGGER.error(e.getStackTrace(),e);
        }

        return status;
    }
    
    public GetUsersRolesResponse getUserRolesFromJira(String userid){
        LOGGER.info("---getUserFromJIRA RestApi Method -- invoked for CheckUserExitanceInJira method ----");
        URL url = null;
        String errorStatus;
        GetUserResponseBean getuserResponse =new GetUserResponseBean();
        StringBuilder urlfull = new StringBuilder();
        urlfull.append(UtilProperties.getJiraGetUserURL()).append("username=").append(userid).append("&expand=groups");
        
        LOGGER.info("value of  urlfull for getJIRA_getUserURL --"+urlfull);
        List<String> rolesList=new ArrayList<>();
        GetUsersRolesResponse resp=new GetUsersRolesResponse();
        HttpURLConnection connection = null;
        try {
            url=new URL(urlfull.toString());           
            StringBuilder response = new StringBuilder();
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");        
            connection.setRequestProperty("Authorization",basicAuthPayload);
            connection.setReadTimeout(15 * 1000);
            connection.setUseCaches(true);
            LOGGER.info("--Response from JIRA GetUser service------------"+connection.getResponseCode());
            if (connection.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));               
                String s=null;
                while ((s=br.readLine()) != null) {
                    response.append(s);
                }   
                JsonElement jelement = new JsonParser().parse(response.toString());
                JsonObject innerObject = jelement.getAsJsonObject().get("groups").getAsJsonObject();
                JsonArray jsonArray = innerObject.getAsJsonArray("items");
                for (int i = 0, size = jsonArray.size(); i < size; i++)
                {
                    JsonObject objectInArray = (JsonObject) jsonArray.get(i);
                    rolesList.add(objectInArray.get("name").getAsString());
                }
                LOGGER.info("--Response from JIRA GetUser service------------"+response.toString());
                br.close();
                resp.setRoles(rolesList);
                resp.setStatus("200");
            }
            
            else{
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String Error_output;
                StringBuffer response2 = new StringBuffer();
                while ((Error_output = in.readLine()) != null) {
                 response2.append(Error_output);
                }
                JsonElement jelement = new JsonParser().parse(response2.toString());
                LOGGER.info("-ServiceResponse for JIRA GetUser  service --- -" +jelement); 
                JsonObject jobject = jelement.getAsJsonObject();
                LOGGER.info("Error Message for for JIRA GetUser  service"+jobject.getAsJsonArray("errorMessages"));
                errorStatus=(jobject.getAsJsonArray("errorMessages").toString());
                LOGGER.info("errorStatus ----"+errorStatus);
                
                in.close();
                resp.setStatus(errorStatus);
                }   
            LOGGER.info("getuserResponse for JIRA GetUser  service ----"+getuserResponse);
        }
        catch (Exception e) {
            LOGGER.error(e.getStackTrace(),e);
        }
        return resp;

    }
    
    public JiraGetUserIssueCreateResponseBean getIssueDetailsInJIRA(String username) {
        System.out.println("--- createUserInJIRA RestApi Method invoked-----");
        JiraGetUserIssueCreateResponseBean bean=new JiraGetUserIssueCreateResponseBean();
        String CreateStatus="";
        String Output=null;
        String errorStatus=" ";
        URL url = null;
        HttpURLConnection connection = null;

        try {
            //http://jiraapp1.newindia.co.in:8889/rest/api/2/search?jql=cf[11303]~
            url=new URL(UtilProperties.getJiraCheckIssueURL()+username);   
            StringBuilder response = new StringBuilder();
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("GET"); 
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Authorization",basicAuthPayload);
            connection.setReadTimeout(15 * 1000);
            connection.setUseCaches(true);
            System.out.println("--Response from JIRA checkUserInJIRA  service------------"+connection.getResponseCode());           
            if (connection.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));               
                while ((Output=br.readLine()) != null) {
                    response.append(Output);
                }   
                JsonElement jelement = new JsonParser().parse(response.toString());
                JsonObject jobject = jelement.getAsJsonObject();
                 JsonArray issues= jobject.get("issues").getAsJsonArray();
                 for (JsonElement jsonElement : issues) {
                     bean.setIssueKeyNumber(jsonElement.getAsJsonObject().get("key").getAsString());
                     bean.setIssueID(jsonElement.getAsJsonObject().get("id").getAsString());
                 }
                br.close();
               
            }
            
        

        } catch (Exception e) {
            LOGGER.error(e.getStackTrace(),e);
        } 
        return bean;
    }    
}
