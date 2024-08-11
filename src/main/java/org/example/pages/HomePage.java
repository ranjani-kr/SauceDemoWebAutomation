package org.example.pages;

import java.time.Duration;
import java.util.List;

import org.example.models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver webDriver;

    //WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    By logo = By.className("app_logo");
    By filterIcon = By.className("product_sort_container");
    By currentFilterValue = By.className("active_option");
    By productList = By.xpath("//div[@class='inventory_list']/div");
    By addToCartButtonLocator = By.cssSelector("button[id^='add-to-cart']");
    By shoppingCartBadge = By.className("shopping_cart_badge");
    By productName = By.className("inventory_item_name");
    By productDescription = By.className("inventory_item_desc");
    By productAmount = By.className("inventory_item_price");

    public HomePage(WebDriver webDriver){
        this.webDriver = webDriver;
    }
    public void chooseFilterValue(String filterValue){
        WebElement filterDropDown = webDriver.findElement(filterIcon);
        Select filterDD = new Select(filterDropDown);
        filterDD.selectByVisibleText(filterValue);
    }
    public List<WebElement> getProductLists() {
        return webDriver.findElements(productList);
    }
    public Product addItemToCartByIndex(int index) {
        List<WebElement> productList = getProductLists();
        if (index >= 0 && index < productList.size()) {
            WebElement item = productList.get(index);
            // Get the title of the product
            String productTitle = item.findElement(productName).getText();
            String productDes = item.findElement(productDescription).getText();

            String productPrice = item.findElement(productAmount).getText().replace("\n", "").trim(); ;
            // Click the "Add to Cart" button
            WebElement addToCartButton = item.findElement(addToCartButtonLocator);
            addToCartButton.click();
            return Product.builder()
                    .name(productTitle)
                    .description(productDes)
                    .price(productPrice)
                    .build();
        }
        else {
            System.out.println("Invalid index: " + index);
            return null;
        }
    }
    private String getElementText(By locator) {
        //WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return webDriver.findElement(locator).getText();
    }
    public String getPageTitle() {
        return getElementText(logo); // Using the generic method
    }
    public String getFilterSelectedValue() {
        return getElementText(currentFilterValue);
    }
    public String getBadgeValue(){
        return getElementText(shoppingCartBadge);
    }
    public CartPage clickOnCartIcon(){
        webDriver.findElement(shoppingCartBadge).click();
        return new CartPage(webDriver);
    }
}
