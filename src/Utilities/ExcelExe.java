package Utilities;

public class ExcelExe 
{

	public static void main(String[] args) throws Throwable
	{
		ExcelUtil f=new ExcelUtil();
		int tot=f.rowCount("ApplicationLogin");
		System.out.println(tot);
	}

}
