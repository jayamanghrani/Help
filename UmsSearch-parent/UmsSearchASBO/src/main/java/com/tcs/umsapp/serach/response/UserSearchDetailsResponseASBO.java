package com.tcs.umsapp.serach.response;

import java.util.List;

import com.tcs.umsapp.search.commons.UserSearchDetails;
import com.tcs.umsapp.search.vo.cmo.UmsappResponseObject;

public class UserSearchDetailsResponseASBO extends UmsappResponseObject {

    private static final long serialVersionUID = -5124191866802190620L;

    /** The result. */
    private List<UserSearchDetails> list;

    /**
     * @return the list
     */
    public List<UserSearchDetails> getList() {
        return list;
    }

    /**
     * @param list
     *            the list to set
     */
    public void setList(List<UserSearchDetails> list) {
        this.list = list;
    }

}
