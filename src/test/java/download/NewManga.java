//package download;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//
//import java.util.List;
//
//public class NewManga {
//    public static int count = 900;
//
//    public void findNewManga(driver) {
//
//        try {
//            driver.get("https://yaoi-chan.me/manga/5091-gerbera.html");
//            Thread.sleep(1000);
//
//            Actions action = new Actions(driver);
//            action
//                    .moveToElement(driver.findElement(By.
//                            xpath("//a[text()='Скачать мангу']")))
//                    .click()
//                    .build()
//                    .perform();
//
//            List<WebElement> elements = driver.findElements(
//                    By.xpath("//table[@id='download_table']//a"));
//
//            for (WebElement a : elements) {
//                Thread.sleep(1000);
//                a.click();
//            }
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            Thread.sleep(2000);
//            driver.quit();
//        }
//    }
//
//    public String downloadManga() {
//        return "Downloaded " + n + " chapters to " + (count++) + " folder.";
//    }
//
//    ;
//}
