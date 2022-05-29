package com.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.bean.H1QueryBean;
import com.bean.NewlyOpenedHOBean;
import com.bean.NewlyOpenedTicketsBean;
import com.bean.Reopen10To3pmBean;
import com.bean.Reopen3To6pmBean;
import com.bean.ReopenedBean;
import com.bean.ResolvedByHO_OFWeeklyBean;
import com.bean.ResolvedByHoBean;
import com.bean.ResolvedByTicketsBean;
import com.bean.ResolvedbyTCS_OFWeeklyBean;
import com.bean.ReturnAfterClarificationBean;
import com.bean.SentForClarificationBean;
import com.bean.SentForClarificationHO_NEW_OFWeeklyBean;
import com.bean.SentForClarificationTCS_NEW_OFWeeklyBean;
import com.bean.aginingWithIPBean;
import com.bean.dateBean;
import com.bean.propertiesBean;
import com.bean.updated10amQueryBean;
import com.bean.updated3pmQueryBean;
import com.bean.updated_3pm_OF_3To6Bean;
import com.bean.updated_6pm_OF_3To6Bean;
import com.util.DatabaseUtil;

public  class dao {
	static PreparedStatement psmt=null;
	static ResultSet rs=null;
	static Connection con = null;
	propertiesBean b1 = new propertiesBean();
	propertiesDao d = new propertiesDao();
	static Statement smt = null;
	static org.apache.log4j.Logger logger = Logger.getLogger(propertiesDao.class);  
	@SuppressWarnings("finally")
	public  static ArrayList<aginingWithIPBean> getAgingwithwipDao() {
		
		//System.out.println("enterd in getAgingwithwipDao");
		ArrayList<aginingWithIPBean> AgingwithipList= new ArrayList<aginingWithIPBean>();
		try {
		con = DatabaseUtil.getConnection();
						
			/*String value = prop.getProperty("AgingWithIPquery");*/
			////System.out.println("Query is :-"+propertiesBean.getAgingwithIP());
			smt = con.createStatement();
			rs = smt.executeQuery(propertiesBean.getAgingwithIP());
			
			if(rs.next()){
			while (rs.next()){
				 int ticketNO=rs.getInt(1);
				 Date ticketLogDate = rs.getDate(2);
				 Date assignedOn = rs.getDate(3);
				 String problemCategory = rs.getString(4);
				 String problemType = rs.getString(5);
				 String problemItem = rs.getString(6);
				 String problemSummary = rs.getString(7);
				 String problemDescription = rs.getString(8);
				 int ticketLoggedUserID = rs.getInt(9);
				 String ticketLoggedUsername = rs.getString(10);
				 String PersonResponsibleUserId = rs.getString(11);
				 String PersonResponsibeUserName = rs.getString(12);
				 String role =rs.getString(13);
				 String pendingToWIP= rs.getString(14);
				 String department= rs.getString(15);
				 String status = rs.getString(16);
				 String userOfficeCode =  rs.getString(17);
				 String priority = rs.getString(18);
				 String severity = rs.getString(19);
				 String customerGroupName = rs.getString(20);
				 String callClassification = rs.getString(21);
				 String spCallClassification = rs.getString(22);
				 int noOfAgingDays = rs.getInt(23);
			////System.out.println(noOfAgingDays);
			aginingWithIPBean b = new aginingWithIPBean(ticketNO, ticketLogDate, assignedOn, problemCategory, problemType, problemItem, problemSummary, problemDescription, ticketLoggedUserID, ticketLoggedUsername, PersonResponsibleUserId, PersonResponsibeUserName, role, pendingToWIP, department, status, userOfficeCode, priority, severity, customerGroupName, callClassification, spCallClassification, noOfAgingDays);
			AgingwithipList.add(b);
			////System.out.println("contents added in bean");
			}
			////System.out.println("Query successfully executed");
		}
			} catch (SQLException e) {
				logger.error("ErroMessage:sql or IO exception in AgingwithIP method in  dao class " +"Exception:"+e.getStackTrace());
				return AgingwithipList;
			}
			
		finally {
				DatabaseUtil.closePreparedStatement(psmt);
				DatabaseUtil.closeStatement(smt);
				DatabaseUtil.closeResultSet(rs);
				DatabaseUtil.closeConnection(con);
				return AgingwithipList;
		}
	
	}

	public  static ArrayList<H1QueryBean> getH1QueryDao(){
		propertiesDao.dbproperties();
		propertiesDao.config();
		ArrayList<H1QueryBean> fetchList = new ArrayList<H1QueryBean>(); 
		try {
		con = DatabaseUtil.getConnection();
			/*String query = prop.getProperty("H1Query");*/
			smt = con.createStatement();
			rs = smt.executeQuery(propertiesBean.getH1Query());
		while(rs.next()){
			String ticketNo = rs.getString(1);
			////System.out.println(ticketNo);
			String SPUsername = rs.getString(2) ;
			String Department = rs.getString(3);
			H1QueryBean bean = new H1QueryBean(ticketNo, SPUsername, Department);
			fetchList.add(bean);
		}
		
		return fetchList;
		} catch (SQLException | IOException e) {
			logger.error("ErroMessage:sql or IO exception in H1Query method in  dao class " +"Exception:"+e.getStackTrace());
		}
		finally {
			DatabaseUtil.closePreparedStatement(psmt);
			DatabaseUtil.closeStatement(smt);
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeConnection(con); 
			}
		return null;	
	}

	public  static ArrayList<NewlyOpenedHOBean> getNewlyOpenedHODao() {
		propertiesDao.dbproperties();
		propertiesDao.config();
		ArrayList<NewlyOpenedHOBean> fetchList = new ArrayList<NewlyOpenedHOBean>(); 
		try {
		con = DatabaseUtil.getConnection();
		/*String query = prop.getProperty("NewlyOpenedHOQuery");*/
		/*//System.out.println("froooom: - " +dateBean.getUserEnteredFromdate());
		//System.out.println("Toooooo: - " +dateBean.getUserEnteredTodate());*/
			//System.out.println("query is : "+propertiesBean.getNewlyOpenedHO());
			psmt = con.prepareStatement(propertiesBean.getNewlyOpenedHO());
			psmt.setString(1, dateBean.getUserEnteredFromdate());
			psmt.setString(2, dateBean.getUserEnteredTodate());
			psmt.setString(3, dateBean.getUserEnteredFromdate());
			psmt.setString(4, dateBean.getUserEnteredTodate());
			////System.out.println(psmt);
			rs = psmt.executeQuery();
			////System.out.println(rs);
		while(rs.next()){
			String ticketNO = rs.getString(1);
			Date assignedOn = rs.getDate(2);
			Date ticketLogDate = rs.getDate(3);
			String problemCategory = rs.getString(4);
			String problemType = rs.getString(5);
			String problemItem = rs.getString(6);
			String problemSummary = rs.getString(7);
			String problemDescription = rs.getString(8);
			String ticketLoggedUserID = rs.getString(9);
			String ticketLoggedUsername = rs.getString(10);
			String SpUserId = rs.getString(11);
			String SpUserName = rs.getString(12);
			String role = rs.getString(13);
			String department = rs.getString(14);
			String status = rs.getString(15);
			String userOfficeCode = rs.getString(16);
			String priority = rs.getString(17);
			String severity = rs.getString(18);
			String customerGroupName = rs.getString(19);
			String callClassification = rs.getString(20);
			String spCallClassification = rs.getString(21);
			NewlyOpenedHOBean bean = new NewlyOpenedHOBean(ticketNO, assignedOn, ticketLogDate, problemCategory, problemType, problemItem, problemSummary, problemDescription, ticketLoggedUserID, ticketLoggedUsername, SpUserId, SpUserName, role, department, status, userOfficeCode, priority, severity, customerGroupName, callClassification, spCallClassification);
			fetchList.add(bean);
		}
		
		return fetchList;
		} catch (SQLException | IOException e) {
			logger.error("ErroMessage:sql or IO exception in NewlyOpenedHO method in dao class " +"Exception:"+e.getStackTrace());
		}
		finally {
			DatabaseUtil.closePreparedStatement(psmt);
			DatabaseUtil.closeStatement(smt);
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeConnection(con); 
			}
		return fetchList;	
	}
	
	public  static ArrayList<NewlyOpenedTicketsBean> getNewlyOpenedTicketsDao() {
			propertiesDao.dbproperties();
			propertiesDao.config();
			ArrayList<NewlyOpenedTicketsBean> fetchList = new ArrayList<NewlyOpenedTicketsBean>();
			
			/*String query = prop.getProperty("NewlyOpenedTicketsQuery");*/
			/*//System.out.println("froooom: - " +dateBean.getUserEnteredFromdate());
			//System.out.println("Toooooo: - " +dateBean.getUserEnteredTodate());*/
			try {
				con = DatabaseUtil.getConnection();
				/*//System.out.println("query is : "+propertiesBean.getNewlyOpenedTickets());*/
				psmt = con.prepareStatement(propertiesBean.getNewlyOpenedTickets());
				psmt.setString(1, dateBean.getUserEnteredFromdate());
				psmt.setString(2, dateBean.getUserEnteredTodate());
				psmt.setString(3, dateBean.getUserEnteredFromdate());
				psmt.setString(4, dateBean.getUserEnteredTodate());
				////System.out.println(psmt);
				rs = psmt.executeQuery();
				////System.out.println(rs);
			while(rs.next()){
				String ticketNO = rs.getString(1);
				Date assignedOn = rs.getDate(2);
				Date ticketLogDate = rs.getDate(3);
				String problemCategory = rs.getString(4);
				String problemType = rs.getString(5);
				String problemItem = rs.getString(6);
				String problemSummary = rs.getString(7);
				String problemDescription = rs.getString(8);
				String ticketLoggedUserID = rs.getString(9);
				String ticketLoggedUsername = rs.getString(10);
				String SpUserId = rs.getString(11);
				String SpUserName = rs.getString(12);
				String role = rs.getString(13);
				String department = rs.getString(14);
				String status = rs.getString(15);
				String userOfficeCode = rs.getString(16);
				String priority = rs.getString(17);
				String severity = rs.getString(18);
				String customerGroupName = rs.getString(19);
				String callClassification = rs.getString(20);
				String spCallClassification = rs.getString(21);
				NewlyOpenedTicketsBean bean = new NewlyOpenedTicketsBean(ticketNO, assignedOn, ticketLogDate, problemCategory, problemType, problemItem, problemSummary, problemDescription, ticketLoggedUserID, ticketLoggedUsername, SpUserId, SpUserName, role, department, status, userOfficeCode, priority, severity, customerGroupName, callClassification, spCallClassification);
				fetchList.add(bean);
			}
			return fetchList;
			} catch (SQLException | IOException e) {
				logger.error("ErroMessage:sql or IO exception in NewlyOpenedHO method in dao class " +"Exception:"+e.getStackTrace());
			}
			finally {
				DatabaseUtil.closePreparedStatement(psmt);
				DatabaseUtil.closeStatement(smt);
				DatabaseUtil.closeResultSet(rs);
				DatabaseUtil.closeConnection(con);	
			}
			return fetchList;	
		}

	public  static ArrayList<Reopen10To3pmBean> getReopen10To3pmDao() {
		propertiesDao.dbproperties();
		propertiesDao.config();
		ArrayList<Reopen10To3pmBean> fetchList = new ArrayList<Reopen10To3pmBean>(); 
		try {
			con = DatabaseUtil.getConnection();
			/*String query = prop.getProperty("Reopen10To3pmQuery");*/
			/*//System.out.println("froooom: - " +dateBean.getUserEnteredFromdate());
			//System.out.println("Toooooo: - " +dateBean.getUserEnteredTodate());
			//System.out.println("query is : "+propertiesBean.getReopen10To3pmQuery());*/
			psmt = con.prepareStatement(propertiesBean.getReopen10To3pmQuery());
			psmt.setString(1, dateBean.getUserEnteredFromdate());
			psmt.setString(2, dateBean.getUserEnteredTodate());
			psmt.setString(3, dateBean.getUserEnteredFromdate());
			psmt.setString(4, dateBean.getUserEnteredTodate());
			rs = psmt.executeQuery();
			////System.out.println(psmt);
			////System.out.println(rs);
		while(rs.next()){
			 String ticketNo = rs.getString(1);
			 String problemSummary = rs.getString(2);
			 Reopen10To3pmBean bean = new Reopen10To3pmBean(ticketNo, problemSummary);
			fetchList.add(bean);
		}
			return fetchList;
		} catch (SQLException | IOException e) {
			logger.error("ErroMessage:sql or IO exception in getReopen10To3pmDao method in dao class " +"Exception:"+e.getStackTrace());
			return fetchList;
		}
		finally {
			DatabaseUtil.closePreparedStatement(psmt);
			DatabaseUtil.closeStatement(smt);
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeConnection(con); 
			}
	}	

	public  static ArrayList<Reopen3To6pmBean> getReopen3To6pmDao() {
		propertiesDao.dbproperties();
		propertiesDao.config();
		ArrayList<Reopen3To6pmBean> fetchList = new ArrayList<Reopen3To6pmBean>(); 
		try {
			con = DatabaseUtil.getConnection();
			/*//System.out.println("froooom: - " +dateBean.getUserEnteredFromdate());
			//System.out.println("Toooooo: - " +dateBean.getUserEnteredTodate());
			//System.out.println("query is : "+propertiesBean.getNewlyOpenedHO());*/
			psmt = con.prepareStatement(propertiesBean.getReopen3To6pmQuery());
			psmt.setString(1, dateBean.getUserEnteredFromdate());
			psmt.setString(2, dateBean.getUserEnteredTodate());
			psmt.setString(3, dateBean.getUserEnteredFromdate());
			psmt.setString(4, dateBean.getUserEnteredTodate());
			/*//System.out.println(psmt);*/
			rs = psmt.executeQuery();
		while(rs.next()){
			 String ticketNo = rs.getString(1);
			 String problemSummary = rs.getString(2);
			 Reopen3To6pmBean bean = new Reopen3To6pmBean(ticketNo, problemSummary);
			fetchList.add(bean);
		}
			return fetchList;
		} catch (SQLException | IOException e) {
			logger.error("ErroMessage:sql or IO exception in getReopen3To6pmDao method in dao class " +"Exception:"+e.getStackTrace());
			return fetchList;
		}
		finally {
			DatabaseUtil.closePreparedStatement(psmt);
			DatabaseUtil.closeStatement(smt);
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeConnection(con);
		}
	}

	public  static ArrayList<ReopenedBean> getReopenedDao() {
		propertiesDao.dbproperties();
		propertiesDao.config();
		ArrayList<ReopenedBean> fetchList = new ArrayList<ReopenedBean>(); 
		
		/*String query = prop.getProperty("ReopenedQuery");*/
		try {
			con = DatabaseUtil.getConnection();
			/*//System.out.println("froooom: - " +dateBean.getUserEnteredFromdate());
			//System.out.println("Toooooo: - " +dateBean.getUserEnteredTodate());
			//System.out.println("query is : "+propertiesBean.getReopenedTickets());*/
				psmt = con.prepareStatement(propertiesBean.getReopenedTickets());
				psmt.setString(1, dateBean.getUserEnteredFromdate());
				psmt.setString(2, dateBean.getUserEnteredTodate());
				psmt.setString(3, dateBean.getUserEnteredFromdate());
				psmt.setString(4, dateBean.getUserEnteredTodate());
				//System.out.println(psmt);
				rs = psmt.executeQuery();
			while(rs.next()){
				String ticketNO = rs.getString(1);
				Date assignedOn = rs.getDate(2);
				Date ticketLogDate = rs.getDate(3);
				String problemCategory = rs.getString(4);
				String problemType = rs.getString(5);
				String problemItem = rs.getString(6);
				String problemSummary = rs.getString(7);
				String problemDescription = rs.getString(8);
				String ticketLoggedUserID = rs.getString(9);
				String ticketLoggedUsername = rs.getString(10);
				String SpUserId = rs.getString(11);
				String SpUserName = rs.getString(12);
				String role = rs.getString(13);
				String department = rs.getString(14);
				String status = rs.getString(15);
				String userOfficeCode = rs.getString(16);
				String priority = rs.getString(17);
				String severity = rs.getString(18);
				String customerGroupName = rs.getString(19);
				String callClassification = rs.getString(20);
				String spCallClassification = rs.getString(21);
				String numberOfTimeReopen = rs.getString(22);
				ReopenedBean bean = new ReopenedBean(ticketNO, assignedOn, ticketLogDate, problemCategory, problemType, problemItem, problemSummary, problemDescription, ticketLoggedUserID, ticketLoggedUsername, SpUserId, SpUserName, role, department, status, userOfficeCode, priority, severity, customerGroupName, callClassification, spCallClassification, numberOfTimeReopen);
				fetchList.add(bean);
		}
		 return fetchList;
		} catch (SQLException | IOException e) {
			logger.error("ErroMessage:sql or IO exception in getReopenedDao method in dao class " +"Exception:"+e.getStackTrace());
		}
		finally {
			DatabaseUtil.closePreparedStatement(psmt);
			DatabaseUtil.closeStatement(smt);
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeConnection(con);
		}
		return fetchList;	
	}

	public  static ArrayList<ResolvedByHO_OFWeeklyBean> getResolvedByHO_OFWeeklyDao(){
		propertiesDao.dbproperties();
		propertiesDao.config();
		ArrayList<ResolvedByHO_OFWeeklyBean> fetchList = new ArrayList<ResolvedByHO_OFWeeklyBean>(); 
		
		/*String query = prop.getProperty("ResolvedByHO_OFWeekly");*/
		try {
			con = DatabaseUtil.getConnection();
			/*//System.out.println("froooom: - " +dateBean.getUserEnteredFromdate());
			//System.out.println("Toooooo: - " +dateBean.getUserEnteredTodate());
			//System.out.println("query is : "+propertiesBean.getResolvedByHO_OFWeekly());*/
				psmt = con.prepareStatement(propertiesBean.getResolvedByHO_OFWeekly());
				psmt.setString(1, dateBean.getUserEnteredFromdate());
				psmt.setString(2, dateBean.getUserEnteredTodate());
				psmt.setString(3, dateBean.getUserEnteredFromdate());
				psmt.setString(4, dateBean.getUserEnteredTodate());
				////System.out.println(psmt);
				rs = psmt.executeQuery();
		while(rs.next()){
			String ticketNO = rs.getString(1);
			Date solvedDate = rs.getDate(2);
			Date assignedOn = rs.getDate(3);
			Date ticketLogDate = rs.getDate(4);
			Date closedDate = rs.getDate(5);
			String problemCategory = rs.getString(6);
			String problemType = rs.getString(7);
			String problemItem = rs.getString(8);
			String problemSummary = rs.getString(9);
			String problemDescription = rs.getString(10);
			String ticketLoggedUserID = rs.getString(11);
			String ticketLoggedUsername = rs.getString(12);
			String SpUserId = rs.getString(13);
			String SpUserName = rs.getString(14);
			String role = rs.getString(15);
			String department = rs.getString(16);
			String status = rs.getString(17);
			String userOfficeCode = rs.getString(18);
			String priority = rs.getString(19);
			String severity = rs.getString(20);
			String customerGroupName = rs.getString(21);
			String callClassification = rs.getString(22);
			String spCallClassification = rs.getString(23);
			ResolvedByHO_OFWeeklyBean bean = new ResolvedByHO_OFWeeklyBean(ticketNO, solvedDate, assignedOn, ticketLogDate, closedDate, problemCategory, problemType, problemItem, problemSummary, problemDescription, ticketLoggedUserID, ticketLoggedUsername, SpUserId, SpUserName, role, department, status, userOfficeCode, priority, severity, customerGroupName, callClassification, spCallClassification);
			fetchList.add(bean);
		}
		
		return fetchList;
		} catch (SQLException | IOException e) {
			logger.error("ErroMessage:sql or IO exception in getResolvedByHO_OFWeeklyDao method in dao class " +"Exception:"+e.getStackTrace());
		}
		finally {
			DatabaseUtil.closePreparedStatement(psmt);
			DatabaseUtil.closeStatement(smt);
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeConnection(con); 
		}
		return fetchList;	
	}

	public  static ArrayList<ResolvedByHoBean> getResolvedByHoDao(){
		propertiesDao.dbproperties();
		propertiesDao.config();
		ArrayList<ResolvedByHoBean> fetchList = new ArrayList<ResolvedByHoBean>(); 
		try {	
		con = DatabaseUtil.getConnection();
		/*String query = prop.getProperty("ResolvedByHoQuery");*/
				
			/*//System.out.println("froooom: - " +dateBean.getUserEnteredFromdate());
			//System.out.println("Toooooo: - " +dateBean.getUserEnteredTodate());
			//System.out.println("query is : "+propertiesBean.getResolvedByHo());*/
			psmt = con.prepareStatement(propertiesBean.getResolvedByHo());
			psmt.setString(1, dateBean.getUserEnteredFromdate());
			psmt.setString(2, dateBean.getUserEnteredTodate());
			psmt.setString(3, dateBean.getUserEnteredFromdate());
			psmt.setString(4, dateBean.getUserEnteredTodate());
			//System.out.println(psmt);
			rs = psmt.executeQuery();
		while(rs.next()){
			String ticketNO = rs.getString(1);
			Date assignedOn = rs.getDate(2);
			Date ticketLogDate = rs.getDate(3);
			String problemCategory = rs.getString(4);
			String problemType = rs.getString(5);
			String problemItem = rs.getString(6);
			String problemSummary = rs.getString(7);
			String problemDescription = rs.getString(8);
			String ticketLoggedUserID = rs.getString(9);
			String ticketLoggedUsername = rs.getString(10);
			String SpUserId = rs.getString(11);
			String SpUserName = rs.getString(12);
			String role = rs.getString(13);
			String department = rs.getString(14);
			String status = rs.getString(15);
			String userOfficeCode = rs.getString(16);
			String priority = rs.getString(17);
			String severity = rs.getString(18);
			String customerGroupName = rs.getString(19);
			String callClassification = rs.getString(20);
			String spCallClassification = rs.getString(21);
			ResolvedByHoBean bean = new ResolvedByHoBean(ticketNO, assignedOn, ticketLogDate, problemCategory, problemType, problemItem, problemSummary, problemDescription, ticketLoggedUserID, ticketLoggedUsername, SpUserId, SpUserName, role, department, status, userOfficeCode, priority, severity, customerGroupName, callClassification, spCallClassification);
			fetchList.add(bean);
		}
			return fetchList;
		} catch (SQLException | IOException e) {
			logger.error("ErroMessage:sql or IO exception in getResolvedByHoDao method in dao class " +"Exception:"+e.getStackTrace());
		}
		finally {
			DatabaseUtil.closePreparedStatement(psmt);
			DatabaseUtil.closeStatement(smt);
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeConnection(con); 
			}
		return fetchList;	
	}

	public  static ArrayList<ResolvedbyTCS_OFWeeklyBean> getResolvedbyTCS_OFWeeklyDao() {
		propertiesDao.dbproperties();
		propertiesDao.config();
		ArrayList<ResolvedbyTCS_OFWeeklyBean> fetchList = new ArrayList<ResolvedbyTCS_OFWeeklyBean>(); 
		
		/*String query = prop.getProperty("ResolvedByTCS_OFWeekly");*/
		try{
			con = DatabaseUtil.getConnection();
			/*//System.out.println("froooom: - " +dateBean.getUserEnteredFromdate());
			//System.out.println("Toooooo: - " +dateBean.getUserEnteredTodate());
			//System.out.println("query is : "+propertiesBean.getResolvedByTCS_OFWeekly());*/
			psmt = con.prepareStatement(propertiesBean.getResolvedByTCS_OFWeekly());
			psmt.setString(1, dateBean.getUserEnteredFromdate());
			psmt.setString(2, dateBean.getUserEnteredTodate());
			psmt.setString(3, dateBean.getUserEnteredFromdate());
			psmt.setString(4, dateBean.getUserEnteredTodate());
			/*//System.out.println(psmt);*/
			rs = psmt.executeQuery();
		while(rs.next()){
			String ticketNO = rs.getString(1);
			Date solvedDate = rs.getDate(2);
			Date assignedOn = rs.getDate(3);
			Date ticketLogDate = rs.getDate(4);
			Date closedDate = rs.getDate(5);
			String problemCategory = rs.getString(6);
			String problemType = rs.getString(7);
			String problemItem = rs.getString(8);
			String problemSummary = rs.getString(9);
			String problemDescription = rs.getString(10);
			String ticketLoggedUserID = rs.getString(11);
			String ticketLoggedUsername = rs.getString(12);
			String SpUserId = rs.getString(13);
			String SpUserName = rs.getString(14);
			String role = rs.getString(15);
			String department = rs.getString(16);
			String status = rs.getString(17);
			String userOfficeCode = rs.getString(18);
			String priority = rs.getString(19);
			String severity = rs.getString(20);
			String customerGroupName = rs.getString(21);
			String callClassification = rs.getString(22);
			String spCallClassification = rs.getString(23);
			ResolvedbyTCS_OFWeeklyBean bean = new ResolvedbyTCS_OFWeeklyBean(ticketNO, solvedDate, assignedOn, ticketLogDate, closedDate, problemCategory, problemType, problemItem, problemSummary, problemDescription, ticketLoggedUserID, ticketLoggedUsername, SpUserId, SpUserName, role, department, status, userOfficeCode, priority, severity, customerGroupName, callClassification, spCallClassification);
			fetchList.add(bean);
		}
		 	return fetchList;
		} catch (SQLException | IOException e) {
			logger.error("ErroMessage:sql or IO exception in getResolvedbyTCS_OFWeeklyDao method in dao class " +"Exception:"+e.getStackTrace());
		}
		finally {
			DatabaseUtil.closePreparedStatement(psmt);
			DatabaseUtil.closeStatement(smt);
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeConnection(con);
		}
		return fetchList;	
	}

	public  ArrayList<ResolvedByTicketsBean> getResolvedByTicketsDao(){
		propertiesDao.dbproperties();
		propertiesDao.config();
		ArrayList<ResolvedByTicketsBean> fetchList = new ArrayList<ResolvedByTicketsBean>(); 
		
		/*String query = prop.getProperty("ResolvedByTicketsQuery");*/
		try{
			con = DatabaseUtil.getConnection();
			/*//System.out.println("froooom: - " +dateBean.getUserEnteredFromdate());
			//System.out.println("Toooooo: - " +dateBean.getUserEnteredTodate());
			//System.out.println("query is : "+propertiesBean.getResolvedByTickets());*/
			psmt = con.prepareStatement(propertiesBean.getResolvedByTickets());
			psmt.setString(1, dateBean.getUserEnteredFromdate());
			psmt.setString(2, dateBean.getUserEnteredTodate());
			psmt.setString(3, dateBean.getUserEnteredFromdate());
			psmt.setString(4, dateBean.getUserEnteredTodate());
			/*//System.out.println(psmt);*/
			rs = psmt.executeQuery();
		while(rs.next()){
			String ticketNO = rs.getString(1);
			Date SolvedDate = rs.getDate(2);
			Date ticketLogDate = rs.getDate(3);
			String problemCategory = rs.getString(4);
			String problemType = rs.getString(5);
			String problemItem = rs.getString(6);
			String problemSummary = rs.getString(7);
			String problemDescription = rs.getString(8);
			String ticketLoggedUserID = rs.getString(9);
			String ticketLoggedUsername = rs.getString(10);
			String SpUserId = rs.getString(11);
			String SpUserName = rs.getString(12);
			String role = rs.getString(13);
			String department = rs.getString(14);
			String status = rs.getString(15);
			String userOfficeCode = rs.getString(16);
			String priority = rs.getString(17);
			String severity = rs.getString(18);
			String customerGroupName = rs.getString(19);
			String callClassification = rs.getString(20);
			String spCallClassification = rs.getString(21);
			ResolvedByTicketsBean bean = new ResolvedByTicketsBean(ticketNO, SolvedDate, ticketLogDate, problemCategory, problemType, problemItem, problemSummary, problemDescription, ticketLoggedUserID, ticketLoggedUsername, SpUserId, SpUserName, role, department, status, userOfficeCode, priority, severity, customerGroupName, callClassification, spCallClassification);
			fetchList.add(bean);
		}
			return fetchList;
		} catch (SQLException | IOException e) {
			logger.error("ErroMessage:sql or IO exception in getResolvedByTicketsDao method in dao class " +"Exception:"+e.getStackTrace());
		}
		finally {
			DatabaseUtil.closePreparedStatement(psmt);
			DatabaseUtil.closeStatement(smt);
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeConnection(con); 
		}
		return fetchList;	
	}

	public   ArrayList<ReturnAfterClarificationBean> getReturnAfterClarificationDao()  {
			propertiesDao.dbproperties();
			propertiesDao.config();
			ArrayList<ReturnAfterClarificationBean> fetchList = new ArrayList<ReturnAfterClarificationBean>(); 
			try {
				con = DatabaseUtil.getConnection();
				/*String query = prop.getProperty("ReturnAfterClarificationQuery");
				//System.out.println("from Date: - " +dateBean.getUserEnteredFromdate());
				//System.out.println("To  Date: - " +dateBean.getUserEnteredTodate());
				//System.out.println("query is : "+propertiesBean.getReturnAfterClarification());*/
				psmt = con.prepareStatement(propertiesBean.getReturnAfterClarification());
				psmt.setString(1, dateBean.getUserEnteredFromdate());
				psmt.setString(2, dateBean.getUserEnteredTodate());
				psmt.setString(3, dateBean.getUserEnteredFromdate());
				psmt.setString(4, dateBean.getUserEnteredTodate());
				////System.out.println(psmt);
				rs = psmt.executeQuery();
			while(rs.next()){
				String ticketNO = rs.getString(1);
				Date assignedOn = rs.getDate(2);
				Date ticketLoggedDate = rs.getDate(3);
				String problemCategory = rs.getString(4);
				String problemType = rs.getString(5);
				String problemItem = rs.getString(6);
				String problemSummary = rs.getString(7);
				String problemDescription = rs.getString(8);
				String ticketLoggedUserID = rs.getString(9);
				String ticketLoggedUsername = rs.getString(10);
				String SpUserId = rs.getString(11);
				String SpUserName = rs.getString(12);
				String role = rs.getString(13);
				String department = rs.getString(14);
				String status = rs.getString(15);
				String userOfficeCode = rs.getString(16);
				String priority = rs.getString(17);
				String severity = rs.getString(18);
				String customerGroupName = rs.getString(19);
				String callClassification = rs.getString(20);
				String spCallClassification = rs.getString(21);
				ReturnAfterClarificationBean bean = new ReturnAfterClarificationBean(ticketNO, assignedOn, ticketLoggedDate, problemCategory, problemType, problemItem, problemSummary, problemDescription, ticketLoggedUserID, ticketLoggedUsername, SpUserId, SpUserName, role, department, status, userOfficeCode, priority, severity, customerGroupName, callClassification, spCallClassification);
			fetchList.add(bean);
		}
			return fetchList;
		} catch (SQLException | IOException e) {
			logger.error("ErroMessage:sql or IO exception in getReturnAfterClarificationDao method in dao class " +"Exception:"+e.getStackTrace());
			return fetchList;
		}
		finally {
			DatabaseUtil.closePreparedStatement(psmt);
			DatabaseUtil.closeStatement(smt);
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeConnection(con);
		}
	}

	public  static ArrayList<SentForClarificationBean> getSentForClarificationDao() {
		propertiesDao.dbproperties();
		propertiesDao.config();
		ArrayList<SentForClarificationBean> fetchList = new ArrayList<SentForClarificationBean>(); 
		try {
		con = DatabaseUtil.getConnection();
		/*String query = prop.getProperty("SentForClarificationQuery");*/
			/*//System.out.println("from Date: - " +dateBean.getUserEnteredFromdate());
			//System.out.println("To Date: - " +dateBean.getUserEnteredTodate());
			//System.out.println("query is : "+propertiesBean.getSentForClarification());*/
			psmt = con.prepareStatement(propertiesBean.getSentForClarification());
			psmt.setString(1, dateBean.getUserEnteredFromdate());
			psmt.setString(2, dateBean.getUserEnteredTodate());
			
			psmt.setString(3, dateBean.getUserEnteredFromdate());
			psmt.setString(4, dateBean.getUserEnteredTodate());
			
			psmt.setString(5, dateBean.getUserEnteredFromdate());
			psmt.setString(6, dateBean.getUserEnteredTodate());
			
			psmt.setString(7, dateBean.getUserEnteredFromdate());
			psmt.setString(8, dateBean.getUserEnteredTodate());
			
			psmt.setString(9, dateBean.getUserEnteredFromdate());
			psmt.setString(10, dateBean.getUserEnteredTodate());
			
			psmt.setString(11, dateBean.getUserEnteredFromdate());
			psmt.setString(12, dateBean.getUserEnteredTodate());
			
			/*//System.out.println(psmt.toString());*/
			rs = psmt.executeQuery();
		while(rs.next()){
			String ticketNO = rs.getString(1);
			Date assignOn = rs.getDate(2);
			Date ticketLoggedDate = rs.getDate(3);
			String problemCategory = rs.getString(4);
			String problemType = rs.getString(5);
			String problemItem = rs.getString(6);
			String problemSummary = rs.getString(7);
			String problemDescription = rs.getString(8);
			String ticketLoggedUserID = rs.getString(9);
			String ticketLoggedUsername = rs.getString(10);
			String SpUserId = rs.getString(11);
			String SpUserName = rs.getString(12);
			String role = rs.getString(13);
			String department = rs.getString(14);
			String status = rs.getString(15);
			String userOfficeCode = rs.getString(16);
			String priority = rs.getString(17);
			String severity = rs.getString(18);
			String customerGroupName = rs.getString(19);
			String callClassification = rs.getString(20);
			String spCallClassification = rs.getString(21);
			
			SentForClarificationBean bean = new SentForClarificationBean(ticketNO, assignOn, ticketLoggedDate, problemCategory, problemType, problemItem, problemSummary, problemDescription, ticketLoggedUserID, ticketLoggedUsername, SpUserId, SpUserName, role, department, status, userOfficeCode, priority, severity, customerGroupName, callClassification, spCallClassification);
			fetchList.add(bean);
		}
		 
		return fetchList;
		} catch (SQLException | IOException e) {
			logger.error("ErroMessage:sql or IO exception in getSentForClarificationDao method in dao class " +"Exception:"+e.getStackTrace());
			return fetchList;
		}
		finally {
			DatabaseUtil.closePreparedStatement(psmt);
			DatabaseUtil.closeStatement(smt);
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeConnection(con);
		}
	}

	public  static ArrayList<SentForClarificationHO_NEW_OFWeeklyBean> getSentForClarificationHO_NEW_OFWeeklyDao() {
		propertiesDao.dbproperties();
		propertiesDao.config();
		ArrayList<SentForClarificationHO_NEW_OFWeeklyBean> fetchList = new ArrayList<SentForClarificationHO_NEW_OFWeeklyBean>(); 
		try {
			con = DatabaseUtil.getConnection();
			/*String query = prop.getProperty("SentForClarificationHO_NEW_OFWeekly");*/
			/*//System.out.println("froooom: - " +dateBean.getUserEnteredFromdate());
			//System.out.println("Toooooo: - " +dateBean.getUserEnteredTodate());
			//System.out.println("query is : "+propertiesBean.getSentForClarificationHO_NEW_OFWeekly());*/
			psmt = con.prepareStatement(propertiesBean.getSentForClarificationHO_NEW_OFWeekly());
			
			psmt.setString(1, dateBean.getUserEnteredFromdate());
			psmt.setString(2, dateBean.getUserEnteredTodate());
			
			psmt.setString(3, dateBean.getUserEnteredFromdate());
			psmt.setString(4, dateBean.getUserEnteredTodate());
			
			psmt.setString(5, dateBean.getUserEnteredFromdate());
			psmt.setString(6, dateBean.getUserEnteredTodate());
			
			psmt.setString(7, dateBean.getUserEnteredFromdate());
			psmt.setString(8, dateBean.getUserEnteredTodate());
			
			psmt.setString(9, dateBean.getUserEnteredFromdate());
			psmt.setString(10, dateBean.getUserEnteredTodate());
			
			psmt.setString(11, dateBean.getUserEnteredFromdate());
			psmt.setString(12, dateBean.getUserEnteredTodate());
			
			//System.out.println(psmt);
			rs = psmt.executeQuery();
		while(rs.next()){
			String ticketNO = rs.getString(1);
			Date assignOn = rs.getDate(2);
			Date ticketLoggedDate = rs.getDate(3);
			String problemCategory = rs.getString(4);
			String problemType = rs.getString(5);
			String problemItem = rs.getString(6);
			String problemSummary = rs.getString(7);
			String problemDescription = rs.getString(8);
			String ticketLoggedUserID = rs.getString(9);
			String ticketLoggedUsername = rs.getString(10);
			String SpUserId = rs.getString(11);
			String SpUserName = rs.getString(12);
			String role = rs.getString(13);
			String department = rs.getString(14);
			String status = rs.getString(15);
			String userOfficeCode = rs.getString(16);
			String priority = rs.getString(17);
			String severity = rs.getString(18);
			String customerGroupName = rs.getString(19);
			String callClassification = rs.getString(20);
			String spCallClassification = rs.getString(21);
			SentForClarificationHO_NEW_OFWeeklyBean bean = new SentForClarificationHO_NEW_OFWeeklyBean(ticketNO, assignOn, ticketLoggedDate, problemCategory, problemType, problemItem, problemSummary, problemDescription, ticketLoggedUserID, ticketLoggedUsername, SpUserId, SpUserName, role, department, status, userOfficeCode, priority, severity, customerGroupName, callClassification, spCallClassification);
			fetchList.add(bean);
		}
		 
		return fetchList;
		} catch (SQLException | IOException e) {
			logger.error("ErroMessage:sql or IO exception in getSentForClarificationHO_NEW_OFWeeklyDao method in dao class " +"Exception:"+e.getStackTrace());
			return fetchList;
		}
		finally {
			DatabaseUtil.closePreparedStatement(psmt);
			DatabaseUtil.closeStatement(smt);
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeConnection(con);
		}
	}

	public  static ArrayList<SentForClarificationTCS_NEW_OFWeeklyBean> getSentForClarificationTCS_NEW_OFWeeklyDao() {
		propertiesDao.dbproperties();
		propertiesDao.config();
		ArrayList<SentForClarificationTCS_NEW_OFWeeklyBean> fetchList = new ArrayList<SentForClarificationTCS_NEW_OFWeeklyBean>(); 
		try {
			con = DatabaseUtil.getConnection();
			/*String query = prop.getProperty("SentForClarificationTCS_NEW_OFWeekly");
			//System.out.println("froooom: - " +dateBean.getUserEnteredFromdate());
			//System.out.println("Toooooo: - " +dateBean.getUserEnteredTodate());
			//System.out.println("query is : "+propertiesBean.getSentForClarificationTCS_NEW_OFWeekly());*/
			
			psmt = con.prepareStatement(propertiesBean.getSentForClarificationTCS_NEW_OFWeekly());
			psmt.setString(1, dateBean.getUserEnteredFromdate());
			psmt.setString(2, dateBean.getUserEnteredTodate());
			
			psmt.setString(3, dateBean.getUserEnteredFromdate());
			psmt.setString(4, dateBean.getUserEnteredTodate());
			
			psmt.setString(5, dateBean.getUserEnteredFromdate());
			psmt.setString(6, dateBean.getUserEnteredTodate());
			
			psmt.setString(7, dateBean.getUserEnteredFromdate());
			psmt.setString(8, dateBean.getUserEnteredTodate());
			
			psmt.setString(9, dateBean.getUserEnteredFromdate());
			psmt.setString(10, dateBean.getUserEnteredTodate());
			
			psmt.setString(11, dateBean.getUserEnteredFromdate());
			psmt.setString(12, dateBean.getUserEnteredTodate());
			
			/*//System.out.println(psmt);*/
			rs = psmt.executeQuery();
		while(rs.next()){
			
			String ticketNO = rs.getString(1);
			Date assignOn = rs.getDate(2);
			Date ticketLoggedDate = rs.getDate(3);
			String problemCategory = rs.getString(4);
			String problemType = rs.getString(5);
			String problemItem = rs.getString(6);
			String problemSummary = rs.getString(7);
			String problemDescription = rs.getString(8);
			String ticketLoggedUserID = rs.getString(9);
			String ticketLoggedUsername = rs.getString(10);
			String SpUserId = rs.getString(11);
			String SpUserName = rs.getString(12);
			String role = rs.getString(13);
			String department = rs.getString(14);
			String status = rs.getString(15);
			String userOfficeCode = rs.getString(16);
			String priority = rs.getString(17);
			String severity = rs.getString(18);
			String customerGroupName = rs.getString(19);
			String callClassification = rs.getString(20);
			String spCallClassification = rs.getString(21);
			
			SentForClarificationTCS_NEW_OFWeeklyBean bean = new SentForClarificationTCS_NEW_OFWeeklyBean(ticketNO, assignOn, ticketLoggedDate, problemCategory, problemType, problemItem, problemSummary, problemDescription, ticketLoggedUserID, ticketLoggedUsername, SpUserId, SpUserName, role, department, status, userOfficeCode, priority, severity, customerGroupName, callClassification, spCallClassification);
			fetchList.add(bean);
		}
		return fetchList;
		} catch (SQLException | IOException e) {
			logger.error("ErroMessage:sql or IO exception in getSentForClarificationTCS_NEW_OFWeeklyDao method in dao class " +"Exception:"+e.getStackTrace());
			return fetchList;
		}
		finally {
			DatabaseUtil.closePreparedStatement(psmt);
			DatabaseUtil.closeStatement(smt);
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeConnection(con); 
		}
	}
	
	public static ArrayList<updated_3pm_OF_3To6Bean> getupdated_3pm_OF_3To6Dao() {
		propertiesDao.dbproperties();
		propertiesDao.config();
		ArrayList<updated_3pm_OF_3To6Bean> fetchList = new ArrayList<updated_3pm_OF_3To6Bean>(); 
		try {
			con = DatabaseUtil.getConnection();
			/*String query = prop.getProperty("updated_3pm_OF_3To6");*/
			/*//System.out.println("froooom: - " +dateBean.getUserEnteredFromdate());
			//System.out.println("Toooooo: - " +dateBean.getUserEnteredTodate());
			//System.out.println("query is : "+propertiesBean.getUpdated_3pm_OF_3To6());*/
			
			psmt = con.prepareStatement(propertiesBean.getUpdated_3pm_OF_3To6());
			psmt.setString(1, dateBean.getUserEnteredFromdate());
			psmt.setString(2, dateBean.getUserEnteredTodate());
			
			/*//System.out.println(psmt);*/
			rs = psmt.executeQuery();
		while(rs.next()){
			 String ticketNo = rs.getString(1);
			 String problemSummary = rs.getString(2);
			 updated_3pm_OF_3To6Bean bean = new updated_3pm_OF_3To6Bean(ticketNo, problemSummary);
			fetchList.add(bean);
		}
		return fetchList;
		} catch (SQLException | IOException e) {
			logger.error("ErroMessage:sql or IO exception in getupdated_3pm_OF_3To6Dao method in dao class " +"Exception:"+e.getStackTrace());
			return fetchList;
		}
		finally {
			DatabaseUtil.closePreparedStatement(psmt);
			DatabaseUtil.closeStatement(smt);
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeConnection(con);
		}
	}
	
	public static ArrayList<updated_6pm_OF_3To6Bean> getupdated_6pm_OF_3To6Dao() {
		propertiesDao.dbproperties();
		propertiesDao.config();
		ArrayList<updated_6pm_OF_3To6Bean> fetchList = new ArrayList<updated_6pm_OF_3To6Bean>(); 
		try {
			con = DatabaseUtil.getConnection();
			/*String query = prop.getProperty("updated_6pm_OF_3To6");*/
			/*//System.out.println("froooom: - " +dateBean.getUserEnteredFromdate());
			//System.out.println("Toooooo: - " +dateBean.getUserEnteredTodate());
			//System.out.println("query is : "+propertiesBean.getUpdated_6pm_OF_3To6());*/
			
			psmt = con.prepareStatement(propertiesBean.getUpdated_6pm_OF_3To6());
			psmt.setString(1, dateBean.getUserEnteredFromdate());
			psmt.setString(2, dateBean.getUserEnteredTodate());
			
			/*//System.out.println(psmt);*/
			rs = psmt.executeQuery();
		while(rs.next()){
			 String ticketNo = rs.getString(1);
			 String problemSummary = rs.getString(2);
			 updated_6pm_OF_3To6Bean bean = new updated_6pm_OF_3To6Bean(ticketNo, problemSummary);
			fetchList.add(bean);
		}
		
		return fetchList;
		} catch (SQLException | IOException e) {
			logger.error("ErroMessage:sql or IO exception in getupdated_6pm_OF_3To6Dao method in dao class " +"Exception:"+e.getStackTrace());
			return fetchList;
		}	
		finally {
			DatabaseUtil.closePreparedStatement(psmt);
			DatabaseUtil.closeStatement(smt);
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeConnection(con); 
		}
	}
	
	public static ArrayList<updated10amQueryBean> getupdated10amQueryDao() {
		propertiesDao.dbproperties();
		propertiesDao.config();
		ArrayList<updated10amQueryBean> fetchList = new ArrayList<updated10amQueryBean>(); 
		try {
			con = DatabaseUtil.getConnection();
			/*String query = prop.getProperty("updated10amQuery");*/
			/*//System.out.println("froooom: - " +dateBean.getUserEnteredFromdate());
			//System.out.println("Toooooo: - " +dateBean.getUserEnteredTodate());
			//System.out.println("query is : "+propertiesBean.getUpdated10amQuery());*/
			psmt = con.prepareStatement(propertiesBean.getUpdated10amQuery());
			psmt.setString(1, dateBean.getUserEnteredFromdate());
			psmt.setString(2, dateBean.getUserEnteredTodate());
			//System.out.println(psmt);
			rs = psmt.executeQuery();
		while(rs.next()){
			 String ticketNo = rs.getString(1);
			 String problemSummary = rs.getString(2);
			 updated10amQueryBean bean = new updated10amQueryBean(ticketNo, problemSummary);
			fetchList.add(bean);
		}
		return fetchList;
		} catch (SQLException | IOException e) {
			logger.error("ErroMessage:sql or IO exception in getupdated10amQueryDao method in dao class " +"Exception:"+e.getStackTrace());
			return fetchList;
		}
		finally {
			DatabaseUtil.closePreparedStatement(psmt);
			DatabaseUtil.closeStatement(smt);
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeConnection(con);
		}
	}

	public static ArrayList<updated3pmQueryBean> getupdated3pmQueryDao() {
		propertiesDao.dbproperties();
		propertiesDao.config();
		ArrayList<updated3pmQueryBean> fetchList = new ArrayList<updated3pmQueryBean>(); 
		try {
			con = DatabaseUtil.getConnection();
			/*String query = prop.getProperty("updated3pmQuery");*/
			/*//System.out.println("froooom: - " +dateBean.getUserEnteredFromdate());
			//System.out.println("Toooooo: - " +dateBean.getUserEnteredTodate());
			//System.out.println("query is : "+propertiesBean.getUpdated3pmQuery());*/
			psmt = con.prepareStatement(propertiesBean.getUpdated3pmQuery());
			psmt.setString(1, dateBean.getUserEnteredFromdate());
			psmt.setString(2, dateBean.getUserEnteredTodate());
			//System.out.println(psmt);
			rs = psmt.executeQuery();
		while(rs.next()){
			 String ticketNo = rs.getString(1);
			 String problemSummary = rs.getString(2);
			 updated3pmQueryBean bean = new updated3pmQueryBean(ticketNo, problemSummary);
			fetchList.add(bean);
		}
		
		return fetchList;
		} catch (SQLException | IOException e) {
			logger.error("ErroMessage:sql or IO exception in getupdated3pmQueryDao method in dao class " +"Exception:"+e.getStackTrace());
			return fetchList;
		}
		finally {
			DatabaseUtil.closePreparedStatement(psmt);
			DatabaseUtil.closeStatement(smt);
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeConnection(con);
		}
		
	}
	
	
	
	
}
