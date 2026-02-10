package ru.praktikumService.qascooter;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/*Набор тестов проверяющие авторизацию, реализован отдельными тестами
 * 1. Успешная авторизация
 * 2. Запрос без логина
 * 3. Запрос без пароля
 * 4. Запрос логин ""
 * 5. Не существуяющая запись*/
public class LoginCourierTest {
    //Объекты
    private CourierAuthorization courierAuthorization;
    private ClientCourier clientCourier;
    //Данные
    private final String login = "RyzhkovTestLogin_01";
    private final String password = "12345";
    private Integer loginId;

    @Before
    public void beforeTest() {
        clientCourier = new ClientCourier();
        courierAuthorization = new CourierAuthorization();
        //Создаем курьера над которым будут проводится тесты
        ValidatableResponse createResponse =
                clientCourier.create(new Courier(login, password, "VinDisiel"));
        //Получаем айди, чтобы вконце удалить
        loginId = clientCourier
                .login(new CourierAuthorization(login, password)) //логинимся
                .extract() //достаем данные
                .path("id"); //а именно айди
    }

    @After
    public void aftetTest() {
        //Здесь просто удалим без проверок курьера, созданного в before
        clientCourier.delete(loginId);
    }

    @Test
    public void AuthSuccessTest() {
        courierAuthorization.setLogin(login);
        courierAuthorization.setPassword(password);

        ValidatableResponse loginResponse =
                clientCourier.login(courierAuthorization);
        Assert.assertEquals("Ожидаем ответ 200, успешно",
                loginResponse.extract().statusCode(), HttpStatus.SC_OK /*200*/);
        Assert.assertNotNull(loginResponse.extract().path("id"));
    }

    @Test
    public void AuthNoLoginTest() {
        courierAuthorization.setPassword(password);

        ValidatableResponse loginResponse =
                clientCourier.login(courierAuthorization);
        Assert.assertEquals("Ожидаем ответ 400",
                loginResponse.extract().statusCode(), HttpStatus.SC_BAD_REQUEST);
        Assert.assertEquals("Недостаточно данных для входа",
                loginResponse.extract().path("message"));
    }

    /*Этот тест вместо ошибки, зависает.
     * По сути это обнаруженный баг*/
    @Test
    public void AuthNoPasswordTest() {
        courierAuthorization.setLogin(login);

        ValidatableResponse loginResponse =
                clientCourier.login(courierAuthorization);
        Assert.assertEquals("Ожидаем ответ 400",
                loginResponse.extract().statusCode(), HttpStatus.SC_BAD_REQUEST);
        Assert.assertEquals("Недостаточно данных для входа",
                loginResponse.extract().path("message"));
    }

    @Test
    public void AuthLoginIsNullTest() {
        courierAuthorization.setLogin("");
        courierAuthorization.setPassword(password);

        ValidatableResponse loginResponse =
                clientCourier.login(courierAuthorization);
        Assert.assertEquals("Ожидаем ответ 400",
                loginResponse.extract().statusCode(), HttpStatus.SC_BAD_REQUEST);
        Assert.assertEquals("Недостаточно данных для входа",
                loginResponse.extract().path("message"));
    }

    /*Грубо и не причесано
     * Вопрос к быстродействию и лаконичности написанного
     */
    @Test
    public void AuthNotFoundLoginTest() {
        //Чтобы избежать случайную авторизацию, мало ли кто-то создал курьера такого
        //же Сделаем цикл пока не получим ошибку
        String loginNotFound = "RyzhkovNotFound";
        Integer statusCode;
        String messageResponce;

        courierAuthorization.setLogin(loginNotFound);
        courierAuthorization.setPassword(password);

        ValidatableResponse loginResponse =
                clientCourier.login(courierAuthorization);

        statusCode = loginResponse.extract().statusCode();
        //Если статус не 404, то возможно такой клиент в базе есть
        if (!statusCode.equals(404)) {
            //Тогда создадим цикл, где будем прибавлять циферки к логину
            int x = 1;
            while (!statusCode.equals(404)) {
                //Добавим в объект изменненное имя
                courierAuthorization.setLogin(loginNotFound + x);
                //Пробуем еще раз
                loginResponse = clientCourier.login(courierAuthorization);

                statusCode = loginResponse.extract().statusCode();
                //Вконце цикла увеличим число на единицу
                x++;
            }
            //Получим сообщение
            messageResponce = loginResponse.extract().path("message");
        } else {
            //Если все-таки 404 получили, то тоже получим сообщение
            messageResponce = loginResponse.extract().path("message");
        }
        //Приходится преобразовать, чтобы удобно было сравнивать
        //Имхо надо подумать над более красивым решением
        int loginStatusCode = statusCode;
        //Проверки
        Assert.assertEquals(loginStatusCode, HttpStatus.SC_NOT_FOUND);
        Assert.assertEquals("Учетная запись не найдена", messageResponce);
    }
}
