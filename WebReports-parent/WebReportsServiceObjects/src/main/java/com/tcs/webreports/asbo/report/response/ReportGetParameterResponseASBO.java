package com.tcs.webreports.asbo.report.response;
import com.oracle.xmlns.oxp.service.v2.ParamNameValues;
import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

public class ReportGetParameterResponseASBO extends WebReportsResponseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7478425807176101018L;
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
	 * @param paramNameValue the paramNameValue to set
	 */
	public void setParamNameValue(ParamNameValues paramNameValue) {
		this.paramNameValue = paramNameValue;
	}
	/**
	 * @return the paramNameValue
	 */
	public ParamNameValues getParamNameValue() {
		return paramNameValue;
		
	}
}