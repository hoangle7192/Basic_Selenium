package SeleniumWebDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

// [Online 21] - Topic 20 (User Interaction - Part I) - https://www.youtube.com/watch?v=VkqkLUKXdrk&list=PLo1QA-RK2zyosMdELpBN0h027Ht7PA2kj&index=22
// [Online 22] - Topic 23 (User Interaction - Part I) - https://www.youtube.com/watch?v=I9ht3LW_3lw
public class Topic10_01_Hover_To_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Actions action;

	String osName = System.getProperty("os.name");
	Keys key;


	@BeforeClass
	public void beforeClass() {
		// Firefox
		//System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();

		// Chrome123
		System.setProperty("webdriver.chrome.driver", projectPath +
		 "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	@Test
	public void TC_01_Hover_Mouse01() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");

		// Hover mouse to textbox
		action.moveToElement(driver.findElement(By.id("age"))).perform(); // Không thể thiếu perform()

		Assert.assertEquals(driver.findElement(By.cssSelector(".ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	}

	@Test
	public void TC_02_Hover_Mouse02() {
		driver.get("https://www.myntra.com/");

		// Hover mouse to "Kids"
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']"))).perform();
		driver.findElement(By.xpath("//a[text()='Kids Accessories']")).click();

		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-accessories");
	}

	@Test
	public void TC_02_Hover_Mouse03() {
		// url không giống
	}

	@Test
	public void TC_04_Click_and_hold_Ctrl() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> list = driver.findElements(By.cssSelector("#selectable>li"));

		// Click and hold
		action.clickAndHold(list.get(0)).moveToElement(list.get(10)).release().perform();

		Assert.assertEquals(driver.findElements(By.cssSelector("#selectable>li.ui-selected")).size(), 9);
	}

	@Test
	public void TC_04_Click_and_hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> list = driver.findElements(By.cssSelector("#selectable>li"));

		if (osName.contains("Windows")) {
			key = Keys.CONTROL;
		} else {
			key = Keys.COMMAND;
		}

		// Click Ctrl Down
		action.keyDown(key).perform();
		// -> Chọn các element đích
		action.click(list.get(0)).click(list.get(5)).click(list.get(6)).click(list.get(10)).perform(); // bắt buộc phải có
		// -> Nhả Ctrl ra
		action.keyUp(key).perform();

		Assert.assertEquals(driver.findElements(By.cssSelector("#selectable>li.ui-selected")).size(), 4);
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
