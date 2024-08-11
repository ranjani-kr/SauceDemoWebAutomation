package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LauncherPage {
    private final WebDriver webDriver;
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");

    public LauncherPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void navigateTo(String url) {
        webDriver.get(url);
    }

    public void enterLoginDetails(String un, String pwd) {
        webDriver.findElement(username).sendKeys(un);
        webDriver.findElement(password).sendKeys(pwd);
    }

    public HomePage clickOnLoginButton() {
        webDriver.findElement(loginButton).click();
        return new HomePage(webDriver);
    }
}
