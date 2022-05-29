package com.tcs.docstore.persist.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the T_DOC_MAIL_ACCESS database table.
 * 
 */

@Entity
@Table(name="T_DOC_MAIL_ACCESS")
@NamedQueries({
@NamedQuery(name="TDocMailAccess.findAll", query="SELECT t FROM TDocMailAccess t"),
@NamedQuery(name="TDocMailAccess.findUserAcccess",query="select t from TDocMailAccess t where t.tTdmaUserid=:userId and t.tTdmaAccess=:accessFlag")
})
public class TDocMailAccess implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="T_TDMA_ACCESS")
	private String tTdmaAccess;

	@Column(name="T_TDMA_MAIL_LIST")
	private String tTdmaMailList;

	@Id
	@Column(name="T_TDMA_USERID")
	private String tTdmaUserid;

	public TDocMailAccess() {
	}

	public String getTTdmaAccess() {
		return this.tTdmaAccess;
	}

	public void setTTdmaAccess(String tTdmaAccess) {
		this.tTdmaAccess = tTdmaAccess;
	}

	public String getTTdmaMailList() {
		return this.tTdmaMailList;
	}

	public void setTTdmaMailList(String tTdmaMailList) {
		this.tTdmaMailList = tTdmaMailList;
	}

	public String getTTdmaUserid() {
		return this.tTdmaUserid;
	}

	public void setTTdmaUserid(String tTdmaUserid) {
		this.tTdmaUserid = tTdmaUserid;
	}

}