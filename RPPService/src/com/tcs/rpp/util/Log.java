/*
 * Created on Sep 12, 2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.tcs.rpp.util;

import java.io.IOException;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * @author 463762
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class Log
{
  public static Logger rpp_Log = Logger.getLogger("RppApiLogger");

  static {
	  FileAppender appender1 = null;
	
	  String log_path = UtilConstants.logPath;
    
    try
    {
      appender1 = new DailyRollingFileAppender(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %C{1}:%L - %m%n"), log_path, "'.'dd-MM-yyyy");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    rpp_Log.setLevel(Level.INFO);
    rpp_Log.addAppender(appender1);
  }
}



