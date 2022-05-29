/**
 * 
 */
package com.tcs.docstore.persist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.tcs.docstore.config.utils.UtilProperties;
import com.tcs.docstore.db.asbo.request.GetHRMSDetailsDBRequestASBO;
import com.tcs.docstore.db.asbo.response.GetHRMSDetailsDBResponseASBO;
import com.tcs.docstore.persist.query.Queries;

/**
 * @author 585226
 *
 */
public class GetHRMSDetailsDaoImpl implements GetHRMSDetailsDao {

	
	private static final Logger LOGGER = Logger.getLogger(GetHRMSDetailsDaoImpl.class);
	@Override
	public GetHRMSDetailsDBResponseASBO gethrmsdetailsList(
			GetHRMSDetailsDBRequestASBO gethrmsdetailsdbreqasbo) {
		GetHRMSDetailsDBResponseASBO gthrmsdbrespasbo = new GetHRMSDetailsDBResponseASBO();
		Connection conn = null ;
	//	Connection conn =DBUtil.getConnection("HRMS"); // uncomment to test on local
		PreparedStatement statement = null;
		ResultSet resultSet = null;
	
	// Uncoment while deploying on the server
		
		  Context ctx = null;
		  Hashtable ht = new Hashtable();
		  LOGGER.info("executing the new HRMS code which has the weblogic data source");
		/*  commented by omkar 27 oct 2016 ht.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		  ht.put(Context.PROVIDER_URL,PropertiesUtil.getConfigProperty("DataSourceUrl"));*/
		  try {
	/*	commented by omkar 27 oct 2016	ctx = new InitialContext(ht);*/
			ctx = new InitialContext();
			javax.sql.DataSource ds  = (javax.sql.DataSource) ctx.lookup (UtilProperties.getHRMSDSName());
			conn = ds.getConnection();
		} catch (NamingException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			LOGGER.error("ERROR in connecting to the data source-->"+e1.getStackTrace());
		}
		  

		try {
			
			LOGGER.debug("The connection is-->"+conn);
			statement = conn.prepareStatement(Queries.hrmsquery().toString());
			statement.setString(1, gethrmsdetailsdbreqasbo.getUserID());
			LOGGER.debug("The query is"+Queries.hrmsquery().toString());
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				LOGGER.info("rs is nt null");
				LOGGER.info("employeeID "+resultSet.getInt(1));
				LOGGER.info("Position number  "+resultSet.getInt(2));
				LOGGER.info("Position description "+resultSet.getString(3)+"");
				LOGGER.info("Department ID  "+resultSet.getInt(4));
				LOGGER.info("Department description is   "+resultSet.getString(5));
				LOGGER.debug("Name of the user is   "+resultSet.getString(6));
				gthrmsdbrespasbo.setUserID(resultSet.getInt(1)+"");
				gthrmsdbrespasbo.setDepartmentName(resultSet.getString(5));
				gthrmsdbrespasbo.setPosition(resultSet.getString(3));
				gthrmsdbrespasbo.setRoleOID(gethrmsdetailsdbreqasbo.getRoleOID());
				
			}
		}
		catch(Exception e){
			LOGGER.error("ERROR in executing the HRMS query"+e.getStackTrace());
			e.printStackTrace();
		}
		
		finally{
			try {
				
				LOGGER.info("closing all the data base connections");
				resultSet.close();
				statement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LOGGER.error("error in closing all the data base connections--->"+e.getStackTrace());
			}
		}
		// TODO Auto-generated method stub
		return gthrmsdbrespasbo;
	}
	
	/*public static void main(String args[]){
		LOGGER.info("insied the main method");
		GetHRMSDetailsDaoImpl a = new GetHRMSDetailsDaoImpl();
		GetHRMSDetailsDBRequestASBO b = new GetHRMSDetailsDBRequestASBO();
		b.setUserID("16228");
		a.gethrmsdetailsList(b);
		LOGGER.info("executed successfylly");
		
		
	}
*/
}
