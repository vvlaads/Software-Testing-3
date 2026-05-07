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

    public void open() {
        driver.get("https://hosting.timeweb.ru/");
    }

    public boolean isOpened() {
        try {
            waitUntilLoaded();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
