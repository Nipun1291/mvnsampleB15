package datascripts;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import commonUtil.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DataParamTest {

	public static WebDriver driver;
	ExtentTest extentTest;
	ExtentReports reports;
	ExtentHtmlReporter htmlReport;

	@BeforeTest
	public void setExtent() {
		reports = new ExtentReports();
		htmlReport = new ExtentHtmlReporter(
				"//Users//nipun//eclipse-workspace//mvnsampleB15//src//screenshot//extent.html");
		reports.attachReporter(htmlReport);
	}

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(dataProvider = "loginData")
	public void loginTest(String strUser, String strPwd) {
		extentTest = reports.createTest("loginTest");
		driver.get("https://the-internet.herokuapp.com/login");
		driver.findElement(By.id("username")).sendKeys(strUser);
		driver.findElement(By.id("password")).sendKeys(strPwd);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		String isLoginSuccess = driver.findElement(By.xpath("//div[@id='flash-messages']")).getText();
		System.out.println(isLoginSuccess);
		Assert.assertEquals(isLoginSuccess, "You logged into a secure area!\n" + "×");

	}
	/*
	 * @DataProvider(name = "loginData") public Object[][] getData() { return new
	 * Object[][] { new Object[] {"testuser1", "welcome123"}, new Object[]
	 * {"testuser2", "welcome123"}, new Object[] {"tomsmith",
	 * "SuperSecretPassword!"} }; }
	 */

	/*
	 * @DataProvider(name = "loginData") public Object[][] getData() throws
	 * CsvValidationException, IOException { String path =
	 * System.getProperty("user.dir") + "//src//test//resources//loginData.csv";
	 * CSVReader reader = new CSVReader(new FileReader(path));
	 * 
	 * String[] col; ArrayList<Object[]> datalist = new ArrayList<Object[]>();
	 * while((col=reader.readNext())!=null) { Object[] record = {col[0], col[1]};
	 * datalist.add(record); } reader.close(); return datalist.toArray(new
	 * Object[datalist.size()][]); }
	 */
	
	@DataProvider(name = "loginData") 
	public String[][] getData() throws IOException, ParseException 
	{
		String path = System.getProperty("user.dir") + "//src//test//resources//loginData.json";
		FileReader reader = new FileReader(path);
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(reader);
		JSONObject jsonObject = (JSONObject)obj;
		JSONArray userArray = (JSONArray)jsonObject.get("userLogins");
		String arr[][] = new String[userArray.size()][];
		for(int i =0; i< userArray.size(); i++)
		{
			JSONObject user = (JSONObject)userArray.get(i);
			String strUser = (String)user.get("username");
			String strPwd = (String)user.get("password");
			String record[] = new String[2];
			record[0] = strUser;
			record[1] = strPwd;
			arr[i] = record;
		}
	
	return arr;
	}
	
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			String path = Utility.getScreenshotPath(driver);
			extentTest.fail(result.getName());
			extentTest.fail(result.getThrowable().getMessage());
			extentTest.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		driver.close();

	}

	@AfterTest
	public void finishExtent() {
		reports.flush();
	}
}
