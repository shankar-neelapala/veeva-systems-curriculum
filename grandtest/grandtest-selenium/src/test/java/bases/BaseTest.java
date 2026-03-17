package bases;

import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	public WebDriver driver;
	public Properties prop;
	
	@BeforeMethod
	public void setUp() {
		prop = new Properties();
		try{
			FileReader file = new FileReader(".//src//test//resources//config.properties");
			prop.load(file);
			file.close();
		}
		catch(Exception e) {
			
		}
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("base.url"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
