import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pageobject.BasePage;

import static pageobject.BasePage.BASE_PAGE_URL;

public class ConstructorSectionTest extends AbstractUiTest {
    private static WebDriver driver;
    private static BasePage basePage;

    /*Так как будет просто переключаться по вкладкам, то не надо перед каждый тестом запускать браузер*/
    @BeforeClass
    public static void beforeTest() {
        driver = getDriver();

        basePage = new BasePage(driver);
        driver.get(BASE_PAGE_URL);
    }

    @AfterClass
    public static void afterTest() {
        driver.quit();
    }


    //Проверим переход к секции булки
    @DisplayName("Проверка перехода к секции булки")
    @Test
    public void crossSectionBunsTest() {
        basePage.clickButtonBuns();
        //Проверяем, что появилась область с булками
        Assert.assertTrue(basePage.visibilityBuns());
    }

    //Проверим переход к секции соусы
    @DisplayName("Проверка перехода к секции соусы")
    @Test
    public void crossSectionSauceTest() {
        basePage.clickButtonSauce();
        //Проверяем, что появилась область с булками
        Assert.assertTrue(basePage.visibilitySauce());
    }

    //Проверим переход к секции начинки
    @DisplayName("Проверка перехода к секции начинки")
    @Test
    public void crossSectionFillingTest() {
        basePage.clickButtonFilling();
        //Проверяем, что появилась область с булками
        Assert.assertTrue(basePage.visibilityFilling());
    }
}
