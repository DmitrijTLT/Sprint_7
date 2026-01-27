package ru.yandex.practicum.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import java.util.List;

public class OrderListTest {
    private OrderSteps steps = new OrderSteps();
    List<String> nearestStation = List.of("1", "2");
    Integer limit = 3;
    Integer page = 0;

    @Test
    public void orderListWithoutCourierIdTest() throws JsonProcessingException {
        steps.listOrderResponse(null, nearestStation, limit, page).checkOrderListResponse(3);
    }

    @Test
    public void allOrderListTest() throws JsonProcessingException {
        steps.listOrderResponse(null, null, null, null).checkOrderListResponse();
    }

    @Test
    public void orderListWithoutNearestStationTest() throws JsonProcessingException {
        steps.listOrderResponse(null, null, null, page).checkOrderListResponse(30);
    }
}
