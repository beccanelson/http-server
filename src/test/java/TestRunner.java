import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin={"pretty"}, glue="com.rnelson.server", features="src/applicationTests/resources")

public class TestRunner {
}
