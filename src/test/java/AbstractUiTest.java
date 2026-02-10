import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.*;
import java.util.*;

/**
 * Базовый клас для ui-тестов
 * Здесь прописаны процедуры, используемые для создания драйверов
 */

public abstract class AbstractUiTest {
    //некоторые переменные засунем в одно место, удобнее будет поддерживать
    protected String name = "name11";
    protected String email = "ryzkov12312@diplom.ru";
    protected String password = "123213123!";

    //Запуск браузера данные о браузере берем из My.properties
    protected static WebDriver getDriver (){

        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("My.properties")) {
            prop.load(input);  // Загружаем свойства из файла
        } catch (IOException e) {
            System.out.println("Ругаемся при загрузке проперти");  // Обрабатываем исключение в случае ошибки
            e.printStackTrace();
        }

        //Получим браузер из файла конфигурации
        String driverType = prop.getProperty("browser");
        switch (driverType) {
            case "chrome" :
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                return new ChromeDriver(options);
            case "mozila" :
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                return new FirefoxDriver(firefoxOptions);
            default:
                throw new IllegalArgumentException("Driver type is not support");
        }
    }

}
