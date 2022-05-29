/**
 * 
 */
package com.tcs.docstore.log.cmo;

import java.io.Serializable;
import java.util.List;

/**
 * @author 738566
 *
 */
public class LoggerHeader implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1879203640170926825L;

	/** The name. */
	private String name;

	/** The values. */
	private List<String> values;

	/**
	 * 
	 */
	public LoggerHeader() {
		super();
	}

	/**
	 * @param name
	 * @param value
	 */
	public LoggerHeader(String name, List<String> values) {
		super();
		this.name = name;
		this.values = values;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the values
	 */
	public List<String> getValues() {
		return values;
	}

	/**
	 * @param values
	 *            the values to set
	 */
	public void setValues(List<String> values) {
		this.values = values;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoggerHeader [name=");
		builder.append(name);
		builder.append(", values=");
		builder.append(values);
		builder.append("]");
		return builder.toString();
	}

}

