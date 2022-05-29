package com.tcs.webreports.security.filters;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

public class ServletOutputStreamCopier extends ServletOutputStream
{
  private OutputStream outputStream;
  private ByteArrayOutputStream copy;

  public ServletOutputStreamCopier(OutputStream outputStream)
  {
    this.outputStream = outputStream;
    this.copy = new ByteArrayOutputStream(1024);
  }

  public void write(int b)
    throws IOException
  {
    this.outputStream.write(b);
    this.copy.write(b);
  }

  public byte[] getCopy()
  {
    return this.copy.toByteArray();
  }

  public boolean isReady()
  {
    return false;
  }

  public void setWriteListener(WriteListener arg0)
  {
  }
}