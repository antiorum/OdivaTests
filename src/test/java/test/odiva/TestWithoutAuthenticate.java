package test.odiva;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class TestWithoutAuthenticate extends BaseTestClass {

    @Test
    public void cookiesCountTest() {
        int actual = driver.manage().getCookies().size();
        // Переименовать для понятности.
        int expected = 15;
        assertEquals(expected, actual);
    }

    @Test
    public void pagePartCountTest() {
        List<WebElement> list = new ArrayList<>();
        list.add(driver.findElement(By.id("header")));
        list.add(driver.findElement(By.id("page-content-wrap")));
        list.add(driver.findElement(By.id("footer")));
        // Переименовать для понятности.
        int expected = 3;
        assertEquals(expected, list.size());
    }

    @Test
    public void menuPartCountTest() {
        List<WebElement> list = driver.findElements(By.xpath("//nav//ul[contains(@class,'menu-items_lvl1')]/li"));
        // Переименовать для понятности.
        int expected = 11;
        assertEquals(expected, list.size());
    }

    @Test
    public void menuSectionTest() {
        chooseMenuSection();
        String actual = driver.getCurrentUrl();
        // Здесь и ниже вынести общую часть путь в константу. Конкатенировать при необходимости.
        String expected = "https://odiva.ru/catalog/nogti/";
        assertEquals(expected, actual);
    }

    @Test
    public void categoryMenuTest() {
        Actions action = new Actions(driver);
        action
                .moveToElement(driver.findElement(By.xpath("//span[text()='Волосы']")))
                .pause(Duration.ofSeconds(1))
                .moveToElement(driver.findElement(By.xpath("//span[text()='Уход и лечение']")))
                .click()
                .build()
                .perform();
        String actual = driver.getCurrentUrl();
        String expected = "https://odiva.ru/catalog/uhod_dlya_volos/";
        assertEquals(expected, actual);
    }

    @Test
    public void searchTest() {
        WebElement search = driver.findElement(By.xpath("//input[@class='input']"));
        search.sendKeys("staleks", Keys.ENTER);
        List<WebElement> list = driver.findElements(By.xpath("//div[@class='results__items']//a"));
        assertFalse(list.isEmpty());
    }

    @Test
    public void sideCategoryTest() {
        chooseMenuSection();
        WebElement category = driver.findElement(By.xpath("//div[contains(@class,'categories')]//ul[@data-lvl='1']//li[3]//a"));
        category.click();
        String actual = driver.getCurrentUrl();
        String expected = "https://odiva.ru/catalog/geli/";
        assertEquals(expected, actual);
    }

    @Test
    public void addToBasketTest() {
        fillBasket();
        int actual = Integer.parseInt(driver.findElement(By.xpath("//div[@data-type='basket']//span[@class='num products-count']")).getAttribute("innerText"));
        // Переименовать для понятности.
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void removeFromBasketTest() throws InterruptedException {
        fillBasket();
        driver.get("https://odiva.ru/personal/cart/");
        WebElement remove = driver.findElement(By.xpath("(//div[@class='product-list']//div[@class='delete-wrap'])[1]"));
        remove.click();
        // Использовать механизмы фреймворка, если получится.
        Thread.sleep(1000);
        String actual = driver.findElement(By.xpath("(//div[@data-tab='incart'])[1]")).getText();
        String expected = "В КОРЗИНЕ (1 )";
        assertEquals(expected, actual);
    }

    @Test
    public void addToFavoriteTest() {
        chooseMenuSection();

        Actions add = new Actions(driver);
        add
                .moveToElement(driver.findElement(By.xpath("//div[@class='catalog__items products']/div[1]")))
                .pause(Duration.ofSeconds(1))
                .moveToElement(driver.findElement(By.xpath("//div[@class='catalog__items products']/div[1]//span[contains(@class,'btn__text')][1]")))
                .click()
                .pause(Duration.ofSeconds(1))
                .build()
                .perform();
        int actual = Integer.parseInt(driver.findElement(By.xpath("//div[@data-type='fav']//span[@class='num products-count']")).getAttribute("innerText"));
        // Переименовать для понятности.
        int expected = 1;
        assertEquals(expected, actual);
    }

    private void chooseMenuSection() {
        // final тут лишний. Убрать в начало класса.
        final int MENUSECTION = 4;
        WebElement choice = driver.findElement(By.xpath("(//nav//ul[contains(@class,'menu-items_lvl1')]/li)[" + MENUSECTION + "]"));
        choice.click();
    }

    private void fillBasket() {
        chooseMenuSection();

        Actions add = new Actions(driver);
        add
                .moveToElement(driver.findElement(By.xpath("(//div[@class='catalog__items products']/div)[1]")))
                .pause(Duration.ofSeconds(1))
                .moveToElement(driver.findElement(By.xpath("(//div[@class='catalog__items products']/div)[1]//button[@class='btn buy-btn']")))
                .click()
                .moveToElement(driver.findElement(By.xpath("(//div[@class='catalog__items products']/div)[2]")))
                .pause(Duration.ofSeconds(1))
                .moveToElement(driver.findElement(By.xpath("(//div[@class='catalog__items products']/div)[2]//button[@class='btn buy-btn']")))
                .click()
                .pause(Duration.ofSeconds(1))
                .build()
                .perform();
    }
}