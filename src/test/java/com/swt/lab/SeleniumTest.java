package com.swt.lab;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTest {
    public List<WebDriver> driverList;

    @BeforeEach
    public void setUp() {
        ConfigReader config = new ConfigReader();
        driverList = new ArrayList<>();

        if (config.getBoolean("use.chrome")) {
            driverList.add(new ChromeDriver());
        }

        if (config.getBoolean("use.firefox")) {
            driverList.add(new FirefoxDriver());
        }
    }

    @Test
    public void hello() {
        driverList.forEach(webDriver -> {
            webDriver.get("http://timeweb.com");
            assertEquals("Хостинг-провайдер - Купить домен и хостинг у аккредитованного регистратора", webDriver.getTitle());
            webDriver.quit();
        });
    }
}
