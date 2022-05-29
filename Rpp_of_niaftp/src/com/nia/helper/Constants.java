package com.nia.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Constants {
	public static String signature_constant="";
	public static final String salutation="Dear Sir / Madam," 
	+ System.getProperty("line.separator")+ System.getProperty("line.separator");
	public static final String signature="Thanks and Regards,"
			+ System.getProperty("line.separator");
	public static final SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
	
	
	
	
	public static class SOURCE
	{ 
		public static final String BSSBY = "BSSBY";	
	}
	public static class QUERY_FETCH_FILENAME_FROM_SERVER
	{ 
	
		public static final String COMMON_QUERY_TO_GET_FILE_PATH= "SELECT fcr.outfile_name FROM apps.fnd_concurrent_requests fcr "+
				" WHERE 1 = 1 AND fcr.status_code = 'C' "+
				" AND fcr.argument1 = ? AND fcr.argument2 = ? AND fcr.argument3 = ? "+
				" AND (fcr.actual_start_date, fcr.concurrent_program_id) = (SELECT MAX(fcr.actual_start_date) ,fcr.concurrent_program_id "+
				" FROM apps.fnd_concurrent_requests fcr ,apps.fnd_conc_req_summary_v  fcrsv WHERE fcr.request_id = fcrsv.request_id "+
				" AND fcr.status_code = 'C' AND fcrsv.status_code = 'C' AND fcrsv.program_short_name = 'NIANEFTPAYMENT' "+
				" AND fcr.argument1 = ? AND fcr.argument2 = ? AND fcr.argument3 = ? "+
				" AND trunc(fcr.actual_start_date) = trunc(SYSDATE) GROUP BY fcr.concurrent_program_id)"; // change in quesry done -1 against sysdate
				
		
		public static final String COMMON_QUERY_TO_GET_FILE_PREFIX = "select attribute5,attribute6,attribute8,attribute9,attribute10,attribute11 from FND_LOOKUP_VALUES_VL where 1=1 and lookup_type = 'NIA_SOURCE_TO_BANK_PAYMENT' and nvl(end_date_active, sysdate+1) > sysdate and enabled_flag = 'Y' and attribute1 = ? and attribute2 = ? and attribute3 = ?";
		
	}
	
	
	public static boolean isValidSource(String source) 
	{
		 ArrayList<String> list=new ArrayList<String>(); 
		  list.add("BSSBY");  
		  
	    for (String item : list) {
	        if (item.equals(source)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	
}
