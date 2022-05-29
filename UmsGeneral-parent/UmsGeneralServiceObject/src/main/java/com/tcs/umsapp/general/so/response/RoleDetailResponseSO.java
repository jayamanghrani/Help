package com.tcs.umsapp.general.so.response;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import com.tcs.umsapp.general.vo.cmo.UmsappResponseObject;

public class RoleDetailResponseSO extends UmsappResponseObject {

    private static final long serialVersionUID = -2053400209698556650L;

    ConcurrentMap<String, List<String>> result;

    public ConcurrentMap<String, List<String>> getResult() {
        return result;
    }

    public void setResult(ConcurrentMap<String, List<String>> result) {
        this.result = result;
    }

}
