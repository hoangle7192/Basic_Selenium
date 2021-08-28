package practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class X01_Practice_jquery {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	JavascriptExecutor jsExcutor;
	WebDriverWait explicitwait;

	@BeforeClass
	public void beforeClass() {
		//Firefox
		//System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();
		
		//Chrome123
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		// add js
		jsExcutor = (JavascriptExecutor) driver;
		
		// wait
		explicitwait = new WebDriverWait(driver, 15);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}	

	@Test
	public void TC_01_Basic() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.switchTo().alert().getText(), "I am a JS Alert");
		driver.switchTo().alert().accept();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='You clicked an alert successfully ']")).isDisplayed());

		//test123434
		}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond*1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}


}
