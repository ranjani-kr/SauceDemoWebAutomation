package org.example.tests.e2e;

import org.example.drivers.DriverCreator;
import org.example.models.Customer;
import org.example.models.Product;
import org.example.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class BuyAProductTest {

    private final WebDriver webDriver = new DriverCreator().create("chrome");
    String standardUser= "standard_user";
    String pw = "secret_sauce";
    String lowToHigh = "Price (low to high)";
//    String fn = "Rachel";
//    String ln = "Green";
//    String postCode = "2134";

    @BeforeTest
    public void setUp() {
        // Arrange

    }
    @AfterClass
    public void tearDown() {
        webDriver.quit();
    }

    @Test
    public void sortByLowToHighAndBuyFirstTwoProductFromTheList() throws InterruptedException {

        Customer customerInfo = new Customer().init();
        LauncherPage launcherPage = new LauncherPage(webDriver);
        launcherPage.navigateTo("https://www.saucedemo.com/");
        launcherPage.enterLoginDetails(standardUser,pw);
        HomePage homePage = launcherPage.clickOnLoginButton();
        String titleText = homePage.getPageTitle();
        Assert.assertEquals(titleText,"Swag Labs");
        Thread.sleep(3000);
        homePage.chooseFilterValue(lowToHigh);
        String selectedFilterValue = homePage.getFilterSelectedValue();
        Assert.assertEquals(selectedFilterValue,lowToHigh);
        Product infantOnesie = homePage.addItemToCartByIndex(0);
        System.out.println("Product added to cart: " + infantOnesie.getName());
        System.out.println("Product description: " + infantOnesie.getDescription());
        System.out.println("Product price: " + infantOnesie.getPrice());
        Product bikeLight = homePage.addItemToCartByIndex(1);
        System.out.println("Product added to cart: " + bikeLight.getName());
        System.out.println("Product description:" + bikeLight.getDescription());
        System.out.println("Product price: " + bikeLight.getPrice());
        Thread.sleep(3000);
        String badgeNumber = homePage.getBadgeValue();
        Assert.assertEquals(badgeNumber,"2");
        CartPage cartPage = homePage.clickOnCartIcon();
        Assert.assertTrue(cartPage.isProductInCart(infantOnesie.getName()), "Product " + infantOnesie.getName() + " is not found in the cart.");
        Assert.assertTrue(cartPage.isProductInCart(bikeLight.getName()), "Product " + bikeLight.getName() + " is not found in the cart.");
        CheckoutYourInformationPage checkoutYourInformationPage = cartPage.clickOnCheckoutButton();
        Thread.sleep(3000);
        checkoutYourInformationPage.enterCheckoutInformation(customerInfo);
        Thread.sleep(3000);
        CheckoutOverViewPage checkOutOverViewPage = checkoutYourInformationPage.clickOnContinueButton();

        // Verify the page title
        Assert.assertEquals(checkOutOverViewPage.getPageTitle(), "Checkout: Overview");

        // Get the product list and verify that the correct products are listed in the overview
        List<Product> productsInOverview = checkOutOverViewPage.getProductList();
        Assert.assertTrue(productsInOverview.contains(infantOnesie), "Product " + infantOnesie.getName() + " is not listed in the overview.");
        Assert.assertTrue(productsInOverview.contains(bikeLight), "Product " + bikeLight.getName() + " is not listed in the overview.");

        // Verify payment and shipping information
        Assert.assertEquals(checkOutOverViewPage.getPaymentInformation(), "SauceCard #31337", "Payment information does not match.");
        Assert.assertEquals(checkOutOverViewPage.getShippingInformation(), "Free Pony Express Delivery!", "Shipping information does not match.");

        // Verify item total, tax, and total price
        Assert.assertEquals(checkOutOverViewPage.getItemTotal(), "Item total: $17.98", "Item total does not match.");
        Assert.assertEquals(checkOutOverViewPage.getTax(), "Tax: $1.44", "Tax does not match.");
        Assert.assertEquals(checkOutOverViewPage.getTotalPrice(), "Total: $19.42", "Total price does not match.");
    }

        

}
