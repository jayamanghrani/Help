package com.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;
import com.bean.propertiesBean;
import com.util.encryptDecrypt;

public class propertiesDao {
	
	static propertiesBean b = new propertiesBean();
	static org.apache.log4j.Logger logger = Logger.getLogger(propertiesDao.class);  
	public static void dbproperties(){
	try {
		InputStream db = null ;
		Properties prop = new  Properties();
		/*String filepath= "D:/joginder/ccm/Joginder/Joginder/2/2/excel/src/db.properties";*/
		String filepath= "/wls/middleware/app_config/ccmReports/db.properties";
		db = new FileInputStream(new File(filepath));
		prop.load(db);
		propertiesBean.setAgingwithIP(prop.getProperty("AgingWithIPquery"));
		propertiesBean.setH1Query(prop.getProperty("H1Query"));
		propertiesBean.setNewlyOpenedTickets(prop.getProperty("NewlyOpenedTicketsQuery"));
		propertiesBean.setNewlyOpenedHO(prop.getProperty("NewlyOpenedHOQuery"));
		propertiesBean.setReopen10To3pmQuery(prop.getProperty("Reopen10To3pmQuery"));
		propertiesBean.setReopenedTickets(prop.getProperty("ReopenedQuery"));
		propertiesBean.setNewlyOpenedTickets(prop.getProperty("NewlyOpenedTicketsQuery"));
		propertiesBean.setResolvedByHo(prop.getProperty("ResolvedByHoQuery"));
		propertiesBean.setResolvedByTickets(prop.getProperty("ResolvedByTicketsQuery"));
		propertiesBean.setReturnAfterClarification(prop.getProperty("ReturnAfterClarificationQuery"));
		propertiesBean.setSentForClarification(prop.getProperty("SentForClarificationQuery"));
		propertiesBean.setReopen10To3pmQuery(prop.getProperty("Reopen10To3pmQuery"));
		propertiesBean.setUpdated3pmQuery(prop.getProperty("updated3pmQuery"));
		propertiesBean.setUpdated10amQuery(prop.getProperty("updated10amQuery"));
		propertiesBean.setReopen3To6pmQuery(prop.getProperty("Reopen3To6pmQuery"));
		propertiesBean.setUpdated_3pm_OF_3To6(prop.getProperty("updated_3pm_OF_3To6"));
		propertiesBean.setUpdated_6pm_OF_3To6(prop.getProperty("updated_6pm_OF_3To6"));
		propertiesBean.setResolvedByHO_OFWeekly(prop.getProperty("ResolvedByHO_OFWeekly"));
		propertiesBean.setResolvedByTCS_OFWeekly(prop.getProperty("ResolvedByTCS_OFWeekly"));
		propertiesBean.setSentForClarificationHO_NEW_OFWeekly(prop.getProperty("SentForClarificationHO_NEW_OFWeekly"));
		propertiesBean.setSentForClarificationTCS_NEW_OFWeekly(prop.getProperty("SentForClarificationTCS_NEW_OFWeekly"));
				
	} catch (FileNotFoundException e) {
		logger.error("ErroMessage:db.properites file not found please check the file "+"Exception:e.printStackTrace()");
		} catch (IOException e) {
		logger.error("ErroMessage:properties not loaded " +"Exception:e.printStackTrace()");
		/*e.printStackTrace();*/
	}
	
	}
	
	public static void config(){
		try {
			InputStream config = null ;
			Properties prop4config = new  Properties();
			/*String filepath= "D:/joginder/ccm/Joginder/Joginder/2/2/excel/src/config.properties";*/
			String filepath= "/wls/middleware/app_config/ccmReports/config.properties";
			config = new FileInputStream(new File(filepath));
			prop4config.load(config);
			propertiesBean.setDRIVERNAME(prop4config.getProperty("DRIVERNAME"));
			propertiesBean.setURL(prop4config.getProperty("URL"));
			final String secretKey = "TcsNia@ccm";
			String USERNAME = encryptDecrypt.decrypt(prop4config.getProperty("USERNAME"), secretKey) ;
			String PASSWORD = encryptDecrypt.decrypt(prop4config.getProperty("PASSWORD"), secretKey) ;
			
			//System.out.println("usr"+ USERNAME);
			//System.out.println("pwd"+ PASSWORD);
			
			propertiesBean.setUSERNAME(USERNAME);
			propertiesBean.setPASSWORD(PASSWORD);
			
		} catch (FileNotFoundException e) {
			logger.error("ErroMessage:config.properites file not found please check the file "+"Exception:e.printStackTrace()");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			logger.error("ErroMessage:properties not loaded"+"Exception:e.printStackTrace()");
			
		}
		
	}
	
}
