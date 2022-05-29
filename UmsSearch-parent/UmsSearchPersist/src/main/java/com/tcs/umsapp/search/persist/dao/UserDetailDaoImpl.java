package com.tcs.umsapp.search.persist.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import com.tcs.umsapp.search.commons.PermissionDetailsCommmon;
import com.tcs.umsapp.search.commons.UserPermissionDetails;
import com.tcs.umsapp.search.commons.UserRoleDetails;
import com.tcs.umsapp.search.commons.UserRoleDetailsXLS;
import com.tcs.umsapp.search.commons.UserSearchDetails;
import com.tcs.umsapp.search.commons.UserSearchPermission;
import com.tcs.umsapp.search.persist.DBUtil;
import com.tcs.umsapp.search.persist.entities.PermissionDetails;
import com.tcs.umsapp.search.persist.entities.UserDetails;
import com.tcs.umsapp.search.persist.entities.UserPermission;
import com.tcs.umsapp.search.persist.query.Queries;
import com.tcs.umsapp.search.request.GetUserPermissionDetailsRequestASBO;
import com.tcs.umsapp.search.request.GetUserSearchDetailsRequestASBO;
import com.tcs.umsapp.search.request.UserPermissionSearchRequestASBO;
import com.tcs.umsapp.search.request.UserRoleSearchRequestASBO;
import com.tcs.umsapp.search.request.UserRoleSearchRequestXLSASBO;
import com.tcs.umsapp.search.so.response.UserOfficeCodeDetails;
import com.tcs.umsapp.search.so.response.UserSearchDetailsSO;
import com.tcs.umsapp.search.util.UtilConstants;
import com.tcs.umsapp.serach.response.UserPermissionResponseASBO;
import com.tcs.umsapp.serach.response.UserPermissionSearchResponseASBO;
import com.tcs.umsapp.serach.response.UserRoleExcelResponseASBO;
import com.tcs.umsapp.serach.response.UserRoleSearchResponseASBO;
import com.tcs.umsapp.serach.response.UserSearchDetailsResponseASBO;

public class UserDetailDaoImpl implements UserDetailDao {

    private static final Logger LOGGER = Logger
            .getLogger(UserDetailDaoImpl.class);
    private final String statusVal = "S";
    //public static String loggedInUserBranchId ;

    @Override
    public UserSearchDetailsResponseASBO getUserDetailsDB(
            GetUserSearchDetailsRequestASBO getUserSearchDetailsRequestASBO) {
        LOGGER.info("inside getUserDetailsDB");
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern(UtilConstants.dateFormatterPattern);
        DateTimeFormatter formatter2 = DateTimeFormatter
                .ofPattern(UtilConstants.dateDisplay);
        UserSearchDetailsResponseASBO getUserSearchDetailsResponseASBO = new UserSearchDetailsResponseASBO();
        EntityManager entityManager = null;
        List<UserSearchDetails> userList = new ArrayList<>();

        String branch = getLoginUserBranch(getUserSearchDetailsRequestASBO
                .getHeader().getEmployeeId());
        LOGGER.info("branch " + branch);
        try {
            entityManager = DBUtil.getEntityManager();

            StringBuilder sb = new StringBuilder();
            sb.append(Queries.UserDetails);

            if (null != getUserSearchDetailsRequestASBO.getApplicationId()) {
                sb.append(" ,ums_usr_appl_map uuam ");
            }

            if (null != getUserSearchDetailsRequestASBO.getRoleId()) {
                sb.append(" ,ums_usr_role_map uurm ");

            }
            sb.append(" where uum.uum_end_date is null and uum.uum_status ='E' ");

            if (null != getUserSearchDetailsRequestASBO.getUserId()
                    && !getUserSearchDetailsRequestASBO.getUserId().isEmpty()) {
                sb.append(" and lower(uum.uum_usr_id) like lower('"
                        + getUserSearchDetailsRequestASBO.getUserId() + "%')");
            }
            
            if (branch.equals("100000")) {
                if (null != getUserSearchDetailsRequestASBO.getBranchId()
                        && !getUserSearchDetailsRequestASBO.getBranchId()
                                .isEmpty()) {
                    sb.append("  and lower(uum.uum_branch_id) like lower('"
                            + getUserSearchDetailsRequestASBO.getBranchId()
                            + "%')");
                }
            }else if(branch.substring(2).equals("0000")){
            	 if((null != getUserSearchDetailsRequestASBO.getBranchId())&&(!getUserSearchDetailsRequestASBO.getBranchId().isEmpty())){
                	if(getUserSearchDetailsRequestASBO.getBranchId().length()>1){
                		if(getUserSearchDetailsRequestASBO.getBranchId().substring(0, 2).equals(branch.substring(0, 2))){
                			sb.append("  and lower(uum.uum_branch_id) like lower('"
                					+ getUserSearchDetailsRequestASBO.getBranchId() + "%')");
                		}else{
                			return getUserSearchDetailsResponseASBO;
                		}
                	}
                }else{
                	String subBranch = branch.substring(0, 2);
                    sb.append("  and lower(uum.uum_branch_id) like lower('"
                            + subBranch + "%')");
                }
            }
            else{
                sb.append("  and lower(uum.uum_branch_id) like lower('"
                        + branch + "%')");
            }
            
            
            
            

            if (null != getUserSearchDetailsRequestASBO.getSupervisorID()
                    && !getUserSearchDetailsRequestASBO.getSupervisorID()
                            .isEmpty()) {
                sb.append("  and lower(uum.uum_supervisor_id) like lower('"
                        + getUserSearchDetailsRequestASBO.getSupervisorID()
                        + "%')");
            }

            if (null != getUserSearchDetailsRequestASBO.getFirstName()
                    && !getUserSearchDetailsRequestASBO.getFirstName()
                            .isEmpty()) {
                sb.append("  and lower(uum.uum_first_name) like lower('"
                        + getUserSearchDetailsRequestASBO.getFirstName()
                        + "%')");
            }
            if (null != getUserSearchDetailsRequestASBO.getLastName()
                    && !getUserSearchDetailsRequestASBO.getLastName().isEmpty()) {
                sb.append("  and lower(uum.uum_last_name) like lower('"
                        + getUserSearchDetailsRequestASBO.getLastName() + "%')");
            }

            if (null != getUserSearchDetailsRequestASBO.getApplicationId()
                    && !getUserSearchDetailsRequestASBO.getApplicationId()
                            .isEmpty()) {
                sb.append("  and uuam.uuam_appl_id='"
                        + getUserSearchDetailsRequestASBO.getApplicationId()
                        + "' and uuam.uuam_usr_id=uum.uum_usr_id and uuam.uuam_end_date is null");
            }

            if (null != getUserSearchDetailsRequestASBO.getRoleId()
                    && !getUserSearchDetailsRequestASBO.getRoleId().isEmpty()) {
                sb.append("  and uurm.uurm_role_id='"
                        + getUserSearchDetailsRequestASBO.getRoleId()
                        + "' and uurm.uurm_usr_id=uum.uum_usr_id and uurm.uurm_end_date is null");
            }

            sb.append(" order by uum.uum_usr_id");

            Query query = entityManager.createNativeQuery(sb.toString());

            @SuppressWarnings("unchecked")
			List<Object[]> result = query.getResultList();
            if (result != null && !result.isEmpty()) {

                Iterator iter = result.iterator();
                while (iter.hasNext()) {
                    Object[] obj = (Object[]) iter.next();
                    UserSearchDetails userSearchDetails = new UserSearchDetails(
                            (obj[0] != null) ? obj[0].toString() : "",
                            (obj[1] != null) ? obj[1].toString() : "",
                            (obj[2] != null) ? obj[2].toString() : "",
                            (obj[3] != null) ? obj[3].toString() : "",
                            (obj[5] != null) ? obj[5].toString() : "",
                            (obj[23] != null) ? obj[23].toString() : "",
                            (obj[6] != null) ? obj[6].toString() : "",
                            ((obj[13] != null) ? LocalDate.parse(
                                    obj[13].toString(), formatter).format(
                                    formatter2) : ""),
                            ((obj[14] != null) ? obj[14].toString() : "")
                                    + " "
                                    + ((obj[15] != null) ? obj[15].toString()
                                            : "")
                                    + " "
                                    + ((obj[16] != null) ? obj[16].toString()
                                            : "")
                                    + " "
                                    + ((obj[9] != null) ? obj[9].toString()
                                            : "")
                                    + " "
                                    + ((obj[10] != null) ? obj[10].toString()
                                            : ""),
                            (obj[7] != null) ? obj[7].toString() : "",
                            (obj[17] != null) ? obj[17].toString() : "",
                            (obj[8] != null) ? obj[8].toString() : "",
                            (obj[20] != null) ? obj[20].toString() : "",
                            (obj[27] != null) ? obj[27].toString() : "");

                    userList.add(userSearchDetails);
                }

            } else {
                LOGGER.info("! ALERT - No Data In Table !");
            }

            entityManager.clear();
            entityManager.close();

        } catch (Exception ex) {
            LOGGER.info("Exception: " + ex.getStackTrace());
            entityManager.close();
        }

        getUserSearchDetailsResponseASBO.setList(userList);

        return getUserSearchDetailsResponseASBO;
    }

    @Override
    public UserRoleSearchResponseASBO getUserRoleDetailsDB(
            UserRoleSearchRequestASBO userRoleSearchRequestASBO) {

        LOGGER.info("inside getUserRoleDetailsDB");

        UserRoleSearchResponseASBO userRoleSearchResponseASBO = new UserRoleSearchResponseASBO();
        EntityManager entityManager = null;

        try {
            entityManager = DBUtil.getEntityManager();

            StringBuilder sb = new StringBuilder();
            sb.append(Queries.UserSearchRoleDetailPost);
            
           
           if((null != userRoleSearchRequestASBO.getBranchId())&&(!userRoleSearchRequestASBO.getBranchId().isEmpty())){
            	sb.append(" and uam.uam_appl_id = 'FINANCIALS' and uurm.uurm_branch_id = '"+userRoleSearchRequestASBO.getBranchId()+"'");
            }
            
            LOGGER.info("DB Query: " + sb.toString());
            Query query = entityManager.createNativeQuery(sb.toString());

            query.setParameter("userId", userRoleSearchRequestASBO.getUserId());

            

            @SuppressWarnings("unchecked")
            List<Object[]> obList = query.getResultList();
            List<UserRoleDetails> result = new ArrayList<>();

            if (!obList.isEmpty()) {

                for (Object[] objects : obList) {
                	UserRoleDetails userRoleDetails = new UserRoleDetails();
                    
                    userRoleDetails.setApplicationName((objects[0] == null ? null : objects[0].toString()));                 
                    userRoleDetails.setRoleName((objects[1] == null ? null : objects[1].toString()));
                    userRoleDetails.setAppId((objects[2] == null ? null : objects[2].toString()));
                    userRoleDetails.setRoleId((objects[3] == null ? null : objects[3].toString()));
                    userRoleDetails.setOfficeCode((objects[4] == null ? null : objects[4].toString()));
                    userRoleDetails.setStartDate((objects[5] == null ? null : objects[5].toString().substring(0,objects[5].toString().indexOf(" "))));
                    userRoleDetails.setEndDate((objects[6] == null ? null : objects[6].toString().substring(0,objects[6].toString().indexOf(" "))));
                    
                    userRoleDetails.setStatus(statusVal);
                    
                    result.add(userRoleDetails);
                }
            }
            LOGGER.info("DB Query: " + result.size());
            // Modification for getting pending requests

            StringBuilder pendingRoleQuey = new StringBuilder();
            pendingRoleQuey.append(Queries.UserRoleInPending);
            
            
            if((null != userRoleSearchRequestASBO.getBranchId())&&(!userRoleSearchRequestASBO.getBranchId().isEmpty())){
            	pendingRoleQuey.append(" AND uam.uam_appl_id = 'FINANCIALS' AND uurm.uurm_branch_id = '"+userRoleSearchRequestASBO.getBranchId()+"'");
            }
            LOGGER.info(" Generated Query : " + pendingRoleQuey.toString());
            
            Query queryPendingRole = entityManager
                    .createNativeQuery(pendingRoleQuey.toString());

            queryPendingRole.setParameter("userId",
                    userRoleSearchRequestASBO.getUserId());

            @SuppressWarnings("unchecked")
            List<Object[]> rsListInObject = queryPendingRole.getResultList();

            LOGGER.info("Pending role size : " + rsListInObject.size());
            if (!rsListInObject.isEmpty()) {
                for (Object[] objects : rsListInObject) {
                    UserRoleDetails userRoleDetails = new UserRoleDetails();
                    
                    userRoleDetails.setApplicationName((objects[0] == null ? null : objects[0].toString()));                 
                    userRoleDetails.setRoleName((objects[1] == null ? null : objects[1].toString()));
                    userRoleDetails.setAppId((objects[2] == null ? null : objects[2].toString()));
                    userRoleDetails.setRoleId((objects[3] == null ? null : objects[3].toString()));
                    userRoleDetails.setStatus((objects[4] == null ? null : objects[4].toString()));
                    userRoleDetails.setOfficeCode((objects[5] == null ? null : objects[5].toString()));
                    userRoleDetails.setStartDate((objects[6] == null ? null : objects[6].toString().substring(0,objects[6].toString().indexOf(" "))));
                    userRoleDetails.setEndDate((objects[7] == null ? null : objects[7].toString().substring(0,objects[7].toString().indexOf(" "))));
                    
                    result.add(userRoleDetails);
                }

                LOGGER.info(" userRoleSearchResponseASBO value: "
                        + userRoleSearchResponseASBO.toString());
            } else {
                LOGGER.info(" No roles in pending mode ");
            }
            userRoleSearchResponseASBO.setStatusList(result);

        } catch (Exception ex) {
            LOGGER.error("error occurred "+ex.getStackTrace());
        } finally {
            try {
                if (null != entityManager) {
                    entityManager.close();
                }
            } catch (Exception e) {
LOGGER.error("error occurred "+e.getStackTrace());
            }
        }

        return userRoleSearchResponseASBO;
    }

    private String getLoginUserBranch(String user) {

        LOGGER.info(" *** Inside method getLoginUserBranch *** ");
        EntityManager entityManager = null;
        UserDetails details = new UserDetails();
        try {
            entityManager = DBUtil.getEntityManager();

            String q = Queries.UserBranch;

            LOGGER.info(" Generated Query " + q);

            LOGGER.info("employeeId " + user);

            details = entityManager.find(UserDetails.class, user);
            LOGGER.info("DB Query object" + details.getBranchId());
            entityManager.close();
        } catch (Exception ex) {
            LOGGER.info("Exception in getLoginUserBranch: "
                    + ex.getStackTrace());
            if (entityManager != null) {
                entityManager.clear();
                entityManager.close();
            }
        }finally {
            try {
                if (null != entityManager) {
                    entityManager.close();
                }
            } catch (Exception e) {
                LOGGER.error("error occurred "+e.getStackTrace());
            }
        }
        return details.getBranchId();
    }

    public UserRoleExcelResponseASBO getUserRoleDetailXLSDB(
            UserRoleSearchRequestXLSASBO userRoleSearchRequestASBO) {

        LOGGER.info("inside getUserRoleDetailXLSDB");

        UserRoleExcelResponseASBO userRoleExcelResponseASBO = new UserRoleExcelResponseASBO();

        EntityManager entityManager = null;

        entityManager = DBUtil.getEntityManager();
try{
        String q = Queries.UserSearchRoleDetailPostXLS;

        Query query = entityManager.createNativeQuery(q);
        //Query query2 = entityManager.createNativeQuery(q);

        List<List<UserRoleDetailsXLS>> result1 = new LinkedList<>();
 
        int n = Math.round((userRoleSearchRequestASBO.getUserId().size()) / 999);
        

        int m = Math
                .round((userRoleSearchRequestASBO.getUserId().size()) % 999);
      
        if (m != 0) {
            n = n + 1;
        }

        List<Object[]> obList = null;
        for (int i = 1; i <= n; i++) {

            if (i == 1) {
                LOGGER.info("Inside IF i==1");
                List<String> firstNElementsList = userRoleSearchRequestASBO
                        .getUserId().stream().limit(999)
                        .collect(Collectors.toList());

                query.setParameter("userId", firstNElementsList);
                obList = query.getResultList();

            } else {
                LOGGER.info("Inside else of IF i==1");
            
                List<String> secondNElementsList = userRoleSearchRequestASBO
                        .getUserId().stream().skip(999 * (i - 1))
                        .collect(Collectors.toList());
                List<String> temp=secondNElementsList.stream().limit(999)
                        .collect(Collectors.toList());
                

                query.setParameter("userId", temp);
                obList.addAll(query.getResultList());
            }
        }

        LOGGER.info("DB Query: " + query.toString());

        List<UserRoleDetailsXLS> result = new LinkedList<>();

        if (!obList.isEmpty()) {

            for (Object[] ob : obList) {
                result.add(new UserRoleDetailsXLS(ob[0].toString(), ob[1]
                        .toString(), ob[2].toString(), ob[3].toString(), ob[4]
                        .toString(), ob[5].toString(), ob[6].toString(), ob[7]
                        .toString(), statusVal));
            }
            result1.add(result);
            userRoleExcelResponseASBO.setStatusList(result1);
        }
        
		        entityManager.close();
		}catch(Exception e)
		{
		    LOGGER.error("Error occurred "+e.getStackTrace());
		}finally {
		    try {
		        if (null != entityManager) {
		            entityManager.close();
		        }
		    } catch (Exception e) {
		        LOGGER.error("error occurred "+e.getStackTrace());
		    }
		}
		        return userRoleExcelResponseASBO;
		    }

    @Override
    public UserPermissionResponseASBO getUserPermissionDetailsDB(
            GetUserPermissionDetailsRequestASBO getUserPermissionDetailsRequestASBO) {

        LOGGER.info("****inside getUserPermissionDetailsDB *****");
        UserPermissionResponseASBO getUserPermissionResponseASBO = new UserPermissionResponseASBO();
        EntityManager entityManager = null;
        PermissionDetailsCommmon permissiondetailsCommon = null;
        List<PermissionDetailsCommmon> permissionCommonlist = new ArrayList<>();
        List<UserPermissionDetails> userperList = new ArrayList<>();
        LOGGER.info("User Id for get Branch Details----: "
                + getUserPermissionDetailsRequestASBO.getHeader()
                        .getEmployeeId());

        String userbranch = getLoginUserBranchDetails(getUserPermissionDetailsRequestASBO
                .getHeader().getEmployeeId());
        LOGGER.info("Value of userbranch " + userbranch);
        try {
            entityManager = DBUtil.getEntityManager();

            CriteriaBuilder criteriaBuilderObj = entityManager
                    .getCriteriaBuilder();
            CriteriaQuery<PermissionDetails> queryObj = criteriaBuilderObj
                    .createQuery(PermissionDetails.class);

            Root<PermissionDetails> root = queryObj
                    .from(PermissionDetails.class);

            List<Predicate> predicates = new ArrayList<>();
            if (userbranch.equals("100000")
                    || getUserPermissionDetailsRequestASBO.getBranchID()
                            .startsWith("10")) {
                String userinputplName = getUserPermissionDetailsRequestASBO
                        .getPlName() + "%";
                LOGGER.info("Value of UserinputplName " + userinputplName);
                Predicate predicateplName = criteriaBuilderObj.like(
                        root.get("plName"), userinputplName);
                predicates.add(predicateplName);
                queryObj.where(predicates.toArray(new Predicate[] {}));
                CriteriaQuery<PermissionDetails> selectQuery = queryObj
                        .select(root);
                TypedQuery<PermissionDetails> typedQuery = entityManager
                        .createQuery(selectQuery);
                List<PermissionDetails> plist = typedQuery.getResultList();
                UserPermissionDetails userPermissionDetails = null;
                if (!plist.isEmpty()) {
                    for (PermissionDetails perdetail : plist) {

                        LOGGER.debug(" perdetail.getPlID() "
                                + perdetail.getPlID());
                        userPermissionDetails = new UserPermissionDetails();
                        userPermissionDetails.setPlID(perdetail.getPlID());
                        userPermissionDetails.setPlName(perdetail.getPlName());
                        userPermissionDetails.setPlDesc(perdetail.getPlDesc());
                        userperList.add(userPermissionDetails);
                    }
                } else {
                    LOGGER.info("! ALERT - No Data In Table !");
                }
                entityManager.clear();
                entityManager.close();
            }

            else {

                LOGGER.info("Value of userbranch " + userbranch);
                String userinputplName = "%"
                        + getUserPermissionDetailsRequestASBO.getPlName() + "%";
                LOGGER.info("Value of UserinputplName " + userinputplName);
                String plID = "NIA_" + userbranch + "_" + "%";
                LOGGER.info("Value of plID " + plID);

                Predicate predicatepIID = criteriaBuilderObj.like(
                        root.get("plID"), plID);
                predicates.add(predicatepIID);
                Predicate predicateplName = criteriaBuilderObj.like(
                        root.get("plName"), userinputplName);
                predicates.add(predicateplName);
                queryObj.where(predicates.toArray(new Predicate[] {}));

                CriteriaQuery<PermissionDetails> selectQuery = queryObj
                        .select(root);
                TypedQuery<PermissionDetails> typedQuery = entityManager
                        .createQuery(selectQuery);

                List<PermissionDetails> plist = typedQuery.getResultList();
                UserPermissionDetails userPermissionDetails = null;
                if (plist != null && !plist.isEmpty()) {
                    for (Object obj : plist) {
                        PermissionDetails perdetail = (PermissionDetails) obj;
                        userPermissionDetails = new UserPermissionDetails(
                                perdetail.getPlID(), perdetail.getPlName(),
                                perdetail.getPlDesc(), perdetail.getStDate(),
                                perdetail.getEndDate(),
                                perdetail.getCreatedBY(),
                                perdetail.getCreatedDate(),
                                perdetail.getModifiedBY(),
                                perdetail.getModifiedDate());

                        userperList.add(userPermissionDetails);
                    }
                    LOGGER.info("value of userperList in else: "
                            + userperList.size());
                } else {
                    LOGGER.info("! ALERT - No Data In Table !");
                }
                entityManager.close();
            }

        } catch (Exception ex) {
            LOGGER.info("Exception: " + ex.getMessage());
            entityManager.close();
        }finally {
            try {
                if (null != entityManager) {
                    entityManager.close();
                }
            } catch (Exception e) {
                LOGGER.error("error occurred "+e.getStackTrace());
            }
        }

        for (UserPermissionDetails uobj : userperList) {
            permissiondetailsCommon = new PermissionDetailsCommmon();
            permissiondetailsCommon.setPlID(uobj.getPlID());
            permissiondetailsCommon.setPlName(uobj.getPlName());
            permissiondetailsCommon.setPlDesc(uobj.getPlDesc());
            permissionCommonlist.add(permissiondetailsCommon);
        }
        getUserPermissionResponseASBO.setDetails(permissionCommonlist);
        return getUserPermissionResponseASBO;
    }

    private String getLoginUserBranchDetails(String employeeId) {

        LOGGER.info(" *** Inside method getLoginUserBranch *** ");
        EntityManager entityManager = null;
        UserDetails details = new UserDetails();
        try {
            entityManager = DBUtil.getEntityManager();

            String q = Queries.UserBranch;

            LOGGER.debug(" Generated Query " + q);
            LOGGER.info("employeeId " + employeeId);

            details = entityManager.find(UserDetails.class, employeeId);
            entityManager.clear();
            entityManager.close();

        } catch (Exception ex) {
            LOGGER.info("Exception in getLoginUserBranch: "
                    + ex.getStackTrace());
            if (entityManager != null) {
                entityManager.clear();
                entityManager.close();
            }
        }finally {
            try {
                if (null != entityManager) {
                    entityManager.close();
                }
            } catch (Exception e) {
                LOGGER.error("error occurred "+e.getStackTrace());
            }
        }
        return details.getBranchId();
    }

    @Override
    public UserPermissionSearchResponseASBO getUserSearchPermissionDetailsDB(
            UserPermissionSearchRequestASBO getUserPermissionSearchRequestASBO) {
        LOGGER.info("***inside getUserSearchPermissionDetailsDB****");
        UserPermissionSearchResponseASBO userPermissionSearchResponseASBO = new UserPermissionSearchResponseASBO();
        List<UserPermission> pList = new ArrayList<>();
        UserSearchPermission userSearchPermissionobj = new UserSearchPermission();
        List<UserSearchPermission> userSearchPermissionList = new ArrayList<>();
        EntityManager entityManager = null;

        entityManager = DBUtil.getEntityManager();
       try{
        String userID = getUserPermissionSearchRequestASBO.getUserId();
        String q = Queries.UserSearchPermissionDetailPost;
        LOGGER.info(" Generated Query for UserSearchPermissionDetailPost --"
                + q);
        Query query = entityManager.createNativeQuery(q);

        LOGGER.info("UserID As input : " + userID);
        query.setParameter("userId", userID);

        LOGGER.debug("DB Query object" + query);
        @SuppressWarnings("unchecked")
        List<Object[]> obList = query.getResultList();
        LOGGER.info("Query Result : " + obList.size());
        if (!obList.isEmpty()) {
            for (Object[] ob : obList) {
                if ((!(ob[0].toString().isEmpty())) && (ob[1] != null)) {
                    pList.add(new UserPermission(ob[0].toString(), ob[1]
                            .toString()));
                } else {
                    pList.add(new UserPermission(userID, "null"));
                }
                LOGGER.info(" Permission has been found againts,User"
                        + pList.toString());
            }
        } else {
            LOGGER.info("QueryResult-" + q
                    + "No Permission has been found againts,User" + userID);

        }
        for (UserPermission userpermission : pList) {
            userSearchPermissionobj.setUserId(userpermission.getUserId());
            userSearchPermissionobj.setUserPermissionList(userpermission
                    .getUserPermissionList());
            userSearchPermissionList.add(userSearchPermissionobj);
        }

        userPermissionSearchResponseASBO
                .setUserpermission(userSearchPermissionList);
        entityManager.clear();
        entityManager.close();
       }catch(Exception e)
       {
           LOGGER.info("error occurred "+e.getStackTrace());
       }finally {
           try {
               if (null != entityManager) {
                   entityManager.close();
               }
           } catch (Exception e) {
               LOGGER.error("error occurred "+e.getStackTrace());
           }
       }
        
        return userPermissionSearchResponseASBO;
    }

    @Override
    public UserSearchDetailsSO getLoginUserDetails(String loginId) {
    	
        UserSearchDetailsSO detailsSO = new UserSearchDetailsSO();
        LOGGER.info(" *** Inside method getLoginUserBranch *** ");
        EntityManager entityManager = null;
        UserDetails details = new UserDetails();
        try {
            entityManager = DBUtil.getEntityManager();
            LOGGER.info("employeeId " + loginId);
            details = entityManager.find(UserDetails.class, loginId);
            LOGGER.info("DB Query object" + details.getBranchId());
            detailsSO.setFirstName(details.getFirstName());
            detailsSO.setBranchId(details.getBranchId());
           // UserDetailDaoImpl.loggedInUserBranchId=details.getBranchId();
            detailsSO.setLastName(details.getLastName());
            detailsSO.setUserId(loginId);
            entityManager.close();
        } catch (Exception ex) {
            LOGGER.info("Exception in getLoginUserBranch: "
                    + ex.getStackTrace());
            if (entityManager != null) {
                entityManager.clear();
                entityManager.close();
            }
        }finally {
            try {
                if (null != entityManager) {
                    entityManager.close();
                }
            } catch (Exception e) {
                LOGGER.error("error occurred "+e.getStackTrace());
            }
        }
        return detailsSO;
    }
    
    
    @Override
	public UserOfficeCodeDetails getOfficeCodeDetails(String branchId) {
		
		UserOfficeCodeDetails userOfficeCodeDetails = new UserOfficeCodeDetails();
		LOGGER.info(" *** Inside method getOfficeCodeDetails *** ");
	    EntityManager entityManager = null;
	    
	    try {
            entityManager = DBUtil.getEntityManager();
            StringBuilder query = new StringBuilder().append(Queries.AllOfficeCodes);
            
			  if (branchId.equals("100000")) {
				  LOGGER.info("HO branch request");
	          }else if(branchId.substring(2).equals("0000")){
	        	 query.append(" where lower (uum.UUM_BRANCH_ID) like lower('"
                            + branchId.substring(0,2) + "%')");  
	          }
	          else{
	        	 query.append(" where lower (uum.UUM_BRANCH_ID) like lower('"
                          + branchId + "%')");
	          }
			  
			  query.append(" order by uum.UUM_BRANCH_ID ");
			  
			  Query queryJPA = entityManager.createNativeQuery(query.toString());

              @SuppressWarnings("unchecked")
              List<Object> resultList = queryJPA.getResultList();
              List<String> officeCodeList = new ArrayList<String>();
              if (resultList != null && !resultList.isEmpty()) {
            	 for(Object officeCode : resultList){
            		 officeCodeList.add(officeCode.toString());
            	 }
              }
              LOGGER.info("Office codes size : " + officeCodeList.size());
              userOfficeCodeDetails.setOfficeCodes(officeCodeList);
              
			  
	    } catch (Exception ex) {
            LOGGER.info("Exception in getLoginUserBranch: "
                    + ex.getStackTrace());
            if (entityManager != null) {
                entityManager.clear();
                entityManager.close();
            }
        }finally {
            try {
                if (null != entityManager) {
                    entityManager.close();
                }
            } catch (Exception e) {
                LOGGER.error("error occurred "+e.getStackTrace());
            }
        }
        return userOfficeCodeDetails;
	}

	@Override
	public UserRoleSearchResponseASBO getUserFinanceRoleDetailsDB(
			UserRoleSearchRequestASBO userRoleSearchRequestASBO) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}