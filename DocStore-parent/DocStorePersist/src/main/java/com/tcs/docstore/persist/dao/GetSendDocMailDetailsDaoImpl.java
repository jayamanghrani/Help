/**
 * 
 */
package com.tcs.docstore.persist.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.tcs.docstore.persist.DBUtil;
import com.tcs.docstore.persist.entities.TDocMailAccess;

/**
 * @author 738566
 *
 */
public class GetSendDocMailDetailsDaoImpl implements GetSendDocMailDetailsDao {
	private static final Logger LOGGER = Logger.getLogger(GetSendDocMailDetailsDaoImpl.class);

	@Override
	public List sendDocumentUpplodMailDetails(String userID) {
		// TODO Auto-generated method stub
	EntityManager entityManager=null;
	List returnList = new ArrayList<String>();
		TDocMailAccess response=null;
		try {
			entityManager=DBUtil.getEntityManager();
			
			List rs = new ArrayList();
			TypedQuery<TDocMailAccess> query= entityManager.createNamedQuery("TDocMailAccess.findUserAcccess",TDocMailAccess.class);
			query.setParameter("userId", userID);
			query.setParameter("accessFlag", "Y");
			rs= (List) query.getResultList();
			if(rs.size()>0){
				response=(TDocMailAccess) rs.get(0);
				String mailList = response.getTTdmaMailList();
				String flag=response.getTTdmaAccess();
				LOGGER.info("executed successfully the sizeof list is -->"+rs.get(0));
				LOGGER.info("the mailing list is as---->"+mailList);
				returnList.add(mailList);
				returnList.add(flag);
			}
			else{
				LOGGER.debug("not authorized for access");
				returnList.add("NA");
				returnList.add("N");
				//returnList.clear();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
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
					e.printStackTrace();
				LOGGER.error(e);
			}
		}
		
		return returnList;
	}
	
}

