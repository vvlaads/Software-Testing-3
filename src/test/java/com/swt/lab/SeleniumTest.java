package com.swt.lab;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

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


    private void switchToNewTab(WebDriver driver) {
        String originalWindow = driver.getWindowHandle();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> d.getWindowHandles().size() > 1);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                return;
            }
        }
    }

    @Test
    public void homeTest() {
        driverList.forEach(webDriver -> {
            HomePage homePage = new HomePage(webDriver);
            homePage.open();

            assertTrue(homePage.isOpened());
            webDriver.quit();
        });
    }

    @Test
    public void loginTest() {
        driverList.forEach(webDriver -> {
            HomePage homePage = new HomePage(webDriver);
            homePage.open();
            homePage.waitUntilLoaded();
            homePage.clickLogin();

            switchToNewTab(webDriver);

            String login = EnvReader.get("LOGIN");
            String password = EnvReader.get("PASSWORD");

            LoginPage loginPage = new LoginPage(webDriver);
            loginPage.waitUntilLoaded();
            loginPage.login(login, password);

            ProfilePage profilePage = new ProfilePage(webDriver);
            assertTrue(profilePage.isOpened());

            webDriver.quit();
        });
    }
}
