package Week5Day2Assignment;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Public_Ajio extends BaseClassPublicSite {
	@BeforeTest
	public void setData()
	{
		excelFilepath = "./TestData/Publicsite/Ajio.xlsx";
	}
	
	
    @Test(dataProvider="Dynamic_Data")
	public void TC001_ajio(String bag) throws InterruptedException {
		
		driver.get("https://www.ajio.com/");
        driver.findElement(By.name("searchVal")).sendKeys(bag ,Keys.ENTER);
        driver.findElement(By.xpath("//label[@class='facet-linkname facet-linkname-genderfilter facet-linkname-Men']")).click(); 
        Thread.sleep(2000);
        //driver.findElement(By.xpath("//label[@class='facet-linkname facet-linkname-l1l3nestedcategory facet-linkname-Men - Fashion Bags']")).click();
       // Thread.sleep(3000);
        String numberOfItems = driver.findElement(By.className("length")).getText();
        System.out.println("Total number of items : " + numberOfItems);
        
        //Get the list of brand of the products displayed in the page and print the list.
        List<WebElement> brand =driver.findElements(By.className("brand"));
        int NumberofBrands = brand.size();
        System.out.println("The Number of Brands " + NumberofBrands);
        
        for (int i=0;i<NumberofBrands;i++)
        {
        	System.out.println("The Brand Names are " + brand.get(i).getText());
        }
        
     // Get the list of names of the bags and print it
        
        List<WebElement> bags = driver.findElements(By.className("nameCls"));
        int NumberofBags = bags.size();
        System.out.println("The Number of bags " + NumberofBags);
        System.out.println("The Bag Names are ");
        for (int i=0;i<NumberofBags;i++)
        {
        	System.out.println(bags.get(i).getText());
        }
        
	}

}
