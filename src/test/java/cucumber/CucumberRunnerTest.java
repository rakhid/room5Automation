package cucumber;

import cucumber.api.CucumberOptions;
import org.junit.Before;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
    strict = true,
    features = "src/test/java/cucumber/feature/Room5FeatureFile.feature",
    glue = "cucumber.steps",
    plugin = {"pretty", "html:target/cucumber-html-report/" }

)
public class CucumberRunnerTest {



}
