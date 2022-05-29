package com.tcs.rpp.bean.request;

import java.io.Serializable;

public class FileUploadRequest implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String merchantCode;
    private String bankCode;
    private String fileName;
    private String fileContent;
    private String remitterAccountNumber;
    private String totalTransactionCount;
    /**
     * @return the merchantCode
     */
    public String getMerchantCode() {
        return merchantCode;
    }
    /**
     * @param merchantCode the merchantCode to set
     */
    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }
    /**
     * @return the bankCode
     */
    public String getBankCode() {
        return bankCode;
    }
    /**
     * @param bankCode the bankCode to set
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
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
     * @return the fileContent
     */
    public String getFileContent() {
        return fileContent;
    }
    /**
     * @param fileContent the fileContent to set
     */
    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }
    /**
     * @return the remitterAccountNumber
     */
    public String getRemitterAccountNumber() {
        return remitterAccountNumber;
    }
    /**
     * @param remitterAccountNumber the remitterAccountNumber to set
     */
    public void setRemitterAccountNumber(String remitterAccountNumber) {
        this.remitterAccountNumber = remitterAccountNumber;
    }
    /**
     * @return the totalTransactionCount
     */
    public String getTotalTransactionCount() {
        return totalTransactionCount;
    }
    /**
     * @param totalTransactionCount the totalTransactionCount to set
     */
    public void setTotalTransactionCount(String totalTransactionCount) {
        this.totalTransactionCount = totalTransactionCount;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FileUploadRequest [merchantCode=" + merchantCode
                + ", bankCode=" + bankCode + ", fileName=" + fileName
                + ", fileContent=" + fileContent + ", remitterAccountNumber="
                + remitterAccountNumber + ", totalTransactionCount="
                + totalTransactionCount + "]";
    }
    
    
    
}
