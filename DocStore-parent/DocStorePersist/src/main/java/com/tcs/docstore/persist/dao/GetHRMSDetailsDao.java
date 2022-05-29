/**
 * 
 */
package com.tcs.docstore.persist.dao;

import com.tcs.docstore.db.asbo.request.GetHRMSDetailsDBRequestASBO;
import com.tcs.docstore.db.asbo.response.GetHRMSDetailsDBResponseASBO;

/**
 * @author 585226
 *
 */
public interface GetHRMSDetailsDao {

	GetHRMSDetailsDBResponseASBO gethrmsdetailsList(GetHRMSDetailsDBRequestASBO gethrmsdetailsdbreqasbo);

}
