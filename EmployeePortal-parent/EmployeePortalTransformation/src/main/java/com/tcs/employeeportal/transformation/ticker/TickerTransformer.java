package com.tcs.employeeportal.transformation.ticker;

import com.tcs.employeeportal.asbo.emp.request.TickerRequestASBO;
import com.tcs.employeeportal.asbo.emp.response.TickerResponseASBO;
import com.tcs.employeeportal.db.asbo.request.TickerDBRequestASBO;
import com.tcs.employeeportal.db.asbo.response.TickerDBResponseASBO;

public class TickerTransformer {

	private TickerRequestASBO tikerasbo;
	private TickerDBRequestASBO tikerDBreqasbo;
	private TickerResponseASBO tikerresponasbo;
	
	
	public TickerTransformer(){
		tikerDBreqasbo = new TickerDBRequestASBO();
		tikerresponasbo = new TickerResponseASBO();
		
	}
	
	public TickerDBRequestASBO transformTickerrequest(
			TickerRequestASBO requestASBO) {
		// TODO Auto-generated method stub
		this.tikerasbo=requestASBO;
		tikerDBreqasbo.setHeader(requestASBO.getHeader());
		transformrequest(requestASBO);
		return tikerDBreqasbo;
	}

	private void transformrequest(TickerRequestASBO requestASBO) {
		// TODO Auto-generated method stub
		tikerDBreqasbo.setTickerinput(requestASBO.getTickerinput());
	}

	public TickerResponseASBO transformresponse(
			TickerDBResponseASBO tickerdbresponseasbo) {
		// TODO Auto-generated method stub
		tikerresponasbo.setHeader(tickerdbresponseasbo.getHeader());
		tikerresponasbo.setDailyPremium(tickerdbresponseasbo.getDailyPremium());
		tikerresponasbo.setDatevalue(tickerdbresponseasbo.getDatevalue());
		tikerresponasbo.setMonthlyPremium(tickerdbresponseasbo.getMonthlyPremium());
		tikerresponasbo.setUptoPremium(tickerdbresponseasbo.getUptoPremium());
		
		return tikerresponasbo;
	}

}
