package com.nia.jpa.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;



import com.nia.jpa.dao.OfMonitoringDao;
import com.nia.jpa.dao.ReverseFeedDao;
import com.nia.jpa.dto.Of_monitor_dto;
import com.nia.jpa.dto.Reverse_Feed_dto;
import com.nia.jpa.exception.OfMonitoringDaoException;
import com.nia.jpa.exception.ReverseFeedDaoException;
import com.nia.jpa.helper.ResourceManager;

public class ReverseFeed_daoImpl implements ReverseFeedDao {

	final static Logger logger = Logger.getLogger(ReverseFeed_daoImpl.class);
	
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
	protected final String SQL_reverse_feed_1 = "select count(*) from apps.nia_iims_cancelled_invoices where trunc(inv_cancelled_date) =trunc(sysdate-1)";
	protected final String SQL_reverse_feed_2 = "select count(*) from CANCELLED_INVo_REVERSE_FEED where trunc(inv_cancelled_date)=trunc(sysdate-1)";
	protected final String SQL_reverse_feed_3 = "select count(distinct check_id)  from nia_iims_void_payment_feed  where trunc(creation_date) =trunc(sysdate-1)";
	protected final String SQL_reverse_feed_4 = "select count(distinct check_id)  from VOID_REVERSE_FEED where trunc(last_update_date) =trunc(sysdate-1)";
	protected final String SQL_reverse_feed_5 = "select count(distinct check_id)  from nia_iims_payment_feed where trunc(creation_date) =trunc(sysdate-1)";
	protected final String SQL_reverse_feed_6 = "select count(distinct check_id)  from PAYMENT_REVERSE_FEED where trunc(last_update_date) =trunc(sysdate-1)";
	protected final String SQL_reverse_feed_7 = "select count(distinct check_id)  from nia_iims_payment_feed where trunc(cheque_date) =trunc(sysdate-1)";
	protected final String SQL_reverse_feed_8 = "select count(distinct check_id)  from PAYMENT_REVERSE_FEED  where trunc(prf_cheque_date) =trunc(sysdate-1)";
		
	
	
	protected static final int COLUMN_count=1;
	
	
	
	
	@Override
	public  List<Reverse_Feed_dto> findAll(Connection conn) throws ReverseFeedDaoException {
		
		logger.info("inside findAll");
		
		// declare variables
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		logger.info("creatinglistof  Reverse_Feed_dto---variables-----String reverse_feed ,iims_count; of_count;");
		Reverse_Feed_dto dto;
		List<Reverse_Feed_dto> Reverse_Feed_dtoList = new ArrayList<Reverse_Feed_dto>();
		try {
			// construct the SQL statement
		
				dto=new Reverse_Feed_dto();
				logger.info("setting reverse feed --1 Cancelled invoices");
				dto.setReverse_feed("Cancelled invoices");				
				if (logger.isTraceEnabled()) {
					logger.trace( "Executing : " + SQL_reverse_feed_1);
				}
				// prepare statement for query 1
				logger.info("executing SQL_reverse_feed_1 query for iims_count");
				stmt = conn.prepareStatement( SQL_reverse_feed_1 );
				rs = stmt.executeQuery();
				while (rs.next()){
					dto.setIims_count(rs.getString(COLUMN_count));
				}
				
				// prepare statement for query 2
				logger.info("executing SQL_reverse_feed_2 query for of_count");
				stmt = conn.prepareStatement( SQL_reverse_feed_2 );
				rs = stmt.executeQuery();
				while (rs.next()){
					dto.setOf_count(rs.getString(COLUMN_count));
				}				
				Reverse_Feed_dtoList.add(dto);
				
				
				//--------------------------------------------------------------
				
				
				logger.info("setting reverse feed --2 Void payment");
				dto=new Reverse_Feed_dto();
				dto.setReverse_feed("Void payment");				
				if (logger.isTraceEnabled()) {
					logger.trace( "Executing : " + SQL_reverse_feed_3);
				}
				// prepare statement for query 3
				logger.info("executing SQL_reverse_feed_3 query for iims_count");
				stmt = conn.prepareStatement( SQL_reverse_feed_3 );
				rs = stmt.executeQuery();
				while (rs.next()){
					dto.setIims_count(rs.getString(COLUMN_count));
				}
				
				// prepare statement for query 4
				logger.info("executing SQL_reverse_feed_4 query for of_count");
				stmt = conn.prepareStatement( SQL_reverse_feed_4 );
				rs = stmt.executeQuery();
				while (rs.next()){
					dto.setOf_count(rs.getString(COLUMN_count));
				}				
				Reverse_Feed_dtoList.add(dto);
				
				
				//--------------------------------------------------------------------
				
				logger.info("setting reverse feed  3Payment feed by Creation Date");
				dto=new Reverse_Feed_dto();
				dto.setReverse_feed("Payment feed by Creation Date");				
				if (logger.isTraceEnabled()) {
					logger.trace( "Executing : " + SQL_reverse_feed_5);
				}
				// prepare statement for query 5
				logger.info("executing SQL_reverse_feed_5 query for iims_count");
				stmt = conn.prepareStatement( SQL_reverse_feed_5 );
				rs = stmt.executeQuery();
				while (rs.next()){
					dto.setIims_count(rs.getString(COLUMN_count));
				}
				
				// prepare statement for query 6
				logger.info("executing SQL_reverse_feed_6 query for of_count");
				stmt = conn.prepareStatement( SQL_reverse_feed_6 );
				rs = stmt.executeQuery();
				while (rs.next()){
					dto.setOf_count(rs.getString(COLUMN_count));
				}				
				Reverse_Feed_dtoList.add(dto);

				
				//----------------------------------------------------------------------
				
				
				
				
				logger.info("setting reverse feed  4Payment feed by Cheque Date");
				dto=new Reverse_Feed_dto();
				dto.setReverse_feed("Payment feed by Cheque Date");				
				if (logger.isTraceEnabled()) {
					logger.trace( "Executing : " + SQL_reverse_feed_7);
				}
				// prepare statement for query 7
				logger.info("executing SQL_reverse_feed_7 query for iims_count");
				stmt = conn.prepareStatement( SQL_reverse_feed_7 );
				rs = stmt.executeQuery();
				while (rs.next()){
					dto.setIims_count(rs.getString(COLUMN_count));
				}
				
				// prepare statement for query 8
				logger.info("executing SQL_reverse_feed_8 query for of_count");
				stmt = conn.prepareStatement( SQL_reverse_feed_8 );
				rs = stmt.executeQuery();
				while (rs.next()){
					dto.setOf_count(rs.getString(COLUMN_count));
				}				
				Reverse_Feed_dtoList.add(dto);
				
			
			// fetch the results
			return Reverse_Feed_dtoList;
		}
		
		catch (Exception _e) {
			logger.error( "Exception: " + _e.getMessage(), _e );
			throw new ReverseFeedDaoException(_e.getMessage(), _e.getLocalizedMessage(),"Exception in Gather Schema OfMonitoring_daoImpl",true, _e);
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
