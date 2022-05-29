package com.util;

import com.bean.dateBean;

public class dateValidation {
	dateBean d = new dateBean();
	public static String datevalid(){
	String fromDate = dateBean.getUserEnteredFromdate();
	String toDate = dateBean.getUserEnteredTodate();
	int Fyyyy = Integer.parseInt(fromDate.substring(6));
	int Fdd = Integer.parseInt(fromDate.substring(0, 2));
	int Fmm = Integer.parseInt(fromDate.substring(3, 5));
	int Tyyyy = Integer.parseInt(toDate.substring(6));
	int Tdd = Integer.parseInt(toDate.substring(0, 2));
	int Tmm = Integer.parseInt(toDate.substring(3, 5));
	
	if (Fyyyy<Tyyyy) {
		System.out.println("fY < ty");
	}
	else if (Fyyyy == Tyyyy) {
		//System.out.println("fy = ty");
		if (Fmm == Tmm) {
			System.out.println("fm = tm");
			if (Fdd <= Tdd) {
				System.out.println("fd <= td");
			}
			else{
				return null;
			}
		}
		else if(Fmm < Tmm){
			System.out.println(" fm < tm ");
		}
		else{
			System.out.println("tm>fm when fy=ty");
			return null;
		}
	}
	else{
		return null;
	}
	
	
	return toDate;
	}

}
