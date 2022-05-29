package com.tcs.umsapp.upload.controller.util;


import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;

import com.tcs.umsapp.upload.commons.ErrorList;
import com.tcs.umsapp.upload.commons.ExcelError;
import com.tcs.umsapp.upload.controller.ApplicationRoleController;
import com.tcs.umsapp.upload.validation.Validation;

public class ExcelDataValidation {

	private Validation validation;
	private ApplicationRoleController appRoleController;
	private List<String> applicationList;

	private Map<String, String> roleList;
	
	
	public ExcelDataValidation() {
		validation = new Validation();
		appRoleController = new ApplicationRoleController();
		applicationList = appRoleController.getApplicationList();
		roleList = appRoleController.getRoleList();
		
	}

	
	

	public ExcelError validateHeader(Row row) {

		ExcelError excelError = new ExcelError();
		excelError.setRow(0);
		excelError.setColumn(1);

		String[] headers = { "SR Number", "Office Code", "Application", "Role",
				"Action" };

		for (int i = 0; i < 5; i++) {
			if (!headers[i].toLowerCase().equalsIgnoreCase(
					row.getCell(i).toString().toLowerCase()))

				excelError.setError(ErrorList.ERR_HDR_0001);
		}

		return excelError;
	}

	public ExcelError validateSRNumber(String SRNumber, int rowNum) {

		ExcelError excelError = new ExcelError();
		excelError.setRow(rowNum);
		excelError.setColumn(0);
		
		// Check SR number is null or not
		if (SRNumber == null || SRNumber.startsWith("***")) {

			excelError.setError(ErrorList.ERR_SRNUM_0001);
			return excelError;
		}

		// check SR number contain only number or not
		if (!validation.isNum(SRNumber)) {

			excelError.setError(ErrorList.ERR_SRNUM_0002);

		}

		return excelError;
	}

	public ExcelError validateOfficeCode(String officeCode, int rowNum) {

		ExcelError excelError = new ExcelError();
		excelError.setRow(rowNum);
		excelError.setColumn(1);

		// Check Branch Code is null or not
		if (officeCode == null || officeCode.startsWith("***")) {
			excelError.setError(ErrorList.ERR_OFF_0001);
			return excelError;
		}

		// check Branch Code contain only number or not
		if (!validation.isOfficeCode(officeCode)) {
			excelError.setError(ErrorList.ERR_OFF_0002);

		}

		return excelError;

	}

	public ExcelError validateApplication(String app, int rowNum,List<String> userAppList, String loginBranchId) {
		ExcelError excelError = new ExcelError();
		excelError.setRow(rowNum);
		excelError.setColumn(2);

		// Check Application is null or not
		if (app==null) {
			excelError.setError(ErrorList.ERR_APPLID_0001);
			return excelError;
		}
		if(!applicationList.contains(app)){
			excelError.setError(ErrorList.ERR_APPLID_0002);
			return excelError;
		}
		if(!loginBranchId.substring(0, 2).equals("10")){
    		if(!userAppList.contains(app)){
    			excelError.setError(ErrorList.ERR_APPLID_0003);
    		}
		}
		
		return excelError;
	}

	public ExcelError validateRole(String role, int rowNum,String logInUserBranch) {
		ExcelError excelError = new ExcelError();
		excelError.setRow(rowNum);
		excelError.setColumn(3);

		// Check Role is null or not
		if (role==null) {
			excelError.setError(ErrorList.ERR_ROLEID_0001);
			return excelError;
		}
		if(!roleList.containsKey(role) ){
			excelError.setError(ErrorList.ERR_ROLEID_0002);
			return excelError;
		}
		if(!logInUserBranch.equals("100000") && !roleList.get(role).equalsIgnoreCase("N")){
			excelError.setError(ErrorList.ERR_ROLEID_0003);
			return excelError;
		}
		
		return excelError;
	}

	public ExcelError validateAction(String action, int rowNum) {
		ExcelError excelError = new ExcelError();
		excelError.setRow(rowNum);
		excelError.setColumn(4);
		
		// Check Action is null or not
		if (action==null) {
			excelError.setError(ErrorList.ERR_ACTION_0001);
			return excelError;
		}
		if (!action.toLowerCase().equalsIgnoreCase("add") && !action.toLowerCase().equalsIgnoreCase("remove")){
			excelError.setError(ErrorList.ERR_ACTION_0002);
		}
		
		return excelError;
	}

}
