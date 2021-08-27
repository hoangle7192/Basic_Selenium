package Method_In_Java;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class sleep {
	WebDriver driver;
	
	@Test
	public void TC_03_Register() {
		sleepInSecond(3); // gọi lệnh sleep ra cho hàm này
	}

	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}

			



