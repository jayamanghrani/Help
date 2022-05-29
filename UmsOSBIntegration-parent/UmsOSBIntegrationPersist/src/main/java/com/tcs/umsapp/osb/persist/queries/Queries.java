package com.tcs.umsapp.osb.persist.queries;

public class Queries {
	public static final String ProvisionList ="select upd.upd_prov_id, upd.upd_appl_id,  urm.urm_role_code, upd.upd_prov_action, upd.upd_modified_by, upd.upd_modified_date , uum.uum_usr_id, uum.uum_first_name, uum.uum_middle_name, uum.uum_last_name, upd.upd_branch_id ,uum.uum_gender, uum.uum_dob, uum.uum_addr1, uum.uum_addr2, uum.uum_addr3, uum.uum_city, uum.uum_state, uum.uum_country, uum.uum_pin, uum.uum_email, uum.uum_phone_no, uum.uum_mobile, uum.uum_extension, uum.uum_ip_phone, uum.uum_start_date, uum.uum_title, uum.uum_supervisor_id, uum.uum_designation, uum.uum_permission_list, upd_prov_status from ums_prov_dtls upd, ums_usr_mst uum, ums_role_mst urm where upd.upd_prov_status = 'R' and uum.uum_usr_id = upd.upd_usr_id and uum.uum_end_date is null and uum.uum_status = 'E' and upd.upd_appl_id <> 'OID' and upd.upd_appl_id <> 'JIRA' and upd.upd_appl_id <> 'UMS' and upd.upd_appl_id <> 'WORKFLOW' and urm.urm_role_id(+) = upd.upd_role_id AND upd.UPD_MODIFIED_DATE <= sysdate";
	
	public static final String ProvisionListForDeleteUser ="select upd.upd_prov_id, upd.upd_appl_id, urm.urm_role_code, upd.upd_prov_action, upd.upd_modified_by , upd.upd_modified_date , uum.uum_usr_id, uum.uum_first_name, uum.uum_middle_name, uum.uum_last_name, upd.upd_branch_id, 	uum.uum_gender, uum.uum_dob, uum.uum_addr1, uum.uum_addr2, uum.uum_addr3, uum.uum_city, uum.uum_state, uum.uum_country, uum.uum_pin, uum.uum_email, uum.uum_phone_no, uum.uum_mobile, uum.uum_extension, uum.uum_ip_phone, uum.uum_start_date, uum.uum_title, uum.uum_supervisor_id, uum.uum_designation,  uum.uum_permission_list, upd_prov_status from ums_prov_dtls upd, ums_usr_mst uum, ums_role_mst urm where upd.upd_prov_status = 'R' and uum.uum_usr_id = upd.upd_usr_id AND upd.upd_prov_action = 'DU' and upd.upd_appl_id <> 'OID' and upd.upd_appl_id <> 'UMS' and upd.upd_appl_id <> 'JIRA' and upd.upd_appl_id <> 'WORKFLOW' and urm.urm_role_id(+) = upd.upd_role_id";
	
}
