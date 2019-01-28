package miss.pell.ted.rvcctestjg;

import miss.pell.ted.rvcctestjg.cases.NavigationBackgroundChangesWhenPageScrolled;
import miss.pell.ted.rvcctestjg.drivers.FirefoxDriverFactory;
import miss.pell.ted.rvcctestjg.drivers.HtmlUnitDriverFactory;
import miss.pell.ted.rvcctestjg.drivers.HtmlUnitWithJsDriverFactory;
import miss.pell.ted.rvcctestjg.drivers.WebDriverFactory;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The launcher of an execution request of the program.
 */
public class Launcher {

    private static final int ARGUMENT_PROPERTIES_FILE_PATH = 0;

    private static void displayUsage() {
        System.out.println("Usage:");
        System.out.println("  java -jar rvcctestjg.jar [options]");
        System.out.println();
        System.out.println("Options:");
        System.out.println("  <none>     - Displays this usage information.");
        System.out.println();
        System.out.println("  <path>     - Specifies the configuration file path governing the execution of");
        System.out.println("               the Selenium tests. See the README for more details.");
    }

    private static List<WebDriverFactory> webDriverFactories() {
        List<WebDriverFactory> webDriverFactories = new ArrayList<>(0);

        // For now, the web driver factories are hard-coded into existence, available for usage (if they're available).
        webDriverFactories.add(new HtmlUnitDriverFactory());
        webDriverFactories.add(new HtmlUnitWithJsDriverFactory());
        webDriverFactories.add(new FirefoxDriverFactory());

        return webDriverFactories;
    }

    private static void runTest(SeleniumTest test, WebDriverFactory webDriverFactory) {
        // Track the created instance of the WebDriver, for cleaning up, in the case of an exception, for example.
        WebDriver webDriver = webDriverFactory.create();

        // Provide a new instance of the WebDriver for which to run the test.
        test.use(webDriver);

        try {
            System.out.printf("Running '%s' on '%s'\n",
                    test.id(), webDriverFactory.id());

            String result = test.call();

            if (result == null) {
                System.out.printf("'%s' on '%s' passed.\n",
                        test.id(), webDriverFactory.id());
            } else {
                System.out.printf("'%s' on '%s' failed: %s.\n",
                        test.id(), webDriverFactory.id(), result);
            }
        } catch (Exception ex) {
            System.out.printf("'%s' on '%s' failed: %s.\n",
                    test.id(), webDriverFactory.id(), ex.getLocalizedMessage());
            ex.printStackTrace(System.err);
        } finally {
            try {
                webDriver.quit();
            } catch (Throwable thrown) {
                System.err.println(thrown.getLocalizedMessage());
            }
        }
    }

    /**
     * The entry point into the program.
     *
     * @param arguments The array of textual arguments included in the execution request.
     */
    public static void main(String[] arguments) {
        if (arguments.length == 0) {
            displayUsage();
        } else {
            // Allow specifying the desired WebDriver to use, but default to the basic HtmlUnitDriver ("html").
            String propertiesFilePath = arguments[ARGUMENT_PROPERTIES_FILE_PATH];

            Properties properties = new Properties();

            try (FileInputStream fileInputStream = new FileInputStream(propertiesFilePath)) {
                properties.load(fileInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // And now get the associated factory for the desired or default WebDriver.
            WebDriverFactory webDriverFactory = webDriverFactories().stream()
                    .filter(candidate -> candidate.provides(properties))
                    .findFirst()
                    .orElse(null);

            // Drop a note if the desired factory is not available, otherwise, get to running some tests!
            if (webDriverFactory == null) {
                System.out.printf("A web driver was not compatible with the properties configuration file at '%s'.\n",
                        propertiesFilePath);
            } else {
                System.out.printf("The '%s' web driver is compatible with the properties configuration file at '%s'.\n",
                        webDriverFactory.id(), propertiesFilePath);

                System.out.printf("Configuring the '%s' web driver.\n",
                        webDriverFactory.id());

                webDriverFactory.configure(properties);

                if (webDriverFactory.ready()) {

                    List<SeleniumTest> testPlan = new ArrayList<>(0);

                    // Example adapted Selenium tests.
//                    testPlan.add(GoogleSearchTermInPageTitleAfterSubmission.searchFor("Cheese!"));
//                    testPlan.add(GoogleSuggestReturnsSuggestionsAfterSubmission.suggestFor("Cheese"));

                    // Assessment Selenium tests.
                    testPlan.add(new NavigationBackgroundChangesWhenPageScrolled());

                    for (SeleniumTest test : testPlan) {
                        runTest(test, webDriverFactory);
                    }
                } else {
                    System.out.printf("Failed to configure the '%s' web driver from the properties configuration file at '%s'.\n",
                            webDriverFactory.id(), propertiesFilePath);
                }
            }
        }
    }

}
