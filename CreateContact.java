//Assignment 2.1:Create Contact
package Week5Day2Assignment;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateContact extends BaseClass{
	
	@BeforeTest
	public void setData() {
		excelFilepath = "./TestData/Leaftaps/CreateLead.xlsx";
		
	}
	
	@Test(dataProvider= "Dynamic_Data")
	 public void TC001_CreateContact(String username, String password,String firstName,String lastName,String fnLocal,String lnLocal,String departName ,String Decs,String email) {
		// 2. Enter UserName and Password Using Id Locator
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		// 3. Click on Login Button using Class Locator
		driver.findElement(By.className("decorativeSubmit")).click();
		// 4. Click on CRM/SFA Link
		driver.findElement(By.linkText("CRM/SFA")).click();
		// 5. Click on contacts Button
		driver.findElement(By.linkText("Contacts")).click();
		// 6. Click on Create Contact
		driver.findElement(By.linkText("Create Contact")).click();
		// 7. Enter FirstName Field Using id Locator
		driver.findElement(By.id("firstNameField")).sendKeys(firstName);
		// 8. Enter LastName Field Using id Locator
		driver.findElement(By.id("lastNameField")).sendKeys(lastName);
		// 9. Enter FirstName(Local) Field Using id Locator
		driver.findElement(By.id("createContactForm_firstNameLocal")).sendKeys(fnLocal);
		// 10. Enter LastName(Local) Field Using id Locator
		driver.findElement(By.id("createContactForm_lastNameLocal")).sendKeys(lnLocal);
		// 11. Enter Department Field Using any Locator of Your Choice
		driver.findElement(By.id("createContactForm_departmentName")).sendKeys(departName);
		// .12 Enter Description Field Using any Locator of your choice
		driver.findElement(By.id("createContactForm_description")).sendKeys(Decs);
		// 13. Enter your email in the E-mail address Field using the locator of your
		// choice
		driver.findElement(By.id("createContactForm_primaryEmail")).sendKeys(email);
		// 14. Select State/Province as NewYork Using Visible Text
		WebElement elementState = driver.findElement(By.id("createContactForm_generalStateProvinceGeoId"));
		Select dd = new Select(elementState);
		dd.selectByVisibleText("New York");
		// 15. Click on Create Contact
		driver.findElement(By.xpath("//input[@type = 'submit']")).click();
		// 16. Click on edit button
		driver.findElement(By.linkText("Edit")).click();
		// 17. Clear the Description Field using .clear
		driver.findElement(By.id("updateContactForm_description")).clear();
		// 18. Fill ImportantNote Field with Any text
		driver.findElement(By.id("updateContactForm_importantNote")).sendKeys("Important Note");
		// 19. Click on update button using Xpath locator
		driver.findElement(By.xpath("//input[@value= 'Update']")).click();
		// 20. Get the Title of Resulting Page.
		System.out.println("The title of the page is :" + driver.getTitle());
	}

}
