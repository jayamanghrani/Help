/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package com.nia.jpa.dto;

import java.io.Serializable;

public class Reverse_Feed_dto implements Serializable
{
	protected String reverse_feed;
	protected String iims_count;
	protected String of_count;
	
	public String getReverse_feed() {
		return reverse_feed;
	}

	public void setReverse_feed(String reverse_feed) {
		this.reverse_feed = reverse_feed;
	}

	public String getIims_count() {
		return iims_count;
	}

	public void setIims_count(String iims_count) {
		this.iims_count = iims_count;
	}

	public String getOf_count() {
		return of_count;
	}

	public void setOf_count(String of_count) {
		this.of_count = of_count;
	}





	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof Reverse_Feed_dto)) {
			return false;
		}
		
		final Reverse_Feed_dto _cast = (Reverse_Feed_dto) _other;
		if (reverse_feed == null ? _cast.reverse_feed != reverse_feed : !reverse_feed.equals( _cast.reverse_feed )) {
			return false;
		}
	
		
		if (iims_count == null ? _cast.iims_count != iims_count : !iims_count.equals( _cast.iims_count )) {
			return false;
		}
		
		if (of_count == null ? _cast.of_count != of_count : !of_count.equals( _cast.of_count )) {
			return false;
		}
		
	
		
			
		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		
		
	
		if (reverse_feed != null) {
			_hashCode = 29 * _hashCode + reverse_feed.hashCode();
		}
		
		if (iims_count != null) {
			_hashCode = 29 * _hashCode + iims_count.hashCode();
		}
		
		if (of_count != null) {
			_hashCode = 29 * _hashCode + of_count.hashCode();
		}
	
			
		_hashCode = 29 * _hashCode;
		return _hashCode;
	}

	

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.nia.jpa.dto.OfMonitoring: " );
		ret.append( "iims_count=" + iims_count );
		ret.append( ", of_count=" + of_count );

		
		return ret.toString();
	}

	
}
