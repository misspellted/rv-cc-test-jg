package miss.pell.ted.rvcctestjg.drivers;

import org.openqa.selenium.WebDriver;

import java.util.Properties;

public interface WebDriverFactory {

    String id();

    boolean provides(Properties properties);

    void configure(Properties properties);

    boolean ready();

    WebDriver create();

}
