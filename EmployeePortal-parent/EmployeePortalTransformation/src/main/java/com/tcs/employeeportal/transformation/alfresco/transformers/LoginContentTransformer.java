/**
 * 
 */
package com.tcs.employeeportal.transformation.alfresco.transformers;

import com.tcs.employeeportal.alfresco.asbo.request.GetAlfrecoContentRequestASBO;
import com.tcs.employeeportal.asbo.login.request.LoginRequestASBO;
import com.tcs.employeeportal.cache.util.CacheConstants;
import com.tcs.employeeportal.exception.cmo.IntegrationTransformationException;

/**
 * @author 738566
 *
 */
public class LoginContentTransformer {
	
	/** The get content alfresco request asbo. */
	private GetAlfrecoContentRequestASBO getAlfrecoContentRequestASBO;
		
	/** Login ASBO request*/
	private LoginRequestASBO loginRequestASBO;
	
	public LoginContentTransformer(){

		getAlfrecoContentRequestASBO= new GetAlfrecoContentRequestASBO();
	}

	/**
	 * Transform login request.
	 * @param loginRequestASBO
	 * @return
	 */
	public GetAlfrecoContentRequestASBO transformLoginAddLink(
			LoginRequestASBO loginRequestASBO)throws IntegrationTransformationException {
		this.loginRequestASBO=loginRequestASBO;
		getAlfrecoContentRequestASBO.setHeader(loginRequestASBO.getHeader());
		this.transformLoginAddLink();
		return getAlfrecoContentRequestASBO;
	}
	
	/**
	 * 
	 */
	private void transformLoginAddLink() {
	
		getAlfrecoContentRequestASBO.setChannel(loginRequestASBO.getAlfrescoInput().getChannel());
		getAlfrecoContentRequestASBO.setTypeOfContent(CacheConstants.ADDITIONAL_LINKS_CONTENT);
	}

}
