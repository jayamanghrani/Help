package com.tcs.umsapp.search.exception.cmo;

public class ValidationException extends Exception {

    /**
	 * 
	 */
    private static final long serialVersionUID = 4004665790331367419L;

    private int errorCode;
    private String field;

    public ValidationException() {
        super();
    }

    /**
     * 
     * @param errorCode
     * @param field
     */
    public ValidationException(int errorCode, String field) {
        super();
        this.errorCode = errorCode;
        this.field = field;
    }

    /**
     * @return
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 
     * @return
     */
    public String getField() {
        return field;
    }

    /**
     * 
     * @param field
     */
    public void setField(String field) {
        this.field = field;
    }

}
