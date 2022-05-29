package com.tcs.docstore.asbo.alfresco;

import java.io.Serializable;
public class AlfrescoInputs implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1111021374765873271L;
	
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
	
	/** The group. */
	private String group;
	
	/** The label. */
	private String label;
	
	/** The additionalLink. */
	private String additionalLink;
	
	/** The orderNumber. */
	private String orderNumber;
		
	private String matchMethod; // whether you want to match any of the following or match all of the following
	
	private String docuploaddatestart;
	
	private String docuploaddateend;
	
	private String docpublishdatestart;
	
	private String docpublishdateend;
	
	private String searchbytype;  // means search by docupload date or document publish date

	/** The issue date. */
	private String issueDate;
	
	/** The deployment date. */
	private String deploymentDate;
	
	
	private String docName;
	
	/** The deptName. */
	private String deptName;
	
	/** The issuedBy. */
	private String issuedBy;
	
	/** The docType. */
	private String docType;
	
	/** The prodList. */
	private String prodList;
	
	/** The empKeyWords. */
	private String empKeyWords;
	
	/** The year. */
	private String year;
	
	/** The confidential. */
	private String isConfidential;
	
	/** descOldEmp **/
	private String descOldEmp;
	
	
	/**
	 * @return the descOldEmp
	 */
	public String getDescOldEmp() {
		return descOldEmp;
	}

	/**
	 * @param descOldEmp the descOldEmp to set
	 */
	public void setDescOldEmp(String descOldEmp) {
		this.descOldEmp = descOldEmp;
	}

	/**
	 * @return the orderNumber
	 */
	public String getOrderNumber() {
		return orderNumber;
	}

	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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
	 * @return the docName
	 */
	public String getDocName() {
		return docName;
	}

	/**
	 * @param docName the docName to set
	 */
	public void setDocName(String docName) {
		this.docName = docName;
	}

	
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
	 * @return the searchbytype
	 */
	public String getSearchbytype() {
		return searchbytype;
	}

	/**
	 * @param searchbytype the searchbytype to set
	 */
	public void setSearchbytype(String searchbytype) {
		this.searchbytype = searchbytype;
	}

	/**
	 * @return the docuploaddatestart
	 */
	public String getDocuploaddatestart() {
		return docuploaddatestart;
	}

	/**
	 * @param docuploaddatestart the docuploaddatestart to set
	 */
	public void setDocuploaddatestart(String docuploaddatestart) {
		this.docuploaddatestart = docuploaddatestart;
	}

	/**
	 * @return the empKeyWords
	 */
	public String getEmpKeyWords() {
		return empKeyWords;
	}

	/**
	 * @param empKeyWords the empKeyWords to set
	 */
	public void setEmpKeyWords(String empKeyWords) {
		this.empKeyWords = empKeyWords;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param prodList the prodList to set
	 */
	public void setProdList(String prodList) {
		this.prodList = prodList;
	}

	/**
	 * @return the docuploaddateend
	 */
	public String getDocuploaddateend() {
		return docuploaddateend;
	}

	/**
	 * @param docuploaddateend the docuploaddateend to set
	 */
	public void setDocuploaddateend(String docuploaddateend) {
		this.docuploaddateend = docuploaddateend;
	}

	/**
	 * @return the docpublishdatestart
	 */
	public String getDocpublishdatestart() {
		return docpublishdatestart;
	}

	/**
	 * @param docpublishdatestart the docpublishdatestart to set
	 */
	public void setDocpublishdatestart(String docpublishdatestart) {
		this.docpublishdatestart = docpublishdatestart;
	}

	/**
	 * @return the docpublishdateend
	 */
	public String getDocpublishdateend() {
		return docpublishdateend;
	}

	/**
	 * @param docpublishdateend the docpublishdateend to set
	 */
	public void setDocpublishdateend(String docpublishdateend) {
		this.docpublishdateend = docpublishdateend;
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

		/** @return the issueDate
	 */
	public String getIssueDate() {
		return issueDate;
	}

	/**
	 * @param issueDate the issueDate to set
	 */
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * @return the deploymentDate
	 */
	public String getDeploymentDate() {
		return deploymentDate;
	}

	/**
	 * @param deploymentDate the deploymentDate to set
	 */
	public void setDeploymentDate(String deploymentDate) {
		this.deploymentDate = deploymentDate;
	}

	/**
	 * @return the prodList
	 */
	public String getProdList() {
		return prodList;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
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
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the additionalLink
	 */
	public String getAdditionalLink() {
		return additionalLink;
	}

	/**
	 * @param additionalLink the additionalLink to set
	 */
	public void setAdditionalLink(String additionalLink) {
		this.additionalLink = additionalLink;
	}

	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * Gets the process.
	 *
	 * @return the process
	 */
	public String getProcess() {
		return process;
	}
	
	/**
	 * Sets the process.
	 *
	 * @param process the process to set
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
	 * @param typeOfContent the typeOfContent to set
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
	 * @param language the language to set
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
	 * @param channel the channel to set
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
	 * @param productName the productName to set
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
	 * @param newsReferenceId the newsReferenceId to set
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
	 * @param keyWord the keyWord to set
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AlfrescoInput [process=");
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
		builder.append(", group=");
		builder.append(group);
		builder.append(", additionalLink=");
		builder.append(additionalLink);
		builder.append(", label=");
		builder.append(label);
		builder.append(", docName=");
		builder.append(docName);
		builder.append(", docpublishdateend=");
		builder.append(docpublishdateend);
		builder.append(", docpublishdatestart=");
		builder.append(docpublishdatestart);
		builder.append(", docType=");
		builder.append(docType);
		builder.append(", docuploaddateend=");
		builder.append(docuploaddateend);
		builder.append(", docuploaddatestart=");
		builder.append(docuploaddatestart);
		builder.append(", date=");
		builder.append(date);
		builder.append(", deploymentDate=");
		builder.append(deploymentDate);
		builder.append(", deptName=");
		builder.append(deptName);
		builder.append(", descOldEmp=");
		builder.append(descOldEmp);
		builder.append(", searchbytype=");
		builder.append(searchbytype);
		builder.append(", matchMethod=");
		builder.append(matchMethod);
		builder.append(", isConfidential=");
		builder.append(isConfidential);
		builder.append(", issueDate=");
		builder.append(issueDate);
		builder.append(", issuedBy=");
		builder.append(issuedBy);
		builder.append(", orderNumber=");
		builder.append(orderNumber);
		builder.append(", empKeyWords=");
		builder.append(empKeyWords);
		builder.append(", year=");
		builder.append(year);
		builder.append(", prodList=");
		builder.append(prodList);
		builder.append("]");
		return builder.toString();
	}

	
	
}

