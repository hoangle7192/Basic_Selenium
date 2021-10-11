package SeleniumWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

// [Online 21] - Topic 24 (Handle Windows/ Tabs) - https://www.youtube.com/watch?v=7Al14b-SRQ8&list=PLo1QA-RK2zyosMdELpBN0h027Ht7PA2kj&index=27
//


public class Topic11_12_03_Windows_Tabs {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {

        // Firefox
        //System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
        //driver = new FirefoxDriver();

        // Chrome123
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();

        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_07_Window() {  // chỉ đúng cho 2 window hoặc 2 tab
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Get ra ID của active tab/windows (driver đang đứng) -> 1 cái
        String parentID = driver.getWindowHandle();
        System.out.println("parentID :" + parentID);

        // Click để mở window facebook
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();

        // gọi hàm để switch qua window khác
        switchToWindowByID(parentID);
        String childID = driver.getWindowHandle();

        // Input data
        driver.findElement(By.cssSelector("#email")).sendKeys("abc@gmail.com");

        // Switch qua lại window cha
        switchToWindowByID(childID);
        System.out.println(driver.getTitle());

    }

    public void switchToWindowByID(String switchID) { // chỉ đúng cho 2 window hoặc 2 tab
        // Get ra ID của tất cả tab/window đang có-> all
        Set<String> allWindows = driver.getWindowHandles();  // chứa những giá trị UNIQUE

        for (String id : allWindows) {
            System.out.println("windowID =" + id);
            // Nếu id mà khác với switchID thì switch vào id đó
            if (!id.equals(switchID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    @Test
    public void TC_08_Window() { // dùng cho 2 window hoặc 2 tab trở lên
        // truy cập vào url
        driver.get("https://kyna.vn/");

        // xử lý popup
        By popupline = By.cssSelector("div.fancybox-skin");
        By popupClose = By.cssSelector("a[title*='Close']");
        popupHandleBy(popupline,popupClose);

        // Scroll xuống cuối trang
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight || document.documentElement.scrollHeight)");

        // Click vào link FB để mở FB ra
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content>iframe")));
        driver.findElement(By.cssSelector("a[title='Kyna.vn']")).click();
        driver.switchTo().defaultContent(); // Switch ra lại parent

        // Click vào 2 link của bộ công thương

    }

    public void switchWindowTitle(String expectedTitle) { // dùng cho 2 window hoặc 2 tab trở lên
        Set<String> windowID = driver.getWindowHandles();
        for (String window : windowID) {
            driver.switchTo().window(window);
            String windowTitle = driver.getTitle();
            if (windowTitle.equals(expectedTitle)) {
                break;
            }
        }
    }

    public void popupHandleBy(By popupline, By popupClose) {
        List<WebElement> popups = driver.findElements(popupline);
        if (popups.size()>0 && popups.get(0).isDisplayed()) {
            driver.findElement(popupClose).click();
            System.out.println("Có hiển thị poup");
        } else {
            System.out.println("Không hiển thị popup");
        }
    }

    public void sleepInSecond(long timeOutInSecond) {
        try {
            Thread.sleep(timeOutInSecond*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

}
