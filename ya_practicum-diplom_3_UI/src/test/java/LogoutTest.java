import api.clients.ClientUser;
import io.qameta.allure.junit4.DisplayName;
import json.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.BasePage;
import pageobject.LoginPage;
import pageobject.PersonalAccountPage;

import static pageobject.LoginPage.LOGIN_URL;

public class LogoutTest extends AbstractUiTest {
    WebDriver driver;
    WebDriverWait wait;
    PersonalAccountPage personalAccountPage;
    LoginPage loginPage;
    ClientUser clientUser = new ClientUser();
    String tokken;

    //Отркываем браузер и создаем объекты
    @Before
    public void beforeTest() {
        driver = getDriver();
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        //Объекты
        personalAccountPage = new PersonalAccountPage(driver);
        loginPage = new LoginPage(driver);

        /*Для того, чтобы выйти, нужно создать пользователя*/
        //Создаем JSON из объекта
        User user = new User(name, email, password);
        tokken = clientUser.createUser(user);
    }

    //в after закрывается браузер
    @After
    public void afterTest() {
        clientUser.deleteUser(tokken);
        driver.quit();
    }

    @DisplayName("Проверка выхода из аккаунта")
    @Test
    public void logoutTest() {
        //Сначала нужно авторизоваться
        driver.get(LOGIN_URL);
        //авторизуемся
        loginPage.inputEmailRegistration(email) //email
                .inputPasswordRegistration(password) //password
                .clickLoginButton();

        new BasePage(driver).clickPersAccButton();//Кликнем на "личный кабинет
        //дождемся появления нужной нам кнопки
        personalAccountPage.waitForLoadedButtonExit();
        //и нажмем на нее
        personalAccountPage.clickButtonExit();
        //Проверим что перешли на другую страницу, а соответсвенно и вышли
        Assert.assertTrue(personalAccountPage.transitionUrl(LOGIN_URL));
    }

}
