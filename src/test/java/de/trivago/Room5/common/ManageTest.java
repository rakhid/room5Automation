package de.trivago.Room5.common;


import org.openqa.selenium.remote.UnreachableBrowserException;


import java.io.IOException;

import static java.lang.System.getProperty;

public class ManageTest
{
  protected static String url;
  protected static TestWebDriver testWebDriver;
  private static DriverFactory driverFactory;
  public ReadProps readProps;

  public ManageTest() throws IOException {
    readProps=new ReadProps();
    driverFactory=new DriverFactory(readProps);
  }

  public void setup() throws Exception {
    String browser = readProps.getBrowser();
     url = readProps.base_url();


      loadDriver(browser);
      addTearDownShutDownHook();
    testWebDriver.getUrl(url);



  }

  protected void addTearDownShutDownHook() {
    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run() {
        try{
          testWebDriver.quitDriver();
        }
        catch (UnreachableBrowserException e) {
          e.printStackTrace();
        }
      }
    });
  }

  protected void loadDriver(String browser) throws InterruptedException, IOException {
    testWebDriver = new TestWebDriver(driverFactory.loadDriver(browser));
    testWebDriver.deleteAllCookies();

  }
}
