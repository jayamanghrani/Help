/**
 * 
 */
package com.tcs.docstore.asbo.emp.response;

import java.util.List;

import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

/**
 * @author 738566
 *
 */
public class GetStakesResponseASBO extends DocStoreResponseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -434932791578758761L;
	
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
