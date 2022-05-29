package com.tcs.umsapp.search.so.response;

import java.util.List;



import javax.naming.spi.DirStateFactory.Result;

import com.tcs.umsapp.search.vo.cmo.UmsappResponseObject;

public class GetContentResponseSO extends UmsappResponseObject {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8518293422189859003L;
	
	
	/** The result. */
	private List<Result> result;


    /**
     * @return the result
     */
    public List<Result> getResult() {
        return result;
    }


    /**
     * @param result the result to set
     */
    public void setResult(List<Result> result) {
        this.result = result;
    }

	
}

	


