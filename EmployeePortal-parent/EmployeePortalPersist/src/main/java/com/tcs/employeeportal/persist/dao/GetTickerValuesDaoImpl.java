/**
 * 
 */
package com.tcs.employeeportal.persist.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import com.tcs.employeeportal.config.utils.PropertiesUtil;
import com.tcs.employeeportal.config.utils.UtilProperties;
import com.tcs.employeeportal.db.asbo.request.TickerDBRequestASBO;
import com.tcs.employeeportal.db.asbo.response.TickerDBResponseASBO;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;
/**
 * @author 585226
 *
 */
public class GetTickerValuesDaoImpl implements GetTickerValuesDao {
	
	 Connection conn = null;
	  Statement stmt = null;
	  ResultSet rs = null;
	  private static final Logger LOGGER = Logger.getLogger(GetTickerValuesDaoImpl.class);
	  TickerDBResponseASBO tdrasbo = new TickerDBResponseASBO();
	  
		@SuppressWarnings("unchecked")
		@Override
	public EmployeePortalResponseObject getTickerValues(
			TickerDBRequestASBO getTickerValues) {

			return null;
			}

		@Override
		public List<String> getTickerListValues(
				TickerDBRequestASBO getTickerValues) throws Exception {

			Context ctx = null;
		
			  ctx = new InitialContext();
			    
			  javax.sql.DataSource ds 
		      = (javax.sql.DataSource) ctx.lookup (UtilProperties.getReportsDS());
			    conn = ds.getConnection();
			    LOGGER.info("GetTickerValuesDaoImpl thec connection name is "+conn.getClass());
			Statement stmt=conn.createStatement(); 
			StringBuilder reportsqueryBuilder = new StringBuilder();
			reportsqueryBuilder.append("SELECT MAX(run_date) A, ROUND(SUM(for_mnth_Premium), 0) B, ROUND(SUM(for_day_premium), 0) C,  ROUND(SUM(Up_to_premium), 0) D FROM (   (SELECT '' run_date,     SUM(dp.curr_yr_upto_prm)     / 10000000 Up_to_premium,     SUM(CASE WHEN TO_CHAR(SYSDATE, 'DD')= '01' THEN     0      ELSE (dp.curr_yr_for_mnth_prm) END) / 10000000  AS for_mnth_Premium,     0 for_day_premium   FROM ODS.daily_premium_accretion dp   WHERE dp.run_date =      (SELECT TRUNC(MAX(dp1.last_update_date - 1))     FROM ODS.daily_premium_accretion dp1     WHERE DP1.RUN_DATE >=(sysdate-10)     )   GROUP BY dp.run_date ) UNION ALL(SELECT TO_CHAR(t.date_time) run_date,     t.premium_underwritten / 10000000 HR_premium,     t.premium_underwritten / 10000000 HR_premium1,     t.premium_underwritten / 10000000 HR_premium1   FROM ODS.ticker_data_current t   )) ");
			
			LOGGER.info("GetTickerValuesDaoImpl the query to be executed is--> "+reportsqueryBuilder);
			//step4 execute query  
			ResultSet rs=stmt.executeQuery(reportsqueryBuilder.toString());
			LOGGER.info("GetTickerValuesDaoImpl the rs.next() is  before while--> "+rs.next());
			LOGGER.info("GetTickerValuesDaoImpl the date of premium is "+rs.getString("A"));
			LOGGER.info("GetTickerValuesDaoImpl the monthly premium of premium is "+rs.getInt(2));
			LOGGER.info("GetTickerValuesDaoImpl the todays  of premium is "+rs.getInt(3));
			LOGGER.info("GetTickerValuesDaoImpl the uptodate of premium is "+rs.getInt(4));
			
			List<String> tickerValuesList = new ArrayList<String>();
			tdrasbo.setDailyPremium(rs.getString("C"));
			tdrasbo.setDatevalue(rs.getString("A"));
			tdrasbo.setMonthlyPremium(rs.getString("B"));
			tdrasbo.setUptoPremium(rs.getString("D"));
			
			tickerValuesList.add(rs.getString("C"));
			tickerValuesList.add(rs.getString("A"));
			tickerValuesList.add(rs.getString("B"));
			tickerValuesList.add(rs.getString("D"));
			
				// addding the closing connections code
				conn.close();
				rs.close();
				//adding the close connections code
			return tickerValuesList;
			
			
		}

}
