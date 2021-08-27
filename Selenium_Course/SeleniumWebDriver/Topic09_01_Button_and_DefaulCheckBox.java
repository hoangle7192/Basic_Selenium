package SeleniumWebDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

// [Online 22] - Topic 20 (Handle Button/ Radio/ Checkbox) - https://www.youtube.com/watch?v=hKVDbgaOGYU

public class Topic09_01_Button_and_DefaulCheckBox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		// Firefox
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		// Chrome123
		 //System.setProperty("webdriver.chrome.driver", projectPath +
		 //"\\browserDrivers\\chromedriver.exe");
		 //driver = new ChromeDriver();
		 
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_button() {
		driver.get("https://www.fahasa.com/customer/account/create"); // get URL
		String createPage = driver.getCurrentUrl();
		
		driver.findElement(By.xpath("//ul[@id='popup-login-tab_list']//a[text()='Đăng nhập']")).click(); // click on the section "Dang Nhap"
		By loginButton = By.xpath("//div[@class='popup-login-content']//button[@title='Đăng nhập']"); //create variable loginButton
		Assert.assertFalse(driver.findElement(loginButton).isEnabled()); // Verify login button is disabled
		sleepInSecond(3); // sleep

		// Delete attribute disable of "Dang Nhap" button
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')",
				driver.findElement(loginButton));
		
		Assert.assertTrue(driver.findElement(loginButton).isEnabled()); // Verify login button is enable
		driver.findElement(loginButton).click();
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Số điện thoại/Email']/following-sibling::div[text()='Thông tin này không thể để trống']")).getText(), 
				"Thông tin này không thể để trống"); // verify validation
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::div[text()='Thông tin này không thể để trống']")).getText(), 
				"Thông tin này không thể để trống"); // verify validation
		sleepInSecond(10); // sleep
		
		driver.get(createPage); // get again URL
		driver.findElement(By.xpath("//ul[@id='popup-login-tab_list']//a[text()='Đăng nhập']")).click(); // click on the section "Dang Nhap"
		Assert.assertFalse(driver.findElement(loginButton).isEnabled()); // Verify login button is disabled
		sleepInSecond(10); // sleep
		
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("test@gmail.com"); //input data in the phone number textbox
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("test123");	//input data in the pass textbox
		
		Assert.assertTrue(driver.findElement(loginButton).isEnabled()); // Verify login button is Enable
		
		sleepInSecond(10); // sleep
		// verify "Dang Nhap" button's background color  is red
		String rgbaColor = driver.findElement(loginButton).getCssValue("background-color");
		System.out.println("RGBA: " + rgbaColor);
		// Convert to Hexa
		String hexa = Color.fromString(rgbaColor).asHex().toUpperCase(); //toUpperCase() convert hexa to UpperCase
		System.out.println("Hexa: " + hexa);
		
		Assert.assertEquals(hexa, "#C92127"); //compare rgba=hexa
	}
	
	//@Test
	public void Tc_02_Radio_default() {
		By petrol92 = By.xpath("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::input");
		By diesel103 = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input");
		
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");		
		radioCheckBox(petrol92);
		Assert.assertTrue(driver.findElement(petrol92).isSelected());
		
		radioCheckBox(diesel103);
		Assert.assertTrue(driver.findElement(diesel103).isSelected());
		Assert.assertFalse(driver.findElement(petrol92).isSelected());
	}
	
	@Test
	public void Tc_03_checkbox_default() {
		By airbags = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");
		By leather = By.xpath("//label[text()='Leather trim']/preceding-sibling::input");
		By luggage = By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input");
		
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		checkToCheckBox(airbags);
		Assert.assertFalse(driver.findElement(airbags).isSelected());
		
		Assert.assertFalse(driver.findElement(leather).isEnabled());
		
		checkToCheckBox(luggage);
		Assert.assertTrue(driver.findElement(luggage).isSelected());
		
		checkToCheckBox(airbags);
		Assert.assertTrue(driver.findElement(airbags).isSelected());		
	}
	
	public void radioCheckBox(By by) {
		driver.findElement(by).click();
	}
	
	public void checkToCheckBox(By by) {
		if(!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		} else {
			if(driver.findElement(by).isSelected()) {
				driver.findElement(by).click();
			}
		}
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
		//driver.quit();
	}

}
