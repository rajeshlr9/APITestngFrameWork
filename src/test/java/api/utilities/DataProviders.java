package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;


public class DataProviders {
  
@DataProvider(name="Data")
public String[][] getData() throws IOException
		{
	String path	=".\\TestData\\Test_Data.xlsx";
	
	ExcelUtility xl = new ExcelUtility(path);
	
	int rownum = xl.getRowCount("Sheet1");
	int colcount = xl.getCellCount("Sheet1", 1);
	
	String apidata[][] = new String[rownum][colcount];
	
	for (int i=1;i<=rownum;i++)    // 5
	
	for(int j=0;j<colcount;j++)    // 3
	
	{
		apidata[i-1][j]=ExcelUtility.getCellData("Sheet1", i, j);
		
//		System.out.println(ExcelUtility.getCellData("Sheet1", i, j));
		
	}
	

	
	return apidata; // returning two dimension array
	
		}
	
	
	@DataProvider(name="UserNames")
	
	public String[] getUserNames () throws IOException
	
	{
    String path	=".\\TestData\\Test_Data.xlsx";
	
	ExcelUtility xl = new ExcelUtility(path);
	
	int rownum = xl.getRowCount("Sheet1");
	
	String apidata[] = new String[rownum];
	
	for(int i=1;i<=rownum;i++)
	
	{
		
		apidata[i-1] = xl.getCellData("Sheet1", i, 1);
		
		
		
	}
	
	
	
	return apidata;
	
	
	
	
	}
	
}
