/**
 * 
 */
package com.tcs.docstore.cache;


import java.io.Serializable;



// TODO: Auto-generated Javadoc
/**
 * The Class CacheEntry.
 *
 * @author 751723
 */
public class CacheEntry implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9106096970254318468L;

	/** The cache key. */
	private CacheKey cacheKey;
	
	/** The object. */
	private Object object;

	/**
	 * Gets the cache key.
	 *
	 * @return the cacheKey
	 */
	public CacheKey getCacheKey() {
		return cacheKey;
	}

	/**
	 * Sets the cache key.
	 *
	 * @param cacheKey            the cacheKey to set
	 */
	public void setCacheKey(CacheKey cacheKey) {
		this.cacheKey = cacheKey;
	}

	/**
	 * Gets the object.
	 *
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}

	/**
	 * Sets the object.
	 *
	 * @param object            the object to set
	 */
	public void setObject(Object object) {
		this.object = object;
	}

}
