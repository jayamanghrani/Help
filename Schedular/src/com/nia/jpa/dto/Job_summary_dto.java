package com.nia.jpa.dto;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Job_summary_dto implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String programe_name="None";
	protected int  exec_count=0;
	protected String last_status_code="None";
	protected Date first_act_start_time;
	protected Date last_act_start_time;
	protected long min_timeTaken=0;
	protected long max_timeTaken=0;
	protected long avg_timeTaken=0;
	protected long tot_timeTaken=0;
	
	
	public Job_summary_dto() throws ParseException{
		 Date dummydate = new Date();
		 String dummyDateStr = "01-Jan-2000 @ 01:01:01";
		  SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy @ HH:mm:ss");
	 first_act_start_time = formatter.parse(dummyDateStr);
		 last_act_start_time = formatter.parse(dummyDateStr);
	}
	
	public String getPrograme_name() {
		return programe_name;
	}
	public void setPrograme_name(String programe_name) {
		this.programe_name = programe_name;
	}
	public int getExec_count() {
		return exec_count;
	}
	public void setExec_count(int exec_count) {
		this.exec_count = exec_count;
	}
	
	public String getLast_status_code() {
		return last_status_code;
	}
	public void setLast_status_code(String last_status_code) {
		this.last_status_code = last_status_code;
	}
	public Date getFirst_act_start_time() {
		return first_act_start_time;
	}
	public void setFirst_act_start_time(Date first_act_start_time) {
		this.first_act_start_time = first_act_start_time;
	}
	public Date getLast_act_start_time() {
		return last_act_start_time;
	}
	public void setLast_act_start_time(Date last_act_start_time) {
		this.last_act_start_time = last_act_start_time;
	}
	public long getTot_timeTaken() {
		return tot_timeTaken;
	}
	public void setTot_timeTaken(long tot_timeTaken) {
		this.tot_timeTaken = tot_timeTaken;
	}
	public long getMin_timeTaken() {
		return min_timeTaken;
	}
	public void setMin_timeTaken(long min_timeTaken) {
		this.min_timeTaken = min_timeTaken;
	}
	public long getMax_timeTaken() {
		return max_timeTaken;
	}
	public void setMax_timeTaken(long max_timeTaken) {
		this.max_timeTaken = max_timeTaken;
	}
	public long getAvg_timeTaken() {
		return avg_timeTaken;
	}
	public void setAvg_timeTaken(long avg_timeTaken) {
		this.avg_timeTaken = avg_timeTaken;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
