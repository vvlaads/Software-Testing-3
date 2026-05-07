package com.swt.lab;

import org.openqa.selenium.WebDriver;

public class HomePage extends Page {
    HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://timeweb.com/");
    }
}
