package com.nia.jpa.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


//import com.nia.jpa.dao.OfMonitoringDao;
import com.nia.jpa.dao.ReverseFeedDao;
import com.nia.jpa.dao.TPA_Rejection_Dao;
import com.nia.jpa.dto.Of_monitor_dto;
import com.nia.jpa.dto.Reverse_Feed_dto;
import com.nia.jpa.dto.TPA_REJECTION_DTO;
import com.nia.jpa.exception.OfMonitoringDaoException;
import com.nia.jpa.exception.ReverseFeedDaoException;
import com.nia.jpa.exception.TPARejectionDaoException;
import com.nia.jpa.helper.ResourceManager;

public class TPA_Rejection_DaoImpl implements TPA_Rejection_Dao {

	final static Logger logger = Logger.getLogger(TPA_Rejection_DaoImpl.class);
	
	// Dev  Queries
	/* 	protected final String SQL_reverse_feed_1 = "select count(*) from apps.nia_iims_cancelled_invoices where trunc(inv_cancelled_date) =trunc(sysdate-14)";
	protected final String SQL_reverse_feed_2 = "select count(*) from CANCELLED_INVo_REVERSE_FEED where trunc(inv_cancelled_date)='14-MAR-12'";
	protected final String SQL_reverse_feed_3 = "select count(distinct check_id)  from nia_iims_void_payment_feed  where trunc(creation_date) =trunc(sysdate-14)";
	protected final String SQL_reverse_feed_4 = "select count(distinct check_id)  from VOID_REVERSE_FEED where trunc(last_update_date) ='13-MAR-15'";
	protected final String SQL_reverse_feed_5 = "select count(distinct check_id)  from nia_iims_payment_feed where trunc(creation_date) =trunc(sysdate-14)";
	protected final String SQL_reverse_feed_6 = "select count(distinct check_id)  from PAYMENT_REVERSE_FEED where trunc(last_update_date) ='17-MAR-15'";
	protected final String SQL_reverse_feed_7 = "select count(distinct check_id)  from nia_iims_payment_feed where trunc(cheque_date) =trunc(sysdate-14)";
	protected final String SQL_reverse_feed_8 = "select count(distinct check_id)  from PAYMENT_REVERSE_FEED  where trunc(prf_cheque_date) ='17-MAR-15'";
	
	
	SQL_reverse_feed_1  - this is for critical rejections
	
	
	
	
	
	*/
	
	
	
	
	 
	 // Production Queries	
	/*SOC - ADDED BY SARALA - QUERY CHNAGE FOR Single Window TPA Rejection*/
	/*protected final String SQL_reverse_feed_1 = " select DISTINCT(INVOICE_NUM) from  nia_iims_ap_stage NAS, FND_FLEX_VALUES_VL FF" +
	" where NAS.source = FF.FLEX_VALUE " +
	"       AND FF.FLEX_VALUE_SET_ID = 1015844" +
	"       AND NAS.INVOICE_NUM NOT IN (select INVOICE_NUM from apps.ap_invoices_all)" +
	"       AND TRUNC(NAS.OF_RECEIVED_TIME) = TRUNC(SYSDATE - 1)" ;*/
	/*protected final String SQL_rejection = 
			"select DISTINCT(INVOICE_NUM) from  nia_iims_ap_stage NAS, FND_FLEX_VALUES_VL FF "+
			"where NAS.source = FF.FLEX_VALUE "+
			"AND FF.FLEX_VALUE_SET_ID = 1015844 "+
			"AND NAS.INVOICE_NUM NOT IN (select INVOICE_NUM from apps.ap_invoices_all) "+
			"AND NAS.of_received_time between to_date(to_char(SYSDATE - 1) || ' 00:00:00','DD-MM-YY HH24:MI:SS') AND to_date(to_char(SYSDATE - 1) || ' 23:59:59','DD-MM-YY HH24:MI:SS')" ;*/
			
	protected final String SQL_rejection ="SELECT * FROM NIA_CRITICAL_REJ";	
	
	
			
	/*EOC - ADDED BY SARALA - QUERY CHNAGE FOR Single Window TPA Rejection*/
	
	protected static final int COLUMN_count=1;
	
	
	
	
	@Override
	public  List<TPA_REJECTION_DTO> findAll(Connection conn) throws TPARejectionDaoException {
		
		logger.info("this is for critical rejection");
		
		// declare variables
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		TPA_REJECTION_DTO dto;
		List<TPA_REJECTION_DTO> resultList = new ArrayList<TPA_REJECTION_DTO>();
		try {
			// construct the SQL statement
		
				
							
				if (logger.isTraceEnabled()) {
					logger.trace( "Executing : " + SQL_rejection);
				}
				// prepare statement for query 1
				stmt = conn.prepareStatement( SQL_rejection );
				rs = stmt.executeQuery();
				logger.info( "executing SQL_rejection query"+SQL_rejection);
				logger.info(rs);
				while (rs.next()){
					dto=new TPA_REJECTION_DTO();
					dto.setInvoiceNo(rs.getString(COLUMN_count));
					resultList.add(dto);
					logger.info( "resultList : " + resultList);
				}
				
							
			
			// fetch the results
			return resultList;
		}
		
		catch (Exception _e) {
			_e.printStackTrace();
			logger.error( "Exception: " + _e.getMessage(), _e );
			throw new TPARejectionDaoException(_e.getMessage(), _e.getLocalizedMessage(),"Exception in Gather Schema OfMonitoring_daoImpl",true, _e);
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
