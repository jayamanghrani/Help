package com.tcs.umsapp.smsmail.so.request;

public class SmsContentRequestSO {

    private String body;
    private String mobileNumber;
    

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }


    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }


    /**
     * @return the mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }


    /**
     * @param mobileNumber the mobileNumber to set
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    @Override
    public String toString() {
        return "SmsContentRequestSO [body=" + body + ", mobileNumber="
                + mobileNumber + "]";
    }

}
