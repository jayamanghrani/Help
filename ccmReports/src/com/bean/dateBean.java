package com.bean;

public class dateBean {
	private static String userEnteredTodate;
	private static String userEnteredFromdate;
	public static String getUserEnteredTodate() {
		return userEnteredTodate;
	}
	public static void setUserEnteredTodate(String userEnteredTodate) {
		dateBean.userEnteredTodate = userEnteredTodate;
	}
	public static String getUserEnteredFromdate() {
		return userEnteredFromdate;
	}
	public static void setUserEnteredFromdate(String userEnteredFromdate) {
		dateBean.userEnteredFromdate = userEnteredFromdate;
	}
	
	

}
