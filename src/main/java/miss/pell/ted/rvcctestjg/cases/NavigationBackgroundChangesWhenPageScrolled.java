package miss.pell.ted.rvcctestjg.cases;

import miss.pell.ted.rvcctestjg.SeleniumTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class NavigationBackgroundChangesWhenPageScrolled extends SeleniumTest {

    private static final String ATTRIBUTE_CLASS = "class";
    private static final String CLASS_STYLE_TRANSPARENT_NAV = "transparent-nav";

    @Override
    public String id() {
        return "[CC] Navigation Background Changes when Page Scrolled";
    }

    private boolean isTransparent(WebElement element) {
        return element.getAttribute(ATTRIBUTE_CLASS).contains(CLASS_STYLE_TRANSPARENT_NAV);
    }

    @Override
    public void run() {
        webDriver().get("http://www.creditcards.com");

        // First get the indication that the initial transparency is the situation.
        WebElement header = webDriver().findElement(By.xpath("//header"));

        // Check to see that the 'transparent-nav' style class is applied to the header element.
        boolean transparent = isTransparent(header);

        if (transparent) {
            // Scroll down a little.
            ((JavascriptExecutor) webDriver()).executeScript("scroll(0, 100);");

            // Wait up to 8 seconds.
            long end = System.currentTimeMillis() + 8000;
            while (System.currentTimeMillis() < end && transparent) {
                transparent = isTransparent(header);
            }

            // If the transparency is still there, then the class style was not removed.
            if (isTransparent(header)) {
                fail("The transparent-nav class was not removed when the page was scrolled.");
            }
        } else {
            fail("The header element did not have the initial transparent-nav CSS class.");
        }

        webDriver().quit();
    }

}
