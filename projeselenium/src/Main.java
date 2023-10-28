import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        String downloadFilepath = "home/daacarvalho/livro";

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", "/home/daacarvalho/Documentos/livro/");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.scritti.luigigiussani.org/login/");

        synchronized (driver) {
            driver.wait(10000);
        }


        String login_email = "login_email";
        WebElement inputLogin = driver.findElement(By.id(login_email));

        inputLogin.clear();


        String email = "danielfccarvalho@hotmail.com";
        inputLogin.sendKeys(email);

        String login_pwd = "login_password"; // Replace with the actual ID of the element
        WebElement inputPwd = driver.findElement(By.id(login_pwd));

        inputPwd.clear();

        String pwd = System.getenv("MY_PASSWORD_SELENIUM_LOGIN");;
        inputPwd.sendKeys(pwd);

        String fazerLogin = "btn_login";
        WebElement fazerLoginButton = driver.findElement(By.id(fazerLogin));

        fazerLoginButton.click();

        synchronized (driver) {
            driver.wait(5000);
        }

        driver.get("https://www.scritti.luigigiussani.org/pdf?idscheda=6087");

        synchronized (driver) {
            driver.wait(15000);
        }

        for (int i = 148;i < 299;i=i+2) {

            WebElement inputPage = driver.findElement(By.cssSelector("#top > span.pager > input[type=text]"));

            synchronized (driver) {
                driver.wait(1000);
            }

            inputPage.click();

            synchronized (driver) {
                driver.wait(1000);
            }

            inputPage.sendKeys(Keys.BACK_SPACE);

            inputPage.sendKeys(String.valueOf(i));

            synchronized (driver) {
                driver.wait(1000);
            }

            inputPage.sendKeys(Keys.ENTER);

            synchronized (driver) {
                driver.wait(1000);
            }

            synchronized (inputPage) {
                inputPage.sendKeys(Keys.ENTER);
            }

            synchronized (driver) {
                driver.wait(1000);
            }

            WebElement imgElement = driver.findElement(By.cssSelector("#publication > div > div > div.bottom-toolbar-frame > div > button:nth-child(6)"));
            imgElement.click();

            synchronized (driver) {
                driver.wait(1000);
            }

            WebElement ulElement = driver.findElement(By.tagName("ul"));
            List<WebElement> listItems = ulElement.findElements(By.tagName("li"));

            for (WebElement listItem : listItems) {
                WebElement anchorTag = listItem.findElement(By.tagName("a"));
                anchorTag.click();
            }

            driver.findElement(By.className("close-button")).click();
        }

        synchronized (driver) {
            driver.wait(3000);
        }

        synchronized (driver) {
            driver.wait(30000);
        }
        driver.close();
        driver.quit();

//        System.out.println("Hello world!");
    }
}