package ru.praktikumService.scooter;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/* Набор тестов для проверки ответов на FAQ
   Запуск и закрытие браузера выполняется в родительском абстрактном классе с помощью аннотацией BeforeClass и AfterClass.
   Такая аннотация выполнена, чтобы сыкономить ресурсы и не открывать браузер перед каждым тестом.
   Иначе пришлось бы 8 раз открыть и закрыть браузер.
 */
@RunWith(Parameterized.class)
public class FaqQuestionsTests extends AbstractUiTest {
    private final String expectedResult;
    private final String faqNumber;

    public FaqQuestionsTests(String faqNumber, String expectedResult) {
        this.faqNumber = faqNumber;
        this.expectedResult = expectedResult;
    }

    //Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"1", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"2", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"3", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"4", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"5", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"6", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"7", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void getTextFaqQuestionsTest() {
        String actualResult;
        BasePage basePage = new BasePage(driver);
        //Получаем текст ответа на вопрос с главной страницы
        actualResult = basePage.getFaqQuestions(faqNumber);
        //сравниваем с ожидаемым
        Assert.assertEquals("Текст не совпадает с ожидаемым", expectedResult, actualResult);
    }
}
