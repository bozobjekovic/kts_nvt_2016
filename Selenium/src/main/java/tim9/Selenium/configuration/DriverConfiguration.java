package tim9.Selenium.configuration;

import java.io.InputStream;
import java.util.Properties;

public class DriverConfiguration {

	Properties properties = new Properties();
	InputStream input = null;
	
	public String getDriverPath() {
		try {
			String file = "config.properties";
			input = getClass().getClassLoader().getResourceAsStream(file);
			
			if (input != null) {
				properties.load(input);
				return properties.getProperty("driver");
			}
		} catch (Exception e) {
			return "";
		}
		
		return null;
	}
    
	

}
