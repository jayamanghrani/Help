package com.tcs.umsapp.search.so.response;

import java.util.List;

import com.tcs.umsapp.search.vo.cmo.UmsappResponseObject;

public class UserOfficeCodeDetails extends UmsappResponseObject{

	private static final long serialVersionUID = 1L;
	
	private List<String> officeCodes;

	public List<String> getOfficeCodes() {
		return officeCodes;
	}

	public void setOfficeCodes(List<String> officeCodes) {
		this.officeCodes = officeCodes;
	}
	
	
}
