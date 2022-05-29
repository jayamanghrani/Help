package com.tcs.docstore.cache.key;

import com.tcs.docstore.cache.key.AlfrescoKey;
import com.tcs.docstore.cache.key.CacheKey;

public class AlfrescoKey implements CacheKey{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3013467960594571337L;
	
	/** The type of content. */
	final private String typeOfContent;
	
	/**
	 * Instantiates a new alfresco key.
	 *
	 * @param typeOfContent the type of content
	 */
	public AlfrescoKey(String typeOfContent) {
		super();
		this.typeOfContent = typeOfContent;
	}
	
	/**
	 * Gets the type of content.
	 *
	 * @return the typeOfContent
	 */
	public String getTypeOfContent() {
		return typeOfContent;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((typeOfContent == null) ? 0 : typeOfContent.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlfrescoKey other = (AlfrescoKey) obj;
		if (typeOfContent == null) {
			if (other.typeOfContent != null)
				return false;
		} else if (!typeOfContent.equals(other.typeOfContent))
			return false;
		return true;
	}
	
}
