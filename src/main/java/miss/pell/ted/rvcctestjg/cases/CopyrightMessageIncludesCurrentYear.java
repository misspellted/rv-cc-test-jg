package miss.pell.ted.rvcctestjg.cases;

import miss.pell.ted.rvcctestjg.SeleniumTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;

public class CopyrightMessageIncludesCurrentYear extends SeleniumTest {

    @Override
    public String id() {
        return "[CC] Copyright Message Includes Current Year";
    }

    @Override
    public void run() {
        webDriver().get("http://www.creditcards.com");

        // Find the span of text that wraps the copyright message.
        WebElement span = webDriver().findElement(By.xpath("//footer/div[3]/div/div/span"));

        String copyrightMessage = span.getText();

        String yearText = Integer.toString(LocalDateTime.now().getYear());

        if (!copyrightMessage.contains(yearText)) {
            fail("Expected that the copyright message contained the text '%s'.",
                    yearText);
        }

        webDriver().quit();
    }

}
