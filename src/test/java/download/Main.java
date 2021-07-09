package download;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

// Мы вроде договаривались, что не будем демонстрировать всем твое пристрастие к яою. Убрать в другой проект.
public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\leel\\AppData\\Local\\Microsoft\\WindowsApps\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        try {
            findNewManga(driver);


//            List<WebElement> elements = driver.findElements(
//                    By.xpath("//table[@id='download_table']//a"));
//
//            for (WebElement a : elements) {
//                Thread.sleep(1000);
//                a.click();
//            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Thread.sleep(100);
//            driver.quit();
        }
    }


    private static void findNewManga(WebDriver driver) throws InterruptedException {
        driver.get("https://yaoi-chan.me");
        Thread.sleep(100);

        WebElement login = driver.findElement(
                By.xpath("//input[@name='login_name']"));
        login.sendKeys("leel");
        Thread.sleep(100);

        WebElement password = driver.findElement(
                By.xpath("//input[@name='login_password']"));
        password.sendKeys("533566leel");
        Thread.sleep(100);

        WebElement enter = driver.findElement(
                By.xpath("//input[@value='Вход']"));
        enter.click();
        Thread.sleep(100);

        WebElement catalog = driver.findElement(
                By.xpath("//a[text()='Каталог Яой Манги']"));
        catalog.click();
        Thread.sleep(100);

        WebElement complete = driver.findElement(
                By.xpath("//div[@class='only']/a[text()='Перевод завершен']"));
        complete.click();
        Thread.sleep(100);

        List<WebElement> elements = driver.findElements(
                By.xpath("//div[contains(@id,'wanted')]/span/a"));


        var prochitano = elements
                .stream()
                .filter(e -> e.getText().equals("Не прочитано!"))
                .collect(Collectors.toList());

        //prochitano.get(1).

//div[contains(@id,'wanted')]/span/a/../../../../../../div/a
    }
}




