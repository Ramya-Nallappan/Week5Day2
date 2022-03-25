package Week5Day2Assignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow_DeleteIncident_ExcelData extends BaseClass{
	
	@BeforeTest
		public void setData()
	{
		excelFilepath = "./TestData/ServiceNow/DeleteIncident.xlsx";
	}
		
        @Test(dataProvider="Dynamic_Data")
        public void TC004_DeleteIncident(String username, String password, String incident,String inc_no) throws InterruptedException {
        	// Switch to Frame
			driver.switchTo().frame(0);
			driver.findElement(By.id("user_name")).sendKeys(username);
			driver.findElement(By.id("user_password")).sendKeys(password);
			driver.findElement(By.id("sysverb_login")).click();
			// Search “incident “ Filter Navigator
			driver.findElement(By.id("filter")).sendKeys(incident, Keys.ENTER);
        	
        	driver.findElement(By.xpath("(//div[text()='Incidents'])[2]")).click();
		
		//Search for the existing incident and click on the incident
		WebElement frame = driver.findElement(By.id("gsft_main"));
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(inc_no, Keys.ENTER);
		String incidentNo = driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).getText();
		System.out.println("The incident number that gng to be deleted is" +incidentNo);
		driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).click();
		
		//Delete the incident
		driver.findElement(By.xpath("(//button[text()='Delete'])[1]")).click();
		driver.findElement(By.xpath("(//button[text()='Delete'])[3]")).click();
		Thread.sleep(1500);
		//Verify the deleted incident
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(incidentNo, Keys.ENTER);
		//driver.findElement(By.id("incident_table_header_search_control")).sendKeys(incidentNo,Keys.ENTER);
		
		String text = driver.findElement(By.xpath("//tr[1]/td[text()='No records to display']")).getText();
		 System.out.println(text);
		if (text.equals("No records to display"))
			System.out.println("Incident Deleted Successfully");
		else
			System.out.println("Incident not Deleted");
	}

}
