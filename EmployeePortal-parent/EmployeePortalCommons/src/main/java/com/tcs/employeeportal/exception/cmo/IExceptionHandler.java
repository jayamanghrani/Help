/**
 * 
 */
package com.tcs.employeeportal.exception.cmo;

import com.tcs.employeeportal.exception.cmo.ErrorVO;

/**
 * @author 738566
 *
 */
public interface IExceptionHandler {
	
	public void handle(ErrorVO errorVo, String className);


}
