
package com.tcs.umsrole.request;

import java.util.Date;

public class AcknowledgeASBO {
    private String provId;
    private String status;
    private Date provDate;
    private String sqlCode;
    private String sqlMessage;
    private String remark;
    private String branchId;
    private Date endDate;
    
    
    
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getProvId() {
		return provId;
	}
	public void setProvId(String provId) {
		this.provId = provId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getProvDate() {
		return provDate;
	}
	public void setProvDate(Date provDate) {
		this.provDate = provDate;
	}
	public String getSqlCode() {
		return sqlCode;
	}
	public void setSqlCode(String sqlCode) {
		this.sqlCode = sqlCode;
	}
	public String getSqlMessage() {
		return sqlMessage;
	}
	public void setSqlMessage(String sqlMessage) {
		this.sqlMessage = sqlMessage;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
    
    
    
}
