package com.tcs.employeeportal.cache.asbo.request;

import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;

public class GetContentValueRequestASBO extends EmployeePortalRequestObject {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7935271323970269957L;

	/** The cache region. */
	private String cacheRegion;

	/** The type of content. */
	private String typeOfContent;
	
	/** The type of content. */
	private String channel;


	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * Gets the cache region.
	 *
	 * @return the cacheRegion
	 */
	public String getCacheRegion() {
		return cacheRegion;
	}

	/**
	 * Sets the cache region.
	 *
	 * @param cacheRegion            the cacheRegion to set
	 */
	public void setCacheRegion(String cacheRegion) {
		this.cacheRegion = cacheRegion;
	}

	/**
	 * Gets the type of content.
	 *
	 * @return the typeOfContent.
	 */
	public String getTypeOfContent() {
		return typeOfContent;
	}

	/**
	 * Sets the type of content.
	 *
	 * @param typeOfContent the typeOfContent to set
	 */
	public void setTypeOfContent(String typeOfContent) {
		this.typeOfContent = typeOfContent;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetContentValueRequestASBO [cacheRegion=")
				.append(cacheRegion).append(", typeOfContent=")
				.append(typeOfContent).append("]");
		return builder.toString();
	}

	


}
