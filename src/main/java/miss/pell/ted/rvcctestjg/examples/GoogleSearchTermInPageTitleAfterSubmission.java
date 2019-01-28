package miss.pell.ted.rvcctestjg.examples;

import miss.pell.ted.rvcctestjg.SeleniumTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GoogleSearchTermInPageTitleAfterSubmission extends SeleniumTest {

    private String searchTerm;

    @Override
    public String id() {
        return String.format("Google Search Term (%s) in Page Title after Submission",
                searchTerm);
    }

    @Override
    public void run() {
        if (searchTerm == null || searchTerm.isEmpty()) {
            fail("A search term is required.");
        } else {
            // Visit Google.
            webDriver().get("http://www.google.com");

            // Find the text input element by its name.
            WebElement element = webDriver().findElement(By.name("q"));

            // Provide the search term to use.
            element.sendKeys(searchTerm);

            // Now submit the form. WebDriver will find the form for us from the element.
            element.submit();

            // Wait up to 5 seconds.
            long end = System.currentTimeMillis() + 5000;
            while (System.currentTimeMillis() < end) {
                if (webDriver().getTitle().contains(searchTerm)) {
                    break;
                }
            }

            // Inspect the title - it should be updated with the search term.
            if (!webDriver().getTitle().contains(searchTerm)) {
                fail("Expected search term '%s' in the title after submitting query.",
                        searchTerm);
            }

            webDriver().quit();
        }
    }

    public static GoogleSearchTermInPageTitleAfterSubmission searchFor(String searchTerm) {
        GoogleSearchTermInPageTitleAfterSubmission test = new GoogleSearchTermInPageTitleAfterSubmission();

        test.searchTerm = searchTerm;

        return test;
    }

}
