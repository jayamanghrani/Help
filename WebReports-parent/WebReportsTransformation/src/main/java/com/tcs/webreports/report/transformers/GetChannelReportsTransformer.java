/**
 * 
 */
package com.tcs.webreports.report.transformers;

import com.tcs.webreports.asbo.report.request.ChannelReportsRequestASBO;
import com.tcs.webreports.cache.asbo.request.ChannelReportsCacheRequestASBO;
import com.tcs.webreports.cache.asbo.request.GetChannelReportsCacheRequestASBO;
import com.tcs.webreports.cache.asbo.response.ChannelReportsCacheResponseASBO;
import com.tcs.webreports.cache.asbo.response.GetChannelReportsCacheResponseASBO;
import com.tcs.webreports.exception.cmo.IntegrationTransformationException;
import com.tcs.webreports.oamoid.asbo.request.GetChannelOAMOIDRequestASBO;
import com.tcs.webreports.vo.cmo.WebReportsRequestObject;

/**
 * @author 738566
 *
 */
public class GetChannelReportsTransformer {
	
	 private ChannelReportsRequestASBO channelReportsRequestASBO;
	  private GetChannelOAMOIDRequestASBO getChannelOAMOIDRequestASBO;
	  private GetChannelReportsCacheRequestASBO channelReportsCacheRequestASBO;
	  private ChannelReportsCacheResponseASBO cacheChannelReportsCacheResponseASBO;
	  private GetChannelReportsCacheResponseASBO getChannelReportsCacheResponseASBO;
	  
	  public GetChannelReportsTransformer()
	  {
	    this.getChannelOAMOIDRequestASBO = new GetChannelOAMOIDRequestASBO();
	    this.channelReportsCacheRequestASBO=new GetChannelReportsCacheRequestASBO();
	    this.cacheChannelReportsCacheResponseASBO=new ChannelReportsCacheResponseASBO();
	  }

	  /**
	   * 
	   * @param channelReportsRequestASBO
	   * @return
	   * @throws IntegrationTransformationException
	   */
	public WebReportsRequestObject transformRequest(
			ChannelReportsRequestASBO channelReportsRequestASBO) throws IntegrationTransformationException {
		this.channelReportsRequestASBO = channelReportsRequestASBO;

	    this.getChannelOAMOIDRequestASBO.setHeader(this.channelReportsRequestASBO.getHeader());

	    this.getChannelOAMOIDRequestASBO.setSessionToken(this.channelReportsRequestASBO.getSessionToken());

	    return this.getChannelOAMOIDRequestASBO;
	}

	/**
	 * 
	 * @param cacheResponseASBO
	 * @return
	 * @throws IntegrationTransformationException
	 */
	public ChannelReportsCacheResponseASBO transformResponse(GetChannelReportsCacheResponseASBO cacheResponseASBO) throws IntegrationTransformationException {
		this.getChannelReportsCacheResponseASBO=cacheResponseASBO;
		this.cacheChannelReportsCacheResponseASBO.setHeader(this.getChannelReportsCacheResponseASBO.getHeader());
		this.cacheChannelReportsCacheResponseASBO.setReportsName(this.getChannelReportsCacheResponseASBO.getReportNames());
		return this.cacheChannelReportsCacheResponseASBO;
	}

	/**
	 * 
	 * @param channelReportsCacheRequestASBO
	 * @return
	 * @throws IntegrationTransformationException
	 */
	public WebReportsRequestObject transformCacheRequest(ChannelReportsCacheRequestASBO channelReportsCacheRequestASBO)throws IntegrationTransformationException
	{   this.channelReportsCacheRequestASBO.setHeader(channelReportsCacheRequestASBO.getHeader());
		this.channelReportsCacheRequestASBO.setChannelName(channelReportsCacheRequestASBO.getChannelName());
		return this.channelReportsCacheRequestASBO;
	}
}