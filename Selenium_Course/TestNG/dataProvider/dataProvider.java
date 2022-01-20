package TestNG.dataProvider;

import Method_In_Java.function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class dataProvider {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    By emailTextBox = By.cssSelector("#user_email_login");
    By passwordTextBox = By.cssSelector("#user_password");
    By submitButton = By.cssSelector("#user_submit");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "DataProvider")
    public void TC_01_LoginToSystem(String username, String password) {
        driver.get("https://www.browserstack.com/users/sign_in");
        driver.findElement(emailTextBox).sendKeys(username);
        driver.findElement(passwordTextBox).sendKeys(password);
        driver.findElement(submitButton).click();
        System.out.println("hoan thanh1");
    }

    @Test(dataProvider = "DataProvider") // Khi dataprovider nằm cùng class
    public void TC_02_LoginToSystem(String username, String password) {
        driver.get("https://www.browserstack.com/users/sign_in");
        driver.findElement(emailTextBox).sendKeys(username);
        driver.findElement(passwordTextBox).sendKeys(password);
        driver.findElement(submitButton).click();
        System.out.println("hoan thanh2");
    }

    @Test(dataProvider = "DataProvider", dataProviderClass = function.class) // Khi dataprovider nằm ngoài class
    public void TC_03_LoginToSystem(String username, String password) {
        driver.get("https://www.browserstack.com/users/sign_in");
        driver.findElement(emailTextBox).sendKeys(username);
        driver.findElement(passwordTextBox).sendKeys(password);
        driver.findElement(submitButton).click();
        System.out.println("hoan thanh2");
    }

    @DataProvider
    public Object[][] DataProvider(Method method) {
        Object[][] data = null;
        if (method.getName().equals("TC_01_LoginToSystem")) {
            data = new Object[][]{
                    {"abc@gmail.com", "abc1234"},
                    {"abc@gmail.com", "abc1234"}
            };
        } else if (method.getName().equals("TC_02_LoginToSystem")) {
            data = new Object[][]{
                    {"c1@gmail.com", "11111"},
                    {"c1@gmail.com", "11111"}
            };
        }
        return data;
    }

    @DataProvider(name = "login")
    public Object[][] loginData() {
        return new Object[][]{
                {"c1@gmail.com", "11111"},
                {"c1@gmail.com", "11111"}
        };
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }
}
