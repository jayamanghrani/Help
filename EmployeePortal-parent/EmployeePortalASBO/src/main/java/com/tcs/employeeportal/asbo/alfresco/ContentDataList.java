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
package com.tcs.employeeportal.asbo.alfresco;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The class ContentDataList.
 * @author 751723
 * 
 */
public class ContentDataList implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2383412205192349127L;
	
	/** The content data list. */
	private List<ContentDataASBO> contentDataList;

	/**
	 * Gets the content data list.
	 *
	 * @return the contentDataList
	 */
	public List<ContentDataASBO> getContentDataList() {
		return contentDataList;
	}

	/**
	 * Sets the content data list.
	 *
	 * @param contentDataList            the contentDataList to set
	 */
	public void setContentDataList(List<ContentDataASBO> contentDataList) {
		this.contentDataList = contentDataList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContentDataList [contentDataList=");
		builder.append(contentDataList);
		builder.append("]");
		return builder.toString();
	}

}
