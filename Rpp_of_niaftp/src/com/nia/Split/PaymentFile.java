package com.nia.Split;

public class PaymentFile {
	
	private String Bnakfilename;	
	private String  MerchantCode;	
	private String  PaymnetDate;
	private String  SequenceNumeber;
	/**
	 * @return the bnakfilename
	 */
	public String getBnakfilename() {
		return Bnakfilename;
	}
	/**
	 * @param bnakfilename the bnakfilename to set
	 */
	public void setBnakfilename(String bnakfilename) {
		Bnakfilename = bnakfilename;
	}
	/**
	 * @return the merchantCode
	 */
	public String getMerchantCode() {
		return MerchantCode;
	}
	/**
	 * @param merchantCode the merchantCode to set
	 */
	public void setMerchantCode(String merchantCode) {
		MerchantCode = merchantCode;
	}
	/**
	 * @return the paymnetDate
	 */
	public String getPaymnetDate() {
		return PaymnetDate;
	}
	/**
	 * @param paymnetDate the paymnetDate to set
	 */
	public void setPaymnetDate(String paymnetDate) {
		PaymnetDate = paymnetDate;
	}
	/**
	 * @return the sequenceNumeber
	 */
	public String getSequenceNumeber() {
		return SequenceNumeber;
	}
	/**
	 * @param sequenceNumeber the sequenceNumeber to set
	 */
	public void setSequenceNumeber(String sequenceNumeber) {
		SequenceNumeber = sequenceNumeber;
	}
	public PaymentFile(String bnakfilename, String merchantCode,
			String paymnetDate, String sequenceNumeber) {
		super();
		Bnakfilename = bnakfilename;
		MerchantCode = merchantCode;
		PaymnetDate = paymnetDate;
		SequenceNumeber = sequenceNumeber;
	}
	public PaymentFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaymentFile [Bnakfilename=" + Bnakfilename + ", MerchantCode="
				+ MerchantCode + ", PaymnetDate=" + PaymnetDate
				+ ", SequenceNumeber=" + SequenceNumeber + "]";
	}
	
	
	

}
