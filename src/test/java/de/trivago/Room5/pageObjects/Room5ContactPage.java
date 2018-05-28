package de.trivago.Room5.pageObjects;


import de.trivago.Room5.common.Page;
import de.trivago.Room5.common.TestWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class Room5ContactPage extends Page{

  public List<String> tabs_windows=null;


  @FindBy(how = How.XPATH, using = "//h2/span")
  private WebElement contactPageHeading=null;

  @FindBy(how= How.XPATH, using = "//input[@class='contact-input']")
  private WebElement contactFullName=null;

  @FindBy(how= How.XPATH, using = "//input[@id='contact-email']")
  private WebElement contactEmailID=null;

  @FindBy(how= How.XPATH, using = "//textarea[@class='contact-msg']")
  private WebElement contactFeedbackMessage=null;

  @FindBy(how = How.XPATH, using = "//input[@id='confirm']")
  private WebElement contactCheckbox=null;

  @FindBy(how = How.XPATH, using = "//button[@class='contact-submit']")
  private WebElement contactSubmit=null;

  @FindBy(how = How.XPATH, using = "//div/p[contains(text(),'Successfully')]")
  private WebElement contactSuccessMsg=null;


  public Room5ContactPage(TestWebDriver driver)
  {
    super(driver);
    PageFactory.initElements(new AjaxElementLocatorFactory(TestWebDriver.getDriver(),10),this);
  }


  public void checkContactPage(){
    assertTrue(contactPageHeading.getText().contains("Please"));
  }

  public void fillContactPage( String fullName,String emailId , String message){
    contactFullName.sendKeys(fullName);
    contactEmailID.click();
    contactEmailID.sendKeys(emailId);
    contactFeedbackMessage.click();
    contactFeedbackMessage.sendKeys(message);
    contactCheckbox.click();

  }

  public void fillContactPageWoCheckBox( String fullName,String emailId , String message){
    contactFullName.sendKeys(fullName);
    contactEmailID.click();
    contactEmailID.sendKeys(emailId);
    contactFeedbackMessage.click();
    contactFeedbackMessage.sendKeys(message);

  }

  public void submitContactForm(){
    assertTrue(contactCheckbox.isSelected());
    contactSubmit.click();
  }

  public void checkFromSuccess(){
    testWebDriver.waitForElementToAppear(contactSuccessMsg);
    assertTrue(contactSuccessMsg.isDisplayed());
  }


}
