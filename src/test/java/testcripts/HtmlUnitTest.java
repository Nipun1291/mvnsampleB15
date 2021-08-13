package testcripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class HtmlUnitTest {
	
	public static WebDriver driver;
  @Test
  public void test() {
	  
        driver = new HtmlUnitDriver();
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		WebElement search = driver.findElement(By.cssSelector("input[title='Search']"));
		search.sendKeys("Java Tutorial");
		search.sendKeys(Keys.ENTER);
		System.out.println("Page Title : " + driver.getTitle());
		
		Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
}
  }

