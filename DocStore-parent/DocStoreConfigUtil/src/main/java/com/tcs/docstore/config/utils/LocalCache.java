package com.tcs.docstore.config.utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LocalCache {

	public static ConcurrentMap<String, Object> dataMap = new ConcurrentHashMap<String, Object>();
	
}
