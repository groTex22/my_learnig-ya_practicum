package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage {
    WebDriver driver;
    WebDriverWait wait;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(3));
    }

    public final static String FORGOT_PASS_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    //Кнопка "войти" внизу страницы
    private final By loginButton = By.xpath(".//a[text()='Войти']");


    @Step("Восстановление пароля.Клик на кнопку 'Войти' внизу страницы")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }




}
