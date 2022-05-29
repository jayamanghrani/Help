package com.tcs.umsapp.upload.commons;

public class ExcelError {

	private int row;
	private int column;
	private String error;
	
	
	
	public ExcelError() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ExcelError(int row, int column, String error) {
		super();
		this.row = row;
		this.column = column;
		this.error = error;
	}
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
	
	
	
}
