/**
 * 
 */
package com.tcs.docstore.persist.dao;

import com.tcs.docstore.db.asbo.request.GetListOfIssuedByDBRequestASBO;
import com.tcs.docstore.db.asbo.response.GetListOfIssuedByDBResponseASBO;

/**
 * @author 585226
 *
 */
public interface GetIssuedByListDao {

	GetListOfIssuedByDBResponseASBO getIssuedByList(
			GetListOfIssuedByDBRequestASBO getIssuedByList);

}
