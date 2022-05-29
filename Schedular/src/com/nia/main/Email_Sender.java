package com.nia.main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.hp.gagawa.java.Document;
import com.hp.gagawa.java.DocumentType;
import com.hp.gagawa.java.elements.Br;
import com.hp.gagawa.java.elements.Font;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Tr;
import com.nia.jpa.dao.MessageCountDao;
import com.nia.jpa.dao.OfMonitoringDao;
import com.nia.jpa.dao.Program_Hold_Dao;
import com.nia.jpa.dao.Program_Not_Run_Dao;
import com.nia.jpa.dao.TPA_Rejection_Dao;
import com.nia.jpa.daoImpl.AccPrograme_OfOfMonitoring_daoImpl;
import com.nia.jpa.daoImpl.Cr_Acc_OfOfMonitoring_daoImpl;
import com.nia.jpa.daoImpl.Gatherstat_OfMonitoring_daoImpl;
import com.nia.jpa.daoImpl.MessageCount_daoImpl;
import com.nia.jpa.daoImpl.NIA_Interface_OfMonitoring_daoImpl;
import com.nia.jpa.daoImpl.NIA_Interface_pre_day_daoImpl;
import com.nia.jpa.daoImpl.Program_Hold_DaoImpl;
import com.nia.jpa.daoImpl.Program_Not_Run_DaoImpl;
import com.nia.jpa.daoImpl.ReverseFeed_daoImpl;
import com.nia.jpa.daoImpl.TPA_Rejection_DaoImpl;
import com.nia.jpa.dto.Job_summary_dto;
import com.nia.jpa.dto.Msg_Count_dto;
import com.nia.jpa.dto.Of_monitor_dto;
import com.nia.jpa.dto.Program_Hold_DTO;
import com.nia.jpa.dto.Program_Not_Run_DTO;
import com.nia.jpa.dto.Reverse_Feed_dto;
import com.nia.jpa.dto.TPA_REJECTION_DTO;
import com.nia.jpa.exception.Job_monitor_Exception;
import com.nia.jpa.exception.MessageCountDaoException;
import com.nia.jpa.exception.OfMonitoringDaoException;
import com.nia.jpa.exception.ProgramHoldDaoException;
import com.nia.jpa.exception.ReverseFeedDaoException;
import com.nia.jpa.exception.TPARejectionDaoException;
import com.nia.jpa.helper.Converter;
import com.nia.jpa.helper.ResourceManager;


public class Email_Sender {
	
	protected static final Logger logger = Logger.getLogger( Email_Sender.class );
	Connection con;
	  
	
	/* Fetch data from database and generate Map */
	public Map<String,List> Generate_outputMap() throws OfMonitoringDaoException, SQLException, ClassNotFoundException, ReverseFeedDaoException, MessageCountDaoException, TPARejectionDaoException, ProgramHoldDaoException
	{
		logger.info("inside first method ");
		
		Map<String,List> outputMap = (java.util.HashMap<java.lang.String, java.util.List>) new LinkedHashMap<String,List>();

		
	// Prepare List of Accounting Programe 
	
		logger.info("1 accprograme_List");
	List<Of_monitor_dto> accprograme_List = list_AccountingPrograme();
	outputMap.put("Accounting Program", accprograme_List);
	
	logger.info("2 interface_program_List");
	List<Of_monitor_dto> interface_program_List = list_interfacePrograme();
	outputMap.put("NIA Interface Program", interface_program_List);

	
	logger.info("3 NIA Sysdate-1 Interface Program");
	List<Of_monitor_dto> interface_previous_day_List = list_previoud_day_interfacePrograme();
	outputMap.put("NIA Sysdate-1 Interface Program", interface_previous_day_List);
	
	
	logger.info("4 gatherstat_List");
	List<Of_monitor_dto> gatherstat_List = list_gatherstat();
	outputMap.put("Gather Stat", gatherstat_List);
	
	
	
	/****** Added on 7th May 2015 for Reverse Feed [start]******/
	logger.info("5 reverse_feed_List");
	List<Reverse_Feed_dto> reverse_feed_List = list_reverseFeed();
	outputMap.put("Reverse Feed", reverse_feed_List);
	/****** Added on 7th May 2015 for Reverse Feed [end]******/
	
	
	
	/******** added on 21st Aug 2015 for Email and SMS counts [start]******/
	logger.info("6 list_msgCount");
	List<Msg_Count_dto> messageCount_List = list_msgCount();
	outputMap.put("Email and SMS Counts", messageCount_List);
	/******** added on 21st Aug 2015 for Email and SMS counts [end]******/
	
	
	
	
	
	/******** added on 21st Aug 2015 for Email and SMS counts [start]******/
	logger.info("7 list_tpaRejection");
	List<TPA_REJECTION_DTO> tpa_rejection_list = list_tpaRejection();
	outputMap.put("Single Window TPA Rejection", tpa_rejection_list);
	/******** added on 21st Aug 2015 for Email and SMS counts [end]******/
	
	
	
	
	
	
	
	/******** added on 19 Nov 2015 for Program on hold [start]******/
	logger.info("8 list_prog_hold");
	List<Program_Hold_DTO> prog_hold_list = list_prog_hold();
	outputMap.put("Programs On Hold", prog_hold_list);
	/******** added on 19 Nov 2015 for Program on hold [end]******/
	
	
	/******** added on 27 march 2019 for list did not run [start]******/
	logger.info("9 list did not run");
	List<Program_Not_Run_DTO> listnotrun = list_prog_not_run();
	logger.info("listnotrun---"+listnotrun);
	outputMap.put("list of Programs not run", listnotrun);
	/******** added on 19 Nov 2015 for Program on hold [end]******/
	
	
	return outputMap;
	}
	
	

	public List<Of_monitor_dto> list_interfacePrograme() throws SQLException, ClassNotFoundException, OfMonitoringDaoException 
	{
		logger.info("inside list_interfacePrograme , same process like 1 ");
			con = ResourceManager.getInstance().getConnection();
		//	con.setAutoCommit(false);
			
			OfMonitoringDao dao = new NIA_Interface_OfMonitoring_daoImpl();
			
			List<Of_monitor_dto> programe_list = dao.findAll(con);
		//	System.out.println("List Prepared"+programe_list.size());
		
				
		return programe_list;
	}

	
	public List<Of_monitor_dto> list_previoud_day_interfacePrograme() throws SQLException, ClassNotFoundException, OfMonitoringDaoException 
	{
		logger.info("inside list_previoud_day_interfacePrograme() , same process like 2 ");

			con = ResourceManager.getInstance().getConnection();
		//	con.setAutoCommit(false);
			
			OfMonitoringDao dao = new NIA_Interface_pre_day_daoImpl();
			
			List<Of_monitor_dto> programe_list = dao.findAll(con);
		//	System.out.println("List Prepared"+programe_list.size());
		
				
		return programe_list;
	}
	

	
	
	public List<Of_monitor_dto> list_AccountingPrograme() throws SQLException, ClassNotFoundException, OfMonitoringDaoException 
	{
		logger.info("173");
		
			con = ResourceManager.getInstance().getConnection();
		//	con.setAutoCommit(false);
			logger.info("177");
			OfMonitoringDao dao = new AccPrograme_OfOfMonitoring_daoImpl();
			
			List<Of_monitor_dto> programe_list = dao.findAll(con);
		//	System.out.println("List Prepared"+programe_list.size());
		
				
		return programe_list;
	}

	
	
	public List<Of_monitor_dto> list_gatherstat() throws SQLException, ClassNotFoundException, OfMonitoringDaoException 
	{
		logger.info("inside list_gatherstat() , same process like 1 ");

			con = ResourceManager.getInstance().getConnection();
		//	con.setAutoCommit(false);
			
			OfMonitoringDao dao = new Gatherstat_OfMonitoring_daoImpl();
			
			List<Of_monitor_dto> programe_list = dao.findAll(con);
		//	System.out.println("List Prepared"+programe_list.size());
		
				
		return programe_list;
	}
	
	public String prepare_email_message(Map<String,List> output) throws ParseException
	{
	
		Document document = new Document(DocumentType.XHTMLStrict);	
		document = Converter.getInstance().prepare_Summary(document);
		
		
		
		/****** Added on 7th May 2015 for Single Window TPA Rejection ******/
		List<TPA_REJECTION_DTO> tpa_rejection_list = output.get("Single Window TPA Rejection");
		/*SOC - ADDED BY SARALA - HEADING CHNAGE FOR Single Window TPA Rejection*/
		//document = Converter.getInstance().generateTPARejectionList("Single Window TPA Rejection", tpa_rejection_list,document);
		document = Converter.getInstance().generateTPARejectionList("Critical Rejections (TPA/BSSBY/HPCL)", tpa_rejection_list,document);
		/*EOC - ADDED BY SARALA - HEADING CHNAGE FOR Single Window TPA Rejection*/
		/****** Added on 7th May 2015 for Single Window TPA Rejection ******/
		
		
		List<Program_Hold_DTO> prog_Hold_list = output.get("Programs On Hold");
		document = Converter.getInstance().generateprogHoldList("Programs On Hold", prog_Hold_list,document);

		
		
		
		/****** Added on 7th May 2015 for Reverse Feed ******/
		List<Reverse_Feed_dto> programe_list = output.get("Reverse Feed");
		document = Converter.getInstance().generateReverseFeedHtml("Reverse Feed", programe_list,document);
		/****** Added on 7th May 2015 for Reverse Feed ******/
		
		/******** added on 21st Aug 2015 for Email and SMS counts [start]******/
		List<Msg_Count_dto> msgCountList = output.get("Email and SMS Counts");
		document = Converter.getInstance().generateMsgCountHtml("Email and SMS Counts", msgCountList,document);
		/******** added on 21st Aug 2015 for Email and SMS counts [end]******/
		
		
		/******** added on 27st Mar 2019 for program that did not run [start]******/
		List<Program_Not_Run_DTO> Program_Not_Run_List = output.get("list of Programs not run");
		document = Converter.getInstance().generateProgNotRunList("list of Programs not run", Program_Not_Run_List,document);
		/******** added on 21st Aug 2015 for Email and SMS counts [end]******/
		
		document = Converter.getInstance().PrepareErrorTable(document, output);

		document = Converter.getInstance().PrepareJobSummaryReport(document, output);
		
		
		
	String emailMsg="";	

	int prog_count = 0;
	for (String key : output.keySet())
	{
		
		/* Prepare details report only for selected batch programe */
		if (key.equalsIgnoreCase("Create Accounting") || 
			key.equalsIgnoreCase("Accounting Program") || 
			key.equalsIgnoreCase("Gather Stat")	 || 
			key.equalsIgnoreCase("NIA Sysdate-1 Interface Program")
			)
		{
	
			prog_count = prog_count+1;	
		//	System.out.println("Key :"+key);
			//System.out.println("Value Length :"+output.get(key).size());
			document =Converter.getInstance().generateHtml(key, output.get(key),document);
			
		}
		
	
//	System.out.println("Programe_count"+prog_count);
	}
	
	emailMsg = document.write();
	
	// Test code for html file generation [Start]
	// added by sarala 
/*try {
	      //File outputFile = new File("C:\\Users\\478071\\Desktop\\test.html");
	      File outputFile = new File("D:\\test.html");
	      PrintWriter out = new PrintWriter(new FileOutputStream(outputFile));
	      
	      out.println(document.write()); 
	 //     System.out.println(document.write());
	      out.close();
	    } catch (FileNotFoundException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }*/
	// added by sarala 

	
	// Test code for html file generation [End]
	return emailMsg;
	}
	
	/****** Added on 7th May 2015 for Reverse Feed ******/
	public List<Reverse_Feed_dto> list_reverseFeed() throws SQLException, ClassNotFoundException, ReverseFeedDaoException 
	{
		logger.info(" inside 5 list_reverseFeed");
		
			con = ResourceManager.getInstance().getConnection();
		//	con.setAutoCommit(false);
			
			ReverseFeed_daoImpl dao = new ReverseFeed_daoImpl();
			
			List<Reverse_Feed_dto> programe_list = dao.findAll(con);
			
		
				
		return programe_list;
	}
	
	/****** Added on 7th May 2015 for Reverse Feed ******/
	
	/******** added on 21st Aug 2015 for Email and SMS counts [start]
	 * @throws MessageCountDaoException ******/  
	public List<Msg_Count_dto> list_msgCount() throws SQLException, ClassNotFoundException, MessageCountDaoException 
	{
		
			con = ResourceManager.getInstance().getConnection();
			MessageCountDao dao = new MessageCount_daoImpl() ;
				
				
			
			List<Msg_Count_dto> programe_list = dao.findAll(con);
			
		
				
		return programe_list;
	}
	
	
	public List<TPA_REJECTION_DTO> list_tpaRejection() throws SQLException, ClassNotFoundException, TPARejectionDaoException 
	{
		
			con = ResourceManager.getInstance().getConnection();
			TPA_Rejection_Dao dao = new TPA_Rejection_DaoImpl() ;
				
				
			
			List<TPA_REJECTION_DTO> programe_list = dao.findAll(con);
			
		
				
		return programe_list;
	}
	
	public List<Program_Hold_DTO> list_prog_hold() throws SQLException, ClassNotFoundException, ProgramHoldDaoException 
	{
		
			con = ResourceManager.getInstance().getConnection();
			Program_Hold_Dao dao = new Program_Hold_DaoImpl() ;
				
				
			
			List<Program_Hold_DTO> programe_list = dao.findAll(con);
			
		
				
		return programe_list;
	}
	
	 
	private List<Program_Not_Run_DTO> list_prog_not_run() throws SQLException, ClassNotFoundException, ProgramHoldDaoException  {
		// TODO Auto-generated method stub
	
		
		logger.info("getting connection");
		con = ResourceManager.getInstance().getConnection();
		Program_Not_Run_Dao dao = new Program_Not_Run_DaoImpl() ;
	
		List<Program_Not_Run_DTO> programe_list = dao.findAll(con);
		logger.info("get programe_list--"+ programe_list);
	
			
	return programe_list;
		
	
	}

	
	
	
	/******** added on 21st Aug 2015 for Email and SMS counts [end]******/
    
	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws Job_monitor_Exception 
	 * @throws OfMonitoringDaoException 
	 * @throws ParseException 
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		/*String log4jConfPath = "src/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);*/
		try {
			long t1 = System.currentTimeMillis();	
			Email_Sender es = new Email_Sender();
			Map<String,List> outputMap = es.Generate_outputMap();
			String emailMsg = es.prepare_email_message(outputMap);
		
			// commented by sarala to stop email send
			ResourceManager.getInstance().sendHtmlEmail(emailMsg);
		long t2 = System.currentTimeMillis();
//		System.out.println("Email_Sent & Time taken:"+(t2-t1));
		 		

	if (logger.isDebugEnabled()) {
				logger.debug( " Email sent. & Time taken to generate Report :" + (t2-t1) + " ms)");
			}

		}
		catch(OfMonitoringDaoException _e)
		{
			logger.error( " OfMonitoringDaoException Exception: " + _e.getMessage(), _e );
		}
		catch(SQLException _e)
		{
			_e.printStackTrace();
			logger.error( " SQLException Exception: " + _e.getMessage(), _e );
		}
		catch(ClassNotFoundException _e)
		{
			logger.error( " ClassNotFoundException Exception: " + _e.getMessage(), _e );
		}
		 catch (ReverseFeedDaoException _e) {
			logger.error( " ReverseFeedDaoException Exception: " + _e.getMessage(), _e );
		
		} 
			catch (ParseException _e) {
			logger.error( " ParseException Exception: " + _e.getMessage(), _e );
	
		}// commented by sarala 
	    catch (AddressException _e) {
			// TODO Auto-generated catch block
			logger.error( " AddressException Exception: " + _e.getMessage(), _e );
			
		} catch (MessagingException _e) {
			// TODO Auto-generated catch block
			logger.error( " MessagingException Exception: " + _e.getMessage(), _e );
		} catch (MessageCountDaoException _e) {
			// TODO Auto-generated catch block
			logger.error( " ReverseFeedDaoException Exception: " + _e.getMessage(), _e );
		} catch (TPARejectionDaoException _e) {
			// TODO Auto-generated catch block
			logger.error( "TPARejectionDaoException  Exception: " + _e.getMessage(), _e );
		} catch (ProgramHoldDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
