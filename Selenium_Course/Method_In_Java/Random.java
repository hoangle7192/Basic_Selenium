package Method_In_Java;

import org.openqa.selenium.WebDriver;

public class Random {
	WebDriver driver;

	// Random số
	public int randomNumber() {
		java.util.Random rand = new java.util.Random();
		return rand.nextInt(100000);
	}


	// Random Email
	public String genarationEmail() {
		java.util.Random rand = new java.util.Random();
		return rand.nextInt(9999) + "@gmail.com";
	}
}
