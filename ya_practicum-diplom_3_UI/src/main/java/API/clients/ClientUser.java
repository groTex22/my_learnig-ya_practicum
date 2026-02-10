package api.clients;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import json.User;
import json.UserEmailPassword;

import static io.restassured.RestAssured.given;

public class ClientUser {

    private static final String USER_AUTH_ENDPOINT = "https://stellarburgers.nomoreparties.site/api/auth/user";
    private static final String USER_LOGIN_ENDPOINT = "https://stellarburgers.nomoreparties.site/api/auth/login";
    private static final String USER_CREATE_ENDPOINT = "https://stellarburgers.nomoreparties.site/api/auth/register";

    @Step("deleteUser")
    public void deleteUser(String accessToken) {
         given().spec(getSpec())
                .header("Authorization", accessToken)
                .when()
                .delete(USER_AUTH_ENDPOINT);
    }

    @Step("loginUserReturnToken")
    public String loginUserReturnToken(UserEmailPassword loginUser) {
        return given()
                .spec(getSpec())
                .body(loginUser) //передаем инфу по курьеру
                .when()
                .post(USER_LOGIN_ENDPOINT)
                .then()
                .extract().path("accessToken");
    }
    @Step("loginUser")
    public void loginUser(String loginUser) {
         given()
                .spec(getSpec())
                .body(loginUser) //передаем инфу по курьеру
                .when()
                .post(USER_LOGIN_ENDPOINT)
                .then();
    }

    @Step("RegistrationUserReturnToken")
    public String createUser(User user) {
            return given()
                    .spec(getSpec())
                    .body(user) //передаем инфу по курьеру
                    .when()
                    .post(USER_CREATE_ENDPOINT)
                    .then()
                    .extract()
                    .path("accessToken");
    }

    //Создает спецификацию для запроса на эндпоинт
    protected static RequestSpecification getSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON) //Тип JSON
                .build();
    }
}
