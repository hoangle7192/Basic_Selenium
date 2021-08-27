package Method_In_Java;

import org.openqa.selenium.WebDriver;

public class Random {
	WebDriver driver;
	
	// Ham random
	public int randomNumber() {
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(100000);
	}

	private int nextInt(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

}
