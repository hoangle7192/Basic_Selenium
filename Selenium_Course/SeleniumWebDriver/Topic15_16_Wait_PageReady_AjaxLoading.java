package SeleniumWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Topic15_16_Wait_PageReady_AjaxLoading {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		//Firefox
		//System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();

		//Chrome123
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Visible() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.findElement(By.id("txtUsername")).sendKeys("admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();

		//Assert.assertTrue(isJQueryAndAjaxLoadedSuccess());
		Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='total']//span")).getText(), "3 month(s)");

		driver.findElement(By.cssSelector("#menu_pim_viewPimModule>b")).click();
		//Assert.assertTrue(isJQueryAndAjaxLoadedSuccess());
		driver.findElement(By.xpath("//label[text()='Employee Name']/following-sibling::input[@id='empsearch_employee_name_empName']")).sendKeys("ttext");
	}


	// --------------Chờ cho Page load xong sẽ trả về giá trị TRUE --------//
	public boolean isJQueryLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, 10);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean>jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver webDriver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery!=null) && (jQuery.active===0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

	//--------- Chờ cho Ajax Loading và Page load xong -----------//
	public boolean isJQueryAndAjaxLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, 10);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean>jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
						return ((Long) jsExecutor.executeScript("return JQuery.active")==0);
				} catch (Exception e) {
						return true;
				}
			}
		};

		ExpectedCondition<Boolean> ajaxIconLoading = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver webDriver) {
				return jsExecutor.executeScript("return $('.raDiv').is(':visible')").toString().equals("false");
			}
		};
		return  explicitWait.until(jQueryLoad) && explicitWait.until(ajaxIconLoading);
	}

	// ---------------------------------------------- //
	public boolean isJQueryAndPageLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, 10);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean>jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver webDriver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") ==0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean>jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver webDriver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}


	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
