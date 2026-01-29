package ru.yandex.practicum.orders;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.practicum.BaseHttpClient;

import java.util.List;


public class OrderApi extends BaseHttpClient {
    private final String postCreateOrderPath = "/api/v1/orders";
    private final String getListOrderPath = "/api/v1/orders";

    @Step("Создание заказа")
    public Response createOrder(OrdersCreate ordersCreate) {
        return doPostRequest(postCreateOrderPath, ordersCreate);
    }

    @Step("Получение списка заказов")
    public Response listOrderResponse(Integer courierId, List<String> nearestStation, Integer limit, Integer page) throws JsonProcessingException {
        return doGetRequest(getListOrderPath, courierId, nearestStation, limit, page);
    }
}
