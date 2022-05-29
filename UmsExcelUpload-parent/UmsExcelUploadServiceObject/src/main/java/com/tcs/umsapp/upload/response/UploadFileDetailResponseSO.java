package com.tcs.umsapp.upload.response;

import java.util.List;

import com.tcs.umsapp.upload.commons.AppRoleId;
import com.tcs.umsapp.upload.commons.ExcelError;
import com.tcs.umsapp.upload.commons.FileContent;
import com.tcs.umsapp.upload.vo.cmo.UmsappResponseObject;

public class UploadFileDetailResponseSO extends UmsappResponseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5959535573561505583L;
	
	private List<FileContent> userRoles;
	
   
	private String requestBy; 
	private String status;
	private List<ExcelError> errorList;
	private List<AppRoleId> appRoleId;
	
	 public String getRequestBy() {
			return requestBy;
		}

		public void setRequestBy(String requestBy) {
			this.requestBy = requestBy;
		}


	public List<AppRoleId> getAppRoleId() {
		return appRoleId;
	}

	public void setAppRoleId(List<AppRoleId> appRoleId) {
		this.appRoleId = appRoleId;
	}

	public List<FileContent> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<FileContent> userRoles) {
		this.userRoles = userRoles;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ExcelError> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<ExcelError> errorList) {
		this.errorList = errorList;
	}
	
	
	
}
