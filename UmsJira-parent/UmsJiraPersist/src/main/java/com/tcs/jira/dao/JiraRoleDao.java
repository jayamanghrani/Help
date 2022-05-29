package com.tcs.jira.dao;

import com.tcs.jira.asbo.request.GetJiraUserRequestDetails;


public interface JiraRoleDao {
	
		public GetJiraUserRequestDetails getUserDetails(String userId);
		public String getRoleName(String roleId);
		public String getIssueName(String userId);
}
