package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.edge.EdgeDriver;

public class OpenGoogle {

	public static void main(String[] args) {
		
		WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		
		String title = driver.getTitle();
		System.out.println("Title of the page: "+title);

		driver.switchTo().newWindow(WindowType.TAB);
		driver.navigate().to("https://www.google.co.in");
		driver.quit();
	}

}
