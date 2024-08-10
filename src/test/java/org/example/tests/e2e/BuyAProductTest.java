package org.example.tests.e2e;

import org.example.drivers.DriverCreator;
import org.example.pages.CartPage;
import org.example.pages.HomePage;
import org.example.pages.LauncherPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BuyAProductTest {

    private final WebDriver webDriver = new DriverCreator().create("chrome");
    String standardUser= "standard_user";
    String pw = "secret_sauce";
    String lowToHigh = "Price (low to high)";

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
        String product1 = homePage.addItemToCartByIndex(1);
        System.out.println("Product added to cart: " + product1);
        String product2 = homePage.addItemToCartByIndex(2);
        System.out.println("Product added to cart: " + product2);
        Thread.sleep(3000);
        String badgeNumber = homePage.getBadgeValue();
        Assert.assertEquals(badgeNumber,"2");
        CartPage cartPage = homePage.clickOnCartIcon();
        Assert.assertTrue(cartPage.isProductInCart(product1), "Product " + product1 + " is not found in the cart.");
        Assert.assertTrue(cartPage.isProductInCart(product2), "Product " + product2 + " is not found in the cart.");
        
    }
}
