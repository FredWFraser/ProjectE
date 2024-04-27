package fredfraser.pageobjects;

import static org.testng.Assert.fail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fredfraser.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	private WebElement cart;

	@FindBy(xpath = "//h3[normalize-space()='ZARA COAT 3']")
	private WebElement coatElement;

	@FindBy(xpath = "(//button[normalize-space()='Checkout'])[1]")
	private WebElement checkout;

	public CheckoutPage goToCheckout(String productName) {

		cart.click();

		if (coatElement.getText().equals(productName)) {

			checkout.click();

		} else {

			fail();
		}

		return new CheckoutPage(driver);

	}

}
