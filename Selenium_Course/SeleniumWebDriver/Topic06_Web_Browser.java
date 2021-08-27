package SeleniumWebDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic06_Web_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	// Tao bien
	By myAccountButton = By.xpath("//div[@class='footer']//a[@title='My Account']");
	By createButton = By.xpath("//a[@title='Create an Account']");

	@BeforeClass
	public void beforeClass() {
		// Firefox
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		// Chrome123
		// System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		// driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();					
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver.get("http://live.demoguru99.com/");
	}
	
    @Test
    public void TC_01_Verrify_URL() {
    	// Click URL My Account
    	driver.findElement(myAccountButton).click();
    	// Verify Loginpage URL
    	String loginPageUrl = driver.getCurrentUrl();
    	Assert.assertEquals(loginPageUrl, "http://live.demoguru99.com/index.php/customer/account/login/");
        // Click Create Button
    	driver.findElement(createButton).click();
    	// Verify Registerpage URL
    	String registerPageUrl = driver.getCurrentUrl();
    	Assert.assertEquals(registerPageUrl, "http://live.demoguru99.com/index.php/customer/account/create/");
    }
    
    @Test
    public void TC_02_Verify_Title() {
    	// Click URL My Account
    	driver.findElement(myAccountButton).click();
    	// Verify Login Page's Title
    	String loginPageTitle = driver.getTitle();
    	Assert.assertEquals(loginPageTitle, "Customer Login");
        // Click Create Button
    	driver.findElement(createButton).click();
    	// Verify Register Page's Title
    	String registerPageTitle = driver.getTitle();
    	Assert.assertEquals(registerPageTitle, "Create New Customer Account");
    }
    
    @Test
    public void TC_03_Navigate_Function() {
    	// Click URL My Account
    	driver.findElement(myAccountButton).click();
        // Click Create Button
    	driver.findElement(createButton).click();
    	// Verify Registerpage URL
    	String registerPageUrl = driver.getCurrentUrl();
    	Assert.assertEquals(registerPageUrl, "http://live.demoguru99.com/index.php/customer/account/create/");
    	// Back to LoginPage
    	driver.navigate().back();
    	// Verify Loginpage URL
    	String loginPageUrl = driver.getCurrentUrl();
    	Assert.assertEquals(loginPageUrl, "http://live.demoguru99.com/index.php/customer/account/login/");
    	// Foward to Register Page
    	driver.navigate().forward();
    	// Verify Register Page's Title
    	String registerPageTitle = driver.getTitle();
    	Assert.assertEquals(registerPageTitle, "Create New Customer Account");
    }
    
    @Test
    public void TC_04_Get_Page_SrcCode() {
    	// Click URL My Account
    	driver.findElement(myAccountButton).click();
    	// Verify Text at Login Page
    	String loginPageSource = driver.getPageSource();
    	Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));
    	// Verify Text at Register Page
    	driver.findElement(createButton).click();
    	String registerPageSource = driver.getPageSource();
    	Assert.assertTrue(registerPageSource.contains("Create an Account"));
    }
    
    @AfterClass
    public void afterClass() {
            driver.quit();
    }
}
