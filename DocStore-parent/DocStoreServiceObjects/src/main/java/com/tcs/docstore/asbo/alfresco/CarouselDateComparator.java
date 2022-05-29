/**
 * 
 */
package com.tcs.docstore.asbo.alfresco;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import org.apache.log4j.Logger;


import com.tcs.docstore.asbo.alfresco.ContentData;

/**
 * @author 376448
 *
 */
public class CarouselDateComparator implements Comparator<ContentData> {
	/** The logger. */
	Logger logger=Logger.getLogger(CarouselDateComparator.class);

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(ContentData obj1, ContentData obj2) {
		Date d1 = null;
		Date d2 = null;
		int order1 = obj1.getOrderNum();
		int order2 = obj2.getOrderNum();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try {
			d1 = dateFormat.parse(obj1.getModifiedDate());
			d2 = dateFormat.parse(obj2.getModifiedDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		if (d1.after(d2) && order1 == order2) {
			return -1;
		} else if (d1.before(d2) && order1 == order2) {  //changed
			return 1;
		} else {
			return 0;
		}
	}

}
