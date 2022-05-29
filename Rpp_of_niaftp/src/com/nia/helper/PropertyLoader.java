package com.nia.helper;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import com.nia.jaxb.Config;


public class PropertyLoader {
	private File file = new File("config"+System.getProperty("file.separator")+ "config.xml");
	private Config LogConfigurator;
	
	private static PropertyLoader instance=null;
	

	
	
	public static PropertyLoader getInstance()
	{
		if (instance == null)
		{
		instance = new PropertyLoader();	
		}
		return instance;
	}
	
	  public Config getLogConfiguration()
			    throws JAXBException
			  {
			    JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] { Config.class });
			    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			    JAXBElement root = jaxbUnmarshaller.unmarshal(new StreamSource(this.file), Config.class);
			    this.LogConfigurator = ((Config)root.getValue());
			    return this.LogConfigurator;
			  }
	

}
