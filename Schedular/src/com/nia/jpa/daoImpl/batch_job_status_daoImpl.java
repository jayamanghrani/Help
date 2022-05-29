package com.nia.jpa.daoImpl;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

//import com.nia.jpa.dao.Job_monitor_dao;
import com.nia.jpa.dao.batch_job_status_dao;
import com.nia.jpa.dto.Job_summary_dto;
import com.nia.jpa.exception.batch_job_status_daoImpl_Exception;

public class batch_job_status_daoImpl implements batch_job_status_dao {
	final static Logger logger = Logger.getLogger(batch_job_status_daoImpl.class);
	
	private HashMap <Integer, List<Job_summary_dto>> jobMap;

	public batch_job_status_daoImpl()
	{
		jobMap = new HashMap <Integer, List<Job_summary_dto>>();
	}
	@Override
	public HashMap<Integer, List<Job_summary_dto>> add_job_statusMap(
			Integer programe_id, String programe_name,List<Job_summary_dto> programe_list) throws batch_job_status_daoImpl_Exception {
		
		// Check if Programe ID already Present in the Map
		if(!jobMap.containsKey(programe_id))
		{
			jobMap.put(programe_id, programe_list);
		}
		else {
			String errorMsg = "Job Status already contains list for Programe ID: "+programe_id+" Programe Name: "+programe_name;
			logger.error(errorMsg);
			
			throw new batch_job_status_daoImpl_Exception(errorMsg);
		}
		
		// TODO Auto-generated method stub
		return jobMap;
	}

}
