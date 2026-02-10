package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(3));
    }

    public final static String BASE_PAGE_URL = "https://stellarburgers.nomoreparties.site/";


    private final By buttonLoginInBasePage = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");
    private final By buttonPersAccInBasePage = By.xpath(".//p[text()='Личный Кабинет']");
    private final By buttonCreateOrder= By.xpath(".//button[text()='Оформить заказ']");

    private final By buttonBuns= By.xpath(".//span[text()='Булки']/parent::*");
    private final By buttonSauce= By.xpath(".//span[text()='Соусы']/parent::*");
    private final By buttonFilling= By.xpath(".//span[text()='Начинки']/parent::*");
    //При выделении разделам он становится подчеркнутым
    // и к имени класса добавляется стиль tab_tab_type_current__2BEPc
    //Для поиска проще обратится к родителю от имени раздела
    private final By bunsInUnderlined
            = By.xpath(".//span[text()='Булки']/parent::div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");
    private final By sauceInUnderlined
            = By.xpath(".//span[text()='Соусы']/parent::div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");
    private final By fillingInUnderlined
            = By.xpath(".//span[text()='Начинки']/parent::div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");


    @Step("Главная страница.Нажатие кнопки 'Войти в аккаунт'")
    public void clickLoginButton() {
        driver.findElement(buttonLoginInBasePage).click();
    }

    @Step("Главная страница.Нажатие кнопки 'Личный кабинет'")
    public void clickPersAccButton() {
        driver.findElement(buttonPersAccInBasePage).click();
    }

    @Step("Доступна ли кнопка Оформить заказ")
    public Boolean bolleanButtonCreateOrder() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(buttonCreateOrder)).isDisplayed();
    }

    @Step("Клик по кнопке 'Булки'")
    public void clickButtonBuns() {
        driver.findElement(buttonBuns).click();
    }

    @Step("На экране раздел 'Булки' стал подчеркнутым")
    public boolean visibilityBuns() {
        return driver.findElement(bunsInUnderlined).isDisplayed();
    }

    @Step("Клик по кнопке 'Соусы'")
    public void clickButtonSauce() {
        driver.findElement(buttonSauce).click();
    }

    @Step("На экране раздел 'Соусы' стал подчеркнутым")
    public boolean visibilitySauce() {
        return driver.findElement(sauceInUnderlined).isDisplayed();
    }

    @Step("Клик по кнопке 'Начинки'")
    public void clickButtonFilling() {
        driver.findElement(buttonFilling).click();
    }

    @Step("На экране раздел 'Начинки' стал подчеркнутым")
    public boolean visibilityFilling() {
        return driver.findElement(fillingInUnderlined).isDisplayed();
    }
}