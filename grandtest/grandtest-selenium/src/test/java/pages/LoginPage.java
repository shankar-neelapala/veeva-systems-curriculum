package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By usernameText = By.xpath("//input[@placeholder='Username']");
	private By passwordTest = By.xpath("//input[@placeholder='Password']");
	private By loginButton = By.xpath("//button[@type='submit']");
	
	public void enterUsernameAndPassword(String username, String password) {
		driver.findElement(usernameText).sendKeys(username);
		driver.findElement(passwordTest).sendKeys(password);
	}
	
	public void clickLogin() {
		driver.findElement(loginButton).click();
	}
}
