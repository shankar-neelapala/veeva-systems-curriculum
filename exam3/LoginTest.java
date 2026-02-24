package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class LoginTest {

	public static void main(String agrs[]) {
		WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		WebElement txtUsername = driver.findElement(By.xpath("//input[@placeholder='Username']"));
		txtUsername.sendKeys("Admin");
		
		WebElement txtPassword = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		txtPassword.sendKeys("admin123");
		
		WebElement btnLogin = driver.findElement(By.xpath("//button[@type='submit']"));
		btnLogin.click();
		
		boolean status = driver.getCurrentUrl().contains("dashboard");
		if(status) {
			System.out.println("Login Successful...");
		}
		else {
			System.out.println("Login failed!!!");
		}
		
		driver.quit();
	}
}
