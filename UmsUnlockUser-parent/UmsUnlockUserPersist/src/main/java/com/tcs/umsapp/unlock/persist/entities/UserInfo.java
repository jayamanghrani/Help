package com.tcs.umsapp.unlock.persist.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="UMS_USR_MST")
public class UserInfo {
	
	@Id
	@Column(name = "UUM_USR_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String userId;
	
	
	@Column(name = "UUM_FIRST_NAME")
	private String firstName;
	
	@Column(name = "UUM_LAST_NAME")
	private String lastName;
	
	@Column(name = "UUM_GENDER")
	private String gender;
	
	@Column(name = "UUM_EMAIL")
	private String email;
	
	@Column(name = "UUM_MOBILE")
	private String mobile;

	
	/**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }


    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }


    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }


    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }


    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }


    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }


    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }


    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }


    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    @Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", gender=" + gender + ", email="
				+ email + ", mobile=" + mobile + "]";
	}
	
	
	

}
