package com.tcs.umsapp.general.controller;

import org.apache.log4j.Logger;

import com.tcs.umsapp.general.cache.RoleCacheService;
import com.tcs.umsapp.general.response.RoleDetailDBResponseASBO;
import com.tcs.umsapp.general.vo.cmo.Header;
import com.tcs.umsapp.general.vo.cmo.UmsappResponseObject;

public class RoleDetailController {

    private static final Logger LOGGER = Logger
            .getLogger(RoleDetailController.class);

    public RoleDetailController() {

    }

    // This controller user for service getRoleDetailPost
    public UmsappResponseObject getRoleList(Header header, String userId) {
        LOGGER.debug("getRoleList Method Called");

        RoleCacheService cacheService = new RoleCacheService();
        RoleDetailDBResponseASBO response = cacheService.cacheRoleDetails(userId);
        response.setHeader(header);
        return response;

    }

}
