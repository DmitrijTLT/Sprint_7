package ru.yandex.practicum.courier;
import io.restassured.response.Response;
import ru.yandex.practicum.BaseHttpClient;
import io.qameta.allure.Step;

public class CourierApi extends BaseHttpClient {
    private final String postCreateCourierPath = "/api/v1/courier";
    private final String postLoginCourierPath = "/api/v1/courier/login";
    private final String deleteCourierPath = "/api/v1/courier/{id}";

    @Step("Создание курьера")
    public Response createCourier(Courier courier) {
        return doPostRequest(postCreateCourierPath, courier);
    }

    @Step("Создание курьера без тела запроса")
    public Response createCourier() {
        return doPostRequestWithoutBody(postCreateCourierPath);
    }

    @Step("Авторизация курьера")
    public Response getLoginCourierResponse(Login login) {
        return doPostRequest(postLoginCourierPath, login);
    }

    @Step("Удаление курьера")
    public Response deleteCourierResponse(CourierDeleteId courierDeleteIdId, Integer courierId) {
        return doDeleteRequest(deleteCourierPath, courierDeleteIdId, courierId);
    }
}
