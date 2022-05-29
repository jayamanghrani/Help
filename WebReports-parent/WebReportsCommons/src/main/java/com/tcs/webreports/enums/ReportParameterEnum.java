package com.tcs.webreports.enums;

public enum ReportParameterEnum {
  
	FROMDATE("FromDate"),
    TODATE("ToDate"),
    PARTYCODE("PartyCode"),
    CHANNEL("Channel");
  
  private final String paramType;

private ReportParameterEnum(String paramType) {
	this.paramType = paramType;
}

public boolean equalsParam(String paramType) {
    return paramType.equals(paramType);
}

public String toString() {
   return this.paramType;
}
	
}
