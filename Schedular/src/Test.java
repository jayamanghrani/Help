import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;

import com.hp.gagawa.java.Document;
import com.hp.gagawa.java.DocumentType;
import com.hp.gagawa.java.elements.Br;
import com.hp.gagawa.java.elements.Font;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Tr;
import com.nia.jpa.dao.OfMonitoringDao;
import com.nia.jpa.daoImpl.Cr_Acc_OfOfMonitoring_daoImpl;
import com.nia.jpa.daoImpl.NIA_Interface_OfMonitoring_daoImpl;
import com.nia.jpa.dto.Job_summary_dto;
import com.nia.jpa.dto.Of_monitor_dto;
import com.nia.jpa.exception.Job_monitor_Exception;
import com.nia.jpa.exception.OfMonitoringDaoException;
import com.nia.jpa.helper.Converter;
import com.nia.jpa.helper.ResourceManager;


public class Test {
	
	protected static final Logger logger = Logger.getLogger( Test.class );
	Connection con;
	  
	
	/* Fetch data from database and generate Map */
	public Map<String,List> Generate_outputMap() throws OfMonitoringDaoException, SQLException, ClassNotFoundException
	{
		Map<String,List> outputMap = (java.util.HashMap<java.lang.String, java.util.List>) new LinkedHashMap<String,List>();

	// Prepare List of Create Account List 
		logger.info("inside Generate_outputMap");
	List<Of_monitor_dto> createacc_List = list_createAccountingPrograme();
	outputMap.put("Create Accounting", createacc_List);
		
	// Prepare List of Accounting Programe 
	
	List<Of_monitor_dto> accprograme_List = list_createAccountingPrograme();
	outputMap.put("Accounting Program", accprograme_List);
	
	List<Of_monitor_dto> interface_program_List = list_interfacePrograme();
	outputMap.put("NIA Interface Program", interface_program_List);
	
	return outputMap;
	}
	
	
	
	public List<Of_monitor_dto> list_interfacePrograme() throws SQLException, ClassNotFoundException, OfMonitoringDaoException 
	{
		
			con = ResourceManager.getInstance().getConnection();
		//	con.setAutoCommit(false);
			
			OfMonitoringDao dao = new NIA_Interface_OfMonitoring_daoImpl();
			
			List<Of_monitor_dto> programe_list = dao.findAll(con);
			//System.out.println("List Prepared"+programe_list.size());
		
				
		return programe_list;
	}
	
	
	
	public List<Of_monitor_dto> list_createAccountingPrograme() throws SQLException, ClassNotFoundException, OfMonitoringDaoException 
	{
		logger.info("inside list_createAccountingPrograme()");
			con = ResourceManager.getInstance().getConnection();
		//	con.setAutoCommit(false);
			logger.info("got connection");
			OfMonitoringDao dao = new Cr_Acc_OfOfMonitoring_daoImpl();
			
			List<Of_monitor_dto> programe_list = dao.findAll(con);
		//	System.out.println("List Prepared"+programe_list.size());
			logger.info("programe_list -"+ programe_list);
				
		return programe_list;
	}
	
	public String prepare_email_message(Map<String,List> output) throws ParseException
	{
		
		logger.info("inside prepare_email_message");
	
		Document document = new Document(DocumentType.XHTMLStrict);	
		
		document = Converter.getInstance().prepare_Summary(document);
		document = Converter.getInstance().PrepareErrorTable(document, output);
		
		document = Converter.getInstance().PrepareJobSummaryReport(document, output);
		
	String emailMsg="";	

	int prog_count = 0;
	for (String key : output.keySet())
	{
		
		/* Prepare details report only for selected batch programe */
		if (key.equalsIgnoreCase("Create Accounting") || key.equalsIgnoreCase("Accounting Program") )
	{
	
			prog_count = prog_count+1;	
		//	System.out.println("Key :"+key);
			//System.out.println("Value Lenghth :"+output.get(key).size());
			document =Converter.getInstance().generateHtml(key, output.get(key),document);
			
		}
	
	
//	System.out.println("Programe_count"+prog_count);
	}
	
	emailMsg = document.write();
	
	// Test code for html file generation [Start]
	
	try {
	      File outputFile = new File("E:\\NIA\\Things_To_Do\\OF_Automation\\Sample\\test.html");
	      PrintWriter out = new PrintWriter(new FileOutputStream(outputFile));
	      
	      out.println(document.write());
	 //     System.out.println(document.write());
	      out.close();
	    } catch (FileNotFoundException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	
	
	// Test code for html file generation [End]
	return emailMsg;
	}
	
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
	public static void main(String[] args) throws Job_monitor_Exception, SQLException, ClassNotFoundException, OfMonitoringDaoException, ParseException, AddressException, MessagingException {
		// TODO Auto-generated method stub

		long t1 = System.currentTimeMillis();	
		Test test = new Test();
		logger.info("1");
		Map<String,List> outputMap = test.Generate_outputMap();
		logger.info("2");
		String emailMsg = test.prepare_email_message(outputMap);
		logger.info("3");
		// System.out.println("Email sent.");
		
		ResourceManager.getInstance().sendHtmlEmail(emailMsg);
		logger.info("4");
		long t2 = System.currentTimeMillis();
		if (logger.isDebugEnabled()) {
			logger.debug( " Time taken to generate Report :" + (t2-t1) + " ms)");
		}
		
		
	}

}
