/**
 * 
 */
package com.tcs.docstore.asbo.emp.request;

import com.tcs.docstore.vo.cmo.DocStoreRequestObject;

/**
 * @author 585226
 *
 */
public class AlfrescoRequestASBO extends DocStoreRequestObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileName;
	private String filePath;
	private String deptName;
	private String docType;
	private String docDesc;
	private String docPublishDate;
	private String issuedBy;
	private String docUploadDate;
	private String confidentialStatus;
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return the docType
	 */
	public String getDocType() {
		return docType;
	}
	/**
	 * @param docType the docType to set
	 */
	public void setDocType(String docType) {
		this.docType = docType;
	}
	/**
	 * @return the docDesc
	 */
	public String getDocDesc() {
		return docDesc;
	}
	/**
	 * @param docDesc the docDesc to set
	 */
	public void setDocDesc(String docDesc) {
		this.docDesc = docDesc;
	}
	/**
	 * @return the docPublishDate
	 */
	public String getDocPublishDate() {
		return docPublishDate;
	}
	/**
	 * @param docPublishDate the docPublishDate to set
	 */
	public void setDocPublishDate(String docPublishDate) {
		this.docPublishDate = docPublishDate;
	}
	/**
	 * @return the issuedBy
	 */
	public String getIssuedBy() {
		return issuedBy;
	}
	/**
	 * @param issuedBy the issuedBy to set
	 */
	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}
	/**
	 * @return the docUploadDate
	 */
	public String getDocUploadDate() {
		return docUploadDate;
	}
	/**
	 * @param docUploadDate the docUploadDate to set
	 */
	public void setDocUploadDate(String docUploadDate) {
		this.docUploadDate = docUploadDate;
	}
	/**
	 * @return the confidentialStatus
	 */
	public String getConfidentialStatus() {
		return confidentialStatus;
	}
	/**
	 * @param confidentialStatus the confidentialStatus to set
	 */
	public void setConfidentialStatus(String confidentialStatus) {
		this.confidentialStatus = confidentialStatus;
	}
	
	
	
	

}
