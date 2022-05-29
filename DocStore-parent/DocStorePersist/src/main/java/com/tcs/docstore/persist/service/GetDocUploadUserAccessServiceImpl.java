/**
 * 
 */
package com.tcs.docstore.persist.service;

import com.tcs.docstore.persist.dao.GetDocUploadUserAccessDao;
import com.tcs.docstore.persist.dao.GetDocUploadUserAccessDaoImpl;

/**
 * @author 585226
 *
 */
public class GetDocUploadUserAccessServiceImpl implements GetDocUploadUserAccessService{
	
	private GetDocUploadUserAccessDao docuploadaccesdao;
	
	public GetDocUploadUserAccessServiceImpl( ){
		docuploadaccesdao = new GetDocUploadUserAccessDaoImpl();
	}

	@Override
	public String validateUploadOfUser(String oidGrpValue,String selectgrpValue) {
		String access = null;
		access = docuploadaccesdao.getDocUploadUserAccessCheck(oidGrpValue, selectgrpValue);

		return access;
	}

}
