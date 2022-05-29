package com.tcs.webreports.cache.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.tcs.webreports.cache.Cache;
import com.tcs.webreports.cache.asbo.request.GetChannelReportsCacheRequestASBO;
import com.tcs.webreports.cache.asbo.response.GetChannelReportsCacheResponseASBO;
import com.tcs.webreports.cache.keys.CacheKey;
import com.tcs.webreports.cache.keys.ChannelListKey;
import com.tcs.webreports.cache.model.ChannelReportsModel;
import com.tcs.webreports.util.UtilConstants;

public class ReportsChannelCacheService {

	private static final Logger LOGGER = Logger
			.getLogger(ReportsChannelCacheService.class);
    /**
     * cache channel and reports mapping
     */
	public void cacheChannelAndReportsList() {
		LOGGER.info("caching channel Reports list");
		Map<String,String> list=new HashMap<String,String>();
		list=getChannelReporsMapping();
		Map<ChannelListKey, String> channelReportsMap = new HashMap<ChannelListKey, String>();
		ChannelListKey channelListKey = null;
		for (Map.Entry entry : list.entrySet()) {
			channelListKey = getChannelKey(entry.getKey().toString());
			channelReportsMap.put(channelListKey, (String) entry.getValue());
		}
		    
		 for (Map.Entry entry : channelReportsMap.entrySet()) {
			 Cache.put((CacheKey)entry.getKey(), entry.getValue());
			}
		 LOGGER.info("cached channel reports list");
	}
	/**
	 * 
	 * @param channelName
	 * @return
	 */
	private ChannelListKey getChannelKey(String channelName) {
		return new ChannelListKey(channelName);
	}
	/**
	 * reads file from remote location
	 * @return
	 */
	public Map<String,String>  getChannelReporsMapping() {
		Map<String,String> reportsModels=new HashMap<String,String>();
		try {
			InputStream excelFile = new FileInputStream(UtilConstants.CHANNEL_REPORTS_MAPPING_PATH);
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile); 
			XSSFSheet sheet = workbook.getSheetAt(0); 
			XSSFRow row=sheet.getRow(0);
			int rows = sheet.getLastRowNum();
			for (int r = 1; r <= rows; r++) {
				ChannelReportsModel model=new ChannelReportsModel();
				row = sheet.getRow(r);
				if ((row != null) && (row.getCell(0) != null)
						&& (row.getCell(0).toString().equals(""))) {
					continue;
				}
				if ((row.getCell(0) != null)
						&& (!row.getCell(0).toString().startsWith("***"))) {

					if (row.getCell(0) != null) {
						if (row.getCell(1) != null) {
							model.setChannelName(row.getCell(0).toString().toLowerCase());
							model.setReportName(row.getCell(1).toString());
							reportsModels.put(row.getCell(0).toString().toLowerCase(), row.getCell(1).toString());
						}
					}
				}
				else {
					r = rows;
					workbook.close();
					break;
				}
			}

		} catch (Exception e) {
			
			LOGGER.error("Error in reading file",e);
		}
		return reportsModels;
	}
	/**
	 * Get channel -reports mapping from cache
	 * @param requestASBO
	 * @return
	 */
	public GetChannelReportsCacheResponseASBO getChannelReportsList(GetChannelReportsCacheRequestASBO requestASBO)
	  {
	    GetChannelReportsCacheResponseASBO responseASBO = new GetChannelReportsCacheResponseASBO();
	    ChannelListKey channelListKey = new ChannelListKey(requestASBO.getChannelName());
        String reportsName="";
		if (Cache.get(channelListKey) != null) {
			reportsName =(String) Cache.get(channelListKey);
			List<String> reportsList=Arrays.asList(reportsName.split(","));
			responseASBO.setReportNames(reportsList);
		}
	    return responseASBO;
	  }

}
