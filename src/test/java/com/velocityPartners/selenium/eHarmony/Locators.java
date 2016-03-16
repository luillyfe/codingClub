package com.velocityPartners.selenium.eHarmony;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Locators {
	private WebDriver driver;
	private static final String linksToSelenium = "h3.r > a:link";
	
	public Locators(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getFirstLink() {
		return (new WebDriverWait(driver, 10)).until(new ExpectedCondition<WebElement>() {
		  public WebElement apply(WebDriver d) {
			  return d.findElement(By.cssSelector(linksToSelenium));
		  }
		});
	}
}
//d.findElement(By.cssSelector("h3.h a:link")).getAttribute("href").contains("selenium")