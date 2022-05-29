package com.tcs.webreports.cache.keys;

public class ChannelListKey implements CacheKey
{
	  /**
	 * 
	 */
	private static final long serialVersionUID = -8562350641925746047L;
	
	private final String channelName;

	  public ChannelListKey(String channelName)
	  {
	    this.channelName = channelName;
	  }

	  public int hashCode()
	  {
	    int result = 1;
	    result = 31 * result + (this.channelName == null ? 0 : this.channelName.hashCode());

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
	    ChannelListKey other = (ChannelListKey)obj;
	    if (this.channelName == null) {
	      if (other.channelName != null)
	        return false;
	    } else if (!this.channelName.equals(other.channelName))
	      return false;
	    return true;
	  }

	  public String getChannelName()
	  {
	    return this.channelName;
	  }
}
