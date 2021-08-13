package parallelscripts;

import org.testng.annotations.Test;

public class MethodsTest {
 
	
	@Test
	public void searchJavaTest()
	{
		long id = Thread.currentThread().getId();
		System.out.println("Search Java Test"+ id);
	}
	@Test
	public void searchSeleniumTest()
	{
		long id = Thread.currentThread().getId();
		System.out.println("Search Selenium Test"+ id);
	}
	@Test
	public void searchCypressTest()
	{
		long id = Thread.currentThread().getId();
		System.out.println("Search Cypress Test"+ id);
	}
	@Test
	public void searchAppiumTest()
	{
		long id = Thread.currentThread().getId();
		System.out.println("Search Appium Test"+ id);
	}
	@Test(threadPoolSize = 4, invocationCount = 6, timeOut=1000)
	public void searchAPITest()
	{
		long id = Thread.currentThread().getId();
		System.out.println("Search API Test"+ id);
	}

	
}


