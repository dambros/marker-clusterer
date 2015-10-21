package br.com.loducca.clusterer.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by: dambros
 * Date: 10/20/2015
 */
public class PropertiesHelper {

	public static Properties properties;

	static {
		try {
			final InputStream stream = ClassLoader.getSystemResourceAsStream("application.properties");
			properties = new Properties();
			properties.load(stream);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getProperties(String value) {
		return properties.getProperty(value);
	}
}
