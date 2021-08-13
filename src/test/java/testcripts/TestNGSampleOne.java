package testcripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGSampleOne {
	
	public static WebDriver driver;
	
	@Parameters("browser")
	@BeforeMethod
	public void setUp(String strBrowser)
	{
		System.out.println("Browser name is : " + strBrowser);
		if(strBrowser.equalsIgnoreCase("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}else if(strBrowser.equalsIgnoreCase("firefox"))
	{
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}
	}
	
  @Test
  public void f() {
	  
	  
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		WebElement search = driver.findElement(By.cssSelector("input[title='Search']"));
		search.sendKeys("Java Tutorial");
		search.sendKeys(Keys.ENTER);
		
		Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
  }
  
  @Test
  public void test2()
  {
	  driver.get("https://www.google.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		WebElement search = driver.findElement(By.cssSelector("input[title='Search']"));
		search.sendKeys("Java Tutorial");
		search.sendKeys(Keys.ENTER);
		
		Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
  }
		@AfterMethod
		public void tearDown()
		{
			driver.close();
		}
  }

