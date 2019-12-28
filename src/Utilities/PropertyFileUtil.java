package Utilities;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil 
{
	public static String getValueForKeys(String key) throws IOException
	{
		Properties configProperties=new Properties();
		FileInputStream fis=new FileInputStream("D:\\bharath\\Stock_Accounting_Hybrid\\PropertiesFile\\Environment.properties");
		configProperties.load(fis);
		return (String)configProperties.get(key);
	}
}
