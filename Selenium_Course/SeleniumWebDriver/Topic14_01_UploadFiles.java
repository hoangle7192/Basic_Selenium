package SeleniumWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class Topic14_01_UploadFiles {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	String name01 = "name01.jpg";
	String name02 = "name02.jpg";
	String name03 = "name03.jpg";
	String name01Path = projectPath + "\\uploadFiles\\" + name01;
	String name02Path = projectPath + "\\uploadFiles\\" + name02;
	String name03Path = projectPath + "\\uploadFiles\\" + name03;

	@BeforeClass
	public void beforeClass() {
		//Firefox
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//Chrome123
		//System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 15);
	}
	
	@Test
	public void TC_01_SendKeys_single_file() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// load file bằng cách sendkeys
		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(name01Path);
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + name01 + "']")).isDisplayed());

		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(name02Path);
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + name02 + "']")).isDisplayed());

		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(name03Path);
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + name03 + "']")).isDisplayed());

		// upload image lên sever
		List<WebElement> startButtonList = driver.findElements(By.cssSelector("table.table button.start"));
		for (WebElement startButton : startButtonList) {
			startButton.click();
			// wait
			Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.row div.progress-bar-success"))));
		}

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + name01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + name02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + name03 + "']")).isDisplayed());

	}

	@Test
	public void TC_01_SendKeys_multiple_file() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// load file bằng cách sendkeys (phải có kí tự \n ở giữa các path)
		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(name01Path + "\n" + name02Path + "\n" + name03Path);
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + name01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + name02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + name03 + "']")).isDisplayed());

		// upload image lên sever
		List<WebElement> startButtonList = driver.findElements(By.cssSelector("table.table button.start"));
		for (WebElement startButton : startButtonList) {
			startButton.click();
			// wait
			Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.row div.progress-bar-success"))));
		}

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + name01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + name02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + name03 + "']")).isDisplayed());
	}

	@Test
	public void TC_03_GoFile() {
		driver.get("https://gofile.io/uploadFiles");

		driver.findElement(By.cssSelector("#uploadFile-Input")).sendKeys(name01Path + "\n" + name02Path + "\n" + name03Path);

		// wait
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.cssSelector("div.progress"))));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#rowLoading i.fa-spinner")));
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Your files have been successfully uploaded')]"))).isDisplayed());

		String dowloadPage = driver.findElement(By.cssSelector("#rowUploadSuccess-downloadPage")).getAttribute("href");
		driver.get(dowloadPage);

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#mainContent i.fa-spinner")));
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + name01 +"'] ")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + name02 +"'] ")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + name03 +"'] ")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



}
