package de.trivago.Room5.common;

import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static java.lang.System.*;
import static java.util.Objects.isNull;


public class DriverFactory {

  private String driverType;
  private String INPUT_ZIP_FILE_IEDRIVER = null;
  private String INPUT_ZIP_FILE_CHROMEDRIVER = null;
  private String OUTPUT_FOLDER = null;
  private boolean enableJavascript = true;
  private String LOCAL_FIREFOX_X11_PATH = "/opt/local/bin/firefox-x11";
  private String LOCAL_X11_DISPLAY = ":5";
  private Unzip unZip;
  ReadProps readProps;

  public DriverFactory(ReadProps _readProps) {
    readProps=_readProps;
  }

  public WebDriver loadDriver(String browser) throws InterruptedException, IOException {
    String Separator = getProperty("file.separator");
    File parentDir = new File(getProperty("user.dir"));
    return loadDriver(enableJavascript, browser);
  }

  public String driverType() throws InterruptedException {
    return driverType.trim();
  }

  public WebDriver getDriver() throws IOException, InterruptedException {
    return loadDriver(true, "firefox");
  }

  public WebDriver loadDriver(boolean enableJavascript, String browser ) throws InterruptedException, IOException {
    switch (browser) {
      case "firefox":
        driverType = getProperty("web.driver", "Firefox");
        return createFirefoxDriver(enableJavascript);

      case "ie":
        unZip = new Unzip();
        unZip.unZipIt(INPUT_ZIP_FILE_IEDRIVER, OUTPUT_FOLDER);
        Thread.sleep(1500);
        driverType = setProperty("webdriver.ie.driver", OUTPUT_FOLDER + "IEDriverServer.exe");
        driverType = getProperty("webdriver.ie.driver");

        return createInternetExplorerDriver();


      case "chrome":
        unZip = new Unzip();
        unZip.unZipIt(INPUT_ZIP_FILE_CHROMEDRIVER, OUTPUT_FOLDER);
        Thread.sleep(1500);
        driverType = setProperty("webdriver.chrome.driver", OUTPUT_FOLDER + "chromedriver.exe");
        driverType = getProperty("webdriver.chrome.driver");
        return createChromeDriver();

      case "safari":
        return createSafariDriver();


      default:


        driverType = getProperty("web.driver", "Firefox");
        return createFirefoxDriver(enableJavascript);
    }
  }

  private WebDriver createSafariDriver(){
    SafariOptions options = new SafariOptions();
    options.setUseCleanSession(true);

    return new SafariDriver(options);
  }

  private WebDriver createFirefoxDriver(boolean enableJavascript) {

    boolean PROXY_ENABLED = Boolean.parseBoolean(System.getenv("PROXY_ENABLED"));
    DesiredCapabilities capabilities = DesiredCapabilities.firefox();

    if(PROXY_ENABLED) {
      String httpProxyHost = System.getenv("FT_HTTP_PROXY_HOST");
      Integer httpsProxyPort = parseInt(System.getenv("FT_HTTP_PROXY_PORT"));
      String noProxy = System.getenv("http_no_proxy");
      Proxy proxy = new Proxy();
      proxy.setHttpProxy(String.format("%s:%s", httpProxyHost, httpsProxyPort));
      FirefoxProfile profile=createCIServerFireFoxProfile(httpProxyHost, httpsProxyPort, noProxy);
      capabilities.setCapability(FirefoxDriver.PROFILE, profile);
      capabilities.setCapability(CapabilityType.PROXY, proxy);
      capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, enableJavascript);
    }

    FirefoxDriverManager.getInstance().setup();
    return new FirefoxDriver(capabilities);
  }

  private WebDriver createInternetExplorerDriver() throws IOException {
    Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
    DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
    ieCapabilities.setCapability("ignoreZoomSetting", true);
    return new InternetExplorerDriver(ieCapabilities);
  }


  private WebDriver createChromeDriver() {
    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    Map<String, String> prefs = new Hashtable<>();
    prefs.put("download.prompt_for_download", "false");
    prefs.put("download.default_directory", "C:\\Users\\openlmis\\Downloads");

    String[] switches = {"--start-maximized", "--ignore-certificate-errors"};
    capabilities.setCapability("chrome.prefs", prefs);
    capabilities.setCapability("chrome.switches", Arrays.asList(switches));

    return new ChromeDriver(capabilities);
  }

  public FirefoxProfile createCIServerFireFoxProfile(String proxyHost, int proxyPort, String noProxy)
  {
    FirefoxProfile profile = new FirefoxProfile();
    profile.setPreference("network.proxy.type", "1");
    profile.setPreference("network.proxy.http", proxyHost);
    profile.setPreference("network.proxy.http_port", proxyPort);
    profile.setPreference("network.proxy.ssl", proxyHost);
    profile.setPreference("network.proxy.ssl_port", proxyPort);
    profile.setPreference("network.proxy.socks", proxyHost);
    profile.setPreference("network.proxy.socks_port", proxyPort);
    profile.setPreference("network.proxy.ftp", proxyHost);
    profile.setPreference("network.proxy.ftp_port", proxyPort);
    profile.setPreference("network.proxy.no_proxies_on", noProxy);
    return profile;
  }

}