package org.example.pages;

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

    public void enterCheckoutInformation(String fn,String ln, String postCode){
        webDriver.findElement(firstName).click();
        webDriver.findElement(firstName).sendKeys(fn);
        webDriver.findElement(lastName).click();
        webDriver.findElement(lastName).sendKeys(ln);
        webDriver.findElement(postalCode).click();
        webDriver.findElement(postalCode).sendKeys(postCode);
    }

    public  CheckOutOverViewPage clickOnContinueButton(){
        webDriver.findElement(continueButton).click();
        return new CheckOutOverViewPage(webDriver);
    }
}
