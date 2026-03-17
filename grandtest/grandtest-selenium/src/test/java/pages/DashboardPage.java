package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
	private WebDriver driver;
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By accountMenu = By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']");
	private By logoutButton = By.xpath("//a[normalize-space()='Logout']");
	
	public void clickLogout() {
		driver.findElement(accountMenu).click();
		driver.findElement(logoutButton).click();
	}
	
	public boolean isLoginSuccessful() {
		String curUrl = driver.getCurrentUrl();
		return curUrl.contains("dashboard");
	}
}
