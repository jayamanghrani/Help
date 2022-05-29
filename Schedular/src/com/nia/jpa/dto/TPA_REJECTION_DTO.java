package com.nia.jpa.dto;

import java.io.Serializable;

public class TPA_REJECTION_DTO implements Serializable {
 protected String invoiceNo;

public String getInvoiceNo() {
	return invoiceNo;
}

public void setInvoiceNo(String invoiceNo) {
	this.invoiceNo = invoiceNo;
}
}
