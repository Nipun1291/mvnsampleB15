package datascripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;

import commonUtil.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ExcelDataTest {
	
	public static WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("Modifying my file");
	}

	@Test
	public void loginTest() throws IOException {
		driver.get("https://the-internet.herokuapp.com/login");
		driver.findElement(By.xpath(getExcelObj("txtusername"))).sendKeys("tomsmith");
		driver.findElement(By.xpath(getExcelObj("txtpassword"))).sendKeys("SuperSecretPassword!");
		driver.findElement(By.xpath(getExcelObj("loginBtn"))).click();
		String isLoginSuccess = driver.findElement(By.xpath("//div[@id='flash-messages']")).getText();
		System.out.println(isLoginSuccess);
		Assert.assertEquals(isLoginSuccess, "You logged into a secure area!\n" + "×");
	}
		
		
	public String getExcelData(String colName) throws IOException
	{
		String colValue = " ";
		String path = System.getProperty("user.dir") + "//src//test//resources//testData.xlsx";
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet loginSheet = workbook.getSheet("loginData");
		int numRows = loginSheet.getLastRowNum();
		System.out.println("Number of rows : " + numRows);
		for(int i = 1; i<=numRows; i++)
		{
			XSSFRow row = loginSheet.getRow(i);
			if(row.getCell(0).getStringCellValue().equalsIgnoreCase(colName))
			{
				colValue = row.getCell(1).getStringCellValue();
			}
		}
	return colValue;
	
	}
	
	public String getExcelObj(String elementName) throws IOException
	{
		String elementPath = " ";
		String path = System.getProperty("user.dir") + "//src//test//resources//testData.xlsx";
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet loginSheet = workbook.getSheet("loginObjRepo");
		int numRows = loginSheet.getLastRowNum();
		System.out.println("Number of rows : " + numRows);
		for(int i = 1; i<=numRows; i++)
		{
			XSSFRow row = loginSheet.getRow(i);
			if(row.getCell(0).getStringCellValue().equalsIgnoreCase(elementName))
			{
				elementPath = row.getCell(1).getStringCellValue();
			}
		}
	return elementPath;
	
	}


	@AfterMethod
	public void tearDown(){
		driver.close();


	}

}
