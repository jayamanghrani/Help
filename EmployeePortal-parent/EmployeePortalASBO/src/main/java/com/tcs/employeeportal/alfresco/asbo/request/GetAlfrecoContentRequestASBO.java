package com.tcs.employeeportal.alfresco.asbo.request;



import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;

//TODO: Auto-generated Javadoc
	/**
	 * The Class GetContentRequestASBO.
	 *
	 * @author 376448
	 */
public class GetAlfrecoContentRequestASBO extends EmployeePortalRequestObject {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4662629643177021288L;
	
	/** The process. */
	private String process;
	
	/** The type of content. */
	private String typeOfContent;
	
	/** The language. */
	private String language;
	
	/** The channel. */
	private String channel;
	
	/** The product name. */
	private String productName;
	
	/** The news reference id. */
	private String newsReferenceId;
	
	/** The key word. */
	private String keyWord;
	
	/** The date. */
	private String date;

	/**
	 * Gets the process.
	 *
	 * @return the process
	 */
	
	 //Addition for document search starts
	
		private String fileName;
		private String depatmentName;
		private String documentType;
		private String issuedBy;
		private String searchBy;
		private String docUploadDateStart;
		private String docUploadDateEnd;
		private String docPublishDateStart;
		private String docPublishDateEnd;
		
		
		//Addition for document search Ends
		
		
	public String getProcess() {
		return process;
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

	/**
	 * Sets the process.
	 *
	 * @param process            the process to set
	 */
	public void setProcess(String process) {
		this.process = process;
	}

	/**
	 * Gets the type of content.
	 *
	 * @return the typeOfContent
	 */
	public String getTypeOfContent() {
		return typeOfContent;
	}

	/**
	 * Sets the type of content.
	 *
	 * @param typeOfContent            the typeOfContent to set
	 */
	public void setTypeOfContent(String typeOfContent) {
		this.typeOfContent = typeOfContent;
	}

	/**
	 * Gets the language.
	 *
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Sets the language.
	 *
	 * @param language            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * Gets the channel.
	 *
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * Sets the channel.
	 *
	 * @param channel            the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * Gets the product name.
	 *
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Sets the product name.
	 *
	 * @param productName            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * Gets the news reference id.
	 *
	 * @return the newsReferenceId
	 */
	public String getNewsReferenceId() {
		return newsReferenceId;
	}

	/**
	 * Sets the news reference id.
	 *
	 * @param newsReferenceId            the newsReferenceId to set
	 */
	public void setNewsReferenceId(String newsReferenceId) {
		this.newsReferenceId = newsReferenceId;
	}

	/**
	 * Gets the key word.
	 *
	 * @return the keyWord
	 */
public String getKeyWord() {
		return keyWord;
	}

	/**
	 * Sets the key word.
	 *
	 * @param keyWord            the keyWord to set
	 */
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
	

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetContentAlfrescoRequestASBO [process=");
		builder.append(process);
		builder.append(", typeOfContent=");
		builder.append(typeOfContent);
		builder.append(", language=");
		builder.append(language);
		builder.append(", channel=");
		builder.append(channel);
		builder.append(", productName=");
		builder.append(productName);
		builder.append(", newsReferenceId=");
		builder.append(newsReferenceId);
		builder.append(", keyWord=");
		builder.append(keyWord);
		builder.append("]");
		return builder.toString();
	}

}
