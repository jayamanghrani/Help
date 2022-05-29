/**
 * 
 */
package com.tcs.docstore.persist.service;

import com.tcs.docstore.db.asbo.request.GetHRMSDetailsDBRequestASBO;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

/**
 * @author 585226
 *
 */
public interface GetHRMSDetailsService {

	DocStoreResponseObject getHrmsDetailsList(GetHRMSDetailsDBRequestASBO gethrmsdetailsdbreqasbo);

}
