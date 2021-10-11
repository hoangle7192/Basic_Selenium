package SeleniumWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

// [Online 21] - Topic 23 (Handle Frame/ Iframe) - https://www.youtube.com/watch?v=pIgCMolCjWo&list=PLo1QA-RK2zyosMdELpBN0h027Ht7PA2kj&index=25
//

// Định nghĩa Frame-Iframe
// Frame: nhúng html của cùng 1 domain thì gọi là Frame
// Iframe: nhúng html của trang web khác domain thì gọi là Iframe

public class Topic11_12_02_Frame_Iframe {
    WebDriver driver;
    Select select;
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
    public void TC_05_Iframe() {
        driver.get("https://kyna.vn/");

        // Xử lý close popup
        By popupLine = By.cssSelector("div.fancybox-outer");
        By closePopup = By.cssSelector("div.fancybox-outer +a");
        popupHandle(popupLine,closePopup);

        // Scroll xuống dưới cùng của page
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight || document.documentElement.scrollHeight)");

        // Switch qua Iframe Facebook: co 3 cach dung (1.index; 2.name.id; 3.element)
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content>iframe")));

        // Verify số lượng like của fanpage
        Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(), "168K lượt thích");
        System.out.println(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText());

        // Switch qua lại parent
        driver.switchTo().defaultContent();
        // Switch qua khung chat iframe
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#cs_chat_iframe")));
        // Click
        driver.findElement(By.cssSelector("div.button_bar")).click();
        // Input thông tin vào khung chat
        driver.findElement(By.xpath("//div[@class='container_and_brand_wrapper']//input[@placeholder='Nhập tên của bạn']")).sendKeys("kimkim");
        driver.findElement(By.xpath("//div[@class='container_and_brand_wrapper']//input[@placeholder='Nhập số điện thoại của bạn']")).sendKeys("0234567894");
        select = new Select(driver.findElement(By.xpath("//div[@class='container_and_brand_wrapper']//select[@id='serviceSelect']")));
        select.selectByVisibleText("HỖ TRỢ KỸ THUẬT");
        driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("xin chào nhé");
        //driver.findElement(By.cssSelector("input[value='Gửi tin nhắn']")).click();

        // Switch về lại parent
        driver.switchTo().defaultContent();
        // Scroll lên trên cùng của page
        jsExecutor.executeScript("window.scrollTo(document.body.scrollHeight,0)");
        // Input data vào khung tìm kiếm
        driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("excel");
        driver.findElement(By.cssSelector("button.search-button")).click();
        List<WebElement> artical = driver.findElements(By.cssSelector("div.content h4"));
        Assert.assertEquals(artical.size(), 10);
        for(WebElement list: artical) {
            Assert.assertTrue(list.getText().toLowerCase().contains("excel"));
            System.out.println("Ten khoa hoc :" + list.getText().toLowerCase());
        }
    }

    @Test
    public void TC_06_Frame() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");

        // switch qua frame chứa CustomerID
        driver.switchTo().frame(driver.findElement(By.name("login_page")));
        // input data
        driver.findElement(By.cssSelector("input.form-control")).sendKeys("testabcagd");
        driver.findElement(By.cssSelector("a.login-btn")).click();

        Assert.assertTrue(driver.findElement(By.name("fldPassword")).isDisplayed());

        // switch lại parent
        driver.switchTo().defaultContent();
        // switch qua frame
        driver.switchTo().frame(driver.findElement(By.name("login_page")));

        // get windowID
        String parendID = driver.getWindowHandle();

        // Click
        driver.findElement(By.xpath("//a[text()='Terms and Conditions']")).click();

        // switch qua tab khác
        switchToWindowID(parendID);
        System.out.println(driver.getTitle());
        driver.manage().window().maximize();

        driver.findElement(By.cssSelector("div#nvpush_cross>svg")).click();

    }
    public void sleepInSecond(long timeOutInSecond) {
        try {
            Thread.sleep(timeOutInSecond*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void popupHandle(By popupLine, By closePopup) {
        List<WebElement> popup = driver.findElements(popupLine);
        if(popup.size()>0 && popup.get(0).isDisplayed()) {
            System.out.println("Popup có hiển thị");
            driver.findElement(closePopup).click();
        } else {
            System.out.println("Popup không hiển thị");
        }
    }

    public void switchToWindowID(String switchID) {
        Set<String> allIDs = driver.getWindowHandles(); // get hết all window ID
        for (String id : allIDs) {
            if (!id.equals(switchID));
            driver.switchTo().window(id);
        }
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

}
