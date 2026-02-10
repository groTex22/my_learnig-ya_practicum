package ru.praktikumService.qascooter;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

//Пока нужен только для того, чтобы в одном месте создавать спеку для всех мест
//откуда будет дергать эндпоинты
public abstract class AbstractTestApi {

    protected static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    //Создает спецификацию для запроса на эндпоинт
    protected static RequestSpecification getSpec(){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON) //Тип JSON
                .setBaseUri(BASE_URL) //Урла что тестим
                .build();
    }
}