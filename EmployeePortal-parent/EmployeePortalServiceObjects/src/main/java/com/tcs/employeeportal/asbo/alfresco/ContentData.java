package com.tcs.employeeportal.asbo.alfresco;

import java.io.Serializable;
import java.util.List;

import com.tcs.employeeportal.asbo.alfresco.ContentData;

public class ContentData implements Serializable, Comparable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7345085225670080737L;

	/** The title. */
	private String title;
	
	/** The content. */
	private String content;
	
	/** The order num. */
	private int orderNum;
	
	/** The product. */
	//private List<String> product;
	
	/** The channel. */
	private List<String> channel;
	
	/** The additional link. */
	private String additionalLink;
	
	/** The label. */
	private String label;
	
	/** The accordion header. */
	private String accordionHeader;
	
	/** The process. */
	private List<String> process;
	
	/** The description. */
	private String description;
	
	/** The image link. */
	private String imageLink;
	
	/** The image text. */
	private String imageText;
	
	/** The image content url. */
	private String imageContentURL;
	
	/** The doc name. */
	private String docName;
	
	
	
	/** The doc url. */
	private String docUrl;
	
	
	private String uploadDate;
	
	private String publishDate;
	
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
	private String confidential;
	
	
	/** The news ref id. */
	private String newsRefId;

	
	/** The search link. */
	private String searchLink;
	
	/** The product. */
	private List<String> keyWord;
	
	/** The content end date. */
	private String contentEndDate;
	
	/** The content start date. */
	private String contentStartDate;
	
	/** The modified date. */
	private String modifiedDate;
	
	/** The display type. */
	private String displayType;
	
	/** The doc size. */
	private String docSize;
	
	/** The doc mime type. */
	private String docMimeType;
	
	/** The visible to */
	private String visibleTo;
	
	/**
	 * @return the visibleTo
	 */
	public String getVisibleTo() {
		return visibleTo;
	}

	/**
	 * @param visibleTo the visibleTo to set
	 */
	public void setVisibleTo(String visibleTo) {
		this.visibleTo = visibleTo;
	}

	/** The group. */
	private List<String> group;
	
	// added for document Search functionality
	
	private String fileName;
	private String depatmentName;
	private String documentType;
	private String searchBy;
	private String docUploadDateStart;
	private String docUploadDateEnd;
	
	private String statusOfDocSearch; // added to privide the details to team if any user data is found in the search parameters
	

	
	
	// added for document Search functionlity
	
	/**
	 * @return the statusOfDocSearch
	 */
	public String getStatusOfDocSearch() {
		return statusOfDocSearch;
	}

	/**
	 * @param statusOfDocSearch the statusOfDocSearch to set
	 */
	public void setStatusOfDocSearch(String statusOfDocSearch) {
		this.statusOfDocSearch = statusOfDocSearch;
	}

	/**
	 * @return the uploadDate
	 */
	public String getUploadDate() {
		return uploadDate;
	}

	/**
	 * @param uploadDate the uploadDate to set
	 */
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	/**
	 * @return the publishDate
	 */
	public String getPublishDate() {
		return publishDate;
	}

	/**
	 * @param publishDate the publishDate to set
	 */
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
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
	 * @return the prodList
	 */
	public String getProdList() {
		return prodList;
	}

	/**
	 * @param prodList the prodList to set
	 */
	public void setProdList(String prodList) {
		this.prodList = prodList;
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
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the confidential
	 */
	public String getConfidential() {
		return confidential;
	}

	/**
	 * @param confidential the confidential to set
	 */
	public void setConfidential(String confidential) {
		this.confidential = confidential;
	}

	/**
	 * @return the accordionHeader
	 */
	public String getAccordionHeader() {
		return accordionHeader;
	}

	/**
	 * @param accordionHeader the accordionHeader to set
	 */
	public void setAccordionHeader(String accordionHeader) {
		this.accordionHeader = accordionHeader;
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
	 * @return the imageText
	 */
	public String getImageText() {
		return imageText;
	}

	/**
	 * @param imageText the imageText to set
	 */
	public void setImageText(String imageText) {
		this.imageText = imageText;
	}

	/**
	 * @return the searchLink
	 */
	public String getSearchLink() {
		return searchLink;
	}

	/**
	 * @param searchLink the searchLink to set
	 */
	public void setSearchLink(String searchLink) {
		this.searchLink = searchLink;
	}

	/**
	 * @return the group
	 */
	public List<String> getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(List<String> group) {
		this.group = group;
	}

	/**
	 * Gets the doc mime type.
	 *
	 * @return the docMimeType
	 */
	public String getDocMimeType() {
		return docMimeType;
	}

	/**
	 * Sets the doc mime type.
	 *
	 * @param docMimeType the docMimeType to set
	 */
	public void setDocMimeType(String docMimeType) {
		this.docMimeType = docMimeType;
	}

	/**
	 * Gets the doc size.
	 *
	 * @return the docSize
	 */
	public String getDocSize() {
		return docSize;
	}

	/**
	 * Sets the doc size.
	 *
	 * @param docSize the docSize to set
	 */
	public void setDocSize(String docSize) {
		this.docSize = docSize;
	}

	/**
	 * Gets the display type.
	 *
	 * @return the displayType
	 */
	public String getDisplayType() {
		return displayType;
	}

	/**
	 * Sets the display type.
	 *
	 * @param displayType the displayType to set
	 */
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets the order num.
	 *
	 * @return the orderNum
	 */
	public int getOrderNum() {
		return orderNum;
	}

	/**
	 * Sets the order num.
	 *
	 * @param orderNum            the orderNum to set
	 */
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * Gets the product.
	 *
	 * @return the product
	 */
/*	public List<String> getProduct() {
		return product;
	}

	*//**
	 * Sets the product.
	 *
	 * @param product            the product to set
	 *//*
	public void setProduct(List<String> product) {
		this.product = product;
	}*/

	/**
	 * Gets the channel.
	 *
	 * @return the channel
	 */
	public List<String> getChannel() {
		return channel;
	}

	/**
	 * Sets the channel.
	 *
	 * @param channel            the channel to set
	 */
	public void setChannel(List<String> channel) {
		this.channel = channel;
	}

	/**
	 * Gets the additional link.
	 *
	 * @return the additionalLink
	 */
	public String getAdditionalLink() {
		return additionalLink;
	}

	/**
	 * Sets the additional link.
	 *
	 * @param additionalLink            the additionalLink to set
	 */
	public void setAdditionalLink(String additionalLink) {
		this.additionalLink = additionalLink;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}


	/**
	 * Gets the accordion header.
	 *
	 * @return the accordionHeader
	 *//*
	public String getAccordionHeader() {
		return accordionHeader;
	}
*/
	/**
	 * Sets the accordion header.
	 *
	 * @param accordionHeader            the accordionHeader to set
	 */
/*	public void setAccordionHeader(String accordionHeader) {
		this.accordionHeader = accordionHeader;
	}

	*//**
	 * Gets the process.
	 *
	 * @return the process
	 */
	public List<String> getProcess() {
		return process;
	}

	/**
	 * Sets the process.
	 *
	 * @param process            the process to set
	 */
	public void setProcess(List<String> process) {
		this.process = process;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the image link.
	 *
	 * @return the imageLink
	 */
	public String getImageLink() {
		return imageLink;
	}

	/**
	 * Sets the image link.
	 *
	 * @param imageLink            the imageLink to set
	 */
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	/**
	 * Gets the image content url.
	 *
	 * @return the imageContentURL
	 */
	public String getImageContentURL() {
		return imageContentURL;
	}

	/**
	 * Sets the image content url.
	 *
	 * @param imageContentURL            the imageContentURL to set
	 */
	public void setImageContentURL(String imageContentURL) {
		this.imageContentURL = imageContentURL;
	}

	/**
	 * Gets the doc name.
	 *
	 * @return the docName
	 */
	public String getDocName() {
		return docName;
	}

	/**
	 * Sets the doc name.
	 *
	 * @param docName            the docName to set
	 */
	public void setDocName(String docName) {
		this.docName = docName;
	}

	/**
	 * Gets the doc url.
	 *
	 * @return the docUrl
	 */
	public String getDocUrl() {
		return docUrl;
	}

	/**
	 * Sets the doc url.
	 *
	 * @param docUrl            the docUrl to set
	 */
	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}

	/**
	 * Gets the answer.
	 *
	 * @return the answer
	 */
	/*public String getAnswer() {
		return answer;
	}

	*//**
	 * Sets the answer.
	 *
	 * @param answer            the answer to set
	 *//*
	public void setAnswer(String answer) {
		this.answer = answer;
	}*/

	/**
	 * Gets the question.
	 *
	 * @return the question
	 */
	/*public String getQuestion() {
		return question;
	}

	*//**
	 * Sets the question.
	 *
	 * @param question            the question to set
	 *//*
	public void setQuestion(String question) {
		this.question = question;
	}*/



	/**
	 * Gets the news ref id.
	 *
	 * @return the newsRefId
	 */
	public String getNewsRefId() {
		return newsRefId;
	}

	/**
	 * Sets the news ref id.
	 *
	 * @param newsRefId            the newsRefId to set
	 */
	public void setNewsRefId(String newsRefId) {
		this.newsRefId = newsRefId;
	}

	

	/**
	 * Gets the search link.
	 *
	 * @return the searchLink
	 */
	/*public String getSearchLink() {
		return searchLink;
	}

	*//**
	 * Sets the search link.
	 *
	 * @param searchLink the searchLink to set
	 *//*
	public void setSearchLink(String searchLink) {
		this.searchLink = searchLink;
	}
	*/
	

	/**
	 * Gets the key word.
	 *
	 * @return the keyWord
	 */
	public List<String> getKeyWord() {
		return keyWord;
	}

	/**
	 * Sets the key word.
	 *
	 * @param keyWord the keyWord to set
	 */
	public void setKeyWord(List<String> keyWord) {
		this.keyWord = keyWord;
	}

	

	/**
	 * Gets the content end date.
	 *
	 * @return the contentEndDate
	 */
	public String getContentEndDate() {
		return contentEndDate;
	}

	/**
	 * Sets the content end date.
	 *
	 * @param contentEndDate the contentEndDate to set
	 */
	public void setContentEndDate(String contentEndDate) {
		this.contentEndDate = contentEndDate;
	}

	/**
	 * Gets the content start date.
	 *
	 * @return the contentStartDate
	 */
	public String getContentStartDate() {
		return contentStartDate;
	}

	/**
	 * Sets the content start date.
	 *
	 * @param contentStartDate the contentStartDate to set
	 */
	public void setContentStartDate(String contentStartDate) {
		this.contentStartDate = contentStartDate;
	}
	
	

	/**
	 * Gets the modified date.
	 *
	 * @return the modifiedDate
	 */
	public String getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * Sets the modified date.
	 *
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContentData [title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", orderNum=");
		builder.append(orderNum);
		/*builder.append(", product=");
		builder.append(product);*/
		builder.append(", channel=");
		builder.append(channel);
		builder.append(", additionalLink=");
		builder.append(additionalLink);
		builder.append(", label=");
		builder.append(label);
		builder.append(", accordionHeader=");
		builder.append(accordionHeader);
		builder.append(", process=");
		builder.append(process);
		builder.append(", description=");
		builder.append(description);
		builder.append(", imageLink=");
		builder.append(imageLink);
		builder.append(", imageContentURL=");
		builder.append(imageContentURL);
		builder.append(", docName=");
		builder.append(docName);
		builder.append(", docUrl=");
		builder.append(docUrl);
		/*builder.append(", answer=");
		builder.append(answer);
		builder.append(", question=");
		builder.append(question);*/
		builder.append(", publishEndDate=");
		builder.append(publishDate);
		builder.append(", newsRefId=");
		builder.append(newsRefId);
		builder.append(", publishStartDate=");
		builder.append(publishDate);    
		/*builder.append(", searchLink=");
		builder.append(searchLink);*/
		builder.append(", keyWord=");
		builder.append(keyWord);
		builder.append(", contentEndDate=");
		builder.append(contentEndDate);
		builder.append(", contentStartDate=");
		builder.append(contentStartDate);
		builder.append(", group=");
		builder.append(group);
		builder.append("]");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object o) {
		 Integer p1 =((ContentData) o).getOrderNum();
	      

	       if (this.orderNum > p1) {
	           return 1;
	       } else if (this.orderNum < p1){
	           return -1;
	       } else {
	           return 0;
	       }
	}
	 

}
