/**
 * 
 */
package com.tcs.umsrole.so.request;

import java.util.Date;

import com.tcs.umsrole.vo.cmo.UmsRoleRequestObject;

/**
 * @author 926814
 *
 */
public class AcknowledgeRequestSo extends UmsRoleRequestObject{

    private String provId;
    private String status;
    private String provDate;
    private String sqlCode;
    private String sqlMessage;
    private String remark;
    /**
     * @return the provId
     */
    public String getProvId() {
        return provId;
    }
    /**
     * @param provId the provId to set
     */
    public void setProvId(String provId) {
        this.provId = provId;
    }
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @return the provDate
     */

    /**
     * @return the sqlCode
     */
    public String getSqlCode() {
        return sqlCode;
    }
    /**
     * @return the provDate
     */
    public String getProvDate() {
        return provDate;
    }
    /**
     * @param provDate the provDate to set
     */
    public void setProvDate(String provDate) {
        this.provDate = provDate;
    }
    /**
     * @param sqlCode the sqlCode to set
     */
    public void setSqlCode(String sqlCode) {
        this.sqlCode = sqlCode;
    }
    /**
     * @return the sqlMessage
     */
    public String getSqlMessage() {
        return sqlMessage;
    }
    /**
     * @param sqlMessage the sqlMessage to set
     */
    public void setSqlMessage(String sqlMessage) {
        this.sqlMessage = sqlMessage;
    }
    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }
    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AcknowledgeRequestSo [provId=" + provId + ", status=" + status
                + ", provDate=" + provDate + ", sqlCode=" + sqlCode
                + ", sqlMessage=" + sqlMessage + ", remark=" + remark + "]";
    }
    
}
