package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;


    public final static String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";

    private final By emailUserInput = By.xpath(".//input[@name='name']");
    private final By passwordUserInput = By.xpath(".//input[@name='Пароль']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By constructButton = By.xpath(".//a[@class='AppHeader_header__link__3D_hX' and @href='/']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Авторизация.Заполнение email")
    public LoginPage  inputEmailRegistration(String email) {
        driver.findElement(emailUserInput).sendKeys(email);
        return this;
    }

    @Step("Авторизация.Заполнение пароля")
    public LoginPage inputPasswordRegistration(String password) {
        driver.findElement(passwordUserInput).sendKeys(password);
        return this;
    }

    @Step(".Нажатие кнопки 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Авторизация.Нажатие на кнопку 'Конструктор'")
    public void clickConstructButton() {
        driver.findElement(constructButton).click();
    }


}
