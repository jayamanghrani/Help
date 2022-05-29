package com.nia.jpa.helper;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.hp.gagawa.java.Document;
import com.hp.gagawa.java.elements.Br;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.H2;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Tr;
import com.nia.jpa.dto.Job_summary_dto;
import com.nia.jpa.dto.Msg_Count_dto;
import com.nia.jpa.dto.Of_monitor_dto;
import com.nia.jpa.dto.Program_Hold_DTO;
import com.nia.jpa.dto.Program_Not_Run_DTO;
import com.nia.jpa.dto.Reverse_Feed_dto;
import com.nia.jpa.dto.TPA_REJECTION_DTO;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class Converter {

	private static Converter instance;
	Date dummydate = new Date();
	String dummyDateStr = "01-Jan-2000 @ 01:01:01";

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy @ HH:mm:ss");

	protected static final Logger logger = Logger.getLogger( Converter.class );

	
	public static synchronized Converter getInstance()
	{
		if (instance == null)
		{
			instance = new Converter();
		}
		return instance;
	}


	public Converter()
	{
		try {
			dummydate = formatter.parse(dummyDateStr);
		}
		catch(ParseException e){
			//	System.out.println("Exception in formatting date:"+e.getMessage());
			/* 	Not handling date parse exception as date can be blank  i.e. Fnish date will be blank if Job is Running */
		}

	}

	public Date Dateconverter(String dateInString) 
	{
		Date date = new Date();

		try {
			if (null != dateInString)
			{
				date = formatter.parse(dateInString);
			}
			else {
				date = dummydate;
			}
		}
		catch(ParseException e){
			/* 	Not handling date parse exception as date can be blank  i.e. Fnish date will be blank if Job is Running */
			date = dummydate;
		}


		return date;
	}


	public Job_summary_dto convertsummaryRecord(Job_summary_dto summarydto, Of_monitor_dto dto) throws ParseException
	{
		//	System.out.println("========================================================");
		//	System.out.println("Converting:"+dto.toString());
		summarydto.setPrograme_name(dto.getPrograme());

		// Convert text into date & check actual start time is first in given record 
		Date currActStartDate = Dateconverter(dto.getAct_startTime());
		Date currActFinishDate = Dateconverter(dto.getFinishTime());
	
		if(dummydate.equals(currActStartDate) || dummydate.equals(currActFinishDate))
		{


		}
		else {

			summarydto.setExec_count(summarydto.getExec_count()+1);

			Date storedActStartDate = summarydto.getFirst_act_start_time();
			if(storedActStartDate.equals(dummydate))
			{
				summarydto.setFirst_act_start_time(currActStartDate);	
			}
			else {

				if(currActStartDate.before(storedActStartDate))
				{
					summarydto.setFirst_act_start_time(currActStartDate);	
				}

			}



			if(summarydto.getLast_act_start_time().equals(dummydate))
			{
				summarydto.setLast_act_start_time(currActStartDate);	
			}
			else {

				if(currActStartDate.after(summarydto.getLast_act_start_time()))
				{
					summarydto.setLast_act_start_time(currActStartDate);	
				}


			}



			long currtimeTaken = currActFinishDate.getTime() - currActStartDate.getTime();

			// Check if this time taken in Minimum than previous one

			if(currtimeTaken < summarydto.getMin_timeTaken() || summarydto.getMin_timeTaken()==0 )
			{
				summarydto.setMin_timeTaken(currtimeTaken);	
			}

			// Check if this time taken is maximum than previous one 

			if(currtimeTaken > summarydto.getMax_timeTaken()  )
			{
				summarydto.setMax_timeTaken(currtimeTaken);	
			}

			summarydto.setTot_timeTaken(summarydto.getTot_timeTaken()+currtimeTaken);

			// Set Avergate time taken by particular job

			summarydto.setAvg_timeTaken(summarydto.getTot_timeTaken()/summarydto.getExec_count());

			summarydto.setLast_status_code(dto.getStatusCode());


		}
		return summarydto;


	}


	public Table PrepareHeader(Table table)
	{
		Tr tr_row = new Tr();
		tr_row.setBgcolor("#2AA7FA");

		Td td_sr_no = new Td();
		// td_sr_no.setAttribute("color", "#FFFFFF");
		tr_row.appendChild(td_sr_no);
		td_sr_no.appendChild(new Text("Sr.No"));


		Td td_request_id = new Td();
		tr_row.appendChild(td_request_id);
		td_request_id.appendChild(new Text("Request ID"));

		Td td_programe = new Td();
		tr_row.appendChild(td_programe);
		td_programe.appendChild(new Text("Programe Name"));

		Td td_phase = new Td();
		tr_row.appendChild(td_phase);
		td_phase.appendChild(new Text("Phase Code"));

		Td td_status = new Td();
		tr_row.appendChild(td_status);
		td_status.appendChild(new Text("Status Code"));

		/* Commented by Pawan on 31-03-2015 as First 3 character of argument we added in programe name
		    Td td_argument = new Td();
		    tr_row.appendChild(td_argument);
		    td_argument.appendChild(new Text("Argument")); */


		Td td_req_dt = new Td();
		tr_row.appendChild(td_req_dt);
		td_req_dt.appendChild(new Text("Request start time"));


		Td td_act_dt = new Td();
		tr_row.appendChild(td_act_dt);
		td_act_dt.appendChild(new Text("Actual start time"));

		Td td_fin_dt = new Td();
		tr_row.appendChild(td_fin_dt);
		td_fin_dt.appendChild(new Text("Finish Time"));

		Td td_fin_time = new Td();
		tr_row.appendChild(td_fin_time);
		td_fin_time.appendChild(new Text("Time Taken"));

		table.appendChild(tr_row);



		return table;	
	}

	

	public Table PrepareSummary_Header(Table table)
	{
		Tr tr_row = new Tr();
		tr_row.setBgcolor("#2AA7FA");

		Td td_sr_no = new Td();
		// td_sr_no.setAttribute("color", "#FFFFFF");
		tr_row.appendChild(td_sr_no);
		td_sr_no.appendChild(new Text("Sr.No"));

		Td td_programe = new Td();
		tr_row.appendChild(td_programe);
		td_programe.appendChild(new Text("Programe Name"));

		Td td_execCount = new Td();
		tr_row.appendChild(td_execCount);
		td_execCount.appendChild(new Text("Execution Count"));


		Td td_firstActual = new Td();
		tr_row.appendChild(td_firstActual);
		td_firstActual.appendChild(new Text("First Actual Start Date"));

		Td td_lastActual = new Td();
		tr_row.appendChild(td_lastActual);
		td_lastActual.appendChild(new Text("Last Actual Start Date"));

		Td td_minTime = new Td();
		tr_row.appendChild(td_minTime);
		td_minTime.appendChild(new Text("Min.Time"));


		Td td_maxTime = new Td();
		tr_row.appendChild(td_maxTime);
		td_maxTime.appendChild(new Text("Max. Time"));

		Td td_avgTime = new Td();
		tr_row.appendChild(td_avgTime);
		td_avgTime.appendChild(new Text("Avg. Time"));

		Td td_totTime = new Td();
		tr_row.appendChild(td_totTime);
		td_totTime.appendChild(new Text("Total Time"));

		table.appendChild(tr_row);

		return table;	
	}



	public Document generateHtml(String programeName, List<Of_monitor_dto> programe_list,Document document)
	{
		logger.info("generating Html");
		
		//String htmlMsg="";

		Table table = new Table();
		Tr tr_hdr = new Tr();
		tr_hdr.setBgcolor("#D8DBDD");
		Td td_Hdr1 = new Td();
		td_Hdr1.setColspan("2");
		tr_hdr.appendChild(td_Hdr1);
		td_Hdr1.appendChild(new Text("Programe Name:"));
		Td td_Hdr2 = new Td();
		td_Hdr2.setColspan("7");
		tr_hdr.appendChild(td_Hdr2);
		
		if(programeName.equalsIgnoreCase("Accounting Program"))
			td_Hdr2.appendChild(new Text(programeName+", Transfer Journal Entries to GL"));
		else
			td_Hdr2.appendChild(new Text(programeName));
		table.appendChild(tr_hdr);
		
		logger.info("preparing header");

		table = Converter.getInstance().PrepareHeader(table);

		int row = 0;
		logger.info(" checkinfg programe_list.size()");

		if(programe_list.size()==0)
		{
			//System.out.println("Programe list Size is NUll==================================");
			Tr tr_row = new Tr();
			Td td_noRow = new Td();
			td_noRow.setColspan("9");
			tr_row.appendChild(td_noRow);
			td_noRow.appendChild(new Text("No Data Found"));
			table.appendChild(tr_row);

		}
		for ( Of_monitor_dto dto: programe_list)
		{

			Tr tr_row = new Tr();
			if (row % 2 == 0)
			{

				tr_row.setBgcolor("#DFECF5");
			}
			else {
				tr_row.setBgcolor("#ADCDE3");
			}


			Td td_sr_no = new Td();
			tr_row.appendChild(td_sr_no);
			td_sr_no.appendChild(new Text(row+1));


			Td td_request_id = new Td();
			tr_row.appendChild(td_request_id);
			td_request_id.appendChild(new Text(dto.getRequest_id()));

			Td td_programe = new Td();
			tr_row.appendChild(td_programe);
			td_programe.appendChild(new Text(dto.getPrograme()));

			Td td_phase = new Td();
			tr_row.appendChild(td_phase);
			td_phase.appendChild(new Text(dto.getPhaseCode()));


			Td td_status = new Td();
			tr_row.appendChild(td_status);
			td_status.appendChild(new Text(dto.getStatusCode()));
			/*  
			 * Commented by Pawan on 31-03-2015 as First 3 character of argument we added in programe name 
	    Td td_argument = new Td();
	    tr_row.appendChild(td_argument);
	    td_argument.appendChild(new Text(dto.getArg_txt()));

			 */ 
			Td td_req_dt = new Td();
			tr_row.appendChild(td_req_dt);
			td_req_dt.appendChild(new Text(dto.getReq_startTime()));


			Td td_act_dt = new Td();
			tr_row.appendChild(td_act_dt);
			td_act_dt.appendChild(new Text(dto.getAct_startTime()));

			Td td_fin_dt = new Td();
			tr_row.appendChild(td_fin_dt);
			td_fin_dt.appendChild(new Text(dto.getFinishTime()));

			Td td_fin_time = new Td();
			tr_row.appendChild(td_fin_time);
			td_fin_time.appendChild(new Text(dto.getRunTime()));

			table.appendChild(tr_row);
			row = row+1;

		} // End of Programe List For Loop 

		document.body.appendChild(table);
		document.body.appendChild(new Br());

		return document;

	} // End of Function


	/* Prepare All batch job Summary Report */
	public Document PrepareJobSummaryReport(Document document, Map<String,List> output) throws ParseException
	{
		
		logger.info(" ------------------------inside PrepareJobSummaryReport");
		logger.info(" creating set of dto");
		Set<Of_monitor_dto> Alljobset = new LinkedHashSet<Of_monitor_dto>();
		
		//Job_summary_dto summarydto = new Job_summary_dto();
		/*String [] dailyprogrammeList = {"NIA IIMS AP Interface (Request Set NIA IIMS AP Interface)",
				"NIA Validate Interface",
				"NIA AP IIMS Interface Program",
				"NIA IIMS AP Payments Reverse Feed",
				"NIA IIMS AP Void Payments Reverse Feed",
				"NIA IIMS AP Cancelled Invoices Reverse Feed",
				"NIA GL Daily Feed Process (Report Set)",
				"NIA GL Interface Import",
				"NIA Custom Table Purging",
				"Program - Automatic Posting",
				"NIA ODS Daily Trial Balance Export",
				"NIA IIMS EMAIL ALERT PROGRAM",
				"NIA IIMS EMAIL HOST PROGRAM (NIA IIMS EMAIL HOST PROGRAM)",
				"NIA EMAIL ALERT PROGRAM",
				"NIA EMAIL HOST PROGRAM (NIA EMAIL HOST PROGRAM)",
				"NIA SMS Alerts Batch",
				"NIA EMPLOYEE DETAILS TO ODS",
				"NIA AP HRMS Interface Program",
				"NIA Agent Bill Export to ODS",
				"Purge Concurrent Request and/or Manager Data",
				"Transfer Journal Entries to GL-(222 )",
				"Transfer Journal Entries to GL-(200 )",
				"Transfer Journal Entries to GL-(260 )",
				"Invoice Validation",
				"NIA EMPLOYEE DETAILS TO ODS",
				"NIA Cash Management Auto Clearing",
				"Accounting Program-(200 )",
				"Create Accounting-(200 ), MANUAL", // changes done by sarala
				"Create Accounting-(200 ), INVOICES",
				"Create Accounting-(200 ), PAYMENTS",
				"Create Accounting-(200 ), THIRD_PARTY_MERGE", // changes done by sarala
				"Accounting Program-(222 )",
				"Create Accounting-(222 )",
				"Accounting Program-(260 )",
				"Create Accounting-(260 )",
				"NIA AP IIMS Commission Invoices Batch"
				};
		*/
		
		
	logger.info("output.keySet()"+output.keySet());
		
		for (String key : output.keySet())
		{
			logger.info("key"+key);
			if(!(key.equalsIgnoreCase("Reverse Feed") || key.equalsIgnoreCase("Email and SMS Counts") 
					|| key.equalsIgnoreCase("Single Window TPA Rejection") ||
					key.equalsIgnoreCase("Programs On Hold")||key.equalsIgnoreCase("list of Programs not run")))
			{
				logger.info("output.get(key)"+output.get(key));
				
				List<Of_monitor_dto> programe_list = output.get(key);
				logger.info("457");
			Alljobset.addAll(programe_list);
			
			
			}
		}

		Map<String,List> outputMap = (java.util.HashMap<java.lang.String, java.util.List>) new LinkedHashMap<String,List>();

		logger.info("creating tree map <String, Job_summary_dto> ");
		TreeMap<String, Job_summary_dto> AlljobsummaryMap = new TreeMap<String, Job_summary_dto>();
		
		logger.info("Alljobset"+Alljobset);
		
		for (Of_monitor_dto dto : Alljobset)
		{
			//	System.out.println("Monitor Object"+dto.toString());
			String programe_name = dto.getPrograme();
			
			logger.info("programe_name"+programe_name);
			
			logger.info("if "+!AlljobsummaryMap.containsKey(programe_name));
			
			if (!AlljobsummaryMap.containsKey(programe_name))
			{
				logger.info("true");
				Job_summary_dto summarydto = new Job_summary_dto();
				logger.info("setting all the data in summary dto");
				summarydto = Converter.getInstance().convertsummaryRecord(summarydto, dto) ;

				if(!summarydto.getFirst_act_start_time().equals(dummydate))
				{
					AlljobsummaryMap.put(programe_name, summarydto);	
				}
				

			}
			else {

				Job_summary_dto summarydto = AlljobsummaryMap.get(programe_name);
				
				summarydto = Converter.getInstance().convertsummaryRecord(summarydto, dto);
				
				if(!summarydto.getFirst_act_start_time().equals(dummydate))
				{
					AlljobsummaryMap.put(programe_name, summarydto);	
				}


			}
			logger.info("AlljobsummaryMap.put(programe_name, summarydto);");
		}
		
		/*logger.info("get program names that have not run");
		*//*******Added to get program names that have not run[start]********//*
		ArrayList<String> progNotRun=new ArrayList<String>();
		
		logger.info("arraylist of progNotRun"); 
		for(int i=0;i<dailyprogrammeList.length;i++){
			String program=dailyprogrammeList[i];
			boolean progFound=false;
			for (String key : AlljobsummaryMap.keySet())
			{
				if(program.equalsIgnoreCase(key)){
					progFound=true;// that prog is running 
					break;
				}
			}
			
			if(!progFound){
				progNotRun.add(program);
				logger.info("arraylist of progNotRun"+progNotRun);
			}
			
		}
		logger.info("generating list  for  program not run"); 
		
		document=genrateProgramNotrunHTML(progNotRun,document);
		
		logger.info("after getting  list  for  program not run");*/ 
		
		logger.info("generating summary html ");
				
		
		document = generateSummaryHtml(AlljobsummaryMap, document);
		logger.info("generated summary html ");


		return document;	
	}

	public Document genrateProgramNotrunHTML(ArrayList<String> progNotRun,Document document)
	{
		logger.info(" progNotRun HTML headings"); 
		//String htmlMsg="";

		Table table = new Table();
		table.setBorder("true");
		Tr tr_hdr = new Tr();
		tr_hdr.setBgcolor("#D8DBDD");
		Td td_Hdr1 = new Td();
		
		tr_hdr.appendChild(td_Hdr1);
		td_Hdr1.appendChild(new Text("Report Name:"));
		Td td_Hdr2 = new Td();
		
		tr_hdr.appendChild(td_Hdr2);
		td_Hdr2.appendChild(new Text("List of Programmes that did not run"));

		table.appendChild(tr_hdr);

		Tr tr_title = new Tr();
		tr_title.setBgcolor("#2AA7FA");
		Td td_Hdr1_srno = new Td();
		
		tr_title.appendChild(td_Hdr1_srno);
		td_Hdr1_srno.appendChild(new Text("Sr. No"));
		Td td_Hdr2_progName = new Td();
		tr_title.appendChild(td_Hdr2_progName);
		td_Hdr2_progName.appendChild(new Text("Program Name"));
		table.appendChild(tr_title);
		int row = 1;
		
		if(progNotRun.size()==0){
			Tr tr_row = new Tr();
			Td td = new Td();
			td.setColspan("2");
			tr_row.appendChild(td);
			td.appendChild(new Text("All the program has run"));
			table.appendChild(tr_row);
		}
		else{
		
		for (String progName : progNotRun)
		{
			

			Tr tr_row = new Tr();
			if (row % 2 == 0)
			{

				tr_row.setBgcolor("#DFECF5");
			}
			else {
				tr_row.setBgcolor("#ADCDE3");
			}


			Td td_sr_no = new Td();
			// td_sr_no.setAttribute("color", "#FFFFFF");
			tr_row.appendChild(td_sr_no);
			td_sr_no.appendChild(new Text(row));

			Td td_programe = new Td();
			tr_row.appendChild(td_programe);
			td_programe.appendChild(new Text(progName));

			

			row = row+1;
			table.appendChild(tr_row);


			//			System.out.println("Programe_count"+prog_count);
		}
		}


		document.body.appendChild(table);
		document.body.appendChild(new Br());

		return document;

	} // End of Function


	public Document generateSummaryHtml(TreeMap<String, Job_summary_dto> AlljobsummaryMap,Document document)
	{
		//String htmlMsg="";

		Table table = new Table();
		table.setBorder("true");
		Tr tr_hdr = new Tr();
		tr_hdr.setBgcolor("#D8DBDD");
		Td td_Hdr1 = new Td();
		td_Hdr1.setColspan("2");
		tr_hdr.appendChild(td_Hdr1);
		td_Hdr1.appendChild(new Text("Report Name:"));
		Td td_Hdr2 = new Td();
		td_Hdr2.setColspan("7");
		tr_hdr.appendChild(td_Hdr2);
		td_Hdr2.appendChild(new Text("Summary Report"));

		table.appendChild(tr_hdr);

		table = PrepareSummary_Header(table);

		int row = 1;
		for (String key : AlljobsummaryMap.keySet())
		{
			Job_summary_dto dto = AlljobsummaryMap.get(key);

			Tr tr_row = new Tr();
			if (row % 2 == 0)
			{

				tr_row.setBgcolor("#DFECF5");
			}
			else {
				tr_row.setBgcolor("#ADCDE3");
			}


			Td td_sr_no = new Td();
			// td_sr_no.setAttribute("color", "#FFFFFF");
			tr_row.appendChild(td_sr_no);
			td_sr_no.appendChild(new Text(row));

			Td td_programe = new Td();
			tr_row.appendChild(td_programe);
			td_programe.appendChild(new Text(dto.getPrograme_name()));

			Td td_execCount = new Td();
			tr_row.appendChild(td_execCount);
			td_execCount.appendChild(new Text(dto.getExec_count()));



			Td td_firstActual = new Td();
			tr_row.appendChild(td_firstActual);
			td_firstActual.appendChild(new Text(formatter.format(dto.getFirst_act_start_time())));

			Td td_lastActual = new Td();
			tr_row.appendChild(td_lastActual);
			td_lastActual.appendChild(new Text(formatter.format(dto.getLast_act_start_time())));

			Td td_minTime = new Td();
			tr_row.appendChild(td_minTime);
			td_minTime.appendChild(new Text(FormatTime(dto.getMin_timeTaken())));


			Td td_maxTime = new Td();
			tr_row.appendChild(td_maxTime);
			td_maxTime.appendChild(new Text(FormatTime(dto.getMax_timeTaken())));

			Td td_avgTime = new Td();
			tr_row.appendChild(td_avgTime);
			td_avgTime.appendChild(new Text(FormatTime(dto.getAvg_timeTaken())));

			Td td_totTime = new Td();
			tr_row.appendChild(td_totTime);
			td_totTime.appendChild(new Text(FormatTime(dto.getTot_timeTaken())));

			table.appendChild(tr_row);

			row = row+1;



			//			System.out.println("Programe_count"+prog_count);
		}



		document.body.appendChild(table);
		document.body.appendChild(new Br());

		return document;

	} // End of Function


	private String FormatTime(long duration)
	{
		long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;

		//		long elapsedDays = duration / daysInMilli;
		//	duration = duration % daysInMilli;

		long elapsedHours = duration / hoursInMilli;
		duration = duration % hoursInMilli;

		long elapsedMinutes = duration / minutesInMilli;
		duration = duration % minutesInMilli;

		long elapsedSeconds = duration / secondsInMilli;

		return elapsedHours+":"+elapsedMinutes+":"+elapsedSeconds;

	}

	public Document prepare_Summary(Document document)
	{
		logger.info("creating header for report");

		Table table = new Table();


		Tr tr_hdr1 = new Tr();
		// tr_hdr1.setBgcolor("#D8DBDD").setStyle("color:#D8DBDD;");;
		tr_hdr1.setStyle("color:#0066FF;");
		Td td_Hdr1_1 = new Td();
		tr_hdr1.appendChild(td_Hdr1_1);
		td_Hdr1_1.setColspan("2");
		td_Hdr1_1.appendChild(new H2().appendChild(new Text("TCS Oracle Financial Batch Status Report")));
		table.appendChild(tr_hdr1);

		// table.setBorder("false");
		Tr tr_hdr2 = new Tr();
		Td td_Hdr2_1 = new Td();
		//td_Hdr1.setColspan("2");
		tr_hdr2.appendChild(td_Hdr2_1);
		td_Hdr2_1.appendChild(new H2().appendChild(new Text("Generate Date:")));
		Td td_Hdr2_2 = new Td();
		tr_hdr2.appendChild(td_Hdr2_2);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		td_Hdr2_2.appendChild(new H2().appendChild(new Text(dateFormat.format(date))));
		table.appendChild(tr_hdr2);

		document.body.appendChild(table);
		document.body.appendChild(new Br());
		return document;	
	}


	public Document PrepareErrorTable(Document document,Map<String,List> output)
	{
		logger.info("Preparing ErrorTable");
		logger.info(output);

		
		/* Extract Error Messages from Captured data */

		List<Of_monitor_dto> errorList = new ArrayList<Of_monitor_dto>();

		for (String key : output.keySet())
		{
			logger.info("key is "+key);

			//	System.out.println("Key :"+key);
			if(!(key.equalsIgnoreCase("Reverse Feed") || key.equalsIgnoreCase("Email and SMS Counts")
					|| key.equalsIgnoreCase("Single Window TPA Rejection") ||
					key.equalsIgnoreCase("Programs On Hold")|| key.equalsIgnoreCase("list of Programs not run")))
			{
				
				logger.info("output.get(key)"+output.get(key));
				List<Of_monitor_dto> programe_list = output.get(key);

				logger.info("programe_list"+programe_list);
				
				for ( Of_monitor_dto dto: programe_list)
				{
					
					logger.info(" error status code");

					
					if (dto.getStatusCode().equalsIgnoreCase("Error") || dto.getStatusCode().equalsIgnoreCase("Warning") )
					{
						errorList.add(dto); 
						logger.info(" errorlist generate");

					}
				}
				logger.info(" errorlist sorting");


				/* Sort Error Programe list order by Error then warning */
				Collections.sort(errorList, new Comparator<Of_monitor_dto>()
				{

					public int compare(Of_monitor_dto o1, Of_monitor_dto o2)
					{
						int value=0;

						if(o2.getStatusCode().equalsIgnoreCase("Error"))
						{
							value = 1;
						}
						//value =  o2.getStatusCode().compareTo(o1.getStatusCode());


						return value;
					}
				});
			}
		}
		
		logger.info(" sorted errorlist generate");

		logger.info(" errorlist sending");

		logger.info("calling generateHtml");
		
		
		document= Converter.getInstance().generateHtml("Error Summary Report ", errorList,document);	

		
		logger.info("error table prepared");
		return document;	
	}


	
	/****** Added on 7th May 2015 for Reverse Feed ******/
	public Document generateReverseFeedHtml(String programeName, List<Reverse_Feed_dto> programe_list,Document document)
	{
		//String htmlMsg="";
		
		
		

		Table table = new Table();
		Tr tr_hdr = new Tr();
		tr_hdr.setBgcolor("#D8DBDD");
		Td td_Hdr1 = new Td();
		td_Hdr1.setColspan("2");
		tr_hdr.appendChild(td_Hdr1);		
		td_Hdr1.appendChild(new Text("Programe Name:"));
		Td td_Hdr2 = new Td();
		td_Hdr2.setColspan("2");
		tr_hdr.appendChild(td_Hdr2);
		td_Hdr2.appendChild(new Text(programeName));

		table.appendChild(tr_hdr);

		table = Converter.getInstance().PrepareReverseFeedHeader(table);
		boolean errorOccured=false;
		int row = 0;

		if(programe_list.size()==0)
		{
			//System.out.println("Programe list Size is NUll=========");
			Tr tr_row = new Tr();
			Td td_noRow = new Td();
			td_noRow.setColspan("3");
			tr_row.appendChild(td_noRow);
			td_noRow.appendChild(new Text("No Data Found"));
			table.appendChild(tr_row);

		}
		for ( Reverse_Feed_dto dto: programe_list)
		{

			Tr tr_row = new Tr();
			if (row % 2 == 0)
			{

				tr_row.setBgcolor("#DFECF5");
			}
			else {
				tr_row.setBgcolor("#ADCDE3");
			}

			if(!dto.getIims_count().equals(dto.getOf_count())){
				tr_row.setBgcolor("#F5A9A9");
				errorOccured=true;
			}

			Td td_sr_no = new Td();
			tr_row.appendChild(td_sr_no);
			td_sr_no.appendChild(new Text(row+1));

			Td td_rev_feed = new Td();
			tr_row.appendChild(td_rev_feed);
			td_rev_feed.appendChild(new Text(dto.getReverse_feed()));

			Td td_iims = new Td();
			tr_row.appendChild(td_iims);
			td_iims.appendChild(new Text(dto.getIims_count()));

			Td td_of = new Td();
			tr_row.appendChild(td_of);
			td_of.appendChild(new Text(dto.getOf_count()));



			table.appendChild(tr_row);
			row = row+1;

		}
		
		// End of Programe List For Loop 
		
		String text="";
		if(errorOccured){
			 text="Reverse Feed for IIMS and OF does not match";
		}
		Text summaryText=new Text("");
		
		Table table1 = new Table();


		Tr tr_hdr1 = new Tr();
		if(errorOccured){
			 text="Reverse Feed for IIMS and OF does not match";
			 tr_hdr1.setStyle("color:red;");
		}
		else{
			text="No mismatch in Reverse Feed";
			 tr_hdr1.setStyle("color:#0066FF;");
		}
		Td td_Hdr1_1 = new Td();
		tr_hdr1.appendChild(td_Hdr1_1);
		
		td_Hdr1_1.appendChild(new H2().appendChild(new Text(text)));
		table1.appendChild(tr_hdr1);

		
		
		
		
		document.body.appendChild(table1);
		document.body.appendChild(table);
		document.body.appendChild(new Br());

		return document;

	} // End of Function
	
	public Table PrepareReverseFeedHeader(Table table)
	{
		Tr tr_row = new Tr();
		tr_row.setBgcolor("#2AA7FA");

		Td td_sr_no = new Td();
		tr_row.appendChild(td_sr_no);
		td_sr_no.appendChild(new Text("Sr.No"));
		
		
		Td td_rev_feed = new Td();
		tr_row.appendChild(td_rev_feed);
		td_rev_feed.appendChild(new Text("Reverse Feed"));


		Td td_iims = new Td();
		tr_row.appendChild(td_iims);
		td_iims.appendChild(new Text("OF"));

		Td td_of = new Td();
		tr_row.appendChild(td_of);
		td_of.appendChild(new Text("IIMS"));

		table.appendChild(tr_row);

		return table;	
	}
	
	/****** Added on 7th May 2015 for Reverse Feed [end]******/
	
	
	/******** added on 21st Aug 2015 for Email and SMS counts [start]******/
	public Document generateMsgCountHtml(String programeName, List<Msg_Count_dto> programe_list,Document document)
	{
		//String htmlMsg="";
		
		
		

		Table table = new Table();
		Tr tr_hdr = new Tr();
		tr_hdr.setBgcolor("#D8DBDD");
		Td td_Hdr1 = new Td();
		td_Hdr1.setColspan("2");
		tr_hdr.appendChild(td_Hdr1);		
		td_Hdr1.appendChild(new Text("Programe Name:"));
		Td td_Hdr2 = new Td();
		td_Hdr2.setColspan("2");
		tr_hdr.appendChild(td_Hdr2);
		td_Hdr2.appendChild(new Text(programeName));

		table.appendChild(tr_hdr);

		table = Converter.getInstance().PrepareMsgCountHeader(table);
		boolean errorOccured=false;
		int row = 0;

		if(programe_list.size()==0)
		{
			//System.out.println("Programe list Size is NUll=========");
			Tr tr_row = new Tr();
			Td td_noRow = new Td();
			td_noRow.setColspan("4");
			tr_row.appendChild(td_noRow);
			td_noRow.appendChild(new Text("No Data Found"));
			table.appendChild(tr_row);

		}
		for ( Msg_Count_dto dto: programe_list)
		{

			Tr tr_row = new Tr();
			if (row % 2 == 0)
			{

				tr_row.setBgcolor("#DFECF5");
			}
			else {
				tr_row.setBgcolor("#ADCDE3");
			}

			

			Td td_msg_type = new Td();
			tr_row.appendChild(td_msg_type);
			td_msg_type.appendChild(new Text(dto.getMsgType()));

			Td td_count = new Td();
			tr_row.appendChild(td_count);
			td_count.appendChild(new Text(dto.getCount()));

			Td td_date = new Td();
			tr_row.appendChild(td_date);
			td_date.appendChild(new Text(dto.getDate()));

			Td td_status = new Td();
			tr_row.appendChild(td_status);
			td_status.appendChild(new Text(dto.getStatus()));



			table.appendChild(tr_row);
			row = row+1;

		}
		
		// End of Programe List For Loop 
		
		
		
		document.body.appendChild(table);
		document.body.appendChild(new Br());

		return document;

	} // End of Function
	
	public Table PrepareMsgCountHeader(Table table)
	{
		Tr tr_row = new Tr();
		tr_row.setBgcolor("#2AA7FA");

		Td td_msg_type = new Td();
		tr_row.appendChild(td_msg_type);
		td_msg_type.appendChild(new Text("SMS/EMAIL"));
		
		
		Td td_count = new Td();
		tr_row.appendChild(td_count);
		td_count.appendChild(new Text("Count"));


		Td td_date = new Td();
		tr_row.appendChild(td_date);
		td_date.appendChild(new Text("Sent Date"));

		Td td_status = new Td();
		tr_row.appendChild(td_status);
		td_status.appendChild(new Text("Status"));

		table.appendChild(tr_row);

		return table;	
	}
	

	/******** added on 21st Aug 2015 for Email and SMS counts [end]******/
	
	/******** added on 21st Aug 2015 for Email and SMS counts [start]******/
	public Document generateTPARejectionList(String programeName, List<TPA_REJECTION_DTO> programe_list,Document document)
	{
		//String htmlMsg="";
		
		
		

		Table table = new Table();
		Tr tr_hdr = new Tr();
		tr_hdr.setBgcolor("#D8DBDD");
		Td td_Hdr1 = new Td();
		td_Hdr1.setColspan("2");
		tr_hdr.appendChild(td_Hdr1);		
		td_Hdr1.appendChild(new Text(programeName));
		

		table.appendChild(tr_hdr);

		table = Converter.getInstance().PrepareTPARejectionHeader(table);
		boolean errorOccured=false;
		int row = 0;

		if(programe_list.size()==0)
		{
			//System.out.println("Programe list Size is NUll=========");
			Tr tr_row = new Tr();
			Td td_noRow = new Td();
			td_noRow.setColspan("4");
			tr_row.appendChild(td_noRow);
			td_noRow.appendChild(new Text("No Data Found"));
			table.appendChild(tr_row);

		}
		int count=0;
		for ( TPA_REJECTION_DTO dto: programe_list)
		{

			Tr tr_row = new Tr();
			if (row % 2 == 0)
			{

				tr_row.setBgcolor("#DFECF5");
			}
			else {
				tr_row.setBgcolor("#ADCDE3");
			}

			

			Td td_srno = new Td();
			tr_row.appendChild(td_srno);
			td_srno.appendChild(new Text(++count));

			Td td_invNo = new Td();
			tr_row.appendChild(td_invNo);
			td_invNo.appendChild(new Text(dto.getInvoiceNo()));

			


			table.appendChild(tr_row);
			row = row+1;

		}
		
		// End of Programe List For Loop 
		
		
		
		document.body.appendChild(table);
		document.body.appendChild(new Br());

		return document;

	} // End of Function
	
	
	

	/******** added on 21st Aug 2015 for Email and SMS counts [start]******/
	public Document generateProgNotRunList(String programeName, List<Program_Not_Run_DTO> programe_list,Document document)
	{
		logger.info("generating html");
		//String htmlMsg="";
		
		Table table = new Table();
		Tr tr_hdr = new Tr();
		tr_hdr.setBgcolor("#D8DBDD");
		Td td_Hdr1 = new Td();
		td_Hdr1.setColspan("2");
		tr_hdr.appendChild(td_Hdr1);		
		td_Hdr1.appendChild(new Text(programeName));
		
		logger.info("1221");
		table.appendChild(tr_hdr);

		table = Converter.getInstance().PrepareProgNotRunHeader(table);
		boolean errorOccured=false;
		int row = 0;

		if(programe_list.size()==0)
		{
			//System.out.println("Programe list Size is NUll=========");
			Tr tr_row = new Tr();
			Td td_noRow = new Td();
			td_noRow.setColspan("4");
			tr_row.appendChild(td_noRow);
			td_noRow.appendChild(new Text("No Data Found"));
			table.appendChild(tr_row);

		}
		int count=0;
		for ( Program_Not_Run_DTO dto: programe_list)
		{

			Tr tr_row = new Tr();
			if (row % 2 == 0)
			{

				tr_row.setBgcolor("#DFECF5");
			}
			else {
				tr_row.setBgcolor("#ADCDE3");
			}

			

			Td td_srno = new Td();
			tr_row.appendChild(td_srno);
			td_srno.appendChild(new Text(++count));

			Td td_ProgramName = new Td();
			tr_row.appendChild(td_ProgramName);
			td_ProgramName.appendChild(new Text(dto.getProgramName()));

			logger.info("1263");


			table.appendChild(tr_row);
			row = row+1;

		}
		
		// End of Programe List For Loop 
		
		
		
		document.body.appendChild(table);
		document.body.appendChild(new Br());

		return document;

	} 
	
	
	public Document generateprogHoldList(String programeName, List<Program_Hold_DTO> programe_list,Document document)
	{

		Table table = new Table();
		Tr tr_hdr = new Tr();
		tr_hdr.setBgcolor("#D8DBDD");
		Td td_Hdr1 = new Td();
		td_Hdr1.setColspan("2");
		tr_hdr.appendChild(td_Hdr1);		
		td_Hdr1.appendChild(new Text(programeName));
		

		table.appendChild(tr_hdr);

		table = Converter.getInstance().PrepareProgHoldHeader(table);
		boolean errorOccured=false;
		int row = 0;

		if(programe_list.size()==0)
		{
			//System.out.println("Programe list Size is NUll=========");
			Tr tr_row = new Tr();
			Td td_noRow = new Td();
			td_noRow.setColspan("4");
			tr_row.appendChild(td_noRow);
			td_noRow.appendChild(new Text("No Data Found"));
			table.appendChild(tr_row);

		}
		int count=0;
		for ( Program_Hold_DTO dto: programe_list)
		{

			Tr tr_row = new Tr();
			if (row % 2 == 0)
			{

				tr_row.setBgcolor("#DFECF5");
			}
			else {
				tr_row.setBgcolor("#ADCDE3");
			}

			

			Td td_srno = new Td();
			tr_row.appendChild(td_srno);
			td_srno.appendChild(new Text(dto.getRequestId()));

			Td td_invNo = new Td();
			tr_row.appendChild(td_invNo);
			td_invNo.appendChild(new Text(dto.getProgramName()));

			


			table.appendChild(tr_row);
			row = row+1;

		}
		
		// End of Programe List For Loop 
		
		
		
		document.body.appendChild(table);
		document.body.appendChild(new Br());

		return document;

	} // End of Function
	
	public Table PrepareTPARejectionHeader(Table table)
	{
		Tr tr_row = new Tr();
		tr_row.setBgcolor("#2AA7FA");

		Td td_msg_type = new Td();
		tr_row.appendChild(td_msg_type);
		td_msg_type.appendChild(new Text("Sr. No."));
		
		
		Td td_count = new Td();
		tr_row.appendChild(td_count);
		td_count.appendChild(new Text("Invoice Number"));


		

		table.appendChild(tr_row);

		return table;	
	}
	
	public Table PrepareProgNotRunHeader(Table table)
	{
		Tr tr_row = new Tr();
		tr_row.setBgcolor("#2AA7FA");

		Td td_msg_type = new Td();
		tr_row.appendChild(td_msg_type);
		td_msg_type.appendChild(new Text("Sr. No.")); 
		
		
		Td td_count = new Td();
		tr_row.appendChild(td_count);
		td_count.appendChild(new Text("Program Name "));


		

		table.appendChild(tr_row);

		return table;	
	}
	
	
	
	public Table PrepareProgHoldHeader(Table table)
	{
		Tr tr_row = new Tr();
		tr_row.setBgcolor("#2AA7FA");

		Td td_msg_type = new Td();
		tr_row.appendChild(td_msg_type);
		td_msg_type.appendChild(new Text("Request ID"));
		
		
		Td td_count = new Td();
		tr_row.appendChild(td_count);
		td_count.appendChild(new Text("Program Name"));


		

		table.appendChild(tr_row);

		return table;	
	}


	
	
}












