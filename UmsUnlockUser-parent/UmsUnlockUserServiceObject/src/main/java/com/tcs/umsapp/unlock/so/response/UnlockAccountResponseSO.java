package com.tcs.umsapp.unlock.so.response;

import com.tcs.umsapp.general.vo.cmo.UmsappResponseObject;

public class UnlockAccountResponseSO extends UmsappResponseObject {

	private static final long serialVersionUID = 7449198446776093828L;

	private String statusCode;

	private String statusMsg;
	
	private int mailStatus;
	
	private int smsStatus;
	private String password;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public int getMailStatus() {
		return mailStatus;
	}

	public void setMailStatus(int mailStatus) {
		this.mailStatus = mailStatus;
	}

	public int getSmsStatus() {
		return smsStatus;
	}

	public void setSmsStatus(int smsStatus) {
		this.smsStatus = smsStatus;
	}

	
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UnlockAccountResponseSO [statusCode=" + statusCode
                + ", statusMsg=" + statusMsg + ", mailStatus=" + mailStatus
                + ", smsStatus=" + smsStatus + ", password=" + password + "]";
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
	
	

	
	
}
