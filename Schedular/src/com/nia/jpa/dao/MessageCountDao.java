/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package com.nia.jpa.dao;

import java.sql.Connection;
import java.util.List;

import com.nia.jpa.dto.Msg_Count_dto;
import com.nia.jpa.exception.MessageCountDaoException;


public interface MessageCountDao
{
	
	/** 
	 * Returns all rows from the of_monitoring table that match the criteria ''.
	 */
	public List<Msg_Count_dto> findAll(Connection conn) throws MessageCountDaoException;

	
	/** 
	 * Returns all rows from the of_monitoring table that match the criteria 'programe = :programe'.
	 */
	
	
	
	//public List<Of_monitor_dto> findWhereProgrameEquals(Connection conn, String programe) throws OfMonitoringDaoException;

	
	
}