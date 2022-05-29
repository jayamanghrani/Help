/**
 * 
 */
package com.tcs.jira.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.jira.asbo.request.AddRoleRequestSO;
import com.tcs.jira.asbo.request.UpdateUserRequestSO;
import com.tcs.jira.controller.JiraIntegrationController;
import com.tcs.jira.vo.cmo.UmsJiraResponseObject;

/**
 * @author 926814
 *
 */
@Component
@RestController
@RequestMapping("/jiraIntegration")
public class JiraController {

    private static final Logger LOGGER = Logger.getLogger(JiraController.class);

    /**
     * 
     * Request mapping for roleupdate
     */

    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    public ResponseEntity<UmsJiraResponseObject> addRole(
            @RequestBody AddRoleRequestSO addRoleRequestSO,
            HttpServletRequest request) {
        LOGGER.info(" variable service roleupdate set ");

        LOGGER.info(" *** Service Ends *** " + addRoleRequestSO.toString());
        return new ResponseEntity<>(
                new JiraIntegrationController()
                        .processAddRole(addRoleRequestSO),
                HttpStatus.OK);

    }
    
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity<UmsJiraResponseObject> updateUser(
            @RequestBody UpdateUserRequestSO updateUserRequestSO,
            HttpServletRequest request) {
        LOGGER.info(" variable service roleupdate set ");

        LOGGER.info(" *** Service Ends *** " + updateUserRequestSO.toString());
        return new ResponseEntity<>(
                new JiraIntegrationController()
                        .processUpdateUser(updateUserRequestSO),
                HttpStatus.OK);

    }
}
