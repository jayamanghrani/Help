package com.tcs.umsapp.upload.validation;

import java.text.DecimalFormat;

public class Validation {

	public boolean isNum(String input) {

		// Convert Number from double to integer format.
		DecimalFormat formatter = new DecimalFormat("0");
		try {
			input = formatter.format(Double.parseDouble(input));
		} catch (NumberFormatException ex) {
			return false;
		}

		if (input.matches("^[0-9]+$"))
			return true;
		else
			return false;

	}

	public boolean isOfficeCode(String number) {

		// Convert Number from double to integer format.
		DecimalFormat formatter = new DecimalFormat("0");
		try {
			number = formatter.format(Double.parseDouble(number));
		} catch (NumberFormatException ex) {
			return false;
		}
		if (number.length() == 6) {
			if (number.matches("[0-9]+"))
				return true;
			else
				return false;
		} else
			return false;
	}

}
