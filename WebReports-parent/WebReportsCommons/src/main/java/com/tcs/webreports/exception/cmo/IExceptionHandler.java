/**
 * 
 */
package com.tcs.webreports.exception.cmo;

import com.tcs.webreports.exception.cmo.ErrorVO;

/**
 * @author 738566
 *
 */
public interface IExceptionHandler {
	
	public void handle(ErrorVO errorVo, String className);


}
