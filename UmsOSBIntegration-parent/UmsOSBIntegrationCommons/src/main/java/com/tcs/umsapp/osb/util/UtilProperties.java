package com.tcs.umsapp.osb.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class UtilProperties {
    private static final Logger LOGGER = Logger.getLogger(UtilProperties.class);
    public static String ackUrl;
    public static String osbUrl;
    public static int Hour_schedular1;
    public static int Minute_schedular1;
    public static int Hour_schedular2;
    public static int Minute_schedular2;
    
    

    public static String getAckUrl() {
        return ackUrl;
    }


    public static void setAckUrl(String ackUrl) {
        UtilProperties.ackUrl = ackUrl;
    }


    public static String getOsbUrl() {
        return osbUrl;
    }


    public static void setOsbUrl(String osbUrl) {
        UtilProperties.osbUrl = osbUrl;
    }
    
    public static int getHour_schedular1() {
		return Hour_schedular1;
	}


	public static void setHour_schedular1(int hour_schedular1) {
		Hour_schedular1 = hour_schedular1;
	}


	public static int getMinute_schedular1() {
		return Minute_schedular1;
	}


	public static void setMinute_schedular1(int minute_schedular1) {
		Minute_schedular1 = minute_schedular1;
	}


	public static int getHour_schedular2() {
		return Hour_schedular2;
	}


	public static void setHour_schedular2(int hour_schedular2) {
		Hour_schedular2 = hour_schedular2;
	}


	public static int getMinute_schedular2() {
		return Minute_schedular2;
	}


	public static void setMinute_schedular2(int minute_schedular2) {
		Minute_schedular2 = minute_schedular2;
	}


	public static void load(String path) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(path)));

            if (properties.getProperty("ackUrl") != null) {
                ackUrl = properties.getProperty("ackUrl");
            }
            if (properties.getProperty("osbUrl") != null) {
                osbUrl = properties.getProperty("osbUrl");
            }
            if (properties.getProperty("Hour_schedular1") != null) {
            	Hour_schedular1 = Integer.parseInt(properties.getProperty("Hour_schedular1"));
            }
            if (properties.getProperty("Minute_schedular1") != null) {
            	Minute_schedular1 = Integer.parseInt(properties.getProperty("Minute_schedular1"));
            }
            if (properties.getProperty("Hour_schedular2") != null) {
            	Hour_schedular2 = Integer.parseInt(properties.getProperty("Hour_schedular2"));
            }
            if (properties.getProperty("Minute_schedular2") != null) {
            	Minute_schedular2 = Integer.parseInt(properties.getProperty("Minute_schedular2"));
            }
           
        }

        catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Unable to load properties :" + e.getMessage(), e);

        }
    }
}