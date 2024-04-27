package fredfraser.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import fredfraser.TestComponents.BaseTest;
import fredfraser.pageobjects.CartPage;
import fredfraser.pageobjects.CheckoutPage;
import fredfraser.pageobjects.ConfirmationPage;
import fredfraser.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test
	public void submitOrder() throws InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication("ffiii1@aol.com", "Esther!00");
		CartPage cartPage = productCatalogue.addProductToCart(productName);
		CheckoutPage checkoutPage = cartPage.goToCheckout(productName);
		checkoutPage.enterInfo();
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

	}
}
