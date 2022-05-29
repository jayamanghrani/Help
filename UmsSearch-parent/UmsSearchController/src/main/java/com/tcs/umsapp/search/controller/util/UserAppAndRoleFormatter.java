package com.tcs.umsapp.search.controller.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.tcs.umsapp.search.commons.UserRoleDetails;
import com.tcs.umsapp.search.so.response.UserAppAndRoleAccessDetails;
import com.tcs.umsapp.serach.response.UserRoleSearchResponseASBO;

public class UserAppAndRoleFormatter {
	
	private static Logger LOGGER = Logger.getLogger(UserAppAndRoleFormatter.class);

	public UserAppAndRoleAccessDetails getFormattedList(
            UserRoleSearchResponseASBO userRoleSearchResponseASBO) {
        UserAppAndRoleAccessDetails userAppAndRoleAccessDetails =new UserAppAndRoleAccessDetails();

        Map<String, List<UserRoleDetails>> mapList = new HashMap<>();
        Set<String> appIdSet= new HashSet<>();

        userAppAndRoleAccessDetails.setHeader(userRoleSearchResponseASBO.getHeader());
        List<UserRoleDetails> statusList = userRoleSearchResponseASBO.getStatusList();
        LOGGER.debug("complete list : :  "+ statusList);
        if(statusList != null){
        for (UserRoleDetails userSingleRole : statusList) {         
            appIdSet.add(userSingleRole.getApplicationName());
        }
        LOGGER.debug("App ID complete list : :  "+ appIdSet);

        Iterator<String> iterator = appIdSet.iterator();
            while(iterator.hasNext()){
                String tempAppID = iterator.next();
                LOGGER.debug("Adding role for : "+ tempAppID);
                List<UserRoleDetails> totalTempRole = new ArrayList<>();
                for (UserRoleDetails userSingleRole : statusList) {                         
                    if(tempAppID.equalsIgnoreCase(userSingleRole.getApplicationName())){
                        totalTempRole.add(userSingleRole);
                    }
                }
                if(!totalTempRole.isEmpty()){
                    mapList.put(tempAppID, totalTempRole);
                }
            }

        }

        LOGGER.info("Map value of complete list : "+ mapList.size());
        userAppAndRoleAccessDetails.setAppAndRoleAccessDetails(mapList);
        return userAppAndRoleAccessDetails;
    }
}


