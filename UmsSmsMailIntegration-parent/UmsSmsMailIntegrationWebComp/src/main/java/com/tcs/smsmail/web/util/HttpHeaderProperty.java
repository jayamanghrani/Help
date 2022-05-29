/**
 * 
 */
package com.tcs.smsmail.web.util;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author 738566
 *
 */
@Component
public class HttpHeaderProperty {

    /**
	 * 
	 */
    private Map<String, List<String>> property;

    /**
	 * 
	 */
    public HttpHeaderProperty() {
        super();
    }

    /**
     * 
     * @param property
     */
    public HttpHeaderProperty(Map<String, List<String>> property) {
        super();
        this.property = property;
    }

    /**
     * 
     * @return
     */
    public Map<String, List<String>> getProperty() {
        return property;
    }

    /**
     * 
     * @param property
     */
    public void setProperty(Map<String, List<String>> property) {
        this.property = property;
    }

}
