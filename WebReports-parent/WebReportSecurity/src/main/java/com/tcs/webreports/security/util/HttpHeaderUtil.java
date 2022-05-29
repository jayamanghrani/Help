package com.tcs.webreports.security.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tcs.webreports.exception.cmo.IntegrationTechnicalException;
import com.tcs.webreports.vo.cmo.Header;

@Component("httpHeaderUtil")
public class HttpHeaderUtil
{
  private static final Logger LOGGER = Logger.getLogger("portalLogger");

  public Header generateSpringHeader(HttpHeaderProperty httpHeaderProperties, String eventID)
    throws IntegrationTechnicalException
  {
    Header header = new Header();
    List applicationIds = (List)httpHeaderProperties.getProperty().get("applicationid");

    List customername = (List)httpHeaderProperties.getProperty().get("customername");

    List typeofcustomer = (List)httpHeaderProperties.getProperty().get("typeofcustomer");

    if ((applicationIds == null) || (applicationIds.isEmpty())) {
      throw new IntegrationTechnicalException("header with application id missing");
    }

    header.setApplicationId((String)applicationIds.get(0));

    if ((!"validateUrl".equalsIgnoreCase(eventID)) && (!"refreshContent".equalsIgnoreCase(eventID)))
    {
      if ((null == customername) || (customername.isEmpty())) {
        throw new IntegrationTechnicalException("header with customerName missing");
      }

      header.setCustomerName((String)customername.get(0));

      if ((null == typeofcustomer) || (typeofcustomer.isEmpty())) {
        throw new IntegrationTechnicalException("header with typeofcustomer missing");
      }

      header.setTypeOfCustomer((String)typeofcustomer.get(0));
    }

    header.setEventID(eventID);
    @SuppressWarnings("rawtypes")
	List coRelationIds = (List)httpHeaderProperties.getProperty().get("coRelationId");
    header.setCoRelationId((String)coRelationIds.get(0));
    LOGGER.info("EventId : " + header.getEventID());
    return header;
  }

}