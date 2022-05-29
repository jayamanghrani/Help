package com.tcs.umsrole.response;

import com.tcs.umsrole.vo.cmo.UmsRoleResponseObject;

public class ProvisionDetailsResponseASBO extends UmsRoleResponseObject{
private Long provId;
private String resultStatus;
private String statusMessage;

public String getResultStatus() {
    return resultStatus;
}

public void setResultStatus(String resultStatus) {
    this.resultStatus = resultStatus;
}

public String getStatusMessage() {
    return statusMessage;
}

public void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
}

public Long getProvId() {
    return provId;
}

public void setProvId(Long provId) {
    this.provId = provId;
}

@Override
public String toString() {
	return "ProvisionDetailsResponseASBO [provId=" + provId + ", resultStatus="
			+ resultStatus + ", statusMessage=" + statusMessage + "]";
}



}
