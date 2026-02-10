package ru.praktikumService.qascooter;

import io.restassured.response.ValidatableResponse;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

//Класс для указния методов для объекта курьер
public class ClientCourier extends AbstractTestApi {

    private static final String COURIER_CREATE_ENDPOINT = "/api/v1/courier";
    private static final String COURIER_DELETE_ENDPOINT = "/api/v1/courier/{id}";
    private static final String COURIER_LOGIN_ENDPOINT = "/api/v1/courier/login";

    //тип ValidatableResponse используем, так как судя по документации
    //внутри него вшиты методы, которые потом нам удобно использовать
    @Step
    public ValidatableResponse create(Courier courier){
        return given()
                .spec(getSpec())
                .body(courier) //передаем инфу по курьеру
                .when()
                .post(COURIER_CREATE_ENDPOINT)
                .then();
    }
    @Step
    public void delete(Integer loginId){
         given()
                .spec(getSpec())
                .pathParam("id", loginId)
                .when()
                .delete(COURIER_DELETE_ENDPOINT);
    }

    @Step
    public ValidatableResponse login(CourierAuthorization courier){
        return given()
                .spec(getSpec())
                .body(courier) //передаем инфу по курьеру
                .when()
                .post(COURIER_LOGIN_ENDPOINT)
                .then();
    }
}
