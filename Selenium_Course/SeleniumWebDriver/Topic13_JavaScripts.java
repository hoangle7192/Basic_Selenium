package SeleniumWebDriver;

import Method_In_Java.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

// [Online 21] - Topic 25 (JavascriptExecutor) - https://www.youtube.com/watch?v=TKO0LisV-7w&list=PLo1QA-RK2zyosMdELpBN0h027Ht7PA2kj&index=27
//


public class Topic13_JavaScripts {
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
    public void TC_01() {
        // get URL
        navigateToUrlByJS("http://live.demoguru99.com/");

        // lấy ra Domain
        String liveGuruDomain = (String) jsExecutor.executeScript("return document.domain");
        Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");

        // Lấy ra URL
        String liveGuruURL = (String) jsExecutor.executeScript(" return document.URL");
        Assert.assertEquals(liveGuruURL, "http://live.demoguru99.com/");

        // highlight + click
        hightlightElement("//a[text()='Mobile']");
        sleepInSecond(5);
        clickToElementByJS("//a[text()='Mobile']");

        hightlightElement("//a[@title='Samsung Galaxy']/following-sibling::div//button");
        clickToElementByJS("//a[@title='Samsung Galaxy']/following-sibling::div//button"); // add cart

        // lấy text và verify
        Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

        // mở trang Customer Service
        clickToElementByJS("//a[text()='Customer Service']");
        String customerServiceTitle = (String) jsExecutor.executeScript("return document.title");
        Assert.assertEquals(customerServiceTitle, "Customer Service");

        // Scroll đến New letter
        scrollToElement("//input[@id='newsletter']");

        // Input vào New letter
        sendkeyToElementByJS("//input[@id='newsletter']", genarationEmail());

        // navigate đến trang khác
        navigateToUrlByJS("http://demo.guru99.com/v4/");
        String bankGuruDomain = (String) jsExecutor.executeScript("return document.domain");
        Assert.assertEquals(bankGuruDomain, "demo.guru99.com");
    }

    @Test
    public void TC_02_HTML5_message() {
        driver.get("https://automationfc.github.io/html5/index.html");

        driver.findElement(By.id("fname")).sendKeys("name");

    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

    // Random Email
    public String genarationEmail() {
        java.util.Random rand = new java.util.Random();
        return rand.nextInt(9999) + "@gmail.com";
    }

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {  // lấy ra toàn bộ text trong 1 trang Web
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) { // Khởi động URL lên
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    public void hightlightElement(String locator) { // mắc màu khi demo cho KH
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
    }

    public void scrollToElement(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {  // xóa thuộc tính của element
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) { // get HTML5 message
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {  // Check hình ảnh có đúng hay không
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(locator));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

}
