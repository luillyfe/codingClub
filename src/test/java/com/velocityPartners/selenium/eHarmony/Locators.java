package com.velocityPartners.selenium.eHarmony;

import java.util.List;

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
	
	public WebElement getElementByCss(String selector) {
		return (new WebDriverWait(driver, 10)).until(new ExpectedCondition<WebElement>() {
		  public WebElement apply(WebDriver driver) {
			  return driver.findElement(By.cssSelector(selector));
		  }
		});
	}
	
	public List<WebElement> getElementsByCss(String selector) {
		return (new WebDriverWait(driver, 10)).until(new ExpectedCondition<List<WebElement>>() {
		  public List<WebElement> apply(WebDriver driver) {
			  return driver.findElements(By.cssSelector(selector));
		  }
		});
	}
	
	public String getTitlePage() {
		return (new WebDriverWait(driver, 10)).until(new ExpectedCondition<String>() {
		  public String apply(WebDriver driver) {
			  return driver.getTitle();
		  }
		});
	}
}
//d.findElement(By.cssSelector("h3.h a:link")).getAttribute("href").contains("selenium")