/**
 * 
 */
package com.tcs.employeeportal.asbo.emp.request;


import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;

/**
 * @author 585226
 *
 */
public class ContactUsRequestASBO extends EmployeePortalRequestObject{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The name. */
	private String name;
	
	/** The email id. */
	private String emailId;
	
	/** The mobile no. */
	private String mobileNo;
	
	/** The comments. */
	private String comments;
	
	/** The mobile applink. */
	private String mobileApplink;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the mobileApplink
	 */
	public String getMobileApplink() {
		return mobileApplink;
	}

	/**
	 * @param mobileApplink the mobileApplink to set
	 */
	public void setMobileApplink(String mobileApplink) {
		this.mobileApplink = mobileApplink;
	}


}
