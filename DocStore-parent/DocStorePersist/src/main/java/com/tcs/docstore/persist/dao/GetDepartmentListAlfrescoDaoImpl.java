package com.tcs.docstore.persist.dao;

import com.tcs.docstore.db.asbo.request.GetDepartmentListAlfrescoDBRequestASBO;
import com.tcs.docstore.persist.DBUtil;
import com.tcs.docstore.persist.entities.TDocdetail;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;

public class GetDepartmentListAlfrescoDaoImpl
  implements GetDepartmentListAlfrescoDao
{
  private static final Logger LOGGER = Logger.getLogger(GetDepartmentListAlfrescoDaoImpl.class);
  EntityManager entityManager;
  List<TDocdetail> getdepartmentnamesList = null;
  List<String> listOfDepartmentNames = null;

  public List getDepartmentList(GetDepartmentListAlfrescoDBRequestASBO getDepartmentList)
  {
    LOGGER.info("DiscountSetupForIntermediariesDaoImpl");

    try
    {
      entityManager = DBUtil.getEntityManager();
      TypedQuery query = entityManager.createNamedQuery("TDocdetail.findAll", TDocdetail.class);

      String size = String.valueOf(query.getResultList().size());
      LOGGER.debug("******inside getDepartmentList GetDepartmentListAlfrescoDaoImpl size available is *******" + size);

      this.getdepartmentnamesList = query.getResultList();
      this.listOfDepartmentNames = new ArrayList();
      LOGGER.debug("******inside getDepartmentList GetDepartmentListAlfrescoDaoImpl  the CLASS IS *******" + this.getdepartmentnamesList.getClass());

      for (TDocdetail td : this.getdepartmentnamesList) {
        LOGGER.debug("the value of the department is" + String.valueOf(td.getDepartmentName()));
        this.listOfDepartmentNames.add(String.valueOf(td.getDepartmentName()));
      }

      LOGGER.debug("******inside getDepartmentList GetDepartmentListAlfrescoDaoImpl size available is *******" + size);
      query.getResultList();

      LOGGER.debug("******inside getDepartmentList GetDepartmentListAlfrescoDaoImpl  the CLASS IS *******" + this.getdepartmentnamesList.getClass());
    }
    catch (Exception e)
    {
      LOGGER.error(e.getStackTrace());
      e.printStackTrace();
      try
      {
        if (this.entityManager != null)
          this.entityManager.close();
      }
      catch (Exception e1)
      {
        LOGGER.error(e1);
        e1.printStackTrace();
      }
    }
    finally{
    	 if (this.entityManager != null){
             this.entityManager.close();
    }
    }
   
    return listOfDepartmentNames;
  }
}