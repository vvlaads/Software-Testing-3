package com.swt.lab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends Page {
    private final By accountButton = By.xpath("//button[@data-test-id='header-account-dropdown-button']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilLoaded() {
        waitForVisible(accountButton);
    }

    @Override
    public void open() {
        driver.get("https://hosting.timeweb.ru/");
    }
}
