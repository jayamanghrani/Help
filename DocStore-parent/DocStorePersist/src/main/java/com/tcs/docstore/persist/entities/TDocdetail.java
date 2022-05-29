package com.tcs.docstore.persist.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the T_DOCDETAILS database table.
 * 
 */
@Entity
@Table(name="T_DOCDETAILS")
@NamedQueries({@NamedQuery(name="TDocdetail.findAll", query="SELECT t FROM TDocdetail t"),
	@NamedQuery(name="TDocdetail.findIssuedByAll",query="select t.issuedby from TDocdetail t where t.issuedby is not null")})
public class TDocdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DEPARTMENT_NAME")
	private String departmentName;

	@Column(name="DOCUMENT_TYPE")
	private String documentType;

	private String issuedby;

	public TDocdetail() {
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDocumentType() {
		return this.documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getIssuedby() {
		return this.issuedby;
	}

	public void setIssuedby(String issuedby) {
		this.issuedby = issuedby;
	}

}