/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package com.nia.jpa.dto;

import java.io.Serializable;

public class Sftp_fname_dto implements Serializable
{
	/**
	 * 
	 */
	
	protected String file_name;
		
	public Sftp_fname_dto()
	{
	}

	
	public String getFile_name() {
		return file_name;
	}





	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}





	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof Sftp_fname_dto)) {
			return false;
		}
		
		final Sftp_fname_dto _cast = (Sftp_fname_dto) _other;
		if (file_name == null ? _cast.file_name != file_name : !file_name.equals( _cast.file_name )) {
			return false;
		}
					
		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		
		
	
		if (file_name != null) {
			_hashCode = 29 * _hashCode + file_name.hashCode();
		}
		
		_hashCode = 29 * _hashCode;
		return _hashCode;
	}

	

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
/*	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.nia.jpa.dto.sftp_fname_dto: " );
		ret.append( "file_name=" + file_name );
		
		return ret.toString();
	}
*/
	protected String destinationPath;
	protected String filePrefix;
	protected String bankWiseOutboundAPath;
	protected String bankWisePlainFileBackupPath;
	protected String bankWiseEncryptedFileBackupPath;
	protected String rejectedFileCommonPath;

	public String getDestinationPath() {
		return destinationPath;
	}


	public void setDestinationPath(String destinationPath) {
		this.destinationPath = destinationPath;
	}


	public String getFilePrefix() {
		return filePrefix;
	}


	public void setFilePrefix(String filePrefix) {
		this.filePrefix = filePrefix;
	}


	public String getBankWiseOutboundAPath() {
		return bankWiseOutboundAPath;
	}


	public void setBankWiseOutboundAPath(String bankWiseOutboundAPath) {
		this.bankWiseOutboundAPath = bankWiseOutboundAPath;
	}


	public String getBankWisePlainFileBackupPath() {
		return bankWisePlainFileBackupPath;
	}


	public void setBankWisePlainFileBackupPath(String bankWisePlainFileBackupPath) {
		this.bankWisePlainFileBackupPath = bankWisePlainFileBackupPath;
	}


	public String getBankWiseEncryptedFileBackupPath() {
		return bankWiseEncryptedFileBackupPath;
	}


	public void setBankWiseEncryptedFileBackupPath(
			String bankWiseEncryptedFileBackupPath) {
		this.bankWiseEncryptedFileBackupPath = bankWiseEncryptedFileBackupPath;
	}


	public String getRejectedFileCommonPath() {
		return rejectedFileCommonPath;
	}


	public void setRejectedFileCommonPath(String rejectedFileCommonPath) {
		this.rejectedFileCommonPath = rejectedFileCommonPath;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sftp_fname_dto [file_name=" + file_name + ", destinationPath="
				+ destinationPath + ", filePrefix=" + filePrefix
				+ ", bankWiseOutboundAPath=" + bankWiseOutboundAPath
				+ ", bankWisePlainFileBackupPath="
				+ bankWisePlainFileBackupPath
				+ ", bankWiseEncryptedFileBackupPath="
				+ bankWiseEncryptedFileBackupPath + ", rejectedFileCommonPath="
				+ rejectedFileCommonPath + "]";
	}
	
	
	/********************** EOC- Added by sarala to common Utility method ***********************************/

}
