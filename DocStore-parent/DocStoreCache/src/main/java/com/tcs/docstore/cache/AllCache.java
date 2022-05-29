
package com.tcs.docstore.cache;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class AllCache.
 */
public class AllCache implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9106096970254318468L;

	/** The cache entries. */
	private List<CacheEntry> cacheEntries;

	/**
	 * Gets the cache entries.
	 *
	 * @return the cacheEntries
	 */
	public List<CacheEntry> getCacheEntries() {
		return cacheEntries;
	}

	/**
	 * Sets the cache entries.
	 *
	 * @param cacheEntries            the cacheEntries to set
	 */
	public void setCacheEntries(List<CacheEntry> cacheEntries) {
		this.cacheEntries = cacheEntries;
	}

}
