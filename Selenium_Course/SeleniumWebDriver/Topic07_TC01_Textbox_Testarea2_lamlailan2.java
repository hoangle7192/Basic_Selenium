package SeleniumWebDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic07_TC01_Textbox_Testarea2_lamlailan2 {
	WebDriver driver;
	JavascriptExecutor jsExcecutor;

	String projectPath = System.getProperty("user.dir");
	String loginPageUrl, loginPageUrlVerify;
	String Email_ID, User_ID, Password;
	String customerName_Input, dateOfBirth_Input, address_Input, city_Input, state_Input, pin_Input, mobileNumber_Input,
			email_Input, password_Input;
	String customerID_Output, dateOfBirth_Output;
	String address_Edit, city_Edit, state_Edit, pin_Edit, mobileNumber_Edit, email_Edit;

	// New Customer Input
	By customerName_Input_TextboxBy = By.name("name");
	By gender_Input_RadioBy = By.xpath("//input[@name='rad1' and @value='m']");
	By dateOfBirth_Input_TextboxBy = By.id("dob");
	By address_Input_TextboxBy = By.name("addr");
	By city_Input_TextboxBy = By.name("city");
	By state_Input_TextboxBy = By.name("state");
	By pin_Input_TextboxBy = By.name("pinno");
	By mobileNumber_Input_TextboxBy = By.name("telephoneno");
	By email_Input_TextboxBy = By.name("emailid");
	By password_Input_TextboxBy = By.name("password");
	
	// New Customer Output
	By customerID_OutputBy = By.xpath("//td[text()='Customer ID']/following-sibling::td");
	By customerName_OutputBy = By.xpath("//td[text()='Customer Name']/following-sibling::td");
	By gender_OutputBy = By.xpath("//td[text()='Gender']/following-sibling::td");
	By birthdate_OutputBy = By.xpath("//td[text()='Birthdate']/following-sibling::td");
	By address_OutputBy = By.xpath("//td[text()='Address']/following-sibling::td");
	By city_OutputBy = By.xpath("//td[text()='City']/following-sibling::td");
	By state_OutputBy = By.xpath("//td[text()='State']/following-sibling::td");
	By pin_OutputBy = By.xpath("//td[text()='Pin']/following-sibling::td");
	By mobile_OutputBy = By.xpath("//td[text()='Mobile No.']/following-sibling::td");
	By email_OutputBy = By.xpath("//td[text()='Email']/following-sibling::td");

	@BeforeClass
	public void beforeClass() {
		// Firefox
		// System.setProperty("webdriver.gecko.driver",
		// ".\\browserDrivers\\geckodriver.exe");
		// driver = new FirefoxDriver();

		// Chrome
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		jsExcecutor = (JavascriptExecutor) driver;

		loginPageUrl = "http://demo.guru99.com/v4/index.php";
		Email_ID = "test" + randomNumber() + "@hotmail.com";

		// New Customer Input
		customerName_Input = "Maccus rac fo";
		dateOfBirth_Input = "05/01/1992";
		address_Input = "09 thuy khe";
		city_Input = "Ha Noi";
		state_Input = "Ba Dinh";
		pin_Input = "456789";
		mobileNumber_Input = "0905123456";
		email_Input = "ditconmemay" + randomNumber() + "@hotmail.com";
		password_Input = "123" + randomNumber();
		
		// New Customer Verify
		dateOfBirth_Output = "1992-05-01";
		
		// Edit Customer
		address_Edit = "10 lang ha";
		city_Edit = "Hung yen";
		state_Edit = "Bai but";
		pin_Edit = "987654";
		mobileNumber_Edit = "9865741110";
		email_Edit = "ditconmemay" + randomNumber() + "@hotmail.com";

	}

	@Test
	public void TC01_register() {
		// login page
		driver.get(loginPageUrl);
		loginPageUrlVerify = driver.getCurrentUrl();

		// Click "here" button
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(Email_ID);
		driver.findElement(By.name("btnLogin")).click();

		// Get UserID and Password
		User_ID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		Password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	}

	@Test
	public void TC02_newCustomer() {
		driver.get(loginPageUrlVerify);
		driver.findElement(By.name("uid")).sendKeys(User_ID);
		driver.findElement(By.name("password")).sendKeys(Password);
		driver.findElement(By.name("btnLogin")).click();

		// Manager Screen
		Assert.assertTrue(driver
				.findElement(By
						.xpath("//marquee[@class='heading3' and text()=\"Welcome To Manager's Page of Guru99 Bank\"]"))
				.isDisplayed());
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		// New Customer Screen
		driver.findElement(customerName_Input_TextboxBy).sendKeys(customerName_Input);
		driver.findElement(gender_Input_RadioBy).click();

		jsExcecutor.executeScript("arguments[0].removeAttribute('type')",
				driver.findElement(dateOfBirth_Input_TextboxBy));
		driver.findElement(dateOfBirth_Input_TextboxBy).sendKeys(dateOfBirth_Input);

		driver.findElement(address_Input_TextboxBy).sendKeys(address_Input);
		driver.findElement(city_Input_TextboxBy).sendKeys(city_Input);
		driver.findElement(state_Input_TextboxBy).sendKeys(state_Input);
		driver.findElement(pin_Input_TextboxBy).sendKeys(pin_Input);
		driver.findElement(mobileNumber_Input_TextboxBy).sendKeys(mobileNumber_Input);
		driver.findElement(email_Input_TextboxBy).sendKeys(email_Input);
		driver.findElement(password_Input_TextboxBy).sendKeys(password_Input);
		driver.findElement(By.name("sub")).click();

		// Verify
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']"))
						.isDisplayed());
		customerID_Output = driver.findElement(customerID_OutputBy).getText();
		Assert.assertEquals(driver.findElement(customerName_OutputBy).getText(), customerName_Input);
		Assert.assertEquals(driver.findElement(gender_OutputBy).getText(), "male");
		Assert.assertEquals(driver.findElement(birthdate_OutputBy).getText(), dateOfBirth_Output);
		Assert.assertEquals(driver.findElement(address_OutputBy).getText(), address_Input);
		Assert.assertEquals(driver.findElement(city_OutputBy).getText(), city_Input);
		Assert.assertEquals(driver.findElement(state_OutputBy).getText(), state_Input);
		Assert.assertEquals(driver.findElement(pin_OutputBy).getText(), pin_Input);
		Assert.assertEquals(driver.findElement(mobile_OutputBy).getText(), mobileNumber_Input);
		Assert.assertEquals(driver.findElement(email_OutputBy).getText(), email_Input);
	}
	
	@Test
	public void TC03_EditCustomer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID_Output);
		driver.findElement(By.name("AccSubmit")).click();
		
		// Edit Customer
		driver.findElement(address_Input_TextboxBy).clear();
		driver.findElement(address_Input_TextboxBy).sendKeys(address_Edit);
		
		driver.findElement(city_Input_TextboxBy).clear();
		driver.findElement(city_Input_TextboxBy).sendKeys(city_Edit);
		
		driver.findElement(state_Input_TextboxBy).clear();
		driver.findElement(state_Input_TextboxBy).sendKeys(state_Edit);
		
		driver.findElement(pin_Input_TextboxBy).clear();
		driver.findElement(pin_Input_TextboxBy).sendKeys(pin_Edit);
		
		driver.findElement(mobileNumber_Input_TextboxBy).clear();
		driver.findElement(mobileNumber_Input_TextboxBy).sendKeys(mobileNumber_Edit);
		
		driver.findElement(email_Input_TextboxBy).clear();
		driver.findElement(email_Input_TextboxBy).sendKeys(email_Edit);
		
		driver.findElement(By.name("sub")).click();
		
		// Verify
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer details updated Successfully!!!']"))
						.isDisplayed());
		Assert.assertEquals(driver.findElement(customerID_OutputBy).getText(), customerID_Output);
		Assert.assertEquals(driver.findElement(customerName_OutputBy).getText(), customerName_Input);
		Assert.assertEquals(driver.findElement(gender_OutputBy).getText(), "male");
		Assert.assertEquals(driver.findElement(birthdate_OutputBy).getText(), dateOfBirth_Output);
		Assert.assertEquals(driver.findElement(address_OutputBy).getText(), address_Edit);
		Assert.assertEquals(driver.findElement(city_OutputBy).getText(), city_Edit);
		Assert.assertEquals(driver.findElement(state_OutputBy).getText(), state_Edit);
		Assert.assertEquals(driver.findElement(pin_OutputBy).getText(), pin_Edit);
		Assert.assertEquals(driver.findElement(mobile_OutputBy).getText(), mobileNumber_Edit);
		Assert.assertEquals(driver.findElement(email_OutputBy).getText(), email_Edit);
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	// Ham random
	public int randomNumber() {
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(100000);
	}

	// Ham sleep
	public void sleepInSecond(long timeOutInSecond) {
		try {
			Thread.sleep(timeOutInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
