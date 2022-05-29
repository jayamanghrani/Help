/**
 * 
 */
package com.tcs.umsrole.persist.dao;

import com.tcs.umsrole.request.AcknowledgeASBO;

/**
 * @author 926814
 *
 */
public interface AcknowledgeDao {

    public void acknowledgeRequest(AcknowledgeASBO acknowledgeASBO , boolean xlFlag);
    public void mailUpdateRoleXL(String requestId);
}
