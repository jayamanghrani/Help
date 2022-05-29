package com.tcs.umsrole.persist.query;

public class Query {

	
	public static final String UserRoleRequest = "select distinct uum.uum_usr_id as userID, uum.uum_branch_id as branchID, uum.uum_supervisor_id as supervisorID,uum.uum_first_name  as firstName,uum.uum_last_name as lastName,uum.uum_designation as designation from ums_usr_mst uum where uum.uum_status ='E' and uum.uum_usr_id = :userId";
	public static final String getCountDetails="select count(1) as total from UMS_USR_APPL_MAP uuam where uuam.uuam_appl_id='FINANCIALS'and uuam.uuam_appl_approved_status='Y' and uuam.uuam_end_date is null and uuam.uuam_usr_id = :userId";
}
