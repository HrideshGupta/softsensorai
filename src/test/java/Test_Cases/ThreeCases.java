//package Test_Cases;
//
//import java.io.IOException;
//import java.time.Duration;
//import java.util.List;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.TimeoutException;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import SoftsensorAI.SoftsensorAI.SoftsensorAI_Base;
//
//public class Demo_Analysis extends SoftsensorAI_Base {
//
//    public Demo_Analysis() throws IOException {
//        super();
//    }
//
//    @BeforeMethod
//    public void setup() {
//        driver = initBrowserAndOpenApp(prop.getProperty("browserName"));
//    }
//
//    @Test
//    public void verifyLoginValidCredential() {
//        driver.get(prop.getProperty("url"));
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//
//        try {
//            // Debug: Print all available elements
//            List<WebElement> inputs = driver.findElements(By.tagName("input"));
//            for (WebElement el : inputs) {
//                System.out.println("📝 Found input field: Name = " + el.getAttribute("name") + " | ID = " + el.getAttribute("id"));
//            }
//
//            WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(
//                By.xpath(loc.getProperty("login.email"))));
//            System.out.println("✅ Email field is found!");
//            emailField.sendKeys(prop.getProperty("validUsername"));
//            
//            WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(
//                By.xpath(loc.getProperty("login.password"))));
//            System.out.println("✅ Password field is found!");
//            passwordField.sendKeys(prop.getProperty("validPassword"));
//
//            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath(loc.getProperty("login.button"))));
//            System.out.println("✅ Login button is clickable!");
//            safeClick(loginButton);
//            
//            executeUserFlow(wait);
//
//        } catch (TimeoutException e) {
//            System.err.println("❌ Timeout: Element not found! Check locators.");
//        }
//    }
//
//    private void executeUserFlow(WebDriverWait wait) {
//        clickElement( "dashboard.navigation.button");
//        clickElement( "getStarted.button");
//        clickElement( "skipAndStartChat.button");
//        clickElement( "analysisIcon.button");
//        clickElement( "noFileSelected.button");
//        
//        enterText( "fileName.input", "Beginner-Guide-To-Software-Testing");
//        toggleCheckbox( "fileCheckbox");
//        clickElement( "confirm.button");
//        enterText( "chatInput", "Summary");
//        pressEnter( "chatInput");
//        
//        clickElement( "generateSummary.button");
//        clickElement( "combobox.dropdown");
//        clickElement( "videoOption.button");
//        clickElement( "videoAnalysis.button");
//        clickElement( "confirmAnalysis.button");
//        clickElement( "generateSummary.button");
//    }
//
//    private void clickElement(WebDriverWait  String locatorKey) {
//        safeClick(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty(locatorKey)))));
//    }
//
//    private void enterText(WebDriverWait  String locatorKey, String text) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty(locatorKey)))).sendKeys(text);
//    }
//
//    private void pressEnter(WebDriverWait  String locatorKey) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty(locatorKey)))).sendKeys(Keys.RETURN);
//    }
//
//    private void toggleCheckbox(WebDriverWait  String locatorKey) {
//        WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty(locatorKey))));
//        if (!checkbox.isSelected()) {
//            checkbox.click();
//        }
//        Assert.assertTrue(checkbox.isSelected(), "Checkbox selection failed.");
//    }
//
//    private void waitForSeconds(int seconds) {
//        try {
//            Thread.sleep(seconds * 1000L);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    }
//
//    public void safeClick(WebElement element) {
//        try {
//            element.click();
//        } catch (Exception e) {
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("arguments[0].click();", element);
//        }
//    }
//}

//package Test_Cases;
//
//import java.io.IOException;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.annotations.Test;
//
//import SoftsensorAI.SoftsensorAI.SoftsensorAI_Base;
//
//public class Demo_Analysis extends SoftsensorAI_Base {
//
//	public Demo_Analysis() throws IOException {
//		super();
//	}
//
//	@Test(priority=1)
//	private void executeUserFlow() throws InterruptedException {
//		//clickElement("dashboard.navigation.button");
//		//clickElement("getStarted.button");
//		//clickElement("skipAndStartChat.button");
//		//clickElement("analysisIcon.button");
//		
//		//TestCase1 - PDF Analysis
//		clickElement("noFileSelected.button");
//		enterText("fileName.input", "Beginner-Guide-To-Software-Testing");
//		toggleCheckbox("fileCheckbox");
//		clickElement("confirm.button");
//		enterText("chatInput", "Summary");
//		pressEnter("chatInput");
//
//		//TestCase2 - Video Analysis
//		clickElement("selectForVideo.button");
//		clickElement("combobox.dropdown");
//		clickElement("videoOption.button");
//		clickElement("videoAnalysis.button");
//		clickElement("selectVideo.button");
//		clickElement("confirmAnalysis");
//		enterText("chatInput", "Summary");
//		pressEnter("chatInput");
//		
//		//TestCase3 - C&C
//		
//		 System.out.println("🔹 Running Test Case 3 - Click & Confirm...");
//
//	        // Click on C&C button
//	        clickElement("clickOnC&C.button");
//	        Thread.sleep(15000); // Wait for modal to load if any
//	        System.out.println("✅ Clicked on C&C button");
//
//	        // Click on Cross button
//	        clickElement("Cross.button");
//	        System.out.println("✅ Clicked on Cross button");
//
//	        // Debug: Print the locator value before use
//	        String searchInputXPath = loc.getProperty("Searchinput.button");
//	        if (searchInputXPath == null || searchInputXPath.isEmpty()) {
//	            throw new IllegalArgumentException("❌ XPath for 'Searchinput.button' is missing or empty. Check properties file.");
//	        }
//
//	        // Ensure Search Input Exists
//	        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchInputXPath)));
//
//	        // Scroll into view
//	        scrollToElement(searchInput);
//
//	        // Click to focus the input field
//	        searchInput.click();
//
//	        // Use Actions class to input text
//	        Actions actions = new Actions(driver);
//	        actions.moveToElement(searchInput).click().sendKeys("BOB").perform();
//	        Thread.sleep(1000); // Ensure text is fully entered before proceeding
//	        System.out.println("✅ Entered 'BOB' in search input field.");
//
//	        // Select Checkbox
//	        toggleCheckbox("fileCheckbox2");
//
//	        System.out.println("🔹 Test Case 3 - Click & Confirm execution completed successfully.");
//	    }
//
//	    private void clickElement(String locatorKey) {
//	        String xpathValue = loc.getProperty(locatorKey);
//	        if (xpathValue == null || xpathValue.isEmpty()) {
//	            throw new IllegalArgumentException("❌ Locator not found or empty for key: " + locatorKey);
//	        }
//
//	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathValue)));
//	        safeClick(element);
//	    }
//
//	    private void toggleCheckbox(String locatorKey) {
//	        String xpathValue = loc.getProperty(locatorKey);
//	        if (xpathValue == null || xpathValue.isEmpty()) {
//	            throw new IllegalArgumentException("❌ Locator not found or empty for key: " + locatorKey);
//	        }
//
//	        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathValue)));
//	        if (!checkbox.isSelected()) {
//	            safeClick(checkbox);
//	            System.out.println("✅ Checkbox selected.");
//	        } else {
//	            System.out.println("⚠️ Checkbox was already selected.");
//	        }
//	    }
//
//	    private void scrollToElement(WebElement element) {
//	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//	    }
//
//	    public void safeClick(WebElement element) {
//	        try {
//	            element.click();
//	        } catch (Exception e) {
//	            System.out.println("⚠️ Normal click failed, using JavaScript click...");
//	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
//	        }
//	    }
//	}