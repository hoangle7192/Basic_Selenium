package Method_In_Java;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Java_script_executor {
	WebDriver driver;
	JavascriptExecutor jsExcutor;
	
	@BeforeClass
	public void beforeClass() {
		// Add kieu tuong minh jsExecutor (Reference casting)
		jsExcutor = (JavascriptExecutor) driver;

	}
	
	@Test
	public void TC01() {
		// delete attribute of element
		jsExcutor.executeScript("arguments[0].removeAttribute('type')", driver.findElement(By.xpath("xpath")));			
		
		// click
		jsExcutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("a")));
		
		// scroll
		jsExcutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("a")));
		
	}
}
