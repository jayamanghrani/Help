package com.nia.jpa.dto;

import java.io.Serializable;

public class Program_Hold_DTO implements Serializable {
 protected String programName;
 protected String requestId;
public String getProgramName() {
	return programName;
}
public void setProgramName(String programName) {
	this.programName = programName;
}
public String getRequestId() {
	return requestId;
}
public void setRequestId(String requestId) {
	this.requestId = requestId;
};


}
