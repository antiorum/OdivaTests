package test.odiva;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

public class BaseTestClass {
    // модификаторы доступа где.
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void start() throws InterruptedException {
        // Путь вынести в константу в начало класса.
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\leel\\AppData\\Local\\Microsoft\\WindowsApps\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        // Тоже вынести в константу.
        driver.get("https://odiva.ru");
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

