package Test_Cases;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import SoftsensorAI.SoftsensorAI.SoftsensorAI_Base;

public class Demo_Analysis extends SoftsensorAI_Base {

    public Demo_Analysis() throws IOException {
        super();
    }

    @Test(priority = 1)
    private void executeUserFlow() throws InterruptedException {
        clickElement("dashboard.navigation.button");
        clickElement("getStarted.button");
        clickElement("skipAndStartChat.button");
        clickElement("analysisIcon.button");
        // TestCase1 - PDF Analysis and Video Analysis
        clickElement("noFileSelected.button");
        enterText("fileName.input", "bob");
        toggleCheckbox("fileCheckbox");
        clickElement("confirm.button");
        enterText("chatInput", "In your capacity as a financial assistant, I require your assistance in retrieving specific financial data. Could you kindly retrieve the totals for short-term liabilities, long-term liabilities, and shareholder funds from the relevant documents or records? Please ensure your response includes accurate totals for each category");
        pressEnter("chatInput");
        Thread.sleep(7000);
        clickElement("selectForVideo.button");
        clickElement("combobox.dropdown");
        clickElement("videoOption.button");
        clickElement("videoAnalysis.button");
        clickElement("selectVideo.button");
        clickElement("confirmAnalysis");
        enterText("chatInput", "Summary");
        pressEnter("chatInput");
        Thread.sleep(15000);
    }

    @Test(priority = 2)
    private void executeUserFlow2() throws InterruptedException {
        // TestCase3 - C&C
        clickElement("clickOnC&C.button");
        Thread.sleep(5000); // Wait for modal to load if any
        System.out.println("✅ Clicked on C&C button");

        // Click on Cross button **only if present**
        safeClickIfPresent("Cross.button");

        // Debug: Print the locator value before use
        String searchInputXPath = loc.getProperty("Searchinput.button");
        if (searchInputXPath == null || searchInputXPath.isEmpty()) {
            throw new IllegalArgumentException(
                    "❌ XPath for 'Searchinput.button' is missing or empty. Check properties file.");
        }

        // Ensure Search Input Exists
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchInputXPath)));

        // Scroll into view
        scrollToElement(searchInput);

        // Click to focus the input field
        searchInput.click();

        // Use Actions class to input text
        Actions actions = new Actions(driver);
        actions.moveToElement(searchInput).click().sendKeys("BOB").perform();
        Thread.sleep(1000);
        System.out.println("✅ Entered 'BOB' in search input field.");
        Thread.sleep(5000);

        // Select Checkbox
        toggleCheckbox("fileCheckbox");

        // Clear Text Box after selecting BOB
        clearTextBox("clearTextBox");
        System.out.println("✅ Cleared 'BOB' from search input field.");
        Thread.sleep(2000);

        actions.moveToElement(searchInput).click().sendKeys("JUBILANT").perform();
        Thread.sleep(1000);
        System.out.println("✅ Entered 'JUBILANT' in search input field.");
        Thread.sleep(5000);

        // Select Checkbox for JUBILANT
        toggleCheckbox("fileCheckbox2");

        clickElement("Com&ConTextBox");
        System.out.println("✅ Clicked on Com&ConTextBox button");

        // Click on Cross button **only if present**
        safeClickIfPresent("Cross.button");

        enterText("chatInput", "Summary");
        pressEnter("chatInput");
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void clickElement(String locatorKey) {
        safeClick(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty(locatorKey)))));
    }

    private void enterText(String locatorKey, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty(locatorKey)))).sendKeys(text);
    }

    private void pressEnter(String locatorKey) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty(locatorKey))))
                .sendKeys(Keys.RETURN);
    }

    private void toggleCheckbox(String locatorKey) {
        WebElement checkbox = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(loc.getProperty(locatorKey))));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
    }

    private void clearTextBox(String locatorKey) {
        WebElement textBox = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty(locatorKey))));
        textBox.clear();
        textBox.sendKeys(Keys.CONTROL + "a");
        textBox.sendKeys(Keys.BACK_SPACE);
        System.out.println("✅ Text box cleared using `.clear()` and `CTRL + A + BACKSPACE`.");
    }

    // **New Method**: Clicks element if it is present, otherwise skips it.
    private void safeClickIfPresent(String locatorKey) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(loc.getProperty(locatorKey))));
            if (element.isDisplayed()) {
                safeClick(element);
                System.out.println("✅ Clicked on " + locatorKey);
            }
        } catch (Exception e) {
            System.out.println("⚠️ Element " + locatorKey + " not found, skipping click.");
        }
    }

    public void safeClick(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
}
