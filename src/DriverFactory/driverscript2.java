package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunctionLibrary.FunctionLibrary;
import Utilities.ExcelUtil;

public class driverscript2 
{
	ExtentReports report;
	ExtentTest test;
	WebDriver driver;
	@Test
	public void startTest() throws Throwable
	{
		ExcelUtil excel=new ExcelUtil();
		
		for(int i=1;i<=excel.rowCount("Sheet2");i++)
		{
			if(excel.getData("Sheet2", i, 2).equalsIgnoreCase("Y"))
			{
				String TCModule=excel.getData("Sheet2", i, 1);
				System.out.println("tcmodule is "+TCModule);
				report=new ExtentReports("D:\\bharath\\Stock_Accounting_Hybrid\\Reports\\myreport.html");
				for(int j=1;j<=excel.rowCount(TCModule);j++)
				{
					test=report.startTest(TCModule);
					String Description=excel.getData(TCModule, j, 0);
					String Function_Name=excel.getData(TCModule, j, 1);
					String Locator_Type=excel.getData(TCModule, j, 2);
					String Locator_Value=excel.getData(TCModule, j, 3);
					String Test_Data=excel.getData(TCModule, j, 4);
					
					System.out.println(Function_Name);
					
					try
					{
						if(Function_Name.equalsIgnoreCase("startBrowser"))
						{
							FunctionLibrary.startBrowser();
							System.out.println("Launching browser");
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("openApplication"))
						{
							System.out.println("in open block");
							FunctionLibrary.openApplication();
							System.out.println("Launching application in browser");
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("typeAction"))
						{
							FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("clickAction"))
						{
							FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("waitForElement"))
						{
							FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);	
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("closeBrowser"))
						{
							FunctionLibrary.closeBrowser(driver);
							test.log(LogStatus.INFO, Description);
						}
						//write as pass into status column
						excel.setData(TCModule, j, 5, "PASS");
						excel.setData("Sheet2", i, 3, "PASS");
						test.log(LogStatus.PASS, Description);		
					}
					catch(Exception e)
					{
						System.out.println("exception handled");
						File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						FileUtils.copyFile(srcFile, new File("D:\\bharath\\Stock_Accounting_Hybrid\\Screens\\myscreenshot.png"));
						excel.setData(TCModule, j, 5, "Fail");
						excel.setData("Sheet2", i, 3, "Fail");
						break;
					}	
					report.flush();
					report.endTest(test);
				}
			}
			else
			{
				//write as not Executed in status column in MasterTestCases sheet
				excel.setData("Sheet2", i, 3, "Not Executed");
			}
		}
		
	}

}
