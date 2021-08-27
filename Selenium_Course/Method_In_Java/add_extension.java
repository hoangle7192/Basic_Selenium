package Method_In_Java;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

public class add_extension {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		
		// Chrome123
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		
		// Add extension to browser
		// 1. copy link refer đến extension cần add
		// 2. mở trang: https://chrome-extension-downloader.com/ , get file crx về local
		// 3. tạo folder browserExtension trên IDE, paste vào folder
		// 4. Config trên IDE
		
		// 4.1 cấu hình profile cho Chrome
		ChromeOptions options = new ChromeOptions();
		options.addExtensions(new File(projectPath + "\\browserExtension\\UltraSurf-VPN_v1.6.0.crx"));
		driver = new ChromeDriver(options);
		
	}
	


}
