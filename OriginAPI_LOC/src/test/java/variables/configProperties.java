package variables;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class configProperties {
	public static Properties property;
	private static String configPath = "config/config.properties";
	
	public static void initializePropertyFile()
	{
		property = new Properties();
		 try {
			   InputStream instm = new FileInputStream(configPath);
			   property.load(instm);
			  } catch (FileNotFoundException e) {
			   e.printStackTrace();
			  } catch (IOException e) {
			   e.printStackTrace();
			  }
	}
}
