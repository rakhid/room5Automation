package de.trivago.Room5.pageObjects;


import de.trivago.Room5.common.Page;
import de.trivago.Room5.common.ReadProps;
import de.trivago.Room5.common.TestWebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.List;


public class Room5MainPage extends Page{

  public ReadProps readProps;
  public List<String> tabs_windows=null;

  /*For main heading as "Editor's Pick"*/
  @FindBy(how = How.XPATH, using = "//h2[@class='section-title main']/span[contains(text(),'Editor')]")
  private WebElement mainPageHeading=null;

  /*To check the heading after destination has been clicked from hamburger menu e.g."CANADA/QUEBEC/MONTREAL"*/
  @FindBy(how = How.XPATH, using = "//div/h1")
  private WebElement searchResultPageHeading=null;

  /*Identify country selection dropdown*/
  @FindBy(how = How.CLASS_NAME, using = "locales-dropdown")
  private WebElement countryDropDown=null;

  /*selecting values from dropdown*/
  private Select dropDown= new Select(testWebDriver.findElement(By.className("locales-dropdown")));

  @FindBy(how = How.CLASS_NAME, using = "nav-icon")
  private WebElement hamburgerMenu=null;

  @FindBy(how = How.LINK_TEXT, using = "Contact")
  private WebElement contactLink=null;

  @FindBy(how = How.LINK_TEXT, using = "About")
  private WebElement aboutLink=null;

  @FindBy(how = How.LINK_TEXT, using = "Download App")
  private WebElement appLink=null;

  @FindBy(how = How.LINK_TEXT, using = "Cookie Policy")
  private WebElement policyLink=null;

  @FindBy(how = How.LINK_TEXT, using = "Legal Information")
  private WebElement infoLink=null;

  @FindBy(how = How.LINK_TEXT, using = "trivago")
  private WebElement trivagoLink=null;
  //trivago
  @FindBy(how = How.XPATH, using ="//button[contains(text(),'Inspire')]")
  private WebElement subscribeButton=null;

  @FindBy(how = How.NAME, using = "email")
  private WebElement subscriptionEmailID=null;

  @FindBy(how = How.ID, using = "confirm")
  private WebElement subscriptionCheckbox=null;

  @FindBy(how= How.XPATH, using = "//div[@class='Cookie__button']")
  private WebElement cookieButtonOK=null;


  @FindBy(how = How.XPATH, using = "//section[@class='newsletter']/p[contains(text(),'You are now checked-in!')]")
  private WebElement subscriptionSuccessMsg=null;

  @FindBy(how=How.XPATH, using = "//ul/li[1]/ul")
  private WebElement listDestinations;

  @FindBy(how=How.CLASS_NAME, using ="search-icon")
  private WebElement searchIcon;

  @FindBy(how=How.XPATH, using ="//input[@class='input search-input']")
  private WebElement searchBar;

  @FindBy(how=How.XPATH, using ="//div[@class='container-wide search-results']/h3[@class='section-title']/span")
  private WebElement searchResultsText;

  @FindBy(how=How.XPATH, using ="//div/a/span[@class='facebook']")
  private WebElement linkToFB;

  @FindBy(how=How.XPATH, using ="//div/a/span[@class='twitter']")
  private WebElement linkToTwitter;

  @FindBy(how=How.XPATH, using ="//div/a/span[@class='pinterest']")
  private WebElement linkToPinterest;

  @FindBy(how=How.XPATH, using ="//div/a/span[@class='instagram']")
  private WebElement linkToInsta;

  @FindBy(how=How.XPATH, using ="//div/div[contains(text(),'Please mark the checkbox.')]")
  private WebElement checkBoxError;

  /*This is Faulty case as there are 2 elements for the same text [ needs to be fixed at source code level  */
  @FindBy(how=How.XPATH, using ="//div/div[contains(text(),'Please enter a valid e-mail address')]")
  private WebElement emailError;


  public Room5MainPage(TestWebDriver driver)
  {
    super(driver);
    PageFactory.initElements(new AjaxElementLocatorFactory(TestWebDriver.getDriver(),10),this);
  }

  public void scrollAndClickContact() throws IOException,InterruptedException{
    cookieButtonOK.click();
    testWebDriver.scrollToElement(contactLink);
    contactLink.click();
    /*Inserting Thread.sleep as implicit wait and wait donot work.The usage here is to wait for another tab to open and load*/
    Thread.sleep(5000);
    /*Transferring control to another tab*/
    testWebDriver.switchTab(1);
  }

  public void gotoHompage() throws IOException,InterruptedException{
    /*Checking that the heading is "Editor's pick "*/
    testWebDriver.waitForPageToLoad();
    assertTrue(mainPageHeading.isDisplayed());

  }
  public void scrollToInspireButton() throws IOException,InterruptedException{
    assertTrue(mainPageHeading.isDisplayed());
    cookieButtonOK.click();
    testWebDriver.scrollToElement(subscribeButton);

  }

  public void fillSubscriptionDetails( String EmailID) throws IOException,InterruptedException{
    subscriptionEmailID.clear();
    /*Used trim function here as the website cannot handle email addresses with whitespaces*/
    subscriptionEmailID.sendKeys(EmailID.trim());
    if(!subscriptionCheckbox.isSelected())
      subscriptionCheckbox.click();
  }

  public void fillSubscriptionDetailsNoCb( String EmailID) throws IOException,InterruptedException{
    subscriptionEmailID.clear();
    subscriptionEmailID.sendKeys(EmailID);
  }

  public void requestSubscription( ) throws IOException,InterruptedException{
    subscribeButton.click();
  }

  public void assertSubscription( ) throws IOException,InterruptedException{
    testWebDriver.waitForElementToAppear(subscriptionSuccessMsg);
    assertTrue(subscriptionSuccessMsg.isDisplayed());
  }


  public void scrollToCountryDropDown() throws  IOException,InterruptedException{
    assertTrue(mainPageHeading.isDisplayed());
    cookieButtonOK.click();
    testWebDriver.scrollToElement(countryDropDown);

  }

  public void selectCountryFromDropDown( String countryName) throws  IOException,InterruptedException{
    dropDown.selectByVisibleText(countryName);
    testWebDriver.waitForPageToLoad();
  }


  public void selectDestinationFromDropDown( String destinationName) throws  IOException,InterruptedException{
    hamburgerMenu.click();
    testWebDriver.waitForPageToLoad();
    /*Here we traverse every element of the list till we reach the text we are searching for*/
    List<WebElement> destinationsList=listDestinations.findElements(By.tagName("li"));
    for (WebElement li : destinationsList) {
      if (li.getText().equalsIgnoreCase(destinationName)) {
        /*Find the linktext of the element in the list and click it*/
        li.findElement(By.linkText(destinationName)).click();
        testWebDriver.waitForPageToLoad();
      }
    }
  }

  public void checkSearchResults(String country , String destination){
    assertTrue(searchResultPageHeading.getText().contains(country.toUpperCase()));
    assertTrue(searchResultPageHeading.getText().contains(destination.toUpperCase()));
  }

  public void clickSearchIcon(){
    searchIcon.click();
    testWebDriver.waitForPageToLoad();
  }

  public void checkSearchBar(){
    testWebDriver.waitForElementToBeEnabled(searchBar);
    assertTrue(searchBar.isDisplayed());
  }

  public void fillSearchBar( String destination){
    searchBar.click();
    searchBar.sendKeys(destination);
    searchBar.sendKeys(Keys.ENTER);
    testWebDriver.waitForPageToLoad();
  }

  public void checkSearchedLocationResults(){
    assertTrue(searchResultsText.isDisplayed());
    assertTrue(searchResultsText.getText().compareToIgnoreCase("Search Results")==0);

  }

  public void scrollToBottomOfThePage() throws IOException,InterruptedException{
    testWebDriver.scrollToElement(trivagoLink);
    testWebDriver.waitForPageToLoad();
  }


  public void checkLinks() throws IOException,InterruptedException{
    assertTrue(aboutLink.isDisplayed());
    assertTrue(contactLink.isDisplayed());
    assertTrue(appLink.isDisplayed());
    assertTrue(policyLink.isDisplayed());
    assertTrue(infoLink.isDisplayed());
  }



  public void checkSocialMediaLinks() throws IOException,InterruptedException{
    assertTrue(linkToFB.isDisplayed());
    assertTrue(linkToFB.isDisplayed());
    assertTrue(linkToInsta.isDisplayed());
    assertTrue(linkToPinterest.isDisplayed());

  }

  public void checkErrorForEmail(){
    assertTrue(emailError.isDisplayed());
    assertTrue(emailError.getText().compareToIgnoreCase("Please enter a valid e-mail address")==0);

  }

  public void checkErrorForCheckBox(){
    assertTrue(checkBoxError.isDisplayed());
    assertTrue(checkBoxError.getText().compareToIgnoreCase("Please mark the checkbox.")==0);

  }
}
