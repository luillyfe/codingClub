package com.velocityPartners.selenium.eHarmony;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WhenSearchingForDrupalUsingGoogleTest {
	private static String baseUrl;
	private static WebDriver driver;
	private static WebElement searchField;
	private ScreenshotHelper screenshotHelper;
	private Queries queries; 
	
	@BeforeClass
	public static void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "/Users/luillyfe/chromedriver");
		
		baseUrl = System.getProperty("webdriver.base.url")!=null ? 
		  System.getProperty("webdriver.base.url") : "http://www.google.com/";
		driver = new ChromeDriver();
		driver.get(baseUrl);
		
		searchField = driver.findElement(By.name("q"));
		searchField.sendKeys("Selenium");
		searchField.submit();
	}
	
	@Before
	public void setup() {
	    screenshotHelper = new ScreenshotHelper();
	    queries = new Queries(driver);
	}
	
	@AfterClass
	public static void saveScreenshotAndCloseBrowser() throws IOException {
		driver.quit();
	}
	
	@After
	public void teardown() throws IOException {
		screenshotHelper.saveScreenshot("screenshot.png");
	}
	
	@Test
	public void pageTitleAfterSearchShouldBeginWithSelenium() throws IOException {
		assertEquals("The page title should equal Google at the start of the test.",
		  "Selenium - Buscar con Google", driver.getTitle());
		
		assertTrue("The page title should start with the search string after the search.",
		  (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		      return d.getTitle().toLowerCase().startsWith("selenium");
		    }
		}));
	}
	
	@Test //http://www.seleniumhq.org/
	public void pageLinksAfterSearchShouldContaintSelenium()  {
		assertTrue("The links should be conatint the string 'selenium'",
		  (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			  public Boolean apply(WebDriver d) {
				  return d.findElement(By.cssSelector("h3.r > a:link"))
						  .getAttribute("href").contains("selenium");
			  }
		}));
		
		System.out.println( queries.getLink() );
	}
	
	private class ScreenshotHelper {

		public void saveScreenshot(String screenshotFileName)
				throws IOException {
			File screenshot = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(screenshotFileName));
		}
	}
}
