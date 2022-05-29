package com.tcs.umsapp.osb.persist.services;
import java.util.concurrent.ConcurrentHashMap;

import com.tcs.umsapp.osb.common.ProvisionDetail;
import com.tcs.umsapp.osb.persist.entities.ProvDetailEntity;

public interface UMSOSBDao {
	
	public ConcurrentHashMap<Number, ProvisionDetail> getProvisionDetails(); 

	public void updateIntermediateStatus(ProvDetailEntity provisionDetail);

	void revertIntermediateStatus(Long provisionId);

}
