package com.swt.lab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page {
    private final By loginButton = By.xpath("//button[@data-selenium='header-btn-login']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilLoaded() {
        waitForVisible(loginButton);
    }

    public void open() {
        driver.get("https://timeweb.com/");
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }
}
