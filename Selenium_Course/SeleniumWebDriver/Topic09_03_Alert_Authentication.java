package SeleniumWebDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

// [Online 22] - Topic 22 (Handle Alert/ Authentication Alert) - https://www.youtube.com/watch?v=MtUdEvHqyiw

public class Topic09_03_Alert_Authentication {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor executor;
	Alert alert;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		// Firefox
		//System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();

		// Chrome123
		System.setProperty("webdriver.chrome.driver", projectPath +
		 "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		// add kieu tuong minh
		executor = (JavascriptExecutor) driver;

		// Wait cho alert xuat hien
		explicitWait = new WebDriverWait(driver, 15);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_05_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		By jsAlertButtonBy = By.xpath("//button[text()='Click for JS Alert']"); //dat ten bien local

		executor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(jsAlertButtonBy)); //Scroll xuong button
		driver.findElement(jsAlertButtonBy).click(); // click vao button

		// Wait cho alert duoc hien thi
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		sleepInSecond(3);

		//Switch qua 1 alert
		//alert = driver.switchTo().alert();

		Assert.assertEquals(alert.getText(), "I am a JS Alert"); // verify text trong aler

		alert.accept(); // accept alert

		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You clicked an alert successfully"); // verify text tra ve

		//Switch qua 1 alert
		//alert = driver.switchTo().alert();
	}

	@Test
	public void TC_06_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		By jsConfirmButtonBy = By.xpath("//button[text()='Click for JS Confirm']"); //dat ten bien local

		executor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(jsConfirmButtonBy)); //Scroll xuong button
		driver.findElement(jsConfirmButtonBy).click(); // click vao button

		// Wait cho alert duoc hien thi
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		sleepInSecond(3);

		//Switch qua 1 alert
		//alert = driver.switchTo().alert();

		Assert.assertEquals(alert.getText(), "I am a JS Confirm"); // verify text trong aler

		alert.dismiss(); // accept alert

		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You clicked: Cancel"); // verify text tra ve

		//Switch qua 1 alert
		//alert = driver.switchTo().alert();
	}

	@Test
	public void TC_07_Rrompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		By jsPromptButtonBy = By.xpath("//button[text()='Click for JS Prompt']"); //dat ten bien local

		executor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(jsPromptButtonBy)); //Scroll xuong button
		driver.findElement(jsPromptButtonBy).click(); // click vao button

		// Wait cho alert duoc hien thi
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		sleepInSecond(3);

		//Switch qua 1 alert
		//alert = driver.switchTo().alert();

		String input = "test_automation";
		alert.sendKeys(input);

		alert.dismiss(); // accept alert

		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You entered: " + input); // verify text tra ve

		//Switch qua 1 alert
		//alert = driver.switchTo().alert();
	}
	

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond*100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
