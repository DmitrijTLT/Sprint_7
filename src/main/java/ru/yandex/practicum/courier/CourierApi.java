package ru.yandex.practicum.courier;
import io.restassured.response.Response;
import ru.yandex.practicum.BaseHttpClient;

public class CourierApi extends BaseHttpClient {
    private final String postCreateCourierPath = "/api/v1/courier";
    private final String postLoginCourierPath = "/api/v1/courier/login";
    private final String deleteCourierPath = "/api/v1/courier/{id}";

    public Response createCourier(Courier courier) {
        return doPostRequest(postCreateCourierPath, courier);
    }

    public Response createCourier() {
        return doPostRequestWithoutBody(postCreateCourierPath);
    }

    public Response createCouriereResponse(Courier courier) {
        return doPostRequest(postCreateCourierPath, courier);
    }

    public Response createCouriereResponse() {
        return doPostRequestWithoutBody(postCreateCourierPath);
    }

    public Response getLoginCourierResponse(Login login) {
        return doPostRequest(postLoginCourierPath, login);
    }

    public Response deleteCourierResponse(CourierDeleteId courierDeleteIdId, Integer courierId) {
        return doDeleteRequest(deleteCourierPath, courierDeleteIdId, courierId);
    }
}
