package SeleniumWebDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

// [Online 22] - Topic 21 (Handle Custom Radio/ Checkbox) - https://www.youtube.com/watch?v=1mrUHLB3dGk

public class Topic09_02_Button_and_CustomCheckBox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor  jsExecutor;

	@BeforeClass
	public void beforeClass() {
		// Firefox
		//System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();

		// Chrome123
		System.setProperty("webdriver.chrome.driver", projectPath +
		 "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		jsExecutor = (JavascriptExecutor) driver; // add kiểu tường minh		 

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_03_Radio_custom_web() {
		driver.get("https://material.angular.io/components/radio/examples");
		
		By summerRadioButton = By.cssSelector("input[value=Summer]");
		clickRadioCustom(summerRadioButton);
		Assert.assertTrue(driver.findElement(summerRadioButton).isSelected());
	}
	
	//@Test
	public void TC_03_Checkbox_custom_web() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		By checkboxChecked = By.xpath("//span[contains(text(),'Checked')]/preceding-sibling::span/input");
		By checkboxIndeterminate = By.xpath("//span[contains(text(),'Indeterminate')]/preceding-sibling::span/input");
		clickRadioCustom(checkboxChecked);
		clickRadioCustom(checkboxIndeterminate);
		Assert.assertTrue(driver.findElement(checkboxChecked).isSelected());
		Assert.assertTrue(driver.findElement(checkboxIndeterminate).isSelected());
		sleepInSecond(3);
		
		clickRadioCustom(checkboxIndeterminate);
		Assert.assertFalse(driver.findElement(checkboxIndeterminate).isSelected());
	}
	
	@Test
	public void TC_04_custom_googledoc() {
	 driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
	 // radio
	 By hcmRadio = By.xpath("//div[@aria-label='Hồ Chí Minh']");
	 By dnRadio = By.xpath("//div[@aria-label='Đà Nẵng']");
	 
	 clickRadioCustom(hcmRadio);
	 Assert.assertEquals(driver.findElement(hcmRadio).getAttribute("aria-checked"), "true");
	 clickRadioCustom(dnRadio);
	 Assert.assertEquals(driver.findElement(dnRadio).getAttribute("aria-checked"), "true");
	 
	 sleepInSecond(5);
	 
	 // checkbox
	 By quangninh = By.xpath("//div[@data-answer-value='Quảng Ninh']");
	 By quangbinh = By.xpath("//div[@data-answer-value='Quảng Bình']");
	 
	 checkBoxCustom(quangninh);
	 Assert.assertEquals(driver.findElement(quangninh).getAttribute("aria-checked"), "true");
	 sleepInSecond(5);
	 checkBoxCustom(quangninh);
	 Assert.assertEquals(driver.findElement(quangninh).getAttribute("aria-checked"), "false");
	 sleepInSecond(5);
	 
	 checkBoxCustom(quangbinh);
	 Assert.assertEquals(driver.findElement(quangbinh).getAttribute("aria-checked"), "true");
	 sleepInSecond(5);
	 checkBoxCustom(quangbinh);
	 Assert.assertEquals(driver.findElement(quangbinh).getAttribute("aria-checked"), "false");
	 
	 // checkbox all
	  List<WebElement> allcheckbox = driver.findElements(By.xpath("//div[@role='checkbox']"));
	 for (WebElement checkbox : allcheckbox) {
		checkbox.click();
		sleepInSecond(2);
		 Assert.assertEquals(checkbox.getAttribute("aria-checked"), "true");
	}
	 
	}
	
	public void clickRadioCustom(By by) {
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
	}
	
	public void checkBoxCustom(By by) {
		if (!driver.findElement(by).isSelected()) {
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
		} else {
			if(driver.findElement(by).isSelected());
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
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
