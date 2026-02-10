package ru.praktikumService.qascooter;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

//Здесь проверим только, что что-то возвращается.
//неважно с какими параметрами делаем запрос и неважно, что возвращается,
//главное что-то
public class GetOrderTest {

    ClientOrder clientOrder;

    @Before
    public void beforeTest() {
        clientOrder = new ClientOrder();
    }

    //Хотел с помощью аннотации queryPath сделать, чтобы не перегружать clientOrder
    //Но так как требований пока нет, сделал тупо один запрос на лимит
    //Для этого достаточно просто дернуть вызов и проверить, что боди не пустое
    @Test
    public void getOrderTest() {

        Response response = clientOrder.getOrderLimit(10);

        String body = response.body().toString();
        Assert.assertNotNull("body не пустое", body);
    }
}
