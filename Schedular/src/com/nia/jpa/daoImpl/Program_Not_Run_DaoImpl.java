package com.nia.jpa.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.nia.jpa.dao.Program_Hold_Dao;
import com.nia.jpa.dao.Program_Not_Run_Dao;
import com.nia.jpa.dto.Program_Hold_DTO;
import com.nia.jpa.dto.Program_Not_Run_DTO;
import com.nia.jpa.exception.ProgramHoldDaoException;
import com.nia.jpa.helper.ResourceManager;

public class Program_Not_Run_DaoImpl implements Program_Not_Run_Dao 
{

	final static Logger logger = Logger.getLogger(Program_Not_Run_DaoImpl.class);
	 // Production Queries
	protected final String SQL_prog_not_run_query = "SELECT * FROM NIA_PROGRAM_NOT_EXEC" ;
	protected static final int COLUMN_1=1;
	
	public  List<Program_Not_Run_DTO> findAll(Connection conn) throws ProgramHoldDaoException 
	{
		// declare variables 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Program_Not_Run_DTO dto;
		List<Program_Not_Run_DTO> resultList = new ArrayList<Program_Not_Run_DTO>();
		try {
			// construct the SQL statement
					
				if (logger.isTraceEnabled()) {
					logger.trace( "Executing : " + SQL_prog_not_run_query);
				}
				// prepare statement for query 1
				logger.info( "Executing : sql query----" + SQL_prog_not_run_query);
				stmt = conn.prepareStatement( SQL_prog_not_run_query );
				rs = stmt.executeQuery();
				while (rs.next()){
					dto=new Program_Not_Run_DTO();
					// changes done by sarala;
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
