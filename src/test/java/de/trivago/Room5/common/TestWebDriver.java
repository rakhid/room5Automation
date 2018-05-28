package de.trivago.Room5.common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class TestWebDriver {

  public static WebDriver driver;
  private int DEFAULT_WAIT_TIME = 10;

  public TestWebDriver(WebDriver driver) throws IOException, InterruptedException {
    TestWebDriver.driver = driver;
    setBrowserSize(new Dimension(968, 1024));
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  public WebElement findElement(By by) {
    return driver.findElement(by);
  }

  public List<WebElement> findElements(By by) {
    return driver.findElements(by);
  }


  public void getUrl(String url) {
    driver.get(url);
  }

  public void refreshBrowser(){ driver.navigate().refresh();}

  public static WebDriver getDriver() {
    return driver;
  }

  public void setImplicitWait(int defaultTimeToWait) {
    driver.manage().timeouts().implicitlyWait(defaultTimeToWait, TimeUnit.SECONDS);
  }

  public void deleteAllCookies(){
    driver.manage().deleteAllCookies();
  }

  public void quitDriver() {
    driver.quit();
  }

  public void navigateBack(){
    driver.navigate().back();
  }

  public byte[] takeScreenShot(){
    final byte[] screenshot = ((TakesScreenshot) driver)
            .getScreenshotAs(OutputType.BYTES);
    return screenshot;
  }

  public void maximizeBrowser() {
    driver.manage().window().maximize();
  }

  public void setBrowserSize(Dimension dim)
  {
    driver.manage().window().setSize(dim);
  }


  public void waitForPageToLoad() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    (new WebDriverWait(driver, DEFAULT_WAIT_TIME)).until((ExpectedCondition<Boolean>) d -> (((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete")));
  }

  public void waitForElementsToAppear(final List<WebElement> elements) {
    (new WebDriverWait(driver, DEFAULT_WAIT_TIME)).until((ExpectedCondition<Boolean>) d -> elements.size() == elements.stream().map(WebElement::isDisplayed).toArray().length);
  }

  public void waitForElementToAppear(final WebElement element) {
    (new WebDriverWait(driver, DEFAULT_WAIT_TIME)).until((ExpectedCondition<Boolean>) d -> (element.isDisplayed()));
  }

  public void click(final WebElement element) {
    Actions action = new Actions(driver);
    action.click(element).perform();
  }


  public void switchTab( int tabNumber) {
    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
    driver.switchTo().window(tabs.get(tabNumber));

  }

  public void waitForElementToBeEnabled(final WebElement element) {
    (new WebDriverWait(driver, DEFAULT_WAIT_TIME)).until((ExpectedCondition<Boolean>) d -> (element.isEnabled()));
  }

  public String getText(WebElement element) {
    return element.getText();
  }

  public String getAttribute(WebElement element, String value) {
    return element.getAttribute(value);
  }

  public static void scrollToElement(WebElement ele) throws InterruptedException, IOException {

    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(false);", ele);
    Thread.sleep(1000);
  }

}
