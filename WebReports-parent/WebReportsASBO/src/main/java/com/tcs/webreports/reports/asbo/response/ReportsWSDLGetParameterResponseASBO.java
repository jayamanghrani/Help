package com.tcs.webreports.reports.asbo.response;
import com.oracle.xmlns.oxp.service.v2.ParamNameValues;
import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

/**
 * @author 738796
 *
 */
public class ReportsWSDLGetParameterResponseASBO extends WebReportsResponseObject {
	/**
	 * 
	 */
private static final long serialVersionUID = 2766661784679821167L;
	private String name;
	private ParamNameValues paramNameValue;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
/**
 * @return the paramNameValue
 */
public ParamNameValues getParamNameValue() {
	return paramNameValue;
}

/**
 * @param paramNameValue the paramNameValue to set
 */
public void setParamNameValue(ParamNameValues paramNameValue) {
	this.paramNameValue = paramNameValue;
	}
}