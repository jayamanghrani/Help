package com.tcs.employeeportal.cache;

import java.util.Set;

import com.tcs.employeeportal.cache.key.CacheKey;

public interface MyCache {

	/**
	 * Put.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	void put(CacheKey key,Object value);
	
	/**
	 * Gets the.
	 * 
	 * @param key
	 *            the key
	 * @return the object
	 */
	Object get(CacheKey key);
	
	/**
	 * Size.
	 * 
	 * @return the int
	 */
	int size();
	
	/**
	 * Removes the.
	 * 
	 * @param key
	 *            the key
	 */
	void remove(CacheKey key);
	
	/**
	 * Key exists.
	 * 
	 * @param key
	 *            the key
	 * @return true, if successful
	 */
	boolean keyExists(CacheKey key);
	
	/**
	 * Key set.
	 * 
	 * @return the sets the
	 */
	Set<CacheKey> keySet();
}
