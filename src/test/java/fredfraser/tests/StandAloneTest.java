package fredfraser.tests;

import static org.testng.Assert.fail;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		String productName = "ZARA COAT 3";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();

		// login page

		driver.findElement(By.id("userEmail")).sendKeys("ffiii1@aol.com");
		driver.findElement(By.id("userPassword")).sendKeys("Esther!00");
		driver.findElement(By.id("login")).click();

		// product page

		driver.findElement(By.xpath("//section[@id='sidebar']//div[3]//div[2]//input[1]")).click();

		Assert.assertTrue(
				driver.findElement(By.xpath("//section[@id='sidebar']//div[3]//div[2]//input[1]")).isSelected());

		if (driver.findElement(By.xpath("//b[normalize-space()='ZARA COAT 3']")).getText().equals(productName)) {

			driver.findElement(By.cssSelector(".btn.w-10.rounded")).click();
			w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
			w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		} else {

			fail();
		}

		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@routerlink='/dashboard/cart']")));

		// click on view cart (cart page)

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		if (driver.findElement(By.xpath("//h3[normalize-space()='ZARA COAT 3']")).getText().equals(productName)) {

			driver.findElement(By.xpath("(//button[normalize-space()='Checkout'])[1]")).click();

		} else {

			fail();
		}

		// credit card info screen (checkout page)

		driver.findElement(By.xpath("//div[@class='payment__type payment__type--cc active']")).click();
		driver.findElement(By.cssSelector(".input.txt.text-validated")).clear();
		driver.findElement(By.cssSelector(".input.txt.text-validated")).sendKeys("4242424242424242");

		WebElement staticDropdown = driver.findElement(By.xpath("(//select[@class='input ddl'])[1]"));
		Select dropdown = new Select(staticDropdown);
		dropdown.selectByIndex(3);

		WebElement staticDropdown2 = driver.findElement(By.xpath("(//select[@class='input ddl'])[2]"));
		Select dropdown2 = new Select(staticDropdown2);
		dropdown2.selectByIndex(24);

		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("123");
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("Fred Fraser");
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("United");

		Thread.sleep(3000);

		List<WebElement> options = driver.findElements(By.cssSelector(".ng-star-inserted"));

		for (WebElement option : options) {

			if (option.getText().equalsIgnoreCase("United States")) {

				option.click();
				break;
			}

		}
		
		driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
		
		// Thank you for your order page (confirmation page)
		
		Assert.assertTrue(driver.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase("Thankyou for the order."));

		driver.quit();

	}

}
