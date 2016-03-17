package com.velocityPartners.selenium.eHarmony;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoremIpsum {
	private static String baseUrl;
	private static WebDriver driver;
	private static WebElement searchField;
	private Locators locators;
	
	private static final String theFirstTitleParagraph = "div > h2";
	private static final String theFirstParagraph = "div > p";
	private static final String inputSubmit = "input[type=submit]";
	private static final String label = "input[type=radio]";
	private static final String paragraphs = "div#lipsum > p";
	private static final String inputText = "input[type=text]";
	
	@BeforeClass
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", "/Users/luillyfe/chromedriver");
		
		ChromeOptions options = new ChromeOptions();
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		List<String> list = new ArrayList<String>();
        list.add("disable-component-update");
        options.setExperimentalOption("excludeSwitches", list);
        options.addArguments("user-data-dir=/Users/luillyfe/Library/Application Support/Google/Chrome/Profile 1");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		
		baseUrl = System.getProperty("webdriver.base.url")!=null ? 
		  System.getProperty("webdriver.base.url") : "http://www.lipsum.com/";
		driver = new ChromeDriver();
		//driver.get(baseUrl);
		
	}
	
	@Before
	public void openBrowser() {
		driver.get(baseUrl);
	    locators = new Locators(driver);
	}
	
	@AfterClass
	public static void saveScreenshotAndCloseBrowser() throws IOException {
		driver.close();
	}
	
	@Test
	public void titleParagraph() {
		assertTrue("The page paragraph should containts What is Lorem Ipsum? text",
			locators.getElementByCss(theFirstTitleParagraph).getAttribute("textContent").equals("What is Lorem Ipsum?"));
	}
	
	@Test
	public void paragraph() throws InterruptedException {
		assertTrue("The page title should start with the search string after the search.",
			locators.getElementByCss(theFirstParagraph).getAttribute("textContent")
			  .contains("Lorem Ipsum is simply dummy text of the printing and typesetting industry"));
	}
	
	@Test
	public void paragraphsGenerator() throws InterruptedException {
		locators.getElementByCss(inputText).clear();
		locators.getElementByCss(inputText).sendKeys("3");
		locators.getElementByCss(inputSubmit).submit();
		
		assertTrue("The page title should start with the search string after the search.",
				locators.getElementsSize(paragraphs)==3);
	}
}

//document.querySelector("input[type=radio]").parentElement.nextElementSibling.firstChild.textContent
