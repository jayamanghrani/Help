/**
 * 
 */
package com.tcs.docstore.exception.cmo;

import com.tcs.docstore.exception.cmo.ErrorVO;

/**
 * @author 738566
 *
 */
public interface IExceptionHandler {
	
	public void handle(ErrorVO errorVo, String className);


}
