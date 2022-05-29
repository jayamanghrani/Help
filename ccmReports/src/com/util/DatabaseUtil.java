package com.util;


import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.bean.propertiesBean;

import com.dao.propertiesDao;

public class DatabaseUtil{
	static org.apache.log4j.Logger logger = Logger.getLogger(propertiesDao.class);
	
	// get a database connection
	public static Connection getConnection() throws IOException {
		
		 
		propertiesDao.dbproperties();
		propertiesDao.config();
		Connection connection = null;
		/*InputStream input = null;
		String filename = "db.properties";
		ArrayList<H1QueryBean> fetchList = new ArrayList<H1QueryBean>(); 
		Properties prop = new Properties();
		
		input = H1QueryDao.class.getClassLoader().getResourceAsStream(filename);
		if(input == null){
			System.out.println("unable to find " +input);
		}
		prop.load(input);*/
		try {
			//Class.forName(prop.getProperty("DRIVERNAME"));
			Class.forName(propertiesBean.getDRIVERNAME());
			//connection = DriverManager.getConnection(prop.getProperty("URL"), prop.getProperty("USERNAME"), prop.getProperty("PASSWORD"));
			connection = DriverManager.getConnection(propertiesBean.getURL(), propertiesBean.getUSERNAME(), propertiesBean.getPASSWORD());
		} catch (ClassNotFoundException | SQLException e) {
			logger.error("ErroMessage:plese check propertiesBean class and properties file and databaseutil class  Status:failure" +"Exception:e.getStackTrace()");
			
			
		}
		/*System.out.println("connection establisted");*/
				return connection;
	}

	// close database connection
	public static void closeConnection(Connection con) {
		
		
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {

				logger.error("ErroMessage:plese check propertiesBean class and properties file and databaseutil class  Status:failure" +"Exception:e.getStackTrace()");
				
			} 
		}
	}

	// close statements
	public static void closeStatement(Statement smt) {

		if (smt != null) {
			try {
				smt.close();
			} catch (SQLException e) {

				System.out.println(e);
			} 
		}
	}
	
	public static void closePreparedStatement(PreparedStatement pst) {

		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {

				System.out.println(e);
			} 
		}
	}
	
	public static void closeResultSet(ResultSet rs) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {

				System.out.println(e);
			} 
		}
	}


}