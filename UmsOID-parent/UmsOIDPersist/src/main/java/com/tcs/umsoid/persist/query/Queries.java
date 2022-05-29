package com.tcs.umsoid.persist.query;


public class Queries {

	
	public static final String getUserRoleCode = "select urm.urm_role_code from ums_role_mst urm, ums_usr_role_map uurm where uurm.uurm_role_id = urm.urm_role_id and urm.urm_appl_id='OID' and urm.urm_role_id<>'170' and uurm.uurm_end_date is null and uurm.uurm_usr_id = :userId";

	public static final String GetProvisionDetailsAction="SELECT upd_prov_action from ums_prov_dtls where upd_prov_status='R' and upd_prov_id =:provisionID "; 
	
	public static final String GetProvisionDetails = "select upd.upd_prov_id, urm.urm_role_code,upd.upd_role_id,upd.upd_prov_action,upd.upd_modified_by,upd.upd_modified_date,uum.uum_usr_id,uum.uum_first_name,uum.uum_middle_name,uum.uum_last_name,uum.uum_branch_id,uum.uum_gender,uum.uum_email,uum.uum_phone_no,uum.uum_designation,uum.uum_usr_type_id from ums_prov_dtls upd, ums_usr_mst uum, ums_role_mst urm where upd.upd_prov_status = 'R' AND uum.uum_usr_id= upd.upd_usr_id AND uum.uum_end_date IS NULL AND uum.uum_status = 'E' AND upd.upd_appl_id = 'OID' AND urm.urm_role_id(+) = upd.upd_role_id AND upd.upd_prov_id =:provisionID ";

	public static final String GetProvisionDetailsForDeleteUser = "select upd.upd_prov_id, urm.urm_role_code,upd.upd_role_id,upd.upd_prov_action,upd.upd_modified_by,upd.upd_modified_date,uum.uum_usr_id,uum.uum_first_name,uum.uum_middle_name,uum.uum_last_name,uum.uum_branch_id,uum.uum_gender,uum.uum_email,uum.uum_phone_no,uum.uum_designation,uum.uum_usr_type_id from ums_prov_dtls upd, ums_usr_mst uum, ums_role_mst urm where upd.upd_prov_status = 'R' AND uum.uum_usr_id= upd.upd_usr_id AND upd.upd_prov_action ='DU' AND upd.upd_appl_id = 'OID' AND urm.urm_role_id(+) = upd.upd_role_id AND upd.upd_prov_id =:provisionID ";
	
	public static final String InsertRoleActionStatus = "insert into ums_unlock_acct values(:userId,:status,:action,:sysdate)";

}