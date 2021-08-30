package SeleniumWebDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

// [Online 22] - Topic 22 (Handle Alert/ Authentication Alert) - https://www.youtube.com/watch?v=MtUdEvHqyiw

public class Topic09_03_Alert_Authentication {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor executor;
	Alert alert;
	WebDriverWait explicitWait;

	// Khai bao Path cho AUTO IT
	String authenChromeAutoIT = projectPath + "\\AutoIT\\authen_chrome.exe";
	String authenFirefoxAutoIT = projectPath + "\\AutoIT\\authen_firefox.exe";

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

		// Wait + switch cho alert
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

		alert.accept(); // accept alert

		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You entered: " + input); // verify text tra ve

		//Switch qua 1 alert
		//alert = driver.switchTo().alert();
	}

    @Test
    public void TC_08_01_Authentication_Alert() {
		String password = "admin";
		String loginUser = "admin";
        String url = "http://" + loginUser + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth";
        driver.get(url);
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }

	@Test
	public void TC_08_02_Authentication_Alert() {
		// Sử dung khi không get trược tiếp url

		String password = "admin";
		String loginUser = "admin";

		driver.get("http://the-internet.herokuapp.com/");

		String[] basicAuthlink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href").split("//"); // lay ra thuoc tinh href
		String url = basicAuthlink[0] + "//" + loginUser + ":" + password + "@" + basicAuthlink[1];
		//System.out.println(basicAuthlink);

		driver.get(url); // truyen ham vao

		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

	}


    // tam thoi Pending
    //@Test
    // phải khởi tạo script chứa Auto-IT trước
    public void TC_09_Authentication_Alert_AutoIT() throws IOException {
		String loginUser = "admin";
		String password = "admin";
		String url = "http://the-internet.herokuapp.com/basic_auth";

		if(driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] {authenChromeAutoIT, loginUser, password});
		} else {
			Runtime.getRuntime().exec(new String[] {authenFirefoxAutoIT, loginUser, password});
		}

		driver.get(url); // bắt buộc phải để dưới if else
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
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
