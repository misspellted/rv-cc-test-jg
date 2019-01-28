package miss.pell.ted.rvcctestjg.cases;

import miss.pell.ted.rvcctestjg.SeleniumTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ClickingSavingOnGasShowsGasSavingsCardOffers extends SeleniumTest {

    @Override
    public String id() {
        return "[CC] Clicking Saving on Gas Shows Gas Savings Card Offers";
    }

    private boolean isActive(WebElement element) {
        return element.getAttribute("class").contains("is-active");
    }

    private String ariaControls(WebElement element) {
        return element.getAttribute("aria-controls");
    }

    private WebElement ariaControlsTarget(WebElement element) {
        return webDriver().findElement(By.id(ariaControls(element)));
    }

    @Override
    public void run() {
        webDriver().get("http://www.creditcards.com");

        // Get a reference to the SaveOn button.
        WebElement saveOnButton = webDriver().findElement(By.xpath("//main/section[5]/div/button[2]"));

        // Get the tab panel to which the Save On button points.
        WebElement saveOnTabPanel = ariaControlsTarget(saveOnButton);

        // Click on the Save On button to show the Save On tab panel.
        saveOnButton.click();

        if (isActive(saveOnTabPanel)) {
            // Find the Save On Gas offer button.
            WebElement saveOnGasOfferButton = saveOnTabPanel.findElements(By.xpath("div/button")).stream()
                    .filter(offerButton -> offerButton.getText().equalsIgnoreCase("gas"))
                    .findFirst()
                    .orElse(null);

            if (saveOnGasOfferButton != null) {
                // Click on the Save On Gas button to show the Save On Gas card offers sub panel.
                WebElement saveOnGasCardOffersSubPanel = ariaControlsTarget(saveOnGasOfferButton);

                saveOnGasOfferButton.click();

                if (isActive(saveOnGasCardOffersSubPanel)) {
                    // Make sure there is at least one offer.
                    if (saveOnGasCardOffersSubPanel.findElements(By.xpath("div")).size() == 0) {
                        fail("Expected at least one Save On Gas card offer.");
                    }
                } else {
                    fail("Clicking on the Save On Gas button did not activate the Save On Gas card offers sub panel.");
                }
            } else {
                fail("Failed to find the Save On Gas offer button.");
            }

        } else {
            fail("Clicking the Save On button did not activate the Save On tab panel.");
        }
    }
}
