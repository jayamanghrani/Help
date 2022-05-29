package com.tcs.employeeportal.test;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import com.tcs.employeeportal.db.asbo.request.TickerDBRequestASBO;
import com.tcs.employeeportal.service.ticker.TickerCacheService;

public class TestCacheTicker {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("inside the main method of the TestCacheTicker ");
		TickerDBRequestASBO tdbrasbo = new TickerDBRequestASBO();
		tdbrasbo.setTickerinput("tickerinout");
	//	TickerCacheService tcs = new TickerCacheService();
	//	ConcurrentMap<String, List<String>> cacheingGenerinccachedlist = tcs.cacheTickerValues(tdbrasbo);		
		ConcurrentMap<String, List<String>> cacheingnewGenerinc = TickerCacheService.cacheingGenerinc;
		if (cacheingnewGenerinc.get(tdbrasbo.getTickerinput())!= null)
			{
			System.out.println("caching implemented");
		}
		else{
			
			System.out.println("caching not implemented");
		}

	}

}
