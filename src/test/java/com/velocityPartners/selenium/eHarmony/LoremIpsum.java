package com.velocityPartners.selenium.eHarmony;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;


public class LoremIpsum {
	private static String baseUrl;
	private static WebDriver driver;;
	private Locators locators;
	
	private static final String theFirstTitleParagraph = "div > h2";
	private static final String theFirstParagraph = "div > p";
	private static final String inputSubmit = "input[type=submit]";
	//private static final String label = "input[type=radio]";
	private static final String paragraphs = "div#lipsum > p";
	private static final String inputText = "input[type=text]";
	private static final int inputTextValue = 3;
	
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
		driver = new ChromeDriver(capabilities);
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
		assertTrue("The page paragraph should be equal to: 'What is Lorem Ipsum?' text",
			locators.getElementByCss(theFirstTitleParagraph).getAttribute("textContent").equals("What is Lorem Ipsum?"));
	}
	
	@Test
	public void paragraph() {
		assertTrue("The paragraph should containts 'Lorem Ipsum is simply...' text",
			locators.getElementByCss(theFirstParagraph).getAttribute("textContent")
			  .contains("Lorem Ipsum is simply dummy text of the printing and typesetting industry"));
	}
	
	@Test
	public void paragraphsGenerator() throws InterruptedException {
		locators.getElementByCss(inputText).clear();
		locators.getElementByCss(inputText).sendKeys(String.valueOf(inputTextValue));
		locators.getElementByCss(inputSubmit).submit();
		
		assertTrue("The page should be have 3 paragraphs",
				locators.getElementsByCss(paragraphs).size()==inputTextValue);
	}
}

//document.querySelector("input[type=radio]").parentElement.nextElementSibling.firstChild.textContent
