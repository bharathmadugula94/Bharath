package propertypkage;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyDemo {

	public static void main(String[] args) throws Exception 
	{
		Properties p=new Properties();
		FileInputStream fis=new FileInputStream("D:\\bharath\\Stock_Accounting_Hybrid\\PropertiesFile\\dummy.properties");
			p.load(fis);
			String keyvalue=(String) p.get("Browser");
			System.out.println("keyvalue is" + keyvalue);
	}

}
