package TestNG.group;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class group {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		//Firefox
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		//Chrome123
		//System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
	}

	@Test(groups = "abc")
	public void TC_01_ValidateCurrentUrl() {
		// Login Page url matching
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://demo.guru99.com/v4/");
		System.out.println("một hai ba");

	}

	@Test
	public void TC_02_ValidatePageTitle() {
		// Login Page titleO
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "Guru99 Bank Home Page");
	}

	@Test
	public void TC_03_LoginFormDisplayed() {
		// Login form displayed
		Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}


}
