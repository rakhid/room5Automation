package de.trivago.Room5.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class Page{

  public TestWebDriver testWebDriver;



  protected Page(TestWebDriver driver) {
    this.testWebDriver = driver;
  }
  public void sendKeys(WebElement locator, String value) {
    int length = testWebDriver.getAttribute(locator, "value").length();
    for (int i = 0; i < length; i++)
      locator.sendKeys("\u0008");
    locator.sendKeys(value);
  }

  public boolean isElementPresent(By locator){
    try {
      WebElement element = testWebDriver.findElement(locator);
      if (element != null)
        return true;
      else
        return false;
    }
    catch (org.openqa.selenium.NoSuchElementException ex){
      return false;
    }
  }

}

