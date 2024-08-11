package org.example.pages;

import org.example.models.Customer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutYourInformationPage {
    WebDriver webDriver;

    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By continueButton = By.id("continue");
    By postalCode = By.id("postal-code");

    public CheckoutYourInformationPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public void enterCheckoutInformation(Customer customer){
        webDriver.findElement(firstName).click();
        webDriver.findElement(firstName).sendKeys(customer.getFirstname());
        webDriver.findElement(lastName).click();
        webDriver.findElement(lastName).sendKeys(customer.getLastname());
        webDriver.findElement(postalCode).click();
        webDriver.findElement(postalCode).sendKeys(customer.getPostCode());
    }

    public CheckoutOverViewPage clickOnContinueButton(){
        webDriver.findElement(continueButton).click();
        return new CheckoutOverViewPage(webDriver);
    }
}
