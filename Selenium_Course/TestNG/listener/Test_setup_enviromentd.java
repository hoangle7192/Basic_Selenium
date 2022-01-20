package TestNG.listener;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Random;

@Listeners(ExtentReport.class)
public class Test_setup_enviromentd {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {

	}

	@Test(invocationCount = 10)
	public void TC_01_ValidateCurrentUrl() {
		System.out.println(genarationEmail());
		System.out.println(randomNumber1());
		Assert.assertTrue(false);
	}

	// Random Email
	public String genarationEmail() {
		Random rand = new Random();
		return "abc" + rand.nextInt(9999) + "@gmail.com";
	}

	// Random số
	public int randomNumber() {
		Random rand = new Random();
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
