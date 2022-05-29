package com.tcs.webreports.cache.keys;

public class ReportPathKey  implements CacheKey{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7780642982200387333L;
	private final String reportType;

	  public int hashCode()
	  {
	    int result = 1;
	    result = 31 * result + (this.reportType == null ? 0 : this.reportType.hashCode());

	    return result;
	  }

	  public boolean equals(Object obj)
	  {
	    if (this == obj)
	      return true;
	    if (obj == null)
	      return false;
	    if (getClass() != obj.getClass())
	      return false;
	    ReportPathKey other = (ReportPathKey)obj;
	    if (this.reportType == null) {
	      if (other.reportType != null)
	        return false;
	    } else if (!this.reportType.equals(other.reportType))
	      return false;
	    return true;
	  }

	  public ReportPathKey(String reportType)
	  {
	    this.reportType = reportType;
	  }

	  public String getReportType()
	  {
	    return this.reportType;
	  }
}
