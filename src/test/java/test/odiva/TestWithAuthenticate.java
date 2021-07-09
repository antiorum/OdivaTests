package test.odiva;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static junit.framework.TestCase.assertEquals;

public class TestWithAuthenticate extends BaseTestClass {

    @Before
    @Override
    public void start() throws InterruptedException {
        super.start();
        driver.findElement(By.xpath("//span[@class='auth__link sign-in']")).click();
        driver.findElement(By.xpath("//input[@name='login']")).sendKeys("tester.qualitative@yandex.ru");
        driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("test_password");
        driver.findElement(By.xpath("//button[text()='Войти']")).click();
    }

    @Test
    public void positiveAuth() throws InterruptedException {
        // таймоут используется в двух местах, вынести в константу. Подумать над использованием механизмов фреймоворка вместо Thread.Sleep
        Thread.sleep(3000);
        String actual = driver.findElement(By.xpath("//div[@class='profile__name']")).getText();
        String expected = "Tester";
        assertEquals(expected, actual);
    }

    @Test
    public void primaryDiscount() throws InterruptedException {
        int expected = 5;
        Thread.sleep(3000);
        String text = driver.findElement(By.xpath("//span[@class='num']")).getAttribute("innerText");
        int actual = Integer.parseInt(text.substring(0, (text.length() - 1)));
        assertEquals(expected, actual);
    }


}
