package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    WebDriver webDriver;
    By cartItemsLocator = By.cssSelector(".cart_list .cart_item");
    By productNameLocator = By.className("inventory_item_name");
    By checkoutButton = By.id("checkout");
    public CartPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public boolean isProductInCart(String product1) {
        List<WebElement> cartItems = webDriver.findElements(cartItemsLocator);

        for (WebElement cartItem : cartItems) {
            String actualProductName = cartItem.findElement(productNameLocator).getText();
            if (actualProductName.equals(product1)) {
                return true;
            }
        }
        return false;
    }

    public CheckoutYourInformationPage clickOnCheckoutButton(){
        webDriver.findElement(checkoutButton).click();
        return new CheckoutYourInformationPage(webDriver);
    }
}
