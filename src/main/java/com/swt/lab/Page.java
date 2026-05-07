package com.swt.lab;

import org.openqa.selenium.WebDriver;

public class Page {
    protected final WebDriver driver;

    Page(WebDriver driver) {
        this.driver = driver;
    }
}
