package com.pb.dps.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {
	
	public static String getResourceProperty(String propertyName) throws IOException{
		InputStream is= Utils.class.getResourceAsStream("/ConfigurationFile");		
		Properties props=new Properties();
		props.load(is);
		return props.getProperty(propertyName);
	}

}
