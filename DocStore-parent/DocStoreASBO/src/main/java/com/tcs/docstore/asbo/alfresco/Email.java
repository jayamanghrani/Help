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
package com.tcs.docstore.asbo.alfresco;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Email.
 * @author 751723
 * 
 */
public class Email implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1430189934087971205L;

	/** The content. */
	private String content;
	
	/** The title. */
	private String title;
	
	/** The product. */
	private List<String> product;
	
	/** The process. */
	private List<String> process;
	
	/** The channel. */
	private List<String> channel;

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the product.
	 *
	 * @return the product
	 */
	public List<String> getProduct() {
		return product;
	}

	/**
	 * Sets the product.
	 *
	 * @param product            the product to set
	 */
	public void setProduct(List<String> product) {
		this.product = product;
	}

	/**
	 * Gets the process.
	 *
	 * @return the process
	 */
	public List<String> getProcess() {
		return process;
	}

	/**
	 * Sets the process.
	 *
	 * @param process            the process to set
	 */
	public void setProcess(List<String> process) {
		this.process = process;
	}

	/**
	 * Gets the channel.
	 *
	 * @return the channel
	 */
	public List<String> getChannel() {
		return channel;
	}

	/**
	 * Sets the channel.
	 *
	 * @param channel            the channel to set
	 */
	public void setChannel(List<String> channel) {
		this.channel = channel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Email [content=");
		builder.append(content);
		builder.append(", title=");
		builder.append(title);
		builder.append(", product=");
		builder.append(product);
		builder.append(", process=");
		builder.append(process);
		builder.append(", channel=");
		builder.append(channel);
		builder.append("]");
		return builder.toString();
	}

}
