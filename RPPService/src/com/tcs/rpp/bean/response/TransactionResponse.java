package com.tcs.rpp.bean.response;

import java.util.ArrayList;
import java.util.List;

public class TransactionResponse {

    public String status;
    public String message;
    public List<TransactionDetails> data=new ArrayList<>();
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
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * @return the data
     */
    public List<TransactionDetails> getData() {
        return data;
    }
    /**
     * @param data the data to set
     */
    public void setData(List<TransactionDetails> data) {
        this.data = data;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TransactionResponse [status=" + status + ", message=" + message
                + ", data=" + data + ", getStatus()=" + getStatus()
                + ", getMessage()=" + getMessage() + ", getData()=" + getData()
                + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }
    
    
}
