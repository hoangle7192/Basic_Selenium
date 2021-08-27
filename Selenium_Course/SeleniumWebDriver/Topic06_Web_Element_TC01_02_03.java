package SeleniumWebDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic06_Web_Element_TC01_02_03 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	By emailTextboxBy = By.id("mail");
	By ageUnder18RadioBy = By.id("under_18");
	By educationTextareaBy = By.id("edu");
	By jobRole01DropdownBy = By.id("job1");
	By jobRole02DropdownBy = By.id("job2");
	By user5TextBy = By.xpath("//h5[text()='Name: User5']");
	By developmentCheckboxBy = By.xpath("//label[text()='Development']");
	By slider01By = By.id("slider-1");
	
	By passwordBy = By.id("password");
	By radioButtonBy = By.id("radio-disabled");
	By biographyTextboxBy = By.id("bio");
	By jobRole03DropdownBy = By.id("job3");
	By checkboxBy = By.id("check-disbaled");
	By slider02By = By.id("slider-2");
	
	By javaCheckboxBy = By.id("java");
	
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
	
		
    //@Test
    public void TC_01_isDisplayed_C1() {
    	driver.get("https://automationfc.github.io/basic-form/index.html");
    	// Email
    	WebElement emailTextbox = driver.findElement(By.id("mail"));

    	if (emailTextbox.isDisplayed()) {
			emailTextbox.sendKeys("Automation FC");
			System.out.println("Email Textbox is displayed");
		} else {
			System.out.println("Email Textbox is not displayed");
		}
    	
    	// Age
    	WebElement ageUnder18Radio = driver.findElement(By.id("under_18"));
    	
    	if (ageUnder18Radio.isDisplayed()) {
			ageUnder18Radio.click();
			System.out.println("Radio Under 18 is displayed");
		} else {
			System.out.println("Radio Under 18 is not displayed");
		}
    	
    	// Edu 	
    	WebElement eduTextArea = driver.findElement(By.id("edu"));
    	
    	if (eduTextArea.isDisplayed()) {
			eduTextArea.sendKeys("AutomationFC");
			System.out.println("Education is displayed");
		} else {
			System.out.println("Education is not displayed");
		}
    	
    	// User5
    	WebElement user5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
    	
    	if (user5Text.isDisplayed()) {
			System.out.println("User5 is displayed");
		} else {
			System.out.println("User5 is not displayed");
		}
    }
    
    //@Test
    public void TC_01_isDisplayed_C2() {
    	// get url
    	driver.get("https://automationfc.github.io/basic-form/index.html");
    
    	if(isElementDisplayed(emailTextboxBy)) {
    		sendKeyToElement(emailTextboxBy, "Automation testing");
    	}
    	
    	if(isElementDisplayed(ageUnder18RadioBy)) {
    		clickToElement(ageUnder18RadioBy);
    	}
    	
    	if(isElementDisplayed(educationTextareaBy)) {
    		sendKeyToElement(educationTextareaBy, "Automation");
    	}
    	
    	Assert.assertFalse(isElementDisplayed(user5TextBy));
    }
    
    //@Test
    public void TC_02_isEnable() {
    	driver.get("https://automationfc.github.io/basic-form/index.html");  
    	
    	// Mong doi Element Enable
    	Assert.assertTrue(isElementEnable(emailTextboxBy));
    	Assert.assertTrue(isElementEnable(ageUnder18RadioBy));
    	Assert.assertTrue(isElementEnable(educationTextareaBy));
    	Assert.assertTrue(isElementEnable(jobRole01DropdownBy));
    	Assert.assertTrue(isElementEnable(jobRole02DropdownBy));
    	Assert.assertTrue(isElementEnable(developmentCheckboxBy));
    	Assert.assertTrue(isElementEnable(slider01By));
    	
    	// Mong doi Element Disnable
    	Assert.assertFalse(isElementEnable(passwordBy));
    	Assert.assertFalse(isElementEnable(radioButtonBy));
    	Assert.assertFalse(isElementEnable(biographyTextboxBy));
    	Assert.assertFalse(isElementEnable(jobRole03DropdownBy));
    	Assert.assertFalse(isElementEnable(checkboxBy));
    	Assert.assertFalse(isElementEnable(slider02By));
    }
    
    @Test
    public void TC_03_isSelected() {
    	driver.get("https://automationfc.github.io/basic-form/index.html"); 
    	
    	clickToElement(ageUnder18RadioBy);
    	Assert.assertTrue(isElementSelected(ageUnder18RadioBy));
    	clickToElement(javaCheckboxBy);
    	Assert.assertTrue(isElementSelected(javaCheckboxBy));
    	
    	clickToElement(javaCheckboxBy);
    	Assert.assertFalse(isElementSelected(javaCheckboxBy));

    }
    
    public boolean isElementDisplayed(By by) {
    	WebElement element = driver.findElement(by);
    	if (element.isDisplayed()) {
			System.out.println("Element["+ by +"] is displayed");
			return true;
		} else {
			System.out.println("Element["+ by +"] is not displayed");
			return false;
		}
    }
    
    
    public void sendKeyToElement(By by, String value) {
    	WebElement element = driver.findElement(by);
    	element.clear();
    	element.sendKeys(value);
    }
    
    public void clickToElement(By by) {
    	WebElement element = driver.findElement(by);
    	element.click();
    }
    
    public boolean isElementEnable(By by) {
    	WebElement element = driver.findElement(by);
    	if (element.isEnabled()) {
			System.out.println("Element["+ by + "] is Enabled");
			return true;
		} else {
			System.out.println("Element["+ by + "] is Dinabled");
			return false;
		}
    }
    
    public boolean isElementSelected(By by) {
    	WebElement element = driver.findElement(by);
    	if (element.isSelected()) {
			System.out.println("Element[" +by+ "] is selected");
			return true;
		} else {
			System.out.println("Element[" +by+ "] is not selected");
			return false;
		}
    }
    
    @AfterClass
    public void afterClass() {
            //driver.quit();
    }
}
