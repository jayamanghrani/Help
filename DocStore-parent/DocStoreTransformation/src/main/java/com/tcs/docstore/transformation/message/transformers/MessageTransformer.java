/********************************************************************************
 * Copyright (c) 2013-2015, TATA Consultancy Services Limited (TCSL)
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are not permitted.
 * 
 * Neither the name of TCSL nor the names of its contributors may be 
 * used to endorse or promote products derived from this software without 
 * specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 *******************************************************************************/
package com.tcs.docstore.transformation.message.transformers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.apache.log4j.Logger;
import com.tcs.docstore.vo.cmo.DocStoreRequestObject;
import com.tcs.docstore.exception.cmo.IntegrationTransformationException;
import com.tcs.docstore.message.asbo.request.MessageServiceRequestASBO;
import com.tcs.docstore.util.UtilConstants;

/**
 * The Class MessageTransformer.
 */
/**
 * @author 738566
 *
 */

public class MessageTransformer {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger("docStoreLogger");
	
	/** The message service request asbo. */
	private MessageServiceRequestASBO messageServiceRequestASBO;


	/**
	 * Instantiates a new message transformer.
	 */
	public MessageTransformer() {
		messageServiceRequestASBO = new MessageServiceRequestASBO();
	
	}

	
	public DocStoreRequestObject transformMobileAppMsgResponse(
			String mobileNo, String app) throws IntegrationTransformationException{

		LOGGER.info("message tranformer transformMobileAppMsgResponse response method");
		
		String content=UtilConstants.MOBILEAPP_SMS;
		String finalContent = content.replace("{appLink}", app);
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",
				Locale.ENGLISH);
		Date sysDate = new Date();
		String currentDate = dateFormat.format(sysDate);
		
		java.util.Map<String, String> data = new HashMap<String, String>();
	//	data.put("EMAILID", forgotPasswordResponseASBO.getEmailId());
		data.put("mobileNo",mobileNo);
		data.put("messageType", "MOBILEAPPLINK");
		data.put("message", finalContent+"\n");
		data.put("date", currentDate);
		

		messageServiceRequestASBO.setData(data);

		return messageServiceRequestASBO;
	}

}
