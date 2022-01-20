package TestNG.parameter_multiBrowser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.util.concurrent.TimeUnit;


public class Test_setup_enviroment {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@Parameters({"browser", "enviromentName", "url"})

	@BeforeClass
	//public void beforeClass(@Optional("Firefox") String browserName, @Optional("Firefox") String enviromentName, @Optional("Firefox") String url)
	public void beforeClass(String browserName, @Optional("Windows") String enviromentName, String url) {

		System.out.println(enviromentName);
		switch (browserName) {
			case "Firefox":
				System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
				driver = new FirefoxDriver();
				break;
			case "Chrome":
				System.setProperty("webdriver.chrome.driver", projectPath + File.separator + "browserDrivers" + File.separator + "chromedriver.exe");
				driver = new ChromeDriver();
				break;
			case "Edge"	:
				System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
				driver = new EdgeDriver();
				break;
			default:
				throw new RuntimeException("Browser not support");
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
	}	

	@Parameters("url")
	@Test
	public void TC_01_ValidateCurrentUrl(String url) {
		// Login Page url matching
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, url);
		
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
