package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccountPage {
    WebDriver driver;
    WebDriverWait wait;

    public final static String PERS_ACC_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    private final By exitButton = By.xpath(".//button[text()='Выход']");
    private final By constructButton =By.xpath(".//p[text()='Конструктор']");


    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(3));
    }

    @Step("Личный кабинет.Нажатие на кнопку 'Выход'")
    public void clickButtonExit() {
        driver.findElement(exitButton).click();
    }

    @Step("Личный кабинет.Проверим, что перешли на другую страницу")
    public Boolean transitionUrl(String URL) {
        return wait.until(ExpectedConditions.urlToBe(URL));
    }

    @Step("Личный кабинет.Нажатие на кнопку 'Конструктор'")
    public void clickConstructButton() {
        driver.findElement(constructButton).click();
    }

    @Step("Личный кабинет. дожидаемся появление кнопки 'Выйти'")
    public void waitForLoadedButtonExit() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(exitButton));
    }
}
