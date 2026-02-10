package ru.praktikumService.qascooter;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/*Набор тестов проверяющие создание куьера, реализован отдельными тестами
 * Возможно есть смысл подумать о параметризации
 *
 ***** НАБОР ТЕСТ-КЕЙСОВ НЕБОЛЬШОЙ, ПРОВЕРЯЕМ МИНИМУМ *******/
public class CreateCourierTest {
    private ClientCourier clientCourier;
    private Courier courier;
    //айди нужно запомнить, чтобы потом удалить курьера
    private Integer loginId;

    @Before
    public void beforeTest() {
        //Создали объект clientCourier
        clientCourier = new ClientCourier();
        //Создаем объект для дальнейшей передачи в качестве JSON на эндпоинт
        // to_do: можно вынести в какой-то генератор, но для этого мало информации
        courier = new Courier();
    }

    @After
    public void aftetTest() {
        //Обвернем в блок исключений, в случае если в тесте курьера создать не удалось.
        try {
            //Здесь просто удалим без проверок, подробнее про удаление в другом классе
            clientCourier.delete(loginId);
        } catch (Exception exception) {
            System.err.println("Ничего не создали, удалять нечего");
        }
    }

    @Test
    public void createCourierFieldAllParamTest() {
        //Заполним все параметры
        courier.setLogin("RyzhkovTest_01");
        courier.setFirstName("SosoPavliashvili");
        courier.setPassword("12345667890");

        ValidatableResponse response = clientCourier.create(courier);
        //Дальше проверки
        // int statusCode = response.extract().statusCode();
        Assert.assertEquals("Ожидаем ответ 201, клиент создался",
                response.extract().statusCode(), HttpStatus.SC_CREATED /*201*/);
        Assert.assertTrue(
                "В теле параметра ok, должно быть тру", response.extract().path("ok"));

        //Залогинимся, чтобы получить id и заодно убедимся, что курьер создан
        loginId = AthorizationCourier(courier);
    }

    @Test
    public void createCourierNoFirstNameTest() {
        //Не будем заполнять firstName параметры
        courier.setLogin("RyzhkovTest_02");
        courier.setPassword("12345667890");

        ValidatableResponse response = clientCourier.create(courier);
        //Дальше проверки
        Assert.assertEquals("Ожидаем ответ 201, клиент создался",
                response.extract().statusCode(), HttpStatus.SC_CREATED /*201*/);
        Assert.assertTrue(
                "В теле параметра ok, должно быть тру", response.extract().path("ok"));

        //Залогинимся, чтобы получить id и заодно убедимся, что курьер создан
        loginId = AthorizationCourier(courier);
    }

    @Test
    public void createCourierNoPasswordTest() {
        //Не укажем пароль
        courier.setLogin("RyzhkovTest_03");
        courier.setFirstName("BoraBora");

        ValidatableResponse response = clientCourier.create(courier);
        //Дальше проверки
        Assert.assertEquals("Ожидаем ответ 400, ошибка",
                response.extract().statusCode(), HttpStatus.SC_BAD_REQUEST /*400 */);
        Assert.assertEquals("Недостаточно данных для создания учетной записи",
                response.extract().path("message"));
    }
    @Test
    public void createCourierPasswordNullTest() {
        //Не укажем пароль
        courier.setLogin("RyzhkovTest_04");
        courier.setPassword("");

        ValidatableResponse response = clientCourier.create(courier);
        //Дальше проверки
        Assert.assertEquals("Ожидаем ответ 400, ошибка",
                response.extract().statusCode(), HttpStatus.SC_BAD_REQUEST /*400 */);
        Assert.assertEquals("Недостаточно данных для создания учетной записи",
                response.extract().path("message"));
    }

    @Test
    public void createCourierOnlyPasswordTest() {
        //Укажем только пароль
        courier.setPassword("12345667890");

        ValidatableResponse response = clientCourier.create(courier);
        //Дальше проверки
        Assert.assertEquals("Ожидаем ответ 400, ошибка",
                response.extract().statusCode(), HttpStatus.SC_BAD_REQUEST /*400 */);
        Assert.assertEquals("Недостаточно данных для создания учетной записи",
                response.extract().path("message"));
    }

    @Test
    public void createCourierDoubleTest() {
        //Укажем только пароль
        courier.setLogin("RyzhkovTest_06");
        courier.setFirstName("SosoPavliashvili");
        courier.setPassword("12345667890");

        ValidatableResponse response = clientCourier.create(courier);
        //Дальше проверки
        Assert.assertEquals("Ожидаем ответ 201, ошибка",
                response.extract().statusCode(), HttpStatus.SC_CREATED /*201 */);
        Assert.assertTrue(
                "В теле параметра ok, должно быть тру", response.extract().path("ok"));

        //Залогинимся, чтобы получить id и заодно убедимся, что курьер создан
        loginId = AthorizationCourier(courier);

        //Теперь пробуем создать дубль
        ValidatableResponse response_double = clientCourier.create(courier);
        //Дальше проверки
        Assert.assertEquals("Ожидаем ответ 409, ошибка",
                response_double.extract().statusCode(), HttpStatus.SC_CONFLICT /*409 */);
        Assert.assertEquals(
                "Этот логин уже используется. Попробуйте другой.", response_double.extract().path("message"));
    }

    //Шаг одинаковый, поэтому для лакончиности теста завернул в обертку
    //to_do: Возможно понадобиться добавить блок исключений
    private Integer AthorizationCourier(Courier courier) {
    /*CourierAuthorization courierAuthorization =
            new CourierAuthorization(courier.getLogin(), courier.getPassword());
    ValidatableResponse loginResponse =
    clientCourier.login(courierAuthorization);*/
        ValidatableResponse loginResponse =
                clientCourier.login(CourierAuthorization.from(courier));
        Integer id;
        Assert.assertEquals("Ожидаем ответ 200, успешно",
                loginResponse.extract().statusCode(), HttpStatus.SC_OK /*200*/);
        id = loginResponse.extract().path("id");
        Assert.assertNotNull(id);

        return id;
    }
}
