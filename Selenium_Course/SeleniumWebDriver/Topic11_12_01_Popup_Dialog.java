package SeleniumWebDriver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SourceType;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.internal.ExpectedExceptionsHolder;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

// [Online 21] - Topic 22 (Handle Popup/ Dialog) - https://www.youtube.com/watch?v=aPOsNdywl50&list=PLo1QA-RK2zyosMdELpBN0h027Ht7PA2kj&index=24
// [Online 22] - Topic 25 (Handle Popup) - https://www.youtube.com/watch?v=H4XYUfDAoa4

public class Topic11_12_01_Popup_Dialog {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    Actions action;
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;


    @BeforeClass
    public void beforeClass() {

        // Firefox
        //System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
        //driver = new FirefoxDriver();

        // Chrome123
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        action = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
        explicitWait = new WebDriverWait(driver, 30);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Fixed_Popup() {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.cssSelector("button.login_.icon-before")).click();
        driver.findElement(By.cssSelector("input#account-input")).sendKeys("test123@hotmai.com");
        driver.findElement(By.cssSelector("input#password-input")).sendKeys("test123");
        action.click(driver.findElement(By.cssSelector("button.btn-v1.btn-login-v1"))).perform();
        //jsExecutor.executeScript("return arguments[0].value;",driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")));
        //Assert.assertEquals(a, "Tài khoản không tồn tại!");

        // không get text được
    }

    @Test
    public void TC_02_Popup_In_Dom() {
        driver.get("https://blog.testproject.io/");
        WebElement popup = driver.findElement(By.cssSelector("div.mailch-wrap"));

        // Nếu popup hiển thị thì xử lý close popup
        if(popup.isDisplayed()) {
            System.out.println("Popup is displayed");
            driver.findElement(By.cssSelector("div#close-mailch")).click();
        } else { // Nếu popup không hiển thị thì xử lý tiếp
            System.out.println("Popup is not displayed");
        }

        driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
        driver.findElement(By.cssSelector("#search-2 span.glass")).click(); // không click được
        List<WebElement> articleTitle = driver.findElements(By.cssSelector("h3.post-title>a"));

        for (WebElement article: articleTitle) {
            System.out.println("Search = " + article.getSize());
            Assert.assertTrue(article.getText().contains("Selenium"));
        }
    }

    @Test
    public void TC_03_Popup_Not_In_Dom() {
        // Nếu element ko có trong DOM thì hàm findElements ko tìm thấy
        // Nó sẽ trả về 1 list empty (size=0)
        // Ko đánh fail tcs
        // Ko throw ra exception

        driver.get("https://shopee.vn/");
        sleepInSecond(20);

        List<WebElement> popup = driver.findElements(By.cssSelector("div.shopee-popup__container img"));

        if (popup.size()>0 && popup.get(0).isDisplayed()) {
            // Close popup
            System.out.println("Popup is displayed");
            driver.findElement(By.cssSelector("div.shopee-popup__container div.shopee-popup__close-btn")).click();
        } else {
            System.out.println("Popup is not displayed");
        }

        //driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("quan lot");
        //driver.findElement(By.cssSelector("button.btn-solid-primary")).click();
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
