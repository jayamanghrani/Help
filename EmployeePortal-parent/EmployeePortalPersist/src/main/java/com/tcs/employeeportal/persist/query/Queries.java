/**
 * 
 */
package com.tcs.employeeportal.persist.query;

/**
 * @author 585226
 *
 */
public class Queries {
	
	public static StringBuilder reportsquery(){

		StringBuilder reportsqueryBuilder = new StringBuilder();
		reportsqueryBuilder.append("SELECT MAX(run_date) A,ROUND(SUM(for_mnth_Premium), 0) B, ROUND(SUM(for_day_premium), 0) C,");
		reportsqueryBuilder.append(" ROUND(SUM(Up_to_premium), 0) D FROM ( (SELECT '' run_date, SUM(dp.curr_yr_upto_prm) / 10000000 Up_to_premium, SUM(CASE WHEN TO_CHAR(SYSDATE, 'DD')= '01' THEN ");
		reportsqueryBuilder.append("0 ELSE (dp.curr_yr_for_mnth_prm) END) / 10000000  AS for_mnth_Premium, 0 for_day_premium FROM ODS.daily_premium_accretion dp WHERE dp.run_date =(SELECT TRUNC(MAX(dp1.last_update_date - 1)) FROM ODS.daily_premium_accretion dp1 WHERE DP1.RUN_DATE >=(sysdate-10))GROUP BY dp.run_date ) UNION ALL");
		reportsqueryBuilder.append("(SELECT TO_CHAR(t.date_time) run_date,t.premium_underwritten / 10000000 HR_premium, t.premium_underwritten / 10000000 HR_premium1,t.premium_underwritten / 10000000 HR_premium1 FROM ODS.ticker_data_current t));");
		return reportsqueryBuilder;
	}
	
}
