package de.trivago.Room5.common;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ReadProps {

  private static final String BASE_URL = "BASE_URL";
  public static final String BROWSER = "BROWSER";
  private static ReadProps instance;

  private Properties props;

  public static ReadProps get() throws IOException {
    if (instance == null) {
      instance = new ReadProps();
    }
    return instance;
  }

  public ReadProps() throws IOException {
    props = new Properties();
    URL resource = ReadProps.class.getClassLoader().getResource("test.properties");
    props.load(resource.openStream());
  }

  public String base_url() {
    return this.props.getProperty(BASE_URL);
  }

  public String getBrowser(){
    return this.props.getProperty(BROWSER);
  }


  

}
