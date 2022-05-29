package com.tcs.jira.persist.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="UMS_JIRA_ISSUE_MST")
public class JiraIssueMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "JIRA_ISSUE_SEQ")
	@SequenceGenerator(sequenceName = "SEQ_JIRA_REQ_ID", allocationSize = 1, name = "JIRA_ISSUE_SEQ")
	@Column(name = "URJM_REQUEST_ID")
	private Long Jira_Issue_requestId;
	
	@Column(name = "URJM_USERID")
	private String Jira_Issue_userId;
	
	@Column(name = "URJM_USERNAME")
	private String Jira_Issue_userName;
	
	@Column(name = "URJM_ISSUE_ID_NO")
	private String Jira_Issue_ID;
	
	@Column(name = "URJM_ISSUE_KEY_NO")
	private String Jira_Issue_KeyNo;
    
	@Column(name = "URJM_MODIFIED_DATE")
	@Temporal(TemporalType.DATE)
	private Date Jira_Issue_modifiedDate;

	public Long getJira_Issue_requestId() {
		return Jira_Issue_requestId;
	}

	public void setJira_Issue_requestId(Long jira_Issue_requestId) {
		Jira_Issue_requestId = jira_Issue_requestId;
	}

	public String getJira_Issue_userId() {
		return Jira_Issue_userId;
	}

	public void setJira_Issue_userId(String jira_Issue_userId) {
		Jira_Issue_userId = jira_Issue_userId;
	}

	public String getJira_Issue_userName() {
		return Jira_Issue_userName;
	}

	public void setJira_Issue_userName(String jira_Issue_userName) {
		Jira_Issue_userName = jira_Issue_userName;
	}

	public String getJira_Issue_ID() {
		return Jira_Issue_ID;
	}

	public void setJira_Issue_ID(String jira_Issue_ID) {
		Jira_Issue_ID = jira_Issue_ID;
	}

	public String getJira_Issue_KeyNo() {
		return Jira_Issue_KeyNo;
	}

	public void setJira_Issue_KeyNo(String jira_Issue_KeyNo) {
		Jira_Issue_KeyNo = jira_Issue_KeyNo;
	}

	public Date getJira_Issue_modifiedDate() {
		return Jira_Issue_modifiedDate;
	}

	public void setJira_Issue_modifiedDate(Date jira_Issue_modifiedDate) {
		Jira_Issue_modifiedDate = jira_Issue_modifiedDate;
	}

	public JiraIssueMaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


}
