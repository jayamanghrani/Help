package com.tcs.employeeportal.cache;

public class Config {
	
	//cache values supported are ehcache and hashmap
		/** The Constant CACHE. */
		public static final String CACHE="hashmap";
		//cachinglevel -- the number of characters as keys
		/** The Constant CACHINGLEVEL. */
		public static final int CACHINGLEVEL = 3;
		
		/**
		 * Instantiates a new config.
		 */
		private Config() {
		}

}
