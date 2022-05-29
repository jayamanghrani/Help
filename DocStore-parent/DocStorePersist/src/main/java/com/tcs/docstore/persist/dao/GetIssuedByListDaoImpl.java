/**
 * 
 */
package com.tcs.docstore.persist.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.tcs.docstore.db.asbo.request.GetListOfIssuedByDBRequestASBO;
import com.tcs.docstore.db.asbo.response.GetListOfIssuedByDBResponseASBO;
import com.tcs.docstore.persist.DBUtil;

/**
 * @author 585226
 *
 */
public class GetIssuedByListDaoImpl implements GetIssuedByListDao{
	private static final Logger LOGGER = Logger.getLogger(GetIssuedByListDaoImpl.class);
	EntityManager entityManager;

	@Override
	public GetListOfIssuedByDBResponseASBO getIssuedByList(
			GetListOfIssuedByDBRequestASBO getIssuedByList) {
		
		GetListOfIssuedByDBResponseASBO glidbrespasbo = new GetListOfIssuedByDBResponseASBO();
		List<String> issuedByList = new ArrayList<String>();
		try{
			entityManager=DBUtil.getEntityManager();
			
			Query query =	entityManager.createNamedQuery("TDocdetail.findIssuedByAll");
			String size=String.valueOf(query.getResultList().size());
			LOGGER.debug("******inside  GetIssuedByListDaoImpl size available is *******" +size);
		
				for(int i=0;i<query.getResultList().size();i++){
					String newissued = query.getResultList().get(i).toString();
				LOGGER.debug("inside the while looop the values are"+query.getResultList().get(i).toString());
				issuedByList.add(newissued);
				}
		}
		
		catch(Exception e){
		e.printStackTrace();
		LOGGER.error(e);
		}
		// TODO Auto-generated method stub
		glidbrespasbo.setIssuedByList(issuedByList);
		return glidbrespasbo;
	}


}
