package SeleniumWebDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

// [Online 21] - Topic 21 (User Interaction - Part II) - https://www.youtube.com/watch?v=MWWGFd2mC5A&list=PLo1QA-RK2zyosMdELpBN0h027Ht7PA2kj&index=23

public class Topic10_02_Double_click {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    Actions action;
    JavascriptExecutor JsExecutor;
    String jsHelper = projectPath + "\\dragAndDrop\\drag_and_drop_helper.js";


    @BeforeClass
    public void beforeClass() {

        // Firefox
        //System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
        //driver = new FirefoxDriver();

        // Chrome123
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();

        action = new Actions(driver);

        JsExecutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_06_Double_click() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //  Trường hợp là Firefox thì cần phải scroll xuống mới click được
        JsExecutor.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()' and text()='Double click me']")));
        // Nhan double
        action.doubleClick(driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()' and text()='Double click me']"))).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//p[@id='demo' and text()='Hello Automation Guys!']")).isDisplayed());
    }

    @Test
    public void TC_07_Right_click() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        // Right Click
        action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();

        // Hover mouse to
        action.moveToElement(driver.findElement(By.cssSelector(".context-menu-icon-quit"))).perform();

        // verify
        Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-hover.context-menu-visible")).isDisplayed());

        // Click
        action.click(driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-hover.context-menu-visible"))).perform();

        // Alert
        Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
        driver.switchTo().alert().accept();
    }

    @Test
    public void TC_08_DragAndDrop_HTML4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement smallCircle = driver.findElement(By.cssSelector("#draggable"));
        WebElement bigCircle = driver.findElement(By.cssSelector("#droptarget"));

        // Drap-Drop
        action.dragAndDrop(smallCircle, bigCircle).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='k-header painted' and text()='You did great!']")).isDisplayed());

        // Verify Background
        Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex(), "#03a9f4");

    }

    @Test
    public void TC_08_DragAndDrop_HTML5_JS_JQuery_Css_Selector() throws IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        jsHelper = readFile(jsHelper) + "$('#column-a').simulateDragDrop({ dropTarget: '#column-b'});";  // chỉ dùng với CSS nguồn tới CSS đích

        // A -> B
        JsExecutor.executeScript(jsHelper);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='A']")).isDisplayed());
        sleepInSecond(3);

        // B-> A
        JsExecutor.executeScript(jsHelper);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());
        sleepInSecond(3);
    }

    @Test
    public void TC_08_DragAndDrop_HTML5_Robot_XPath_CSS() throws AWTException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        // A -> B
        drag_the_and_drop_html5_by_xpath("//div[@id='column-a']", "//div[@id='column-b']");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='A']")).isDisplayed());
        sleepInSecond(3);

        // B -> A
        drag_the_and_drop_html5_by_xpath("//div[@id='column-a']", "//div[@id='column-b']");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());
        sleepInSecond(3);
    }

    public String readFile(String file) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(file);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }

    public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

        WebElement source = driver.findElement(By.xpath(sourceLocator));
        WebElement target = driver.findElement(By.xpath(targetLocator));

        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Get size of elements
        Dimension sourceSize = source.getSize();
        Dimension targetSize = target.getSize();

        // Get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();
        System.out.println(sourceLocation.toString());
        System.out.println(targetLocation.toString());

        // Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        System.out.println(sourceLocation.toString());
        System.out.println(targetLocation.toString());

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public void sleepInSecond(long timeoutInSecond) {
        try {
            Thread.sleep(timeoutInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

}
