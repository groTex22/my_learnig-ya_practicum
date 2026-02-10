package ru.praktikumService.qascooter;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ClientOrder extends AbstractTestApi {

    private static final String ORDER_CREATE_ENDPOINT = "/api/v1/orders";
    private static final String ORDER_CANCEL_ENDPOINT = "/api/v1/orders/cancel";
    private static final String ORDER_GET_ENDPOINT = "/api/v1/orders";

    @Step
    public ValidatableResponse create(Order order) {
        return given()
                .spec(getSpec())
                .body(order) //передаем инфу по курьеру
                .when()
                .post(ORDER_CREATE_ENDPOINT)
                .then();
    }

    @Step
    public ValidatableResponse cancelOrder(String track) {
        return given()
                .spec(getSpec())
                .body(track) //передаем инфу по курьеру
                .when()
                .put(ORDER_CANCEL_ENDPOINT)
                .then();
    }

    @Step
    public Response getOrderLimit(Number limit) {
        return given()
                .spec(getSpec())
                .queryParam("limit", limit)
                .when()
                .get(ORDER_GET_ENDPOINT);
    }
}