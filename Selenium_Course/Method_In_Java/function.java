package Method_In_Java;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

public class function {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		jsExecutor = (JavascriptExecutor) driver; // add jsExecutor vao driver
		
		// Wait de apply cho cac trang thai cua Element 
		// visible: nhin thay thao tac duoc; invisible: bi an ko thao tac duoc; 
		// presense: Wait cho tat ca Element duoc load ra; clickable: Wait cho TH click/sendkey..da thanh cong hay chua
		explicitWait = new WebDriverWait(driver, 15); 
	}
	
	// function for Radio
	public void radioCheckBox(By by) {
		driver.findElement(by).click();
	}
	
	// function for Checkbox
	public void checkToCheckBox(By by) {
		if(!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		} else {
			if(driver.findElement(by).isSelected()) {
				driver.findElement(by).click();
			}
		}
	}
	
	// function for Sendkey
	public void sendKeyElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);
	}

	
	// function for click
	public void clickElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}

	
	// function for Clear
	public void clearElement(By by) {
		WebElement element = driver.findElement(by);
		element.clear();
	}

	
	// function for IsDisplayed
	public boolean isElementdisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element[" + by + "] is displayed");
			return true;
		} else {
			System.out.println("Element[" + by + "] is not displayed");
			return false;
		}
	}

	
	// function for isdisable
	public boolean isElementenable(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element[" + by + "] is enable");
			return true;
		} else {
			System.out.println("Element[" + by + "] is disable");
			return false;
		}
	}

	
	// function for isSelected
	public boolean isElementSelect(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element[" + by + "] is selected");
			return true;
		} else {
			System.out.println("Element[" + by + "] is unselected");
			return false;
		}
	}
	
	// function for custom dropdownlist
	public void selectItemInDropdown(By parentBy, By childBy, String value) {
		// 1- Chờ cho element được phép click, sau đó Click vao dropdown
		explicitWait.until(ExpectedConditions.elementToBeClickable(parentBy)).click();
		
		// 2- Wait cho tat ca Element duoc load ra (co trong HTML/DOM) -- Dung ham presense
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
		
		// Store lai tat ca Element (item cua dropdown)
		List<WebElement> allItems = driver.findElements(childBy); //explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
		System.out.println("All item = " + allItems.size());
		
		for (WebElement item : allItems) {
			if(item.getText().equals(value)) {
				if(item.isDisplayed()) {
					item.click();  // 3- Neu item minh can chon no nam trong view (nhin thay duoc) thi click vao chon
				}
			} else {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
				item.click(); // 4- Neu item minh can chon ko nam trong view thi scroll roi click
			}
		}  // sau đó kết hợp với function isDisplayed để verify giá trị
		// Assert.assertTrue(isElementdisplayed(By.xpath("xpath and text()='5']")));
	}

}
