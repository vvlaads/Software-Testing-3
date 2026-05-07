package com.swt.lab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page {
    private final By loginInput = By.xpath("//input[@name='username']");
    private final By passwordInput = By.xpath("//input[@name='password']");
    private final By loginButton = By.xpath("//button[@type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilLoaded() {
        waitForVisible(loginInput);
        waitForVisible(passwordInput);
        waitForVisible(loginButton);
    }

    public void open() {
        driver.get("https://hosting.timeweb.ru/login");
    }

    public void enterLogin(String login) {
        driver.findElement(loginInput).sendKeys(login);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void login(String login, String password) {
        enterLogin(login);
        enterPassword(password);
        clickLogin();
    }
}
