package com.tcs.umsrole.response;

import com.tcs.umsrole.vo.cmo.UmsRoleResponseObject;

public class AppDetailsResponseASBO extends UmsRoleResponseObject{
private String status;

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

}
