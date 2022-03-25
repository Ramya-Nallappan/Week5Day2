package Week5Day2Assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow_AssignIncident_ExcelData extends BaseClass {
	@BeforeTest
	public void setData()
	{
		excelFilepath = "./TestData/ServiceNow/AssignIncident.xlsx";
	}
	
    @Test(dataProvider="Dynamic_Data")
	public  void TC003_AssignIncident (String username, String password, String incident, String assigngrp) throws InterruptedException {
    	// Switch to Frame
    			driver.switchTo().frame(0);
    			driver.findElement(By.id("user_name")).sendKeys(username);
    			driver.findElement(By.id("user_password")).sendKeys(password);
    			driver.findElement(By.id("sysverb_login")).click();
    			// Search “incident “ Filter Navigator
    			driver.findElement(By.id("filter")).sendKeys(incident, Keys.ENTER);
		//Select Open
		driver.findElement(By.xpath("(//div[text()='Open'])[1]")).click();
		 //Search for the existing incident and click on  the incident
		WebElement frame = driver.findElement(By.id("gsft_main"));
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("INC000", Keys.ENTER);
		driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).click();
		//Assign the incident to  Software group
		driver.findElement(By.id("sys_display.incident.assignment_group")).clear();
		driver.findElement(By.id("sys_display.incident.assignment_group")).sendKeys(assigngrp, Keys.ENTER);
		driver.findElement(By.id("lookup.incident.assigned_to")).click();
		
		//Window Handler
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> list =new ArrayList<String>(windowHandles);
		String newWindow = list.get(1);
		driver.switchTo().window(newWindow);
		//select the Assignee
		driver.findElement(By.xpath("(//a[@class='glide_ref_item_link'])[3]")).click();
		driver.switchTo().window(list.get(0));
		driver.switchTo().frame(0);
		//Update the incident with Work Notes
		driver.findElement(By.id("activity-stream-textarea")).sendKeys("This ticket has been Assigned to Software Group");
		driver.findElement(By.xpath("(//button[text()='Update'])[2]")).click();
		Thread.sleep(1500);
		 //Verify the Assignment group and Assigned for the incident
		driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).click();
		String assigntoCategory = driver.findElement(By.id("sys_display.incident.assignment_group")).getAttribute("value");
		System.out.println("The Assigned Category is " + assigntoCategory);
		String assignto = driver.findElement(By.id("sys_display.incident.assigned_to")).getAttribute("value");
		System.out.println("The Assigned to is " + assignto);
	}

}
