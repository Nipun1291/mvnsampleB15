package testcripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNGSampleTwo {
	
public static WebDriver driver;

public static Properties prop;

   @BeforeTest
   public void readProperty() throws IOException
   {
	   prop = new Properties();
	   String path = System.getProperty("user.dir") + "//src//test//resources//config.properties";
	   FileInputStream fis = new FileInputStream(path);
	   prop.load(fis);
   }
	
	@BeforeMethod
	public void setUp()
	{
		if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
  //@Test(retryAnalyzer = RetryAnalyzerTest.class)
	@Test
  public void f2() {
	  
	  
		driver.get(prop.getProperty("url"));
		WebElement search = driver.findElement(By.cssSelector("input[title='Search']"));
		search.sendKeys(prop.getProperty("searchText"));
		search.sendKeys(Keys.ENTER);
		Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
  }
  
  @AfterTest
	public void tearDown()
	{
		driver.close();
	}
}


