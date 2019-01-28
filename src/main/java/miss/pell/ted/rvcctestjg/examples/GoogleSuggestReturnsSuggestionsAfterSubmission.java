package miss.pell.ted.rvcctestjg.examples;

import miss.pell.ted.rvcctestjg.SeleniumTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GoogleSuggestReturnsSuggestionsAfterSubmission extends SeleniumTest {

    private String searchTerm;

    @Override
    public String id() {
        return String.format("Google Suggest (%s) Returns Suggestions after Submission",
                searchTerm);
    }

    @Override
    public void run() {
        // Go to the Google Suggest home page
        webDriver().get("http://www.google.com/webhp?complete=1&hl=en");

        // Enter the query string
        WebElement query = webDriver().findElement(By.name("q"));
        query.sendKeys(searchTerm);

        List<WebElement> divElements = new ArrayList<>(0);
        long end = System.currentTimeMillis() + 5000;

        // Sleep until the div we want is visible or the wait interval has transpired.
        while (System.currentTimeMillis() < end && divElements.size() == 0) {

            //findElement would throw a NoSuchElement exception if the element is not present.
            // Using findElements is a better approach
            divElements.addAll(webDriver().findElements(By.className("UUbT9")));
        }

        // And now list the suggestions
        List<WebElement> suggestions = webDriver().findElements(By.xpath("//li[@class='sbct']"));

        if (suggestions.size() == 0) {
            fail("No suggestions were returned for '%s'",
                    searchTerm);
        }

        for (WebElement suggestion : suggestions) {
            System.out.println(suggestion.getText());
        }

        webDriver().quit();
    }

    public static GoogleSuggestReturnsSuggestionsAfterSubmission suggestFor(String searchTerm) {
        GoogleSuggestReturnsSuggestionsAfterSubmission test = new GoogleSuggestReturnsSuggestionsAfterSubmission();

        test.searchTerm = searchTerm;

        return test;
    }

}
