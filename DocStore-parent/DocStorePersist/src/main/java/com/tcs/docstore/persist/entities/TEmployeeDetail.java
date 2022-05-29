package com.tcs.docstore.persist.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the T_EMPLOYEE_DETAILS database table.
 * 
 */
@Entity
@Table(name="T_EMPLOYEE_DETAILS")
@NamedQueries({
	@NamedQuery(name="TEmployeeDetail.findAll", query="SELECT t FROM TEmployeeDetail t"),
	@NamedQuery(name="TEmployeeDetail.findUser", query="SELECT t FROM TEmployeeDetail t where t.tEmpLoginId=:loginId")
})

public class TEmployeeDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="T_EMP_PROFILE_ID")
	private BigDecimal tEmpProfileId;
	
	@Column(name="T_EMP_LOGIN_ID")
	private String tEmpLoginId;

	@Column(name="T_EMP_ADDRESS_LINE1")
	private String tEmpAddressLine1;

	@Column(name="T_EMP_ADDRESS_LINE2")
	private String tEmpAddressLine2;

	@Column(name="T_EMP_ADDRESS_LINE3")
	private String tEmpAddressLine3;

	@Column(name="T_EMP_BRANCH_CODE")
	private String tEmpBranchCode;

	@Column(name="T_EMP_CITY")
	private String tEmpCity;

	@Column(name="T_EMP_COUNTRY")
	private String tEmpCountry;

	@Temporal(TemporalType.DATE)
	@Column(name="T_EMP_CRT_DT")
	private Date tEmpCrtDt;

	@Column(name="T_EMP_CRT_USR_ID")
	private String tEmpCrtUsrId;

	@Column(name="T_EMP_DESIGNATION")
	private String tEmpDesignation;

	@Temporal(TemporalType.DATE)
	@Column(name="T_EMP_DOB")
	private Date tEmpDob;

	@Temporal(TemporalType.DATE)
	@Column(name="T_EMP_EFFCT_END_DT")
	private Date tEmpEffctEndDt;

	@Column(name="T_EMP_EMAIL_ID")
	private String tEmpEmailId;

	@Column(name="T_EMP_FIRST_NAME")
	private String tEmpFirstName;

	@Column(name="T_EMP_GENDER")
	private String tEmpGender;

	@Column(name="T_EMP_LANDLINE_NO")
	private BigDecimal tEmpLandlineNo;

	@Column(name="T_EMP_LAST_NAME")
	private String tEmpLastName;

	@Column(name="T_EMP_MIDDLE_NAME")
	private String tEmpMiddleName;

	@Column(name="T_EMP_MOBILE_NO")
	private BigDecimal tEmpMobileNo;

	@Column(name="T_EMP_PINCODE")
	private BigDecimal tEmpPincode;

	@Temporal(TemporalType.DATE)
	@Column(name="T_EMP_REG_DT")
	private Date tEmpRegDt;

	@Column(name="T_EMP_STATE")
	private String tEmpState;

	@Column(name="T_EMP_TITLE")
	private String tEmpTitle;

	@Temporal(TemporalType.DATE)
	@Column(name="T_EMP_UPD_DT")
	private Date tEmpUpdDt;

	@Column(name="T_EMP_UPD_USR_ID")
	private String tEmpUpdUsrId;

	public TEmployeeDetail() {
	}

	public String getTEmpLoginId() {
		return this.tEmpLoginId;
	}

	public void setTEmpLoginId(String tEmpLoginId) {
		this.tEmpLoginId = tEmpLoginId;
	}

	public String getTEmpAddressLine1() {
		return this.tEmpAddressLine1;
	}

	public void setTEmpAddressLine1(String tEmpAddressLine1) {
		this.tEmpAddressLine1 = tEmpAddressLine1;
	}

	public String getTEmpAddressLine2() {
		return this.tEmpAddressLine2;
	}

	public void setTEmpAddressLine2(String tEmpAddressLine2) {
		this.tEmpAddressLine2 = tEmpAddressLine2;
	}

	public String getTEmpAddressLine3() {
		return this.tEmpAddressLine3;
	}

	public void setTEmpAddressLine3(String tEmpAddressLine3) {
		this.tEmpAddressLine3 = tEmpAddressLine3;
	}

	public String getTEmpBranchCode() {
		return this.tEmpBranchCode;
	}

	public void setTEmpBranchCode(String tEmpBranchCode) {
		this.tEmpBranchCode = tEmpBranchCode;
	}

	public String getTEmpCity() {
		return this.tEmpCity;
	}

	public void setTEmpCity(String tEmpCity) {
		this.tEmpCity = tEmpCity;
	}

	public String getTEmpCountry() {
		return this.tEmpCountry;
	}

	public void setTEmpCountry(String tEmpCountry) {
		this.tEmpCountry = tEmpCountry;
	}

	public Date getTEmpCrtDt() {
		return this.tEmpCrtDt;
	}

	public void setTEmpCrtDt(Date tEmpCrtDt) {
		this.tEmpCrtDt = tEmpCrtDt;
	}

	public String getTEmpCrtUsrId() {
		return this.tEmpCrtUsrId;
	}

	public void setTEmpCrtUsrId(String tEmpCrtUsrId) {
		this.tEmpCrtUsrId = tEmpCrtUsrId;
	}

	public String getTEmpDesignation() {
		return this.tEmpDesignation;
	}

	public void setTEmpDesignation(String tEmpDesignation) {
		this.tEmpDesignation = tEmpDesignation;
	}

	public Date getTEmpDob() {
		return this.tEmpDob;
	}

	public void setTEmpDob(Date tEmpDob) {
		this.tEmpDob = tEmpDob;
	}

	public Date getTEmpEffctEndDt() {
		return this.tEmpEffctEndDt;
	}

	public void setTEmpEffctEndDt(Date tEmpEffctEndDt) {
		this.tEmpEffctEndDt = tEmpEffctEndDt;
	}

	public String getTEmpEmailId() {
		return this.tEmpEmailId;
	}

	public void setTEmpEmailId(String tEmpEmailId) {
		this.tEmpEmailId = tEmpEmailId;
	}

	public String getTEmpFirstName() {
		return this.tEmpFirstName;
	}

	public void setTEmpFirstName(String tEmpFirstName) {
		this.tEmpFirstName = tEmpFirstName;
	}

	public String getTEmpGender() {
		return this.tEmpGender;
	}

	public void setTEmpGender(String tEmpGender) {
		this.tEmpGender = tEmpGender;
	}

	public BigDecimal getTEmpLandlineNo() {
		return this.tEmpLandlineNo;
	}

	public void setTEmpLandlineNo(BigDecimal tEmpLandlineNo) {
		this.tEmpLandlineNo = tEmpLandlineNo;
	}

	public String getTEmpLastName() {
		return this.tEmpLastName;
	}

	public void setTEmpLastName(String tEmpLastName) {
		this.tEmpLastName = tEmpLastName;
	}

	public String getTEmpMiddleName() {
		return this.tEmpMiddleName;
	}

	public void setTEmpMiddleName(String tEmpMiddleName) {
		this.tEmpMiddleName = tEmpMiddleName;
	}

	public BigDecimal getTEmpMobileNo() {
		return this.tEmpMobileNo;
	}

	public void setTEmpMobileNo(BigDecimal tEmpMobileNo) {
		this.tEmpMobileNo = tEmpMobileNo;
	}

	public BigDecimal getTEmpPincode() {
		return this.tEmpPincode;
	}

	public void setTEmpPincode(BigDecimal tEmpPincode) {
		this.tEmpPincode = tEmpPincode;
	}

	public BigDecimal getTEmpProfileId() {
		return this.tEmpProfileId;
	}

	public void setTEmpProfileId(BigDecimal tEmpProfileId) {
		this.tEmpProfileId = tEmpProfileId;
	}

	public Date getTEmpRegDt() {
		return this.tEmpRegDt;
	}

	public void setTEmpRegDt(Date tEmpRegDt) {
		this.tEmpRegDt = tEmpRegDt;
	}

	public String getTEmpState() {
		return this.tEmpState;
	}

	public void setTEmpState(String tEmpState) {
		this.tEmpState = tEmpState;
	}

	public String getTEmpTitle() {
		return this.tEmpTitle;
	}

	public void setTEmpTitle(String tEmpTitle) {
		this.tEmpTitle = tEmpTitle;
	}

	public Date getTEmpUpdDt() {
		return this.tEmpUpdDt;
	}

	public void setTEmpUpdDt(Date tEmpUpdDt) {
		this.tEmpUpdDt = tEmpUpdDt;
	}

	public String getTEmpUpdUsrId() {
		return this.tEmpUpdUsrId;
	}

	public void setTEmpUpdUsrId(String tEmpUpdUsrId) {
		this.tEmpUpdUsrId = tEmpUpdUsrId;
	}

}