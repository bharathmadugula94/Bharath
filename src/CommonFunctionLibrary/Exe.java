package CommonFunctionLibrary;



import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Utilities.PropertyFileUtil;

public class Exe {
	static WebDriver driver;

	public static void main(String[] args) throws Exception  
	{
		
		if(PropertyFileUtil.getValueForKeys("Browser").equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\bharath\\Stock_Accounting_Hybrid\\Drivers\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		FunctionLibrary.openApplication();
	}
}
