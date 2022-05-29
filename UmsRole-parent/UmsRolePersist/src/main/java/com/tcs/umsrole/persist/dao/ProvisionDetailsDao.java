package com.tcs.umsrole.persist.dao;

import com.tcs.umsrole.request.ProvDetailsRequestASBO;
import com.tcs.umsrole.response.ProvisionDetailsResponseASBO;

public interface ProvisionDetailsDao {

	public ProvisionDetailsResponseASBO provisionDetailsUpdate(ProvDetailsRequestASBO provDetailsRequestASBO);
}
