package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import bases.BaseTest;

public class HandlingFrames extends BaseTest{

	@Test
	public void handleFramesByIndex() {
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//input[@name='mytext1']")).sendKeys("Handiling frame using index");
	}
	
	@Test
	public void handleFramesByName() {
		driver.switchTo().defaultContent();
		WebElement frame = driver.findElement(By.xpath("//frameset//frameset//frame[1]"));
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("//input[@name='mytext2']")).sendKeys("Handling frame using name");
	}
}
