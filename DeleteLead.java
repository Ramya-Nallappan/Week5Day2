package Week5Day2Assignment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteLead extends BaseClass{
	
	@BeforeTest
	public void setData()
	{
		excelFilepath = "./TestData/Leaftaps/DeleteLead.xlsx";
	}
	
    @Test(dataProvider="Dynamic_Data")
	public void TC002_DeleteLead(String userName, String password,String searchKey) throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.linkText("Find Leads")).click();
		driver.findElement(By.xpath("(//span[@class = 'x-tab-strip-text '])[2]")).click();
		driver.findElement(By.xpath("(//input[@name = 'phoneNumber'])")).sendKeys(searchKey);
		Thread.sleep(3000);
		
		//Click find leads button
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		
		//Capture lead ID of First Resulting lead
		String text = driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])/a[1]")).getText();
		System.out.println("lead ID of First Resulting lead is " + text);
		Thread.sleep(3000);
		
		//Click First Resulting lead
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])/a[1]")).click();
		
		
		//Click Delete 
		driver.findElement(By.xpath("(//a[@class='subMenuButtonDangerous'])")).click();

				
	
	}

}
