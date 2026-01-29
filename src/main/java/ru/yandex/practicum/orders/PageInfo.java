package ru.yandex.practicum.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class PageInfo {
    private int page;
    private int total;
    private int limit;
}
