package SoftsensorAI.SoftsensorAI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SoftsensorAI_Base {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Properties prop;
	protected Properties loc;
	private static final Logger logger = LogManager.getLogger(SoftsensorAI_Base.class);

	public SoftsensorAI_Base() throws IOException {
		loadProperties();
	}

	private void loadProperties() throws IOException {
		prop = new Properties();
		loc = new Properties();

		try (InputStream input = getClass().getClassLoader()
				.getResourceAsStream("java/qa/configuration/config.properties");
				InputStream input2 = getClass().getClassLoader()
						.getResourceAsStream("java/qa/configuration/locators.properties")) {

			if (input == null || input2 == null) {
				throw new FileNotFoundException("Properties file not found in classpath.");
			}

			prop.load(input);
			loc.load(input2);
		} catch (IOException e) {
			logger.error("Error loading properties files: " + e.getMessage());
			throw e;
		}
	}

	public WebDriver initBrowserAndOpenApp(String browserName) {
		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--disable-gpu", "--no-sandbox", "--remote-allow-origins=*");
			driver = new ChromeDriver(chromeOptions);
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			driver = new FirefoxDriver(firefoxOptions);
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			driver = new EdgeDriver(edgeOptions);
			break;

		default:
			logger.error("Invalid browser: " + browserName);
			throw new IllegalArgumentException("Unsupported browser: " + browserName);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	@BeforeMethod
	public void loginOnce() {
		// Initialize the browser
		driver = initBrowserAndOpenApp(prop.getProperty("browserName"));

		// Perform login once
		driver.get(prop.getProperty("url"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		// Debug: Print all available elements
		List<WebElement> inputs = driver.findElements(By.tagName("input"));
		for (WebElement el : inputs) {
			System.out.println(
					"📝 Found input field: Name = " + el.getAttribute("name") + " | ID = " + el.getAttribute("id"));
		}
		WebElement emailField = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(loc.getProperty("login.email"))));
		System.out.println("✅ Email field is found!");
		emailField.sendKeys(prop.getProperty("validUsername"));

		WebElement passwordField = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(loc.getProperty("login.password"))));
		System.out.println("✅ Password field is found!");
		passwordField.sendKeys(prop.getProperty("validPassword"));

		WebElement loginButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("login.button"))));
		System.out.println("✅ Login button is clickable!");
		safeClick(loginButton);
	}
	public void safeClick(WebElement element) {
		try {
			element.click();
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		}
	}

	@AfterMethod(enabled = false)	
	public void tearDown() {
		if (driver != null) {
			try {
				driver.quit();
				logger.info("Browser closed successfully.");
			} catch (Exception e) {
				logger.error("Error closing the browser: " + e.getMessage());
			}
		}
	}
}
