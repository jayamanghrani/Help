package com.tcs.employeeportal.alfresco.asbo.request;


import com.tcs.employeeportal.asbo.alfresco.AlfrescoInputs;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;


//TODO: Auto-generated Javadoc
/**
* The Class GetContentRequestASBO.
*
* @author 376448
*/
public class GetContentRequestASBO extends EmployeePortalRequestObject{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4662629643177021288L;
	
	/** The alfresco input. */
	private AlfrescoInputs alfrescoInput;
	
	/**
	 * Gets the alfresco input.
	 *
	 * @return the alfrescoInput
	 */
	public AlfrescoInputs getAlfrescoInput() {
		return alfrescoInput;
	}
	
	/**
	 * Sets the alfresco input.
	 *
	 * @param alfrescoInput the alfrescoInput to set
	 */
	public void setAlfrescoInput(AlfrescoInputs alfrescoInput) {
		this.alfrescoInput = alfrescoInput;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AlfrescoRequestASBO [alfrescoInput=");
		builder.append(alfrescoInput);
		builder.append("]");
		return builder.toString();
	}
	

}
