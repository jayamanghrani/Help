package com.tcs.umsapp.search.persist.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.tcs.umsapp.search.commons.RequestTrackAppRoleDetail;
import com.tcs.umsapp.search.commons.RequestTrackDetail;
import com.tcs.umsapp.search.commons.RequestTrackDetailXLS;
import com.tcs.umsapp.search.persist.DBUtil;
import com.tcs.umsapp.search.persist.query.Queries;
import com.tcs.umsapp.search.request.RequestTrackAppRoleDBRequestASBO;
import com.tcs.umsapp.search.request.RequestTrackDBReqeustASBO;
import com.tcs.umsapp.search.request.RequestTrackXLSRequestASBO;
import com.tcs.umsapp.search.util.UtilConstants;
import com.tcs.umsapp.serach.response.RequestTrackAppRoleDBResponseASBO;
import com.tcs.umsapp.serach.response.RequestTrackDBResponseASBO;
import com.tcs.umsapp.serach.response.RequestTrackXLSResponseASBO;

public class RequestTrackDaoImpl implements RequestTrackDao {

	private static final Logger LOGGER = Logger
			.getLogger(RequestTrackDaoImpl.class);

	/** 
	 * 
	 */
	@Override
	public RequestTrackDBResponseASBO getRequestTrackDetails(
			RequestTrackDBReqeustASBO requestTrackDBReqeustASBO , String loginUserBranchId) {

		LOGGER.info(" *** Inside method getRequestTrackDetails *** ");

		RequestTrackDBResponseASBO requestTrackDBResponseASBO = new RequestTrackDBResponseASBO();
		EntityManager entityManager = null;

		try {
			entityManager = DBUtil.getEntityManager();
			DateTimeFormatter formatter = DateTimeFormatter
					.ofPattern(UtilConstants.datePattern);
			DateTimeFormatter formatter2 = DateTimeFormatter
					.ofPattern(UtilConstants.dateDisplay);
			StringBuilder stringBuilder = new StringBuilder();

			stringBuilder.append(Queries.RequestTrackDetailPost);

			if (requestTrackDBReqeustASBO.getRequestId() != null
					&& !requestTrackDBReqeustASBO.getRequestId().isEmpty()) {
				stringBuilder.append(" and urm.urqm_request_id = :requestId ");
			}

			if (requestTrackDBReqeustASBO.getUserId() != null
					&& !requestTrackDBReqeustASBO.getUserId().isEmpty()) {
				stringBuilder.append( " and upd.upd_usr_id = :userId ");
			}
			if (requestTrackDBReqeustASBO.getRequestBy() != null
					&& !requestTrackDBReqeustASBO.getRequestBy().isEmpty()) {
				stringBuilder.append(" and upd. upd_created_by = :requestBy " );
			}
			if (requestTrackDBReqeustASBO.getRequestDateFrom() != null
					&& !requestTrackDBReqeustASBO.getRequestDateFrom()
					.isEmpty()) {
				stringBuilder.append( " and urm.urqm_requested_date >= :requestDateFrom ");
			}
			if (requestTrackDBReqeustASBO.getRequestDateTo() != null
					&& !requestTrackDBReqeustASBO.getRequestDateTo().isEmpty()) {
				stringBuilder.append(" and urm.urqm_requested_date <= :requestDateTo ");
			}

			if (requestTrackDBReqeustASBO.getApplication() != null
					&& !requestTrackDBReqeustASBO.getApplication().isEmpty()) {
				stringBuilder.append( " and upd.UPD_APPL_ID = :application ");
			}

			if (requestTrackDBReqeustASBO.getRequestStatus() != null
					&& !requestTrackDBReqeustASBO.getRequestStatus().isEmpty()) {
				stringBuilder.append( " and upd.UPD_PROV_STATUS = :requestStatus " );
			}


			if (!loginUserBranchId.equalsIgnoreCase("100000") && loginUserBranchId.substring(2).equalsIgnoreCase("0000")) {
				stringBuilder.append("  and uum.uum_branch_id like '" + loginUserBranchId.substring(0,2)+ "%'");  
			}
			else if(!loginUserBranchId.equalsIgnoreCase("100000") && !loginUserBranchId.substring(2).equalsIgnoreCase("0000")){
				stringBuilder.append("  and uum.uum_branch_id like '" + loginUserBranchId+ "%'");
			}

			stringBuilder.append( " order by urm.urqm_request_id desc ");

			LOGGER.info(" Generated Query " + stringBuilder.toString());

			Query query = entityManager.createNativeQuery(stringBuilder.toString());

			LOGGER.info("DB Query object" + query);

			if (requestTrackDBReqeustASBO.getRequestId() != null
					&& !requestTrackDBReqeustASBO.getRequestId().isEmpty()) {
				query.setParameter("requestId",
						requestTrackDBReqeustASBO.getRequestId());
			}

			if (requestTrackDBReqeustASBO.getUserId() != null
					&& !requestTrackDBReqeustASBO.getUserId().isEmpty()) {
				query.setParameter("userId",
						requestTrackDBReqeustASBO.getUserId());
			}
			if (requestTrackDBReqeustASBO.getRequestBy() != null
					&& !requestTrackDBReqeustASBO.getRequestBy().isEmpty()) {
				query.setParameter("requestBy",
						requestTrackDBReqeustASBO.getRequestBy());
			}
			if (requestTrackDBReqeustASBO.getRequestDateFrom() != null
					&& !requestTrackDBReqeustASBO.getRequestDateFrom()
					.isEmpty()) {
				query.setParameter(
						"requestDateFrom",
						LocalDate.parse(
								requestTrackDBReqeustASBO.getRequestDateFrom(),
								formatter).format(formatter2));
			}

			if (requestTrackDBReqeustASBO.getRequestDateTo() != null
					&& !requestTrackDBReqeustASBO.getRequestDateTo().isEmpty()) {
				query.setParameter(
						"requestDateTo",
						LocalDate.parse(
								requestTrackDBReqeustASBO.getRequestDateTo(),
								formatter).format(formatter2));
			}

			if (requestTrackDBReqeustASBO.getApplication() != null
					&& !requestTrackDBReqeustASBO.getApplication().isEmpty()) {
				query.setParameter("application",
						requestTrackDBReqeustASBO.getApplication());
			}

			if (requestTrackDBReqeustASBO.getRequestStatus() != null
					&& !requestTrackDBReqeustASBO.getRequestStatus().isEmpty()) {
				query.setParameter("requestStatus",
						requestTrackDBReqeustASBO.getRequestStatus());
			}

			@SuppressWarnings("unchecked")
			List<Object[]> obList = query.getResultList();

			if (!obList.isEmpty()) {

				List<RequestTrackDetail> result = new LinkedList<>();

				for (Object[] ob : obList) {
					RequestTrackDetail reqTrackDetail = new RequestTrackDetail(
							(null != ob[0]) ? ob[0].toString() : "",
									(null != ob[1]) ? ob[1].toString() : "",
											(null != ob[2]) ? ob[2].toString() : "",
													(null != ob[3]) ? ob[3].toString() : "",
															(null != ob[4]) ? ob[4].toString() : "",
																	(null != ob[5]) ? ob[5].toString() : "");
					result.add(reqTrackDetail);
				}

				requestTrackDBResponseASBO.setList(result);
				LOGGER.info(" RequestTrackDBResponseASBO value: "
						+ requestTrackDBResponseASBO.toString());

			}
		} catch (Exception ex) {
			LOGGER.info("Exception inside getRequestTrackDetails"
					+ ex.getMessage());
			LOGGER.error(ex.getStackTrace(),ex);
		} finally {
			try {
				if (null != entityManager) {
					entityManager.close();
					LOGGER.info("Entity Manager is Closed");
				}
			} catch (Exception e) {
				LOGGER.info("Exception in finally block");

			}
		}

		return requestTrackDBResponseASBO;
	}

	/**
	 * This method used for service getRequestTrackerAppRoleDetailPost
	 */
	@Override
	public RequestTrackAppRoleDBResponseASBO getRequestTrackAppRoleDetails(
			RequestTrackAppRoleDBRequestASBO requestTrackAppRoleDBRequestASBO) {

		RequestTrackAppRoleDBResponseASBO requestTrackAppRoleDBResponseASBO = new RequestTrackAppRoleDBResponseASBO();
		EntityManager entityManager = null;

		try {
			entityManager = DBUtil.getEntityManager();

			//String q = Queries.RequestTrackAppRoleDetailPost;
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(Queries.RequestTrackAppRoleDetailPost);


			if (null != requestTrackAppRoleDBRequestASBO.getStatus() && !requestTrackAppRoleDBRequestASBO.getStatus().isEmpty()) {
				if(requestTrackAppRoleDBRequestASBO.getStatus().equalsIgnoreCase("R")){
					stringBuilder.append(" AND (upd.upd_prov_status =:status or upd.upd_prov_status='I') ");
				}
				else{
					stringBuilder.append(" AND upd.upd_prov_status = :status ");
				}
			}

			Query query = entityManager.createNativeQuery(stringBuilder.toString());

			if (requestTrackAppRoleDBRequestASBO.getRequestId() != null
					&& !requestTrackAppRoleDBRequestASBO.getRequestId()
					.isEmpty()) {
				query.setParameter("requestId",
						requestTrackAppRoleDBRequestASBO.getRequestId());
			}

			if (requestTrackAppRoleDBRequestASBO.getUserId() != null
					&& !requestTrackAppRoleDBRequestASBO.getUserId().isEmpty()) {
				query.setParameter("userId",
						requestTrackAppRoleDBRequestASBO.getUserId());
			}
			if (null != requestTrackAppRoleDBRequestASBO.getStatus() && !requestTrackAppRoleDBRequestASBO.getStatus().isEmpty()) {
				query.setParameter("status" , requestTrackAppRoleDBRequestASBO.getStatus());
			}

			@SuppressWarnings("unchecked")
			List<Object[]> obList = query.getResultList();

			if (!obList.isEmpty()) {

				List<RequestTrackAppRoleDetail> result = new LinkedList<>();

				for (Object[] ob : obList) {
					RequestTrackAppRoleDetail reqTrackDetail = new RequestTrackAppRoleDetail(
							ob[1].toString(), ob[2].toString(),
							ob[3].toString(), ob[4].toString(),
							ob[5].toString(), ob[6].toString(), ob[7]!=null?ob[7].toString():null);
					result.add(reqTrackDetail);

				}
				requestTrackAppRoleDBResponseASBO.setList(result);
				LOGGER.info(" getRequestTrackAppRoleDetails value: "
						+ requestTrackAppRoleDBResponseASBO.toString());

			}

		} catch (Exception ex) {
			LOGGER.error("error ocurred" + ex);
			LOGGER.error(ex.getStackTrace(),ex);
		}finally {
			try {
				if (null != entityManager) {
					entityManager.close();
				}
			} catch (Exception e) {
				LOGGER.error("error occurred "+e);
			}
		}

		return requestTrackAppRoleDBResponseASBO;
	}

	/** 
	 * 
	 */
	@Override
	public RequestTrackXLSResponseASBO getRequestTrackXLS(
			RequestTrackXLSRequestASBO requestTrackXLSRequestASBO) {
		RequestTrackXLSResponseASBO requestTrackXLSResponseASBO = new RequestTrackXLSResponseASBO();
		EntityManager entityManager = null;
		try {
			entityManager = DBUtil.getEntityManager();

			String q = Queries.RequestTrackXLSQuery;

			String temp = requestTrackXLSRequestASBO.getUserRequestId()
					.toString();
			LOGGER.info("test string value " + temp);
			temp = temp.replace("[", "(");
			temp = temp.replace("]", ")");
			LOGGER.info("test string value " + temp);

			q = q
					+ " and (upd.upd_usr_id,upd.upd_request_id) IN "
					+ temp
					+ " and urm.urm_status like 'E' and urem.urqm_request_id = upd.upd_request_id and uum.uum_end_date is null and uum.uum_status ='E' and uum.uum_usr_id = upd.upd_usr_id";

			LOGGER.info("q value" + q);
			LOGGER.info("after  entityManager");

			Query query = entityManager.createNativeQuery(q);

			LOGGER.info("after  createNativeQuery" + query);

			List<List<RequestTrackDetailXLS>> result1 = new LinkedList<>();

			LOGGER.info("after RequestTrackDetailXLS");

			List<Object[]> obList = query.getResultList();
			LOGGER.info("after userRequestId"
					+ requestTrackXLSRequestASBO.getUserRequestId().toString());
			LOGGER.info("DB Query: " + temp);
			LOGGER.info("requestTrackXLSRequestASBO :"
					+ requestTrackXLSRequestASBO.toString());
			LOGGER.info("after userRequestId"
					+ requestTrackXLSRequestASBO.getUserRequestId());
			LOGGER.info("DB Query: " + query.toString());
			List<RequestTrackDetailXLS> result = new LinkedList<>();
			LOGGER.info("oblist" + obList);
			if (!obList.isEmpty()) {

				for (Object[] ob : obList) {
					result.add(new RequestTrackDetailXLS(ob[0].toString(),
							ob[1].toString(), ob[2].toString(), ob[3]
									.toString(), ob[4].toString(), ob[5]
											.toString(), ob[6].toString(), ob[7]
													.toString(), ob[8].toString(), ob[9]
															.toString(), ob[10].toString(), ob[11]
																	.toString()));
					LOGGER.info("RequestTrackDetailXLS result: " + result);

				}
				result1.add(result);
				LOGGER.info("result1 " + result1);
			}
			requestTrackXLSResponseASBO.setStatusList(result1);
		}

		catch (Exception ex) {
			LOGGER.error("Error occurred " + ex);
			LOGGER.error(ex.getStackTrace(),ex);

		} finally {
			try {
				if (null != entityManager) {
					entityManager.close();
				}
			} catch (Exception e) {
				LOGGER.error("error occurred "+e);
			}
		}
		return requestTrackXLSResponseASBO;
	}

	@Override
	public String getLoginUserBranch(String loginUserId) {
		String branchIdLogin=null;
		EntityManager entityManager = null;
		try {
			entityManager = DBUtil.getEntityManager();
			Query query = entityManager.createNativeQuery("select distinct UUM_BRANCH_ID from UMS_USR_MST where uum_usr_id=:loginUserId");

			query.setParameter("loginUserId", loginUserId);
			List resultList = query.getResultList();

			if(!resultList.isEmpty()){
				branchIdLogin = (String) resultList.get(0);
			}

			entityManager.close();
		} catch (Exception ex) {
			LOGGER.info("Exception in getLoginUserBranch: "
					+ ex.getStackTrace());
			LOGGER.error(ex.getStackTrace(),ex);
		}finally {
			try {
				if (null != entityManager) {
					entityManager.close();
				}
			} catch (Exception e) {
				LOGGER.error("error occurred "+e.getStackTrace());
			}
		}



		return branchIdLogin;

	}
}