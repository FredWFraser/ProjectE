package fredfraser.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fredfraser.AbstractComponents.AbstractComponent;


public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath = "//div[@class='payment__type payment__type--cc active']")
	 private WebElement paymentType;
	
	@FindBy(css = ".input.txt.text-validated")
	 private WebElement cardNumber;
	
	@FindBy(xpath = "(//select[@class='input ddl'])[1]")
	 private WebElement dropDown;
	
	@FindBy(xpath = "(//select[@class='input ddl'])[2]")
	 private WebElement dropDown2;
	
	@FindBy(xpath = "(//input[@type='text'])[2]")
	 private WebElement cvv;
	
	@FindBy(xpath = "(//input[@type='text'])[3]")
	 private WebElement nameOnCard;
	
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	 private WebElement country;
	
	@FindBy(css = ".ng-star-inserted")
	 private WebElement countryList;
	
	@FindBy(css = ".btnn.action__submit.ng-star-inserted")
	 private WebElement submitOrder;
	
	

	public void enterInfo() throws InterruptedException {

		paymentType.click();
		cardNumber.clear();
		cardNumber.sendKeys("4242424242424242");

		WebElement staticDropdown = dropDown;
		Select dropdown = new Select(staticDropdown);
		dropdown.selectByIndex(3);

		WebElement staticDropdown2 = dropDown2;
		Select dropdown2 = new Select(staticDropdown2);
		dropdown2.selectByIndex(24);

		cvv.sendKeys("123");
		nameOnCard.sendKeys("Fred Fraser");
		country.sendKeys("United");

		Thread.sleep(3000);

		List<WebElement> options = driver.findElements(By.cssSelector(".ng-star-inserted"));

		for (WebElement option : options) {

			if (option.getText().equalsIgnoreCase("United States")) {

				option.click();
				break;
			}

		}

	}
	
	public ConfirmationPage submitOrder()
	{
		submitOrder.click();
		return new ConfirmationPage(driver);
		
		
	}

}
