package Week5Day2Assignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditLead extends BaseClass {
	
	
	@BeforeTest
	public void setData()
	{
		excelFilepath = "./TestData/Leaftaps/EditLead.xlsx";
	}
	
     @Test(dataProvider="Dynamic_Data")
	public  void TC004_EditLead(String username,String password,String searchKey) throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.linkText("Find Leads")).click();
		driver.findElement(By.xpath("(//label[@class= 'x-form-item-label'])[19]/following::input")).sendKeys(searchKey);
		//driver.findElement(By.xpath("(//div[@class= 'x-form-element'])[20]//input")).sendKeys("N");
		driver.findElement(By.xpath("(//button[@type= 'button'])[7]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a [@class= 'linktext'])[4]")).click();
		System.out.println("The title of the page is :" +driver.getTitle());
		driver.findElement(By.linkText("Edit")).click();
		driver.findElement(By.id("updateLeadForm_companyName")).clear();
		WebElement elementCompanyName=driver.findElement(By.id("updateLeadForm_companyName"));
		elementCompanyName.sendKeys("Google");
		String updatedCompanyName =elementCompanyName.getAttribute("value");
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@type= 'submit'])[1]")).click();
		String storedCompanyName = driver.findElement(By.xpath("(//span[@class = 'tabletext'])[3]")).getText();
		
		if (updatedCompanyName.contains("Google"))
			System.out.println("Both are same");
		else
			System.out.println("Both are not same");
		driver.close();
		
	}

}
