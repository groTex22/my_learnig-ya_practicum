package ru.praktikumService.qascooter;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class CreateOrderTest {
    ClientOrder clientOrder;
    Order order;
    String track;
    String color1;
    String color2;

    public CreateOrderTest(String color1, String color2){
        this.color1=color1;
        this.color2=color2;
    }

    @Before
    public void beforeTest() {
        //Создали объект clientOrder
        clientOrder = new ClientOrder();
        //Создаем объект для дальнейшей передачи в качестве JSON на эндпоинт
        //предзаполнив обязательные поля
        order = new Order("Капитан", "Флинт", "Остров сокровищ"
                , "Подземная","+71112223344", 5, "2024-08-18", "И бутылку Рома" );
    }

    @After
    public void afterTest() {
        //Отменим заказ
        clientOrder.cancelOrder(track) ;
    }

    @Parameterized.Parameters
    public static Object[] objects (){
        return new Object[][] {
                { "BLACK", "GREY"},
                { "GREY", null},
                { "BLACK", null},
                {null, null},
        };
    }


    @Test
    public void createOrderDiffColorTest(){
        //Подготовили файл
        String[] arr = {color1, color2};
        order.setColor(arr);
        //делаем запрос
        ValidatableResponse response = clientOrder.create(order);

        //Проверки на код и тело
        Assert.assertEquals("Ожидаем ответ 201, клиент создался",
                response.extract().statusCode(), HttpStatus.SC_CREATED /*201*/);
        track = response.extract().path("track");
        Assert.assertNotNull("В теле параметра есть параметр track", track);
    }
}
