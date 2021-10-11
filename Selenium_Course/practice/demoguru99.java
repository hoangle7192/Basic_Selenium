package practice;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class demoguru99 {

    // [Online 21] - Topic 20 (User Interaction - Part I) - https://www.youtube.com/watch?v=VkqkLUKXdrk&list=PLo1QA-RK2zyosMdELpBN0h027Ht7PA2kj&index=22
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    WebDriverWait explicitwait;
    Alert alert;
    JavascriptExecutor executor;
    Select select;


    // Admin page
    By loginAdminButtonBy = By.cssSelector("input[type='submit']");
    By messagePopupBy = By.cssSelector("#message-popup-window");
    By closePopUpBy = By.cssSelector("a[title='close']");
    String userNameAdmin, passwordAdmin;
    String AssociateToWebsite, Group, Prefix, FirstName, MiddleName, LastName, Suffix, Email, DateOfBirth, Tax, Gender, Password;

    @BeforeClass
    public void beforeClass() {
        //Firefox
        //System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
        //driver = new FirefoxDriver();

        //Chrome123
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();

        executor = (JavascriptExecutor) driver;
        explicitwait = new WebDriverWait(driver, 15);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        // Admin Page
        userNameAdmin = "user01";
        passwordAdmin = "guru99com";
        AssociateToWebsite = "Main Website";
        Group = "Wholesale";
        Prefix = "Prefix";
        FirstName = "jessy";
        MiddleName = "linh";
        LastName = "gardinho";
        Suffix = "Suffix";
        Email = "lingardinho" + randomNumber() +"@gmail.com";
        DateOfBirth = "11/20/2000";
        Tax = "1234567";
        Gender = "Male";
        Password = "1234567";

    }

    @Test
    public void TC_01_Create_CustomerAccount() {
        driver.get("http://live.demoguru99.com/index.php/backendlogin/"); // login admin page

        // input User Name and Password
        driver.findElement(By.cssSelector("#username")).sendKeys(userNameAdmin);
        driver.findElement(By.cssSelector("input[name$='login[password]']")).sendKeys(passwordAdmin);
        driver.findElement(loginAdminButtonBy).click();

        // wait until popup is display
        explicitwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(messagePopupBy));
        // click close message popup
        executor.executeScript("arguments[0].click();", driver.findElement(closePopUpBy));

        // Add New Customer
        driver.findElement(By.xpath("//div[@class='middle']//button[@title='Add New Customer']")).click();
        // Input data
        select = new Select(driver.findElement(By.cssSelector("#_accountwebsite_id")));
        select.selectByVisibleText(AssociateToWebsite);

        select = new Select(driver.findElement(By.cssSelector("#_accountgroup_id")));
        select.selectByVisibleText(Group);

        driver.findElement(By.cssSelector("#_accountprefix")).sendKeys(Prefix);
        driver.findElement(By.cssSelector("#_accountfirstname")).sendKeys(FirstName);
        driver.findElement(By.cssSelector("#_accountmiddlename")).sendKeys(MiddleName);
        driver.findElement(By.cssSelector("#_accountlastname")).sendKeys(LastName);
        driver.findElement(By.cssSelector("#_accountsuffix")).sendKeys(Suffix);

        driver.findElement(By.cssSelector("#_accountemail")).sendKeys(Email);

        driver.findElement(By.cssSelector("#_accountdob")).sendKeys(DateOfBirth);
        driver.findElement(By.cssSelector("#_accounttaxvat")).sendKeys(Tax);

        select = new Select(driver.findElement(By.cssSelector("#_accountgender")));
        select.selectByVisibleText(Gender);

        driver.findElement(By.cssSelector("#_accountsendemail")).click();
        driver.findElement(By.cssSelector("#_accountpassword")).sendKeys(Password);

        // Save Customer
        driver.findElement(By.xpath("//div[@class='columns ']//button[@title='Save Customer']")).click();

        // wait until popup is display
        explicitwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='loading-mask' and @style='display:none']/p")));

        // Click "Edit" button
        driver.findElement(By.xpath("//td[contains(.,'" + Email + "')]/following-sibling::td[contains(@class,'last')]/a")).click();


        // click "Account Information"
        driver.findElement(By.xpath("//span[text()='Account Information']")).click();

        // Verify
        Assert.assertEquals(driver.findElement(By.xpath("//option[@selected='selected' and text()='Main Website']")).getText(), AssociateToWebsite);
        Assert.assertEquals(driver.findElement(By.xpath("//option[@selected='selected' and text()='Wholesale']")).getText(), Group);
        Assert.assertEquals(driver.findElement(By.cssSelector("#_accountemail")).getAttribute("value"), Email);
        Assert.assertEquals(driver.findElement(By.cssSelector("#_accountdob")).getAttribute("value"), DateOfBirth);
    }

    public int randomNumber() {
        java.util.Random rand = new Random();
        return rand.nextInt(99999);
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

}




