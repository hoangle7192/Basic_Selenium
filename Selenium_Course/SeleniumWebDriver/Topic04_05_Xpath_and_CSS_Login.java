package SeleniumWebDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic04_05_Xpath_and_CSS_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	// Tao bien locator
	By myAccountLink = By.xpath("//div[@class='footer']//a[@title='My Account']");
	By emailTextbox = By.id("email");
	By passTextbox = By.id("pass");
	By loginButton = By.id("send2");

	@BeforeClass
	public void beforeClass() {
		// Firefox
		//System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();

		// Chrome123
		 System.setProperty("webdriver.chrome.driver", projectPath +
		 "\\browserDrivers\\chromedriver.exe");
		 driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("http://live.demoguru99.com/");
	}

	//@Test
	public void TC_01_loginWithEmptyEmailAndPass() {
		// Click link "My Account"
		driver.findElement(myAccountLink).click();
		// Click login button
		driver.findElement(loginButton).click();
		// Verify msg error
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),
				"This is a required field.");
	}

	//@Test
	public void TC_02_loginWithInvalidEmail() {
		// Click link "My Account"
		driver.findElement(myAccountLink).click();
		// Input Email and Pass
		driver.findElement(emailTextbox).sendKeys("12345@123.123");
		driver.findElement(passTextbox).sendKeys("123456");
		// Click login button
		driver.findElement(loginButton).click();
		// Verify msg error
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	//@Test
	public void TC_03_loginWithPass_LassThan6Characters() {
		// Click link "My Account"
		driver.findElement(myAccountLink).click();
		// Input Email and Pass
		driver.findElement(emailTextbox).sendKeys("toi1@gmail.com");
		driver.findElement(passTextbox).sendKeys("123");
		// Click login button
		driver.findElement(loginButton).click();
		// Verify msg error
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");
	}

	//@Test
	public void TC_04_loginWithIncorrectEmailPass() {
		// Click link "My Account"
		driver.findElement(myAccountLink).click();
		// Input Email and Pass
		driver.findElement(emailTextbox).sendKeys("toi1@gmail.com");
		driver.findElement(passTextbox).sendKeys("123456123456");
		// Click login button
		driver.findElement(loginButton).click();
		// Verify msg error
		Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']//span")).getText(),
				"Invalid login or password.");
	}

	@Test
	public void TC_05_createANewAccount() {
		// Click link "My Account"
		driver.findElement(myAccountLink).click();
		// Click button create
		driver.findElement(By.xpath("//a[@class='button']")).click();
		// input data
		driver.findElement(By.id("firstname")).sendKeys("Nguyen");
		driver.findElement(By.id("middlename")).sendKeys("Thi");
		driver.findElement(By.id("lastname")).sendKeys("Hongx");
		driver.findElement(By.id("email_address")).sendKeys("ngthihog17@gmail.com");
		driver.findElement(By.id("password")).sendKeys("ngthihog12");
		driver.findElement(By.id("confirmation")).sendKeys("ngthihog12");
		driver.findElement(By.id("is_subscribed")).click();
		// Click button register
		driver.findElement(By.xpath("//div[@class='buttons-set']//button")).click();
		// Verify msg
		Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']//span")).getText(),
				"Thank you for registering with Main Website Store.");
		Assert.assertTrue(driver
				.findElement(By.xpath("//div[@class='box-content']//p[contains(string(),'ngthihog17@gmail.com')]"))
				.getText().contains("ngthihog17@gmail.com"));	
		// Logout 
		driver.findElement(By.xpath("//div[@class='header-minicart']/preceding-sibling::a/span[@class='label']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		// Check navigate ve homapage
		String homePage = driver.getCurrentUrl();		
		Assert.assertEquals(homePage, "http://live.demoguru99.com/index.php/customer/account/logoutSuccess/");	
	}
	
	@Test
	public void TC_06_loginSuccess() {
		// Click link "My Account"
		driver.findElement(myAccountLink).click();
		// Input Email and Pass
		driver.findElement(emailTextbox).sendKeys("ngthihog12@gmail.com");
		driver.findElement(passTextbox).sendKeys("ngthihog12");
		// Click login button
		driver.findElement(loginButton).click();
		// Verify information
		Assert.assertTrue(driver
				.findElement(By.xpath("//div[@class='box-content']//p[contains(string(),'ngthihog12@gmail.com')]"))
				.getText().contains("ngthihog12@gmail.com"));	
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}
