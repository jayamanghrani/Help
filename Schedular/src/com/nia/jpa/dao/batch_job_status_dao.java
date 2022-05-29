package com.nia.jpa.dao;

import java.util.HashMap;
import java.util.List;

import com.nia.jpa.dto.Job_summary_dto;
import com.nia.jpa.exception.batch_job_status_daoImpl_Exception;

public interface batch_job_status_dao {
	
	public HashMap<Integer,List<Job_summary_dto>> add_job_statusMap(Integer programe_id, String programe_name, List<Job_summary_dto> programe_list) throws batch_job_status_daoImpl_Exception;

}
