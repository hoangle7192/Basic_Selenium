package SeleniumWebDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic04_05_Xpath_and_CSS_Register {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	// Tao bien verify msg loi
	By msgErrorFirstName = By.id("txtFirstname-error");
	By msgErrorEmail = By.id("txtEmail-error");
	By msgErrorCEmail = By.id("txtCEmail-error");
	By msgErrorPassword = By.id("txtPassword-error");
	By msgErrorCPassword = By.id("txtCPassword-error");
	By msgErrorPhone = By.id("txtPhone-error");
	
	// Tao bien vi tri textbox
	By firstNameTextbox = By.id("txtFirstname");
	By emailTextbox = By.id("txtEmail");
	By cemailTextbox = By.id("txtCEmail");
	By passwordTextbox = By.id("txtPassword");
	By cpasswordTextbox = By.id("txtCPassword");
 	By phoneTextbox = By.id("txtPhone");
	By submitButton = By.xpath("//form[@id='frmLogin']//button"); 
	
 	// Bien data input
 	String firtname, email, cemail, password, cpassword, phone;

	@BeforeClass
	public void beforeClass() {
		// Firefox
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		// Chrome123
		// System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		// driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		// Dinh nghia data input
		firtname = "Nguyen Van C";
		email = "nguyenvanc@gmail.com";
		cemail = "nguyenvanc@gmail.com";
		password = "toilatoi123";
		cpassword = "toilatoi123";
		phone = "0905123456";				
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}
	
    @Test
    public void TC_01_RegisterWithEmptyData() {
    	// Click vao nut "Dang Ky"
    	driver.findElement(submitButton).click();
    	// Kiem tra Msg error hien thi
    	Assert.assertEquals(driver.findElement(msgErrorFirstName).getText(), "Vui l??ng nh???p h??? t??n");
    	Assert.assertEquals(driver.findElement(msgErrorEmail).getText(), "Vui l??ng nh???p email");
    	Assert.assertEquals(driver.findElement(msgErrorCEmail).getText(), "Vui l??ng nh???p l???i ?????a ch??? email");
    	Assert.assertEquals(driver.findElement(msgErrorCPassword).getText(), "Vui l??ng nh???p l???i m???t kh???u");
    	Assert.assertEquals(driver.findElement(msgErrorPassword).getText(), "Vui l??ng nh???p m???t kh???u");
    	Assert.assertEquals(driver.findElement(msgErrorPhone).getText(), "Vui l??ng nh???p s??? ??i???n tho???i.");          
    }
    
    @Test
    public void TC_02_RegisterWithInvalidEmail() {
    	// input data
    	driver.findElement(firstNameTextbox).sendKeys(firtname);
    	driver.findElement(emailTextbox).sendKeys("123@123@123");
    	driver.findElement(cemailTextbox).sendKeys("123@123@123");
    	driver.findElement(passwordTextbox).sendKeys(password);
    	driver.findElement(cpasswordTextbox).sendKeys(cpassword);
    	driver.findElement(phoneTextbox).sendKeys(phone);
    	// Click nut "Dang ky"
    	driver.findElement(submitButton).click();
    	// Kiem tra msg error
    	Assert.assertEquals(driver.findElement(msgErrorEmail).getText(), "Vui l??ng nh???p email h???p l???");
    	Assert.assertEquals(driver.findElement(msgErrorCEmail).getText(), "Email nh???p l???i kh??ng ????ng");
    }
    
    @Test
    public void TC_03_RegisterWithIncorrectConfirmEmail() {
    	// input data
    	driver.findElement(firstNameTextbox).sendKeys(firtname);
    	driver.findElement(emailTextbox).sendKeys(email);
    	driver.findElement(cemailTextbox).sendKeys("123@123");
    	driver.findElement(passwordTextbox).sendKeys(password);
    	driver.findElement(cpasswordTextbox).sendKeys(cpassword);
    	driver.findElement(phoneTextbox).sendKeys(phone);
    	// Click nut "Dang ky"
    	driver.findElement(submitButton).click();
    	// Kiem tra msg error
    	Assert.assertEquals(driver.findElement(msgErrorCEmail).getText(), "Email nh???p l???i kh??ng ????ng");
    }
    
    @Test
    public void TC_04_RegisterWithPassword_Less_Than_6Characters() {
    	// input data
    	driver.findElement(firstNameTextbox).sendKeys(firtname);
    	driver.findElement(emailTextbox).sendKeys(email);
    	driver.findElement(cemailTextbox).sendKeys(cemail);
    	driver.findElement(passwordTextbox).sendKeys("123");
    	driver.findElement(cpasswordTextbox).sendKeys("123");
    	driver.findElement(phoneTextbox).sendKeys(phone);
    	// Click nut "Dang ky"
    	driver.findElement(submitButton).click();
    	// Kiem tra msg error
    	Assert.assertEquals(driver.findElement(msgErrorPassword).getText(), "M???t kh???u ph???i c?? ??t nh???t 6 k?? t???");
    	Assert.assertEquals(driver.findElement(msgErrorCPassword).getText(), "M???t kh???u ph???i c?? ??t nh???t 6 k?? t???");
    }
    
    @Test
    public void TC_05_RegisterWithIncorrectConfirmPassword() {
    	// input data
    	driver.findElement(firstNameTextbox).sendKeys(firtname);
    	driver.findElement(emailTextbox).sendKeys(email);
    	driver.findElement(cemailTextbox).sendKeys(cemail);
    	driver.findElement(passwordTextbox).sendKeys(password);
    	driver.findElement(cpasswordTextbox).sendKeys("12435345453");
    	driver.findElement(phoneTextbox).sendKeys(phone);
    	// Click nut "Dang ky"
    	driver.findElement(submitButton).click();
    	// Kiem tra msg error
    	Assert.assertEquals(driver.findElement(msgErrorCPassword).getText(), "M???t kh???u b???n nh???p kh??ng kh???p");
    }
    
    @Test
    public void TC_06_RegisterWithInvalidPhoneNumber() {
    	// input data
    	driver.findElement(firstNameTextbox).sendKeys(firtname);
    	driver.findElement(emailTextbox).sendKeys(email);
    	driver.findElement(cemailTextbox).sendKeys(cemail);
    	driver.findElement(passwordTextbox).sendKeys(password);
    	driver.findElement(cpasswordTextbox).sendKeys(cpassword);
    	driver.findElement(phoneTextbox).sendKeys(".");
    	// Click nut "Dang ky"
    	driver.findElement(submitButton).click();
    	// Kiem tra msg error
    	Assert.assertEquals(driver.findElement(msgErrorPhone).getText(), "Vui l??ng nh???p con s???");
    	
    	// input data phoneTextbox
    	driver.findElement(phoneTextbox).clear();
    	driver.findElement(phoneTextbox).sendKeys("0905");
    	// Click nut "Dang ky"
    	driver.findElement(submitButton).click();
    	// Kiem tra msg error
    	Assert.assertEquals(driver.findElement(msgErrorPhone).getText(), "S??? ??i???n tho???i ph???i t??? 10-11 s???.");
    	
    	// input data phoneTextbox
    	driver.findElement(phoneTextbox).clear();
    	driver.findElement(phoneTextbox).sendKeys("123456");
    	// Click nut "Dang ky"
    	driver.findElement(submitButton).click();
    	// Kiem tra msg error
    	Assert.assertEquals(driver.findElement(msgErrorPhone).getText(), "S??? ??i???n tho???i b???t ?????u b???ng: 09 - 03 - 012 - 016 - 018 - 019");
    }
    

    @AfterClass
    public void afterClass() {
            //driver.quit();
    }
}
