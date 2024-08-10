package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LauncherPage {
    private WebDriver webDriver;
    By username = By.id("user-name");
    By password = By.id("password");
    By loginButton = By.id("login-button");
    public LauncherPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public Boolean navigateTo(String url) {
        webDriver.get(url);
        return isSiteLoaded();
    }
    private Boolean isSiteLoaded() {
        return new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
    public void enterLoginDetails(String un, String pwd){
        webDriver.findElement(username).sendKeys(un);
        webDriver.findElement(password).sendKeys(pwd);
    }
    public HomePage clickOnLoginButton(){
        webDriver.findElement(loginButton).click();
        return new HomePage(webDriver);
    }

}
