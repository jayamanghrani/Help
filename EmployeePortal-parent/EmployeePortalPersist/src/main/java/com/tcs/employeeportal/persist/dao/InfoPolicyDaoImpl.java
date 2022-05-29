/**
 * 
 */
package com.tcs.employeeportal.persist.dao;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.tcs.employeeportal.db.asbo.request.GetInfoPolicyDetailsDBRequestASBO;
import com.tcs.employeeportal.db.asbo.request.InfoPolicyAcceptDBRequestASBO;
import com.tcs.employeeportal.db.asbo.response.GetInfoPolicyDetailsDBResponseASBO;
import com.tcs.employeeportal.db.asbo.response.InfoPolicyAcceptDBResponseASBO;
import com.tcs.employeeportal.persist.DBUtil;
import com.tcs.employeeportal.persist.entities.TEmployeeDetail;
import com.tcs.employeeportal.persist.entities.TInfoPolicy;

/**
 * @author 738796
 *
 */
public class InfoPolicyDaoImpl implements InfoPolicyDao {
	
	public InfoPolicyDaoImpl(){
		
	}
	
	private static final Logger LOGGER = Logger.getLogger(InfoPolicyDaoImpl.class);
	
	
	public GetInfoPolicyDetailsDBResponseASBO getInfoPolicyDetails(GetInfoPolicyDetailsDBRequestASBO getInfoPolicyDetailsDBRequestASBO) {
		LOGGER.info("Inside getInfoPolicyDetails");
		GetInfoPolicyDetailsDBResponseASBO getInfoPolicyDetailsDBResponseASBO = new GetInfoPolicyDetailsDBResponseASBO();
			EntityManager entityManager=null;
			TInfoPolicy response=null;
			TEmployeeDetail response1=null;
			try {
			entityManager=DBUtil.getEntityManager();
			TypedQuery<TInfoPolicy> query= entityManager.createNamedQuery("TInfoPolicy.findUser",TInfoPolicy.class);
			query.setParameter("loginId", getInfoPolicyDetailsDBRequestASBO.getUserId());
			if(query.getResultList().size()==0)
			{
				LOGGER.debug("query.getResultList()11111111111" + query.getResultList());
				TypedQuery<TEmployeeDetail> query1= entityManager.createNamedQuery("TEmployeeDetail.findUser", TEmployeeDetail.class);
				query1.setParameter("loginId", getInfoPolicyDetailsDBRequestASBO.getUserId());
				response1= (TEmployeeDetail)query1.getSingleResult();
				
				getInfoPolicyDetailsDBResponseASBO.setFirstName(response1.getTEmpFirstName());
				getInfoPolicyDetailsDBResponseASBO.setLastName(response1.getTEmpLastName());
				getInfoPolicyDetailsDBResponseASBO.setUserId(response1.getTEmpLoginId());
				getInfoPolicyDetailsDBResponseASBO.setOfficeCode(response1.getTEmpBranchCode());
				getInfoPolicyDetailsDBResponseASBO.setStatus("Y");
				LOGGER.info("response------" + response);
			}
				
			else
			{
				LOGGER.info("No Data Available");
				getInfoPolicyDetailsDBResponseASBO.setStatus("N");
			}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block

			LOGGER.error(e);
				
			}
			finally{
				try{
					if(null!=entityManager){
					entityManager.close();
					}
				}
					catch(Exception e){
				LOGGER.error(e);
					
				}
			}

			return getInfoPolicyDetailsDBResponseASBO;

		}



	public InfoPolicyAcceptDBResponseASBO getInfoAcceptPolicy(
			InfoPolicyAcceptDBRequestASBO infoPolicyAcceptDBRequestASBO) {
		// TODO Auto-generated method stub
		LOGGER.info("Inside getInfoAcceptPolicy");
		InfoPolicyAcceptDBResponseASBO infoPolicyAcceptDBResponseASBO = new InfoPolicyAcceptDBResponseASBO();
		EntityManager entityManager=null;
		try {
			entityManager=DBUtil.getEntityManager();
			String userId=infoPolicyAcceptDBRequestASBO.getUserId();
			Query query=entityManager.createQuery("select t.tEmpLoginId from TEmployeeDetail t where t.tEmpLoginId=:tEmpLoginId");
			query.setParameter("tEmpLoginId", userId);
			if(!query.getResultList().isEmpty())
			{
				LOGGER.debug("INSIDE if");
			TInfoPolicy tinfo = new TInfoPolicy();
			tinfo.setTEmpLoginId(userId);
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			Date dateobj = new Date();
		   tinfo.setTPolicyAcceptanceDt(dateobj);
			entityManager.getTransaction().begin();
		
				entityManager.persist(tinfo);
				entityManager.getTransaction().commit(); 
		
				LOGGER.debug("infoPolicyAcceptDBResponseASBO.getAcceptance" +infoPolicyAcceptDBRequestASBO.getUserId());
			
			infoPolicyAcceptDBResponseASBO.setStatus("Submited Successfully");
			
		} 
			else 
				{
				infoPolicyAcceptDBResponseASBO.setStatus("Invalid userId");
				}
				}
			catch (Exception e) 
		{
		if(e.getMessage().toString().contains("Error while committing the transaction"))
			{
		infoPolicyAcceptDBResponseASBO.setStatus("Policy has been accepted by the user.");
			LOGGER.error(e);
		}
		else
		{
			infoPolicyAcceptDBResponseASBO.setStatus("Some error occured. Submit unsuccessful");
		LOGGER.error(e);
		}
			
		}
		finally{
			try{
				if(null!=entityManager){
				entityManager.close();
				}
			}
				catch(Exception e)
				{
			LOGGER.error(e);
			infoPolicyAcceptDBResponseASBO.setStatus("Some error occured. Submit unsuccessful");
				
			}
		}
		
		return infoPolicyAcceptDBResponseASBO;

	}



}
