package SeleniumWebDriver;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


public class Topic15_16_Wait_Fluent {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	Select select;

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

		//explicitWait = new WebDriverWait(driver, 10);
	}	
	
	@Test
	public void TC_08_Time() {
		driver.get("https://automationfc.github.io/fluent-wait/");

		WebElement countDownTime = driver.findElement(By.id("javascript_countdown_time"));
		FluentWait<WebElement> fluentWait = new FluentWait<WebElement>(countDownTime);

		fluentWait.withTimeout(Duration.ofSeconds(13))
				// Tần số mỗi 1s check 1 lần
				.pollingEvery(Duration.ofMillis(100))
				// Nếu gặp exception là find ko thấy element sẽ bỏ qua
				.ignoring(NoSuchElementException.class)
				// kiểm tra điều kiện
				.until(new Function<WebElement, Boolean>() {
					public Boolean apply(WebElement countDown) {
						System.out.println(countDown.getText());
						return countDown.getText().endsWith("00");
					}
				});
	}




	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
