import api.clients.ClientUser;
import io.qameta.allure.junit4.DisplayName;
import json.UserEmailPassword;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pageobject.RegisterPage;

import static pageobject.LoginPage.LOGIN_URL;

public class RegistrationTest extends AbstractUiTest {

    private WebDriver driver;
    private RegisterPage registerPage;

    //В before запускается браузер и открывается страница регистрации
    @Before
    public void beforeTest() {
        driver = getDriver();

        registerPage = new RegisterPage(driver);
        // Переход на тестируемую страничку
        driver.get(RegisterPage.REG_URL);
    }

    //в after закрывается браузер
    @After
    public void afterTest() {
        driver.quit();
    }

    //Положительный сценарий регистрации
    @DisplayName("Успешная регистрация")
    @Test
    public void registrationUserSuccessTest() {
        registerPage
                .inputNameRegistration(name) //заполняем имя
                .inputEmailRegistration(email) //заполняем емаил
                .inputPasswordRegistration(password) //заполняем пароль
                .clickRegButton(); //жмем кнопку регистрации

        //Если true значит зарегались и перешли на страницу авторизации
        Assert.assertTrue(registerPage.waitURL(LOGIN_URL));

        //Чтобы удалить созданного пользователя
        //нужно авторизоваться, получить токен и удалиться
        //Это будет быстрее через API, выносить в after лень, так как пока нужно всего в одном тесте
        ClientUser clientUser = new ClientUser();
        UserEmailPassword user = new UserEmailPassword(email, password);
      //  String json = "{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}";
        clientUser.deleteUser(clientUser.loginUserReturnToken(user));
    }

    //Неудачная попытка регистрации (Пароль не удовлетворяет требованиям)
    @DisplayName("Ошбика регистрации")
    @Test
    public void registrationUserIncorrectPasswordTest() {
        registerPage
                .inputNameRegistration(name)
                .inputEmailRegistration(email)
                .inputPasswordRegistration("12")
                .clickRegButton();

        Assert.assertTrue("Текст об ошибке отсутствует"
                , driver.findElement(By.xpath(".//p[text()='Некорректный пароль']"))
                        .isDisplayed());
    }
}
