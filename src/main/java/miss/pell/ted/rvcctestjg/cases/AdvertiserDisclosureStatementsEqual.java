package miss.pell.ted.rvcctestjg.cases;

import miss.pell.ted.rvcctestjg.SeleniumTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AdvertiserDisclosureStatementsEqual extends SeleniumTest {
    @Override
    public String id() {
        return "[CC] Advertiser Disclosures Statements Equal";
    }

    @Override
    public void run() {
        webDriver().get("http://www.creditcards.com");

        // Find the label that guards the secret advertiser disclosure.
        WebElement label = webDriver().findElement(By.xpath("//section/div[2]/div/label"));

        // Click the label to activate the modal dialog.
        label.click();

        // Get the modal dialog contents (excluding title) in preparation for the comparison.
        WebElement modalInnerDiv = webDriver().findElement(By.className("modal__inner"));
        WebElement modalDialogP = modalInnerDiv.findElement(By.xpath("./p"));

        String dialogContents = modalDialogP.getText();

        WebElement modalOkButton = modalInnerDiv.findElement(By.className("modal__ok-button"));

        // Dismiss the modal dialog.
        modalOkButton.click();

        // Get the contents of the advertiser disclosure in the footer of the page.
        WebElement disclosureSpan = webDriver().findElement(By.className("footer__adDisclosureText"));

        // Remove the screaming capitalization-locked characters at the beginning.
        String footerContents = disclosureSpan.getText().replace("ADVERTISER DISCLOSURE ", "");

        if (!footerContents.equals(dialogContents)) {
            fail("The modal dialog Advertiser Disclosure does not equal the footer Advertiser Disclosure.");

            System.out.printf("DAD: %s\n", dialogContents);
            System.out.printf("FAD: %s\n", footerContents);
        }

        webDriver().quit();
    }

}
