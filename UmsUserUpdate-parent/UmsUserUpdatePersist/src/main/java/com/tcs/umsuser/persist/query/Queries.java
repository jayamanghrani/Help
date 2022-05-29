package com.tcs.umsuser.persist.query;

import com.tcs.umsuser.util.UtilConstants;

public class Queries {
	
	public static final String getPendingUserTempDetails ="select uutr.uutr_usr_id, uutr.uutr_title, uutr.uutr_first_name, uutr.uutr_middle_name, uutr.uutr_last_name, uutr.uutr_branch_id, uutr.uutr_gender, uutr.uutr_dob, uutr.uutr_addr1, uutr.uutr_addr2, uutr.uutr_addr3, uutr.uutr_city, uutr.uutr_state, uutr.uutr_country, uutr.uutr_pin,  uutr.uutr_email, uutr.uutr_phone_no, uutr.uutr_mobile, uutr.uutr_extension, uutr.uutr_ip_phone, uutr.uutr_start_date, uutr.uutr_end_date, uutr.uutr_supervisor_id, uutr.uutr_designation, uutr.uutr_created_by, uutr.uutr_created_date, uutr.uutr_modified_by, uutr.uutr_modified_date, uutr.uutr_status, uutr.uutr_effctive_start_date,  uutr.uutr_flg_action, uutr.uutr_rec_id from ums_usr_temp_rec uutr where UUTR_REC_ID=:recordId and uutr.uutr_rec_status = 'U'";

	public static final String getPendingUserTempDetailsForBatch ="select uutr.uutr_usr_id, uutr.uutr_title, uutr.uutr_first_name, uutr.uutr_middle_name, uutr.uutr_last_name, uutr.uutr_branch_id, uutr.uutr_gender, uutr.uutr_dob, uutr.uutr_addr1, uutr.uutr_addr2, uutr.uutr_addr3, uutr.uutr_city, uutr.uutr_state, uutr.uutr_country, uutr.uutr_pin,  uutr.uutr_email, uutr.uutr_phone_no, uutr.uutr_mobile, uutr.uutr_extension, uutr.uutr_ip_phone, uutr.uutr_start_date, uutr.uutr_end_date, uutr.uutr_supervisor_id, uutr.uutr_designation, uutr.uutr_created_by, uutr.uutr_created_date, uutr.uutr_modified_by, uutr.uutr_modified_date, uutr.uutr_status, uutr.uutr_effctive_start_date,  uutr.uutr_flg_action, uutr.uutr_rec_id from ums_usr_temp_rec uutr where uutr.uutr_rec_status = 'U'";
	
	public static final String getUserApplicationId ="select distinct (uuam.uuam_appl_id) from ums_usr_appl_map uuam where (uuam.uuam_end_date is null or uuam.uuam_appl_id='IIMS') and uuam.uuam_usr_id =:userId";

	public static final String updateAppMap="update ums_usr_appl_map uuam set uuam.uuam_end_date = sysdate, uuam.uuam_modified_by =:modifiedBy, uuam.uuam_modified_date = sysdate where  uuam.uuam_usr_id =:userId and  uuam.uuam_end_date is null and  uuam.uuam_appl_id not in ('PORTALDB','OID','HRMS','JIRA')";

	public static final String updateRoleMap ="update ums_usr_role_map uurm set  uurm.uurm_end_date = sysdate, uurm.uurm_modified_by =:modifiedBy, uurm.uurm_modified_date = sysdate where uurm.uurm_usr_id =:userId and uurm.uurm_role_id not in ('170','481','677','466','"+UtilConstants.JIRA_UTKARSH_ENDUSER+"') and uurm.uurm_end_date is null";

	public static final String updatePermListHistory ="update ums_perm_list_history uplh set uplh.uplh_end_date=sysdate where uplh.uplh_usr_id=:userId and uplh.uplh_end_date is null  and uplh.uplh_pl_id not in ('HCDPALL')";

	public static final String updateUserMst ="update ums_usr_mst uum set uum.uum_permission_list='HCDPALL' where uum.uum_usr_id=:userId and uum.uum_end_date is null";

	public static final String updateUmsUserAccess ="update ums_umsusr_access uua  set uua.uua_end_date=sysdate where uua.uua_usr_id=:userId and uua.uua_end_date is null";

	public static final String updateUmsUserRoleMap ="update ums_umsusr_umsrole_map uuum set uuum.uuum_end_date=sysdate where uuum.uuum_usr_id=:userId and uuum.uuum_end_date is null";

	public static final String updateUserTempStatus ="update ums_usr_temp_rec uutr set uutr.uutr_rec_status = 'P', uutr.uutr_rec_operation =:action, uutr.uutr_rec_remark = 'processed successfully', uutr.uutr_rec_modified_by = 'UMS', uutr.uutr_rec_modified_date = SYSDATE where uutr.uutr_rec_id =:temp_rec_id";

	public static final String errorUpdate ="update ums_usr_temp_rec uutr set uutr.uutr_rec_status = 'R', uutr.uutr_rec_operation =:action, uutr.uutr_rec_sqlcode =:sqlErrcode, uutr.uutr_rec_sqlerrm =:sqlErrMsg, uutr.uutr_rec_remark = 'rejected', uutr.uutr_rec_modified_by   = 'UMS', uutr.uutr_rec_modified_date = SYSDATE where uutr.uutr_rec_id =:temp_rec_id";

	public static final String checkUserExist="select count(1) from ums_usr_mst uum1 where uum1.uum_usr_id =:userId";
	
	public static final String updateUmsUsrMst="update ums_usr_mst uum set uum.uum_end_date = SYSDATE, uum.uum_status = 'D', uum.uum_modified_by =:modifiedBy , uum.uum_modified_date = sysdate where uum.uum_usr_id =:userId and uum.uum_end_date is null and uum.uum_status = 'E'";
	
	public static final String updateUmsUsrAppl="update ums_usr_appl_map uuam set uuam.uuam_end_date = sysdate, uuam.uuam_modified_by =:modifiedBy , uuam.uuam_modified_date = sysdate where uuam.uuam_usr_id =:userId and uuam.uuam_end_date is null";
	
	public static final String updateUmsUsrRole="update ums_usr_role_map uurm set uurm.uurm_end_date = sysdate, uurm.uurm_modified_by =:modifiedBy , uurm.uurm_modified_date = sysdate where uurm.uurm_usr_id =:userId and uurm.uurm_end_date is null";
	
	public static final String getExistingUserDetails ="Select uum from UserMasterUpdate uum where userId=:userId";
	
	public static final String insertInPortalDB= "select count(1) from ums_usr_appl_map uuam where uuam.uuam_usr_id =:userId and uuam.uuam_appl_id = 'PORTALDB' and uuam.uuam_end_date is null";
}
