package com.tcs.umsrole.persist.entity;

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
@Table(name="UMS_REQUEST_MST")


public class RoleRequestMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "REQ_SEQ")
	@SequenceGenerator(sequenceName = "SEQ_REQ_ID", allocationSize = 1, name = "REQ_SEQ")
	@Column(name = "URQM_REQUEST_ID")
	private Long requestId;
	
	@Column(name = "URQM_REQUESTED_BY")
	private String requestBy;
	
	@Column(name = "URQM_REQUESTED_DATE")
	@Temporal(TemporalType.DATE)
	private Date requestDate;
	
	@Column(name = "URQM_MODIFIED_BY")
	private String modifyBy;

	@Column(name = "URQM_MODIFIED_DATE")
	@Temporal(TemporalType.DATE)
	private Date modifydate;
	
	@Column(name = "URQM_REASON")
	private String reason;

	public RoleRequestMaster() {
		super();
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public String getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
