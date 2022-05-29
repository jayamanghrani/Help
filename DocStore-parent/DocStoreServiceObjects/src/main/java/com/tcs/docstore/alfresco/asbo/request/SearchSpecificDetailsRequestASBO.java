/**
 * 
 */
package com.tcs.docstore.alfresco.asbo.request;

import com.tcs.docstore.vo.cmo.DocStoreRequestObject;

/**
 * @author 585226
 *
 */
public class SearchSpecificDetailsRequestASBO extends DocStoreRequestObject  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileName;
	private String depatmentName;
	private String documentType;
	private String issuedBy;
	private String searchBy;
	private String docUploadDateStart;
	private String docUploadDateEnd;
	private String docPublishDateStart;
	private String docPublishDateEnd;
	private String matchMethod;
	private String isConfidential;
	/**
	 * @return the isConfidential
	 */
	public String getIsConfidential() {
		return isConfidential;
	}
	/**
	 * @param isConfidential the isConfidential to set
	 */
	public void setIsConfidential(String isConfidential) {
		this.isConfidential = isConfidential;
	}
	/**
	 * @return the matchMethod
	 */
	public String getMatchMethod() {
		return matchMethod;
	}
	/**
	 * @param matchMethod the matchMethod to set
	 */
	public void setMatchMethod(String matchMethod) {
		this.matchMethod = matchMethod;
	}
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
	 * @return the depatmentName
	 */
	public String getDepatmentName() {
		return depatmentName;
	}
	/**
	 * @param depatmentName the depatmentName to set
	 */
	public void setDepatmentName(String depatmentName) {
		this.depatmentName = depatmentName;
	}
	/**
	 * @return the documentType
	 */
	public String getDocumentType() {
		return documentType;
	}
	/**
	 * @param documentType the documentType to set
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
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
	 * @return the searchBy
	 */
	public String getSearchBy() {
		return searchBy;
	}
	/**
	 * @param searchBy the searchBy to set
	 */
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
	/**
	 * @return the docUploadDateStart
	 */
	public String getDocUploadDateStart() {
		return docUploadDateStart;
	}
	/**
	 * @param docUploadDateStart the docUploadDateStart to set
	 */
	public void setDocUploadDateStart(String docUploadDateStart) {
		this.docUploadDateStart = docUploadDateStart;
	}
	/**
	 * @return the docUploadDateEnd
	 */
	public String getDocUploadDateEnd() {
		return docUploadDateEnd;
	}
	/**
	 * @param docUploadDateEnd the docUploadDateEnd to set
	 */
	public void setDocUploadDateEnd(String docUploadDateEnd) {
		this.docUploadDateEnd = docUploadDateEnd;
	}
	/**
	 * @return the docPublishDateStart
	 */
	public String getDocPublishDateStart() {
		return docPublishDateStart;
	}
	/**
	 * @param docPublishDateStart the docPublishDateStart to set
	 */
	public void setDocPublishDateStart(String docPublishDateStart) {
		this.docPublishDateStart = docPublishDateStart;
	}
	/**
	 * @return the docPublishDateEnd
	 */
	public String getDocPublishDateEnd() {
		return docPublishDateEnd;
	}
	/**
	 * @param docPublishDateEnd the docPublishDateEnd to set
	 */
	public void setDocPublishDateEnd(String docPublishDateEnd) {
		this.docPublishDateEnd = docPublishDateEnd;
	}
	

}
