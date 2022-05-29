package com.tcs.umsapp.web.util;

import com.tcs.umsapp.general.vo.cmo.Header;

public class HttpHeaderUtil {

    public HttpHeaderUtil() {
    }

    /**
     * 
     * @param userId
     * @param eventID
     * @return
     */
    public Header generateSpringHeader(String userId, String eventID) {
        Header header = new Header();
        header.setEventID(eventID);
        header.setEmployeeId(userId);
        return header;

    }

}
