/**
 * 
 */
package com.tcs.docstore.db.asbo.response;

import java.util.List;

import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

/**
 * @author 738566
 *
 */
public class GetStakesDBResponseASBO extends DocStoreResponseObject{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5927849567536840674L;
	
	private List<String> stakes;

	/**
	 * @return the stakes
	 */
	public List<String> getStakes() {
		return stakes;
	}

	/**
	 * @param stakes the stakes to set
	 */
	public void setStakes(List<String> stakes) {
		this.stakes = stakes;
	}
	

}
