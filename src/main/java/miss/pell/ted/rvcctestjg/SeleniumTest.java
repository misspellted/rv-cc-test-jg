package miss.pell.ted.rvcctestjg;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.Callable;

public abstract class SeleniumTest implements Callable<String>, Runnable {

    private WebDriver webDriver;
    private String result;

    /**
     * Provides an identifier associated with the test.
     *
     * @return The identifier associated with the test.
     */
    public abstract String id();

    /**
     * Sets the {@link WebDriver} on which to run the test.
     *
     * @param webDriver The {@link WebDriver} on which to run the test.
     */
    public final void use(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Provides access to the {@link WebDriver} on which the test is to be run.
     *
     * @return The {@link WebDriver} on which the test is to be run.
     */
    protected WebDriver webDriver() {
        return webDriver;
    }

    protected void fail(String format, Object... objects) {
        result = String.format(format, objects);
    }

    @Override
    public final String call() throws Exception {
        if (webDriver == null) {
            result = "A Selenium WebDriver was not configured to be used.";
        } else {
            run();
        }

        return result;
    }

}
