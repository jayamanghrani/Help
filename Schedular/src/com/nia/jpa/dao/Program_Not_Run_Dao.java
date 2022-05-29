package com.nia.jpa.dao;

import java.sql.Connection;
import java.util.List;

import com.nia.jpa.dto.Program_Hold_DTO;
import com.nia.jpa.dto.Program_Not_Run_DTO;
import com.nia.jpa.exception.ProgramHoldDaoException;

public interface Program_Not_Run_Dao {

	
	
	public List<Program_Not_Run_DTO> findAll(Connection conn) throws ProgramHoldDaoException;
	
	
	
	
}
