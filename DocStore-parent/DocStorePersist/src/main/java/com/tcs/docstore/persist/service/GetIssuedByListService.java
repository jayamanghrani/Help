/**
 * 
 */
package com.tcs.docstore.persist.service;

import com.tcs.docstore.db.asbo.request.GetListOfIssuedByDBRequestASBO;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

/**
 * @author 585226
 *
 */
public interface GetIssuedByListService {

	DocStoreResponseObject getIssuedBy(
			GetListOfIssuedByDBRequestASBO getIssuedByList);

}
