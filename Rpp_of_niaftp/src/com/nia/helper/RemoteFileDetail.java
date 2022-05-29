package com.nia.helper;

public class RemoteFileDetail {
	
	private String filename;
	private String Filecount;
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return the filecount
	 */
	public String getFilecount() {
		return Filecount;
	}
	/**
	 * @param filecount the filecount to set
	 */
	public void setFilecount(String filecount) {
		Filecount = filecount;
	}
	public RemoteFileDetail(String filename, String filecount) {
		super();
		this.filename = filename;
		Filecount = filecount;
	}
	public RemoteFileDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RemoteFileDetail [filename=" + filename + ", Filecount="
				+ Filecount + "]";
	}
	
	
	

}
