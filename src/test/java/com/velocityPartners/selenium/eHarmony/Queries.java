package com.velocityPartners.selenium.eHarmony;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Queries {
	private WebDriver driver;
	
	public Queries(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean getLink() {
		return (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		  public Boolean apply(WebDriver d) {
			  return d.findElement(By.cssSelector("h3.r > a:link"))
					  .getAttribute("href").contains("selenium");
		  }
		});
	}
}
//d.findElement(By.cssSelector("h3.h a:link")).getAttribute("href").contains("selenium")