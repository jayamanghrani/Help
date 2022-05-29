package com.tcs.umsapp.upload.persist.query;

public class Queries {

	public static final String UserBranch = "select uum_branch_id from ums_usr_mst where uum_usr_id = :userId";
	
	public static final String UserApplication ="select uam_appl_name from ums_appl_mst where uam_appl_id in (select uuam_appl_id from ums_usr_appl_map where uuam_usr_id = :userId)";

    public static final String AppRoleIdSearch = "select distinct urm.urm_role_id, uam.uam_appl_id from ums_usr_role_map uurm,ums_role_mst urm,ums_appl_mst uam where uurm.uurm_role_id = urm.urm_role_id and urm.urm_appl_id = uam.uam_appl_id and uam.uam_appl_name = :appName and urm.urm_role_name = :roleName";
    
    public static final String RoleId = "select urm_role_id from ums_role_mst where urm_role_name = :Role and urm_appl_id = :AppId";
    
    public static final String UserApplicationId = "select uam_appl_id from ums_appl_mst where uam_appl_name = :appName";
}
