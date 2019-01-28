package miss.pell.ted.rvcctestjg.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.Properties;

public class HtmlUnitDriverFactory implements WebDriverFactory {

    private static final String ID = "html";
    private static final String WEBDRIVER = "webdriver";

    @Override
    public String id() {
        return ID;
    }

    @Override
    public boolean provides(Properties properties) {
        return properties.containsKey(WEBDRIVER) && properties.getProperty(WEBDRIVER).equals(ID);
    }

    @Override
    public void configure(Properties properties) {

    }

    @Override
    public boolean ready() {
        return true;
    }

    @Override
    public WebDriver create() {
        return new HtmlUnitDriver();
    }

}
