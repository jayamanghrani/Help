/**
 * 
 */
package com.tcs.docstore.persist.query;

/**
 * @author 585226
 *
 */
public class Queries {
	
	StringBuilder queryBuilder = new StringBuilder();
	public static StringBuilder hrmsquery(){
		StringBuilder hrmsqueryBuilder = new StringBuilder();
		hrmsqueryBuilder.append("select a.emplid,a.position_nbr,(select b.descr from sysadm.ps_position_data b where ");
		hrmsqueryBuilder.append("b.position_nbr = a.position_nbr and b.effdt = (select max(b1.effdt) from sysadm.ps_position_data b1 ");
		hrmsqueryBuilder.append("where b1.position_nbr = b.position_nbr and b1.effdt <= sysdate) and b.eff_status = 'A') as ");
		hrmsqueryBuilder.append("Position_descr,a.deptid,(select c.descr from sysadm.ps_dept_tbl c where c.setid = 'NIAIN' ");
		hrmsqueryBuilder.append("and c.deptid = a.deptid and c.effdt = (select max(c1.effdt) from sysadm.ps_dept_tbl c1 ");
		hrmsqueryBuilder.append("where c1.setid = c.setid and c1.deptid =c.deptid and c1.effdt <= sysdate) and c.eff_status = 'A' ) ");
		hrmsqueryBuilder.append("as Dept_Descr,(select name_display from sysadm.ps_names N where n.emplid = a.emplid and n.namE_type = 'PRI' AND ");
		hrmsqueryBuilder.append("N.EFFDT = (SELECT MAX(N1.EFFDT) FROM sysadm.PS_NAMES N1 WHERE N1.EMPLID = N.EMPLID AND N1.NAME_TYPE = " );
		hrmsqueryBuilder.append("N.NAME_TYPE AND N1.EFFDT<=SYSDATE) ) as Name from sysadm.ps_job a where a.effdt = (select max(jb1.effdt) from sysadm.ps_job jb1 ");
		hrmsqueryBuilder.append("where jb1.emplid = a.emplid and jb1.empl_rcd = a.empl_rcd and jb1.effdt <= sysdate) ");
		hrmsqueryBuilder.append("and a.effseq =(select max(jb1.effseq) from sysadm.ps_job jb1 where jb1.emplid = a.emplid and jb1.empl_rcd ");
		hrmsqueryBuilder.append("= a.empl_rcd and jb1.effdt = a.effdt) and a.emplid = (?)");
		return hrmsqueryBuilder;
	}
	
	
public static String getUserAccesQuery ="";

public static String getDepartmentNamesQuery ="";


}
