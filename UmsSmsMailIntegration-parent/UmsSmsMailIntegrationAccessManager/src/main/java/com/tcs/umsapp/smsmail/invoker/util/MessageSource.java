package com.tcs.umsapp.smsmail.invoker.util;

/**
 * The Class MessageSource.
 */
public class MessageSource {

    /** The proxy host. */
    private String proxyHost;

    /** The proxy server port. */
    private int proxyServerPort;

    private String smsSenderId;
//    /** The proxy user. */
//    private String proxyUser;
//
//    /** The proxy password. */
//    private String proxyPassword;

//    /** The encode format. */
//    private String encodeFormat;
//
//    /** The version. */
//    private String version;

    /** The content type. */
//    private String contentType;

    private String userId;

    private String password;

    /** The scheme. */
//    private String scheme;

    /** The sms provider url. */
    private String smsProviderUrl;

    private String msgBody;

    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the proxy host.
     *
     * @return the proxy host
     */
    public String getProxyHost() {
        return proxyHost;
    }

    /**
     * Sets the proxy host.
     *
     * @param proxyHost
     *            the new proxy host
     */
    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    /**
     * Gets the proxy server port.
     *
     * @return the proxy server port
     */
    public int getProxyServerPort() {
        return proxyServerPort;
    }

    /**
     * Sets the proxy server port.
     *
     * @param proxyServerPort
     *            the new proxy server port
     */
    public void setProxyServerPort(int proxyServerPort) {
        this.proxyServerPort = proxyServerPort;
    }

    /**
     * Gets the proxy user.
     *
     * @return the proxy user
//     */
//    public String getProxyUser() {
//        return proxyUser;
//    }
//
//    /**
//     * Sets the proxy user.
//     *
//     * @param proxyUser
//     *            the new proxy user
//     */
//    public void setProxyUser(String proxyUser) {
//        this.proxyUser = proxyUser;
//    }
//
//    /**
//     * Gets the proxy password.
//     *
//     * @return the proxy password
//     */
//    public String getProxyPassword() {
//        return proxyPassword;
//    }

    /**
     * Sets the proxy password.
     *
     * @param proxyPassword
     *            the new proxy password
     */
//    PUBLIC VOID SETPROXYPASSWORD(STRING PROXYPASSWORD) {
//        THIS.PROXYPASSWORD = PROXYPASSWORD;
//    }

    /**
     * Gets the encode format.
     *
     * @return the encode format
//     */
//    public String getEncodeFormat() {
//        return encodeFormat;
//    }

    /**
     * Sets the encode format.
     *
     * @param encodeFormat
     *            the new encode format
     */
//    public void setEncodeFormat(String encodeFormat) {
//        this.encodeFormat = encodeFormat;
//    }

    /**
     * Gets the version.
     *
     * @return the version
     */
//    public String getVersion() {
//        return version;
//    }

    /**
     * Sets the version.
     *
     * @param version
     *            the new version
     */
//    public void setVersion(String version) {
//        this.version = version;
//    }

    /**
     * Gets the content type.
     *
     * @return the content type
     */
//    public String getContentType() {
//        return contentType;
//    }

    /**
     * Sets the content type.
     *
     * @param contentType
     *            the new content type
     */
//    public void setContentType(String contentType) {
//        this.contentType = contentType;
//    }

    /**
     * Gets the scheme.
     *
     * @return the scheme
     */
//    public String getScheme() {
//        return scheme;
//    }
//
//    /**
//     * Sets the scheme.
//     *
//     * @param scheme
//     *            the new scheme
//     */
//    public void setScheme(String scheme) {
//        this.scheme = scheme;
//    }

    /**
     * Gets the sms provider url.
     *
     * @return the sms provider url
     */
    public String getSmsProviderUrl() {
        return smsProviderUrl;
    }

    /**
     * Sets the sms provider url.
     *
     * @param smsProviderUrl
     *            the new sms provider url
     */
    public void setSmsProviderUrl(String smsProviderUrl) {
        this.smsProviderUrl = smsProviderUrl;
    }



    /**
     * @return the smsSenderId
     */
    public String getSmsSenderId() {
        return smsSenderId;
    }

    /**
     * @param smsSenderId the smsSenderId to set
     */
    public void setSmsSenderId(String smsSenderId) {
        this.smsSenderId = smsSenderId;
    }

}
