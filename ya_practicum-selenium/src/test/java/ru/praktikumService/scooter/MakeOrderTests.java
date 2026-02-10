package ru.praktikumService.scooter;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Cookie;

/*В классе проверяются тесткейсы с заказазом самоката при вызове из двух мест
 * низ страницы и шапка страницы
 * Не выполняется @Before и @After, так как в AbstractUiTest есть @BeforeClass,
 * но для избежания ошибок, что сейчас находимся где-то в другом месте, перед тестом
 * переходим на стартовую страницу */
public class MakeOrderTests extends AbstractUiTest {

    @Test
    public void makeOrderFromBasePageTests() {
        boolean actual;

        driver.get(BasePage.URL);
        //Прописываем куки и обновляем страницу
        //Так как баннер уведомляющий нас об использовании куки перекрывает нужные кнопки
        setCookie(new Cookie("Cartoshka", "true"));
        setCookie(new Cookie("Cartoshka-legacy", "true"));
        driver.navigate().refresh();
        //Выполняем тест
        actual = makeOrder("BasePage");
        Assert.assertTrue("Заказ не оформлен", actual);
    }

    @Test
    public void makeOrderFromHeaderTests() {
        boolean actual;

        driver.get(BasePage.URL);
        actual = makeOrder("HeaderFragment");
        Assert.assertTrue("Заказ не оформлен", actual);
    }


    public boolean makeOrder(String object) {
        //to_do: подумать над перехватом исключения, для улучшения читаемости

        /*Проверка на то, что открылась новая страница с формой заказа, сверяем URL с ожидаемым
         * Assert.assertEquals("Перешли на правильную страницу", OrderPage.URL, driver.getCurrentUrl());
         * Хотя думаю проверка лишняя, достаточно убедиться, что появились нужные локаторы
         * Поэтому реализовал все единой цепочкной
         *
         * Хотя понимаю, что некоторые шаги можно в один объединить, возможно так и надо будет сделать */
        if (object.equals("HeaderFragment")) {
            boolean result = new HeaderFragment(driver)
                    .clickMakeOrderButton() //кликнули на кнопку заказать в шапке страницы
                    .sendName("Григорий") //заполняем имя
                    .sendFamily("Рыжков") //фамилию
                    .sendAddress("ул.Смешариков, 10") //адрес
                    .sendMetroStation("Сокольники") //метро
                    .sendPhoneNumber("+79998887755") //номер телефона
                    .clickNextButton() //кликаем далее и заполняем дальше
                    .sendDateOrder("03.07.2024") //дату выдачи самоката
                    .sendPeriodRent() // выбор периода аренды
                    .clickOrderButton() //заказываем
                    .clickConfirmationButton(); //подтверждаем и ожидаем, что появилось окно, что заказ оформлен
            return result;
        } else if (object.equals("BasePage")) {
            boolean result = new BasePage(driver)
                    .clickMakeOrderButton() //кликнули на кнопку заказать в шапке страницы
                    .sendName("Григорий") //заполняем имя
                    .sendFamily("Рыжков") //фамилию
                    .sendAddress("ул.Смешариков, 10") //адрес
                    .sendMetroStation("Сокольники") //метро
                    .sendPhoneNumber("+79998887755") //номер телефона
                    .clickNextButton() //кликаем далее и заполняем дальше
                    .sendDateOrder("03.07.2024") //дату выдачи самоката
                    .sendPeriodRent() // выбор периода аренды
                    .clickOrderButton() //заказываем
                    .clickConfirmationButton(); //подтверждаем и ожидаем, что появилось окно, что заказ оформлен
            return result;
        } else {
            throw new IllegalArgumentException("Driver type is not support");
        }
    }
}
