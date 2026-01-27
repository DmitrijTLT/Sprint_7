package ru.yandex.practicum.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.ValidatableResponse;
import ru.yandex.practicum.orders.OrderApi;
import ru.yandex.practicum.orders.OrderListResponse;
import ru.yandex.practicum.orders.OrdersCreate;
import ru.yandex.practicum.orders.OrdersCreateResponse;
import java.util.List;

import static org.junit.Assert.*;

public class OrderSteps {
    private OrderApi orderApi = new OrderApi();
    private ValidatableResponse response;

    public OrderSteps createOrder(OrdersCreate ordersCreate){
        response = orderApi.createOrder(ordersCreate).then();
        return this;
    }

    public OrderSteps listOrderResponse(Integer courierId, List<String> nearestStation, Integer limit, Integer page) throws JsonProcessingException {
        response = orderApi.listOrderResponse(courierId, nearestStation, limit, page).then();
        return this;
    }

    public OrderSteps checkOrderResponse() {
        response.assertThat().statusCode(201);
        OrdersCreateResponse ordersCreateResponseFromApi = response.extract().body().as(OrdersCreateResponse.class);
        assertNotNull("Ответ сервера должен содержать id", ordersCreateResponseFromApi.getTrack());
        assertTrue("id курьера должен быть положительным числом", ordersCreateResponseFromApi.getTrack() > 0);
        return this;
    }

    public OrderSteps checkOrderListResponse(int expectedOrdersCount) {
        response.assertThat().statusCode(200);
        OrderListResponse orderListResponseFromApi = response.extract().body().as(OrderListResponse.class);
        assertNotNull("Массив 'orders' не должен быть пустым", orderListResponseFromApi.getOrders());
        assertNotNull("Объект 'pageInfo' не должен быть пустым", orderListResponseFromApi.getPageInfo());
        assertNotNull("Массив 'availableStations' не должен быть пустым", orderListResponseFromApi.getAvailableStations());
        int actualOrdersCount = orderListResponseFromApi.getOrders().size();
        assertEquals(
                String.format("Ожидаемое количество заказов: %d, фактическое: %d", expectedOrdersCount, actualOrdersCount),
                expectedOrdersCount,
                actualOrdersCount
        );
        return this;
    }

    public OrderSteps checkOrderListResponse() {
        response.assertThat().statusCode(200);
        OrderListResponse orderListResponseFromApi = response.extract().body().as(OrderListResponse.class);
        assertNotNull("Массив 'orders' не должен быть пустым", orderListResponseFromApi.getOrders());
        assertNotNull("Объект 'pageInfo' не должен быть пустым", orderListResponseFromApi.getPageInfo());
        assertNotNull("Массив 'availableStations' не должен быть пустым", orderListResponseFromApi.getAvailableStations());

        return this;
    }
}
