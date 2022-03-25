package Week5Day2Assignment;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Public_Nykaa extends BaseClassPublicSite{
	@Test
	public void TC003_Nykaa() throws InterruptedException, IOException {
		driver.get("https://www.nykaa.com/");
		WebElement brand = driver.findElement(By.xpath("//a[text()='brands']"));
		WebElement LorealParis = driver.findElement(By.xpath("(//a[contains(text(),'Oreal Paris')])[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(brand).perform();
		LorealParis.click();
		String Title = driver.getTitle();
		System.out.println("The title is " + Title);
		if (Title.contains("L'Oreal Paris"))
			System.out.println("The tile of the page contains L'Oreal Paris");
		else
			System.out.println("The tile of the page doesnt contain L'Oreal Paris");

		// Click sort By
		driver.findElement(By.xpath("//span[contains(text(),'Sort By : popularity')]")).click();
		// select customer top rated
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'customer top rated')]")).click();
		// Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("//span[contains(text(),'Category')]")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		// Click->Concern->Color Protection
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		// check whether the Filter is applied with Shampoo
		String filter = driver.findElement(By.xpath("//span[@class='filter-value']")).getText();
		if (filter.equals("Shampoo"))
			System.out.println("Filter is applied with Shampoo");
		else
			System.out.println("Filter is not applied with Shampoo");
		// Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("(//div[@class='css-43m2vm']/img)[1]")).click();
		// window handler for switching to new window
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
		String newWindow = windowHandlesList.get(1);
		driver.switchTo().window(newWindow);
		// GO to the new window and select size as 175ml
		WebElement dropdown = driver.findElement(By.xpath("//select[@class ='css-2t5nwu']"));
		Select dd = new Select(dropdown);
		dd.selectByIndex(1);
		System.out.println("The selected option is " + dd.getFirstSelectedOption().getText());

		// Print the MRP of the product
		String MRP = driver.findElement(By.xpath("//span[@class='css-1jczs19']")).getText();
		System.out.println("The MRP of the Shampoo is " + MRP);
		// Click on ADD to BAG
		driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
		// Go to Shopping Bag
		driver.findElement(By.xpath("//button[@class='css-g4vs13']")).click();
		// Print the Grand Total amount
		// Switch to frames
		driver.switchTo().frame(0);
		String grandTotal = driver.findElement(By.xpath("(//div[@class='value'])[4]")).getText();
		String grandTotalreplace = grandTotal.replace("₹", "");
		int total = Integer.parseInt(grandTotalreplace);
		System.out.println("The Grand Total is " + total);

		// Click Proceed
		driver.findElement(By.xpath("//button[@class='btn full fill no-radius proceed ']")).click();
		// Click on Continue as Guest
		driver.findElement(By.xpath("//button[@class='btn full big']")).click();
		// Check if this grand total is the same in step 14
		String cartTotal = driver.findElement(By.xpath("(//div[@class='value']/span)[2]")).getText();
		int total2 = Integer.parseInt(cartTotal);
		System.out.println("The Cart Total is " + total2);
		if (total == total2)
			System.out.println("The grandTotal & cartTotal are same " + cartTotal);

		else
			System.out.println("The grandTotal & cartTotal are not same " + cartTotal);
		File screenshot = driver.getScreenshotAs(OutputType.FILE);
		File image = new File("./snap/Nykaa.jpg");
		FileUtils.copyFile(screenshot, image);
		// Close all windows
		driver.quit();

	}

}
