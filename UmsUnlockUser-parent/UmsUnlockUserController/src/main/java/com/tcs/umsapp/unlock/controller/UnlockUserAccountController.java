package com.tcs.umsapp.unlock.controller;

import org.apache.log4j.Logger;

import com.tcs.umsapp.general.vo.cmo.UmsappResponseObject;
import com.tcs.umsapp.unlock.invoker.MailComponentInvoker;
import com.tcs.umsapp.unlock.invoker.MessageComponentInvoker;
import com.tcs.umsapp.unlock.invoker.OAMOIDInvoker;
import com.tcs.umsapp.unlock.invoker.util.OAMOIDUtil;
import com.tcs.umsapp.unlock.persist.dao.UserInfoDao;
import com.tcs.umsapp.unlock.persist.dao.UserInfoDaoImpl;
import com.tcs.umsapp.unlock.request.UnlockAccountRequestASBO;
import com.tcs.umsapp.unlock.request.UserDetailDBRequestASBO;
import com.tcs.umsapp.unlock.response.UnlockAccountResponseASBO;
import com.tcs.umsapp.unlock.response.UserDetailDBResponseASBO;
import com.tcs.umsapp.unlock.so.request.UnlockAccountRequestSO;
import com.tcs.umsapp.unlock.so.response.UnlockAccountResponseSO;
import com.tcs.umsapp.unlock.util.GeneralUtil;
import com.tcs.umsapp.unlock.util.UtilProperties;

public class UnlockUserAccountController {

    private static final Logger LOGGER = Logger
            .getLogger(UnlockUserAccountController.class);

    /**
     * This method is userd for unlockAccount Service
     * 
     * @param unlockAccountRequestSO
     * @return
     */
    public UmsappResponseObject unlockAccount(
            UnlockAccountRequestSO unlockAccountRequestSO) {

        LOGGER.info("Inside unlockAccount");
        int mailStatus = 1;
        int smsStatus = 1;
        /** Generate Random Password*/
        String randomPass = GeneralUtil.getRandomPassword();
        LOGGER.debug("New Password: " + randomPass);
        /** Transformation from SO to ASBO*/
        UnlockAccountRequestASBO unlockAccountRequestASBO = new UnlockAccountRequestASBO();
        unlockAccountRequestASBO.setHeader(unlockAccountRequestSO.getHeader());
        unlockAccountRequestASBO.setUserId(unlockAccountRequestSO.getUserId());
        unlockAccountRequestASBO.setNewPassword(randomPass);

        /** Calling Invoker to Unlock UserAccount*/
        OAMOIDInvoker invoker = new OAMOIDInvoker();
        UnlockAccountResponseASBO unlockAccountResponseASBO = invoker
                .unlockUserAccount(unlockAccountRequestASBO);

        LOGGER.debug("unlockAccountResponseASBO: "
                + unlockAccountResponseASBO.toString());

        /** Dao call for email and mobile number*/
        UserDetailDBRequestASBO userDetailDBRequestASBO = new UserDetailDBRequestASBO();
        userDetailDBRequestASBO.setHeader(unlockAccountRequestASBO.getHeader());
        userDetailDBRequestASBO.setUserId(unlockAccountRequestASBO.getUserId());
        userDetailDBRequestASBO.setPassword(unlockAccountRequestASBO
                .getNewPassword());

        UserInfoDao userInfoDao = new UserInfoDaoImpl();
        UserDetailDBResponseASBO userDetailDBResponseASBO = userInfoDao
                .getUserInfo(userDetailDBRequestASBO);

        LOGGER.info("userDetailDBResponseASBO: "
                + userDetailDBResponseASBO.toString());

        UnlockAccountResponseSO unlockAccountResponseSO = new UnlockAccountResponseSO();
        unlockAccountResponseSO
                .setHeader(unlockAccountResponseASBO.getHeader());
        unlockAccountResponseSO.setStatusCode(unlockAccountResponseASBO
                .getStatusCode());
        unlockAccountResponseSO.setStatusMsg(unlockAccountResponseASBO
                .getStatusMsg());

        if(unlockAccountResponseSO.getStatusCode().equals(OAMOIDUtil.ERROR)){
            LOGGER.error("unlockAccountResponseSO : " + unlockAccountResponseSO.toString());
        	return unlockAccountResponseSO;
        }
        
        /** Calling Mailing Service*/
        if ((userDetailDBResponseASBO.getEmail() != null)
                && (userDetailDBResponseASBO.getEmail().contains(UtilProperties
                        .getMailFilter()))) {
            MailComponentInvoker mailComponentInvoker = new MailComponentInvoker();
            mailStatus = mailComponentInvoker.sendMail(
                    userDetailDBResponseASBO.getEmail(),
                    userDetailDBResponseASBO.getUserId(),
                    userDetailDBRequestASBO.getPassword(),
                    userDetailDBResponseASBO.getFirstName() + " "
                            + userDetailDBResponseASBO.getLastName());
            unlockAccountResponseSO.setMailStatus(mailStatus);
        } else {
            unlockAccountResponseSO.setPassword(randomPass);
        }

        /** Calling SMS Service*/

        if (userDetailDBResponseASBO.getMobile() != null) {
            MessageComponentInvoker messageComponentInvoker = new MessageComponentInvoker();
            smsStatus = messageComponentInvoker.sendMessage(
                    userDetailDBRequestASBO.getUserId(),
                    userDetailDBRequestASBO.getPassword(),
                    userDetailDBResponseASBO.getMobile());
            unlockAccountResponseSO.setSmsStatus(smsStatus);
        }
        LOGGER.info("unlockAccountResponseSO : "
                + unlockAccountResponseSO.toString());

        return unlockAccountResponseSO;
    }
}