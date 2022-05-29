package com.nia.jpa.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.nia.jpa.dao.MessageCountDao;
import com.nia.jpa.dto.Msg_Count_dto;
import com.nia.jpa.exception.MessageCountDaoException;
import com.nia.jpa.helper.ResourceManager;

public class MessageCount_daoImpl implements MessageCountDao {

	final static Logger logger = Logger.getLogger(MessageCount_daoImpl.class);
	
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
	protected final String SQL_Query = "SELECT *   FROM " +
			"(SELECT 'SMS', COUNT(*) COUNT, TRUNC(MSG_SENT_TIME) as SENT_DATE, STATUS  " +
			"         FROM NIA_SMS_DATA_DUMP          WHERE TRUNC(MSG_SENT_TIME) >= TRUNC(SYSDATE - 1) " +
			"         GROUP BY TRUNC(MSG_SENT_TIME), STATUS        " +
			" UNION       " +
			"  SELECT 'EMAIL',  COUNT(*) COUNT, TRUNC(EMAIL_SENT_TIME) as SENT_DATE, STATUS  " +
			"         FROM NIA_EMAIL_DATA_DUMP   WHERE TRUNC(EMAIL_SENT_TIME) >= TRUNC(SYSDATE - 1)    " +
			"      GROUP BY TRUNC(EMAIL_SENT_TIME), STATUS)  ORDER BY 1";
			
	
	
	protected static final int COLUMN_msgType=1;
	protected static final int COLUMN_count=2;
	protected static final int COLUMN_date=3;
	protected static final int COLUMN_status=4;
	
	
	
	@Override
	public  List<Msg_Count_dto> findAll(Connection conn) throws MessageCountDaoException {
		
		
		// declare variables
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		
		List<Msg_Count_dto> resultList = new ArrayList<Msg_Count_dto>();
		try {
			// construct the SQL statement
		
				
						
				if (logger.isTraceEnabled()) {
					logger.trace( "Executing : " + SQL_Query);
				}
				// prepare statement for query 1
				stmt = conn.prepareStatement( SQL_Query );
				rs = stmt.executeQuery();
				while (rs.next()){
					Msg_Count_dto dto=new Msg_Count_dto();
					dto.setMsgType(rs.getString(COLUMN_msgType));
					dto.setCount(rs.getString(COLUMN_count));
					//String date=rs.getString(COLUMN_date);

					
					dto.setDate(rs.getString(COLUMN_date));
					dto.setStatus(rs.getString(COLUMN_status));
					resultList.add(dto);
				}
				
				
				
				
				
			
			// fetch the results
			return resultList;
		}
		
		catch (Exception _e) {
			System.out.println("Exception: " + _e.getMessage());
			_e.printStackTrace();
			//logger.error( "Exception: " + _e.getMessage(), _e );
			throw new MessageCountDaoException(_e.getMessage(), _e.getLocalizedMessage(),"Exception in Gather Schema OfMonitoring_daoImpl",true, _e);
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
