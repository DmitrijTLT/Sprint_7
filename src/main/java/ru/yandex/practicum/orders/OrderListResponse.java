package ru.yandex.practicum.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListResponse {
    private List<Order> orders;
    private PageInfo pageInfo;
    private List<AvailableStation> availableStations;
}





