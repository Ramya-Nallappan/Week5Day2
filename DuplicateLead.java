package Week5Day2Assignment;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DuplicateLead extends BaseClass {
	@BeforeTest
	public void setData()
	{
		excelFilepath = "./TestData/Leaftaps/DuplicateLead.xlsx";
	}
	
	@Test(dataProvider = "Dynamic_Data")
	public void TC003_DuplicateLead(String username,String password,String searchKey) throws InterruptedException {
		driver.findElement(By.xpath("//input [@id ='username']")).sendKeys(username);
		System.out.println("To find element for Password button:");
		driver.findElement(By.xpath("//input [@id ='password']")).sendKeys(password);
		driver.findElement(By.xpath("// input [@class = 'decorativeSubmit']")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.linkText("Find Leads")).click();
		//Select Email Tab
		driver.findElement(By.xpath("(//span[@class='x-tab-strip-text '])[3]")).click();
		//Enter EmailID
		driver.findElement(By.xpath("(//div[@class='x-form-element'])[22]/input")).sendKeys(searchKey);
		Thread.sleep(3000);
		//Select Find Lead link
		driver.findElement(By.xpath("(//button[@class='x-btn-text'])[7]")).click();
		//Capture the name of the first resulting Lead
		String name = driver.findElement(By.xpath("(//a[@class='linktext'])[6]")).getText();
		System.out.println("The name of the persion is " +name);
		Thread.sleep(2000);
		//Select the LeadID of the first resulting Lead
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]/a")).click();
		//Select the Duplicate Lead button
		driver.findElement(By.linkText("Duplicate Lead")).click();
		//Capture the Page title
		String title = driver.getTitle();
		//Verification of the title
		if (title.contains("Duplicate Lead"))
		System.out.println("The title is "+ title);
		else
			System.out.println("The title is not correct");
			
		
	}

}
