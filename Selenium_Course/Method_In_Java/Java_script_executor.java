package Method_In_Java;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


// Tài liệu: https://docs.google.com/document/d/1iXBeWi-58bF1rVcJ5s9S9MBnx4q6cNgX0ByUXXUpiWo/edit#

public class Java_script_executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		// Add kieu tuong minh jsExecutor (Reference casting)
		jsExecutor = (JavascriptExecutor) driver;

	}
	
	@Test
	public void TC01() {
		// delete attribute of element
		jsExecutor.executeScript("arguments[0].removeAttribute('type')", driver.findElement(By.xpath("xpath")));
		
		// click
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("a")));
		
		// scroll xuống element
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("a")));

		// scroll xuống cuối page
		jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight || document.documentElement.scrollHeight)");

		// Scroll lên trên cùng của page
		jsExecutor.executeScript("window.scrollTo(document.body.scrollHeight,0)");
		
	}

	// Viết theo kiểu hàm

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

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
