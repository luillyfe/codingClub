package com.velocityPartners.selenium.eHarmony;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WhenSearchingForDrupalUsingGoogleTest {
	private static String baseUrl;
	private static WebDriver driver;
	private static WebElement searchField;
	private ScreenshotHelper screenshotHelper;
	private Locators locators;
	
	private static final String linksToSelenium = "h3.r > a:link";
	private static final String SELENIUM = "selenium";
	private static final String HREF = "href";
	
	@BeforeClass
	public static void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "/Users/luillyfe/chromedriver");
		
		baseUrl = System.getProperty("webdriver.base.url")!=null ? 
		  System.getProperty("webdriver.base.url") : "http://www.google.com/";
		driver = new ChromeDriver();
		driver.get(baseUrl);
		
		searchField = driver.findElement(By.name("q"));
		searchField.sendKeys(SELENIUM);
		searchField.submit();
	}
	
	@Before
	public void setup() {
	    screenshotHelper = new ScreenshotHelper();
	    locators = new Locators(driver);
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
		  "selenium - Buscar con Google", driver.getTitle());
		
		assertTrue("The page title should start with the search string after the search.", 
			locators.getTitlePage().toLowerCase().startsWith(SELENIUM));
	}
	
	@Test //http://www.seleniumhq.org/
	public void pageLinksAfterSearchShouldContaintSelenium()  {
		assertTrue("The links should be conatint the string 'selenium'",
		  locators.getElementByCss(linksToSelenium).getAttribute(HREF).contains(SELENIUM));
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
