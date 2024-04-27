package fredfraser.pageobjects;

import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import fredfraser.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	@FindBy(xpath = "//section[@id='sidebar']//div[3]//div[2]//input[1]")
	WebElement input;
	
	@FindBy(xpath = "//b[normalize-space()='ZARA COAT 3']")
	WebElement coat;
	
	@FindBy(css = ".btn.w-10.rounded")
	WebElement button;

	By toastMessage = By.cssSelector("#toast-container");

	By dashboardCart = By.xpath("//button[@routerlink='/dashboard/cart']");

	public CartPage addProductToCart(String productName) {

		input.click();

		if (coat.getText().equals(productName)) {

			button.click();

			waitForElementToAppear(toastMessage);
			waitForElementToDisappear(spinner);

		} else {

			fail();
		}

			waitForElementToAppear(dashboardCart);
			
			CartPage cartPage = new CartPage(driver);
			return cartPage;

	}

}
