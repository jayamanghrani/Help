package com.tcs.umsapp.osb.persist.entities;

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
@Table(name="UMS_PROV_DTLS")
public class ProvDetailEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "REQ_PROV_SEQ")
	@SequenceGenerator(sequenceName = "SEQ_UMS_PROV_DTLS_ID", allocationSize = 1, name = "REQ_PROV_SEQ")
	@Column(name = "UPD_PROV_ID")
	private Long provisionId;
	
	@Column(name = "UPD_PROV_STATUS")
	private String provisionStatus;
	
	@Column(name = "UPD_PROV_DATE")
	@Temporal(TemporalType.DATE)
	private Date provisionDate;

	@Column(name = "UPD_MODIFIED_DATE")
	@Temporal(TemporalType.DATE)
	private Date modifyDate;
	
	@Column(name = "UPD_MODIFIED_BY")
	private String modifyBy;

	public Long getProvisionId() {
		return provisionId;
	}

	public void setProvisionId(Long provisionId) {
		this.provisionId = provisionId;
	}

	public String getProvisionStatus() {
		return provisionStatus;
	}

	public void setProvisionStatus(String provisionStatus) {
		this.provisionStatus = provisionStatus;
	}

	public Date getProvisionDate() {
		return provisionDate;
	}

	public void setProvisionDate(Date provisionDate) {
		this.provisionDate = provisionDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	@Override
	public String toString() {
		return "ProvDetail [provisionId=" + provisionId + ", provisionStatus="
				+ provisionStatus + ", provisionDate=" + provisionDate
				+ ", modifyDate=" + modifyDate + ", modifyBy=" + modifyBy + "]";
	}
	
	
	
	
}
