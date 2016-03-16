package com.velocityPartners.selenium.eHarmony;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoremIpsum {
	private static String baseUrl;
	private static WebDriver driver;
	private static WebElement searchField;
	private Locators locators;
	
	private static final String theFirstTitleParagraph = "div > h2";
	private static final String theFirstParagraph = "div > p";
	
	@BeforeClass
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", "/Users/luillyfe/chromedriver");
		
		baseUrl = System.getProperty("webdriver.base.url")!=null ? 
		  System.getProperty("webdriver.base.url") : "http://www.lipsum.com/";
		driver = new ChromeDriver();
		driver.get(baseUrl);
		
	}
	
	@Before
	public void openBrowser() {
	    locators = new Locators(driver);
	}
	
	@AfterClass
	public static void saveScreenshotAndCloseBrowser() throws IOException {
		driver.quit();
	}
	
	@Test
	public void titleParagraph() {
		assertTrue("The page title should start with the search string after the search.",
			locators.getElementByCss(theFirstTitleParagraph).getAttribute("textContent").equals("What is Lorem Ipsum?"));
	}
	
	@Test
	public void Paragraph() {
		assertTrue("The page title should start with the search string after the search.",
			locators.getElementByCss(theFirstParagraph).getAttribute("textContent")
			  .contains("Lorem Ipsum is simply dummy text of the printing and typesetting industry"));
	}
}
