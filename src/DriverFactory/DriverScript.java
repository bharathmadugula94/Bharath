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

public class DriverScript 
{
	WebDriver driver;
  @Test
  public void StartTest() throws Exception 
  {
	  ExcelUtil excel=new ExcelUtil();
	  WebDriver driver = null; 
	  ExtentReports report = null;
	  ExtentTest test = null;
	  
	  int rowcount_Sheet2=excel.rowCount("Sheet2");
	  for(int i=1; i<rowcount_Sheet2;i++)
	  {
		  String ModuleStatus="";
		  String TCModule=excel.getData("Sheet2", i, 1);
		  String Executionmode=excel.getData("Sheet2", i, 2);
		  report = new ExtentReports("D:\\bharath\\Stock_Accounting_Hybrid\\Reports\\myreport.html");
		  test=report.startTest("TCModule");
		  if(Executionmode.equalsIgnoreCase("Y"))
		  {
			  int rowcount_TCMODULE=excel.rowCount("TCModule");
			  for(int j=1;j<rowcount_TCMODULE;j++)
			  {
				  String Description=excel.getData(TCModule, j, 0);
				  String Function_Name=excel.getData(TCModule, j, 1);
				  String Locator_Type=excel.getData(TCModule, j, 2);
				  String Locator_Value=excel.getData(TCModule, j, 3);
				  String Test_Data=excel.getData(TCModule, j, 4);
				  try
				  {
					  if(Function_Name.equalsIgnoreCase("startBrowser"))
					  {
						  driver=FunctionLibrary.startBrowser();
						  test.log(LogStatus.INFO, Description);
					  }
					  else if(Function_Name.equalsIgnoreCase("openApplication"))
					  {
						  FunctionLibrary.openApplication();
						  test.log(LogStatus.INFO, Description);
					  }
					  else if(Function_Name.equalsIgnoreCase("waitForElement"))
					  {
						  FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
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
					  else if(Function_Name.equalsIgnoreCase("closeBrowser"))
					  {
						  FunctionLibrary.closeBrowser(driver);
						  test.log(LogStatus.INFO, Description);
					  }
				  }
				  catch(Exception e)
				  {
					  ModuleStatus="false";
					  System.out.println("exception handled");
					  File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					  FileUtils.copyFile(srcFile, new File("D:\\bharath\\Stock_Accounting_Hybrid\\Screens\\myscreenshot.png"));
					  excel.setData(TCModule, j, 5, "FAIL");
					  test.log(LogStatus.FAIL, Description);
					  break;
					  
				  }
			  }
		  }
		  else
		  {
			  excel.setData("Sheet2", i, 3, "NOT EXECUTED");
		  }
		  report.endTest(test);
		  report.flush();
	  }
  }
}
