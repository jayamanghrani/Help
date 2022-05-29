/**
 * 
 */
package com.tcs.employeeportal.asbo.alfresco;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class SMS.
 *
 * @author 751723
 */
public class SMS implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8864362400924457788L;
	
	/** The content. */
	private String content;
	
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
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * @param product the product to set
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
	 * @param process the process to set
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
	 * @param channel the channel to set
	 */
	public void setChannel(List<String> channel) {
		this.channel = channel;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SMS [content=");
		builder.append(content);
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
