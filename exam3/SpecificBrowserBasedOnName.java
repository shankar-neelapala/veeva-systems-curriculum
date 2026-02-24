package tests;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SpecificBrowserBasedOnName {

	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);		
		System.out.println("Enter browser name: ");
		String browser = sc.nextLine().toLowerCase();
		
		WebDriver driver = null;
		
		switch (browser) {
			case "chrome" : driver = new ChromeDriver();break;
			case "edge" : driver = new EdgeDriver();break;
			case "firefox" : driver = new FirefoxDriver();break;
			default : System.out.println("Invalid browser name");break;
		}
		
		driver.get("https://www.flipkart.com/");
		System.out.println(driver.getTitle());
		sc.close();
		driver.quit();
	}
}
