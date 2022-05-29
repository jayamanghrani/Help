package com.tcs.employeeportal.persist.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the T_INFO_POLICY database table.
 * 
 */

@Entity
@Table(name="T_INFO_POLICY")
@NamedQueries({
	@NamedQuery(name="TInfoPolicy.findAll", query="SELECT tinfo FROM TInfoPolicy tinfo"),
	@NamedQuery(name="TInfoPolicy.findUser", query="SELECT tinfo FROM TInfoPolicy tinfo where tinfo.tEmpLoginId=:loginId")
	
	//@NamedQuery(name="TInfoPolicy.findUser", query="SELECT temp FROM TEmployeeDetail temp where exists(select tinfo from TInfoPolicy tinfo where tinfo.tEmpLoginId=temp.tEmpLoginId)")
	
	
})

public class TInfoPolicy implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name="T_EMP_LOGIN_ID")
	private String tEmpLoginId;

	@Temporal(TemporalType.DATE)
	@Column(name="T_POLICY_ACCEPTANCE_DT")
	private Date tPolicyAcceptanceDt;



	public String getTEmpLoginId() {
		return this.tEmpLoginId;
	}

	public void setTEmpLoginId(String tEmpLoginId) {
		this.tEmpLoginId = tEmpLoginId;
	}

	public Date getTPolicyAcceptanceDt() {
		return this.tPolicyAcceptanceDt;
	}

	public void setTPolicyAcceptanceDt(Date tPolicyAcceptanceDt) {
		this.tPolicyAcceptanceDt = tPolicyAcceptanceDt;
	}
	
}