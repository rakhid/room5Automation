package cucumber.steps;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.trivago.Room5.common.ManageTest;
import de.trivago.Room5.common.ReadProps;
import de.trivago.Room5.common.TestWebDriver;
import de.trivago.Room5.pageObjects.Room5ContactPage;
import de.trivago.Room5.pageObjects.Room5MainPage;
import java.io.IOException;
import java.util.List;



public class step_definitions extends ManageTest {

  Room5MainPage room5MainPage;
  Room5ContactPage room5ContactPage;
  static public Scenario currScenario;

  public step_definitions() throws IOException {
    super();
    ReadProps readProps=new ReadProps();
  }


  @Before
  public void before(Scenario scenario) throws Exception {
    setup();
    currScenario=scenario;
    initObjects();
  }

  public void initObjects() throws IOException {
    testWebDriver.waitForPageToLoad();
    room5MainPage =new Room5MainPage(testWebDriver);
    room5ContactPage = new Room5ContactPage(testWebDriver);
  }


  @After
  public void tearDown(Scenario scenario) {
    try {
      TestWebDriver.getDriver().quit();

      if (scenario.isFailed()) {
        scenario.embed(testWebDriver.takeScreenShot(), "image/png");
      }

    } finally {
      Exception e;
    }
  }

 /*Common step in all test cases*/

  @Given("^I am on the Room5 application homepage$")
  public void gotoHompage( ) throws InterruptedException, IOException {
    room5MainPage.gotoHompage();
  }

  /*---------------------------------------Test Case 1---------------------------------------*/

  @When("^I scroll to the bottom of the page and click contact$")
  public void scrollAndClickContact( ) throws InterruptedException, IOException {
    room5MainPage.scrollAndClickContact();
  }

  @Then("^I should be redirected to the contact page$")
  public void assertRedirectionContactPage() throws InterruptedException {
    room5ContactPage.checkContactPage();
  }

  @When("^I fill in valid details and acknowledge the checkbox$")
  public void fillDetailsOnContactPage(DataTable table ) throws InterruptedException, IOException {
    List<List<String>> lstTestData= table.raw();
    String name = lstTestData.get(1).get(0);
    String emailid = lstTestData.get(1).get(1);
    String comments = lstTestData.get(1).get(2);
    room5ContactPage.fillContactPage(name,emailid,comments);
  }


  @When("^I submit the form$")
  public void fsubmitDetails() throws InterruptedException, IOException {
    room5ContactPage.submitContactForm();
  }

  @Then("^I should see the Message Sent Successfully! message$")
  public void assertSubmitMessage() throws InterruptedException {
    room5ContactPage.checkFromSuccess();
  }


  /*------------------------------------Test Case 2----------------------------------------*/

  @When("^I scroll to the bottom of the page to locate subscription box$")
  public void scrollToSubcriptionBox() throws InterruptedException ,IOException {
    room5MainPage.scrollToInspireButton();

  }

  @When("^I fill in (.*) emailID and acknowledge the checkbox$")
  public void emaildIdToSubscribe(String emaildID ) throws InterruptedException ,IOException {
    room5MainPage.fillSubscriptionDetails(emaildID);

  }

  @When("^I submit by clicking on Inspire Me$")
  public void clickInspireMe() throws InterruptedException ,IOException {
    room5MainPage.requestSubscription();

  }

  @Then("^I should see an error message for email$")
  public void verifyEmailID() throws InterruptedException {
    room5MainPage.checkErrorForEmail();
  }

  @When("^I fill in  (.*) emailID and donot acknowledge the checkbox$")
  public void emaildIdToSubscribeNoCb(String emaildID) throws InterruptedException ,IOException {
    room5MainPage.fillSubscriptionDetailsNoCb(emaildID);

  }

  @Then("^I should see an error message for checkbox$")
  public void verifyCheckBox() throws InterruptedException {
    room5MainPage.checkErrorForCheckBox();
  }


  @When("^I should be able to see the You are now checked-in! message$")
  public void checkSubscriptionSuccess() throws InterruptedException ,IOException {
    room5MainPage.assertSubscription();

  }


/*-------------------------Test case 3--------------------------------------*/


  @When("^I scroll to the bottom of the page to locate country drop down$")
  public void scrollToDropDown() throws InterruptedException ,IOException {
    room5MainPage.scrollToCountryDropDown();

  }


  @When("^I select a country$")
  public void selectCountry(DataTable table ) throws InterruptedException ,IOException {
    List<List<String>> lstTestData= table.raw();
    String countryName = lstTestData.get(1).get(0);
    System.out.println(countryName);
    room5MainPage.selectCountryFromDropDown(countryName);
  }

  @When("^I click on a destination$")
  public void selectDestination(DataTable table ) throws InterruptedException ,IOException {
    List<List<String>> lstTestData= table.raw();
    String destinationName = lstTestData.get(1).get(0);
    room5MainPage.selectDestinationFromDropDown(destinationName);
  }


  @Then("^I should be redirected to the relavent search page$")
  public void assertSearchResultPage(DataTable table) throws InterruptedException {
    List<List<String>> lstTestData= table.raw();
    String country = lstTestData.get(1).get(0);
    String destination =lstTestData.get(1).get(1);
    room5MainPage.checkSearchResults(country,destination);

  }
  /*------------------------------------Test Case 4--------------------------------------*/

  @When("^I click on search icon$")
  public void selectSearch() throws InterruptedException ,IOException {
    room5MainPage.clickSearchIcon();
  }

  @Then("^I should see a search bar$")
  public void checkForSearchBar() throws InterruptedException ,IOException {
    room5MainPage.checkSearchBar();
  }


  @When("^I submit the location$")
  public void locationSearch(DataTable table ) throws InterruptedException ,IOException {
    List<List<String>> lstTestData= table.raw();
    String locationName = lstTestData.get(1).get(0);
    room5MainPage.fillSearchBar(locationName);
  }

  @Then("^I should see the search results message$")
  public void verifySearchResults() throws InterruptedException ,IOException {
    room5MainPage.checkSearchedLocationResults();
  }

  /*----------------------------Test case 5-----------------------------------------------*/

  //common step for test case 5 and 6
  @When("^I go to the bottom of the page$")
  public void gotToBottom() throws InterruptedException ,IOException {
    room5MainPage.scrollToBottomOfThePage();
  }

  @Then("^I should see 6 hyperlinks$")
  public void checkLinks() throws InterruptedException ,IOException {
    room5MainPage.checkLinks();
  }


  /*-----------------------------------Test Case 6--------------------------------------*/

  @Then("^I should see 4 social media links$")
  public void checkSocialMediaLinks() throws InterruptedException ,IOException {
    room5MainPage.checkSocialMediaLinks();
  }


}
