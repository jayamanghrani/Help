package com.tcs.webreports.security.filters;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.log4j.Logger;

public class ServletRequestWrapper extends HttpServletRequestWrapper
{
  private static final Logger LOGGER = Logger.getLogger("portalLogger");
  private final String payload;

  public ServletRequestWrapper(HttpServletRequest request)
  {
    super(request);

    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader bufferedReader = null;
    try
    {
      InputStream inputStream = request.getInputStream();
      if (inputStream != null) {
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        char[] charBuffer = new char[''];
        int bytesRead = -1;
        while ((bytesRead = bufferedReader.read(charBuffer)) > 0)
          stringBuilder.append(charBuffer, 0, bytesRead);
      }
      else
      {
        stringBuilder.append("{}");
      }
    } catch (IOException iox) {
      LOGGER.error("IOException", iox);
    } finally {
      if (bufferedReader != null) {
        try {
          bufferedReader.close();
        } catch (IOException iox) {
          LOGGER.error("IOException", iox);
        }
      }
    }
    if ((stringBuilder == null) || (stringBuilder.length() <= 0)) {
      LOGGER.info("Recieved null payload....");
      stringBuilder.append("{}");
    }
    this.payload = stringBuilder.toString();
  }

  public ServletInputStream getInputStream()
    throws IOException
  {
	  final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.payload.getBytes(StandardCharsets.UTF_8));
	    ServletInputStream servletInputStream=new ServletInputStream(){
	        public int read() throws IOException {
	          return byteArrayInputStream.read();
	        }

			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public void setReadListener(ReadListener arg0) {
				
			}
	    };
		return servletInputStream;
  }
}