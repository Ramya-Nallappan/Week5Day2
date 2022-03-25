package Week5Day2Assignment;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClassPublicSite {
	public ChromeDriver driver;
	public String excelFilepath;
	@BeforeMethod
	public void preCondition() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
	}
	
	@AfterMethod
	public void postCondition()
	{
	driver.quit();
	}
	
	@DataProvider(name= "Dynamic_Data")
			public String[][] TestData() throws IOException
			{
		     String[][] data = ExcelSheet.excelSheet(excelFilepath);
		return data;
			}
	
			}
