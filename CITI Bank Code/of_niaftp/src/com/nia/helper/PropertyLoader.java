package com.nia.helper;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import com.nia.jaxb.Config;


public class PropertyLoader {
	private File file = new File("config"+System.getProperty("file.separator")+"config.xml");
	private Config LogConfigurator;
	
	private static PropertyLoader instance=null;
	
	protected PropertyLoader()
	{
		
		
	}
	
	public static PropertyLoader getInstance()
	{
		if (instance == null)
		{
		instance = new PropertyLoader();	
		}
		return instance;
	}
	
	
	public Config getLogConfiguration() throws JAXBException 
	{
		JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		JAXBElement<Config> root = jaxbUnmarshaller.unmarshal(new StreamSource(file),Config.class);
		LogConfigurator = root.getValue();
		return LogConfigurator;
	
	}
	

}
