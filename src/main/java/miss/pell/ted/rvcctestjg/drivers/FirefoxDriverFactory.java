package miss.pell.ted.rvcctestjg.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.util.Properties;

public class FirefoxDriverFactory implements WebDriverFactory {

    private static final String ID = "firefox";
    private static final String WEBDRIVER = "webdriver";
    private static final String WEBDRIVER_GECKO_DRIVER = "webdriver.gecko.driver";

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
        String geckoDriverPath = properties.getProperty(WEBDRIVER_GECKO_DRIVER);

        if (geckoDriverPath != null && !geckoDriverPath.isEmpty()) {
            System.setProperty(WEBDRIVER_GECKO_DRIVER, geckoDriverPath);
        }
    }

    @Override
    public boolean ready() {
        boolean available = false;

        String geckoDriverPath = System.getProperty(WEBDRIVER_GECKO_DRIVER);

        if (geckoDriverPath != null && !geckoDriverPath.isEmpty()) {
            File possibility = new File(geckoDriverPath);

            available = possibility.exists() && !possibility.isDirectory();
        }

        return available;
    }

    @Override
    public WebDriver create() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("marionette", true);
        return new FirefoxDriver(firefoxOptions);
    }

}
