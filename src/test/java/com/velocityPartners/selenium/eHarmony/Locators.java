package com.velocityPartners.selenium.eHarmony;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Locators {
	private WebDriver driver;
	
	
	public Locators(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getElementByCss(String linksToSelenium) {
		return (new WebDriverWait(driver, 10)).until(new ExpectedCondition<WebElement>() {
		  public WebElement apply(WebDriver d) {
			  return d.findElement(By.cssSelector(linksToSelenium));
		  }
		});
	}
	
	public String getTitlePage() {
		return (new WebDriverWait(driver, 10)).until(new ExpectedCondition<String>() {
		  public String apply(WebDriver d) {
			  return d.getTitle();
		  }
		});
	}
}
//d.findElement(By.cssSelector("h3.h a:link")).getAttribute("href").contains("selenium")