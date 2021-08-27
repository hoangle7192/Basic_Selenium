package SeleniumWebDriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//[Online 22] - Topic 17 (Handle Default Dropdown) - https://www.youtube.com/watch?v=W20sPbgr1GY

public class Topic08_01_Dropdown_List_Default {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;
	Action action;
	WebDriverWait explicitWait;

	String firstName, lastName, dateOfBirth_Day, dateOfBirth_Month, dateOfBirth_Year, email, companyName, password;

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

		firstName = "mayman" + randomNumber();
		lastName = "ghe" + randomNumber();
		dateOfBirth_Day = "10";
		dateOfBirth_Month = "January";
		dateOfBirth_Year = "1917";
		email = "hoa" + randomNumber() + "@gmail.com";
		companyName = "yearone" + randomNumber();
		password = "yearone123";
	}

	//@Test
	public void TC_01_LyThuyet() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.cssSelector("a.ico-register")).click();

		driver.findElement(By.cssSelector("input#gender-male")).click();
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);

		//dateOfBirth_Day
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		select.selectByVisibleText(dateOfBirth_Day); 		// Chon 1 item A
		Assert.assertFalse(select.isMultiple());		// Kiem tra dropdown co phai la multiple ko
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dateOfBirth_Day);		// Kiem tra xem da chon dung item A chua
		Assert.assertEquals(select.getOptions().size(), 32);		// Get ra so luong item trong dropdown
		
		// Bo chon 1 iem A
		//select.deselectByVisibleText("5");

		//dateOfBirth_Month
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		select.selectByVisibleText(dateOfBirth_Month);
		Assert.assertFalse(select.isMultiple());
		Assert.assertEquals(select.getOptions().size(), 13);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dateOfBirth_Month);

		// dateOfBirth_Year
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		select.selectByVisibleText(dateOfBirth_Year);
		Assert.assertFalse(select.isMultiple());
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dateOfBirth_Year);
		Assert.assertEquals(select.getOptions().size(), 112);

		driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
		driver.findElement(By.cssSelector("input#Company")).sendKeys(companyName);
		driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);
	}
	
	@Test
	public void TC_02_Baitap() {
		//Step01
		driver.get("https://www.rode.com/wheretobuy");
		
		//Step02
		select = new Select(driver.findElement(By.cssSelector("#where_country")));
		Assert.assertFalse(select.isMultiple());
		
		//Step03
		select.selectByVisibleText("Vietnam");
		
		//Step04
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		
		//Step05
		driver.findElement(By.cssSelector("#search_loc_submit")).click();
		
		//Step06
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='result_count']/span[text()='29']")).isDisplayed());
		
		List<WebElement> storeName = driver.findElements(By.xpath("//div[@id='search_results']//div[@class='store_name']"));
		Assert.assertEquals(storeName.size(), 29);
		
		for (WebElement webElement : storeName) {
			System.out.println(webElement);
		}

	}

	public int randomNumber() {
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(999999);
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
