package com.tcs.employeeportal.persist.dao;
import com.tcs.employeeportal.persist.entities.TEmployeeDetail;

public interface GetUserDetailsDao {
	/**
	 * Gets the user details.
	 *
	 * @param userId the user id
	 * @param dob the dob
	 * @return the user details
	 */
	public TEmployeeDetail getUserDetails(String userId);


	/**
	 * Gets the user details on email dob.
	 *
	 * @param email the email
	 * @param dob the dob
	 * @return the user details on email dob
	 */
}
