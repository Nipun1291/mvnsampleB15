package testcripts;

import org.testng.annotations.Test;

public class GroupOneTest {
	
	@Test
	public void searchJavaTest()
	{
		System.out.println("Search Java Test");
	}
	@Test(groups = {"featureOne"})
	public void searchSeleniumTest()
	{
		System.out.println("Search Selenium Test");
	}
	@Test(groups = {"featureThree"})
	public void searchCypressTest()
	{
		System.out.println("Search Cypress Test");
	}
	@Test(groups = {"featureOne"})
	public void searchAppiumTest()
	{
		System.out.println("Search Appium Test");
	}
	@Test(groups = {"featureTwo"})
	public void searchAPITest()
	{
		System.out.println("Search API Test");
	}

	
}
