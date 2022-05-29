package com.nia.jpa.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


import com.nia.jpa.dao.Program_Hold_Dao;
//import com.nia.jpa.dao.OfMonitoringDao;
import com.nia.jpa.dao.ReverseFeedDao;
import com.nia.jpa.dao.TPA_Rejection_Dao;
import com.nia.jpa.dto.Of_monitor_dto;
import com.nia.jpa.dto.Program_Hold_DTO;
import com.nia.jpa.dto.Reverse_Feed_dto;
import com.nia.jpa.dto.TPA_REJECTION_DTO;
import com.nia.jpa.exception.OfMonitoringDaoException;
import com.nia.jpa.exception.ProgramHoldDaoException;
import com.nia.jpa.exception.ReverseFeedDaoException;
import com.nia.jpa.exception.TPARejectionDaoException;
import com.nia.jpa.helper.ResourceManager;

public class Program_Hold_DaoImpl implements Program_Hold_Dao {

	final static Logger logger = Logger.getLogger(Program_Hold_DaoImpl.class);
	
	// Dev  Queries
	/*	protected final String SQL_reverse_feed_1 = "select count(*) from apps.nia_iims_cancelled_invoices where trunc(inv_cancelled_date) =trunc(sysdate-14)";
	protected final String SQL_reverse_feed_2 = "select count(*) from CANCELLED_INVo_REVERSE_FEED where trunc(inv_cancelled_date)='14-MAR-12'";
	protected final String SQL_reverse_feed_3 = "select count(distinct check_id)  from nia_iims_void_payment_feed  where trunc(creation_date) =trunc(sysdate-14)";
	protected final String SQL_reverse_feed_4 = "select count(distinct check_id)  from VOID_REVERSE_FEED where trunc(last_update_date) ='13-MAR-15'";
	protected final String SQL_reverse_feed_5 = "select count(distinct check_id)  from nia_iims_payment_feed where trunc(creation_date) =trunc(sysdate-14)";
	protected final String SQL_reverse_feed_6 = "select count(distinct check_id)  from PAYMENT_REVERSE_FEED where trunc(last_update_date) ='17-MAR-15'";
	protected final String SQL_reverse_feed_7 = "select count(distinct check_id)  from nia_iims_payment_feed where trunc(cheque_date) =trunc(sysdate-14)";
	protected final String SQL_reverse_feed_8 = "select count(distinct check_id)  from PAYMENT_REVERSE_FEED  where trunc(prf_cheque_date) ='17-MAR-15'";
	
	*/
	
	 
	 // Production Queries
	protected final String SQL_prog_hold_query = "select a.program,b.request_id from fnd_conc_req_summary_v a," +
			"	fnd_concurrent_requests b" +
			"	where a.request_id=b.request_id" +
			"	and b.hold_flag ='Y'" +
			"	and b.phase_code not in('C')" +
			"	and a.PROGRAM in" +
			"       ('NIA IIMS AP Interface (Request Set NIA IIMS AP Interface)'," +
			"        'NIA Validate Interface'," +
			"        'NIA AP IIMS Interface Program'," +
			"        'NIA IIMS AP Payments Reverse Feed'," +
			"        'NIA IIMS AP Void Payments Reverse Feed'," +
			"        'NIA IIMS AP Cancelled Invoices Reverse Feed'," +
			"        'NIA GL Daily Feed Process (Report Set)'," +
			"        'NIA GL Interface Import'," +
			"        'NIA Custom Table Purging'," +
			"        'Program - Automatic Posting'," +
			"        'NIA ODS Daily Trial Balance Export'," +
			"        'NIA IIMS EMAIL ALERT PROGRAM'," +
			"        'NIA IIMS EMAIL HOST PROGRAM (NIA IIMS EMAIL HOST PROGRAM)'," +
			"        'NIA EMAIL ALERT PROGRAM'," +
			"        'NIA EMAIL HOST PROGRAM (NIA EMAIL HOST PROGRAM)'," +
			"        'NIA SMS Alerts Batch'," +
			"        'NIA EMPLOYEE DETAILS TO ODS',        'NIA AP HRMS Interface Program'," +
			"        'NIA Agent Bill Export to ODS',        'Purge Concurrent Request and/or Manager Data'," +
			"        'Transfer Journal Entries to GL-(222 )',        'Transfer Journal Entries to GL-(200 )'," +
			"        'Transfer Journal Entries to GL-(260 )',        'Invoice Validation'," +
			"        'NIA EMPLOYEE DETAILS TO ODS',        'NIA Cash Management Auto Clearing'," +
			"        'Accounting Program-(200 )',        'Create Accounting-(200 ), MANUAL'," +
			"        'Create Accounting-(200 ), INVOICES',        'Create Accounting-(200 ), PAYMENTS'," +
			"        'Create Accounting-(200 ), THIRD_PARTY_MERGE',        'Accounting Program-(222 )'," +
			"        'Create Accounting-(222 )'," +
			"        'Accounting Program-(260 )',        'Create Accounting-(260 )') " ;
			
	
	
	protected static final int COLUMN_1=1;
	protected static final int COLUMN_2=2;
	
	
	
	@Override
	public  List<Program_Hold_DTO> findAll(Connection conn) throws ProgramHoldDaoException {
		
		
		// declare variables
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		Program_Hold_DTO dto;
		List<Program_Hold_DTO> resultList = new ArrayList<Program_Hold_DTO>();
		try {
			// construct the SQL statement
		
				
							
				if (logger.isTraceEnabled()) {
					logger.trace( "Executing : " + SQL_prog_hold_query);
				}
				// prepare statement for query 1
				stmt = conn.prepareStatement( SQL_prog_hold_query );
				rs = stmt.executeQuery();
				while (rs.next()){
					dto=new Program_Hold_DTO();
					// changes done by sarala
					dto.setRequestId(rs.getString(COLUMN_2));
					dto.setProgramName(rs.getString(COLUMN_1));
					resultList.add(dto);	
				}
				
							
			
			// fetch the results
			return resultList;
		}
		
		catch (Exception _e) {
			logger.error( "Exception: " + _e.getMessage(), _e );
			throw new ProgramHoldDaoException(_e.getMessage(), _e.getLocalizedMessage(),"Exception in ProgramHoldDaoException",true, _e);
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(conn);
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//ResourceManager.close(stmt);
		
		}
		
		
	}
	
	
	

	
	

}
