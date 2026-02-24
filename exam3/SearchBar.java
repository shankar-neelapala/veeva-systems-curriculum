package tests;

import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class SearchBar {
	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the product ");
		String product = sc.nextLine().toLowerCase();
		
		WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
		
		WebElement txtSearchBar = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		txtSearchBar.sendKeys(product);
		txtSearchBar.sendKeys(Keys.ENTER);
		
		WebElement confirm = driver.findElement(By.xpath("//div[@class='col-sm-12']//h1"));
		boolean status = confirm.getText().toLowerCase().contains(product);
		if(status) {
			System.out.println("Product searched successfully");
		}
		else {
			System.out.println("Failed to search");
		}
		sc.close();
		driver.quit();
	}
}
