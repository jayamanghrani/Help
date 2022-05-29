package com.nia.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Test {
	public static void main(String [] args){
		Calendar cal = Calendar.getInstance();	 
		SimpleDateFormat dmyFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String date=dmyFormat.format(cal.getTime());
		System.out.println(date);
	}
}
