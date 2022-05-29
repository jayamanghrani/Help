package com.tcs.umsapp.smsmail.so.request;

public class MailContentRequestSO {

    private String sender;
    private String subject;
    private String recepient;
    private String body;
    private String cc;

    /**
     * @return the sender
     */
    public String getSender() {
        return sender;
    }


    /**
     * @param sender the sender to set
     */
    public void setSender(String sender) {
        this.sender = sender;
    }


    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }


    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }


    /**
     * @return the recepient
     */
    public String getRecepient() {
        return recepient;
    }


    /**
     * @param recepient the recepient to set
     */
    public void setRecepient(String recepient) {
        this.recepient = recepient;
    }


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
     * @return the cc
     */
    public String getCc() {
        return cc;
    }


    /**
     * @param cc the cc to set
     */
    public void setCc(String cc) {
        this.cc = cc;
    }


    @Override
    public String toString() {
        return "GetContentRequestSO [sender=" + sender + ", subject=" + subject
                + ", recepient=" + recepient + ", body=" + body + ", cc=" + cc
                + "]";
    }
}