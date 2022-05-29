package com.tcs.umsrole.request;

public class UserRoleDetails implements Comparable<UserRoleDetails>{

    private String roleName;
    private String applicationName;
    private String roleId;
    private String appId;
    private String status;
    private String officeCode;
    private String startDate;
    private String endDate;
    
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOfficeCode() {
		return officeCode;
	}
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public int compareTo(UserRoleDetails toCheck) {
		 int i = this.roleId.compareTo(toCheck.roleId);
		    if (i != 0) return 1;

		    i = this.officeCode.compareTo(toCheck.officeCode);
		    if (i != 0) return 2;

		    i = this.startDate.compareTo(toCheck.startDate);
		    if (i != 0) return 3;
		    if(null==this.endDate){
		    	if(null==toCheck.endDate) return 0;
		    	else return 4;   
		    }else if(null== toCheck.endDate){
		    	return 4;
		    }else if(this.endDate!=null && toCheck.endDate!=null){
		    	i = this.endDate.compareTo(toCheck.endDate);
			    if (i != 0) return 4;
		    }
		    return 0;

	}

	@Override
	public boolean equals(Object check) {
		
		if (this == check) {
            return true;
        }
		if(null==check){
			return false;
		}
        if (check instanceof UserRoleDetails) {
        	UserRoleDetails anotherObj = (UserRoleDetails)check;
        	
        	return this.getRoleId().compareTo(anotherObj.getRoleId())==0 && 
        			  this.getOfficeCode().compareTo(anotherObj.getOfficeCode())==0 &&
        					this.getStartDate().compareTo(anotherObj.getStartDate())==0 &&
        							this.getEndDate().compareTo(anotherObj.getEndDate())==0 ;   							
        }else
          return false;
	}
	
	@Override
	public int hashCode() {
		int result = 0;
	       result = 31*result + (roleId !=null ? roleId.hashCode() : 0);
	       result = 31*result + (officeCode !=null ? officeCode.hashCode() : 0);
	       result = 31*result + (startDate !=null ? startDate.hashCode() : 0);
	       result = 31*result + (endDate !=null ? endDate.hashCode() : 0);
	       
	       return result;
	}
    
	@Override
	public String toString() {
		return "UserRoleDetails [roleName=" + roleName + ", applicationName="
				+ applicationName + ", roleId=" + roleId + ", appId=" + appId
				+ ", status=" + status + ", officeCode=" + officeCode
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
  
  

}
