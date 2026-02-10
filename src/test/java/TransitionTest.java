import api.clients.ClientUser;
import io.qameta.allure.junit4.DisplayName;
import json.User;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.BasePage;
import pageobject.LoginPage;
import pageobject.PersonalAccountPage;


import java.util.concurrent.TimeUnit;

import static pageobject.BasePage.BASE_PAGE_URL;
import static pageobject.LoginPage.LOGIN_URL;
import static pageobject.PersonalAccountPage.PERS_ACC_URL;

/*В этом классе проверяются переходы
 * 1. Переход авторизованного пользвоателя в личный кабинет
 * 2. Переход неавторизованного пользователя из страницы логина
 * 3. Переход авторизоаванного пользователя из личного кабинета в конструктор*/
public class TransitionTest extends AbstractUiTest {
    private  WebDriver driver;
    private WebDriverWait wait;

    private final ClientUser clientUser = new ClientUser();


    private String tokken;
    private BasePage basePage;
    private PersonalAccountPage personalAccountPage;
    private LoginPage loginPage;


    @Before
    public void beforeTest() {
        //Запускаем браузер
        driver = getDriver();
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));

        //Создадим необходимые объекты
        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
        //Создаем JSON из объекта
        User user = new User(name, email, password);
        tokken = clientUser.createUser(user);

    }

    @After
    public void afterTest() {
        driver.quit();
        clientUser.deleteUser(tokken);
    }

    //Переход авторизованного пользователя в личный кабинет
    @DisplayName("Переход авторизованного пользователя в личный кабинет")
    @Test
    public void transitionBasePageIntoPerrAccTest() {
        //Сначала нужно авторизоваться
        driver.get(LOGIN_URL);
        //авторизуемся
        loginPage.inputEmailRegistration(email) //email
                .inputPasswordRegistration(password) //password
                .clickLoginButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.MILLISECONDS);
        //Далее проверяем сценарий
       // driver.get(BASE_PAGE_URL);
        basePage.clickPersAccButton();

        Assert.assertTrue(wait.until(ExpectedConditions.urlToBe(PERS_ACC_URL)));
    }

    //Переход из личного кабинета в конструктор не авторизованного пользователя
    @DisplayName("Переход из личного кабинета в конструктор не авторизованного пользователя")
    @Test
    public void transitionPerrAccIntoConstructTest() {
        driver.get(LOGIN_URL);
        loginPage.clickConstructButton();

        Assert.assertTrue(wait.until(ExpectedConditions.urlToBe(BASE_PAGE_URL)));
    }

    //Переход из личного кабинета в конструктор авторизованного пользователя
    @DisplayName("Переход из личного кабинета в конструктор авторизованного пользователя")
    @Test
    public void transitionPerrAccIntoConstructAuthTest() {
        //Сначала нужно авторизоваться
        driver.get(LOGIN_URL);
        //авторизуемся
        loginPage.inputEmailRegistration(email) //email
                .inputPasswordRegistration(password) //password
                .clickLoginButton();
        //Далее проверяем сценарий
        //Перейдем в личный кабинет
        basePage.clickPersAccButton();
        wait.until(ExpectedConditions.urlToBe(PERS_ACC_URL));
        //Кликнем на "Конструктор"
        personalAccountPage.clickConstructButton();

        Assert.assertTrue(wait.until(ExpectedConditions.urlToBe(BASE_PAGE_URL)));
    }

}
