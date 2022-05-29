package com.tcs.rpp.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    /**
     * Gets the property.
     * 
     * @param property
     *            the property
     * @return the property
     */
    public static String getConfigProperty(String property) {
    
        Properties prop = new Properties();
        InputStream input = null;
        try {

            input = PropertiesUtil.class.getClassLoader().getResourceAsStream("rppConfig");
            prop.load(input);
            return prop.getProperty(property);
        } catch (IOException ex) {
            System.out.println("Failed to read properties file");
            ex.printStackTrace();
        
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    System.out.println("Failed to read properties file");
                    ex.printStackTrace();
                }
            }
        }
        return property;
    }
}
