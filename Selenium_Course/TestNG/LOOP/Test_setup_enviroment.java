package TestNG.LOOP;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Test_setup_enviroment {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {

	}

	@Test(invocationCount = 10)
	public void TC_01_ValidateCurrentUrl() {
		System.out.println(genarationEmail());
		System.out.println(randomNumber1());
		driver.get("");
	}

	// Random Email
	public String genarationEmail() {
		java.util.Random rand = new java.util.Random();
		return "abc" + rand.nextInt(9999) + "@gmail.com";
	}

	// Random số
	public int randomNumber() {
		java.util.Random rand = new java.util.Random();
		return rand.nextInt(100000);
	}

	public int randomNumber1() {
		Random rand = new Random();
		return rand.nextInt(987);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		//driver.quit();
	}


}
