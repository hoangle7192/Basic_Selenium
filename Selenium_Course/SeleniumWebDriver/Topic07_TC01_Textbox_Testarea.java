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

public class Topic07_TC01_Textbox_Testarea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	String loginPageUrl, loginPageUrlVerify;
	String emailID, UserID, Password;
	String customerName, dateOfBirthInput, dateOfBirthVerify, addressInput, addressInputVerify, addressEditVerify, cityInput, stateInput,
			pinInput, mobileNumberInput, emailInput, password;
	String customerId;
	String addressEdit, cityEdit, stateEdit, pinEdit, mobileNumberEdit, emailEdit;

	// Input
	By customerNameInputTextboxBy = By.name("name");
	By genderInputRadioBy = By.xpath("//input[@name='rad1' and @value='f']");
	By dateOfBirthInputTextboxBy = By.name("dob");
	By addressInputTextboxBy = By.name("addr");
	By cityInputTextboxBy = By.name("city");
	By stateInputTextboxBy = By.name("state");
	By pinInputTextboxBy = By.name("pinno");
	By mobileNumberInputTextboxBy = By.name("telephoneno");
	By emailInputTextboxBy = By.name("emailid");
	By passwordInputTextboxBy = By.name("password");

	// Output
	By customerIdBy = By.xpath("//td[text()='Customer ID']/following-sibling::td");
	By customerNameOutputBy = By.xpath("//td[text()='Customer Name']/following-sibling::td");
	By genderOutputBy = By.xpath("//td[text()='Gender']/following-sibling::td");
	By dateOfBirthOutputBy = By.xpath("//td[text()='Birthdate']/following-sibling::td");
	By addressOutputBy = By.xpath("//td[text()='Address']/following-sibling::td");
	By cityOutputBy = By.xpath("//td[text()='City']/following-sibling::td");
	By stateOutputBy = By.xpath("//td[text()='State']/following-sibling::td");
	By pinOutputBy = By.xpath("//td[text()='Pin']/following-sibling::td");
	By mobileNumberOutputBy = By.xpath("//td[text()='Mobile No.']/following-sibling::td");
	By emailOutputBy = By.xpath("//td[text()='Email']/following-sibling::td");

	JavascriptExecutor jsExcecutor;

	@BeforeClass
	public void beforeClass() {
		// Firefox
		// System.setProperty("webdriver.gecko.driver",
		// ".\\browserDrivers\\geckodriver.exe");
		// driver = new FirefoxDriver();

		// Chrome123
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		// Ep kieu tuong minh
		jsExcecutor = (JavascriptExecutor) driver;

		loginPageUrl = "http://demo.guru99.com/v4/";
		emailID = "test" + randomNumber() + "@gmail.com";

		// Data input
		customerName = "taothichthisao";
		dateOfBirthInput = "01/01/1999";
		addressInput = "177 au co\nLien Chieu";
		cityInput = "da nang";
		stateInput = "hoa khanh";
		pinInput = "123456";
		mobileNumberInput = "0905123456";
		emailInput = "ching" + randomNumber() + "@gmail.com";
		password = "12345678";

		dateOfBirthVerify = "1999-01-01";
		addressInputVerify = "177 au co Lien Chieu";

		// Data edit
		addressEdit = "199 ninh ton\nHoa Cuong";
		cityEdit = "ha noi";
		stateEdit = "tu liem";
		pinEdit = "654321";
		mobileNumberEdit = "87654321";
		emailEdit = "tutu" + randomNumber() + "@hotmail.com";
		
		addressEditVerify = "199 ninh ton Hoa Cuong";

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Register() {
		// Login Page url
		driver.get(loginPageUrl);
		loginPageUrlVerify = driver.getCurrentUrl();

		// Click button here
		driver.findElement(By.xpath("//li[contains(string(),'Visit')]/a")).click();

		// Input EmailId
		driver.findElement(By.name("emailid")).sendKeys(emailID);
		driver.findElement(By.name("btnLogin")).click();

		// Verify Access details to demo site
		UserID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		Password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	}

	@Test
	public void TC_02_Login() {
		// input UserID and password
		driver.get(loginPageUrlVerify);
		driver.findElement(By.name("uid")).sendKeys(UserID);
		driver.findElement(By.name("password")).sendKeys(Password);
		driver.findElement(By.name("btnLogin")).click();
		sleepInSecond(10);

		// Manager Screen
		Assert.assertTrue(driver
				.findElement(By
						.xpath("//marquee[@class='heading3' and text()=\"Welcome To Manager's Page of Guru99 Bank\"]"))
				.isDisplayed());
	}

	@Test
	public void TC_03_Register() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		// New Customer Screen
		driver.findElement(customerNameInputTextboxBy).sendKeys(customerName);
		driver.findElement(genderInputRadioBy).click();

		// Xoa thuoc tinh type cua dateOfBirthTextboxBy
		jsExcecutor.executeScript("arguments[0].removeAttribute('type')",
				driver.findElement(dateOfBirthInputTextboxBy));
		sleepInSecond(5);
		driver.findElement(dateOfBirthInputTextboxBy).sendKeys(dateOfBirthInput);

		driver.findElement(addressInputTextboxBy).sendKeys(addressInput);
		driver.findElement(cityInputTextboxBy).sendKeys(cityInput);
		driver.findElement(stateInputTextboxBy).sendKeys(stateInput);
		driver.findElement(pinInputTextboxBy).sendKeys(pinInput);
		driver.findElement(mobileNumberInputTextboxBy).sendKeys(mobileNumberInput);
		driver.findElement(emailInputTextboxBy).sendKeys(emailInput);
		driver.findElement(passwordInputTextboxBy).sendKeys(password);
		driver.findElement(By.name("sub")).click();

		// Verify thong tin register
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']"))
						.isDisplayed());

		customerId = driver.findElement(customerIdBy).getText();
		Assert.assertEquals(driver.findElement(customerNameOutputBy).getText(), customerName);
		Assert.assertEquals(driver.findElement(genderOutputBy).getText(), "female");
		Assert.assertEquals(driver.findElement(dateOfBirthOutputBy).getText(), dateOfBirthVerify);
		Assert.assertEquals(driver.findElement(addressOutputBy).getText(), addressInputVerify);
		Assert.assertEquals(driver.findElement(cityOutputBy).getText(), cityInput);
		Assert.assertEquals(driver.findElement(stateOutputBy).getText(), stateInput);
		Assert.assertEquals(driver.findElement(pinOutputBy).getText(), pinInput);
		Assert.assertEquals(driver.findElement(mobileNumberOutputBy).getText(), mobileNumberInput);
		Assert.assertEquals(driver.findElement(emailOutputBy).getText(), emailInput);
	}

	@Test
	public void TC_04_Edit() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerId);
		driver.findElement(By.name("AccSubmit")).click();

		driver.findElement(addressInputTextboxBy).clear();
		driver.findElement(addressInputTextboxBy).sendKeys(addressEdit);

		driver.findElement(cityInputTextboxBy).clear();
		driver.findElement(cityInputTextboxBy).sendKeys(cityEdit);

		driver.findElement(stateInputTextboxBy).clear();
		driver.findElement(stateInputTextboxBy).sendKeys(stateEdit);

		driver.findElement(pinInputTextboxBy).clear();
		driver.findElement(pinInputTextboxBy).sendKeys(pinEdit);

		driver.findElement(mobileNumberInputTextboxBy).clear();
		driver.findElement(mobileNumberInputTextboxBy).sendKeys(mobileNumberEdit);

		driver.findElement(emailInputTextboxBy).clear();
		driver.findElement(emailInputTextboxBy).sendKeys(emailEdit);
		driver.findElement(By.name("sub")).click();

		Assert.assertTrue(driver
				.findElement(By.xpath("//p[@class='heading3' and text()='Customer details updated Successfully!!!']"))
				.isDisplayed());

		Assert.assertEquals(driver.findElement(customerNameOutputBy), customerName);
		Assert.assertEquals(driver.findElement(genderOutputBy), "female");
		Assert.assertEquals(driver.findElement(dateOfBirthOutputBy), dateOfBirthVerify);
		Assert.assertEquals(driver.findElement(addressOutputBy), addressEditVerify);
		Assert.assertEquals(driver.findElement(cityOutputBy), cityEdit);
		Assert.assertEquals(driver.findElement(stateOutputBy), stateEdit);
		Assert.assertEquals(driver.findElement(pinOutputBy), pinEdit);
		Assert.assertEquals(driver.findElement(mobileNumberOutputBy), mobileNumberEdit);
		Assert.assertEquals(driver.findElement(emailOutputBy), emailEdit);
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(100000);
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
