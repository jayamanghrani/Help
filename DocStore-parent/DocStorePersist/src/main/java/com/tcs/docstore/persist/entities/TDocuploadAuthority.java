package com.tcs.docstore.persist.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the T_DOCUPLOAD_AUTHORITY database table.
 * 
 */
@Entity
@Table(name="T_DOCUPLOAD_AUTHORITY")
@NamedQueries({
@NamedQuery(name="TDocuploadAuthority.findAll", query="SELECT t FROM TDocuploadAuthority t"),
@NamedQuery(name="TDocuploadAuthority.findUploadUserAccess",query="SELECT t.accessUpload FROM TDocuploadAuthority t where UPPER(t.oidGroup) =:oidGrpValue AND UPPER(t.uploadSelectGroup) =:selectgrpValue")})
public class TDocuploadAuthority implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACCESS_UPLOAD")
	private String accessUpload;
	
	@Id
	@Column(name="OID_GROUP")
	private String oidGroup;

	@Column(name="UPLOAD_SELECT_GROUP")
	private String uploadSelectGroup;

	public TDocuploadAuthority() {
	}

	public String getAccessUpload() {
		return this.accessUpload;
	}

	public void setAccessUpload(String accessUpload) {
		this.accessUpload = accessUpload;
	}

	public String getOidGroup() {
		return this.oidGroup;
	}

	public void setOidGroup(String oidGroup) {
		this.oidGroup = oidGroup;
	}

	public String getUploadSelectGroup() {
		return this.uploadSelectGroup;
	}

	public void setUploadSelectGroup(String uploadSelectGroup) {
		this.uploadSelectGroup = uploadSelectGroup;
	}

}