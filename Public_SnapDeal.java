package Week5Day2Assignment;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Public_SnapDeal extends BaseClassPublicSite {
	@Test
	public void TC004_Snapdeal() throws InterruptedException, IOException {
		driver.get("https://www.snapdeal.com/");
		driver.findElement(By.xpath("(//span[contains(text(),'Fashion')])[1]")).click();
		// Go to Sports Shoes
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//span[contains(text(),'Sports Shoes')])[1]")).click();
		// Get the count of the sports shoes
		String count = driver.findElement(By.xpath("//span[@class='category-name category-count']")).getText();
		System.out.println("The count of the sports shoes " + count);
		// Click Training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		// Sort by Low to High
		driver.findElement(By.xpath("//span[text()='Sort by:']")).click();
		driver.findElement(By.xpath("//li[@data-sorttype='plth']")).click();
		Thread.sleep(2000);
		// Check if the items displayed are sorted correctly
		List<WebElement> price = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		List<Integer> price_original = new ArrayList<Integer>();
		for (int i = 0; i < price.size(); i++) {

			String shoePrice = price.get(i).getText();
			String sortvalue = shoePrice.replaceAll("Rs. ", "");
			String sortvalue2 = sortvalue.replace(",", "");
			int number = Integer.parseInt(sortvalue2);
			price_original.add(number);// unsorted list

		}

		List<Integer> price_sorted = new ArrayList<Integer>();
		price_sorted.addAll(price_original);
		Collections.sort(price_sorted);
		System.out.println(" The original list is " + price_original);
		System.out.println(" The sorted list is " + price_sorted);
		if (price_original.equals(price_sorted))
			System.out.println("Price is in sorting order ");
		else
			System.out.println("Price is not in sorting order ");

		// Select the price range (900-1200)

		driver.findElement(By.name("fromVal")).clear();
		driver.findElement(By.name("fromVal")).sendKeys("500");
		driver.findElement(By.name("toVal")).clear();
		driver.findElement(By.name("toVal")).sendKeys("1200");
		driver.findElement(By.xpath("//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")).click();
		Thread.sleep(1500);
		// Filter with color Navy
		driver.findElement(By.xpath("//label[@for='Color_s-Navy']")).click();

		// verify the all applied filters

		// Mouse Hover on first resulting Training shoes
		WebElement element = driver.findElement(By.xpath("(//picture[@class='picture-elem'])[1]/img"));
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
		Thread.sleep(2000);
		// click QuickView button
		WebElement quickView = driver.findElement(By.xpath("(//div[contains(text(),'Quick View')])[1]"));
		quickView.click();

		// Print the cost and the discount percentage
		String cost = driver.findElement(By.xpath("//span[@class='strikee ']")).getText();
		String discountedcost = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		System.out.println("The original cost of the Shoe is " + cost);
		System.out.println("The discounted cost of the Shoe is " + discountedcost);
		// Take the snapshot of the shoes.
		File screenshot = driver.getScreenshotAs(OutputType.FILE);
		File image = new File("./snaps/snapdeal.jpg");
		FileUtils.copyFile(screenshot, image);
		// Close the current window
		driver.findElement(By.xpath("//div[@class='close close1 marR10']")).click();
		// Close the main window
		driver.close();
		driver.quit();

	}
}
