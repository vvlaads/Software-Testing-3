package com.swt.lab;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumTest {

    public List<WebDriver> driverList;
    private static final Logger log = LoggerFactory.getLogger(SeleniumTest.class);

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

        log.info("Drivers initialized: {}", driverList.size());
    }

    @AfterEach
    public void tearDown() {
        driverList.forEach(driver -> {
            try {
                log.info("Closing driver");
                driver.quit();
            } catch (Exception e) {
                log.error("Error while closing driver", e);
            }
        });
    }

    private void switchToNewTab(WebDriver driver) {
        String originalWindow = driver.getWindowHandle();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> d.getWindowHandles().size() > 1);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                log.info("Switched to new tab");
                return;
            }
        }

        throw new RuntimeException("New tab was not found");
    }

    @Test
    public void homeTest() {
        driverList.forEach(driver -> {

            log.info("START HOME TEST");

            HomePage homePage = new HomePage(driver);

            homePage.open();
            homePage.waitUntilLoaded();

            assertTrue(homePage.isOpened());

            log.info("END HOME TEST");
        });
    }

    @Test
    public void loginTest() {
        driverList.forEach(driver -> {

            log.info("START LOGIN TEST");

            HomePage homePage = new HomePage(driver);
            LoginPage loginPage = new LoginPage(driver);
            ProfilePage profilePage = new ProfilePage(driver);

            homePage.open();
            homePage.waitUntilLoaded();
            homePage.clickLogin();

            switchToNewTab(driver);

            String login = EnvReader.get("LOGIN");
            String password = EnvReader.get("PASSWORD");

            loginPage.waitUntilLoaded();
            loginPage.login(login, password);

            assertTrue(profilePage.isOpened());

            log.info("END LOGIN TEST");
        });
    }
}