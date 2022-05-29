package com.nia.jpa.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


import com.nia.jpa.dao.OfMonitoringDao;
import com.nia.jpa.dto.Of_monitor_dto;
import com.nia.jpa.exception.OfMonitoringDaoException;
import com.nia.jpa.helper.ResourceManager;

public class Gatherstat_OfMonitoring_daoImpl implements OfMonitoringDao  {

	final static Logger logger = Logger.getLogger(Gatherstat_OfMonitoring_daoImpl.class);
	
	// Dev MySQL Query
//	protected final String SQL_gather_stat = "SELECT request_id, programe,phase,status,argument,req_date,act_date,finish_date,time_taken FROM of_gatherstat ";
	
	
	protected final String SQL_gather_stat = 
	 
			"SELECT fcr.request_id," +
			"v.PROGRAM," +
			"decode(fcr.phase_code," +
			"'C'," +
			"'Completed'," +
			" 'I'," +
			" 'Inactive'," +
			" 'P'," +
			" 'Pending'," +
			" 'R'," +
			" 'Running'," +
			" fcr.phase_code)," +
			" decode(fcr.status_code," +
			" 'A'," +
			" 'Waiting'," +
			" 'B'," +
			" 'Resuming'," +
			" 'C'," +
			" 'Normal'," +
			" 'D'," +
			" 'Cancelled'," +
			" 'E'," +
			" 'Error'," +
			" 'G'," +
			" 'Warning'," +
			" 'H'," +
			" 'On Hold'," +
			" 'I'," +
			" 'Normal'," +
			" 'M'," +
			" 'No Manager'," +
			" 'P'," +
			" 'Scheduled'," +
			" 'Q'," +
			" 'Standby'," +
			" 'R'," +
			" 'Normal'," +
			" 'S'," +
			" 'Suspended'," +
			" 'T'," +
			" 'Terminating'," +
			" 'U'," +
			" 'Disabled'," +
			" 'W'," +
			" 'Paused'," +
			" 'X'," +
			" 'Terminated'," +
			" 'Z'," +
			" ' Waiting'," +
			" fcr.status_code)," +
			" fcr.argument_text," +
			" TO_CHAR(v.requested_start_date, 'DD-Mon-YYYY @ HH24:MI:SS')," +
			" TO_CHAR(v.actual_start_date, 'DD-Mon-YYYY @ HH24:MI:SS')," +
			" TO_CHAR(v.actual_completion_date, 'DD-Mon-YYYY @ HH24:MI:SS')," +
			" TO_CHAR((TO_NUMBER(v.actual_completion_date - v.actual_start_date) * 1440 -" +
			" MOD(TO_NUMBER(v.actual_completion_date - v.actual_start_date) * 1440," +
			" 60)) / 60) || ' hr ' ||" +
			" ROUND(MOD(TO_NUMBER(v.actual_completion_date - v.actual_start_date) * 1440," +
			" 60)) || ' min ' || 'For ' || trunc(v.ACTUAL_COMPLETION_DATE)" +
			" FROM apps.fnd_concurrent_requests    fcr," +
			" apps.fnd_conc_req_summary_v     v," +
			" apps.fnd_concurrent_programs_vl fcp" +
			" WHERE fcr.request_id = v.request_id" +
			" AND fcp.concurrent_program_id = fcr.concurrent_program_id" +
			" AND fcr.program_application_id = fcp.application_id" +
			" AND v.PROGRAM in ('Gather Table Statistics', 'Gather Schema Statistics')" +
			" ORDER BY program," +
			" argument_text," +
			" TO_CHAR(v.actual_start_date, 'DD-Mon-YYYY @ HH24:MI:SS') DESC";
	
	
	
	protected static final int COLUMN_request_id=1;
	protected static final int COLUMN_programe = 2;
	protected static final int COLUMN_phase_code = 3;
	protected static final int COLUMN_status_code = 4;
	protected static final int COLUMN_arg_txt = 5;
	protected static final int COLUMN_req_start_time = 6;
	protected static final int COLUMN_act_start_time = 7;
	protected static final int COLUMN_finish_time = 8;
	protected static final int COLUMN_run_time = 9;
	protected static final int NUMBER_OF_COLUMNS = 9;
	
	
	
	
	@Override
	public List<Of_monitor_dto> findAll(Connection conn) throws OfMonitoringDaoException {
		
		
		// declare variables
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// construct the SQL statement
			
			if (logger.isTraceEnabled()) {
				logger.trace( "Executing : " + SQL_gather_stat);
			}
			// prepare statement
			stmt = conn.prepareStatement( SQL_gather_stat );
		
			rs = stmt.executeQuery();
			
			// fetch the results
			return fetchMultiResults(rs);
		}
		
		catch (Exception _e) {
			logger.error( "Exception: " + _e.getMessage(), _e );
			throw new OfMonitoringDaoException(_e.getMessage(), _e.getLocalizedMessage(),"Exception in Gather Schema OfMonitoring_daoImpl",true, _e);
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
	
	
	protected List<Of_monitor_dto> fetchMultiResults(ResultSet rs) throws SQLException
	{
		List<Of_monitor_dto> resultList = new ArrayList<Of_monitor_dto>();
		while (rs.next()) {
			Of_monitor_dto dto = new Of_monitor_dto();
			populateDto( dto, rs);
			resultList.add( dto );
		}
		
		return resultList;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(Of_monitor_dto dto, ResultSet rs) throws SQLException
	{
		
		dto.setRequest_id( rs.getString( COLUMN_request_id ) );
		String programeStr =  rs.getString( COLUMN_programe );
		//dto.setPrograme( rs.getString( COLUMN_programe )+" (" );
		dto.setPhaseCode( rs.getString( COLUMN_phase_code ) );
		/* Modified by pawan for adding status code in programe name itself */
		
		dto.setStatusCode( rs.getString(COLUMN_status_code) );
		dto.setArg_txt( rs.getString( COLUMN_arg_txt ));
		String arg_txt = rs.getString( COLUMN_arg_txt );
		dto.setArg_txt(arg_txt);
		dto.setPrograme(programeStr+"-("+arg_txt.substring(0, 3)+" )");
		dto.setReq_startTime( rs.getString( COLUMN_req_start_time ) );
		dto.setAct_startTime( rs.getString( COLUMN_act_start_time ) );
		dto.setFinishTime( rs.getString( COLUMN_finish_time ) );
		dto.setRunTime( rs.getString( COLUMN_run_time ) );
		
	}

	
	

}
