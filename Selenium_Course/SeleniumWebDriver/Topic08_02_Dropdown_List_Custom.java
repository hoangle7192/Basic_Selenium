package SeleniumWebDriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//[Online 22] - Topic 18 (Handle Custom Dropdown - Part I) - https://www.youtube.com/watch?v=VJbNMWAG8tU
//[Online 22] - Topic 19 (Handle Custom Dropdown - Part II) - https://www.youtube.com/watch?v=mw_IP05IEjU

public class Topic08_02_Dropdown_List_Custom {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExcutor;
	String projectPath = System.getProperty("user.dir");
	


	@BeforeClass
	public void beforeClass() {
		// Firefox
		// System.setProperty("webdriver.gecko.driver",
		// ".\\browserDrivers\\geckodriver.exe");
		// driver = new FirefoxDriver();

		// Chrome123
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addExtensions(new File(projectPath + "\\browserExtension\\UltraSurf-VPN_v1.6.0.crx"));
		driver = new ChromeDriver(options);
		
		// Add kieu tuong minh jsExecutor (Reference casting)
		jsExcutor =  (JavascriptExecutor) driver;

		// Wait de tim element (findElement/ findElements)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Wait de apply cho cac trang thai cua Element 
		// visible: nhin thay thao tac duoc; invisible: bi an ko thao tac duoc; 
		// presense: Wait cho tat ca Element duoc load ra; clickable: Wait cho TH click/sendkey..da thanh cong hay chua
		explicitWait = new WebDriverWait(driver, 15);
		
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Basic() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html"); 
		
		// 1- Click vao dropdown
		driver.findElement(By.cssSelector("#number-button")).click();
		
		// 2- Wait cho tat ca Element duoc load ra (co trong HTML/DOM) -- Dung ham presense
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));
		
		// Store lai tat ca Element (item cua dropdown)
		List<WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu div"));
		System.out.println("All item = " + allItems.size());
		
		for (WebElement item : allItems) {
			if(item.getText().equals("19")) {
				if(item.isDisplayed()) {
					item.click(); // 3- Neu item minh can chon no nam trong view (nhin thay duoc) thi click vao chon
				}else {
					jsExcutor.executeScript("arguments[0].scrollIntoView(true);", item);
					item.click(); // 4- Neu item minh can chon ko nam trong view thi scroll roi click
				}
			}
		}			
	}
	
	//@Test
	public void TC_02_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html"); 
		
		By parentBy = By.cssSelector("#number-button");
		By childBy = By.cssSelector("ul#number-menu div");
		
		selectItemInDropdown(parentBy, childBy, "5" );
		Assert.assertTrue(isElementdisplayed(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']")));
		
		selectItemInDropdown(parentBy, childBy, "7" );
		Assert.assertTrue(isElementdisplayed(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='7']")));
		
		selectItemInDropdown(parentBy, childBy, "11" );
		//sleepInSecond(3);		
		Assert.assertTrue(isElementdisplayed(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='11']")));
	}
	
	//@Test
	public void TC_03_reactJs() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/"); 
		
		By parentBy = By.xpath("//div[@class='ui fluid selection dropdown']");
		By childBy = By.xpath("//div[@class='visible menu transition']/child::div//span");
		
		selectItemInDropdown(parentBy, childBy, "Matt" );
		Assert.assertTrue(isElementdisplayed(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']")));
		
		selectItemInDropdown(parentBy, childBy, "7" );
		Assert.assertTrue(isElementdisplayed(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='7']")));
		
		selectItemInDropdown(parentBy, childBy, "11" );
		sleepInSecond(3);		
		Assert.assertTrue(isElementdisplayed(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='11']")));
	}

	//@Test
	public void TC_04_VueJS() {
		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");	
		
		// 1.Categories:
		// Chờ đến khi icon loading biến mất trong vòng 15s
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.kd-loader"))));		
		// Chờ đến khi icon loading trong dwopdown biến mất
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.k-i-loading"))));
				
		selectItemInDropdown(By.cssSelector("span[aria-controls='categories_listbox']"), By.cssSelector("span.k-state-default>h3"), "Dairy Products");
		sleepInSecond(3);	
		
		// 2.Product1:
		// Chờ đến khi icon loading trong dwopdown biến mất
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.k-i-loading"))));
		selectItemInDropdown(By.cssSelector("span[aria-controls='products_listbox']"), By.cssSelector("ul#products_listbox>li"), "Mascarpone Fabioli");
		sleepInSecond(3);	
		
		// 3.Product2:
		// Chờ đến khi icon loading trong dwopdown biến mất
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.k-i-loading"))));
		selectItemInDropdown(By.cssSelector("span[aria-controls='shipTo_listbox']"), By.cssSelector("ul#shipTo_listbox>li"), "Rua do Paço, 67");
		sleepInSecond(3);	
		
	}
	
	//@Test
	public void TC_05_Editable() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		By parentBy = By.xpath("//div[text()='Default']/following-sibling::div/input");
		By childBy = By.xpath("//ul[@class='es-list' and @style]/li");
		
		selectEditItemInDropdown(parentBy, childBy, "Citroen");
		sleepInSecond(5);
		selectEditItemInDropdown(parentBy, childBy, "Renault");
	}
	
	public void selectItemInDropdown(By parentBy, By childBy, String expectedTextItem) {
		// 1- Chờ cho element được phép click, sau đó Click vao dropdown
		explicitWait.until(ExpectedConditions.elementToBeClickable(parentBy)).click();
		
		// 2- Wait cho tat ca Element duoc load ra (co trong HTML/DOM) -- Dung ham presense
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
		
		// Store lai tat ca Element (item cua dropdown)
		List<WebElement> allItems = driver.findElements(childBy); //explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
		System.out.println("All item = " + allItems.size());
		
		for (WebElement item : allItems) {
			if(item.getText().equals(expectedTextItem)) {
				if(item.isDisplayed()) {
					item.click(); // 3- Neu item minh can chon no nam trong view (nhin thay duoc) thi click vao chon
				}else {
					jsExcutor.executeScript("arguments[0].scrollIntoView(true);", item);
					item.click(); // 4- Neu item minh can chon ko nam trong view thi scroll roi click
				}
			}
		}
	}
	
	public void selectEditItemInDropdown(By parentBy, By childBy, String expectedTextItem) {
		// 1- clear element, sau do sendkay vao
		driver.findElement(parentBy).clear();
		driver.findElement(parentBy).sendKeys(expectedTextItem);;
		
		// 2- Wait cho tat ca Element duoc load ra (co trong HTML/DOM) -- Dung ham presense
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
		
		// Store lai tat ca Element (item cua dropdown)
		List<WebElement> allItems = driver.findElements(childBy); //explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
		System.out.println("All item = " + allItems.size());
		
		for (WebElement item : allItems) {
			if(item.getText().equals(expectedTextItem)) {
				if(item.isDisplayed()) {
					item.click(); // 3- Neu item minh can chon no nam trong view (nhin thay duoc) thi click vao chon
				}else {
					jsExcutor.executeScript("arguments[0].scrollIntoView(true);", item);
					item.click(); // 4- Neu item minh can chon ko nam trong view thi scroll roi click
				}
			}
		}
	}
	
	//-----------------------------------------------------
	@Test
	public void TC_06_multiple() {
		
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		
		String[] firstMonth = {"January", "April", "October"};
		String[] secondMonth = {"January", "April", "October", "December"};
		 
		 selectMultiItemInDropdown("(//button[@class='ms-choice'])[1]", "(//button[@class='ms-choice'])[1]/following-sibling::div/ul//span", firstMonth);
		 sleepInSecond(5);
		 Assert.assertTrue(areItemSelected(firstMonth));
		 
		 driver.navigate().refresh();
		 
		 selectMultiItemInDropdown("(//button[@class='ms-choice'])[1]", "(//button[@class='ms-choice'])[1]/following-sibling::div/ul//span", secondMonth);
		 sleepInSecond(5);
		 Assert.assertTrue(areItemSelected(secondMonth));
		 
	}
	
	public void selectMultiItemInDropdown(String parentXpath, String childXpath, String[] expectedValueItem) {
		// 1: click vào cái dropdown cho nó xổ hết tất cả các giá trị ra
		driver.findElement(By.xpath(parentXpath)).click();

		// 2: chờ cho tất cả các giá trị trong dropdown được load ra thành công
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

		List<WebElement> allItems = driver.findElements(By.xpath(childXpath));

		// Duyệt qa hết tất cả các phần tử cho đến khi thỏa mãn điều kiện
		for (WebElement childElement : allItems) {
			// "January", "April", "July"
			for (String item : expectedValueItem) {
				if (childElement.getText().trim().equals(item)) {
					// 3: scroll đến item cần chọn (nếu như item cần chọn có thể nhìn thấy thì ko cần scroll)
					jsExcutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
					sleepInSecond(1);

					// 4: click vào item cần chọn
					childElement.click();
					sleepInSecond(1);
					
					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item selected = " + itemSelected.size());
					if (expectedValueItem.length == itemSelected.size()) {
						break;
					}
				}
			}
		}
	}
  
  public boolean areItemSelected(String[] months) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberItemSelected = itemSelected.size();

		String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
		System.out.println("Text da chon = " + allItemSelectedText);

		if (numberItemSelected <= 3 && numberItemSelected > 0) {
			boolean status = true;
			for (String item : months) {
				if (!allItemSelectedText.contains(item)) {
					status = false;
					return status;
				}
			}
			return status;
		} else if (numberItemSelected >= 12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']")).isDisplayed();
		} else if (numberItemSelected > 3 && numberItemSelected < 12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']")).isDisplayed();
		} else {
			return false;
		}
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
		// driver.quit();
	}

}
