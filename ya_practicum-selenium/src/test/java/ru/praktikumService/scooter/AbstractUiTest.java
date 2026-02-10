package ru.praktikumService.scooter;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Базовый клас для ui-тестов
 * Здесь прописаны процедуры, используемые для создания драйверов
 * Заполнение куки
 */
public abstract class AbstractUiTest {
    protected static  WebDriver driver;

    @BeforeClass
    public static void setUpDriver() {
        //Откроем хром и перейдем на тестируемую страницу
        driver = getDriver("chrome");
        driver.get(BasePage.URL);

        //Прописываем куки и обновляем страницу
        //Так как баннер уведомляющий нас об использовании куки перекрывает нужные кнопки
        setCookie(new Cookie("Cartoshka", "true"));
        setCookie(new Cookie("Cartoshka-legacy", "true"));
        driver.navigate().refresh();
    }

    @AfterClass
    public static void tearnDown(){
        //Закроем браузер
        driver.quit();
    }
    //Запуск указанного браузера
    protected static WebDriver getDriver (String driverType){
        switch (driverType) {
            case "chrome" :
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                return new ChromeDriver(chromeOptions);
            case "mozila" :
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                return new FirefoxDriver(firefoxOptions);
            default:
                throw new IllegalArgumentException("Driver type is not support");
        }
    }
    //Заполняет куки
    public static void setCookie(Cookie cookie){
        driver.manage().addCookie(cookie);
    }
}
