package com.tcs.docstore.persist.dao;

import com.tcs.docstore.persist.DBUtil;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.log4j.Logger;

public class GetDocUploadUserAccessDaoImpl
  implements GetDocUploadUserAccessDao
{
  private static final Logger LOGGER = Logger.getLogger(GetDocUploadUserAccessDaoImpl.class);
  EntityManager entityManager;
  String access = "N";

  public String getDocUploadUserAccessCheck(String oidGrpValue, String selectgrpValue) {
    LOGGER.info("inside the GetDocUploadUserAccessDaoImpl");
    LOGGER.debug("the oidGrpValue" + oidGrpValue);
    LOGGER.debug("the selectgrpValue" + selectgrpValue);
    String oidGrpValuetoUpperCase = oidGrpValue.toUpperCase();
    String selectgrpValuetoUpperCase = selectgrpValue.toUpperCase();
    try {
      this.entityManager = DBUtil.getEntityManager();

      Query queryUploadUserAccess = this.entityManager.createQuery("SELECT t.accessUpload FROM TDocuploadAuthority t where UPPER(t.oidGroup) =:oidGrpValuetoUpperCase AND UPPER(t.uploadSelectGroup) =:selectgrpValuetoUpperCase");
      LOGGER.debug("the oidGrpValuetoUpperCase" + oidGrpValuetoUpperCase);
      LOGGER.debug("the selectgrpValuetoUpperCase--" + selectgrpValuetoUpperCase);
      queryUploadUserAccess.setParameter("oidGrpValuetoUpperCase", oidGrpValuetoUpperCase);
      queryUploadUserAccess.setParameter("selectgrpValuetoUpperCase", selectgrpValuetoUpperCase);
      int size = queryUploadUserAccess.getResultList().size();
      if (size == 0) {
        this.access = "N";
        return this.access;
      }

      LOGGER.debug("the size of the queryUploadUserAccess " + size);
      this.access = queryUploadUserAccess.getResultList().get(0).toString();
      LOGGER.debug("queryUploadUserAccess.getResultList().get(0).toString();" + queryUploadUserAccess.getResultList().get(0).toString());
      return this.access;
    }
    catch (Exception e)
    {
      this.access = "N";
      e.printStackTrace();
      LOGGER.error(e);
    }return this.access;
  }
}