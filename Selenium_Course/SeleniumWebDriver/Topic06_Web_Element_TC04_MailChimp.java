package SeleniumWebDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic06_Web_Element_TC04_MailChimp {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	By emailTextboxBy = By.id("email");
	By userNameTextboxBy = By.id("new_username");
	By passwordTextboxBy = By.id("new_password");
	By signupButtonBy = By.id("create-account");
	By signupCheckboxBy = By.id("marketing_newsletter");

	By oneNumberBy = By.xpath("//li[@class='number-char completed' and text()='One number']");
	By lowerCaseBy = By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']");
	By upperCaseBy = By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']");
	By specialCaseBy = By.xpath("//li[@class='special-char completed' and text()='One special character']");
	By minimumCharCaseBy = By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']");

	String email, username;

	@BeforeClass
	public void beforeClass() {
		// Firefox
		// System.setProperty("webdriver.gecko.driver",
		// ".\\browserDrivers\\geckodriver.exe");
		// driver = new FirefoxDriver();

		// Chrome123
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://login.mailchimp.com/signup/");

		email = "abc@gmail.com";
		username = "abc123";
	}

	@Test
	public void TC_04_MailChimp() {
		sendKeyElement(emailTextboxBy, email);
		sendKeyElement(userNameTextboxBy, username);

		// Pass la so
		sendKeyElement(passwordTextboxBy, "1234");
		Assert.assertTrue(isElementdisplayed(oneNumberBy));
		Assert.assertFalse(isElementenable(signupButtonBy));

		// Pass la chu thuong
		clearElement(passwordTextboxBy);
		sendKeyElement(passwordTextboxBy, "abc");
		Assert.assertTrue(isElementdisplayed(lowerCaseBy));
		Assert.assertFalse(isElementenable(signupButtonBy));

		// Pass la chu hoa
		clearElement(passwordTextboxBy);
		sendKeyElement(passwordTextboxBy, "ABC");
		Assert.assertTrue(isElementdisplayed(upperCaseBy));
		Assert.assertFalse(isElementenable(signupButtonBy));

		// Pass la ki tu dac biet
		clearElement(passwordTextboxBy);
		sendKeyElement(passwordTextboxBy, "@@@");
		Assert.assertTrue(isElementdisplayed(specialCaseBy));
		Assert.assertFalse(isElementenable(signupButtonBy));

		// Pass lon hon 8 ki tu
		clearElement(passwordTextboxBy);
		sendKeyElement(passwordTextboxBy, "123456789");
		Assert.assertTrue(isElementdisplayed(minimumCharCaseBy));
		Assert.assertFalse(isElementenable(signupButtonBy));

		// Pass hop le
		clearElement(passwordTextboxBy);
		sendKeyElement(passwordTextboxBy, "Abc123456@");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='c-mediaBody--centered']/h4")).getText(),
				"Your password is secure and you're all set!");

		Assert.assertTrue(isElementenable(signupButtonBy));
		clickElement(signupCheckboxBy);
		Assert.assertTrue(isElementSelect(signupCheckboxBy));
		clickElement(signupButtonBy);
	}

	public void sendKeyElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);
	}

	public void clickElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}

	public void clearElement(By by) {
		WebElement element = driver.findElement(by);
		element.clear();
	}

	public boolean isElementdisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element[" + by + "] is displayed");
			return true;
		} else {
			System.out.println("Element[" + by + "] is not displayed");
			return false;
		}
	}

	public boolean isElementenable(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element[" + by + "] is enable");
			return true;
		} else {
			System.out.println("Element[" + by + "] is disable");
			return false;
		}
	}

	public boolean isElementSelect(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element[" + by + "] is selected");
			return true;
		} else {
			System.out.println("Element[" + by + "] is unselected");
			return false;
		}
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}
